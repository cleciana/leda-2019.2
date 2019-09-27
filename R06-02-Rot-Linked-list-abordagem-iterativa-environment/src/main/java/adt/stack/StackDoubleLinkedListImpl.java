package adt.stack;

import adt.linkedList.DoubleLinkedList;
import adt.linkedList.DoubleLinkedListImpl;

public class StackDoubleLinkedListImpl<T> implements Stack<T> {

	protected DoubleLinkedList<T> top;
	protected int size;

	public StackDoubleLinkedListImpl(int size) {
		this.size = size;
		this.top = new DoubleLinkedListImpl<T>();
	}

	@Override
	public void push(T element) throws StackOverflowException {
		if(element != null) {
			
			if (!this.isFull()) {
				top.insert(element);
				
			} else {
				throw new StackOverflowException();
			}
		}
	}

	@Override
	public T pop() throws StackUnderflowException {
		
		if(!this.isEmpty()) {
			T retorno = top();
			this.top.removeLast();
			return retorno;
			
		} else {
			throw new StackUnderflowException();
		}
	}

	@Override
	public T top() {
		T retorno = null;
		
		if(!this.isEmpty()) {
			if (top instanceof DoubleLinkedListImpl) {
				retorno = ((T) ((DoubleLinkedListImpl<T>) top).getLast().getData());
			}
		}
		return retorno;
	}

	@Override
	public boolean isEmpty() {
		return top.size() == 0;
	}

	@Override
	public boolean isFull() {
		return top.size() == size;
	}

}
