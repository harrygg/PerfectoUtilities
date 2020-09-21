package g.perfecto.utilities;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Cloud
{
  private PerfectoDriver perfectoDriver;

  public final static String COMMAND_GATEWAY_CALL = "mobile:gateway:call";
  public final static String COMMAND_GATEWAY_EMAIL = "mobile:gateway:email";
  public final static String COMMAND_GATEWAY_SMS = "mobile:gateway:sms";

  private Log log;
  
  public Cloud(PerfectoDriver driver) 
  {
    log = LogFactory.getLog(this.getClass());
    log.debug("Creating " + this.getClass() + " object");
    perfectoDriver = driver;
  }

  /**
   * Generates an external voice call recording to the selected destination
   * @param deviceId The destination device. It is possible to select multiple devices (separated by commas).
   * @return
   */
  public Boolean callDevice(String deviceId)
  {
    Map<String, Object> params = new HashMap<>();
    params.put("to.device", deviceId);
    return perfectoDriver.executor.executeMethod(COMMAND_GATEWAY_CALL, params);
  }

  /**
   * Generates an external voice call recording to the selected destination
   * @param number The destination phone number. It is possible to select multiple phone numbers.
   * Format -  +[country code][area code][phone number], example -  +17812054111
   * @return
   */
  public Boolean callNumber(String number)
  {
    Map<String, Object> params = new HashMap<>();
    params.put("to.number", number);
    return perfectoDriver.executor.executeMethod(COMMAND_GATEWAY_CALL, params);
  }

  /**
   * Generates an external voice call recording to the user currently running the script.
   * @return
   */
  public Boolean callCurrentUser()
  {
    Map<String, Object> params = new HashMap<>();
    params.put("to.user", "user");
    return perfectoDriver.executor.executeMethod(COMMAND_GATEWAY_CALL, params);
  }

  /**
   * Generates an external voice call recording to the selected destination
   * @param user The destination user. It is possible to select multiple users. 
     The user identifier is the Perfecto Mobile username.
     The user must be registered with the correct mobile number.
     The business number field does not apply.
   * @return
   */
  public Boolean callUser(String user)
  {
    Map<String, Object> params = new HashMap<>();
    params.put("to.user", user);
    return perfectoDriver.executor.executeMethod(COMMAND_GATEWAY_CALL, params);
  }

  /**
   * Sends an SMS message to the selected destination.
   * @param deviceId The destination device. It is possible to select multiple devices (separated by commas).
   * @param content The message text for this command.
   * @return
   */
  public Boolean sendSMSToDeivce(String deviceId, String content)
  {
    Map<String, Object> params = new HashMap<>();
    params.put("to.handset", deviceId);
    params.put("body", content);
    return perfectoDriver.executor.executeMethod(COMMAND_GATEWAY_SMS, params);
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
  public Boolean sendSMSToUser(String user, String content)
  {
    Map<String, Object> params = new HashMap<>();
    params.put("to.handset", user);
    params.put("body", content);
    return perfectoDriver.executor.executeMethod(COMMAND_GATEWAY_SMS, params);
  }

  /**
   * Sends an SMS message to the selected destination.
   * @param content The message text for this command.
   * @return
   */
  public Boolean sendSMSToCurrentUser(String content)
  {
    Map<String, Object> params = new HashMap<>();
    params.put("body", content);
    return perfectoDriver.executor.executeMethod(COMMAND_GATEWAY_SMS, params);
  }

  /**
   * Sends an SMS message to the selected destination.
   * @param number The destination phone number. It is possible to select multiple phone numbers. 
   * Format -  +[country code][area code][phone number]
   * @param content The message text for this command.
   * @return
   */
  public Boolean sendSMSToNumber(String number, String content)
  {
    Map<String, Object> params = new HashMap<>();
    params.put("to.handset", number);
    params.put("body", content);
    return perfectoDriver.executor.executeMethod(COMMAND_GATEWAY_SMS, params);
  }

  /**
   * Sends an email message to the selected destination
   * @param to an array of email address for this command.
   * @param body The message text for this command. <default is "test email">
   * @param subject The message subject for this command. <default is "none">
   * @return
   */
  public Boolean sendEmails(String[] to, String body, String subject)
  {
    Map<String, Object> params = new HashMap<>();
    params.put("to.address", to);
    if (body != null)
      params.put("body", body);
    if (subject != null)
      params.put("subject", subject);
    return perfectoDriver.executor.executeMethod(COMMAND_GATEWAY_EMAIL, params);
  }
  /**
   * Sends an email message to the selected destination
   * @param to an array of email addresses for this command. 
   * Body is "test email", subject is "none"
   * @return
   */
  public Boolean sendEmails(String[] to, String subject)
  {
    return sendEmails(to, subject, null);
  }
  
  /**
   * Sends an email message to the selected destination
   * @param to an array of email addresses for this command. 
   * Body is "test email", subject is "none"
   * @return
   */
  public Boolean sendEmails(String[] to)
  {
    return sendEmails(to, null, null);
  }

  /**
   * Sends an email message to the selected destination
   * @param to email address for this command.
   * @param body The message text for this command. <default is "test email">
   * @param subject The message subject for this command. <default is "none">
   * @return
   */
  public Boolean sendEmail(String to, String body, String subject)
  {
    return sendEmails(new String[] {to}, body, subject);
  }
  
  public Boolean sendEmail(String to, String body)
  {
    return sendEmails(new String[] {to}, body, null);
  }
  
  public Boolean sendEmail(String to)
  {
    return sendEmails(new String[] {to}, null, null);
  } 
  /**
   * Sends an email message to the selected destination
   * @param deviceId The destination device. It is possible to select multiple devices.
   * @param body The message text for this command. <default is "test email">
   * @param subject The message subject for this command. <default is "none">
   * @return
   */
  public Boolean sendEmailToDevice(String deviceId, String body, String subject)
  {
    Map<String, Object> params = new HashMap<>();
    
    params.put("to.handset", deviceId);
    
    if (body != null)
      params.put("body", body);
    
    if (subject != null)
      params.put("subject", subject);
    
    return perfectoDriver.executor.executeMethod(COMMAND_GATEWAY_EMAIL, params);
  }

  /**
   * Sends an email message to the selected destination
   * @param deviceId The destination device. It is possible to select multiple devices.
   * @param body The message text for this command. Subject defaults to "none"
   * @return
   */
  public Boolean sendEmailToDevice(String deviceId, String body)
  {
    return sendEmailToDevice(deviceId, body, null);
  }

  /**
   * Sends an email message to the selected destination
   * @param deviceId The destination device. It is possible to select multiple devices.
   * The body and the subject default to "none"
   * @return
   */
  public Boolean sendEmailToDevice(String deviceId)
  {
    return sendEmailToDevice(deviceId, null, null);
  }

  /**
   * Sends an email message to the selected destination
   * @param userEmail The user for this command. It is possible to select multiple users. The user identifier is the Perfecto Mobile username.
   * @param body The message text for this command. <default is "test email">
   * @param subject The message subject for this command. <default is "none">
   * @return
   */
  public Boolean sendEmailToUser(String userEmail, String body, String subject)
  {
    Map<String, Object> params = new HashMap<>();
    
    params.put("to.user", userEmail);
    
    if (body != null)
      params.put("body", body);
    
    if (subject != null)
      params.put("subject", subject);
    
    return perfectoDriver.executor.executeMethod(COMMAND_GATEWAY_EMAIL, params);
  }


  public Boolean sendEmailToUser(String userEmail, String body)
  {
    return sendEmailToUser(userEmail, body, null);
  }


  public Boolean sendEmailToUser(String userEmail)
  {
    return sendEmailToUser(userEmail, null, null);
  }


  /**
   * Sends an email message to the user currently running the script.
   * @param body The message text for this command. <default is "test email">
   * @param subject The message subject for this command. <default is "none">
   * @return
   */
  public Boolean sendEmailToCurrentUser(String body, String subject)
  {
    Map<String, Object> params = new HashMap<>();
    
    params.put("to.logical", "user");
    
    if (body != null)
      params.put("body", body);
    
    if (subject != null)
      params.put("subject", subject);
    
    return perfectoDriver.executor.executeMethod(COMMAND_GATEWAY_EMAIL, params);
  }

  /**
   * Sends an email message to the user currently running the script.
   * @param body The message text for this command. Subject defaults to "none"
   * @return
   */
  public Boolean sendEmailToCurrentUser(String body)
  {
    return sendEmailToCurrentUser(body, null);
  }

  /**
   * Sends an email message to the user currently running the script.
   * Body and subject defaults to "none"
   * @return
   */
  public Boolean sendEmailToCurrentUser()
  {
    return sendEmailToCurrentUser(null, null);
  }
}