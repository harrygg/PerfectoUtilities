package g.perfecto.utilities;

public class VisualAnalysisParameters
{
  public static final int TRESHOLD_DEFAULT = 80;
  public static final String SOURCE_PRIMARY = "primary";
  public static final String SOURCE_NATIVE = "native";
  public static final String SOURCE_CAMERA = "camera";
  public static final String SOURCE_AUTO = "auto";
  public static final String SOURCE_SECONDARY = "secondary";
  public static final String SOURCE_DEFAULT = SOURCE_AUTO;
  public static String[] SOURCES = new String[] { SOURCE_PRIMARY, SOURCE_NATIVE, SOURCE_CAMERA, SOURCE_AUTO };

  public static final String LANGUAGE_ENGLISH = "English";
  public static final String LANGUAGE_DUTCH = "Dutch";
  public static final String LANGUAGE_FRENCH = "French";
  public static final String LANGUAGE_GERMAN = "German";
  public static final String LANGUAGE_ITALIAN = "Italian";
  public static final String LANGUAGE_SPANISH = "Spanish";
  public static final String LANGUAGE_PORTUGESE = "PortugeseStandard";
  public static final String LANGUAGE_RUSSIAN = "Russian";
  public static final String LANGUAGE_HEBREW = "Hebrew";
  public static String[] LANGUAGES = new String[] { LANGUAGE_ENGLISH, LANGUAGE_DUTCH, LANGUAGE_FRENCH, LANGUAGE_GERMAN, LANGUAGE_ITALIAN, LANGUAGE_SPANISH, LANGUAGE_PORTUGESE, LANGUAGE_RUSSIAN, LANGUAGE_HEBREW };

  public static final String SCOPE_SENTENCE = "sentence";
  public static final String SCOPE_CONTACT = "contact";
  public static final String SCOPE_URL = "url";
  public static final String SCOPE_NUMBER = "number";
  public static String[] SCOPES = new String[] { SCOPE_SENTENCE, SCOPE_CONTACT, SCOPE_URL, SCOPE_NUMBER };

  public static final String TEXT_MATCH_CONTAIN = "contain";
  public static final String TEXT_MATCH_EQUAL = "equal";
  public static final String TEXT_MATCH_STARTWITH = "startwith";
  public static final String TEXT_MATCH_ENDWITH = "endwith";
  public static final String TEXT_MATCH_FIRST = "first";
  public static final String TEXT_MATCH_LAST = "last";
  public static final String TEXT_MATCH_INDEX = "index";
  public static final String[] TEXT_MATCHES = new String[] { TEXT_MATCH_CONTAIN, TEXT_MATCH_EQUAL, TEXT_MATCH_STARTWITH, TEXT_MATCH_ENDWITH, TEXT_MATCH_FIRST, TEXT_MATCH_LAST, TEXT_MATCH_INDEX };

  public static final String ANALYSIS_FULL = "full";
  public static final String ANALYSIS_AUTOMATIC = "automatic";
  public static final String ANALYSIS_MANUAL = "manual";
  public static String[] ANALYSIS_VALUES = new String[] { ANALYSIS_FULL, ANALYSIS_AUTOMATIC, ANALYSIS_MANUAL };

  public static final String CAPS_Aa = "Aa";
  public static final String CAPS_A = "A";
  public static final String CAPS_a = "a";
  public static String[] CAPS_VALUES = new String[] {CAPS_Aa, CAPS_A, CAPS_a};

  public static final String LABEL_DIRECTION_INSIDE = "inside";
  public static final String LABEL_DIRECTION_ABOVE = "above";
  public static final String LABEL_DIRECTION_BELOW = "below";
  public static final String LABEL_DIRECTION_LEFT = "left";
  public static final String LABEL_DIRECTION_RIGHT = "right";
  public static final String LABEL_DIRECTION_LEFTMOST = "leftmost";
  public static final String LABEL_DIRECTION_RIGHTMOST = "rightmost";
  public static final String[] LABEL_DIRECTIONS = new String[] { LABEL_DIRECTION_INSIDE, LABEL_DIRECTION_ABOVE, LABEL_DIRECTION_BELOW, LABEL_DIRECTION_LEFT, LABEL_DIRECTION_RIGHT, LABEL_DIRECTION_LEFTMOST, LABEL_DIRECTION_RIGHTMOST };

  public static final String OPERATION_SINGLE = "single";
  public static final String OPERATION_DOUBLE = "double";
  public static final String OPERATION_LONG = "long";
  public static final String OPERATION_ROLL = "roll";
  public static final String OPERATION_NONE = "none";
  public static final String[] OPERATIONS = new String[] {OPERATION_SINGLE, OPERATION_DOUBLE, OPERATION_LONG, OPERATION_ROLL, OPERATION_NONE};

  public static final String INVERSE_ANY = "any";
  public static final String INVERSE_YES = "yes";
  public static final String INVERSE_NO = "no";
  public static final String[] INVERSE_VALUES = new String[] {INVERSE_ANY, INVERSE_YES, INVERSE_NO};

