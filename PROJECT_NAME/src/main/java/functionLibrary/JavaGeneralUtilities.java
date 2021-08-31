package functionLibrary;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.search.FlagTerm;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.cucumber.listener.Reporter;


public class JavaGeneralUtilities {
	
	/**
	 * Method name : isFileExist
	 * Purpose     : Method checks whether a file exist in a given file path
	 * @param      : filePath
	 * @return     : returns boolean value true if file existing otherwise false
	 * @author     : EVRY
	 */
	public boolean isFileExist(String filePath) {
		File path = new File(filePath);
		return path.exists();
	}

	/**
	 * Method name : getUserHomedirectory
	 * Purpose     : Method returns the user home directory
	 * @param      : None
	 * @return     : returns String
	 * @author     : EVRY
	 */
	public String getUserHomedirectory() {
		return System.getProperty("user.home");
	}

	/**
	 * Method name : getFilenames
	 * Purpose     : Method returns the file names in a given zip folder path
	 * @param      : folderPath
	 *              - A string value represent location of the Zip folder
	 * @return     : array of string representing the filenames in a Zip folder
	 * @author     : EVRY
	 */
	public String[] getFilenames(String folderPath) throws IOException {
		String[] fileName = null;
		int totalFiles = 0;
		int i = 0;
		@SuppressWarnings("resource")
		ZipFile zipFile = new ZipFile(folderPath);
		Enumeration zipEntries = zipFile.entries();
		totalFiles = zipFile.size();
		fileName = new String[totalFiles];
		while (zipEntries.hasMoreElements()) {
			fileName[i] = ((ZipEntry) zipEntries.nextElement()).getName();
			i++;

		}
		return fileName;
	}

