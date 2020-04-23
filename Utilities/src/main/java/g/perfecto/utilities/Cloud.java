package g.perfecto.utilities;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.remote.RemoteWebDriver;

public class Cloud
{
	private RemoteWebDriver driver = null;
	
	private final String COMMAND_GATEWAYCALL = "mobile:gateway:call";
	private final String COMMAND_GATEWAYEMAIL = "mobile:gateway:email";
	private final String COMMAND_GATEWAYSMS = "mobile:gateway:sms";
	
	public Cloud(RemoteWebDriver driver) {
		this.driver = driver;
	}
	
	/**
	 * Generates an external voice call recording to the selected destination
	 * @param deviceId The destination device. It is possible to select multiple devices (separated by commas).
	 * @return
	 */
	public Boolean CallDevice(String deviceId)
	{
		Map<String, Object> params = new HashMap<>();
		params.put("to.device", deviceId);
		return Helper.ExecuteMethod(driver, COMMAND_GATEWAYCALL, params);
	}
	
	/**
	 * Generates an external voice call recording to the selected destination
	 * @param number The destination phone number. It is possible to select multiple phone numbers.
	 * Format -  +[country code][area code][phone number], example -  +17812054111
	 * @return
	 */
	public Boolean CallNumber(String number)
	{
		Map<String, Object> params = new HashMap<>();
		params.put("to.number", number);
		return Helper.ExecuteMethod(driver, COMMAND_GATEWAYCALL, params);
	}
	
	/**
	 * Generates an external voice call recording to the user currently running the script.
	 * @return
	 */
	public Boolean CallCurrentUser()
	{
		Map<String, Object> params = new HashMap<>();
		params.put("to.user", "user");
		return Helper.ExecuteMethod(driver, COMMAND_GATEWAYCALL, params);
	}
	
	/**
	 * Generates an external voice call recording to the selected destination
	 * @param user The destination user. It is possible to select multiple users. 
		The user identifier is the Perfecto Mobile username.
		The user must be registered with the correct mobile number.
		The business number field does not apply.
	 * @return
	 */
	public Boolean CallUser(String user)
	{
		Map<String, Object> params = new HashMap<>();
		params.put("to.user", user);
		return Helper.ExecuteMethod(driver, COMMAND_GATEWAYCALL, params);
	}
	
	/**
	 * Sends an SMS message to the selected destination.
	 * @param deviceId The destination device. It is possible to select multiple devices (separated by commas).
	 * @param content The message text for this command.
	 * @return
	 */
	public Boolean SendSMSToDeivce(String deviceId, String content)
	{
		Map<String, Object> params = new HashMap<>();
		params.put("to.handset", deviceId);
		params.put("body", content);
		return Helper.ExecuteMethod(driver, COMMAND_GATEWAYSMS, params);
	}
	
	/**
	 * Sends an SMS message to the selected destination.
	 * @param user The destination user. It is possible to select multiple users. 
	 * The user identifier is the Perfecto Mobile username. 
	 * The user must be registered with the correct mobile number.
	 * The business number field does not apply.
	 * @param content The message text for this command.
	 * @return
	 */
	public Boolean SendSMSToUser(String user, String content)
	{
		Map<String, Object> params = new HashMap<>();
		params.put("to.handset", user);
		params.put("body", content);
		return Helper.ExecuteMethod(driver, COMMAND_GATEWAYSMS, params);
	}
	
	/**
	 * Sends an SMS message to the selected destination.
	 * @param content The message text for this command.
	 * @return
	 */
	public Boolean SendSMSToCurrentUser(String content)
	{
		Map<String, Object> params = new HashMap<>();
		params.put("body", content);
		return Helper.ExecuteMethod(driver, COMMAND_GATEWAYSMS, params);
	}
	
	/**
	 * Sends an SMS message to the selected destination.
	 * @param number The destination phone number. It is possible to select multiple phone numbers. 
	 * Format -  +[country code][area code][phone number]
	 * @param content The message text for this command.
	 * @return
	 */
	public Boolean SendSMSToNumber(String number, String content)
	{
		Map<String, Object> params = new HashMap<>();
		params.put("to.handset", number);
		params.put("body", content);
		return Helper.ExecuteMethod(driver, COMMAND_GATEWAYSMS, params);
	}
	
