package g.perfecto.utilities;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.remote.RemoteWebDriver;

public class VisualAnalysis {

  private RemoteWebDriver driver;
  private Map<String, Object> params = new HashMap<>();

  public String label;
  public String text = "";
  public Integer treshold;
  public Integer index;
  public Boolean ignoreCase;
  public Boolean scroll;
  public Integer maxScroll;
  public String next;
  public static final String NEXT_SWIPE_UP = "SWIPE_UP";
  public static final String NEXT_SWIPE_DOWN = "SWIPE_DOWN";
  public static final String NEXT_SWIPE_RIGHT = "SWIPE_RIGHT";
  public static final String NEXT_SWIPE_LEFT = "SWIPE_LEFT";
  public String backlight;
  public String source;
  public static final String SOURCE_PRIMARY = "primary";
  public static final String SOURCE_NATIVE = "native";
  public static final String SOURCE_CAMERA = "camera";
  public static final String SOURCE_AUTO = "auto";
  public static final String SOURCE_SECONDARY = "secondary";
  public static final String SOURCE_DEFAULT = SOURCE_AUTO;
  public Boolean words;
  public Boolean ignoreSpace;
  public Boolean ignorePunct;
  public String analysis;
  public static final String ANALYSIS_FULL = "full";
  public static final String ANALYSIS_AUTOMATIC = "automatic";
  public static final String ANALYSIS_MANUAL = "manual";
  public String language;
  public static final String LANGUAGE_ENGLISH = "English";
  public static final String LANGUAGE_DUTCH = "Dutch";
  public static final String LANGUAGE_FRENCH = "French";
  public static final String LANGUAGE_GERMAN = "German";
  public static final String LANGUAGE_ITALIAN = "Italian";
  public static final String LANGUAGE_SPANISH = "Spanish";
  public static final String LANGUAGE_PORTUGESE = "PortugeseStandard";
  public static final String LANGUAGE_RUSSIAN = "Russian";
  public static final String LANGUAGE_HEBREW = "Hebrew";
  public String scope;
  public static final String SCOPE_SENTENCE = "sentence";
  public static final String SCOPE_CONTACT = "contact";
  public static final String SCOPE_URL = "url";
  public static final String SCOPE_NUMBER = "number";
  public String caps;
  public String labelDirection;
  public static final String LABEL_DIRECTION_INSIDE = "inside";
  public static final String LABEL_DIRECTION_ABOVE = "above";
  public static final String LABEL_DIRECTION_BELOW = "below";
  public static final String LABEL_DIRECTION_LEFT = "left";
  public static final String LABEL_DIRECTION_RIGHT = "right";
  public static final String LABEL_DIRECTION_LEFTMOST = "leftmost";
  public static final String LABEL_DIRECTION_RIGHTMOST = "rightmost";
  public String labelOffset;
  public String screenTop;
  public String screenHeight;
  public String screenLeft;
  public String screenWidth;
  public String match;
  public String textMatch;
  public static final String TEXT_MATCH_CONTAIN = "contain";
  public static final String TEXT_MATCH_EQUAL = "equal";
  public static final String TEXT_MATCH_STARTWITH = "startwith";
  public static final String TEXT_MATCH_ENDWITH = "endwith";
  public static final String TEXT_MATCH_FIRST = "first";
  public static final String TEXT_MATCH_LAST = "last";
  public static final String TEXT_MATCH_INDEX = "index";
  public String operation;
  public static final String OPERATION_SINGLE = "single";
  public static final String OPERATION_DOUBLE = "double";
  public static final String OPERATION_LONG = "long";
  public static final String OPERATION_ROLL = "roll";
  public static final String OPERATION_NONE = "none";
  public Integer repeat;
  public String inverse;
  public static final String INVERSE_ANY = "any";
  public static final String INVERSE_YES = "yes";
  public static final String INVERSE_NO = "no";
  public String policy;
  public static final String POLICY_AUTO = "auto";
  public static final String POLICY_ACCURACY = "accuracy";
  public static final String POLICY_PERFORMANCE = "performance";
  public String relationDirection;
  public static final String RELATION_DIRECTION_LEFT = "left";
  public static final String RELATION_DIRECTION_RIGHT = "right";
  public static final String RELATION_DIRECTION_ABOVE = "above";
  public static final String RELATION_DIRECTION_BELOW = "below";
  public static final String RELATION_DIRECTION_LEFTABOVE = "left-above";
  public static final String RELATION_DIRECTION_LEFTBELOW = "left-below";
  public static final String RELATION_DIRECTION_RIGHTABOVE = "right-above";
  public static final String RELATION_DIRECTION_RIGHTBELOW = "right-below";
  public static final String RELATION_DIRECTION_INSIDE = "inside";
  public String relationInline;
  public static final String RELATION_INLINE_VALUE_HORIZONTAL = "Horizontal";
  public static final String RELATION_INLINE_VALUE_VERTICAL = "Vertical";
  public String reportResolution;
  public static final String REPORT_RESOLUTION_HIGH = "High";
  public static final String REPORT_RESOLUTION_MEDIUM = "Medium";
  public static final String REPORT_RESOLUTION_LOW = "Low";
  public String grid;
  public String ocr;
  public String imageTop;
  public String imageHeight;
  public String imageLeft;
  public String imageWidth;
  public Integer imageBoundNeedleBound;
  public Integer imageBoundHeystackArea;
  public String matchGeneric;
  public static final String MATCH_GENERIC_TM_SQDIFF_NORMED = "TM_SQDIFF_NORMED";
  public static final String MATCH_GENERIC_TM_CCORR_NORMED = "TM_CCORR_NORMED";
  public String profile;
  public static final String PROFILE_DEFAULT = "default";
  public static final String PROFILE_DOCUMENTCONVERSION_ACCURACY = "DocumentConversion_Accuracy";
  public static final String PROFILE_DOCUMENTCONVERSION_SPEED = "DocumentConversion_Speed";
  public static final String PROFILE_DOCUMENTARCHIVING_ACCURACY = "DocumentArchiving_Accuracy";
  public static final String PROFILE_DOCUMENTARCHIVING_SPEED= "DocumentArchiving_Speed";
  public static final String PROFILE_BOOKARCHIVING_ACCURACY = "BookArchiving_Accuracy";
  public static final String PROFILE_BOOKARCHIVING_SPEED = "BookArchiving_Speed";
  public static final String PROFILE_TEXTEXTRACTION_ACCURACY = "TextExtraction_Accuracy";
  public static final String PROFILE_TEXTEXTRACTION_SPEED = "TextExtraction_Speed";
  public static final String PROFILE_FIELDLEVELRECOGNITION = "FieldLevelRecognition";
  public static final String PROFILE_BARCODERECOGNITION = "BarcodeRecognition";
  public String shift;
  public static final String SHIFT_NONE = "none";
  public static final String SHIFT_ABOVE = "above";
  public static final String SHIFT_BELOW = "below";
  public static final String SHIFT_LEFT = "left";
  public static final String SHIFT_RIGHT = "right";
  public static final String SHIFT_LEFMOST = "leftmost";
  public static final String SHIFT_RIGHTMOST = "rightmost";
  public String offset;
  public String content;
  public String xAlignment;
  public String yAlignment;
  public static final String ALIGNMENT_NONE = "none";
  public static final String ALIGNMENT_LEFT = "left";
  public static final String ALIGNMENT_CENTER = "center";
  public static final String ALIGNMENT_RIGHT = "right";
  public String xWidth;
  public String yHeight;
  public String context;
  public static final String CONTEXT_ALL = "all";
  public static final String CONTEXT_BODY = "body";
  public Integer lines;
  public Integer timeout;
  public Integer interval;
  public String measurement;
  public static final String MEASUREMENT_ROUGH = "rough";
  public static final String MEASUREMENT_ACCURATE = "accurate";
  public String key;

