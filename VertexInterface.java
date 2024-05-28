

import java.util.Iterator;

public interface VertexInterface <T> {
	
	public T getLabel();
	
	public void visit();
	
	public void unVisit();
	
	public boolean isVisited();
	
	public boolean connect(VertexInterface<T> endVertex);
	
	public Iterator<VertexInterface<T>> getNeighborIterator();
	
	public boolean hasNeighbor();
	
	public VertexInterface<T> getUnvisitedNeighbor();
	
	public void setPredecessor(VertexInterface<T> predecessor);
	
	public VertexInterface<T> getPredecessor();
	
	public boolean hasPredecessor();
	
	public void setCost(double newCost);
	
	public double getCost();

}
