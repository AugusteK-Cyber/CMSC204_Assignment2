package application;

/**
 * Exception when a Notation format is incorrect
 * @author Auguste K.
 */
public class InvalidNotationFormatException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidNotationFormatException (String errorMessage) {
		super(errorMessage);
	}

}
