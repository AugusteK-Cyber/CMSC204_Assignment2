package application;

/**
 * Exception when a a top or pop method is called on an empty stack
 * @author Auguste K.
 */
public class StackOverflowException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public StackOverflowException(String errorMessage) {
		super(errorMessage);
	}

}
