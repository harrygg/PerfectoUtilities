package g.perfecto.utilities;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.appium.java_client.AppiumDriver;

public class Audio
{
	public String repositoryKey;
	public Boolean wait;
	public String audioInput;
	public String language;
	public String phrase;
	public String profile;
	public String rate;
	public String repositoryFile;
	public String inputText;
	public Boolean isFemale;
	public String deviceAudioFile;
	public String validationFile;
	public Double threshold;
	public String silenceTrimmingType;
	public Double silenceTrimmingLevel;
	public String calibration;
	public String generic;
	public String content;
	public String target;
	public String match;
	public Integer index;
	public Boolean words;
	public String exact;
	public Integer confidence;
	public List<String> phrases;
	
	private Map<String, Object> params = new HashMap<>();
	private AppiumDriver driver;
	
	private static final String COMMAND_INJECT_AUDIO = "mobile:audio:inject";
	private static final String COMMAND_AUDIO_RECORDING_START = "mobile:audio.recording:start";
	private static final String COMMAND_AUDIO_RECORDING_STOP = "mobile:audio.recording:stop";
	private static final String COMMAND_TEXTTO_AUDIO = "mobile:text:audio";
	private static final String COMMAND_VOICE_ASSIST = "mobile:voice:assist";
	private static final String COMMAND_AUDIO_VALIDATION = "mobile:audio:validation";
	private static final String COMMAND_AUDIO_TEXT_VALIDATION = "mobile:audio-text:validation";
	private static final String COMMAND_AUDIO_TO_TEXT = "mobile:audio:text";
	private Log log = LogFactory.getLog(Audio.class);
	
	public Audio(AppiumDriver driver)
	{
	  log.debug("Creating Audio object");
		this.driver = driver;
	}
	

	/**
	 * Command starts recording the audio output from the device and creates a WAV file. The file is saved in the media storage server. 
	 * @return A URL to the file location is returned in the response to the command
	 */
	public String startRecording()
	{
		return Helper.executeMethodString(driver, COMMAND_AUDIO_RECORDING_START, new HashMap<String, Object>());
	}

	/**
	 * Command stops recording the audio output from the device, closes the file, and stores in the media storage server at the URL declared at the start of audio recording.
	 * @return
	 */
	public String stopRecording()
	{
		return Helper.executeMethodString(driver, COMMAND_AUDIO_RECORDING_STOP, new HashMap<String, Object>());
	}
	
	/**
	 * Plays an audio file into the device audio-in. Supported file types: MP3 and WAV.
	 * @param key The full repository path, including directory and file name, where to locate the audio file. Example - PRIVATE:dir1/dir2/name.mp3
	 * @param wait The execution mode. No wait (default) - continue to the next line in the script immediately
	 * @return
	 */
	public Boolean inject(String key, Boolean wait)
	{
		repositoryKey = key;
		this.wait = wait;
		return inject();
	}
	
	/**
	 * Plays an audio file into the device audio-in. Supported file types: MP3 and WAV.
	 * @param key The full repository path, including directory and file name, where to locate the audio file. Example - PRIVATE:dir1/dir2/name.mp3
	 * @return
	 */
	public Boolean inject(String key)
	{
		this.repositoryKey = key;
		return inject();
	}
	
	public Boolean inject()
	{
	  Map<String, Object> params = new HashMap<>();
		params.put("key", repositoryKey);		
		if (wait != null)
			params.put("wait", "wait");
		
		return Helper.executeMethod(driver, COMMAND_INJECT_AUDIO, params);
	}
	
	public Boolean voiceAssistantInject(String text)
	{
	  Map<String, Object> params = new HashMap<>();
		params.put("text", text);
		return Helper.executeMethod(driver, COMMAND_VOICE_ASSIST, params);
	}
	
