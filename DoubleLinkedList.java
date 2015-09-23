// Linhan Li private work 
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A doubly linked linked list.
 */
public class DoubleLinkedList<T> {
  /** a reference to the first node of the double linked list */
  private DLNode<T> front;
  
  /** a reference to the last node of a double linked list */
  private DLNode<T> back;
  
  /** Create an empty double linked list. */
  public DoubleLinkedList() {
    front = back = null;
  }
  
  /** 
   * Returns true if the list is empty.
   * @return  true if the list has no nodes
   */
  public boolean isEmpty() {
    return (getFront() == null);
  }
  
  /**
   * Returns the reference to the first node of the linked list.
   * @return the first node of the linked list
   */
  protected DLNode<T> getFront() {
    return front;
  }
  
  
  /**
   * Sets the first node of the linked list.
   * @param node  the node that will be the head of the linked list.
   */
  protected void setFront(DLNode<T> node) {
    front = node;
  }
  
  /**
   * Returns the reference to the last node of the linked list.
   * @return the last node of the linked list
   */
  protected DLNode<T> getBack() {
    return back;
  }
  
  /**
   * Sets the last node of the linked list.
   * @param node the node that will be the last node of the linked list
   */
  protected void setBack(DLNode<T> node) {
    back = node;
  }
  
  /*----------------------------------------*/
  /* METHODS TO BE ADDED DURING LAB SESSION */
  /*----------------------------------------*/
  
  /**
   * Add an element to the head of the linked list.
   * @param element  the element to add to the front of the linked list
   */
  public void addToFront(T element) {
      
    DLNode<T> nh = new DLNode<T>(element, null, this.getFront());
    if (this.isEmpty())
      this.setBack(nh);
    this.setFront(nh);

  }
  
  /**
   * Add an element to the tail of the linked list.
   * @param element  the element to add to the tail of the linked list
   */
  public void addToBack(T element) {
    DLNode<T> nb = new DLNode<T>(element,this.getBack(),null);
    if(this.isEmpty())
      this.setFront(nb);
    this.setBack(nb);
  }

  /**
   * Remove and return the element at the front of the linked list.
   * @return the element that was at the front of the linked list
   * @throws NoSuchElementException if attempting to remove from an empty list
   */
  public T removeFromFront() throws NoSuchElementException{
    if(isEmpty())
      throw new NoSuchElementException();
    T element = this.getFront().getElement();
    if(this.getFront().getNext() != null){
    this.getFront().getNext().setPrevious(null);
    this.setFront(this.getFront().getNext());
    }
    else{
      this.setFront(null);
      this.setBack(null);
    }
    return element;
  }
  
  /**
   * Remove and return the element at the back of the linked list.
   * @return the element that was at the back of the linked list
   * @throws NoSuchElementException if attempting to remove from an empty list
   */
  public T removeFromBack() {
    if(isEmpty())
      throw new NoSuchElementException();
     T element = this.getBack().getElement();
     if(this.getBack().getPrevious() != null){
       this.getBack().getPrevious().setNext(null);
       this.setBack(this.getBack().getPrevious());
     }
     else{
       this.setFront(null);
       this.setBack(null);
     }
     return element;
  }
     
  }

