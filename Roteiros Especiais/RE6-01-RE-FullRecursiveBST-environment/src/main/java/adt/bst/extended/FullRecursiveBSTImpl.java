package adt.bst.extended;

import java.util.ArrayList;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.BTNode;

public class FullRecursiveBSTImpl<T extends Comparable<T>> extends BSTImpl<T> implements FullRecursiveBST<T> {

	/**
	 * Sobrescreva este metodo usando recursao.
	 */
	@Override
	public BSTNode<T> maximum() {
		return max(root);
	}

	private BSTNode<T> max(BSTNode<T> node) {
		if (node.getRight().isEmpty())
			return node;

		return max((BSTNode<T>) node.getRight());
	}

	/**
	 * Sobrescreva este metodo usando recursao.
	 */
	@Override
	public BSTNode<T> minimum() {
		return min(root);
	}

	private BSTNode<T> min(BSTNode<T> node) {
		if (node.getLeft().isEmpty())
			return node;

		return min((BSTNode<T>) node.getLeft());
	}

	/**
	 * Sobrescreva este metodo usando recursao. Quando um no tem filho a direita
	 * entao o sucessor sera o minimum do filho a direita. Caso contrario o sucessor
	 * sera o primeiro ascendente a ter um valor maior.
	 */
	@Override
	public BSTNode<T> sucessor(T element) {
		BSTNode<T> node = search(element);

		if (!node.isEmpty())
			return sucessor(node);

		return new BSTNode<T>();
	}

	private BSTNode<T> sucessor(BSTNode<T> node) {
		if (!node.getRight().isEmpty())
			return min((BSTNode<T>) node.getRight());

		return maiorAcima(node, node.getData());
	}

	private BSTNode<T> maiorAcima(BSTNode<T> node, T e) {
		BSTNode<T> parent = (BSTNode<T>) node.getParent();

		if (parent != null) {
			if (node.getParent().getData().compareTo(e) > 0)
				return (BSTNode<T>) node.getParent();

			return maiorAcima((BSTNode<T>) node.getParent(), e);
		}
		return new BSTNode<T>();
	}

	/**
	 * Sobrescreva este metodo usando recursao. Quando um no tem filho a esquerda
	 * entao o predecessor sera o maximum do filho a esquerda. Caso contrario o
	 * predecessor sera o primeiro ascendente a ter um valor menor.
	 */
	@Override
	public BSTNode<T> predecessor(T element) {
		BSTNode<T> node = search(element);

		if (!node.isEmpty())
			return predecessor(node);

		return new BSTNode<T>();
	}

	private BSTNode<T> predecessor(BSTNode<T> node) {
		if (!node.getLeft().isEmpty())
			return max((BSTNode<T>) node.getLeft());

		return menorAcima(node, node.getData());
	}

	private BSTNode<T> menorAcima(BSTNode<T> node, T e) {
		BSTNode<T> parent = (BSTNode<T>) node.getParent();

		if (parent != null) {
			if (node.getParent().getData().compareTo(e) < 0)
				return (BSTNode<T>) node.getParent();

			return menorAcima((BSTNode<T>) node.getParent(), e);
		}
		return new BSTNode<T>();
	}

	@SuppressWarnings("unchecked")
	@Override
	public T[] elementsAtDistance(int k) {
		ArrayList<T> result = new ArrayList<T>();
		elements(root, result, k-1, 0);
		
		
		return (T[]) result.toArray(new Comparable[result.size()]);
	}

	private void elements(BSTNode<T> node, ArrayList<T> array, int k, int cont) {
		if (node != null && !node.isEmpty()) {
			if (cont == k) {
				adiciona(node.getLeft());
			}
		}
		elements((BSTNode<T>) node.getLeft(), array, k, cont+1);
	}

	private void adiciona(BTNode<T> left) {
		// TODO Auto-generated method stub
		
	}
}

