	/**
	 * Sends an email message to the selected destination
	 * @param to The email address for this command.
	 * @param body The message text for this command. <default is "test email">
	 * @param subject The message subject for this command. <default is "none">
	 * @return
	 */
	public Boolean SendEmail(String to, String body, String subject)
	{
		Map<String, Object> params = new HashMap<>();
		params.put("to.address", to);
		if (body != null)
			params.put("body", body);
		if (subject != null)
			params.put("subject", subject);
		return Helper.ExecuteMethod(driver, COMMAND_GATEWAYEMAIL, params);
	}
	
	/**
	 * Sends an email message to the selected destination
	 * @param to The email address for this command.
	 * @param body The message text for this command. The subject is "none"
	 */
	public Boolean SendEmail(String to, String body)
	{
		return SendEmail(to, body, null);
	}
	
	/**
	 * Sends an email message to the selected destination
	 * @param to The email address for this command. 
	 * Body is "test email", subject is "none"
	 * @return
	 */
	public Boolean SendEmail(String to)
	{
		return SendEmail(to, null, null);
	}
	
	/**
	 * Sends an email message to the selected destination
	 * @param deviceId The destination device. It is possible to select multiple devices.
	 * @param body The message text for this command. <default is "test email">
	 * @param subject The message subject for this command. <default is "none">
	 * @return
	 */
	public Boolean SendEmailToDevice(String deviceId, String body, String subject)
	{
		Map<String, Object> params = new HashMap<>();
		params.put("to.handset", deviceId);
		if (body != null)
			params.put("body", body);
		if (subject != null)
			params.put("subject", subject);
		return Helper.ExecuteMethod(driver, COMMAND_GATEWAYEMAIL, params);
	}
	
	/**
	 * Sends an email message to the selected destination
	 * @param deviceId The destination device. It is possible to select multiple devices.
	 * @param body The message text for this command. Subject defaults to "none"
	 * @return
	 */
	public Boolean SendEmailToDevice(String deviceId, String body)
	{
		return SendEmailToDevice(deviceId, body, null);
	}
	
	/**
	 * Sends an email message to the selected destination
	 * @param deviceId The destination device. It is possible to select multiple devices.
	 * The body and the subject default to "none"
	 * @return
	 */
	public Boolean SendEmailToDevice(String deviceId)
	{
		return SendEmailToDevice(deviceId, null, null);
	}
	
	/**
	 * Sends an email message to the selected destination
	 * @param userEmail The user for this command. It is possible to select multiple users. The user identifier is the Perfecto Mobile username.
	 * @param body The message text for this command. <default is "test email">
	 * @param subject The message subject for this command. <default is "none">
	 * @return
	 */
	public Boolean SendEmailToUser(String userEmail, String body, String subject)
	{
		Map<String, Object> params = new HashMap<>();
		params.put("to.user", userEmail);
		if (body != null)
			params.put("body", body);
		if (subject != null)
			params.put("subject", subject);
		return Helper.ExecuteMethod(driver, COMMAND_GATEWAYEMAIL, params);
	}
	
	
	public Boolean SendEmailToUser(String userEmail, String body)
	{
		return SendEmailToUser(userEmail, body, null);
	}
	
	
	public Boolean SendEmailToUser(String userEmail)
	{
		return SendEmailToUser(userEmail, null, null);
	}
	
	
	/**
	 * Sends an email message to the user currently running the script.
	 * @param body The message text for this command. <default is "test email">
	 * @param subject The message subject for this command. <default is "none">
	 * @return
	 */
	public Boolean SendEmailToCurrentUser(String body, String subject)
	{
		Map<String, Object> params = new HashMap<>();
		params.put("to.logical", "user");
		if (body != null)
			params.put("body", body);
		if (subject != null)
			params.put("subject", subject);
		return Helper.ExecuteMethod(driver, COMMAND_GATEWAYEMAIL, params);
	}
	
	/**
	 * Sends an email message to the user currently running the script.
	 * @param body The message text for this command. Subject defaults to "none"
	 * @return
	 */
	public Boolean SendEmailToCurrentUser(String body)
	{
		return SendEmailToCurrentUser(body, null);
	}
	
	/**
	 * Sends an email message to the user currently running the script.
 	 * Body and subject defaults to "none"
	 * @return
	 */
	public Boolean SendEmailToCurrentUser()
	{
		return SendEmailToCurrentUser(null, null);
	}
}