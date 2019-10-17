package adt.linkedList.set;

import adt.linkedList.SingleLinkedListImpl;
import adt.linkedList.SingleLinkedListNode;

@SuppressWarnings("unchecked")
public class SetLinkedListImpl<T> extends SingleLinkedListImpl<T> implements SetLinkedList<T> {

	@Override
	public void removeDuplicates() {
		if (!this.isEmpty()) {

			SingleLinkedListNode<T> node = this.head;
			SingleLinkedListNode<T> aux = node;

			while (node != null && !node.isNIL()) {

				while (aux != null && !aux.isNIL()) {
					SingleLinkedListNode<T> auxNext = aux.getNext();
					
					if (node.getData().equals(auxNext.getData())) {
						aux.setNext(auxNext.getNext());
					}
					aux = auxNext;
				}
				node = node.getNext();
			}
		}
	}

	@Override
	public SetLinkedList<T> union(SetLinkedList<T> otherSet) {

		SetLinkedList<T> retorno = this;

		if (otherSet != null) {
			SingleLinkedListNode<T> other = ((SingleLinkedListImpl<T>) otherSet).getHead();

			while (!other.isNIL()) {
				retorno.insert(other.getData());
				other = other.getNext();
			}
			this.removeDuplicates();
		}
		return retorno;
	}

	@Override
	public SetLinkedList<T> intersection(SetLinkedList<T> otherSet) {

		SetLinkedList<T> retorno = new SetLinkedListImpl<>();

		if (otherSet != null) {
			SingleLinkedListNode<T> other = ((SingleLinkedListImpl<T>) otherSet).getHead();

			while (!other.isNIL()) {
				if (this.search(other.getData()) != null) {
					retorno.insert(other.getData());
				}
				other = other.getNext();
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
