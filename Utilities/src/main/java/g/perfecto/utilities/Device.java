package g.perfecto.utilities;
import java.io.File;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Device {

  private RemoteWebDriver driver = null;
  
  public String target;

  public Integer tail;
  public String operation;
  public static final String ROTATE_OPERATION_RESET = "reset";
  public static final String ROTATE_OPERATION_NEXT = "next";
  public String state;
  public static final String ROTATE_STATE_PORTRAIT = "portrait";
  public static final String ROTATE_STATE_LANDSCAPE = "landscape";
  public String method;
  public static final String ROTATE_METHOD_DEVICE = "device";
  public static final String ROTATE_METHOD_VIEW = "view";
  public Integer timeout;

  private final static String COMMAND_MOBILE_READY = "mobile:handset:ready";
  private final static String COMMAND_REBOOT = "mobile:handset:reboot";
  private final static String COMMAND_RECOVER = "mobile:handset:recover";
  private final static String COMMAND_GETLOG = "mobile:device:log";
  private final static String COMMAND_ROTATE = "mobile:device:rotate";
  private final static String COMMAND_LOCK = "mobile:screen:lock";
  private final static String COMMAND_DEVICE_INFO = "mobile:device:info";
  private final static String COMMAND_SET_LOCATION = "mobile:location:set";
  private final static String COMMAND_GET_LOCATION = "mobile:location:get";
  private final static String COMMAND_RESET_LOCATION = "mobile:location:reset";

  public static final String AUDIO_LANGUAGE_ENGLISH_US = "us-english";
  public static final String AUDIO_LANGUAGE_ENGLISH_UK = "uk-english";
  public static final String AUDIO_LANGUAGE_SPANISH = "es-spanish";
  public static final String AUDIO_LANGUAGE_JAPANESE = "japanese";
  public static final String AUDIO_LANGUAGE_FRENCH = "french";
  public static final String AUDIO_LANGUAGE_CHINESE = "chinese";
  public static final String AUDIO_LANGUAGE_PORTUGESE = "portugese";
  public static final String AUDIO_LANGUAGE_ARABIC = "arabic";

  public static final String AUDIO_RATE_BROAD = "broad";
  public static final String AUDIO_RATE_NARROW = "narrow";
  public static final String AUDIO_PROFILE_PERFORMANCE = "performance";
  public static final String AUDIO_PROFILE_ACCURACY = "accuracy";
  public static final String AUDIO_SILENCE_TRIMMING_TYPE_ABSOLUTE = "absolute";
  public static final String AUDIO_SILENCE_TRIMMING_TYPE_RELATIVE = "relative";
  public static final String AUDIO_VALIDATION_PROFILE_VOIP = "voip";
  public static final String AUDIO_VALIDATION_PROFILE_BASIC = "basic";
  public static final String AUDIO_VALIDATION_PROFILE_VOLTE = "volte";
  public static final String AUDIO_VALIDATION_PROFILE_VOIPE_RMS = "voip_rms";

  public static final String AUDIO_TARGET_ASIS = "as-is";
  public static final String AUDIO_TARGET_ALL = "all";
  public static final String AUDIO_TARGET_ANY = "any";

  public static final String AUDIO_MATCH_MODE_CONTAIN = "contain";
  public static final String AUDIO_MATCH_MODE_EQUAL = "equal";
  public static final String AUDIO_MATCH_MODE_STARTWITH = "startwith";
  public static final String AUDIO_MATCH_MODE_ENDWIDTH = "endwith";
  public static final String AUDIO_MATCH_MODE_FIRST = "first";
  public static final String AUDIO_MATCH_MODE_LAST = "last";
  public static final String AUDIO_MATCH_MODE_INDEX = "index";
  
  public Device(RemoteWebDriver driver)
  {
    Logger.LogDebug("Creating Device object");
    this.driver = driver;
  }

  /**
   * For iOS and Android devices, the device is unlocked and returned to its default rotate orientation. 
   * For example, iPhone devices are returned to portrait mode and iPad devices to landscape mode.
   * @return
   */
  public Boolean ready()
  {
    Map<String, Object> params = new HashMap<>();

    if (target != null)
      params.put("target", this.target);

    return Helper.executeMethod(driver, COMMAND_MOBILE_READY, params);
  }

  /**
   * For iOS and Android devices, the device is unlocked and returned to its default rotate orientation. 
   * For example, iPhone devices are returned to portrait mode and iPad devices to landscape mode.
   * @param target Specify what should be reset - menu navigation state, device orientation, or both.
   * @return
   */
  public Boolean ready(String target)
  {
    this.target = target;
    return ready();
  }

  /**
   * Reboots the device and returns it to the unlocked state. Performed by software reset for Android and iOS devices.
   * @return
   */
  public Boolean reboot()
  {
    return Helper.executeMethod(driver, COMMAND_REBOOT, new HashMap<>());
  }


  /**
   * Recovers a connected device that is unresponsive. For example, a device with an interrupted video, black screen or touch failure.
   * @return
   */
  public Boolean recover()
  {
    return Helper.executeMethod(driver, COMMAND_RECOVER, new HashMap<>());
  }

  /**
   * Retrieves the Perfecto Lab system log relevant to the device.
   * @return Device log returned as String
   */
  public String getLog()
  {
    Map<String, Object> params = new HashMap<>();
    if (tail != null)
      params.put("tail", tail);
    return Helper.executeMethodString(driver, COMMAND_GETLOG, params);
  }

  /**
   * Retrieves the Perfecto Lab system log relevant to the device.
   * @param tail The number of lines to retrieve from the log. This number of lines will be retrieved from the end of the log
   * Effective values are in the range of 0-1000
   * @return Device log returned as String
   */
  public String getLog(int tail)
  {
    this.tail = tail;
    return getLog();
  }

  /**
   * Rotates the device to either landscape or portrait.
   * @return
   */
  public Boolean rotate()
  {
    Map<String, Object> params = new HashMap<>();

    if (operation != null && (operation == ROTATE_OPERATION_RESET || operation == ROTATE_OPERATION_NEXT))
      params.put("operation", operation);
    else
      System.out.println("WARNING! Unsupported operation value '" + operation + "'. Possible values are: " + ROTATE_OPERATION_RESET + " or " + ROTATE_OPERATION_NEXT);

    if (state != null && (state == ROTATE_STATE_PORTRAIT || state == ROTATE_STATE_LANDSCAPE))
      params.put("state", state);
    else
      System.out.println("WARNING! Unsupported state value '" + state + "'. Possible values are: " + ROTATE_STATE_PORTRAIT + " or " + ROTATE_STATE_LANDSCAPE);

    if (method != null && (state == ROTATE_METHOD_DEVICE || state == ROTATE_METHOD_VIEW))
      params.put("method", method);
    else
      System.out.println("WARNING! Unsupported method value '" + method + "'. Possible values are: " + ROTATE_METHOD_DEVICE + " or " + ROTATE_METHOD_VIEW);

    return Helper.executeMethod(driver, COMMAND_ROTATE, params);
  }

  /**
   * Resets the device orientation.
   * @return
   */
  public Boolean resetOrientation()
  {
    Map<String, Object> params = new HashMap<>();
    params.put("orientation", ROTATE_OPERATION_RESET);
    return Helper.executeMethod(driver, COMMAND_ROTATE, params);
  }

  /**
   * rotate the device to its next state
   * @return
   */
  public Boolean rotateNext()
  {
    Map<String, Object> params = new HashMap<>();
    params.put("orientation", ROTATE_OPERATION_NEXT);
    return Helper.executeMethod(driver, COMMAND_ROTATE, params);
  }

  /**
   * rotate the device to its next state
   * @param method The rotation method. device | view
   * @return
   */
  public Boolean rotateNext(String method)
  {
    if (method == ROTATE_METHOD_DEVICE || method == ROTATE_METHOD_VIEW)
      this.method = method;
    return rotateNext();
  }

  /**
   * rotates the device to portrait mode.
   * @return
   */
  public Boolean rotateToPortrait()
  {
    Map<String, Object> params = new HashMap<>();
    params.put("state", ROTATE_STATE_PORTRAIT);
    return Helper.executeMethod(driver, COMMAND_ROTATE, params);
  }

  /**
   * rotates the device to portrait mode using method device | view
   * @param method
   * @return
   */
  public Boolean rotateToPortait(String method) {
    if (method == ROTATE_METHOD_DEVICE || method == ROTATE_METHOD_VIEW)
      this.method = method;
    return rotateToPortrait();
  }

  /**
   * Rotates device to landscape mode
   * @return
   */
  public Boolean rotateToLandscape()
  {
    Map<String, Object> params = new HashMap<>();
    params.put("state", ROTATE_STATE_LANDSCAPE);
    return Helper.executeMethod(driver, COMMAND_ROTATE, params);
  }
  
  /**
   * Rotates device to landscape mode
   * @return
   */
  public Boolean rotateToLandscape(String method) {
    if (method == ROTATE_METHOD_DEVICE || method == ROTATE_METHOD_VIEW)
      this.method = method;
    return rotateToLandscape();
  }

  /**
   * Locks the device screen for a set number of seconds, check instantly how your app behaves when the screen is locked.
   * @return
   */
  public Boolean lock()
  {
    Map<String, Object> params = new HashMap<>();

    if (timeout != null)
      params.put("timeout", timeout);

    return Helper.executeMethod(driver, COMMAND_LOCK, params);
  }

  /**
   * Locks the device screen for a set number of seconds, check instantly how your app behaves when the screen is locked.
   * @param timeout Time, in seconds, to lock device (default: 10)
   * @return
   */
  public Boolean lock(int timeout)
  {
    this.timeout = timeout;
    return lock();
  }

  public String getManifacturer() {
    return getInfo("manifacturer");
  }

  public String getModel() {
    return getInfo("model");
  }

  public String getPhoneNumber() {
    return getInfo("phoneNumber");
  }

  public String getDeviceId() {
    return getInfo("deviceId");
  }

  public String getResolution() {
    return getInfo("resolution");
  }

  public String getResolutionWidth()
  {
    return getInfo("resolutionWidth");
  }

  public String getResolutionHeight()
  {
    return getInfo("resolutionHeight");
  }

  public String getOs()
  {
    return getInfo("os");
  }

  public String getOsVersion()
  {
    return getInfo("osVersion");
  }

  public String getFirmware()
  {
    return getInfo("firmware");
  }

  public String getNetwork()
  {
    return getInfo("network ");
  }

  public String getDistributer()
  {
    return getInfo("distributer");
  }

  public String getImsi()
  {
    return getInfo("imsi");
  }

  public String getNativeImei()
  {
    return getInfo("nativeImei");
  }

  public String getMac()
  {
    return getInfo("wifiMacAddress");
  }

  public String getCradleId()
  {
    return getInfo("cradleId");
  }

  public Boolean IsInUse()
  {
    return getInfo("inUse").equalsIgnoreCase("true");
  }

  public String getPosition()
  {
    return getInfo("position");
  }

  public String getMethod()
  {
    return getInfo("method");
  }

  public String getRotation()
  {
    return getInfo("rotation");
  }

  public String getLocked()
  {
    return getInfo("locked");
  }

  public String getRoles()
  {
    return getInfo("roles");
  }

  public String getCurrentActivity()
  {
    return getInfo("currentActivity");
  }

  public String getCurrentPackage()
  {
    return getInfo("currentPackage");
  }

  public String getAll()
  {
    return getInfo("all");
  }

  public Boolean HasAudio() 
  {
    return getInfo("hasAudio").equalsIgnoreCase("true");
  }

  public String getAutomationInfrastructure()
  {
    return getInfo("automationInfrastructure");
  }

  /**
   * Retrieves the current location of the device, The location is returned as a Latitude, Longitude pair.
   * @return
   */
  public String getLocation()
  {
    return Helper.executeMethodString(driver, COMMAND_GET_LOCATION, new HashMap<String, Object>());
  }

  /**
   * Sets the device location. 
   * @param coordinates The latitude and longitude coordinate of the device location to set. 
   * Example: 43.642659,-79.387050
   * @return
   */
  public Boolean setLocationByCoordinates(String coordinates)
  {
    Map<String, Object> params = new HashMap<>();
    params.put("coordinates", coordinates);
    return Helper.executeMethod(driver, COMMAND_SET_LOCATION, params);
  }

  /**
   * Sets the device location.
   * @param address The address location to set. Format: Google Geocoding. 
   * Example: 1600 Amphitheatre Parkway, Mountain View, CA
   * @return
   */
  public Boolean setLocationByAddress(String address)
  {
    Map<String, Object> params = new HashMap<>();
    params.put("address", address);
    return Helper.executeMethod(driver, COMMAND_SET_LOCATION, params);
  }

  /**
   * Resets the device location to its actual location. Used alongside the Set device location function. Supported only on Android!
   * @return
   */
  public Boolean resetLocation()
  {
    return Helper.executeMethod(driver, COMMAND_RESET_LOCATION, new HashMap<String, Object>());
  }

  /**
   * Retrieves the specified device property and inserts its value into a defined variable. Use the Property parameter to specify the device property to retrieve.
   * @param param
   * @return
   */
  public String getInfo(String param)
  {
    Map<String, Object> params = new HashMap<>();
    params.put("property", param);
    return Helper.executeMethodString(driver, COMMAND_DEVICE_INFO, params);
  }

  /**
   * The function convert audio to text by applying powerful neural network models. 
   * It accepts an audio file that may be recorded from a device and creates a text file that contains the textual translation of the audio file.
   * @param pathToAudio - path to audio file on the device or in Perfecto repository
   * @param lanuage The supported Audio file languages.
   * @phrase Provides a list of phrases for speech-to-text library to use to avoid confusion. For example, provide the words:�two� and �four� to avoid confusion with �to� and �for�. 
   * @param rate Indicates the sampling rate of the audio recording
   * @param profile Profile of the NLP network
   * @return
   */
  public String audioToText(String pathToAudio, String language, String phrase, String rate, String profile)
  {
    Audio au = new Audio(driver);
    return au.toText(pathToAudio, language, phrase, rate, profile);		
  }

  public String audioToText(String pathToAudio, String lanuage, String phrase, String rate)
  {
    return audioToText(pathToAudio, lanuage, phrase, rate, null);
  }

  public String audioToText(String pathToAudio, String lanuage, String phrase)
  {
    return audioToText(pathToAudio, lanuage, phrase, null, null);
  }

  public String audioToText(String pathToAudio, String language)
  {
    return audioToText(pathToAudio, language, null, null, null);
  }

  public String audioToText(String pathToAudio)
  {
    return audioToText(pathToAudio, null, null, null, null);
  }

  /**
   * The command accepts either a text file or text string and returns an audio file. 
   * The function can be configured to produce the audio with a male or female voice, and supports several languages.
   * @param text - The text string to convert to audio or a repository key of a text file. Either Text or Text file key must be specified. Max text length is ~5000 characters.
   * @param repositoryFile Repository key for storing the audio file.
   * @param language Text and audio language. 
   * @param isFemale Indicates the gender voice to use in the audio result file.
   * @return
   */
  public String textToAudio(String text, String repositoryFile, String language, Boolean isFemale)
  {
    Audio au = new Audio(driver);
    return au.fromText(text, repositoryFile, language, isFemale);
  }

  public String textToAudio(String text, String repositoryFile, String language)
  {
    return textToAudio(text, repositoryFile, language, null);
  }

  public String textToAudio(String text, String repositoryFile)
  {
    return textToAudio(text, repositoryFile, null, null);
  }

  /**
   * The command receives an audio file, may have been recorded from a device, uses the parameters to trim off any silence from the beginning or end of the audio. Using this trimmed file, executes an analysis service
   * Note: Only WAV audio files are supported by this function. 
   * @param deviceAudio Identifies the recorded audio file.
   * @param repositoryKey Repository key for the audio file to validate.
   * @param treshold The minimal required MOS threshold. Default value is 3.0.
   * @param profile Indicates the type of analyzed voice. The default profile is VOIP. The VOLTE profile can be used when the audio was recorded from an LTE connection.
   * @param silenceTrimmingType parameter to control trimming silence from the device audio file. Possible values are absolute and relative.
   * @param silenceTrimmingLevel parameter to control trimming silence from the device audio file. The level parameter is set in dB and varies from 0.0 up to 120.0.
   * @param calibration URL to an audio file recorded while the device is silent, i.e. without any audio. This file is used to calibrate the system by eliminating background noise.
   * @param generic A generic audio parameter.
   * @return
   */
  public String validateAudio(String deviceAudio, String repositoryKey, Double treshold, String profile, String silenceTrimmingType, Double silenceTrimmingLevel, String calibration, String generic)
  {
    Audio au = new Audio(driver);
    return au.validateAudio(deviceAudio, repositoryKey, treshold, profile, silenceTrimmingType, silenceTrimmingLevel, calibration, generic);
  }

  public String validateAudio(String deviceAudio, String repositoryKey, Double treshold, String profile, String silenceTrimmingType, Double silenceTrimmingLevel, String calibration)
  {
    return validateAudio(deviceAudio, repositoryKey, treshold, profile, silenceTrimmingType, silenceTrimmingLevel, calibration, null);
  }

  public String validateAudio(String deviceAudio, String repositoryKey, Double treshold, String profile, String silenceTrimmingType, Double silenceTrimmingLevel)
  {
    return validateAudio(deviceAudio, repositoryKey, treshold, profile, silenceTrimmingType, silenceTrimmingLevel, null, null);
  }

  public String validateAudio(String deviceAudio, String repositoryKey, Double treshold, String profile, String silenceTrimmingType)
  {
    return validateAudio(deviceAudio, repositoryKey, treshold, profile, silenceTrimmingType, null, null, null);
  }

  public String ValidateAudio(String deviceAudio, String repositoryKey, Double treshold, String profile)
  {
    return validateAudio(deviceAudio, repositoryKey, treshold, profile, null, null, null, null);
  }

  public String ValidateAudio(String deviceAudio, String repositoryKey, Double treshold)
  {
    return validateAudio(deviceAudio, repositoryKey, treshold, null, null, null, null, null);
  }

  public String validateAudio(String deviceAudio, String repositoryKey)
  {
    return validateAudio(deviceAudio, repositoryKey, null, null, null, null, null, null);
  }

  /**
   * Plays an audio file into the device audio-in. Supported file types: MP3 and WAV.
   * @param key The full repository path, including directory and file name, where to locate the audio file. Example - PRIVATE:dir1/dir2/name.mp3
   * @param wait The execution mode. No wait (default) - continue to the next line in the script immediately
   * @return
   */
  public Boolean injectAudio(String key, Boolean wait)
  {
    Audio au = new Audio(driver);
    return au.inject(key, wait);
  }

  /**
   * Plays an audio file into the device audio-in. Supported file types: MP3 and WAV.
   * @param key The full repository path, including directory and file name, where to locate the audio file. Example - PRIVATE:dir1/dir2/name.mp3
   * @return
   */
  public Boolean InjectAudio(String key)
  {
    return injectAudio(key, null);
  }


  /**
   * Command starts recording the audio output from the device and creates a WAV file. The file is saved in the media storage server. 
   * @return A URL to the file location is returned in the response to the command
   */
  public String startAudioRecording()
  {
    return new Audio(driver).startRecording();
  }

  /**
   * Command stops recording the audio output from the device, closes the file, and stores in the media storage server at the URL declared at the start of audio recording.
   * @return
   */
  public String stopAudioRecording()
  {
    return new Audio(driver).stopRecording();
  }

  /**
   * The command accepts an audio file that may be recorded from a device and creates a text file that contains the textual translation of the audio file. 
   * In addition, performs a text checkpoint function, checking for the existence of a text string in the translated text file. 
   * This function utilizes matching parameters similar to the text checkpoint function.
   * @param content Text that should be validated against the generated text file.
   * @param audioInput Identifies the recorded audio file or the repository key of an audio file.
   * @param target The target search in case the needle includes more than one word.
   * @param match The needle comparison method.
   * @param index In case the needle has multiple occurrences on the screen, enter the index of the required occurrence.
   * @param words The search option to match only whole words in the haystack, or also part of other words
   * @param exact The option to find the exact needle phrase with no errors.
   * @param threshold The acceptable match level percentage, between 20 and 100.
   * @param confidence Indicates the minimal confidence level that the audio to text tool measures for the conversion. If the conversion does not reach this confidence level the function will return a failed status.
   * @param language The supported Audio file languages.
   * @param rate 	Indicates the sampling rate of the audio recording
   * @param profile Profile of the NLP network
   * @param phrase Provides a list of phrases for speech-to-text library to use to avoid confusion. For example, provide the words:�two� and �four� to avoid confusion with �to� and �for�.
   * @return
   */
  public Boolean validateAudioToText(String content, String audioInput, String target, String match, Integer index, Boolean words, String exact, Double threshold, Integer confidence, String language, String rate, String profile, List<String> phrases)
  {
    return new Audio(driver).validateAudioToText(content, audioInput, target, match, index, words, exact, threshold, confidence, language);
  }

  public Boolean validateAudioToText(String content, String audioInput, String target, String match, Integer index, Boolean words, String exact, Double threshold, Integer confidence, String language, String rate, String profile)
  {
    return validateAudioToText(content, audioInput, target, match, index, words, exact, threshold, confidence, language, rate, profile, null);
  }

  public Boolean validateAudioToText(String content, String audioInput, String target, String match, Integer index, Boolean words, String exact, Double threshold, Integer confidence, String language, String rate)
  {
    return validateAudioToText(content, audioInput, target, match, index, words, exact, threshold, confidence, language, rate, null, null);
  }

  public Boolean validateAudioToText(String content, String audioInput, String target, String match, Integer index, Boolean words, String exact, Double threshold, Integer confidence, String language)
  {
    return validateAudioToText(content, audioInput, target, match, index, words, exact, threshold, confidence, language, null, null, null);
  }

  public Boolean validateAudioToText(String content, String audioInput, String target, String match, Integer index, Boolean words, String exact, Double threshold, Integer confidence)
  {
    return validateAudioToText(content, audioInput, target, match, index, words, exact, threshold, confidence, null, null, null, null);
  }

  public Boolean validateAudioToText(String content, String audioInput, String target, String match, Integer index, Boolean words, String exact, Double threshold)
  {
    return validateAudioToText(content, audioInput, target, match, index, words, exact, threshold, null, null, null, null, null);
  }

  public Boolean validateAudioToText(String content, String audioInput, String target, String match, Integer index, Boolean words, String exact)
  {
    return validateAudioToText(content, audioInput, target, match, index, words, exact, null, null, null, null, null, null);
  }

  public Boolean validateAudioToText(String content, String audioInput, String target, String match, Integer index, Boolean words)
  {
    return validateAudioToText(content, audioInput, target, match, index, words, null, null, null, null, null, null, null);
  }

  public Boolean validateAudioToText(String content, String audioInput, String target, String match, Integer index)
  {
    return validateAudioToText(content, audioInput, target, match, index, null, null, null, null, null, null, null, null);
  }

  public Boolean validateAudioToText(String content, String audioInput, String target, String match)
  {
    return validateAudioToText(content, audioInput, target, match, null, null, null, null, null, null, null, null, null);
  }

  public Boolean validateAudioToText(String content, String audioInput, String target)
  {
    return validateAudioToText(content, audioInput, target, null, null, null, null, null, null, null, null, null, null);
  }

  public Boolean validateAudioToText(String content, String audioInput)
  {
    return validateAudioToText(content, audioInput, null, null, null, null, null, null, null, null, null, null, null);
  }

  public Boolean voiceAssistantInject(String text)
  {
    return new Audio(driver).voiceAssistantInject(text);
  }

  /**
   * Gets a screenshot from the device as BASE64
   * @return
   */
  public String getScreenshot()
  {
    return new UserActions(driver).takeScreenshot();
  }
    
  /**
   * Gets a screenshot from the device as base64
   * @return
   */
  public String getScreenshotAsBase64()
  {
    return new UserActions(driver).takeScreenshot();
  }
  
 
  /**
   * Gets a screenshot from the device as File
   * @return
   */
  public File getScreenshotAsFile()
  {
    return new UserActions(driver).takeScreenshotAsFile();
  }
  
  /**
   * Gets a screenshot from the device as Bytes
   * @return
   */
  public byte[] getScreenshotAsBytes()
  {
    return new UserActions(driver).takeScreenshotAsBytes();
  }
  
}