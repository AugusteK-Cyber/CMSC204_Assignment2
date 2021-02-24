package application;
/**
 * Notation Queue class
 * @author Auguste K.
 */
import java.util.ArrayList;

public class NotationQueue<T> implements QueueInterface<T> {

	// Variables
	int maxQueue, nodesNumber, head, tail;
	ArrayList<T> data;

	/**
	 * Constructors
	 */
	public NotationQueue() {
		maxQueue = 5;
		nodesNumber = 0;
		head = 0;
		tail = 0;
		data = new ArrayList<T>(maxQueue);
	}

	public NotationQueue(int maxQueue) {
		this.maxQueue = maxQueue;
		nodesNumber = 0;
		head = 0;
		tail = 0;
		data = new ArrayList<T>(maxQueue);
	}

	/**
	 * Checks if the Queue is empty
	 * @return true if queue is empty and false otherwise
	 */
	@Override
	public boolean isEmpty() {

		return data.isEmpty();
	}

	/**
	 * Checks if the Queue is full
	 * @return true if queue is full and false otherwise
	 */
	@Override
	public boolean isFull() {

		return data.size() >= maxQueue;
	}

	/**
	 * Delete the element at the front of the Queue
	 * @return the element at the front of the Queue
	 */
	@Override
	public T dequeue() throws QueueUnderflowException {

		if (this.isEmpty()) {
			throw new QueueUnderflowException("The queue is empty");
		}
		// Remove the first element		
		return data.remove(0);
	}

	/**
	 * Number of elements in the Queue
	 * @return the number of elements in the Queue
	 */
	@Override
	public int size() {

		return data.size();
	}

	/**
	 * Add an element to the end of the Queue
	 * @param e the element to add to the end of the Queue
	 * @return true if the add was successful, false if not
	 */
	@Override
	public boolean enqueue(T e) throws QueueOverflowException {
		if (this.isFull()) {
			throw new QueueOverflowException("The queue is full");
		}
		else {
			data.add(data.size(), e);
		}
		// Add element to the end of the Queue		
		return true;
	}

	/**
	 * Return the string representation of the elements in the Queue with 
	 * the beginning of the string being the front of the queue
	 * @return string representation of the Queue with elements
	 */
	@Override
	public String toString() {
		String queueList = "";
		ArrayList<T> dataCopy = new ArrayList<T>();
		dataCopy = data;

		for(T e: dataCopy) {
			queueList += e;
		}
		return queueList;
	}

	/**
	 * Return the string representation of the elements in the Queue with 
	 * a delimiter placed between all elements of the Queue
	 * @return string representation of the Queue with elements
	 */
	@Override
	public String toString(String delimiter) {
		String queueList = "", prefix = "";
		ArrayList<T> dataCopy = new ArrayList<T>();
		dataCopy = data;

		for(T e: dataCopy) {
			queueList += (prefix+e);
			prefix = delimiter;
		}
		return queueList;
	}

	/**
	 * Fills the Queue with the elements of the ArrayList with the first element in 
	 * the ArrayList being the first element in the Queue
	 * @param list elements to be added to the Queue
	 */
	@Override
	public void fill(ArrayList<T> list) {
		//Copy of list
		ArrayList<T> copyData = list;
		// Add copy of list to the Stack
		for (T e: copyData) {
			data.add(e);
		}
	}
}
