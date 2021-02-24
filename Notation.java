package application;
/**
 * Notation class
 * @author Auguste K.
 */
public class Notation {

	// stack and queue
	private static NotationStack<String> stack;
	private static NotationQueue<String> queue;
	
	/**
	 * Get the element at the top of the stack
	 * @return the element at the top of the stack (don't remove it)
	 * @throws StackUnderflowException
	 */
	private static String stackTop() throws StackUnderflowException {
		try {
			return stack.top();
		}
		catch(StackUnderflowException e) {
			throw new StackUnderflowException("StackUnderflowException");
		}
	}
	
	/**
	 * Remove the element at the top of the stack
	 * @return the element removed at the top of the stack
	 * @throws StackUnderflowException
	 */
	private static String stackPop() throws StackUnderflowException {
		try {
			return stack.pop();
		}
		catch(StackUnderflowException e) {
			throw new StackUnderflowException("StackUnderflowException");
		}
	}
	
	/**
	 * Add an element to the top of the stack
	 * @param elementToAdd element to add to the stack
	 * @return true if element is successfully added, false otherwise
	 * @throws StackOverflowException
	 */
	private static boolean pushStack(String elementToAdd) throws StackOverflowException {
		try {
			return stack.push(elementToAdd);
		}
		catch(StackOverflowException e) {
			throw new StackOverflowException("StackOverflowException");
		}
	}
	
	/**
	 * Add an element to the queue
	 * @param elementToAdd element to add to the queue
	 * @return the element added to the queue
	 * @throws QueueOverflowException
	 */
	private static boolean enqueue(String elementToAdd) throws QueueOverflowException {
		try {
			return queue.enqueue(elementToAdd);
		}
		catch(QueueOverflowException e) {
			throw new QueueOverflowException("QueueOverflowException");
		}
	}
	
	/**
	 *  Proceed to the calculations following the operand used
	 * @param e first operand
	 * @param f second operand
	 * @param operator operator used
	 * @return result the result of the operation
	 * @throws InvalidNotationFormatException
	 */
	private static String postFixCalculation(String e, String f, char operator) 
			throws InvalidNotationFormatException {
		double first = Double.parseDouble(e);
		double second = Double.parseDouble(f);
		String result = " ";
		if (operator == '*') {
			result = Double.toString(first * second);
		}
		if (operator == '/') {
			if (second == 0) {
				throw new InvalidNotationFormatException("Cannot divide by 0");
			}
			result = Double.toString(first / second);
		}
		if (operator == '+') {
			result = Double.toString(first + second);
		}
		if (operator == '-') {
			result = Double.toString(first - second);
		}
		return result;	
	}
	
	/**
	 * Determine the order of precedence of the different operators
	 * @param stackElement element whose char has to be checked
	 * @return precedence of the operator
	 */
	private static boolean operatorsPrecedence(String stackElement) {
		char c = stackElement.charAt(0);
		boolean precedence = false;

		switch(c) {
		case '*':
		case '/':
			precedence = true; break; 
		case '+':
		case '-':
			precedence = true; break; 
		case '(':
		case ')':
			precedence = false; break;
		}
		return precedence;
		
	}

