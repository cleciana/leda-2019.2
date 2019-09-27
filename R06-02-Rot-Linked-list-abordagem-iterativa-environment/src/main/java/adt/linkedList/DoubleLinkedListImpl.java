package adt.linkedList;

public class DoubleLinkedListImpl<T> extends SingleLinkedListImpl<T> implements
		DoubleLinkedList<T> {

	protected DoubleLinkedListNode<T> last;
	
	public DoubleLinkedListImpl() {
		this.head = new DoubleLinkedListNode<T>();
		this.last = (DoubleLinkedListNode<T>) head;
		
	}
	
	@Override
	public void insert(T element) {
		
		if(element != null) {
			DoubleLinkedListNode<T> auxNil = new DoubleLinkedListNode<T>();
			DoubleLinkedListNode<T> newLast = new DoubleLinkedListNode<T>();
			
			newLast.setData(element);
			newLast.setPrevious(last);
			newLast.setNext(auxNil);
			last.setNext(newLast);
			
			if(last.isNIL()) {
				this.setHead(newLast);
				
			}setLast(newLast);
		}
	}
	
	@Override
	public void insertFirst(T element) {
		
		if(element != null) {
			
			DoubleLinkedListNode<T> newHead = new DoubleLinkedListNode<T>();
			
			newHead.setData(element);
			newHead.setNext(head);
			newHead.setPrevious(new DoubleLinkedListNode<T>());
			
			if(this.head instanceof DoubleLinkedListNode) {
				((DoubleLinkedListNode<T>) head).setPrevious(newHead);
			}
			if(head.isNIL()) {
				this.setLast(newHead);
			} this.setHead(newHead);
		}
	}

	@Override
	public void removeFirst() {
		if(!this.isEmpty()) {
			DoubleLinkedListNode<T> auxNil = new DoubleLinkedListNode<T>();
			
			if(!head.isNIL()) {
				setHead(head.next);
				
				if(head.isNIL()) {
					
					if(head instanceof DoubleLinkedListNode) {
						setLast((DoubleLinkedListNode<T>) head);
					}
				}
				if(head instanceof DoubleLinkedListNode) {
					((DoubleLinkedListNode<T>) head).setPrevious(auxNil);
				}
			}
		} 
	}

	@Override
	public void removeLast() {
		if(!this.isEmpty()) {
			
			if(!last.isNIL()) {
				
				DoubleLinkedListNode<T> auxNil = new DoubleLinkedListNode<T>();
				setLast(last.previous);
				
				if(last.isNIL()) {
					setHead(last);
				}
				last.setNext(auxNil);
			}
		}
	}

	public DoubleLinkedListNode<T> getLast() {
		return last;
	}

	public void setLast(DoubleLinkedListNode<T> last) {
		this.last = last;
	}

}
