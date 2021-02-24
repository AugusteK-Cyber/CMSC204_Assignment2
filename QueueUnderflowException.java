package application;

/**
 * Exception when an enqueue method is called on a full queue
 * @author Auguste K.
 */
public class QueueUnderflowException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public QueueUnderflowException(String errorMessage) {
		super(errorMessage);
	}

}
