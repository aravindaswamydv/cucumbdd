package dataProvider;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.junit.Assert;

import enums.DriverType;
import functionLibrary.JavaGeneralUtilities;

public class ConfigFileReader {

	private Properties properties;
	private String propertyFilePath="\\src\\test\\resources\\configs\\Configuation.properties";
	JavaGeneralUtilities genUtl;

	public ConfigFileReader(){
		BufferedReader reader;
		genUtl= new JavaGeneralUtilities();
		
		try {
			propertyFilePath= genUtl.getUserdirectory() + propertyFilePath;			
			reader = new BufferedReader(new FileReader(propertyFilePath));
			properties = new Properties();
			try {
				properties.load(reader);
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
		}		
	}

	public String getDriverPath_GC(){
		String driverPath = genUtl.getUserdirectory()+properties.getProperty("GCdriverPath");
		if(driverPath.isEmpty()) 
		throw new RuntimeException("Driver Path not specified in the Configuration.properties file for the Key: driverPath");
		return driverPath;
	}
	
	public String getDriverPath_FF(){
		String FFdriverPath = genUtl.getUserdirectory()+properties.getProperty("FFdriverPath");
		if(FFdriverPath.isEmpty()) 
		throw new RuntimeException("Firefox Driver Path not specified in the Configuration.properties file for the Key: FFdriverPath");
		return FFdriverPath;
	}
	
	public String getDriverPath_IE(){
		String IEdriverPath = genUtl.getUserdirectory()+properties.getProperty("IEdriverPath");
		if(IEdriverPath.isEmpty()) 
		throw new RuntimeException("IE Driver Path not specified in the Configuration.properties file for the Key: IEdriverPath");
		return IEdriverPath;
	}


	public String getApplicationUrl() {
		String url = properties.getProperty("url");
		if(url != null) return url;
		else throw new RuntimeException("Application Url not specified in the Configuration.properties file for the Key:url");
	}
	
	public DriverType getBrowser() {
		String browserName = properties.getProperty("browser");
		if(browserName == null || browserName.equals("chrome")) return DriverType.CHROME;
		else if(browserName.equalsIgnoreCase("firefox")) return DriverType.FIREFOX;
		else if(browserName.equals("iexplorer")) return DriverType.INTERNETEXPLORER;
		else if(browserName.equals("safari")) return DriverType.SAFARI;
		else throw new RuntimeException("Browser Name Key value in Configuration.properties is not matched : " + browserName);
	}
	
	public String getUserName() {
		String userName = properties.getProperty("userName");
		if(userName != null) return userName;
		else throw new RuntimeException("User Name not specified in the Configuration.properties file for the Key: userName");
	}
	
	public String getPassword() {
		String password = properties.getProperty("password");
		if(password != null) return password;
		else throw new RuntimeException("Password not specified in the Configuration.properties file for the Key: password");
	}
	
	/**
	 * Method Name 	: getAutoITFilePath
	 * Purpose		: This Method returns the Auto IT file path
	 * @return 		: filePath
	 * @author 		: EVRY
	 */
	public String getUserNameWithoutActiveDirectory() {
		String userNameWithoutActiveDirectory = properties.getProperty("userNameWithoutActiveDirectory");
		if(userNameWithoutActiveDirectory != null) return userNameWithoutActiveDirectory;
		else throw new RuntimeException("Username without ActiveDirectory not specified in the Configuration.properties file for the Key: userNameWithoutActiveDirectory");
	}
	
	/**
	 * Method Name 	: getAutoITFilePath
	 * Purpose		: This Method returns the Auto IT file path
	 * @return 		: filePath
	 * @author 		: EVRY
	 */
	public String getReportConfigPath(){
		String reportConfigPath = genUtl.getUserdirectory()+ properties.getProperty("reportConfigPath");
		if(reportConfigPath.isEmpty())
			Assert.assertTrue("Report Config Path not specified in the Configuration.properties file for the Key: reportConfigPath",false);
			return reportConfigPath;			
	}
	
	/**
	 * Method Name 	: getAutoITFilePath
	 * Purpose		: This Method returns the Auto IT file path
	 * @return 		: filePath
	 * @author 		: EVRY
	 */
	public String getAutoITFilePath(){
		String filePath = genUtl.getUserdirectory() + properties.getProperty("AutoITFilePath");
		if(filePath.isEmpty())
		 throw new RuntimeException("AutoIT exe file path not specified in the Configuration.properties file for the Key: AutoITFilePath");
		 return filePath;
	}
	
	/**
	 * Method Name 	: getUploadFilePath
	 * Purpose		: This Method returns the upload file path
	 * @return 		: filePath
	 * @author 		: EVRY
	 */
	public String getUploadFilePath(){
		String filePath = genUtl.getUserdirectory() + properties.getProperty("UploadFilePath");
		if(filePath.isEmpty()) 
		throw new RuntimeException("Upload file path not specified in the Configuration.properties file for the Key: UploadFilePath");
		return filePath;
	}
	
	/**
	 * Method Name 	: getImageFilePath
	 * Purpose		: This Method returns the image file path
	 * @return 		: filePath
	 * @author 		: EVRY
	 */
	public String getImageFilePath(){
		String filePath = genUtl.getUserdirectory() + properties.getProperty("ImageFilePath");
		if(filePath.isEmpty()) 
		throw new RuntimeException("Image file path not specified in the Configuration.properties file for the Key: ImageFilePath");
		return filePath;
	}

	/**
	 * Method Name 	: getDownloadFilePath
	 * Purpose		: This Method returns the delete file path
	 * @return 		: filePath
	 * @author 		: EVRY
	 */
	public String getDeleteFilePath() {

		String filePath = genUtl.getUserdirectory() + properties.getProperty("DeleteFilePath");

		if (filePath.isEmpty())
			throw new RuntimeException(
					"Delete file path not specified in the Configuration.properties file for the Key: DeleteFilePath");
		return filePath;

	}
	
	/**
	 * Method Name 	: getDownloadFilePath
	 * Purpose		: This Method returns the path for download files
	 * @return 		: filePath
	 * @author 		: EVRY
	 */
	public String getDownloadFilePath(){
		String filePath = genUtl.getUserdirectory() + properties.getProperty("downloadPath");
		if(filePath.isEmpty())
		throw new RuntimeException("Download file path not specified in the Configuration.properties file for the Key: downloadPath");
		 return filePath;
	}
	
	
	/**
	 * Method Name 	: getScreenshotFolderPath
	 * Purpose		: This Method returns the screenshot folder path
	 * @return 		: screenshotfolderpath
	 * @author 		: EVRY
	 */
	public String getScreenshotFolderPath(){
		String screenshotfolderpath = genUtl.getUserdirectory() + properties.getProperty("screenshotFolderPath");
		if(screenshotfolderpath.isEmpty())
		 throw new RuntimeException("Screenshot folder path not specified in the Configuration.properties");
		 return screenshotfolderpath;
	}
	
	
	/**
	 * Method Name 	: getBaseAPIurl
	 * Purpose		: This Method returns the Base URL for APIs
	 * @return 		: apiBaseURL
	 * @author 		: EVRY
	 */
	public String getBaseAPIurl() {
		String baseAPIURL = properties.getProperty("apiBaseURL");
		Assert.assertNotNull("Base URL for API not specified!", baseAPIURL);
		return baseAPIURL;
	
	}
	
	/**
	 * Method Name 	: getLoginAPI
	 * Purpose		: This Method returns the path for login API
	 * @return 		: loginAPI_Path
	 * @author 		: EVRY
	 */
	public String getLoginAPI() {
		String loginAPI_Path = properties.getProperty("loginApi");
		Assert.assertNotNull("Login API path is not specified", loginAPI_Path);
		return loginAPI_Path;
	
	}
	
	/**
	 * Method Name 	: getCurrentArrearsVSBillingAPI
	 * Purpose		: This Method returns the API path for Current Arrears Vs Billing widget
	 * @return 		: currentArrearsBillingAPI_Path
	 * @author 		: EVRY
	 */
	public String getCurrentArrearsVSBillingAPI() {
		String currentArrearsBillingAPI_Path = properties.getProperty("currentArrearsBillingAPI");
		Assert.assertNotNull("Current Arrears Vs Billing API path is not specified", currentArrearsBillingAPI_Path);
		return currentArrearsBillingAPI_Path;
	}
	
	/**
	 * Method Name 	: getBuildingsAPI
	 * Purpose		: This Method returns the Building API path
	 * @return 		: buildingsAPI_Path
	 * @author 		: EVRY
	 */
	public String getBuildingsAPI() {
		String buildingsAPI_Path = properties.getProperty("getBuildingsApi");
		Assert.assertNotNull("Buildings API path is not specified", buildingsAPI_Path);
		return buildingsAPI_Path;
	}
	
	/**
	 * Method Name 	: getCurrentArrearsBillingAPI
	 * Purpose		: This Method returns the Current Arrears Billing API path
	 * @return 		: currentArrearsBillingAPI_Path
	 * @author 		: EVRY
	 */
	public String getCurrentArrearsBillingAPI() {
		String currentArrearsBillingAPI_Path = properties.getProperty("currentArrearsBillingAPI");
		Assert.assertNotNull("Current Arrears Billing API path is not specified", currentArrearsBillingAPI_Path);
		return currentArrearsBillingAPI_Path;
	}

	/**
	 * Method Name 	: getNOITrendAPI
	 * Purpose		: This Method returns the NOI Trend API path
	 * @return 		: noiTrendAPI_Path
	 * @author 		: EVRY
	 */
	public String getNOITrendAPI() {
		String noiTrendAPI_Path = properties.getProperty("noiTrendAPI");
		Assert.assertNotNull("NOI Trend API path is not specified", noiTrendAPI_Path);
		return noiTrendAPI_Path;
	}
	
	/**
	 * Method Name 	: getoccupancyAPI
	 * Purpose		: This Method returns the Occupancy API path
	 * @return 		: occupancyAPI_Path
	 * @author 		: EVRY
	 */
	public String getOccupancyAPI() {
		String occupancyAPI_Path = properties.getProperty("occupancyAPI");
		Assert.assertNotNull("NOI Trend API path is not specified", occupancyAPI_Path);
		return occupancyAPI_Path;
	}	

	/**
	 * Method Name 	: getKeyDatesAPI
	 * Purpose		: This Method returns the Key Dates / Risk items API path
	 * @return 		: keyDatesAPI_Path
	 * @author 		: EVRY
	 */
	public String getKeyDatesAPI() {
		String keyDatesAPI_Path = properties.getProperty("keyDatesAPI");
		Assert.assertNotNull("Key Dates API path is not specified", keyDatesAPI_Path);
		return keyDatesAPI_Path;
	}	
	
	/**
	 * Method Name 	: getRetailOccupancyCostToSalesAPI
	 * Purpose		: This Method returns the Retail Occupancy Cost to Sales API path
	 * @return 		: retailOccupancyCostToSalesAPI_Path
	 * @author 		: EVRY
	 */
	public String getRetailOccupancyCostToSalesAPI() {
		String retailOccupancyCostToSalesAPI_Path = properties.getProperty("retailOccupancyCostToSalesAPI");
		Assert.assertNotNull("Retail Occupancy Cost to Sales API path is not specified", retailOccupancyCostToSalesAPI_Path);
		return retailOccupancyCostToSalesAPI_Path;
	}	
	
	/**
	 * Method Name 	: getRetailSalesPerCustAPI
	 * Purpose		: This Method returns the Retail Sales per Customer API path
	 * @return 		: retailSalesPerCustAPI_Path
	 * @author 		: EVRY
	 */
	public String getRetailSalesPerCustAPI() {
		String retailSalesPerCustAPI_Path = properties.getProperty("retailSalesPerCustAPI");
		Assert.assertNotNull("Retail Sales per Customer API path is not specified", retailSalesPerCustAPI_Path);
		return retailSalesPerCustAPI_Path;
	}	
	
	/**
	 * Method Name 	: getRetail_MAT_SQM_API
	 * Purpose		: This Method returns the Retail MAT/SQM API path
	 * @return 		: retail_MAT_SQM_API_Path
	 * @author 		: EVRY
	 */
	public String getRetail_MAT_SQM_API() {
		String retail_MAT_SQM_API_Path = properties.getProperty("retail_MAT_SQM_API");
		Assert.assertNotNull("Retail MAT/SQM API path is not specified", retail_MAT_SQM_API_Path);
		return retail_MAT_SQM_API_Path;
	}	
	
	
	/**
	 * Method Name 	: getRetailSalesPerUOM
	 * Purpose		: This Method returns the Retail Sales per UOM API path
	 * @return 		: retailSalesPerUOM_Path
	 * @author 		: EVRY
	 */
	public String getRetailSalesPerUOM() {
		String retailSalesPerUOM_Path = properties.getProperty("retailSalesPerUOM");
		Assert.assertNotNull("Retail Sales per UOM API path is not specified", retailSalesPerUOM_Path);
		return retailSalesPerUOM_Path;
	}	
	
	/**
	 * Method Name 	: getEditUploadFilePath
	 * Purpose		: This Method returns the Edit Upload File path
	 * @return 		: filePath
	 * @author 		: EVRY
	 */
	public String getEditUploadFilePath(){
		String filePath = genUtl.getUserdirectory() + properties.getProperty("EditUploadFilePath");
		if(filePath.isEmpty()) 
		throw new RuntimeException("Edit Upload file path not specified in the Configuration.properties file for the Key: EditUploadFilePath");
		return filePath;
	}
	
	/**
	 * Method Name 	: getOCInvoiceVsCash_API
	 * Purpose		: This Method returns the OC Invoice Vs Cash API path
	 * @return 		: ocInvoiceVsCash_Path
	 * @author 		: EVRY
	 */
	public String getOCInvoiceVsCash_API() {
		String ocInvoiceVsCash_Path = properties.getProperty("ocInvoiceVsCash");
		Assert.assertNotNull("OC Invoice Vs Cash API path is not specified", ocInvoiceVsCash_Path);
		return ocInvoiceVsCash_Path;
	}	
	
	/**
	 * Method Name 	: getIncomeMTDvsBudget_API
	 * Purpose		: This Method returns the Income MTD VS Budget API path
	 * @return 		: incomeMTDvsBudget_API_Path
	 * @author 		: EVRY
	 */
	public String getIncomeMTDvsBudget_API() {
		String incomeMTDvsBudget_API_Path = properties.getProperty("incomeMTDvsBudget");
		Assert.assertNotNull("Income MTD VS Budge API path is not specified", incomeMTDvsBudget_API_Path);
		return incomeMTDvsBudget_API_Path;
	}	
	
	
	/**
	 * Method Name 	: getExpenseMTDvsBudget_API
	 * Purpose		: This Method returns the Expense MTD VS Budget API path
	 * @return 		: expenseMTDvsBudget_API_Path
	 * @author 		: EVRY
	 */
	public String getExpenseMTDvsBudget_API() {
		String expenseMTDvsBudget_API_Path = properties.getProperty("expenseMTDvsBudget");
		Assert.assertNotNull("Expense MTD VS Budget API path is not specified", expenseMTDvsBudget_API_Path);
		return expenseMTDvsBudget_API_Path;
	}	


	/**
	 * Method Name 	: getWaleArea
	 * Purpose		: This Method returns the WALE Area File path
	 * @return 		: waleArea_Path
	 * @author 		: EVRY
	 */
	public String getWaleArea(){
		String waleArea_Path = properties.getProperty("waleArea");
		Assert.assertNotNull("WALE Area path is not specified", waleArea_Path);
		return waleArea_Path;
	}
	
	/**
	 * Method Name 	: getWaleRent
	 * Purpose		: This Method returns the WALE Rent path
	 * @return 		: waleRent_Path
	 * @author 		: EVRY
	 */
	public String getWaleRent() {
		String waleRent_Path = properties.getProperty("waleRent");
		Assert.assertNotNull("WALE Rent path is not specified", waleRent_Path);
		return waleRent_Path;
	}	
	
	/**
	 * Method Name 	: getProprityWorkOrders
	 * Purpose		: This Method returns the Priority Work Orders path
	 * @return 		: priorityWorkOrders_Pathl
	 * @author 		: EVRY
	 */
	public String getProprityWorkOrders(){
		String priorityWorkOrders_Path = properties.getProperty("proprityWorkOrders");
		Assert.assertNotNull("Priority Work Orders path is not specified", priorityWorkOrders_Path);
		return priorityWorkOrders_Path;
	}
}
