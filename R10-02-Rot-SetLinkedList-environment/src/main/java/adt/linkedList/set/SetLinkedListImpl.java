package adt.linkedList.set;

import adt.linkedList.SingleLinkedListImpl;
import adt.linkedList.SingleLinkedListNode;

public class SetLinkedListImpl<T> extends SingleLinkedListImpl<T> implements SetLinkedList<T> {

	@Override
	public void removeDuplicates() {
		if (!this.isEmpty()) {

			SingleLinkedListNode<T> node = this.head;
			SingleLinkedListNode<T> aux = node.getNext();

			while (node != null && !node.isNIL()) {

				while (aux != null && !aux.isNIL()) {

					if (node.getData().equals(aux.getData())) {
						this.remove(aux.getData());
					}
					aux = aux.getNext();
				}
				node = node.getNext();
			}
		}
	}

	@Override
	public SetLinkedList<T> union(SetLinkedList<T> otherSet) {
		
		SetLinkedList<T> retorno = this;

		if (otherSet != null) {

			T[] other = otherSet.toArray();
			for (int i = 0; i < other.length; i++) {
				retorno.insert(other[i]);
			}
			this.removeDuplicates();
		}
		return retorno;
	}

	@Override
	public SetLinkedList<T> intersection(SetLinkedList<T> otherSet) {
		
		SetLinkedList<T> retorno = new SetLinkedListImpl<>();

		if (otherSet != null) {
			
			T[] other = otherSet.toArray();
			for (int i = 0; i < other.length; i++) {
				if (this.search(other[i]) != null) {
					retorno.insert(other[i]);
				}
			}
			this.removeDuplicates();
		}
		return retorno;
	}

	@Override
	public void concatenate(SetLinkedList<T> otherSet) {
		
		SingleLinkedListNode<T> node = this.head;
		
		while (node != null && !node.isNIL()) {
			node = node.getNext();
		}
		node.setNext(((SingleLinkedListImpl<T>) otherSet).getHead());
		
		this.removeDuplicates();
	}

}
