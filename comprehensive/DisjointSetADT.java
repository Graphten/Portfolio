package comprehensive;

/**
 * Interface for the Disjoint Set ADT.
 * @author Dillon Boone and Frederico Tremonti
 * @version April 14, 2023
 * @param <E> Type of element stored in the contained sets.
 */
public interface DisjointSetADT<E> {
	/**
	 * Make a new set containing only one element that isn't contained in any existing sets.
	 * @param element Element to be contained in this set.
	 * @throws IllegalArgumentException if element already exists.
	 */
	public void makeSet(E element);
	
	/**
	 * Gets the representative element of the set containing this element.
	 * @param element Element whose representative is being retrieved.
	 * @return Representative of the set containing element.
	 * @throws NoSuchElementException if element does not exist.
	 */
	public E getRepresentative(E element);
	
	/**
	 * Unifies the two sets containing the parameter elements. If the two elements are not in the same set then their sets are unified, otherwise nothing is done.
	 * @param element1 First element.
	 * @param element2 Second element.
	 * @throws NoSuchElementException if at least one of the elements does not exist.
	 * @throws IllegalArgumentException if elements exist in the same set.
	 */
	public void union (E element1, E element2);
}