	/**
	 * The function convert audio to text by applying powerful neural network models. 
	 * It accepts an audio file that may be recorded from a device and creates a text file that contains the textual translation of the audio file.
	 * @param audioInput - path to audio file on the device or in Perfecto repository
	 * @param language The supported Audio file languages.
	 * @phrase Provides a list of phrases for speech-to-text library to use to avoid confusion. For example, provide the words:�two� and �four� to avoid confusion with �to� and �for�. 
	 * @param rate Indicates the sampling rate of the audio recording
	 * @param profile Profile of the NLP network
	 * @return
	 */
	public String toText(String audioInput, String language, String phrase, String rate, String profile)
	{
		this.audioInput = audioInput;
		this.language = language;
		this.phrase = phrase;
		this.rate = rate;
		this.profile = profile;
		return toText();
	}
	
	public String toText(String audioInput, String language, String phrase, String rate)
	{
		this.audioInput = audioInput;
		this.language = language;
		this.phrase = phrase;
		this.rate = rate;
		return toText();
	}
	
	public String toText(String audioInput, String language, String phrase)
	{
		this.audioInput = audioInput;
		this.language = language;
		this.phrase = phrase;
		return toText();
	}

	public String ToText(String audioInput, String language)
	{
		this.audioInput = audioInput;
		this.language = language;
		return toText();
	}
	
	public String toText(String audioInput)
	{
		this.audioInput = audioInput;		
		return toText();
	}
	
