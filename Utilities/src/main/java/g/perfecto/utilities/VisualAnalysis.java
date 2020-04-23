package g.perfecto.utilities;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.remote.RemoteWebDriver;

public class VisualAnalysis {
	
	private RemoteWebDriver driver = null;
	private Map<String, Object> params = new HashMap<>();
	
	public String label = null;
	public String text = "";
	public int treshold = 80;
	public int index = 1;
	public Boolean ignoreCase = false;
	public Boolean scroll = false;
	public int maxScroll = 5;
	public String next = "";
	public String backlight = null;
	public String source = VisualAnalysisParameters.SOURCE_DEFAULT;
	public Boolean words = false;
	public Boolean ignoreSpace = false;
	public Boolean ignorePunct = false;
	public String analysis = null;
	public String language = null;
	public String scope = null;
	public String caps = null;
	public String labelDirection = null;
	public String labelOffset = null;
	public String screenTop = null;
	public String screenHeight = null;
	public String screenLeft = null;
	public String screenWidth = null;
	public String match = null;
	public String textMatch = null;
	public String operation = null;
	public int repeat = 1;
	public String inverse = null;
	public String policy = null;
	public String relationDirection = null;
	public String relationInline = null;
	public String reportResolution = null;
	public String grid = null;
	public String ocr = null;
	public String imageTop = null;
	public String imageHeight = null;
	public String imageLeft = null;
	public String imageWidth = null;
	public int imageBoundNeedleBound = 100;
	public int imageBoundHeystackArea = 786432;
	public String matchGeneric = null;
	public String profile = null;
	public String shift = null;
	public String offset = null;
	public String content = null;
	public String xAlignment = null;
	public String yAlignment = null;
	public String xWidth = null;
	public String yHeight = null;
	public String context = null;
	public int lines = -1;
	public int timeout = -1;
	public int interval = 0;
	public String measurement = null;
	public String key = null;
	
	private final String COMMAND_EDITIMAGEGET = "mobile:edit-image:get";
	private final String COMMAND_EDITIMAGESET = "mobile:edit-image:set";
	private final String COMMAND_EDITTEXTGET = "mobile:edit-text:get";
	private final String COMMAND_EDITTEXTSET = "mobile:edit-text:set";
	private final String COMMAND_IMAGEBUTTONCLICK = "mobile:button-image:click";
	private final String COMMAND_BUTTONTEXTCLICK = "mobile:button-text:click";
	private final String COMMAND_IMAGESELECT = "mobile:image:select";
	private final String COMMAND_TEXTSELECT = "mobile:text:select";
	private final String COMMAND_CHECKPOINTIMAGE = "mobile:checkpoint:image";
	private final String COMMAND_CHECKPOINTTEXT = "mobile:checkpoint:text";
	private final String COMMAND_IMAGEFIND = "mobile:image:find";
	private final String COMMAND_TEXTFIND = "mobile:text:find";
	private final String COMMAND_SCREENTEXT = "mobile:screen:text";
	
	public VisualAnalysis(RemoteWebDriver driver)
	{
		this.driver = driver;
	}
	
	public void ResetParams()
	{
		params.clear();
		label = null;
		text = "";
		treshold = 80;
		index = 1;
		ignoreCase = false;
		scroll = false;
		maxScroll = 5;
		next = "";
		backlight = null;
		source = VisualAnalysisParameters.SOURCE_DEFAULT;
		words = false;
		ignoreSpace = false;
		ignorePunct = false;
		analysis = null;
		language = null;
		scope = null;
		caps = null;
		labelDirection = null;
		labelOffset = null;
		screenTop = null;
		screenHeight = null;
		screenLeft = null;
		screenWidth = null;
		match = null;
		textMatch = null;
		operation = null;
		repeat = 1;
		inverse = null;
		policy = null;
		relationDirection = null;
		relationInline = null;
		reportResolution = null;
		grid = null;
		ocr = null;
		imageTop = null;
		imageHeight = null;
		imageLeft = null;
		imageWidth = null;
		imageBoundNeedleBound = 100;
		imageBoundHeystackArea = 786432;
		matchGeneric = null;
		profile = null;
		shift = null;
		offset = null;
		content = null;
		xAlignment = null;
		yAlignment = null;
		xWidth = null;
		yHeight = null;
		context = null;
		lines = -1;
		timeout = -1;
		interval = 0;
		measurement = null;
		key = null;
	}
	
