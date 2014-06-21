package com.staxmod.core;

/**
 * @author Adrian Diez
 * @author Emanuel Iosif
 * @author Ivan de Paz
 * @since 16/06/2014
 * @version 1
 *
 * Global class with all the global values needed for the application.
 */
public class Globals {

    /** Values of the cancel dialog **/
    public static final String ERROR_TITLE = "Cancel";
    public static final String ERROR_MSG = "Cancelled by user";

    /** Title of the correct dialog **/
    public static final String CORRECT_TITLE = "XML Analysis";

    /** Message about count of books **/
    public static final String MSG_COUNT_1 = "Hay un total de ";
    public static final String MSG_COUNT_2 = " libros en el documento.";

    /** Message about books **/
    public static final String MSG_1 = "Los autores: ";
    public static final String MSG_2 = " publicaron el libro ";
    public static final String MSG_3 = " en la editorial ";
    public static final String MSG_4 = ". Cuesta ";
    public static final String MSG_5 = " euros.";


    /** File filter **/
    public static final String XML_FILES = "XML Files";
    public static final String XML = "xml";

    /** Format strings **/
    public static final String NEXT_LINE = "\n";
    public static final String EMPTY = "";
    public static final String COMMA = ",";
    public static final String DOUBLE_COMMA = "\"";

    /** Integer values **/
    public static final int ZERO_I = 0;

    /** Double values **/
    public static final double ZERO_D = 0.0d;

    /** Error value **/
    public static final int ERROR = -1;

    /** Flags for XMLParser **/
    public static final int BOOK_LOCAL_NAME = 0;
    public static final int AUTHOR_LOCAL_NAME = 1;

    /** XML tag values **/
    public static final String BOOK = "Libro";
    public static final String AUTHOR = "autor";
    public static final String NAME = "nombre";
    public static final String TITLE_B = "titulo";

    /** Boolean values **/
    public static final boolean EXISTS = true;
    public static final boolean NO_EXISTS = false;
}


