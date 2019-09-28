package adt.linkedList;

public class RecursiveDoubleLinkedListImpl<T> extends RecursiveSingleLinkedListImpl<T> implements DoubleLinkedList<T> {

	protected RecursiveDoubleLinkedListImpl<T> previous;

	public RecursiveDoubleLinkedListImpl() {
	}

	public RecursiveDoubleLinkedListImpl(T data,
			RecursiveSingleLinkedListImpl<T> next,
			RecursiveDoubleLinkedListImpl<T> previous) {
		super();
		this.previous = previous;
	}

	@Override
	public void insert(T element) {
		if (this.isEmpty()) {
			
			this.setData(element);
			RecursiveDoubleLinkedListImpl<T> helper = new RecursiveDoubleLinkedListImpl<>();
			helper.setPrevious(this);
			this.setNext(helper);
			
			if (this.previous == null) {
				this.setPrevious(new RecursiveDoubleLinkedListImpl<T>());
				this.getPrevious().setNext(this);
			}
		} else {
			this.next.insert(element);
		}
	}

	@Override
	public void remove(T element) {		
		if (element != null) {
			if (!this.isEmpty() && this.data.equals(element)) {
				
				if (this.getPrevious().isEmpty() && this.getNext().isEmpty()) {
					this.setData(null);
					this.setNext(null);
					this.setPrevious(null);
					
				} else {
					this.setData(this.next.getData());
					this.setNext(this.next.getNext());
					
					if (this.getNext() != null)
						((RecursiveDoubleLinkedListImpl<T>) this.getNext()).setPrevious(this);
				}
			} else {
				this.getNext().remove(element);
			}
		}
	}

	@Override
	public void insertFirst(T element) {
		if (this.isEmpty()) {
			this.insert(element);
			
		} else {
			RecursiveDoubleLinkedListImpl<T> node = new RecursiveDoubleLinkedListImpl<T>(this.getData(), null, null);
			node.setNext(this.getNext());
			((RecursiveDoubleLinkedListImpl<T>) this.getNext()).setPrevious(node);
			node.setPrevious(this);
			this.setNext(node);
			this.setData(element);
		}
	}
	
	@Override
	public void removeFirst() {
		if (!this.isEmpty()) {
			
			if (this.getPrevious().isEmpty() && this.getNext().isEmpty()) {
				this.setData(null);
				this.setNext(null);
				this.setPrevious(null);
				
			} else {
				this.setData(this.next.getData());
				this.setNext(this.next.getNext());
				((RecursiveDoubleLinkedListImpl<T>) this.next).setPrevious(this);
			}
		}
	}

	@Override
	public void removeLast() {
		if (!this.isEmpty()) {
			
			if (this.getNext().isEmpty() && this.getPrevious().isEmpty()) {
				this.setData(null);
				this.setNext(null);
				this.setPrevious(null);
				
			} else {
				if (this.getNext().isEmpty()) {
					this.setData(null);
					this.setNext(null);

				} else {
					((RecursiveDoubleLinkedListImpl<T>) this.next).removeLast();
				}
			}
		}
	}

	public RecursiveDoubleLinkedListImpl<T> getPrevious() {
		return previous;
	}

	public void setPrevious(RecursiveDoubleLinkedListImpl<T> previous) {
		this.previous = previous;
	}
}
