//this class standfor a pile of cards
public class Pile extends DoubleLinkedList<Card>{
   
 //this method make all cards face down by go through the linked list
  public void flipAllCard(){
   DLNode<Card> ptr = this.getFront();
   while (ptr != null){
     Card e = ptr.getElement();
     e.setSide(!e.getSide());
     ptr = ptr.getNext();
   }
  }
   
   //this method move certain number of cards from top of one pile to front or back of another pile
   public void moveCard(Pile pto,boolean ForB){     
      if(ForB)
     pto.addToFront(this.getFront().getElement());
      else
        pto.addToBack(this.getFront().getElement());
      this.removeFromFront();
     
   }
   
   //move pile to top of another pile to form a new pile,assuming from pile is not empty
   public void movePile(Pile pto){
     if(pto.isEmpty()){
       pto.setFront(this.getFront());
       pto.setBack(this.getBack());
     }
     else{
     pto.getFront().setPrevious(this.getBack());
     this.getBack().setNext(pto.getFront());
     pto.setFront(this.getFront());
   }
   }
   
   //this method detect how many cards left in the pile
   public int getNumber(){
     int counter = 0;
     DLNode<Card> ptr = this.getFront();
     while(ptr != null){
       counter++;
       ptr = ptr.getNext();
     }
     return counter;
   }
   
   //this method print the pile
   public void printer(){
    DLNode<Card> ptr = this.getBack();
     while(ptr != null){
       System.out.print(ptr.getElement().toString() + "  ");
       ptr = ptr.getPrevious();
     }
   }
   
     
  
}
  
    
    
  