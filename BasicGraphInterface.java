

public interface BasicGraphInterface <T> {
	
	public boolean addVertex(T vertexLabel);
	
	public boolean addEdge(T begin, T end);
	
	public boolean hasEdge(T begin, T end);
	
	public boolean isEmpty();
	
	public int numberOfVertices();
	
	public int numberOfEdges();
	
	public void clear();
}