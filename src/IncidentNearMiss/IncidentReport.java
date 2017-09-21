package IncidentNearMiss;

import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Row;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentReports;

import common_Function.RW;

public class IncidentReport  extends RW {

	private static ExtentReports report;

	public synchronized static ExtentReports getReporter(String filePath) {

		if (report == null) {
			report = new ExtentReports(path.concat("Report\\Cars.html"));

			report.addSystemInfo("Host Name", "Priti") // Environment Setup For
														// Report
					.addSystemInfo("Environment", "QA");
		}

		return report;
	}

	public void IncidentReports(WebDriver driver1) throws Exception {
		// Get URLPage
		driver1.get("http://192.168.1.102/JIBE/Technical/JMS/JMS_Index.aspx");

		// Create Multiple type Incident Report
		//MultipleIncidentReport(driver1);
		// Edit Multiple Incident Report
	     //  EditIncidentReport(driver1);
		
		// Delete Incident Report using ID
		//DeleteIncidentReport(driver1);
		
		// Check All Field Validation On Incident Report 
		ValidationOnIncidentRepor(driver1);
	}
	
	
	private void MultipleIncidentReport(WebDriver driver1) throws Exception {

		ArrayList<Row> locator = OR_Locator.searchSheet("MultipleIncidentReport", 1, 9);

		ArrayList<Row> Inputdata = input_Data.searchSheet("MultipleIncidentReport", 2, 0);
		// Loop for mumtiple Validation
		for (int TestCase = 1; TestCase < Inputdata.size(); TestCase++) {
			driver1.get("http://192.168.1.102/JIBE/Technical/JMS/JMS_Index.aspx");
			NavigateLocators(driver1, locator, Inputdata, TestCase);
		}
	}

	private void EditIncidentReport(WebDriver driver1) throws Exception {

		ArrayList<Row> locator = OR_Locator.searchSheet("EditIncidentReport", 1, 9);

		ArrayList<Row> Inputdata = input_Data.searchSheet("EditIncidentReport", 2, 0);
		// Loop for mumtiple Validation
		for (int TestCase = 1; TestCase < Inputdata.size(); TestCase++) {
			driver1.get("http://192.168.1.102/JIBE/Technical/JMS/JMS_Index.aspx");
			NavigateLocators(driver1, locator, Inputdata, TestCase);
		}

	}

	private void DeleteIncidentReport(WebDriver driver1) throws Exception {

		ArrayList<Row> locator = OR_Locator.searchSheet("DeleteIncidentReport", 1, 9);

		ArrayList<Row> Inputdata = input_Data.searchSheet("DeleteIncidentReport", 2, 0);
		// Loop for mumtiple Validation
		for (int TestCase = 1; TestCase < Inputdata.size(); TestCase++){
			NavigateLocators(driver1, locator, Inputdata, TestCase);
		}

	}

	private void ValidationOnIncidentRepor(WebDriver driver1) throws Exception {

		ArrayList<Row> locator = OR_Locator.searchSheet("NCreateNewJob", 1, 9);

		ArrayList<Row> Inputdata = input_Data.searchSheet("NCreateNewJob", 2, 0);
		// Loop for Negative Validation
		for (int TestCase = 1; TestCase < Inputdata.size(); TestCase++) {
			driver1.get("http://192.168.1.102/JIBE/Technical/JMS/JMS_Index.aspx");
			NavigateLocators(driver1, locator, Inputdata, TestCase);
		}
		
		ArrayList<Row> locator1 = OR_Locator.searchSheet("PCreateNewJob", 1, 9);

		ArrayList<Row> Inputdata1 = input_Data.searchSheet("PCreateNewJob", 2, 0);
		// Loop for Positive Validation
		for (int TestCase = 1; TestCase < Inputdata1.size(); TestCase++) {
			driver1.get("http://192.168.1.102/JIBE/Technical/JMS/JMS_Index.aspx");
			NavigateLocators(driver1, locator1, Inputdata1, TestCase);
		}
	}
	private void NavigateLocators(WebDriver driver1, ArrayList<Row> locator, ArrayList<Row> Inputdata, int TestCase)
			throws Exception {

		for (int i = 0; i < locator.size(); i++) {

			// Get type of element (dropdown, text)
			String strControlTypeKey = locator.get(i).getCell(10).toString();

			if (strControlTypeKey != null) {

				// Get property of element
				String LocatorName = locator.get(i).getCell(0).getStringCellValue();

				// Get property of element
				String LocatorType = locator.get(i).getCell(1).getStringCellValue();

				// Get client ID of the element
				String strControl = locator.get(i).getCell(2).getStringCellValue();

				String InputValue = Inputdata.get(TestCase).getCell(i + 1).toString();

				// Get Sleep Time
				Long SleepTime = (long) locator.get(i).getCell(4).getNumericCellValue();

				// Button Click and navigate page

				if (strControlTypeKey.compareTo("Click_Ctrl") == 0) {
					click_element(driver1, LocatorType, strControl);
					Thread.sleep(SleepTime);
				}
				// Text
				if (strControlTypeKey.compareTo("Dropdown_ctrl") == 0) {
					sendkeys(driver1, LocatorType, strControl, InputValue);
					Thread.sleep(SleepTime);
				}
				if (strControlTypeKey.compareTo("Clear_Ctrl") == 0) {
					clear_element(driver1, LocatorType, strControl);
					Thread.sleep(k);
				}
				if (strControlTypeKey.compareTo("SendKey_Ctrl") == 0) {
					sendkeys(driver1, LocatorType, strControl, InputValue);
					Thread.sleep(SleepTime);
				}

				if (strControlTypeKey.compareTo("Alert_accept") == 0) {
					click_element(driver1, LocatorType, strControl);
					Thread.sleep(SleepTime);
					Alert_accept(driver1);
					Thread.sleep(SleepTime);
				}
				if (strControlTypeKey.compareTo("WindowSwitch_Ctrl") == 0) {
					click_element(driver1, LocatorType, strControl);
					Thread.sleep(SleepTime);
					WindowSwitchto(driver1);
				}
				if (strControlTypeKey.compareTo("Upload_Ctrl") == 0) {
					uploadFile(driver1, LocatorType, strControl, strControl, InputValue, strControl);
					Thread.sleep(SleepTime);

				}
				if (strControlTypeKey.compareTo("FrameSwitch_Ctrl") == 0) {

					frameSwitchto(driver1, LocatorType, strControl);
					Thread.sleep(SleepTime);
				}

				if (strControlTypeKey.compareTo("FrameSwitchBack_Ctrl") == 0) {
					click_element(driver1, LocatorType, strControl);
					Thread.sleep(SleepTime);
					frameSwitchBack(driver1);
					Thread.sleep(SleepTime);

				}
				if (strControlTypeKey.compareTo("Refresh_Ctrl") == 0) {
					refresh(driver1);
					Thread.sleep(SleepTime);
				}

				if (strControlTypeKey.compareTo("SwitchtoParent") == 0) {
					click_element(driver1, LocatorType, strControl);
					Thread.sleep(SleepTime);
					Alert_accept(driver1);
					Thread.sleep(SleepTime);
					SwitchtoParent(driver1);
					Thread.sleep(SleepTime);
				}
			}

		}
	}

}
