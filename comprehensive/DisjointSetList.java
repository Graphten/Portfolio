package comprehensive;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;

/**
 * This class is a disjoint set implemented using ArrayLists.
 * 
 * @author Dillon Boone and Frederico Tremonti
 * @version April 25th, 2023
 *
 * @param <E>
 */
public class DisjointSetList<E> implements DisjointSetADT<E> {
	HashMap<E, ArrayList<E>> listMap = new HashMap<>();

	@Override
	public void makeSet(E element) {
		if(listMap.get(element) != null)
			throw new IllegalArgumentException("Element already exists.");
		else {
			listMap.put(element, new ArrayList<E>());
			listMap.get(element).add(element);
		}
	}

	@Override
	public E getRepresentative(E element) {
		ArrayList<E> set = listMap.get(element);
		if(set == null)
			throw new NoSuchElementException("Element does not exist.");
		
		return set.get(0);
	}

	@Override
	public void union(E element1, E element2) {
		if (listMap.get(element1) == null || listMap.get(element2) == null)
			throw new NoSuchElementException("Element does not exist.");
		
		E firstRep = getRepresentative(element1);
		E secondRep = getRepresentative(element2);
		
		ArrayList<E> set1 = listMap.get(firstRep);
		ArrayList<E> set2 = listMap.get(secondRep);
		
		if (!firstRep.equals(secondRep))
		{
			if (set1.size() > set2.size()) {
				set1.addAll(set2);
				remap(set2, set1);
			}
			else {
				set2.addAll(set1);
				remap(set1, set2);
			}
		}
	}
		//else
			//throw new IllegalArgumentException("Elements are members of the same set.");
	
	/**
	 * Changes the mapping of all elements in one set to a new set.
	 * @param oldSet Previous set for elements.
	 * @param newSet New set for elements.
	 */
	private void remap(ArrayList<E> oldSet, ArrayList<E> newSet) {
		for(E element : oldSet)
			listMap.put(element, newSet);
	}
}

