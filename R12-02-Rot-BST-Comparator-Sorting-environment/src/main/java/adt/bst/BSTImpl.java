package adt.bst;

import adt.bt.BTNode;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class BSTImpl<T extends Comparable<T>> implements BST<T> {

	protected BSTNode<T> root;

	public BSTImpl() {
		root = new BSTNode<T>();
	}

	public BSTNode<T> getRoot() {
		return this.root;
	}

	@Override
	public boolean isEmpty() {
		return root.isEmpty();
	}

	@Override
	public int height() {
		return this.height(root);
	}

	private int height(BSTNode<T> node) {
		if (node == null || node.isEmpty())
			return -1;

		return 1 + Math.max(height((BSTNode<T>) node.getLeft()), height((BSTNode<T>) node.getRight()));
	}

	@Override
	public BSTNode<T> search(T element) {
		BSTNode<T> retorno = new BSTNode.Builder().build();
		
		if (element != null) {
			retorno =  this.search(root, element);
		}
		return retorno;
	}

	private BSTNode<T> search(BSTNode<T> node, T element) {
		BSTNode<T> retorno = new BSTNode.Builder().build();
		
		if (node != null) {
			if (!node.isEmpty()) {

				if (node.getData().compareTo(element) == 0) {
					retorno = node;

				} else if (node.getData().compareTo(element) < 0) {
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

		} else if (node.getData().compareTo(element) < 0) {

			if (node.getRight() == null) {
				node.setRight(new BSTNode.Builder().data(element).parent(node).build());

			} else {
				this.insert((BSTNode<T>) node.getRight(), element);
			}
		} else if (node.getData().compareTo(element) > 0) {

			if (node.getLeft() == null) {
				node.setLeft(new BSTNode.Builder().data(element).parent(node).build());

			} else {
				this.insert((BSTNode<T>) node.getLeft(), element);
			}
		}
	}

	@Override
	public BSTNode<T> maximum() {
		if (this.isEmpty())
			return null;

		return maximum(root);
	}

	public BSTNode<T> maximum(BTNode<T> node) {

		if (node.getRight() == null || node.getRight().isEmpty())
			return (BSTNode<T>) node;

		return maximum(node.getRight());
	}

	@Override
	public
	BSTNode<T> minimum() {
		if (this.isEmpty())
			return null;

		return minimum(root);
	}

	public BSTNode<T> minimum(BTNode<T> node) {

		if (node.getLeft() == null || node.getLeft().isEmpty())
			return (BSTNode<T>) node;

		return minimum(node.getLeft());
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
				if (parent.getData().compareTo(element) > 0) {
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
				if (parent.getData().compareTo(element) < 0) {
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
				if (node.getData().compareTo(node.getParent().getData()) < 0) {
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

	@Override
	public T[] preOrder() {
		T[] aux = (T[]) new Comparable[this.size()];
		if (!this.isEmpty()) {
			this.preOrder(this.getRoot(), 0, aux);
		}
		return aux;
	}

	private int preOrder(BSTNode<T> node, int i, T[] array) {
		if (node != null) {
			if (!node.isEmpty()) {
				array[i++] = node.getData();
				i = this.preOrder((BSTNode<T>) node.getLeft(), i, array);
				i = this.preOrder((BSTNode<T>) node.getRight(), i, array);
			}
		}
		return i;
	}

	@Override
	public T[] order() {
		T[] aux = (T[]) new Comparable[this.size()];
		if (!this.isEmpty()) {
			this.order(this.getRoot(), 0, aux);
		}
		return aux;
	}

	private int order(BSTNode<T> node, int i, T[] array) {
		if (node != null) {
			if (!node.isEmpty()) {
				i = this.order((BSTNode<T>) node.getLeft(), i, array);
				array[i++] = node.getData();
				i = this.order((BSTNode<T>) node.getRight(), i, array);
			}
		}
		return i;
	}

	@Override
	public T[] postOrder() {
		T[] aux = (T[]) new Comparable[this.size()];
		if (!this.isEmpty()) {
			this.postOrder(this.getRoot(), 0, aux);
		}
		return aux;
	}

	private int postOrder(BSTNode<T> node, int i, T[] array) {
		if (node != null) {
			if (!node.isEmpty()) {
				i = this.postOrder((BSTNode<T>) node.getLeft(), i, array);
				i = this.postOrder((BSTNode<T>) node.getRight(), i, array);
				array[i++] = node.getData();
			}
		}
		return i;
	}

	/**
	 * This method is already implemented using recursion. You must understand how
	 * it work and use similar idea with the other methods.
	 */
	@Override
	public int size() {
		return size(root);
	}

	private int size(BSTNode<T> node) {
		if (node == null)
			return 0;

		int result = 0;

		// base case means doing nothing (return 0)
		if (!node.isEmpty()) { // inductive case
			result = 1 + size((BSTNode<T>) node.getLeft()) + size((BSTNode<T>) node.getRight());
		}
		return result;
	}

}
