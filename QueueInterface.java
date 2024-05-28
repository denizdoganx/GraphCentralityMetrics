

public interface QueueInterface <T> {

	public void enqueue(T item);
	
	public T dequeue();
	
	public T peek();
	
	public int size();
	
	public boolean isEmpty();
	
	public boolean isFull();
	
}