	/**
	 * Identifies an edit field, based on an image label, and retrieves its text value. The value is returned as a string.
	 * @return
	 */
	public String EditImageGet()
	{
		params.clear();
		if (!AddMandatoryParameter("label", label))
			return null;
		SetAdditionalParams();
		
		return Helper.ExecuteMethodString(driver, COMMAND_EDITIMAGEGET, params);	
	}
	
	/**
	 * Identifies an edit field, based on an image label, and retrieves its text value. The value is returned as a string.
	 * @param label
	 * @return
	 */
	public String EditImageGet(String label)
	{
		this.label = label;
		return EditImageGet();
	}

	/**
	 * Identifies an edit field, based on an image label, and retrieves its text value. The value is returned as a string.
	 * @param label
	 * @param lines
	 * @return
	 */
	public String EditImageGet(String label, int lines)
	{
		this.lines = lines;
		return EditImageGet(label);
	}
	
	/**
	 * Identifies an edit field, based on an image label, and inserts the specified text in the value parameter into the field.
	 * @return
	 */
	public Boolean EditImageSet()
	{
		params.clear();
		if (!AddMandatoryParameter("label", label))
			return false;
		params.put("text",  text);
		SetAdditionalParams();
		
		return Helper.ExecuteMethod(driver, COMMAND_EDITIMAGESET, params);	

	}
	
	/**
	 * Identifies an edit field, based on an image label, and inserts the specified text in the value parameter into the field. 
	 * @param label
	 * @param text
	 * @return
	 */
	public Boolean EditImageSet(String label, String text)
	{
		this.label = label;
		this.text = text;
		return EditImageSet();
	}
	
	/**
	 * Identifies an edit field, based on an image label, and inserts the specified text in the value parameter into the field. 
	 * @param label
	 * @param text
	 * @param caps
	 * @return
	 */
	public Boolean EditImageSet(String label, String text, String caps)
	{
		this.caps = caps;
		return EditImageSet(label, text);
	}
	
	/**
	 * Identifies an edit field, based on a text label, and retrieves its value. The value is returned as a string.
	 * @return
	 */
	public String EditTextGet()
	{
		params.clear();
		if (!AddMandatoryParameter("label", label))
			return null;
		SetAdditionalParams();
		
		return Helper.ExecuteMethodString(driver, COMMAND_EDITTEXTGET, params);	
	}
	
	/**
	 * Identifies an edit field, based on a text label, and retrieves its value. The value is returned as a string.
	 * @param label
	 * @return
	 */
	public String EditTextGet(String label)
	{
		this.label = label;
		return EditTextGet();
	}
	
	/**
	 * Identifies an edit field, based on a text label, and retrieves its value. The value is returned as a string.
	 * @param label
	 * @param lines
	 * @return
	 */
	public String EditTextGet(String label, int lines)
	{
		this.lines = lines;
		return EditTextGet(label);
	}
	
	/**
	 * Identifies an edit field, based on a text label, and inserts the specified text in the value parameter into the field. 
	 * The field is in relation to the found label and is defined by the label position and offset parameters.
	 * @return
	 */
	public Boolean EditTextSet()
	{
		params.clear();
		if (!AddMandatoryParameter("label", label))
			return false;	
		params.put("text", this.text);
		
		SetAdditionalParams();
		
		return Helper.ExecuteMethod(driver, COMMAND_EDITTEXTSET, params);	
	}
	
	/**
	 * Identifies an edit field, based on a text label, and inserts the specified text in the value parameter into the field. 
	 * The field is in relation to the found label and is defined by the label position and offset parameters.
	 * @param label
	 * @param text
	 * @return
	 */
	public Boolean EditTextSet(String label, String text)
	{
		this.label = label;
		this.text = text;
		return EditTextSet();
	}
	
	/**
	 * Identifies an edit field, based on a text label, and inserts the specified text in the value parameter into the field. 
	 * The field is in relation to the found label and is defined by the label position and offset parameters.
	 * @param label
	 * @param text
	 * @param scope
	 * @return
	 */
	public Boolean EditTextSet(String label, String text, String scope)
	{
		this.scope = scope;
		return EditTextSet(label, text);
	}
	
	/**
	 * Identifies an edit field, based on a text label, and inserts the specified text in the value parameter into the field. 
	 * The field is in relation to the found label and is defined by the label position and offset parameters.
	 * @param label
	 * @param text
	 * @param scope
	 * @param caps
	 * @return
	 */
	public Boolean EditTextSet(String label, String text, String scope, String caps)
	{
		this.caps = caps;
		return EditTextSet(label, text, scope);
	}
	