  private final static String COMMAND_EDIT_IMAGE_GET = "mobile:edit-image:get";
  private final static String COMMAND_EDIT_IMAGE_SET = "mobile:edit-image:set";
  private final static String COMMAND_EDIT_TEXT_GET = "mobile:edit-text:get";
  private final static String COMMAND_EDIT_TEXT_SET = "mobile:edit-text:set";
  private final static String COMMAND_IMAGE_BUTTON_CLICK = "mobile:button-image:click";
  private final static String COMMAND_BUTTON_TEXT_CLICK = "mobile:button-text:click";
  private final static String COMMAND_IMAGE_SELECT = "mobile:image:select";
  private final static String COMMAND_TEXT_SELECT = "mobile:text:select";
  private final static String COMMAND_CHECKPOINT_IMAGE = "mobile:checkpoint:image";
  private final static String COMMAND_CHECKPOINT_TEXT = "mobile:checkpoint:text";
  private final static String COMMAND_IMAGE_FIND = "mobile:image:find";
  private final static String COMMAND_TEXT_FIND = "mobile:text:find";
  private final static String COMMAND_SCREENTEXT = "mobile:screen:text";

  public VisualAnalysis(RemoteWebDriver driver)
  {
    Logger.LogDebug("Creating VisualAnalysis object");
    this.driver = driver;
  }

