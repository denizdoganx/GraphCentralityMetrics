

public interface StackInterface <T> {

	public void push(T element);
	
	public T peek();
	
	public T pop();
	
	public int size();
	
	public boolean isEmpty();
	
	public boolean isFull();
	
	public void clear();
	
}
