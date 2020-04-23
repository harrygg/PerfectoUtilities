package g.perfecto.utilities;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Device {
	
	private RemoteWebDriver driver = null;
	private Map<String, Object> params = new HashMap<>();
	
	public String target = null;
	private final String[] targets = new String[] {"all", "menu", "position"};
	
	public int tail = 1000;
	public String operation = null;
	public final String ROTATE_OPERATION_RESET = "reset";
	public final String ROTATE_OPERATION_NEXT = "next";
	public String state = null;
	public final String ROTATE_STATE_PORTRAIT = "portrait";
	public final String ROTATE_STATE_LANDSCAPE = "landscape";
	public String method = null;
	public final String ROTATE_METHOD_DEVICE = "device";
	public final String ROTATE_METHOD_VIEW = "view";
	public int timeout = 10;
	
	private final String COMMAND_MOBILEREADY = "mobile:handset:ready";
	private final String COMMAND_REBOOT = "mobile:handset:reboot";
	private final String COMMAND_RECOVER = "mobile:handset:recover";
	private final String COMMAND_GETLOG = "mobile:device:log";
	private final String COMMAND_ROTATE = "mobile:device:rotate";
	private final String COMMAND_LOCK = "mobile:screen:lock";
	private final String COMMAND_DEVICEINFO = "mobile:device:info";
	private final String COMMAND_SETLOCATION = "mobile:location:set";
	private final String COMMAND_GETLOCATION = "mobile:location:get";
	private final String COMMAND_RESETLOCATION = "mobile:location:reset";
	private final String COMMAND_GATEWAYCALL = "mobile:gateway:call";
	private final String COMMAND_GATEWAYEMAIL = "mobile:gateway:email";
	private final String COMMAND_GATEWAYSMS = "mobile:gateway:sms";
	private final String COMMAND_INJECTAUDIO = "mobile:audio:inject";
	private final String COMMAND_AUDIORECORDINGSTART = "mobile:audio.recording:start";
	private final String COMMAND_AUDIORECORDINGSTOP = "mobile:audio.recording:stop";
	private final String COMMAND_TEXTTOAUDIO = "mobile:text:audio";
	private final String COMMAND_VOICEASSIST = "mobile:voice:assist";
	private final String COMMAND_AUDIOVALIDATION = "mobile:audio:validation";
	private final String COMMAND_AUDIOTEXTVALIDATION = "mobile:audio-text:validation";
	private final String COMMAND_AUDIOTOTEXT = "mobile:audio:text";
	
	
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
	private static final String[] AUDIO_SILENCE_TRIMMING_TYPES = new String[] {AUDIO_SILENCE_TRIMMING_TYPE_ABSOLUTE, AUDIO_SILENCE_TRIMMING_TYPE_RELATIVE}; 
	public static final String AUDIO_VALIDATION_PROFILE_VOIP = "voip";
	public static final String AUDIO_VALIDATION_PROFILE_BASIC = "basic";
	public static final String AUDIO_VALIDATION_PROFILE_VOLTE = "volte";
	public static final String AUDIO_VALIDATION_PROFILE_VOIPE_RMS = "voip_rms";
	private static final String[] AUDIO_VALIDATION_PROFILES = new String[] {AUDIO_VALIDATION_PROFILE_VOIP, AUDIO_VALIDATION_PROFILE_BASIC, AUDIO_VALIDATION_PROFILE_VOLTE, AUDIO_VALIDATION_PROFILE_VOIPE_RMS};
	
	public static final String AUDIO_TARGET_ASIS = "as-is";
	public static final String AUDIO_TARGET_ALL = "all";
	public static final String AUDIO_TARGET_ANY = "any";
	private static final String[] AUDIO_TARGETS = new String[] { AUDIO_TARGET_ALL, AUDIO_TARGET_ANY, AUDIO_TARGET_ASIS };
	
	public static final String AUDIO_MATCH_MODE_CONTAIN = "contain";
	public static final String AUDIO_MATCH_MODE_EQUAL = "equal";
	public static final String AUDIO_MATCH_MODE_STARTWITH = "startwith";
	public static final String AUDIO_MATCH_MODE_ENDWIDTH = "endwith";
	public static final String AUDIO_MATCH_MODE_FIRST = "first";
	public static final String AUDIO_MATCH_MODE_LAST = "last";
	public static final String AUDIO_MATCH_MODE_INDEX = "index";
	public static final String[] AUDIO_MATCH_MODES = new String[]{ AUDIO_MATCH_MODE_CONTAIN, AUDIO_MATCH_MODE_EQUAL, AUDIO_MATCH_MODE_STARTWITH, AUDIO_MATCH_MODE_ENDWIDTH, AUDIO_MATCH_MODE_FIRST, AUDIO_MATCH_MODE_LAST, AUDIO_MATCH_MODE_INDEX};
	
	
	public Device(RemoteWebDriver driver)
	{
		this.driver = driver;
	}
	
	public Boolean Ready()
	{
		Map<String, Object> params = new HashMap<>();
		
		if (target != null && Arrays.asList(targets).contains(target))
			params.put("target", this.target);
		else
			System.out.println("WARNING! Unsupported analysis type '" + target + "'. Possible values are: " + String.join(", ", targets));
		
		try {
			driver.executeScript(COMMAND_MOBILEREADY, params);
		} catch (Exception ex)
		{
			System.out.println("Failed to execute " + COMMAND_MOBILEREADY);
			ex.printStackTrace(); 
			return false;
		}
		return true;
	}
	
	public Boolean Ready(String target)
	{
		this.target = target;
		return Ready();
	}
	
	
	public Boolean Reboot()
	{
		try {
			driver.executeScript(COMMAND_REBOOT, new HashMap<>());
		} catch (Exception ex)
		{
			System.out.println("Failed to execute " + COMMAND_REBOOT);
			ex.printStackTrace(); 
			return false;
		}
		return true;
	}
	

	
	public Boolean Recover()
	{
		try {
			driver.executeScript(COMMAND_RECOVER, new HashMap<>());
		} catch (Exception ex)
		{
			System.out.println("Failed to execute " + COMMAND_RECOVER);
			ex.printStackTrace(); 
			return false;
		}
		return true;
	}
	
	public Boolean GetLog()
	{
		Map<String, Object> params = new HashMap<>();
		params.put("tail", tail);
		
		try {
			driver.executeScript(COMMAND_GETLOG, params);
		} catch (Exception ex)
		{
			System.out.println("Failed to execute " + COMMAND_GETLOG);
			ex.printStackTrace(); 
			return false;
		}
		return true;
	}
	
	public Boolean GetLog(int tail)
	{
		this.tail = tail;
		return GetLog();
	}
	
	public Boolean Rotate()
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
		
		try {
			driver.executeScript(COMMAND_ROTATE, params);
		} catch (Exception ex)
		{
			System.out.println("Failed to execute " + COMMAND_ROTATE);
			ex.printStackTrace(); 
			return false;
		}
		return true;
	}
	
	public Boolean ResetOrientation()
	{
		Map<String, Object> params = new HashMap<>();
		params.put("orientation", ROTATE_OPERATION_RESET);
		
		try {
			driver.executeScript(COMMAND_ROTATE, params);
		} catch (Exception ex)
		{
			System.out.println("Failed to execute " + COMMAND_ROTATE);
			ex.printStackTrace(); 
			return false;
		}
		return true;
	}
	
	public Boolean RotateNext()
	{
		Map<String, Object> params = new HashMap<>();
		params.put("orientation", ROTATE_OPERATION_NEXT);
		
		try {
			driver.executeScript(COMMAND_ROTATE, params);
		} catch (Exception ex)
		{
			System.out.println("Failed to execute " + COMMAND_ROTATE);
			ex.printStackTrace(); 
			return false;
		}
		return true;
	}
	
	public Boolean RotateNext(String method)
	{
		if (method == ROTATE_METHOD_DEVICE || method == ROTATE_METHOD_VIEW)
			this.method = method;
		return RotateNext();
	}
	
	public Boolean RotateToPortrait()
	{
		Map<String, Object> params = new HashMap<>();
		params.put("state", ROTATE_STATE_PORTRAIT);
		
		try {
			driver.executeScript(COMMAND_ROTATE, params);
		} catch (Exception ex)
		{
			System.out.println("Failed to execute " + COMMAND_ROTATE);
			ex.printStackTrace(); 
			return false;
		}
		return true;
	}
	
	public Boolean RotateToPortait(String method) {
		if (method == ROTATE_METHOD_DEVICE || method == ROTATE_METHOD_VIEW)
			this.method = method;
		return RotateToPortrait();
	}
	
	public Boolean RotateToLandscape()
	{
		Map<String, Object> params = new HashMap<>();
		params.put("state", ROTATE_STATE_LANDSCAPE);
		
		try {
			driver.executeScript(COMMAND_ROTATE, params);
		} catch (Exception ex)
		{
			System.out.println("Failed to execute " + COMMAND_ROTATE);
			ex.printStackTrace(); 
			return false;
		}
		return true;
	}
	
	public Boolean RotateToLandscape(String method) {
		if (method == ROTATE_METHOD_DEVICE || method == ROTATE_METHOD_VIEW)
			this.method = method;
		return RotateToLandscape();
	}
	
	public Boolean Lock()
	{
		Map<String, Object> params = new HashMap<>();
		
		if (timeout != 10)
			params.put("timeout", timeout);
		
		try {
			driver.executeScript(COMMAND_LOCK, params);
		} catch (Exception ex)
		{
			System.out.println("Failed to execute " + COMMAND_LOCK);
			ex.printStackTrace(); 
			return false;
		}
		return true;
	}
	
	public Boolean Lock(int timeout)
	{
		this.timeout = timeout;
		return Lock();
	}

	public String GetManifacturer() {
		return GetInfo("manifacturer");
	}

	public String GetModel() {
		return GetInfo("model");
	}
	

	public String GetPhoneNumber() {
		return GetInfo("phoneNumber");
	}

	public String GetDeviceId() {
		return GetInfo("deviceId");
	}

	public String GetResolution() {
		return GetInfo("resolution");
	}

	public String GetResolutionWidth()
	{
		return GetInfo("resolutionWidth");
	}
	
	public String GetResolutionHeight()
	{
		return GetInfo("resolutionHeight");
	}
	
	public String GetOs()
	{
		return GetInfo("os");
	}
	
	public String GetOsVersion()
	{
		return GetInfo("osVersion");
	}
	
	public String GetFirmware()
	{
		return GetInfo("firmware");
	}
	
	public String GetNetwork()
	{
		return GetInfo("network ");
	}

	public String GetDistributer()
	{
		return GetInfo("distributer");
	}
	
	public String GetImsi()
	{
		return GetInfo("imsi");
	}
	
	public String GetNativeImei()
	{
		return GetInfo("nativeImei");
	}
	
	public String GetMac()
	{
		return GetInfo("wifiMacAddress");
	}
	
	public String GetCradleId()
	{
		return GetInfo("cradleId");
	}
	
	public Boolean IsInUse()
	{
		return GetInfo("inUse").equalsIgnoreCase("true");
	}
	
	public String GetPosition()
	{
		return GetInfo("position");
	}

	public String GetMethod()
	{
		return GetInfo("method");
	}
	
	public String GetRotation()
	{
		return GetInfo("rotation");
	}
	
	public String GetLocked()
	{
		return GetInfo("locked");
	}
	
	public String GetRoles()
	{
		return GetInfo("roles");
	}
	
	public String GetCurrentActivity()
	{
		return GetInfo("currentActivity");
	}

	public String GetCurrentPackage()
	{
		return GetInfo("currentPackage");
	}

	public String GetAll()
	{
		return GetInfo("all");
	}
	
	public Boolean HasAudio() 
	{
		return GetInfo("hasAudio").equalsIgnoreCase("true");
	}
	
	public String GetAutomationInfrastructure()
	{
		return GetInfo("automationInfrastructure");
	}
	
	public String GetLocation()
	{
		return Helper.ExecuteMethodString(driver, COMMAND_GETLOCATION, new HashMap<String, Object>());
	}
	
	public Boolean SetLocationByCoordinates(String coordinates)
	{
		Map<String, Object> params = new HashMap<>();
		params.put("coordinates", coordinates);
		return Helper.ExecuteMethod(driver, COMMAND_SETLOCATION, params);
	}
	
	public Boolean SetLocationByAddress(String address)
	{
		Map<String, Object> params = new HashMap<>();
		params.put("address", address);
		return Helper.ExecuteMethod(driver, COMMAND_SETLOCATION, params);
	}
	
	/**
	 * Resets the device location to its actual location. Used alongside the Set device location function. Supported only on Android!
	 * @return
	 */
	public Boolean ResetLocation()
	{
		Map<String, Object> params = new HashMap<>();
		return Helper.ExecuteMethod(driver, COMMAND_RESETLOCATION, new HashMap<String, Object>());
	}
	
	private String GetInfo(String param)
	{
		Map<String, Object> params = new HashMap<>();
		params.put("property", param);
		return Helper.ExecuteMethodString(driver, COMMAND_DEVICEINFO, params);
	}
	
	/**
	 * The function convert audio to text by applying powerful neural network models. 
	 * It accepts an audio file that may be recorded from a device and creates a text file that contains the textual translation of the audio file.
	 * @param pathToAudio - path to audio file on the device or in Perfecto repository
	 * @param lanuage The supported Audio file languages.
	 * @phrase Provides a list of phrases for speech-to-text library to use to avoid confusion. For example, provide the words:‘two’ and ‘four’ to avoid confusion with ‘to’ and ‘for’. 
	 * @param rate Indicates the sampling rate of the audio recording
	 * @param profile Profile of the NLP network
	 * @return
	 */
	public String AudioToText(String pathToAudio, String language, String phrase, String rate, String profile)
	{
	  Audio au = new Audio(driver);
	  return au.ToText(pathToAudio, language, phrase, rate, profile);		
	}
	
	public String AudioToText(String pathToAudio, String lanuage, String phrase, String rate)
	{
		return AudioToText(pathToAudio, lanuage, phrase, rate, null);
	}
	
	public String AudioToText(String pathToAudio, String lanuage, String phrase)
	{
		return AudioToText(pathToAudio, lanuage, phrase, null, null);
	}

	public String AudioToText(String pathToAudio, String language)
	{
		return AudioToText(pathToAudio, language, null, null, null);
	}
	
	public String AudioToText(String pathToAudio)
	{
		return AudioToText(pathToAudio, null, null, null, null);
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
	public String TextToAudio(String text, String repositoryFile, String language, Boolean isFemale)
	{
		Audio au = new Audio(driver);
		return au.FromText(text, repositoryFile, language, isFemale);
	}
	
	public String TextToAudio(String text, String repositoryFile, String language)
	{
		return TextToAudio(text, repositoryFile, language, null);
	}
	
	public String TextToAudio(String text, String repositoryFile)
	{
		return TextToAudio(text, repositoryFile, null, null);
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
	public String ValidateAudio(String deviceAudio, String repositoryKey, Double treshold, String profile, String silenceTrimmingType, Double silenceTrimmingLevel, String calibration, String generic)
	{
	  Audio au = new Audio(driver);
    return au.ValidateAudio(deviceAudio, repositoryKey, treshold, profile, silenceTrimmingType, silenceTrimmingLevel, calibration, generic);
	}
	
	public String ValidateAudio(String deviceAudio, String repositoryKey, Double treshold, String profile, String silenceTrimmingType, Double silenceTrimmingLevel, String calibration)
	{
		return ValidateAudio(deviceAudio, repositoryKey, treshold, profile, silenceTrimmingType, silenceTrimmingLevel, calibration, null);
	}
	
	public String ValidateAudio(String deviceAudio, String repositoryKey, Double treshold, String profile, String silenceTrimmingType, Double silenceTrimmingLevel)
	{
		return ValidateAudio(deviceAudio, repositoryKey, treshold, profile, silenceTrimmingType, silenceTrimmingLevel, null, null);
	}
	
	public String ValidateAudio(String deviceAudio, String repositoryKey, Double treshold, String profile, String silenceTrimmingType)
	{
		return ValidateAudio(deviceAudio, repositoryKey, treshold, profile, silenceTrimmingType, null, null, null);
	}
	
	public String ValidateAudio(String deviceAudio, String repositoryKey, Double treshold, String profile)
	{
		return ValidateAudio(deviceAudio, repositoryKey, treshold, profile, null, null, null, null);
	}
	
	public String ValidateAudio(String deviceAudio, String repositoryKey, Double treshold)
	{
		return ValidateAudio(deviceAudio, repositoryKey, treshold, null, null, null, null, null);
	}

	public String ValidateAudio(String deviceAudio, String repositoryKey)
	{
		return ValidateAudio(deviceAudio, repositoryKey, null, null, null, null, null, null);
	}
	
	/**
	 * Plays an audio file into the device audio-in. Supported file types: MP3 and WAV.
	 * @param key The full repository path, including directory and file name, where to locate the audio file. Example - PRIVATE:dir1/dir2/name.mp3
	 * @param wait The execution mode. No wait (default) - continue to the next line in the script immediately
	 * @return
	 */
	public Boolean InjectAudio(String key, Boolean wait)
	{
	  Audio au = new Audio(driver);
	  return au.Inject(key, wait);
	}
	
	/**
	 * Plays an audio file into the device audio-in. Supported file types: MP3 and WAV.
	 * @param key The full repository path, including directory and file name, where to locate the audio file. Example - PRIVATE:dir1/dir2/name.mp3
	 * @return
	 */
	public Boolean InjectAudio(String key)
	{
		return InjectAudio(key, null);
	}
	
	
	/**
	 * Command starts recording the audio output from the device and creates a WAV file. The file is saved in the media storage server. 
	 * @return A URL to the file location is returned in the response to the command
	 */
	public String StartAudioRecording()
	{
		return new Audio(driver).StartRecording();
	}

	/**
	 * Command stops recording the audio output from the device, closes the file, and stores in the media storage server at the URL declared at the start of audio recording.
	 * @return
	 */
	public String StopAudioRecording()
	{
	  return new Audio(driver).StopRecording();
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
	 * @param phrase Provides a list of phrases for speech-to-text library to use to avoid confusion. For example, provide the words:‘two’ and ‘four’ to avoid confusion with ‘to’ and ‘for’.
	 * @return
	 */
	public Boolean ValidateAudioToText(String content, String audioInput, String target, String match, Integer index, Boolean words, String exact, Double threshold, Integer confidence, String language, String rate, String profile, List<String> phrases)
	{
	  return new Audio(driver).ValidateAudioToText(content, audioInput, target, match, index, words, exact, threshold, confidence, language);
	}
	
	public Boolean ValidateAudioToText(String content, String audioInput, String target, String match, Integer index, Boolean words, String exact, Double threshold, Integer confidence, String language, String rate, String profile)
	{
		return ValidateAudioToText(content, audioInput, target, match, index, words, exact, threshold, confidence, language, rate, profile, null);
	}
	
	public Boolean ValidateAudioToText(String content, String audioInput, String target, String match, Integer index, Boolean words, String exact, Double threshold, Integer confidence, String language, String rate)
	{
		return ValidateAudioToText(content, audioInput, target, match, index, words, exact, threshold, confidence, language, rate, null, null);
	}

	public Boolean ValidateAudioToText(String content, String audioInput, String target, String match, Integer index, Boolean words, String exact, Double threshold, Integer confidence, String language)
	{
		return ValidateAudioToText(content, audioInput, target, match, index, words, exact, threshold, confidence, language, null, null, null);
	}
	
	public Boolean ValidateAudioToText(String content, String audioInput, String target, String match, Integer index, Boolean words, String exact, Double threshold, Integer confidence)
	{
		return ValidateAudioToText(content, audioInput, target, match, index, words, exact, threshold, confidence, null, null, null, null);
	}
	
	public Boolean ValidateAudioToText(String content, String audioInput, String target, String match, Integer index, Boolean words, String exact, Double threshold)
	{
		return ValidateAudioToText(content, audioInput, target, match, index, words, exact, threshold, null, null, null, null, null);
	}
	
	public Boolean ValidateAudioToText(String content, String audioInput, String target, String match, Integer index, Boolean words, String exact)
	{
		return ValidateAudioToText(content, audioInput, target, match, index, words, exact, null, null, null, null, null, null);
	}
	
	public Boolean ValidateAudioToText(String content, String audioInput, String target, String match, Integer index, Boolean words)
	{
		return ValidateAudioToText(content, audioInput, target, match, index, words, null, null, null, null, null, null, null);
	}
	
	public Boolean ValidateAudioToText(String content, String audioInput, String target, String match, Integer index)
	{
		return ValidateAudioToText(content, audioInput, target, match, index, null, null, null, null, null, null, null, null);
	}
	
	public Boolean ValidateAudioToText(String content, String audioInput, String target, String match)
	{
		return ValidateAudioToText(content, audioInput, target, match, null, null, null, null, null, null, null, null, null);
	}

	public Boolean ValidateAudioToText(String content, String audioInput, String target)
	{
		return ValidateAudioToText(content, audioInput, target, null, null, null, null, null, null, null, null, null, null);
	}
	
	public Boolean ValidateAudioToText(String content, String audioInput)
	{
		return ValidateAudioToText(content, audioInput, null, null, null, null, null, null, null, null, null, null, null);
	}
	
	public Boolean VoiceAssistantInject(String text)
	{
		return new Audio(driver).VoiceAssistantInject(text);
	}
	
	 public String TakeScreenshot()
	 {
	   Logger.LogInfo("Taking screenshot");
	   return driver.getScreenshotAs(OutputType.BASE64);
	 }
}