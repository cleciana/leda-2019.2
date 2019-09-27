package adt.queue;

import adt.linkedList.DoubleLinkedList;
import adt.linkedList.DoubleLinkedListImpl;

public class QueueDoubleLinkedListImpl<T> implements Queue<T> {

	protected DoubleLinkedList<T> list;
	protected int size;

	public QueueDoubleLinkedListImpl(int size) {
		this.size = size;
		this.list = new DoubleLinkedListImpl<T>();
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if(element != null) {
			
			if (!this.isFull()) {
				list.insert(element);
				
			} else {
				throw new QueueOverflowException();
			}
		}
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		T retorno = null;
		
		if(!this.isEmpty()) {
			retorno = this.head();
			list.removeFirst();
			
		} else {
			throw new QueueUnderflowException();
		}
		return retorno;
	}

	@Override
	public T head() {
		T retorno = null;
		if(!this.isEmpty()) {
		
			if (list instanceof DoubleLinkedListImpl) {
				retorno = (T) ((DoubleLinkedListImpl<T>) list).getHead().getData();
			}
		}
		return retorno;
	}

	@Override
	public boolean isEmpty() {
		return list.size() == 0;
	}

	@Override
	public boolean isFull() {
		return list.size() == size;
	}

}
