package application;

import java.util.ArrayList;

/**
 * @author Auguste K.
 */

public class NotationStack<T> implements StackInterface<T> {

	// Variables
	private ArrayList<T> data;
	private int maxStack;

	/**
	 * Constructor
	 */
	public NotationStack () {
		maxStack = 5;
		data = new ArrayList<T>(maxStack);
	}

	/**
	 * Constructor with a parameter
	 * @param stackSize size of the stack
	 */
	public NotationStack(int stackSize) {
		maxStack = stackSize;
		data = new ArrayList<T>(maxStack);
	}

	/**
	 * Checks if the stack is empty
	 * @return true if stack is empty and false otherwise
	 */
	@Override
	public boolean isEmpty() {
	
		return data.isEmpty();
	}

	/**
	 * Checks if the stack is full
	 * @return true if the stack is full and false otherwise
	 */
	@Override
	public boolean isFull() {

		return data.size() >= maxStack;
	}

	/**
	 * Remove an entry from the top of the stack
	 * @return element popped from the top of stack
	 * @throws StackUnderflowException exception
	 */
	@Override
	public T pop() throws StackUnderflowException {
		if (this.isEmpty()) {
			throw new StackUnderflowException("The stack is empty");
		}
		return data.remove(data.size()-1);
	}

	/**
	 * Get the entry at the top of the stack
	 * @return element at the top of the stack
	 * @throws StackUnderflowException exception
	 */
	@Override
	public T top() throws StackUnderflowException {
		if (this.isEmpty()) {
			throw new StackUnderflowException("The stack is empty");
		}
		return data.get(data.size()-1);
	}

	/**
	 * Size of the stack
	 * @return size of the stack
	 */
	@Override
	public int size() {

		return data.size();
	}

	/**
	 * Add an element to the top of the Stack
	 * @return true if an element is added to the stack
	 * @throws StackOverflowException exception
	 */
	@Override
	public boolean push(T e) throws StackOverflowException {
		if (this.isFull()) {
			throw new StackOverflowException("The stack is full");
		}
		else {
			data.add(data.size(), e);
		}
		return true;
	}

	/**
	 * Lists the elements of the Stack in a string from bottom to top
	 * @return stackList a list of the stack elements
	 */
	@Override
	public String toString() {
		String stackList = "";
		ArrayList<T> dataCopy = new ArrayList<T>();

		dataCopy = data;
		// String representation
		for(T e: dataCopy) {
			stackList += e;
		}
		return stackList;
	}

	/**
	 * Lists the elements of the Stack in a string from bottom to top
	 * with a delimiter placed between all elements of the Stack
	 * @return stackList a list of the stack elements with a delimiter
	 */
	@Override
	public String toString(String delimiter) {
		String stackList = "", prefix = "";
		ArrayList<T> dataCopy = new ArrayList<T>();

		dataCopy = data;
		// String representation
		for(T e: dataCopy) {
			stackList += (prefix + e);
			prefix = delimiter;
		}
		return stackList;
	}

	/**
	 * Fills the Stack with the elements of the ArrayList with the first element in 
	 * the ArrayList being the first bottom element of the Stack
	 * @param list elements to be added to the Stack from bottom to top
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