	/**
	 * Convert an infix expression to a postfix expression
	 * @param infix infix expression
	 * @return postfix postfix expression
	 * @throws InvalidNotationFormatException exception
	 */
	public static String convertInfixToPostfix(String infix) 
			throws InvalidNotationFormatException{

		char currentChar = ' '; // Current char in the infix String
		queue = new NotationQueue<String>(40);
		stack = new NotationStack<String>(40);
		try {
			for (int i = 0; i < infix.length(); i++) {
				currentChar = infix.charAt(i);
				// If the current character is a space, ignore it
				if (currentChar == ' ') {
					continue;
				}
				// If the current character is a digit, copy it to the postfix solution queue
				else if (Character.isDigit(currentChar)) {
					enqueue(Character.toString(currentChar));
				}
				// If the current character is a left parenthesis, push it onto the stack
				else if (currentChar == '(') {
					pushStack(Character.toString(currentChar));
				}
				/*
				 *  If the current character is an operator, pop operators (if there are any) 
				 *  at the top of the stack while they have equal or higher precedence than the 
				 *  current operator, and insert the popped operators in postfix solution queue.
				 *  Push the current character in the infix onto the stack. 
				 */
				else if (currentChar == '*' || currentChar == '/' || 
						currentChar == '+' || currentChar == '-') {
					while (!stack.isEmpty() && operatorsPrecedence(stackTop())) {
						enqueue(stackPop());
					}
					pushStack(Character.toString(currentChar));
				}
				// If the current character is a right parenthesis, push it onto the stack
				else if (currentChar == ')') {
					char element = stackPop().charAt(0);
					while (element != '(') {
						enqueue(Character.toString(element));
						if (stack.isEmpty()) {
							throw new InvalidNotationFormatException("The stack is empty");
						}
						else {
							element = stackPop().charAt(0); //Pop left parenthesis from the stack
						}
					}
				}
			}
			// Pop any remaining operators and insert them in the postfix solution queue
			while (!stack.isEmpty()) {
				enqueue(stackPop()); 
			}
		}
		catch (StackOverflowException e) {
			throw new InvalidNotationFormatException("The infix expression is invalid");
		}
		catch (StackUnderflowException e) {
			throw new InvalidNotationFormatException("The infix expression is invalid");
		} 
		catch (QueueOverflowException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return queue.toString();
	}

	/**
	 * Convert an postfix expression to a infix expression
	 * @param postfix postfix expression
	 * @return infix infix expression
	 * @throws InvalidNotationFormatException exception
	 */
	public static String convertPostfixToInfix(String postfix) 
			throws InvalidNotationFormatException {

		stack = new NotationStack<String>(40);
		String result = "", otherStr, anotherStr;
		char currentChar = ' ';
		try {
			for (int i = 0; i < postfix.length(); i++) {
				currentChar = postfix.charAt(i);
				// If the current character is a space, ignore it
				if (currentChar == ' ') {
					continue;
				}
				// If the current character is a digit, copy it to the infix solution stack
				else if (Character.isDigit(currentChar)) {
					pushStack(Character.toString(currentChar));
				}
				/* If the current character is an operator, pop the top 2 values from the stack. 
				 * If there are fewer than 2 values throw an error. Create a string with 1st value 
				 * and then the operator and then the 2nd value Encapsulate the resulting string 
				 * within parenthesis. Push the resulting string back to the stack
				 */
				else if(currentChar == '*' || currentChar == '/' || 
						currentChar == '+' || currentChar == '-') {
					result = stackPop().toString();
					if (stack.isEmpty()) {
						throw new InvalidNotationFormatException("Stack is empty");
					}
					else {
						otherStr = stackPop().toString();
						anotherStr = '(' + otherStr + currentChar + result + ')';
						pushStack(anotherStr);
					}
				}
			}
		/*
		 *  If there is only one value in the stack, it is the infix string, if more than one 
		 *  value, throw an error
		 */		
		if (stack.size() != 1) {
			throw new InvalidNotationFormatException("More than one value in the stack");
		}
		}
		catch(StackUnderflowException e) {
			throw new InvalidNotationFormatException("Invalid notation format exception");

		} catch (StackOverflowException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String t = " ";
		try {
			t = stackPop();
		} catch (StackUnderflowException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return t;
	}

	/**
	 * Evaluate a postfix expression
	 * @param postfix postfix expression
	 * @return evaluated expression
	 * @throws InvalidNotationFormatException exception
	 */
	public static double evaluatePostfixExpression(String postfix) 
			throws InvalidNotationFormatException {

		stack = new NotationStack<String>(40);
		String result = " ", otherStr, anotherStr;
		char currentChar = ' ';
		try {
			for (int i = 0; i < postfix.length(); i++) {
				currentChar = postfix.charAt(i);
				// If the current character is a space, ignore it
				if (currentChar == ' ') {
					continue;
				}
				// If the current character is a digit or a left parenthesis, push it onto the stack
				else if (currentChar == '(' || Character.isDigit(currentChar)) {
					pushStack(Character.toString(currentChar));
				}
				/*
				 *  If the current character is an operator, pop the top 2 values from the stack. If 
				 *  there are fewer than 2 values throw an error. Perform the arithmetic calculation 
				 *  of the operator with the first popped value as the right operand and the second popped 
				 *  value as the left operand. Push the resulting value onto the stack
				 */
				else if (currentChar == '*' || currentChar == '/' || 
						currentChar == '+' || currentChar == '-') {
					otherStr = stackPop().toString();
					if (stack.isEmpty()) {
						throw new InvalidNotationFormatException("The stack is empty");
					}
					else {
						anotherStr = stackPop().toString();
						result = postFixCalculation(anotherStr, otherStr, currentChar);
						pushStack(result);
					}
					
				}
			}
			/*
			 * If there is only one value in the stack, it is the result of the postfix expression, 
			 * if more than one value, throw an error
			 */
			if (stack.size() != 1) {
				throw new InvalidNotationFormatException("Value was not pushed in the stack");
			}
		}
		catch (StackOverflowException e) {
			throw new InvalidNotationFormatException("StackOverflowException");
		}
		catch (StackUnderflowException e) {
			throw new InvalidNotationFormatException("StackUnderflowException");
		}

		double r = 0;
		;
		try {
			r = Double.parseDouble(stackPop());
		} catch (NumberFormatException | StackUnderflowException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return r;
	}

	/**
	 * Evaluate the postfix expression. It will take in a string and return a double.
	 * @param infixExpr infix expression
	 * @return postfix evaluated expression
	 * @throws InvalidNotationFormatException exception
	 */
	public static double evaluateInfixExpression(String infixExpr) 
			throws InvalidNotationFormatException {

		String postfixExpr = convertInfixToPostfix(infixExpr);
		return evaluatePostfixExpression(postfixExpr);
	}

}
