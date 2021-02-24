package application;

/**
 * Exception when a push method is called on a full stack
 * @author Auguste K.
 */
public class StackUnderflowException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public StackUnderflowException(String errorMessage) {
		super(errorMessage);
	}

}
