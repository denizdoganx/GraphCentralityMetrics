

public interface GraphAlgorithmsInterface <T> {

	public QueueInterface<T> getBreadthFirstTraversal(T origin);
	
	public QueueInterface<T> getDepthFirstTraversal(T origin);
	
	public int getShortestPath(T begin, T end, StackInterface<T> path);
	
	
}