  public void ResetParams()
  {
    params.clear();
    label = null;
    text = "";
    treshold = null;
    index = null;
    ignoreCase = null;
    scroll = null;
    maxScroll = null;
    next = null;
    backlight = null;
    source = null;
    words = null;
    ignoreSpace = null;
    ignorePunct = null;
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
    repeat = null;
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
    imageBoundNeedleBound = null;
    imageBoundHeystackArea = null;
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
    lines = null;
    timeout = null;
    interval = null;
    measurement = null;
    key = null;
  }

  /**
   * Identifies an edit field, based on an image label, and retrieves its text value. The value is returned as a string.
   * @return
   */
  public String editImageGet()
  {
    params.clear();
    if (!addMandatoryParameter("label", label))
      return null;
    setAdditionalParams();

    return Helper.executeMethodString(driver, COMMAND_EDIT_IMAGE_GET, params);	
  }

  /**
   * Identifies an edit field, based on an image label, and retrieves its text value. The value is returned as a string.
   * @param label
   * @return
   */
  public String editImageGet(String label)
  {
    this.label = label;
    return editImageGet();
  }

  /**
   * Identifies an edit field, based on an image label, and retrieves its text value. The value is returned as a string.
   * @param label
   * @param lines
   * @return
   */
  public String editImageGet(String label, int lines)
  {
    this.lines = lines;
    return editImageGet(label);
  }

  /**
   * Identifies an edit field, based on an image label, and inserts the specified text in the value parameter into the field.
   * @return
   */
  public Boolean editImageSet()
  {
    params.clear();
    if (!addMandatoryParameter("label", label))
      return false;
    params.put("text",  text);
    setAdditionalParams();

    return Helper.executeMethod(driver, COMMAND_EDIT_IMAGE_SET, params);	

  }

  /**
   * Identifies an edit field, based on an image label, and inserts the specified text in the value parameter into the field. 
   * @param label
   * @param text
   * @return
   */
  public Boolean editImageSet(String label, String text)
  {
    this.label = label;
    this.text = text;
    return editImageSet();
  }

  /**
   * Identifies an edit field, based on an image label, and inserts the specified text in the value parameter into the field. 
   * @param label
   * @param text
   * @param caps
   * @return
   */
  public Boolean editImageSet(String label, String text, String caps)
  {
    this.caps = caps;
    return editImageSet(label, text);
  }

