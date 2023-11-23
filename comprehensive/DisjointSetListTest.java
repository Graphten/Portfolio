package comprehensive;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests functionality implemented in DisjointSetList.
 * @author Dillon Boone and Frederico Tremonti
 * @version April 25th, 2023
 *
 */

class DisjointSetListTest {


	DisjointSetList<Integer> disjset;
	DisjointSetList<Integer> empty;
	
	@BeforeEach
	void setUp() throws Exception {
		disjset = new DisjointSetList<>();
		empty = new DisjointSetList<>();
		
		// 2 1 3
		disjset.makeSet(1);
		disjset.makeSet(2);
		disjset.makeSet(3);
		disjset.union(1, 2);
		disjset.union(2, 3);
		
		// 5 4 6
		disjset.makeSet(4);
		disjset.makeSet(5);
		disjset.makeSet(6);
		disjset.union(4, 5);
		disjset.union(4, 6);
		
		// 8 7 9 10
		disjset.makeSet(7);
		disjset.makeSet(8);
		disjset.makeSet(9);
		disjset.makeSet(10);
		disjset.union(7, 8);
		disjset.union(7, 9);
		disjset.union(8, 10);
		
		disjset.makeSet(11);
	}

	
	
//	/* In the set up include 3 sets: {1:R, 2, 3} {4:R, 5, 6} {7:R, 8, 9, 10}
//	 * 
//	 * .makeSet() - Makes new set (both for a disjoint set that has already been populated and an empty one).
//	 * 				*New set returns itself in find.
//	 * 				Throws Ill Arg Exc when element already exists.
//	 * 
	
	@Test
	void makeInEmptyDisjointSet() {
		empty.makeSet(0);
		assertEquals(0, empty.getRepresentative(0));
	}
	
	@Test
	void makeInPopulatedDisjointSet() {
		disjset.makeSet(12);
		assertEquals(12, disjset.getRepresentative(12));
	}
	
	@Test
	void makeSetElementAlreadyExists() {
		assertThrows(IllegalArgumentException.class, () -> {disjset.makeSet(11);});
	}
//	 * .getRepresentative() - Returns correct element.
//	 * 						  Throws No Such Element Exception.		
//	 * 						  Representatives returns themselves.
//	 * 						  *Representative changes after UNION (try with sets that have depth greater than 1, and
//	 * 															  single sets where the representative is no longer the rep).
//	 * 
	
	@Test
	void getRepresentativeElementDoesNotExist() {
		assertThrows(NoSuchElementException.class, () -> {disjset.getRepresentative(40);});
	}
	
//	 * .union() - Temporary rank test.
//	 * 			  *Representative changes for all members of a set.
//	 * 			  Throws No Such Element Exception if one of the elements passed in don't exist.
//	 * 			  Throws IllegalArgument if the elements exist in the same set.
//	 * 

	
	@Test
	void unionOneElementDoesNotExist() {
		assertThrows(NoSuchElementException.class, () -> {disjset.union(1, 12);});
		assertThrows(NoSuchElementException.class, () -> {disjset.union(12, 1);});
	}
	
	@Test
	void unionElementsDoNotExist() {
		assertThrows(NoSuchElementException.class, () -> {disjset.union(14, 12);});
	}
	
//	@Test
//	void unionElementsInSameSet() {
//		assertThrows(IllegalArgumentException.class, () -> {disjset.union(1, 2);});
//	}
	
	@Test
	void unionCombinesSets() {
		disjset.union(1, 4);
		disjset.union(7, 1);
		
		assertEquals(disjset.getRepresentative(2), disjset.getRepresentative(10));
	}
	
//	@Test
//	void rankTest() {
//		assertEquals(2, disjset.rankMap.get(1));
//		disjset.union(1, 4);
//		assertEquals(3, disjset.rankMap.get(1));
//	}

}