  public static final String POLICY_AUTO = "auto";
  public static final String POLICY_ACCURACY = "accuracy";
  public static final String POLICY_PERFORMANCE = "performance";
  public static final String[] POLICIES = new String[] { POLICY_AUTO, POLICY_ACCURACY, POLICY_PERFORMANCE };

  public static final String RELATION_DIRECTION_LEFT = "left";
  public static final String RELATION_DIRECTION_RIGHT = "right";
  public static final String RELATION_DIRECTION_ABOVE = "above";
  public static final String RELATION_DIRECTION_BELOW = "below";
  public static final String RELATION_DIRECTION_LEFTABOVE = "left-above";
  public static final String RELATION_DIRECTION_LEFTBELOW = "left-below";
  public static final String RELATION_DIRECTION_RIGHTABOVE = "right-above";
  public static final String RELATION_DIRECTION_RIGHTBELOW = "right-below";
  public static final String RELATION_DIRECTION_INSIDE = "inside";
  public static final String[] RELATION_DIRECTIONS = new String[] { RELATION_DIRECTION_LEFT, RELATION_DIRECTION_RIGHT, RELATION_DIRECTION_ABOVE, RELATION_DIRECTION_BELOW, RELATION_DIRECTION_LEFTABOVE, RELATION_DIRECTION_LEFTBELOW, RELATION_DIRECTION_RIGHTABOVE, RELATION_DIRECTION_RIGHTBELOW, RELATION_DIRECTION_INSIDE};


  public static final String RELATION_INLINE_VALUE_HORIZONTAL = "Horizontal";
  public static final String RELATION_INLINE_VALUE_VERTICAL = "Vertical";
  public static final String[] RELATION_INLINE_VALUES = new String[] { RELATION_INLINE_VALUE_HORIZONTAL, RELATION_INLINE_VALUE_VERTICAL };

  public static final String REPORT_RESOLUTION_HIGH = "High";
  public static final String REPORT_RESOLUTION_MEDIUM = "Medium";
  public static final String REPORT_RESOLUTION_LOW = "Low";
  public static final String[] REPORT_RESOLUTIONS = new String[] {REPORT_RESOLUTION_HIGH, REPORT_RESOLUTION_MEDIUM, REPORT_RESOLUTION_LOW};

  public static final String MATCH_GENERIC_TM_SQDIFF_NORMED = "TM_SQDIFF_NORMED";
  public static final String MATCH_GENERIC_TM_CCORR_NORMED = "TM_CCORR_NORMED";
  public static final String[] MATCH_GENERIC_VALUES = new String[] { MATCH_GENERIC_TM_SQDIFF_NORMED, MATCH_GENERIC_TM_CCORR_NORMED };

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
  public static final String[] PROFILES = new String[] { PROFILE_DOCUMENTCONVERSION_ACCURACY, PROFILE_DOCUMENTCONVERSION_SPEED, PROFILE_DOCUMENTARCHIVING_ACCURACY, PROFILE_DOCUMENTARCHIVING_SPEED, PROFILE_BOOKARCHIVING_ACCURACY, PROFILE_BOOKARCHIVING_SPEED, PROFILE_TEXTEXTRACTION_ACCURACY, PROFILE_TEXTEXTRACTION_SPEED, PROFILE_FIELDLEVELRECOGNITION, PROFILE_BARCODERECOGNITION };

  public static final String SHIFT_NONE = "none";
  public static final String SHIFT_ABOVE = "above";
  public static final String SHIFT_BELOW = "below";
  public static final String SHIFT_LEFT = "left";
  public static final String SHIFT_RIGHT = "right";
  public static final String SHIFT_LEFMOST = "leftmost";
  public static final String SHIFT_RIGHTMOST = "rightmost";
  public static final String[] SHIFT_VALUES = new String[] { SHIFT_NONE, SHIFT_ABOVE, SHIFT_BELOW, SHIFT_LEFT, SHIFT_RIGHT, SHIFT_LEFMOST, SHIFT_RIGHTMOST };

  public static final String ALIGNMENT_NONE = "none";
  public static final String ALIGNMENT_LEFT = "left";
  public static final String ALIGNMENT_CENTER = "center";
  public static final String ALIGNMENT_RIGHT = "right";
  public static final String[] ALIGNMENTS = new String[] { ALIGNMENT_NONE, ALIGNMENT_LEFT, ALIGNMENT_CENTER, ALIGNMENT_RIGHT };

  public static final String CONTEXT_ALL = "all";
  public static final String CONTEXT_BODY = "body";
  public static final String[] CONTEXTS = new String[] { CONTEXT_ALL, CONTEXT_BODY };

  public static final String MEASUREMENT_ROUGH = "rough";
  public static final String MEASUREMENT_ACCURATE = "accurate";
  public static final String[] MEASUREMENTS = new String[] { MEASUREMENT_ROUGH, MEASUREMENT_ACCURATE };

  public static final String NEXT_SWIPE_UP = "SWIPE_UP";
  public static final String NEXT_SWIPE_DOWN = "SWIPE_DOWN";
  public static final String NEXT_SWIPE_RIGHT = "SWIPE_RIGHT";
  public static final String NEXT_SWIPE_LEFT = "SWIPE_LEFT";

  //SWIPE=(50%,70%),(50%,50%);WAIT=2000

}