  /**
   * Identifies an edit field, based on a text label, and retrieves its value. The value is returned as a string.
   * @return
   */
  public String editTextGet()
  {
    params.clear();
    if (!addMandatoryParameter("label", label))
      return null;
    setAdditionalParams();

    return Helper.executeMethodString(driver, COMMAND_EDIT_TEXT_GET, params);	
  }

  /**
   * Identifies an edit field, based on a text label, and retrieves its value. The value is returned as a string.
   * @param label
   * @return
   */
  public String editTextGet(String label)
  {
    this.label = label;
    return editTextGet();
  }

  /**
   * Identifies an edit field, based on a text label, and retrieves its value. The value is returned as a string.
   * @param label
   * @param lines
   * @return
   */
  public String editTextGet(String label, int lines)
  {
    this.lines = lines;
    return editTextGet(label);
  }

  /**
   * Identifies an edit field, based on a text label, and inserts the specified text in the value parameter into the field. 
   * The field is in relation to the found label and is defined by the label position and offset parameters.
   * @return
   */
  public Boolean editTextSet()
  {
    params.clear();
    if (!addMandatoryParameter("label", label))
      return false;	
    params.put("text", this.text);

    setAdditionalParams();

    return Helper.executeMethod(driver, COMMAND_EDIT_TEXT_SET, params);	
  }

  /**
   * Identifies an edit field, based on a text label, and inserts the specified text in the value parameter into the field. 
   * The field is in relation to the found label and is defined by the label position and offset parameters.
   * @param label
   * @param text
   * @return
   */
  public Boolean editTextSet(String label, String text)
  {
    this.label = label;
    this.text = text;
    return editTextSet();
  }

