package adt.linkedList.set;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class Teste {

	private SetLinkedListImpl<Integer> set;
	private SetLinkedListImpl<Integer> aux;
	
	@Before
	public void setUp() {
		set = new SetLinkedListImpl<>();
		aux = new SetLinkedListImpl<>();
		
		set.insert(1);
		set.insert(2);
		set.insert(3);
		set.insert(1);
		set.insert(5);
		set.insert(7);
		set.insert(2);
		set.insert(5);
		set.insert(7);
		
		aux.insert(5);
		aux.insert(10);
		aux.insert(25);
		aux.insert(13);
		aux.insert(4);
		aux.insert(9);
		aux.insert(10);
		aux.insert(1);
	}
	@Test
	public void testRemoveDuplicates() {
		SetLinkedListImpl<Integer> aux = set;
		aux.removeDuplicates();
		Assert.assertArrayEquals(new Integer[] {1, 2, 3, 5, 7}, aux.toArray());
	}

	@Test
	public void testUnion() {
		SetLinkedListImpl<Integer> aux = new SetLinkedListImpl<>();
		SetLinkedList<Integer> test = set.union(aux);

		Assert.assertArrayEquals(new Integer[] {1, 2, 3, 5, 7, 10, 25, 13, 4, 9}, test.toArray());
	}

	@Test
	public void testIntersection() {
		SetLinkedList<Integer> test = set.intersection(aux);
		
		Assert.assertArrayEquals(new Integer[] {1, 5}, test.toArray());
	}

	@Test
	public void testConcatenate() {
		set.concatenate(aux);
		Assert.assertArrayEquals(set.toArray(), new Integer[] {1, 2, 3, 5, 7, 10, 25, 13, 4, 9});
	}

}
