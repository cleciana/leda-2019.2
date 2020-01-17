package roteiro;

import org.junit.Assert;

import org.junit.Test;

public class selectionTests {

	private Integer[] aa = {9, 7, 8, 1, 2, 0, 4};
	private Integer[] bb = {0, 1, 2, 4, 7, 8, 9};
	
	private Selection<Integer> ss = new Selection<Integer>();

	@Test
	public void testSort() {
		ss.sort(aa);
		Assert.assertArrayEquals(bb, aa);
		
	}

}