	public String toText()
	{		
	  Map<String, Object> params = new HashMap<>();
	  
		if (audioInput.startsWith("PUBLIC:") || audioInput.startsWith("PRIVATE:") || audioInput.startsWith("GROUP:"))
			params.put("key", audioInput);
		else
			params.put("deviceAudio", audioInput);

		if (language != null && !language.isEmpty())
			params.put("language", language);
		
		if (phrase != null && !phrase.isEmpty())
			params.put("phrase", phrase);
		
		if (rate != null && !rate.isEmpty())
			params.put("rate", rate);
		
		if (profile != null && !profile.isEmpty())
			params.put("profile", profile);
		
		return Helper.executeMethodString(driver, COMMAND_AUDIO_TO_TEXT, params);
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
	public String fromText(String text, String repositoryFile, String language, Boolean isFemale)
	{
		this.inputText = text;
		this.repositoryFile = repositoryFile;
		this.language = language;
		this.isFemale = isFemale;
		return fromText();
	}
	
	public String textToAudio(String text, String repositoryFile, String language)
	{
		this.inputText = text;
		this.repositoryFile = repositoryFile;
		this.language = language;
		return fromText();
	}
	
	public String textToAudio(String inputText, String repositoryFile)
	{
		this.inputText = inputText;
		this.repositoryFile = repositoryFile;
		return fromText();
	}
	

	public String textToAudio(String inputText)
	{
		this.inputText = inputText;
		return fromText();
	}
	
	public String fromText()
	{
	  Map<String, Object> params = new HashMap<>();
		
		if (inputText.startsWith("PUBLIC:") || inputText.startsWith("PRIVATE:") || inputText.startsWith("GROUP:"))
			params.put("key", inputText);
		else
			params.put("text", inputText);
		
		params.put("repositoryFile", repositoryFile);
		
		if (language != null && !language.isEmpty())
			params.put("language", "us-english");
		
		if (isFemale != null)
			params.put("gender", isFemale ? "female" : "male");
		
		return Helper.executeMethodString(driver, COMMAND_TEXTTO_AUDIO, params);
		
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
	public String validateAudio(String deviceAudio, String repositoryKey, Double threshold, String profile, String silenceTrimmingType, Double silenceTrimmingLevel, String calibration, String generic)
	{
		this.deviceAudioFile = deviceAudio;
		this.repositoryKey = repositoryKey;
		this.threshold = threshold;
		this.profile = profile;
		this.silenceTrimmingType = silenceTrimmingType;
		this.silenceTrimmingLevel = silenceTrimmingLevel;
		this.calibration = calibration;
		this.generic = generic;
		
		return validate();
	}
	
	public String validate(String deviceAudio, String repositoryKey, Double threshold, String profile, String silenceTrimmingType, Double silenceTrimmingLevel, String calibration)
	{
		this.deviceAudioFile = deviceAudio;
		this.repositoryKey = repositoryKey;
		this.threshold = threshold;
		this.profile = profile;
		this.silenceTrimmingType = silenceTrimmingType;
		this.silenceTrimmingLevel = silenceTrimmingLevel;
		this.calibration = calibration;
		
		return validate();
	}
	
	public String validateAudio(String deviceAudio, String repositoryKey, Double threshold, String profile, String silenceTrimmingType, Double silenceTrimmingLevel)
	{
		this.deviceAudioFile = deviceAudio;
		this.repositoryKey = repositoryKey;
		this.threshold = threshold;
		this.profile = profile;
		this.silenceTrimmingType = silenceTrimmingType;
		this.silenceTrimmingLevel = silenceTrimmingLevel;
		
		return validate();
	}
	
	public String validateAudio(String deviceAudio, String repositoryKey, Double threshold, String profile, String silenceTrimmingType)
	{
		this.deviceAudioFile = deviceAudio;
		this.repositoryKey = repositoryKey;
		this.threshold = threshold;
		this.profile = profile;
		this.silenceTrimmingType = silenceTrimmingType;
		
		return validate();
	}
	
	public String validateAudio(String deviceAudio, String repositoryKey, Double threshold, String profile)
	{
		this.deviceAudioFile = deviceAudio;
		this.repositoryKey = repositoryKey;
		this.threshold = threshold;
		this.profile = profile;
		
		return validate();
	}
	
	public String validateAudio(String deviceAudio, String repositoryKey, Double threshold)
	{
		this.deviceAudioFile = deviceAudio;
		this.repositoryKey = repositoryKey;
		this.threshold = threshold;
		
		return validate();
	}

	public String validateAudio(String deviceAudio, String repositoryKey)
	{
		this.deviceAudioFile = deviceAudio;
		this.repositoryKey = repositoryKey;
		
		return validate();
	}
	
	public String validate()
	{
	  Map<String, Object> params = new HashMap<>();
		
		params.put("deviceAudio", deviceAudioFile);
		params.put("key", repositoryKey);
		
		if (threshold != null)
			params.put("treshold", threshold.toString());

		if (profile != null)
			params.put("profile", profile);			
		
		if (silenceTrimmingType != null)
			params.put("deviceAudio.silenceTrimming.type", silenceTrimmingType);
		
		if (silenceTrimmingLevel != null)
			params.put("deviceAudio.silenceTrimming.level", silenceTrimmingLevel.toString());
		
		if (calibration != null)
			params.put("calibration", calibration);
		
		if (calibration != null)
			params.put("generic", generic);
		
		return Helper.executeMethodString(driver, COMMAND_AUDIO_VALIDATION, params);
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
		this.content = content;
		this.audioInput = audioInput;
		this.target = target;
		this.match = match;
		this.index = index;
		this.words = words;
		this.exact = exact;
		this.threshold = threshold;
		this.confidence = confidence;
		this.language = language;
		this.rate = rate;
		this.profile = profile;
		this.phrases = phrases;
		
		return validateAudioToText();
	}
	
	public Boolean validateAudioToText(String content, String audioInput, String target, String match, Integer index, Boolean words, String exact, Double threshold, Integer confidence, String language, String rate, String profile)
	{
		this.content = content;
		this.audioInput = audioInput;
		this.target = target;
		this.match = match;
		this.index = index;
		this.words = words;
		this.exact = exact;
		this.threshold = threshold;
		this.confidence = confidence;
		this.language = language;
		this.rate = rate;
		this.profile = profile;
		
		return validateAudioToText();
	}
	
	public Boolean validateAudioToText(String content, String audioInput, String target, String match, Integer index, Boolean words, String exact, Double threshold, Integer confidence, String language, String rate)
	{
		this.content = content;
		this.audioInput = audioInput;
		this.target = target;
		this.match = match;
		this.index = index;
		this.words = words;
		this.exact = exact;
		this.threshold = threshold;
		this.confidence = confidence;
		this.language = language;
		this.rate = rate;
		
		return validateAudioToText();
	}

	public Boolean validateAudioToText(String content, String audioInput, String target, String match, Integer index, Boolean words, String exact, Double threshold, Integer confidence, String language)
	{
		this.content = content;
		this.audioInput = audioInput;
		this.target = target;
		this.match = match;
		this.index = index;
		this.words = words;
		this.exact = exact;
		this.threshold = threshold;
		this.confidence = confidence;
		this.language = language;
		
		return validateAudioToText();
	}
	
	public Boolean validateAudioToText(String content, String audioInput, String target, String match, Integer index, Boolean words, String exact, Double threshold, Integer confidence)
	{
		this.content = content;
		this.audioInput = audioInput;
		this.target = target;
		this.match = match;
		this.index = index;
		this.words = words;
		this.exact = exact;
		this.threshold = threshold;
		this.confidence = confidence;
		
		return validateAudioToText();
	}
	
	public Boolean validateAudioToText(String content, String audioInput, String target, String match, Integer index, Boolean words, String exact, Double threshold)
	{
		this.content = content;
		this.audioInput = audioInput;
		this.target = target;
		this.match = match;
		this.index = index;
		this.words = words;
		this.exact = exact;
		this.threshold = threshold;
		
		return validateAudioToText();
	}
	
	public Boolean validateAudioToText(String content, String audioInput, String target, String match, Integer index, Boolean words, String exact)
	{
		this.content = content;
		this.audioInput = audioInput;
		this.target = target;
		this.match = match;
		this.index = index;
		this.words = words;
		this.exact = exact;
		
		return validateAudioToText();
	}
	
	public Boolean validateAudioToText(String content, String audioInput, String target, String match, Integer index, Boolean words)
	{
		this.content = content;
		this.audioInput = audioInput;
		this.target = target;
		this.match = match;
		this.index = index;
		this.words = words;
		
		return validateAudioToText();
	}
	
	public Boolean validateAudioToText(String content, String audioInput, String target, String match, Integer index)
	{
		this.content = content;
		this.audioInput = audioInput;
		this.target = target;
		this.match = match;
		this.index = index;
		
		return validateAudioToText();
	}
	
	public Boolean validateAudioToText(String content, String audioInput, String target, String match)
	{
		this.content = content;
		this.audioInput = audioInput;
		this.target = target;
		this.match = match;
		
		return validateAudioToText();
	}

	public Boolean validateAudioToText(String content, String audioInput, String target)
	{
		this.content = content;
		this.audioInput = audioInput;
		this.target = target;
		return validateAudioToText();
	}
	
	public Boolean validateAudioToText(String content, String audioInput)
	{
		this.content = content;
		this.audioInput = audioInput;
		
		return validateAudioToText();
	}
	
	public Boolean validateAudioToText()
	{
	  Map<String, Object> params = new HashMap<>();
		params.put("content", content);
		
		if (audioInput.startsWith("PUBLIC:") || audioInput.startsWith("PRIVATE:") || audioInput.startsWith("GROUP:"))
			params.put("key", audioInput);
		else
			params.put("deviceAudio", audioInput);
		
		if (target != null)
			params.put("target", target);
		
		if (match != null)
			params.put("match", match);
		
		if (index != null)
			params.put("index", index);
		
		if (words != null)
			if (words)
				params.put("words", "words");
		
		if (exact != null)
			params.put("exact", exact);
		
		if (threshold != null)
			params.put("threshold", threshold);			
			
		if (confidence != null)
			params.put("confidence", confidence);
		
		if (language != null)
			params.put("language", language);
		
		if (rate != null)
			params.put("rate", rate);
		
		if (profile != null)
			params.put("profile", profile);
		
		if (phrases != null)
			params.put("phrase", phrases);
		
		return Helper.executeMethod(driver, COMMAND_AUDIO_TEXT_VALIDATION, params);
	}
}