	/**
	 * Method name : isNumeric
	 * Purpose     : Method checks whether given string is a number or not
	 * @param      : Str - string variable having a number
	 * @return     : boolean true if given string is a number otherwise returns false
	 * @author     : EVRY
	 */
	public boolean isNumeric(String str) {
		try {
			double d = Double.parseDouble(str);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

	/**
	 * Method name : getLatestFilefromDir
	 * Purpose     : Method returns the latest updated file in a given directory path
	 * @param      : dirPath
	 * @return     : lastModifiedFile
	 * @author     : EVRY
	 */
	public File getLatestFilefromDir(String dirPath) {
		File dir = new File(dirPath);
		File[] files = dir.listFiles();
		if (files == null || files.length == 0) {
			return null;
		}

		File lastModifiedFile = files[0];
		for (int i = 1; i < files.length; i++) {
			if (lastModifiedFile.lastModified() < files[i].lastModified()) {
				lastModifiedFile = files[i];
			}
		}
		// return lastModifiedFile.getAbsolutePath();
		return lastModifiedFile;
	}

	/**
	 * Method name : appendTimeStamp
	 * Purpose     : Method appends the time stamp (with format: ddMMyyyyHHmmss) to the
	 * text which is passed to it and returns the same text with time stamp
	 * @param      : text
	 * @return     : textWithTimestamp - A String value appended with time stamp
	 * @author     : EVRY
	 */
	public String appendTimeStamp(String text) {
		String textWithTimestamp = "";
		final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("ddMMyyyyHHmmss");
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		textWithTimestamp = text + simpleDateFormat.format(timestamp);
		return textWithTimestamp;
	}

	/**
	 * Method name : getTodayDate
	 * Purpose     : Method returns the current date with format dd/MM/yyyy
	 * @param      : None
	 * @return     : todaysDate - A String value contains today's date in dd/MM/yyyy format
	 * @author     : EVRY
	 */
	public String getTodayDate() {
		String todaysDate = "";
		final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		todaysDate = simpleDateFormat.format(date);
		return todaysDate;
	}
	
	/**
	 * Method name : getTodayDate
	 * Purpose     : Method returns the current date with format dd-MM-yyyy
	 * @param      : None
	 * @return     : todaysDate - A String value contains today's date in dd-MM-yyyy format
	 * @author     : EVRY
	 */
	public String getTodayDate_ddmmyyyy() {
		String todaysDate = "";
		final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
		Date date = new Date();
		todaysDate = simpleDateFormat.format(date);
		return todaysDate;
	}

	/**
	 * Method name : getScreenshot
	 * Purpose     : Method capture the screenshot and save to provided location and return the path of the screenshot
	 * @param      : screenshotFolderPath - location of screenshot to be saved
	 *               fileName - name to be given to screenshot
	 * @return     : screenImg  - location of screen Image that is Saved
	 * @author     : EVRY
	 */
	public String getScreenshot(WebDriver webDriver, String screenshotFolderPath, String fileName) {
		String screenImg = null;

		screenImg = screenshotFolderPath + fileName + ".png";
		// Take screenshot and store as a file format
		File src = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
		try {
			// now copy the screenshot to desired location using copyFile
			FileUtils.copyFile(src, new File(screenImg));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch(Exception e){
			System.out.println(e.getMessage());
		}
		return screenImg;
	}

	/**
	 * Method name : getUserdirectory
	 * Purpose     : Method returns the current user directory
	 * @param      : None
	 * @return     : current user directory
	 * @author     : EVRY
	 */
	public String getUserdirectory() {

		return System.getProperty("user.dir");

	}
	
	/**
	 * Method name : getFileNameFromPath
	 * Purpose     : This method returns the file name from the absolute file path passed to it
	 * @param      : None
	 * @return     : current user directory
	 * @author     : EVRY
	 */
	public String getFileNameFromPath(String filePath) {
		String fileName="";
		File f = new File(filePath);
		fileName= f.getName();
		return fileName;
	}
	

	/**
	 * Method name : attachScreenshot
	 * Purpose     : Method to attach screenshot
	 * @param      : screenPath
	 * @return     : None
	 * @author     : EVRY
	 */
	public void attachScreenshot(String screenPath) {

		try {
			Reporter.addScreenCaptureFromPath(screenPath, "ClickHereToShowScreenshot");

		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	/**
	 * Method to fetch the OTP which is sent to email box.
	 * @return
	 */
	private String getOTP() {
		String code="";
		String result="";
		String user="username", pwd="password";
		try {
			Session session = Session.getDefaultInstance(new Properties());
			Store store= session.getStore("imaps");
			store.connect("outlook.office365.com",993,user,pwd);		
			Folder inbox = store.getFolder("INBOX");
			inbox.open(Folder.READ_ONLY);
			// Fetch unseen messages from inbox folder
			Message[] messages = inbox.search(new FlagTerm(new Flags(Flags.Flag.SEEN),false));
			System.out.println("No of emails" + messages.length);
			// sort messages from old to recent
			Arrays.sort(messages,(m1,m2)->{
				try {
					return m2.getSentDate().compareTo(m2.getSentDate());
				}catch(MessagingException e) {
					throw new RuntimeException(e);
				}
			});
			for(Message message:messages) {
				if(message.getSubject().contains("Sandox: verfication code")) {
					//message.getContentType();
					result=message.getContent().toString();
					code=StringUtils.substringBetween(result,"Verfication Code:","If u dint login to SF");
					code=code.trim();
					inbox.close(false);
					store.close();
					break;
				}
			}
		}catch(NoSuchProviderException e) {
			System.out.println("count not find Proider");
			e.printStackTrace();
		}catch(MessagingException me) {
			System.out.println("count not connect to message store");
			me.printStackTrace();
		}catch(IOException ie) {
			ie.printStackTrace();
		}
		
		return code;
	}
	
	
	public void sendEmailHtmlReport(String subject, String emailbodyTxt, String reportFilePath) {

		String toEmail = "aravindaswamy.doddamane@tietoevry.com";
		String from = "fromName";
		String host = "mail.companyname.com"; // e.g:  mail.spanservices.com
		String fileName = "";
		Properties properties = System.getProperties();
		properties.setProperty("mail.smtp.host", host);
		properties.setProperty("mail.smtp.port", "25");
		Session session = Session.getDefaultInstance(properties);
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
			message.setSubject(subject);

			// create message part
			MimeBodyPart msgBodyPart = new MimeBodyPart();
			msgBodyPart.setContent(emailbodyTxt, "text/html");

			// create Multi part
			Multipart multiPart = new MimeMultipart();
			multiPart.addBodyPart(msgBodyPart);

			// attaching html
			msgBodyPart = new MimeBodyPart();
			fileName = reportFilePath;

			DataSource dataSource = new FileDataSource(fileName);
			msgBodyPart.setDataHandler(new DataHandler(dataSource));
			msgBodyPart.setFileName(new File(fileName).getName());
			multiPart.addBodyPart(msgBodyPart);
			message.setContent(multiPart);

			// send Msg
			Transport.send(message);
			System.out.println("Message sent successfully");

		} catch (MessagingException me) {
			me.printStackTrace();
		}

	}
}