	/**
	 * Identifies a button, based on an image label, and clicks on it.
	 * @return
	 */
	public Boolean ImageButtonClick()
	{
		params.clear();
		if (!AddMandatoryParameter("label", label))
			return false;
		SetAdditionalParams();
		
		return Helper.ExecuteMethod(driver, COMMAND_IMAGEBUTTONCLICK, params);	
	}

	/**
	 * Identifies a button, based on a text label, and clicks on it.
	 * @return
	 */
	public Boolean TextButtonClick()
	{
		params.clear();
		if (!AddMandatoryParameter("label", label))
			return false;
		SetAdditionalParams();
		
		return Helper.ExecuteMethod(driver, COMMAND_BUTTONTEXTCLICK, params);	
	}
	
	/**
	 * Identifies a button, based on a text label, and clicks on it.
	 * @param label
	 * @return
	 */
	public Boolean TextButtonClick(String label)
	{
		this.label = label;
		return TextButtonClick();
	}
	
	/**
	 * Identifies a button, based on a text label, and clicks on it.
	 * @param label
	 * @param treshold
	 * @return
	 */
	public Boolean ButtonTextClick(String label, int treshold)
	{
		this.treshold = treshold;
		return TextButtonClick(label);
	}
	
	/**
	 * Identifies a button, based on a text label, and clicks on it.
	 * @param label
	 * @param scroll
	 * @return
	 */
	public Boolean ButtonTextClick(String label, Boolean scroll)
	{
		this.scroll = scroll;
		return TextButtonClick(label);
	}
	
	/**
	 * Finds an image (needle) on the device screen (haystack) and clicks on it.
	 * @return
	 */
	public Boolean SelectImage()
	{
		params.clear();
		if (!AddMandatoryParameter("content", content))
			return false;
		SetAdditionalParams();
		
		return Helper.ExecuteMethod(driver, COMMAND_IMAGESELECT, params);	
	}
	
	/**
	 * Finds an image (needle) on the device screen (haystack) and clicks on it.
	 * @param content
	 * @return
	 */
	public Boolean SelectImage(String content)
	{
		this.content = content;
		return SelectImage();
	}
	
	/**
	 * Finds text (needle) on the device screen (haystack) and clicks on it.
	 * @return true if the function is executed successfully. 
	 */
	public Boolean SelectText()
	{
		params.clear();
		if (!AddMandatoryParameter("content", content))
			return false;
		SetAdditionalParams();
		
		return Helper.ExecuteMethod(driver, COMMAND_TEXTSELECT, params);	
	}
	
	/**
	 * Finds text (needle) on the device screen (haystack) and clicks on it.
	 * @param content - the text on the screen to click on
	 * @return true if the function is executed successfully. 
	 */
	public Boolean SelectText(String content)
	{
		this.content = content;
		return SelectText();
	}
	
	
	public Boolean SelectText(String content, String context)
	{
		if (context != null && context.trim().length() > 0 && (Arrays.asList(VisualAnalysisParameters.CONTEXTS).contains(context)))
			this.context = context;
		return SelectText(content);
	}
	
	/**
	 * Verifies that the given image appears on the device screen. In the case of failure, the script will be marked as failed.
	 * @return
	 */
	public Boolean ImageCheckPoint()
	{	
		params.clear();
		if (!AddMandatoryParameter("content", content))
			return false;		
		SetAdditionalParams();
		
		return Helper.ExecuteMethod(driver, COMMAND_CHECKPOINTIMAGE, params);
	}
	
	/**
	 * Verifies that the given image appears on the device screen. In the case of failure, the script will be marked as failed.
	 * @param content
	 * @return
	 */
	public Boolean ImageCheckPoint(String content)
	{
		this.content = content;
		return ImageCheckPoint();
	}
	
	/**
	 * Verifies that the given text appears on the device screen. In the case of failure, the script will be marked as failed.
	 * @return
	 */
	public Boolean TextCheckPoint() 
	{
		params.clear();
		if (!AddMandatoryParameter("content", content))
			return false;
		SetAdditionalParams();
		
		return Helper.ExecuteMethod(driver, COMMAND_CHECKPOINTTEXT, params);
	}
	
