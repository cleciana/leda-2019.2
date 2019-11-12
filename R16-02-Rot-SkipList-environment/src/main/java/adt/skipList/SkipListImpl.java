package adt.skipList;

public class SkipListImpl<T> implements SkipList<T> {

	protected SkipListNode<T> root;
	protected SkipListNode<T> NIL;

	protected int maxHeight;

	protected double PROBABILITY = 0.5;

	public SkipListImpl(int maxHeight) {
		this.maxHeight = maxHeight;
		root = new SkipListNode(Integer.MIN_VALUE, maxHeight, null);
		NIL = new SkipListNode(Integer.MAX_VALUE, maxHeight, null);
		connectRootToNil();
	}

	/**
	 * Faz a ligacao inicial entre os apontadores forward do ROOT e o NIL Caso
	 * esteja-se usando o level do ROOT igual ao maxLevel esse metodo deve conectar
	 * todos os forward. Senao o ROOT eh inicializado com level=1 e o metodo deve
	 * conectar apenas o forward[0].
	 */
	private void connectRootToNil() {
		for (int i = 0; i < maxHeight; i++) {
			root.forward[i] = NIL;
		}
	}

	@Override
	public void insert(int key, T newValue, int height) {
		SkipListNode<T>[] update = new SkipListNode[this.maxHeight];

		SkipListNode<T> aux = this.root;
		for (int i = this.maxHeight - 1; i >= 0; i--) {
			while (aux.forward[i] != null && aux.forward[i].getKey() < key) {
				aux = aux.forward[i];
			}
			update[i] = aux;
		}
		aux = aux.forward[0];

		if (aux.getKey() == key) {
			aux.setValue(newValue);
		} else {

			aux = new SkipListNode<T>(key, height, newValue);
			for (int i = 0; i < height; i++) {
				aux.forward[i] = update[i].forward[i];
				update[i].forward[i] = aux;
			}
		}
	}

	@Override
	public void remove(int key) {
		SkipListNode<T>[] update = new SkipListNode[this.maxHeight];
		SkipListNode<T> aux = this.root;

		for (int i = this.maxHeight - 1; i >= 0; i--) {
			while (aux.forward[i] != null && aux.forward[i].getKey() < key) {
				aux = aux.forward[i];
			}
			update[i] = aux;
		}
		aux = aux.forward[0];

		if (aux.getKey() == key) {

			int index = 0;
			while (index < this.maxHeight && update[index].forward[index] == aux) {
				update[index].forward[index] = aux.forward[index];
				index++;
			}
		}
	}

	@Override
	public int height() {
		if (root.forward[0].equals(NIL))
			return 0;

		SkipListNode<T> node = root;
		int i = this.maxHeight -1;

		while (node.getForward(i) == NIL && i >= 0) {
			i--;
			node = node.getForward(i);
		}
		return i;
	}

	@Override
	public SkipListNode<T> search(int key) {
		int h = height();
		SkipListNode<T> node = root;

		for (h--; h >= 0;) {
			while (node.getForward(h).getKey() < key) {
				node = node.getForward(h);
			}
		}
		node = node.forward[1];
		if (node.key == key)
			return node;
		return null;
	}

	@Override
	public int size() {
		return size(root.getForward(0));
	}

	private int size(SkipListNode<T> node) {
		if (node == null || node.equals(NIL))
			return 0;
			
		return 1 + size(node.getForward(0));
	}

	@Override
	public SkipListNode<T>[] toArray() {
		SkipListNode<T>[] retorno = new SkipListNode[size() + 2];
		SkipListNode<T> node = root;

		for (int i = 0; i < retorno.length; i++) {
			retorno[i] = node;
			node = node.getForward(0);
		}
		return retorno;
	}

}
