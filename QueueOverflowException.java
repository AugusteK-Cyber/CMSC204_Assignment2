package application;

/**
 * Exception when a dequeue method is called on an empty queue
 * @author Auguste K.
 */
public class QueueOverflowException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public QueueOverflowException(String errorMessage) {
		super(errorMessage);
	}

}
