

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Vertex<T> implements VertexInterface<T> {
	private T label;
	private boolean visited;
	private double pathLength;
	private VertexInterface<T> previousVertex;
	private ArrayList<Edge> edgeList;
	private int betweennessValue;
	private float closenessValue;
	
	public Vertex(T vertexLabel) {
		this.label = vertexLabel;
		visited = false;
		pathLength = 0;
		previousVertex = null;
		edgeList = new ArrayList<Edge>();
		betweennessValue = 0;
		closenessValue = 0;
	}
	
	public int getBetweennessValue() {
		return betweennessValue;
	}

	public void setBetweennessValue(int betweennessValue) {
		this.betweennessValue = betweennessValue;
	}

	public float getClosenessValue() {
		return closenessValue;
	}

	public void setClosenessValue(float closenessValue) {
		this.closenessValue = closenessValue;
	}

	@Override
	public T getLabel() {
		return label;
	}

	@Override
	public void visit() {
		visited = true;
	}

	@Override
	public void unVisit() {
		visited = false;
	}

	@Override
	public boolean isVisited() {
		return visited;
	}

	@Override
	public boolean connect(VertexInterface<T> endVertex) {
		boolean result = false;
		if(!this.equals(endVertex)) {
			Iterator<VertexInterface<T>> neighbors = getNeighborIterator();
			boolean duplicateEdge = false;
			while(!duplicateEdge && neighbors.hasNext()) {
				VertexInterface<T> nextNeighbor = neighbors.next();
				if(endVertex.equals(nextNeighbor))
					duplicateEdge = true;
			}
			if(!duplicateEdge) {
				edgeList.add(new Edge(endVertex));
				result = true;
			}
		}
		return result;
	}

	@Override
	public Iterator<VertexInterface<T>> getNeighborIterator() {
		return new NeighborIterator();
	}

	@Override
	public boolean hasNeighbor() {
		return !edgeList.isEmpty();
	}

	@Override
	public VertexInterface<T> getUnvisitedNeighbor() {
		Iterator<VertexInterface<T>> neighbor = getNeighborIterator();
		VertexInterface<T> unvisited = null;
		boolean found = false;
		while(neighbor.hasNext() && !found) {
			VertexInterface<T> nextNeighbor = neighbor.next();
			if(!nextNeighbor.isVisited()) {
				unvisited = nextNeighbor;
				found = true;
			}
		}
		return unvisited;
	}

	@Override
	public void setPredecessor(VertexInterface<T> predecessor) {
		this.previousVertex = predecessor;
	}

	@Override
	public VertexInterface<T> getPredecessor() {
		return previousVertex;
	}

	@Override
	public boolean hasPredecessor() {
		return previousVertex != null;
	}

	@Override
	public void setCost(double newCost) {
		this.pathLength = newCost;
	}

	@Override
	public double getCost() {
		return pathLength;
	}
	
	@Override
	public boolean equals(Object other) {
		boolean result;
		 if ((other == null) || (getClass() != other.getClass()))
			 result = false;
		 else {
			 // The cast is safe within this else clause
			 @SuppressWarnings("unchecked")
			 Vertex<T> otherVertex = (Vertex<T>)other;
			 result = label.equals(otherVertex.label);
		 }
		 return result;
	}
	
	protected class Edge {
		private VertexInterface<T> endVertex;
		protected Edge(VertexInterface<T> endVertex) {
			this.endVertex = endVertex;
		}
		protected VertexInterface<T> getEndVertex(){
			return endVertex;
		}
	}
	
	private class NeighborIterator implements Iterator<VertexInterface<T>>{
		private Iterator<Edge> edges;
		private NeighborIterator() {
			edges = edgeList.iterator();
		}
		@Override
		public boolean hasNext() {
			return edges.hasNext();
		}

		@Override
		public VertexInterface<T> next() {
			VertexInterface<T> nextNeighbor = null;
			if(edges.hasNext()) {
				Edge edgeToNextNeighbor = edges.next();
				nextNeighbor = edgeToNextNeighbor.getEndVertex();
			}
			else
				throw new NoSuchElementException();
			return nextNeighbor;
			
		}
	}
}
