package comprehensive;

import java.util.HashMap;
import java.util.NoSuchElementException;

/**
 * This class is a disjoint set implemented tree structures. 
 * Implemented based off lecture.
 * 
 * @author Dillon Boone and Frederico Tremonti
 * @version April 20th, 2023
 *
 * @param <E>
 */
public class DisjointSetForest<E> implements DisjointSetADT<E> {
	//ArrayList<SetNode<E>> nodeList = new ArrayList<>();
	HashMap<E, SetNode<E>> nodeMap = new HashMap<>();

	@Override
	public void makeSet(E element) {
		if(nodeMap.get(element) != null)
			throw new IllegalArgumentException("Element already exists.");
		else
			nodeMap.put(element, new SetNode<E>(element));
	}

	@Override
	public E getRepresentative(E element) {
		SetNode<E> repNode = nodeMap.get(element);
		
		if(repNode == null)
			throw new NoSuchElementException("Element does not exist.");
		
		if (repNode.getRep() != null)
			// Changes the node to point to the result of get representative called on
			// what it currently points to.
			repNode.setRep(nodeMap.get(getRepresentative(repNode.getRep().getData())));
		else
			return repNode.getData();
		
		return repNode.getRep().getData();
		
	}

	@Override
	public void union(E element1, E element2) {
		if (nodeMap.get(element1) == null || nodeMap.get(element2) == null)
			throw new NoSuchElementException("Element does not exist.");
		
		E firstRep = getRepresentative(element1);
		E secondRep = getRepresentative(element2);
		
		SetNode<E> node1 = nodeMap.get(firstRep);
		SetNode<E> node2 = nodeMap.get(secondRep);
		
		if (!firstRep.equals(secondRep))
		{
			if (node1.getRank() > node2.getRank())
				node2.setRep(node1);
			
			else if (node1.getRank() < node2.getRank())
				node1.setRep(node2);
			
			else {
				node2.setRep(node1);
				node1.setRank(node1.getRank() + 1);
			}
		}
		//else
			//throw new IllegalArgumentException("Elements are members of the same set.");
	}

	private class SetNode<E> {
		private E data;
		private SetNode<E> rep;
		private int rank;
		
		public SetNode(E data) {
			this.data = data;
			this.rep = null;
			this.rank = 1;
		}
		
		public E getData() { return this.data; }
		public SetNode<E> getRep() { return this.rep; }
		public int getRank() { return this.rank; }
		
		public void setRep(SetNode<E> rep) { this.rep = rep; }
		public void setRank(int rank) { this.rank = rank; }
	}
}
