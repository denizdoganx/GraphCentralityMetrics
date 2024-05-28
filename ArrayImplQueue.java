
public class ArrayImplQueue<T> implements QueueInterface<T> {
	private int front;
	private int rear;
	private T[] elements;
	private static final int DEFAULT_INITIAL_CAPACITY = 15000;
	public ArrayImplQueue() {
		this(DEFAULT_INITIAL_CAPACITY);
	}
	@SuppressWarnings("unchecked")
	public ArrayImplQueue(int capacity) {
		elements = (T[]) new Object[capacity];
		front = 0;
		rear = - 1;
	}
	@Override
	public void enqueue(T item) {
		if(isFull()) {
			System.out.println("Queue overflow.");
		}
		else {
			rear = (rear + 1) % elements.length;
			elements[rear] = item;
		}
	}
	@Override
	public T dequeue() {
		if(isEmpty()) {
			System.out.println("Queue is empty.");
			return null;
		}
		else {
			T returnData = elements[front];
			elements[front] = null;
			front = (front + 1) % elements.length;
			return returnData;
		}
	}
	@Override
	public T peek() {
		if(isEmpty()) {
			System.out.println("Queue is empty.");
			return null;
		}
		else {
			return elements[front];
		}
	}
	@Override
	public int size() {
		if (rear >= front) {
			return rear - front + 1;
		}
		else if (elements[front] != null) {
			return elements.length - (front - rear) + 1;
		}
		else {
			return 0;
		}
	}
	@Override
	public boolean isEmpty() {
		return size() == 0;
	}
	@Override
	public boolean isFull() {
		if (front == ( rear + 1) % elements.length && elements[front] != null && elements[rear] != null) {
			return true;
		}
		else {
			return false;
		}
	}
	
}
