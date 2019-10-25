package adt.bst.extended;

import java.util.Comparator;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;

/**
 * Implementacao de SortComparatorBST, uma BST que usa um comparator interno em suas funcionalidades
 * e possui um metodo de ordenar um array dado como parametro, retornando o resultado do percurso
 * desejado que produz o array ordenado. 
 * 
 * @author Adalberto
 *
 * @param <T>
 */
public class SortComparatorBSTImpl<T extends Comparable<T>> extends BSTImpl<T> implements SortComparatorBST<T> {

	private Comparator<T> comparator;
	
	public SortComparatorBSTImpl(Comparator<T> comparator) {
		super();
		this.comparator = comparator;
	}

	@Override
	public T[] sort(T[] array) {
		
		while (!this.isEmpty()) {
			this.remove(root);
		}
		
		for (int i = 0; i < array.length; i++) {
			this.insert(array[i]);
		}
		
		return this.preOrder();
	}

	@Override
	public T[] reverseOrder() {
		T[] aux = (T[]) new Comparable[this.size()];
		if (!this.isEmpty()) {
			this.reverseOrder(this.getRoot(), 0, aux);
		}
		return aux;
	}
	
	private int reverseOrder(BSTNode<T> node, int i, T[] aux) {
		if (node != null) {
			if (!node.isEmpty()) {
				i = this.reverseOrder((BSTNode<T>) node.getRight(), i, aux);
				aux[i++] = node.getData();
				i = this.reverseOrder((BSTNode<T>) node.getLeft(), i, aux);
			}
		}
		return i;
	}

	@Override
	public BSTNode<T> search(T element) {
		if (element != null) {
			return  this.search(root, element);
		}
		return new BSTNode.Builder<T>().build();
	}
	
	private BSTNode<T> search(BSTNode<T> node, T element) {
		BSTNode<T> retorno = new BSTNode.Builder<T>().build();
		
		if (node != null) {
			if (!node.isEmpty()) {
				int compare = this.comparator.compare(node.getData(), element);
				
				if (compare == 0) {
					retorno = node;

				} else if (compare < 0) {
					retorno =  search((BSTNode<T>) node.getRight(), element);

				} else {
					retorno = search((BSTNode<T>) node.getLeft(), element);
				}
			}
		}
		return retorno;
	}
	
	@Override
	public void insert(T element) {
		if (element != null)
			this.insert(root, element);
	}

	private void insert(BSTNode<T> node, T element) {

		if (node.isEmpty()) {
			node.setData(element);

		} 
		int compare = this.comparator.compare(node.getData(), element);
		
		if (compare < 0) {

			if (node.getRight() == null) {
				node.setRight(new BSTNode.Builder().data(element).parent(node).build());

			} else {
				this.insert((BSTNode<T>) node.getRight(), element);
			}
		} else if (compare > 0) {

			if (node.getLeft() == null) {
				node.setLeft(new BSTNode.Builder().data(element).parent(node).build());

			} else {
				this.insert((BSTNode<T>) node.getLeft(), element);
			}
		}
	}
	
	@Override
	public BSTNode<T> sucessor(T element) {
		if (element == null)
			return null;

		BSTNode<T> aux = this.search(element);

		if (!aux.isEmpty()) {

			if (aux.getRight() != null) {
				return minimum(aux.getRight());
			}

			BSTNode<T> parent = (BSTNode<T>) aux.getParent();

			while (parent != null) {
				if (this.comparator.compare(parent.getData(), element) > 0) {
					return parent;
				}
				parent = (BSTNode<T>) parent.getParent();
			}
		}
		return null;
	}

	@Override
	public BSTNode<T> predecessor(T element) {
		if (element == null)
			return null;

		BSTNode<T> aux = this.search(element);

		if (!aux.isEmpty()) {

			if (aux.getLeft() != null) {
				return maximum(aux.getLeft());
			}

			BSTNode<T> parent = (BSTNode<T>) aux.getParent();

			while (parent != null) {
				if (this.comparator.compare(parent.getData(), element) < 0) {
					return parent;
				}
				parent = (BSTNode<T>) parent.getParent();
			}
		}
		return null;
	}
	
	@Override
	public void remove(T element) {
		if (element != null) {
			BSTNode<T> aux = search(element);
			if (aux.isEmpty())
				return;
			remove(aux);
		}
	}

	private void remove(BSTNode<T> node) {
		if (node.isEmpty())
			return;

		if (node.getLeft() != null && node.getRight() != null) {

			BSTNode<T> sucessor = sucessor(node.getData());
			node.setData(sucessor.getData());
			remove(sucessor);

		} else {
			if (node.getLeft() == null && node.getRight() == null) {
				if (node.getParent() == null) {
					node.setData(null);
					
				} else if (this.comparator.compare(node.getData(), node.getParent().getData()) < 0) {
					node.getParent().setLeft(null);

				} else {
					node.getParent().setRight(null);
				}
			} else {
				BSTNode<T> auxNode = (BSTNode<T>) node.getLeft();
				if (auxNode == null) {
					auxNode = (BSTNode<T>) node.getRight();
				}

				node.setData(auxNode.getData());
				node.setRight(auxNode.getRight());
				node.setLeft(auxNode.getLeft());

				if (!node.isEmpty() && node.getRight() != null)
					node.getRight().setParent(node);
				if (!node.isEmpty() && node.getLeft() != null)
					node.getLeft().setParent(node);
			}
		}
	}
	
	public Comparator<T> getComparator() {
		return comparator;
	}

	public void setComparator(Comparator<T> comparator) {
		this.comparator = comparator;
	}
	
}
