package adt.hashtable.open;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionLinearProbing;

public class HashtableOpenAddressLinearProbingImpl<T extends Storable> extends AbstractHashtableOpenAddress<T> {

	public HashtableOpenAddressLinearProbingImpl(int size, HashFunctionClosedAddressMethod method) {
		super(size);
		hashFunction = new HashFunctionLinearProbing<T>(size, method);
		this.initiateInternalTable(size);
	}

	@Override
	public void insert(T element) {
		if (this.elements == this.capacity()) {
			throw new HashtableOverflowException();
		}
		if (element != null) {
			int i = 0;

			while (i < this.capacity()) {
				int h = ((HashFunctionLinearProbing<T>) getHashFunction()).hash(element, i);

				if (this.table[h] == null || this.table[h] instanceof DELETED) {
					this.table[h] = element;
					this.elements += 1;
					return;
				}
				this.COLLISIONS += 1;
				i += 1;
			}
		}
	}

	@Override
	public void remove(T element) {
		
		if (!this.isEmpty() && element != null) {
			int i = this.indexOf(element);
			
			if (i != -1) {
				table[i] = new DELETED();
				this.elements -= 1;
			}
		}
	}

	@Override
	public T search(T element) {
		if (element != null) {

			int i = 0;
			while (i < this.capacity()) {
				int hash = ((HashFunctionLinearProbing<T>) getHashFunction()).hash(element, i);

				if (this.table[hash] != null && this.table[hash].equals(element))
					return element;
				i += 1;
			}
		}
		return null;
	}

	@Override
	public int indexOf(T element) {
		int index = -1;
		int i = 0;

		while (i < this.capacity()) {
			int hash = ((HashFunctionLinearProbing<T>) getHashFunction()).hash(element, i);

			if (this.table[hash] == null) {
				return index;
				
			} else if (this.table[hash].equals(element)){
				return hash; 
			}
			i += 1;
		}
		return index;
	}

}
