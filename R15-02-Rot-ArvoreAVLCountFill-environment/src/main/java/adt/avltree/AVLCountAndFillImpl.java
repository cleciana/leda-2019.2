package adt.avltree;

import adt.bst.BSTNode;
import adt.bt.Util;

public class AVLCountAndFillImpl<T extends Comparable<T>> extends AVLTreeImpl<T> implements AVLCountAndFill<T> {

	private int LLcounter;
	private int LRcounter;
	private int RRcounter;
	private int RLcounter;

	public AVLCountAndFillImpl() {
		super();
	}

	@Override
	protected void rebalanceUp(BSTNode<T> node) {
		while (node != null) {
			this.rebalance(node);
			node = (BSTNode<T>) node.getParent();
		}
	}

	@Override
	protected void rebalance(BSTNode<T> node) {
		int balance = calculateBalance(node);
		if (Math.abs(balance) > 1) {

			if (balance > 1) {
				BSTNode<T> left = (BSTNode<T>) node.getLeft();
				int b = calculateBalance(left);

				if (b < 0) {
					Util.leftRotation(left);
					this.LRcounter += 1;
				} else {
					this.RRcounter += 1;
				}
				root = Util.rightRotation(node);

			} else if (balance < -1) {
				BSTNode<T> right = (BSTNode<T>) node.getRight();
				int b = calculateBalance(right);

				if (b > 0) {
					Util.rightRotation(right);
					this.RLcounter += 1;
				} else {
					this.LLcounter += 1;
				}
				root = Util.leftRotation(node);
			}
		}
	}

	@Override
	public int LLcount() {
		return LLcounter;
	}

	@Override
	public int LRcount() {
		return LRcounter;
	}

	@Override
	public int RRcount() {
		return RRcounter;
	}

	@Override
	public int RLcount() {
		return RLcounter;
	}

	@Override
	public void fillWithoutRebalance(T[] array) {
		
	}

}
