package adt.linkedList.set;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class Teste {

	private SetLinkedListImpl<Integer> set;
	
	@Before
	public void setUp() {
		set = new SetLinkedListImpl<>();
		
		set.insert(1);
		set.insert(2);
		set.insert(3);
		set.insert(1);
		set.insert(5);
		set.insert(7);
		set.insert(2);
		set.insert(5);
		set.insert(7);
		
	}
	@Test
	public void testRemoveDuplicates() {
		SetLinkedListImpl<Integer> aux = set;
		aux.removeDuplicates();
		Assert.assertArrayEquals(new Integer[] {1, 2, 3, 5, 7}, aux.toArray());
	}

	@Test
	public void testUnion() {
		fail("Not yet implemented");
	}

	@Test
	public void testIntersection() {
		fail("Not yet implemented");
	}

	@Test
	public void testConcatenate() {
		fail("Not yet implemented");
	}

}
