package tech.tritonit.pdf.core.document;

public class PdfSyntax {
    private PdfSyntax() {
        //Constant class must not be instantiated.
    }

    /**
     * PDF line separator.
     */
    public static final String LINE_SEPARATOR = "\n";
    public static final String REFERENCE = " 0 R";
    /**
     * PDF line separator.
     */
    public static final String PDF_VERSION_1_7 = "%PDF-1.7";

    /**
     * A raw descriptor delimiter.
     */
    public static final String NAME_OBJECT_PREFIX = "/";

    public static final String BEGIN_DICTIONARY = " <<";

    /**
     * End of descriptor PDF command.
     */
    public static final String END_DICTIONARY = ">>";

    /**
     * Starting array PDF command.
     */
    public static final String BEGIN_ARRAY = " [";

    /**
     * End of array PDF command.
     */
    public static final String END_ARRAY = "]";

    public static final String BEGIN_STRING = "(";
    public static final String END_STRING = ") ";

    /**
     * Starting of an stream PDF command.
     */
    public static final String BEGIN_STREAM = "stream";

    /**
     * End stream PDF command.
     */
    public static final String END_STREAM = "endstream";

    /**
     * Starting of an object PDF command.
     */
    public static final String BEGIN_INDIRECT_OBJECT = " 0 obj" + BEGIN_DICTIONARY;

    /**
     * End object PDF command.
     */
    public static final String END_INDIRECT_OBJECT = "endobj";

    /**
     * Begin root command.
     */
    public static final String BEGIN_ROOT = "/Root 1" + REFERENCE;

    /**
     * Begin text PDF command.
     */
    public static final String BEGIN_TEXT = "BT";

    /**
     * End text PDF command.
     */
    public static final String END_TEXT = "ET";

    /**
     * Starting of a catalog PDF command.
     */
    public static final String BEGIN_CATALOG = " /Catalog";
    /**
     * Starting of pages PDF command.
     */
    public static final String BEGIN_PAGES = "/Pages 2" + REFERENCE;

    /**
     * Starting of a font PDF command.
     */
    public static final String BEGIN_FONT = "/Font" + BEGIN_DICTIONARY;

    /**
     * Starting of a resource PDF command.
     */
    public static final String BEGIN_RESOURCE = "/Resources" + BEGIN_DICTIONARY;
    public static final String BEGIN_KIDS = "/Kids [";

    /**
     * Pages PDF descriptor.
     */
    public static final String PAGES = " /Pages";

    public static final String PAGE = " /Page";

    public static final String PARENT = "/Parent ";

    public static final String CONTENTS = "/Contents ";

    /**
     * Length PDF descriptor.
     */
    public static final String LENGTH = "/Length ";

    /**
     * Type PDF descriptor.
     */
    public static final String TYPE = "/Type";

    /**
     * Font type PDF descriptor
     */
    public static final String FONT = " /Font";

    /**
     * Subtype PDF descriptor.
     */
    public static final String SUB_TYPE = "/Subtype";

    /**
     * Type1 subtype PDF descriptor.
     */
    public static final String TYPE1 = " /Type1";

    /**
     * Image subtype PDF descriptor.
     */
    public static final String IMAGE = " /Image";

    /**
     * Count PDF descriptor.
     */
    public static final String COUNT = "/Count ";

    /**
     * MediaBox PDF descriptor.
     */
    public static final String MEDIA_BOX = "/MediaBox";

    public static final String SIZE = "/Size ";

    public static final String BEGIN_TRAILER = "trailer" + BEGIN_DICTIONARY;

    public static final String START_X_REF = "startxref";

    public static final String X_REF = "xref";

    public static final String WIDTH = "/Width ";
    public static final String HEIGHT = "/Height ";
    public static final String X_OBJECT = " /XObject";
    public static final String FILTER = "/Filter";
    public static final String FLATE_DECODE = " /FlateDecode";
    public static final String BITS_PER_COMPONENT = "/BitsPerComponent ";
    public static final String COLOR_SPACE = "/ColorSpace";
    public static final String DEVICE_RGB = " /DeviceRGB";
    public static final String DEVICE_GRAY = " /DeviceGray";
    public static final String ZERO_COORDINATES = "0 0";
    public static final String ONE = "1";
    public static final String EIGHT = "8";
    public static final String CURRENT_MATRIX = " cm";
    public static final String DO = " Do";
    public static final String START_IMAGE = "q";
    public static final String END_IMAGE = "Q";
    public static final String SPACE = " ";
    public static final String BASE_FONT = "/BaseFont ";
    public static final String START_COMMAND = "/";
    public static final String START_X_OBJECT = "/XObject <<";
    public static final String RGB_STROKING_COLOR_AND_SPACE = "RG ";
    public static final String RGB_NON_STROKING_COLOR_AND_SPACE = "rg ";
    public static final String MOVE_TO = " m ";
    public static final String CURVE_TO = " c ";
    public static final String LINE_WIDTH = " w ";
    public static final String END_OF_FILE = "%%EOF";

    public static final String LINE_TO = " l ";
    public static final String TEXT_FONT = " Tf";
    public static final String SHOW_TEXT = "Tj";
    public static final String NEXT_LINE = " Td";
    public static final String FILL_PATH = "f";
    public static final String CLOSE_AND_STROKE_PATH = "s";
    public static final String STROKE_PATH = "S";
    public static final String FILL_AND_STROKE_PATH = "B";

    public static final String REFERENCE_TO_PAGES = "2" + REFERENCE;

    public static final String SOFT_MASK = "/SMask ";
}
