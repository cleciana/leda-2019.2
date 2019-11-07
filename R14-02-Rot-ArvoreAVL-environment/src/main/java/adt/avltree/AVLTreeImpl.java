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
public class AVLTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements AVLTree<T> {

	// TODO Do not forget: you must override the methods insert and remove
	// conveniently.
	@Override
	public void insert(T element) {
		if (element != null)
			this.insert(root, element);
	}

	private void insert(BSTNode<T> node, T element) {
		if (node.isEmpty()) {
			node.setData(element);

			rebalanceUp(node);

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
		rebalanceUp((BSTNode<T>) node.getParent());
	}
	
	// AUXILIARY
	protected int calculateBalance(BSTNode<T> node) {
		return height((BSTNode<T>) node.getLeft()) - height((BSTNode<T>) node.getRight());
	}

	// AUXILIARY
	protected void rebalance(BSTNode<T> node) {
		
		int balance = calculateBalance(node);
		if (Math.abs(balance) > 1) {
			
			if (balance > 1) {
				BSTNode<T> left = (BSTNode<T>) node.getLeft();
				int b = calculateBalance(left);
				
				if (b < 0) {
					Util.leftRotation(left);
				}
				Util.rightRotation(node);
			
			} else if (balance < -1) {
				BSTNode<T> right = (BSTNode<T>) node.getRight();
				int b = calculateBalance(right);
				
				if (b > 0) {
					Util.rightRotation(right);
				}
				Util.leftRotation(node);
				
			}
		}
	}

	// AUXILIARY
	protected void rebalanceUp(BSTNode<T> node) {
		while (node != null) {
			rebalance(node);
			node = (BSTNode<T>) node.getParent();
		}
	}
}
