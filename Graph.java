

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class Graph<T> implements GraphInterface<T> {
	
	private int numOfEdges;
	private HashMap<T, VertexInterface<T>> vertices;
	
	//closeness and betweenness values are defined as a property of the class
	private VertexInterface<T> betweennessCentrality;
	private int valueOfBetweenness;
	private VertexInterface<T> closenessCentrality;
	private double valueOfCloseness;
	
	public Graph() {
		numOfEdges = 0;
		vertices = new HashMap<>();
		betweennessCentrality = null;
		closenessCentrality = null;
		valueOfBetweenness = 0;
		valueOfCloseness = 0;
	}
	
	@Override
	public boolean addVertex(T vertexLabel) {
		VertexInterface<T> addOutcome = null;
		if(!vertices.containsKey(vertexLabel))
			addOutcome = vertices.put(vertexLabel, new Vertex<>(vertexLabel));
		return addOutcome == null;
	}

	@Override
	public boolean addEdge(T begin, T end) {
		boolean result = false;
		VertexInterface<T> beginVertex = vertices.get(begin);
		VertexInterface<T> endVertex = vertices.get(end);
		if ( (beginVertex != null) && (endVertex != null) ) {
			result = beginVertex.connect(endVertex);
			endVertex.connect(beginVertex);
		}
		if (result)
			numOfEdges++;
		return result;
	}

	@Override
	public boolean hasEdge(T begin, T end) {
		return numOfEdges != 0;
	}

	@Override
	public boolean isEmpty() {
		return vertices.isEmpty();
	}

	@Override
	public int numberOfVertices() {
		return vertices.size();
	}

	@Override
	public int numberOfEdges() {
		return numOfEdges;
	}

	@Override
	public void clear() {
		vertices.clear();
	}
	
	@Override
	public QueueInterface<T> getBreadthFirstTraversal(T origin) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public QueueInterface<T> getDepthFirstTraversal(T origin) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getShortestPath(T begin, T end, StackInterface<T> path) {
		//shortest path algorithm is arranged according to BFS
		resetVertices();
		boolean done = false;
		QueueInterface<VertexInterface<T>> vertexQueue = new ArrayImplQueue<>();
		VertexInterface<T> beginVertex = vertices.get(begin);
		VertexInterface<T> endVertex = vertices.get(end);
		beginVertex.visit();
		vertexQueue.enqueue(beginVertex);
		while(!vertexQueue.isEmpty() && !done) {
			VertexInterface<T> frontVertex = vertexQueue.dequeue();
			Iterator<VertexInterface<T>> neighbors = frontVertex.getNeighborIterator();
			while(neighbors.hasNext() && !done) {
				VertexInterface<T> nextNeighbor = neighbors.next();
				if(!nextNeighbor.isVisited()) {
					nextNeighbor.visit();
					nextNeighbor.setPredecessor(frontVertex);
					nextNeighbor.setCost(frontVertex.getCost() + 1);
					vertexQueue.enqueue(nextNeighbor);
				}
				if(nextNeighbor.equals(endVertex))
					done = true;
			}
		}
		int pathLength = (int)endVertex.getCost();
		VertexInterface<T> tempVertex = endVertex;
		path.push(tempVertex.getLabel());
		while(tempVertex.hasPredecessor()) {
			tempVertex = tempVertex.getPredecessor();
			path.push(tempVertex.getLabel());
		}
		return pathLength;
	}
	
	private void calculateBetweennessAndClosenessForAllElementsInGraph() {
		//Calculating the necessary metrics for all elements in the graph
		Set<T> setOfAllValues = vertices.keySet();
		Vertex<T> outterElement;
		Vertex<T> innerElement;
		StackInterface<T> shortestPath;
		int tempPathLength;
		@SuppressWarnings("unchecked")
		T[] arrayOfVertex = (T[]) setOfAllValues.toArray();
		for(int i = 0;i < arrayOfVertex.length; i++) {
			T label1 = arrayOfVertex[i];
			for(int j = i + 1; j < arrayOfVertex.length; j++) {
				T label2 = arrayOfVertex[j];
				shortestPath = new LinkedListImplStack<>();
				tempPathLength = getShortestPath(label1, label2, shortestPath);
				while(!shortestPath.isEmpty()) { 
					T passedVertexLabel = shortestPath.pop();
					Vertex<T> passedVertex = (Vertex<T>) vertices.get(passedVertexLabel);
					passedVertex.setBetweennessValue(passedVertex.getBetweennessValue() + 1);
				}
				innerElement = (Vertex<T>) vertices.get(label2);
				outterElement = (Vertex<T>) vertices.get(label1);
				innerElement.setClosenessValue(innerElement.getClosenessValue() + tempPathLength);
				outterElement.setClosenessValue(outterElement.getClosenessValue() + tempPathLength);
			}
		}
	}
	
	public void findBetweennessAndClosenessOfGraph() {
		calculateBetweennessAndClosenessForAllElementsInGraph();
		//After all metrics have been calculated,
		//the center of range and closeness is found for the graph.
		Collection<VertexInterface<T>> setOfAllValues = vertices.values();
		Iterator<VertexInterface<T>> vertexIterator = setOfAllValues.iterator();
		valueOfCloseness = Integer.MAX_VALUE;
		while(vertexIterator.hasNext()) {
			Vertex<T> next = (Vertex<T>) vertexIterator.next();
			int tempBetweennessValue = next.getBetweennessValue();
			float tempClosenessValue = next.getClosenessValue();
			if(tempBetweennessValue > valueOfBetweenness) {
				valueOfBetweenness = tempBetweennessValue;
				betweennessCentrality = next;
			}
			if(tempClosenessValue != 1 && valueOfCloseness > tempClosenessValue) {
				valueOfCloseness = tempClosenessValue;
				closenessCentrality = next;
			}
		}
		valueOfCloseness = 1 / valueOfCloseness;
	}
	
	public T getBetweennessCentrality() {
		return betweennessCentrality.getLabel();
	}

	public int getValueOfBetweenness() {
		return valueOfBetweenness;
	}

	public T getClosenessCentrality() {
		return closenessCentrality.getLabel();
	}

	public double getValueOfCloseness() {
		return valueOfCloseness;
	}

	public void showBetweenessValues() {
		Set<T> setOfAllValues = vertices.keySet();
		@SuppressWarnings("unchecked")
		T[] arrayOfVertex = (T[]) setOfAllValues.toArray();
		for(int i = 0;i < arrayOfVertex.length; i++) {
			int x = ((Vertex<T>) (vertices.get(arrayOfVertex[i]))).getBetweennessValue();
			float y = ((Vertex<T>) (vertices.get(arrayOfVertex[i]))).getClosenessValue();
			System.out.println(arrayOfVertex[i] + ": Betweenness -> " + x + "\tCloseness -> " + y);
		}
			
	}
	
	private void resetVertices() {
		//Command that initializes all nodes before path finding
		Collection<VertexInterface<T>> setOfAllValues = vertices.values();
		Iterator<VertexInterface<T>> vertexIterator = setOfAllValues.iterator();
		while(vertexIterator.hasNext()) {
			VertexInterface<T> next = vertexIterator.next();
			next.setCost(0);
			next.setPredecessor(null);
			next.unVisit();
		}
	}
}