	/**
	 * Verifies that the given text appears on the device screen. In the case of failure, the script will be marked as failed.
	 * @param content
	 * @return
	 */
	public Boolean TextCheckPoint(String content)
	{
		this.content = content;
		return TextCheckPoint();
	}
	
	/**
	 * Verifies that the given text appears on the device screen. In the case of failure, the script will be marked as failed.
	 * @param content
	 * @param match
	 * @return
	 */
	public Boolean TextCheckPoint(String content, String match)
	{
		this.match = match;
		return TextCheckPoint(content);
	}
	
	/**
	 * Finds an image (needle) on the device screen (haystack), and stores the coordinates for future commands.
	 * @return
	 */
	public Boolean FindImage()
	{
		return Find(COMMAND_IMAGEFIND);	
	}
	
	/**
	 * Finds an image (needle) on the device screen (haystack), and stores the coordinates for future commands.
	 * @param content
	 * @return
	 */
	public Boolean FindImage(String content)
	{
		this.content = content;
		return FindImage();		
	}
	
	/**
	 * Finds an image (needle) on the device screen (haystack), and stores the coordinates for future commands.
	 * @param content
	 * @param context
	 * @return
	 */
	public Boolean FindImage(String content, String context)
	{
		this.context = context;
		return FindImage(content);		
	}
	
	/**
	 * Finds text (needle) on the device screen (haystack), and stores the coordinates for future commands.
	 * @return
	 */
	public Boolean FindText()
	{
		return Find(COMMAND_TEXTFIND);
	}
	
	private Boolean Find(String methodName)
	{
		params.clear();
		if (!AddMandatoryParameter("content", content))
			return false;
		SetAdditionalParams();
		
		return Helper.ExecuteMethod(driver, methodName, params);
	}
	
	/**
	 * Returns the text that appears on the screen of the device. It does not select the text.
	 * @return
	 */
	public String ScreenText()
	{
		params.clear();
		SetAdditionalParams();		
		return Helper.ExecuteMethodString(driver, COMMAND_SCREENTEXT, params);
	}
	
	/**
	 * Returns the text that appears on the screen of the device. It does not select the text.
	 * @param key
	 * @return
	 */
	public String ScreenText(String key)
	{
		this.key = key;
		return ScreenText();
	}
	

	/**
	 * Returns the text that appears on the screen of the device. It does not select the text.
	 * @param key
	 * @param context
	 * @return
	 */
	public String ScreenText(String key, String context)
	{
		this.context = context;
		return ScreenText(key);
	}
	
