

import java.util.EmptyStackException;

public class LinkedListImplStack<T> implements StackInterface<T> {
	private Node topNode;
	private int size;
	public LinkedListImplStack() {
		topNode = null;
	}
	
	@Override
	public void push(T element) {
		topNode = new Node(element, topNode);
		size++;
	}
	@Override
	public T peek() {
		if(isEmpty()) {
			throw new EmptyStackException();
		}
		else {
			return topNode.getData();
		}
	}
	@Override
	public T pop() {
		T top = peek();
		assert topNode != null;
		size--;
		topNode = topNode.getNext();
		return top;
	}
	@Override
	public int size() {
		return size;
	}
	@Override
	public boolean isEmpty() {
		return topNode == null;
	}
	@Override
	public boolean isFull() {
		return false;
	}
	@Override
	public void clear() {
		topNode = null;
	}
	private class Node {
		private Node next;
		private T data;
		private Node(T data, Node next) {
			this.next = next;
			this.data = data;
		}
		private T getData() {
			return data;
		}
		private Node getNext() {
			return next;
		}
	}
}
