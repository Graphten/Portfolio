package comprehensive;
import java.util.HashMap;
import java.util.NoSuchElementException;

/**
 * This class represents a disjoint set using a hashmap that maps data to the representative data.
 * Implemented by the students.
 * 
 * @author Dillon Boone and Frederico Tremonti
 * @version April 14th, 2023
 *
 * @param <E> disjoint set data type.
 */
public class DisjointSetDiner<E> implements DisjointSetADT<E> {
	
	// Stores rank for each representative
	private HashMap<E, Integer> rankMap = new HashMap<>();
	// Stores all elements in the contained sets
	private HashMap<E, E> elementMap = new HashMap<>();

	@Override
	public void makeSet(E element) {
		if (!elementMap.containsKey(element)) {
			elementMap.put(element, null);
			rankMap.put(element, 1);
		}
		else
			throw new IllegalArgumentException("Element already exists.");
	}	

	@Override
	public E getRepresentative(E element) {
		if (!elementMap.containsKey(element))
			throw new NoSuchElementException("Element does not exist.");
		
		E output = element;
		
		while(elementMap.get(output) != null)
			output = elementMap.get(output);
		
		return output;
	}

	@Override
	public void union(E element1, E element2) {	
		if (!(elementMap.containsKey(element1) && elementMap.containsKey(element2)))
			throw new NoSuchElementException("Element does not exist.");
		
		E firstRep = getRepresentative(element1);
		E secondRep = getRepresentative(element2);
		
		if(!firstRep.equals(secondRep)) {
			if(rankMap.get(firstRep) > rankMap.get(secondRep)) {
				elementMap.put(secondRep, firstRep);
				rankMap.remove(secondRep);
			}
			else if(rankMap.get(firstRep) < rankMap.get(secondRep)) {
				elementMap.put(firstRep, secondRep);
				rankMap.remove(firstRep);
			}
			else {
				elementMap.put(secondRep, firstRep);
				rankMap.remove(secondRep);
				rankMap.put(firstRep, rankMap.get(firstRep) + 1);
			}
		}
		//else
		//	throw new IllegalArgumentException("Elements exist in the same set.");
	}
}
