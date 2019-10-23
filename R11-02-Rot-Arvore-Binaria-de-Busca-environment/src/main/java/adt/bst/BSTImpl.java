package adt.bst;

import adt.bt.BTNode;

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
		if (element != null) {
			return this.search(root, element);
		}
		return new BSTNode<>();
	}

	private BSTNode<T> search(BSTNode<T> node, T element) {
		if (!node.isEmpty()) {

			if (node.getData().compareTo(element) == 0) {
				return node;

			} else if (node.getData().compareTo(element) < 0) {
				return search((BSTNode<T>) node.getRight(), element);

			} else {
				search((BSTNode<T>) node.getLeft(), element);
			}
		}
		return new BSTNode<>();
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
				node.setRight(new BSTNode<T>());
				node.getRight().setData(element);
				node.getRight().setParent(node);

			} else {
				this.insert((BSTNode<T>) node.getRight(), element);
			}
		} else if (node.getData().compareTo(element) > 0) {
			if (node.getLeft() == null) {
				node.setLeft(new BSTNode<T>());
				node.getLeft().setData(element);
				node.getLeft().setParent(node);

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

	private BSTNode<T> maximum(BTNode<T> node) {

		if (node.getRight() == null || node.getRight().isEmpty())
			return (BSTNode<T>) node;

		return maximum(node.getRight());
	}

	@Override
	public BSTNode<T> minimum() {
		if (this.isEmpty())
			return null;

		return minimum(root);
	}

	private BSTNode<T> minimum(BTNode<T> node) {

		if (node.getLeft() == null || node.getLeft().isEmpty())
			return (BSTNode<T>) node;

		return minimum(node.getLeft());
	}

	@Override
	public BSTNode<T> sucessor(T element) {
		BSTNode<T> aux = this.search(element);

		if (!aux.isEmpty()) {
			return minimum(aux.getRight());
		}
		return null;
	}

	@Override
	public BSTNode<T> predecessor(T element) {
		BSTNode<T> aux = this.search(element);

		if (!aux.isEmpty()) {
			return maximum(aux.getLeft());
		}
		return null;
	}

	@Override
	public void remove(T element) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	@Override
	public T[] preOrder() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	@Override
	public T[] order() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	@Override
	public T[] postOrder() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
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
		if (node == null) return 0;
		
		int result = 0;
		
		// base case means doing nothing (return 0)
		if (!node.isEmpty()) { // inductive case
			result = 1 + size((BSTNode<T>) node.getLeft()) + size((BSTNode<T>) node.getRight());
		}
		return result;
	}

}
