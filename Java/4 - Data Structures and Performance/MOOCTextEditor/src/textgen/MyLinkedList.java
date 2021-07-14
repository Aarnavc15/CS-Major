package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		head = new LLNode<E>(null);
		tail = new LLNode<E>(null);
		tail.prev = head;
		head.next = tail;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 * @return 
	 */
	
	private LLNode<E> navigator(int index){
		if(index >= this.size || index < 0) {
			throw new IndexOutOfBoundsException("Give index in range");
		}
		
		LLNode<E> currNode;
		
		if(index <= this.size/2) {
			currNode = this.head;
			for(int i = 0; i<=index; i++) {
				currNode = currNode.next;
			}
		}
		
		else {
			currNode = this.tail;
			for(int i = this.size - 1; i>=index; i--) {
				currNode = currNode.prev;
			}
		}
		
		return currNode;
	}
	
	public boolean add(E element) 
	{
		if(element.equals(null)) {
			throw new NullPointerException("Give me not a null");
		}
		LLNode<E> newNode = new LLNode<E>(element, this.tail.prev, this.tail);
		this.tail.prev.next = newNode;
		this.tail.prev = newNode;
		this.size ++;
		
		return false;
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) 
	{
		if(index >= this.size || index < 0) {
			throw new IndexOutOfBoundsException("Give index in range");
		}
		
		LLNode<E> currNode = navigator(index);
		
		return currNode.data;
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) 
	{

		LLNode<E> currNode = navigator(index);
		
		LLNode<E> newNode = new LLNode<E>(element, currNode.prev, currNode);
		currNode.prev.next = newNode;
		currNode.prev = newNode;
	}


	/** Return the size of the list */
	public int size() 
	{
		// TODO: Implement this method
		return this.size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) 
	{
		LLNode<E> currNode = navigator(index);
		currNode.prev.next = currNode.next;
		currNode.next.prev = currNode.prev;
		this.size --;
		return currNode.data;
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) 
	{
		LLNode<E> currNode = navigator(index);
		E e = currNode.data;
		currNode.data = element;
		return e;
	}   
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// TODO: Add any other methods you think are useful here
	// E.g. you might want to add another constructor

	public LLNode(E e) 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}
	
	public LLNode(E e, LLNode<E> prev, LLNode<E> next) 
	{
		this.data = e;
		this.prev = prev;
		this.next = next;
	}

}