	private Boolean AddMandatoryParameter(String parameterName, String parameterValue)
	{
		if (parameterValue == null || parameterValue.trim().length() == 0)
		{
			System.out.println("ERROR! Missing mandatory parameter " + parameterName);
			return false;
		}
		
		params.put(parameterName, parameterValue);
		
		return true;
	}

	
	private void SetAdditionalParams() {
		
		if (treshold != 80)
			params.put("threshold", treshold);
		
		if (ignoreCase)
			params.put("ignorecase", "nocase");

		if (index != 1)
			params.put("index", index);
		
		if (scroll)
			params.put("scrolling", "scroll");
		
		if (maxScroll != 5)
			params.put("maxscroll", maxScroll);
		
		if (words)
			params.put("words", "words");
		
		if (ignoreSpace)
			params.put("ignorespace", "nospace");
		
		if (ignorePunct)
			params.put("ignorepunct", "nopunct");
		
		if (source != VisualAnalysisParameters.SOURCE_AUTO) 
		{
			if (Arrays.asList(VisualAnalysisParameters.SOURCES).contains(source))
				params.put("source", source);
			else
				System.out.println("WARNING! Unsupported source type '" + source + "'. Using default one 'auto'");
		}
		
		if (analysis != null)
		{
			if (Arrays.asList(VisualAnalysisParameters.ANALYSIS_VALUES).contains(analysis))
			{	
				if (source == VisualAnalysisParameters.SOURCE_NATIVE)
					System.out.println("WARNING! The 'analysis' parameter is irrelevant when using native screen source.");			
				params.put("analysis", analysis);
			}
			else
				System.out.println("WARNING! Unsupported analysis type '" + analysis + "'. Possible values are: " + String.join(", ", VisualAnalysisParameters.ANALYSIS_VALUES));
		}
		
		if (language != null)
		{
			if (Arrays.asList(VisualAnalysisParameters.LANGUAGES).contains(language))
				params.put("language", language);
			else
				System.out.println("WARNING! Unsupported language '" + language + "'. Possible values are: " + String.join(", ", VisualAnalysisParameters.LANGUAGES));
		}
		
		if (screenTop != null)
			params.put("screen.top", screenTop);
		
		if (screenHeight != null)
			params.put("screen.height", screenHeight);
		
		if (screenWidth != null)
			params.put("screen.width", screenWidth);
		
		if (screenLeft != null)
			params.put("screen.left", screenLeft);
		
		if (match != null)
			params.put("match", match);
		
		if (textMatch != null) {
			if (Arrays.asList(VisualAnalysisParameters.TEXT_MATCHES).contains(textMatch))
				params.put("match", textMatch);
			else
				System.out.println("WARNING! Unsupported match type '" + textMatch + "'. Possible values are: " + String.join(", ", VisualAnalysisParameters.TEXT_MATCHES));				
		}
			
		if (inverse != null)
		{
			if (Arrays.asList(VisualAnalysisParameters.INVERSE_VALUES).contains(inverse))
			{
				if (analysis != VisualAnalysisParameters.ANALYSIS_MANUAL)
					System.out.println("You can use 'inverse' parameter only with 'analysis' set to manual. Currently 'analysis' is set to " + analysis);
				else
				{
					if (source == VisualAnalysisParameters.SOURCE_NATIVE)
						System.out.println("The inverse parameter is irrelevant when using 'native' screen source.");	
					params.put("inverse", inverse);
				}				
			}
			else
				System.out.println("WARNING! Unsupported inverse type '" + match + "'. Possible values are: " + String.join(", ", VisualAnalysisParameters.INVERSE_VALUES));
		}
		
		if (imageBoundNeedleBound != 100)
			params.put("imageBound.needleBound", imageBoundNeedleBound);

		if (imageBoundHeystackArea != 786432)
		{
			if (imageBoundHeystackArea < 196608)
				System.out.println("WARNING! imageBound.haystackBound value is less than the minimum possible 196608");
				
			params.put("imageBound.haystackBound", imageBoundHeystackArea);
		}
		
		if (policy != null)
		{
			if(Arrays.asList(VisualAnalysisParameters.POLICIES).contains(policy))
			{ 
				if (source == VisualAnalysisParameters.SOURCE_NATIVE)
					System.out.println("The policy parameter is irrelevant when using 'native' screen source.");
				params.put("policy", policy);
			}			
			else
			{
				System.out.println("WARNING! Unsupported policy type '" + policy + "'. Possible values are: " + String.join(", ", VisualAnalysisParameters.POLICIES));			
			}

		}
		
		if (operation != null)
		{
			if (Arrays.asList(VisualAnalysisParameters.OPERATIONS).contains(operation))
				params.put("operation", operation);
			else
				System.out.println("WARNING! Unsupported operation type '" + operation + "'. Possible values are: " + String.join(", ", VisualAnalysisParameters.OPERATIONS));
		}
				
		if (repeat != 1)
		{
			if (operation != "Single tap" && operation != "Double tap")
				System.out.println("WARNING! 'repeat' parameter is relevant for Single tap and Double tap actions only ");				
			params.put("repeat", repeat);			
		}
		
		if (relationDirection != null)
		{
			if (Arrays.asList(VisualAnalysisParameters.RELATION_DIRECTIONS).contains(relationDirection))
				params.put("relation.direction",  relationDirection);
			else
				System.out.println("WARNING! Unsupported operation type '" + relationDirection + "'. Possible values are: " + String.join(", ", VisualAnalysisParameters.RELATION_DIRECTIONS));		
		}

		if (relationInline != null)
		{
			if (Arrays.asList(VisualAnalysisParameters.RELATION_INLINE_VALUES).contains(relationInline))
				params.put("relation.inline",  relationInline);					
			else
				System.out.println("WARNING! Unsupported relation.inline value '" + relationInline + "'. Possible values are: " + String.join(", ", VisualAnalysisParameters.RELATION_INLINE_VALUES));
		}
		
		if (reportResolution != null)
		{
			if (Arrays.asList(VisualAnalysisParameters.REPORT_RESOLUTIONS).contains(reportResolution))
				params.put("report.resolution", reportResolution);
			else
				System.out.println("WARNING! Unsupported report.resolution value '" + reportResolution + "'. Possible values are: " + String.join(", ", VisualAnalysisParameters.REPORT_RESOLUTIONS));
		}

		if (imageTop != null)
			params.put("image.top", imageTop);
		
		if (imageHeight != null)
			params.put("image.height", imageHeight);

		if (imageLeft != null)
			params.put("image.left", imageLeft);

		if (imageWidth != null)
			params.put("image.width", imageWidth);
		
		if (matchGeneric != null)
		{
			if (Arrays.asList(VisualAnalysisParameters.MATCH_GENERIC_VALUES).contains(matchGeneric))
				params.put("matchGeneric", matchGeneric);
			else
				System.out.println("WARNING! Unsupported matchGeneric value '" + matchGeneric + "'. Possible values are: " + String.join(", ", VisualAnalysisParameters.MATCH_GENERIC_VALUES));
			
		}
		
		if (grid != null)
			params.put("grid", grid);
		
		if (ocr != null)
			params.put("ocr", ocr);
		
		if (profile != null) {
			if (Arrays.asList(VisualAnalysisParameters.PROFILES).contains(profile))
				params.put("profile", profile);
			else
				System.out.println("WARNING! Unsupported profile value '" + profile + "'. Possible values are: " + String.join(", ", VisualAnalysisParameters.PROFILES));
		}
		
		if (shift != null)
		{
			if (Arrays.asList(VisualAnalysisParameters.SHIFT_VALUES).contains(shift))
				params.put("shift", shift);
			else
				System.out.println("WARNING! Unsupported shift value '" + shift + "'. Possible values are: " + String.join(", ", VisualAnalysisParameters.SHIFT_VALUES));	
		}
		
		if (offset != null)
			params.put("offset", offset);
		
		if (xAlignment != null)
			if (Arrays.asList(VisualAnalysisParameters.ALIGNMENTS).contains(xAlignment))
				params.put("x.alignment", xAlignment);
			else
				System.out.println("WARNING! Unsupported shift value '" + xAlignment + "'. Possible values are: " + String.join(", ", VisualAnalysisParameters.ALIGNMENTS));		
		
		if (yAlignment != null)
			if (Arrays.asList(VisualAnalysisParameters.ALIGNMENTS).contains(xAlignment))
				params.put("y.alignment", yAlignment);
			else
				System.out.println("WARNING! Unsupported yAlignment value '" + yAlignment + "'. Possible values are: " + String.join(", ", VisualAnalysisParameters.ALIGNMENTS));
		
		if (xWidth != null)
			params.put("x.width", xWidth);
		
		if (yHeight != null)
			params.put("y.height", yHeight);
		
		if (context != null)
			if (Arrays.asList(VisualAnalysisParameters.CONTEXTS).contains(context))
				params.put("context", context);
			else
				System.out.println("WARNING! Unsupported context value '" + context + "'. Possible values are: " + String.join(", ", VisualAnalysisParameters.CONTEXTS));

		if (scope != null)
			if(Arrays.asList(VisualAnalysisParameters.SCOPES).contains(scope))
				params.put("scope", this.scope);
			else
				System.out.println("WARNING! Unsupported scope value: " + scope + ". Possible values are: " + String.join(", ", VisualAnalysisParameters.SCOPES));
		
		
		if (caps != null)
			if (Arrays.asList(VisualAnalysisParameters.CAPS_VALUES).contains(caps))
				params.put("caps", caps);
			else
				System.out.println("WARNING! Unsupported caps value: " + caps + "Possible values are: " + String.join(", ", VisualAnalysisParameters.CAPS_VALUES));		
		

		if (labelDirection != null)
			if (Arrays.asList(VisualAnalysisParameters.LABEL_DIRECTIONS).contains(labelDirection))
				params.put("label.direction",  labelDirection);
			else
				System.out.println("WARNING! Unsupported label.direction value: " + labelDirection + ". Possible values are: " + String.join(", ", VisualAnalysisParameters.LABEL_DIRECTIONS));
		
		if (labelOffset != null)
			params.put("label.offset",  labelOffset);
		
		if (timeout > -1)
			params.put("timeout", timeout);
		
		if (lines > -1)
			params.put("lines", lines);

		if (interval > 0)
			params.put("interval", interval);
		
		if (measurement != null)
			if(Arrays.asList(VisualAnalysisParameters.MEASUREMENTS).contains(measurement))
				params.put("measurement",  measurement);
			else
				System.out.println("WARNING! Unsupported measurement value: " + measurement + ". Possible values are: " + String.join(", ", VisualAnalysisParameters.MEASUREMENTS));
		
		
	}
}