  /**
   * Identifies an edit field, based on a text label, and inserts the specified text in the value parameter into the field. 
   * The field is in relation to the found label and is defined by the label position and offset parameters.
   * @param label
   * @param text
   * @param scope
   * @return
   */
  public Boolean editTextSet(String label, String text, String scope)
  {
    this.scope = scope;
    return editTextSet(label, text);
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
  public Boolean editTextSet(String label, String text, String scope, String caps)
  {
    this.caps = caps;
    return editTextSet(label, text, scope);
  }

  /**
   * Identifies a button, based on an image label, and clicks on it.
   * @return
   */
  public Boolean imageButtonClick()
  {
    params.clear();
    if (!addMandatoryParameter("label", label))
      return false;
    setAdditionalParams();

    return Helper.executeMethod(driver, COMMAND_IMAGE_BUTTON_CLICK, params);	
  }

  /**
   * Identifies a button, based on a text label, and clicks on it.
   * @return
   */
  public Boolean textButtonClick()
  {
    params.clear();
    if (!addMandatoryParameter("label", label))
      return false;
    setAdditionalParams();

    return Helper.executeMethod(driver, COMMAND_BUTTON_TEXT_CLICK, params);	
  }

  /**
   * Identifies a button, based on a text label, and clicks on it.
   * @param label
   * @return
   */
  public Boolean textButtonClick(String label)
  {
    this.label = label;
    return textButtonClick();
  }

  /**
   * Identifies a button, based on a text label, and clicks on it.
   * @param label
   * @param treshold
   * @return
   */
  public Boolean textButtonClick(String label, int treshold)
  {
    this.treshold = treshold;
    return textButtonClick(label);
  }

  /**
   * Identifies a button, based on a text label, and clicks on it.
   * @param label
   * @param scroll
   * @return
   */
  public Boolean textButtonClick(String label, Boolean scroll)
  {
    this.scroll = scroll;
    return textButtonClick(label);
  }

  /**
   * Finds an image (needle) on the device screen (haystack) and clicks on it.
   * @return
   */
  public Boolean selectImage()
  {
    params.clear();
    if (!addMandatoryParameter("content", content))
      return false;
    setAdditionalParams();

    return Helper.executeMethod(driver, COMMAND_IMAGE_SELECT, params);	
  }

  /**
   * Finds an image (needle) on the device screen (haystack) and clicks on it.
   * @param content
   * @return
   */
  public Boolean selectImage(String content)
  {
    this.content = content;
    return selectImage();
  }

  /**
   * Finds text (needle) on the device screen (haystack) and clicks on it.
   * @return true if the function is executed successfully. 
   */
  public Boolean selectText()
  {
    params.clear();
    if (!addMandatoryParameter("content", content))
      return false;
    setAdditionalParams();

    return Helper.executeMethod(driver, COMMAND_TEXT_SELECT, params);	
  }

  /**
   * Finds text (needle) on the device screen (haystack) and clicks on it.
   * @param content - the text on the screen to click on
   * @return true if the function is executed successfully. 
   */
  public Boolean selectText(String content)
  {
    this.content = content;
    return selectText();
  }


  public Boolean selectText(String content, String context)
  {
    this.context = context;
    this.content = content;
    return selectText();
  }

  /**
   * Verifies that the given image appears on the device screen. In the case of failure, the script will be marked as failed.
   * @return
   */
  public Boolean imageCheckPoint()
  {	
    params.clear();
    if (!addMandatoryParameter("content", content))
      return false;		
    setAdditionalParams();

    return Helper.executeMethod(driver, COMMAND_CHECKPOINT_IMAGE, params);
  }

  /**
   * Verifies that the given image appears on the device screen. In the case of failure, the script will be marked as failed.
   * @param content
   * @return
   */
  public Boolean imageCheckPoint(String content)
  {
    this.content = content;
    return imageCheckPoint();
  }

  /**
   * Verifies that the given text appears on the device screen. In the case of failure, the script will be marked as failed.
   * @return
   */
  public Boolean textCheckPoint() 
  {
    params.clear();
    if (!addMandatoryParameter("content", content))
      return false;
    setAdditionalParams();

    return Helper.executeMethod(driver, COMMAND_CHECKPOINT_TEXT, params);
  }

  /**
   * Verifies that the given text appears on the device screen. In the case of failure, the script will be marked as failed.
   * @param content
   * @return
   */
  public Boolean textCheckPoint(String content)
  {
    this.content = content;
    return textCheckPoint();
  }

  /**
   * Verifies that the given text appears on the device screen. In the case of failure, the script will be marked as failed.
   * @param content
   * @param match
   * @return
   */
  public Boolean textCheckPoint(String content, String match)
  {
    this.content = content;
    this.match = match;
    return textCheckPoint();
  }

  /**
   * Finds an image (needle) on the device screen (haystack), and stores the coordinates for future commands.
   * @return
   */
  public Boolean findImage()
  {
    return find(COMMAND_IMAGE_FIND);	
  }

  /**
   * Finds an image (needle) on the device screen (haystack), and stores the coordinates for future commands.
   * @param content
   * @return
   */
  public Boolean findImage(String content)
  {
    this.content = content;
    return findImage();		
  }

  /**
   * Finds an image (needle) on the device screen (haystack), and stores the coordinates for future commands.
   * @param content
   * @param context
   * @return
   */
  public Boolean findImage(String content, String context)
  {
    this.context = context;
    this.content = content;
    return findImage();		
  }

  /**
   * Finds text (needle) on the device screen (haystack), and stores the coordinates for future commands.
   * @return
   */
  public Boolean findText()
  {
    return find(COMMAND_TEXT_FIND);
  }

  private Boolean find(String methodName)
  {
    params.clear();
    if (!addMandatoryParameter("content", content))
      return false;
    setAdditionalParams();

    return Helper.executeMethod(driver, methodName, params);
  }

  /**
   * Returns the text that appears on the screen of the device. It does not select the text.
   * @return
   */
  public String screenText()
  {
    params.clear();
    setAdditionalParams();		
    return Helper.executeMethodString(driver, COMMAND_SCREENTEXT, params);
  }

  /**
   * Returns the text that appears on the screen of the device. It does not select the text.
   * @param key
   * @return
   */
  public String screenText(String key)
  {
    this.key = key;
    return screenText();
  }


  /**
   * Returns the text that appears on the screen of the device. It does not select the text.
   * @param key
   * @param context
   * @return
   */
  public String screenText(String key, String context)
  {
    this.context = context;
    return screenText(key);
  }

  private Boolean addMandatoryParameter(String parameterName, String parameterValue)
  {
    if (parameterValue == null || parameterValue.trim().isEmpty())
    {
      Logger.LogError("Missing mandatory parameter " + parameterName);
      return false;
    }
    params.put(parameterName, parameterValue);
    return true;
  }

  private void setAdditionalParams() {

    if (treshold != null)
      params.put("threshold", treshold);

    if (ignoreCase != null)
      params.put("ignorecase", ignoreCase ? "nocase" : "case");

    if (index != null)
      params.put("index", index);

    if (scroll != null)
      params.put("scrolling", scroll ? "scroll" : "noscroll");

    if (maxScroll != null)
      params.put("maxscroll", maxScroll);

    if (words != null)
      params.put("words", words ? "words" : "substring");

    if (ignoreSpace != null)
      params.put("ignorespace", ignoreSpace ? "nospace" : "space");

    if (ignorePunct != null)
      params.put("ignorepunct", ignorePunct ? "nopunct" : "punct");

    if (source != null) 
      params.put("source", source);
    
    if (analysis != null)
      params.put("analysis", analysis);

    if (language != null)
      params.put("language", language);

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
      params.put("match", textMatch);
     
    if (inverse != null)	
      params.put("inverse", inverse);

    if (imageBoundNeedleBound != null)
      params.put("imageBound.needleBound", imageBoundNeedleBound);

    if (imageBoundHeystackArea != null)
      params.put("imageBound.haystackBound", imageBoundHeystackArea);

    if (policy != null)
      params.put("policy", policy);

    if (operation != null)
      params.put("operation", operation);

    if (repeat != null)		
      params.put("repeat", repeat);			

    if (relationDirection != null)	
      params.put("relation.direction",  relationDirection);

    if (relationInline != null)
      params.put("relation.inline",  relationInline);

    if (reportResolution != null)
      params.put("report.resolution", reportResolution);

    if (imageTop != null)
      params.put("image.top", imageTop);

    if (imageHeight != null)
      params.put("image.height", imageHeight);

    if (imageLeft != null)
      params.put("image.left", imageLeft);

    if (imageWidth != null)
      params.put("image.width", imageWidth);

    if (matchGeneric != null)
      params.put("matchGeneric", matchGeneric);

    if (grid != null)
      params.put("grid", grid);

    if (ocr != null)
      params.put("ocr", ocr);

    if (profile != null) 
      params.put("profile", profile);
    
    if (shift != null)
      params.put("shift", shift);

    if (offset != null)
      params.put("offset", offset);

    if (xAlignment != null)
      params.put("x.alignment", xAlignment);
    
    if (yAlignment != null)
      params.put("y.alignment", yAlignment);
    
    if (xWidth != null)
      params.put("x.width", xWidth);

    if (yHeight != null)
      params.put("y.height", yHeight);

    if (context != null)
      params.put("context", context);
    
    if (scope != null)
      params.put("scope", this.scope);
    
    if (caps != null)
      params.put("caps", caps);
    
    if (labelDirection != null)
      params.put("label.direction",  labelDirection);
    
    if (labelOffset != null)
      params.put("label.offset",  labelOffset);

    if (timeout != null)
      params.put("timeout", timeout);

    if (lines != null)
      params.put("lines", lines);

    if (interval != null)
      params.put("interval", interval);

    if (measurement != null)
      params.put("measurement",  measurement);
    }
  }
}