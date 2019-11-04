package adt.avltree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.Util;

/**
 * 
 * Implementacao de uma arvore AVL
 * 
 * @author Claudio Campelo
 *
 * @param <T>
 */
public class AVLTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements
		AVLTree<T> {

	// TODO Do not forget: you must override the methods insert and remove
	// conveniently.
	@Override
	public void insert(BSTNode<T> node,T element) {
		if (node.isEmpty()) {
			node.setData(element);
			
			BSTNode<T> avo = (BSTNode<T>) node.getParent().getParent();
			int b = this.calculateBalance(avo);
			
			if (Math.abs(b) == 2)
				rebalanceUp(avo);

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
	
	// AUXILIARY
	protected int calculateBalance(BSTNode<T> node) {
		return height((BSTNode<T>) node.getLeft()) - height((BSTNode<T>) node.getRight());
	}

	// AUXILIARY
	protected void rebalance(BSTNode<T> node) {
		BSTNode<T> parent = (BSTNode<T>) node.getParent();
		int balance = calculateBalance(parent);
		
		// pendendo a esq e foi adc na esq
		if (balance == 1 && isLeftChild(node)) {
			Util.rightRotation((BSTNode<T>) parent.getParent());
			
			// pendendo a esq e foi adc a dir
		} else if (balance == 1 && !isLeftChild(node)) {
			Util.leftRotation((BSTNode<T>) parent);
			Util.rightRotation((BSTNode<T>) parent.getParent());
			
			// pendendo a dir a foi adc a dir
		} else if (balance == -1 && !isLeftChild(node)) {
			Util.leftRotation((BSTNode<T>) parent.getParent());
			
			// pendendo a dir a foi adc a esq
		} else if (balance == -1 && isLeftChild(node)) {
			Util.rightRotation(parent);
			Util.leftRotation((BSTNode<T>) parent.getParent());
		}
	}

	private boolean isLeftChild(BSTNode<T> node) {
		return node.getData().compareTo(node.getParent().getData()) < 0;
	}

	// AUXILIARY
	protected void rebalanceUp(BSTNode<T> node) {
		BSTNode<T> parent = (BSTNode<T>) node.getParent();
		while (parent != null) {
			rebalance(parent);
			parent = (BSTNode<T>) parent.getParent();
		}
	}
}
