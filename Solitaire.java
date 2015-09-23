//this class standfor the whole game
import java.util.NoSuchElementException;
import java.lang.*;
import java.util.Scanner;
import java.awt.Color;
 
public class Solitaire{

  //this field store the foundation piles
  private Pile[] p1;
  //this field store the stock
  private Pile stock;
  //this field store the tableau;
  private Pile tableau;
  //this field store the 7 active pile
  private Pile[] active;
  //this filed store the deck of cards
  private Deck dc;
  
  
  //constructor
  public Solitaire(Deck deck, int numActivePiles){
    this.dc = deck;
    p1 = new Pile[deck.getNumberSuit()];
    active = new Pile[numActivePiles];
    stock = new Pile();
  }
  
  //this game initialze the game
  public void initializeGame(){
    //shuffle the deck
    dc.shuffles();
    //move the whole deck to the stock.
    int i = 0;
    while (i < dc.size()){
      stock.addToBack(dc.get(i));
      //set side of card as false
      dc.get(i).setSide(false);
      i++;
    }
    //initialize the p1
    i = 0;
    while(i < p1.length){
      p1[i] = new Pile();
      i++;
    }
    //initialize the tableau
    tableau = new Pile();
    //distribute the card to active
    i = 0;
    int j = 0;
    while (i < active.length){
      active[i] = new Pile();
      j = 0;
      while(j < i + 1){
        stock.moveCard(active[i],false);
        j++;
      }
      active[i].getFront().getElement().setSide(true);
      i++;
    }
  }
  
  public void moveStockToTableau(){
    Pile mover = new Pile();
    if(stock.getNumber() < 3){
      int i = 0;
      while(i < stock.getNumber()){
        stock.moveCard(mover,false);
        i++;
      }
    }
    else{
    stock.moveCard(mover,false);
     stock.moveCard(mover,false);
      stock.moveCard(mover,false);
    }
      mover.flipAllCard();
      mover.movePile(tableau);
  }
  
  public void moveTableauToStock(){
    tableau.flipAllCard();
    tableau.movePile(stock);
  }
 
  public void movePileToFoundation(Pile pe) throws NoCardException,CanNotMoveCards {

    if(pe.getNumber() == 0)
      throw new NoCardException();
    else{
   //top card of pile
    Card cd = pe.getFront().getElement();
    //suits array
    Card.Suit[] s = dc.getSuitArray();
    
    int i = 0;
    int j = 0; 
    //detector to see if card is removed
    int k = 1;
    while(i < s.length){
      //in the proper suit group
      if(cd.getSuit().toString().equals(s[i].toString())){
        //no card in found. pile
        if(p1[i].getNumber() == 0){
          if(cd.getFace().getNValue() == 1){
            pe.moveCard(p1[i],true);
            k = 0;
            i = s.length;
            //last card of input pile is removed
            if(!pe.isEmpty())
            pe.getFront().getElement().setSide(true);
          }
        }                    
        else if (cd.getFace().getNValue() == p1[i].getFront().getElement().getFace().getNValue() + 1){
          pe.moveCard(p1[i],true);
          k = 0;
          i = s.length;
          if(!pe.isEmpty())
          pe.getFront().getElement().setSide(true);
        }
      }
      i++;
    }
    //if no card moved,k = 1;
    if(k == 1)
      throw new CanNotMoveCards();
    }                                      
  }
 
  //move top card of tableau on the active
  public void moveTableauToActive(Pile act) throws NoCardException,CanNotMoveCards{
 
    if(this.tableau.getNumber() == 0)
      throw new NoCardException();
    else {
     // store the first card
    Card c = tableau.getFront().getElement();
    //suits array
    Card.Suit[] s = dc.getSuitArray(); 
    int k = 1;
      if(act.getNumber() == 0){
      // active pile is empty  
        if(c.getFace().getNValue() == 13){
          tableau.moveCard(act,true);                   
          k = 0;
        }
      }
      else if(c.getFace().getNValue() == act.getFront().getElement().getFace().getNValue() - 1 && (c.getSuit().getColor() != act.getFront().getElement().getSuit().getColor())){
        tableau.moveCard(act,true);  
          k = 0;
      }
  
    //check if card is moved
    if(k == 1)
      throw new CanNotMoveCards();
    }    
  }
  

//this method move cards from active pile to active pile.
  public void moveActiveToActive(Pile pfrom, Pile pto) throws NoCardException,CanNotMoveCards{
  //check empty
    if(pfrom.getNumber() == 0 || !pfrom.getFront().getElement().getSide())
      throw new NoCardException();
    else{
     //separate out the faceup card
      int i = 0;
    
      Pile mover = new Pile();
      while(pfrom.getFront().getElement().getSide()){
        pfrom.moveCard(mover,false);
      }
      //test with target pile
      //if pile 2 is empty
      if(pto.getNumber() == 0){
        if(mover.getBack().getElement().getFace().getNValue() == 13){
          mover.movePile(pto);         
        }
        //move back to pile 1 if cannot match
        else{
          mover.movePile(pfrom);
          throw new CanNotMoveCards();
      }
      }
      //if pile 2 is not empty
      else {
        if(mover.getBack().getElement().getFace().getNValue() == pto.getFront().getElement().getFace().getNValue() - 1 && (mover.getBack().getElement().getSuit().getColor() != pto.getFront().getElement().getSuit().getColor())){
          mover.movePile(pto);        
        }
        //no match
        else{
          mover.movePile(pfrom);
          throw new CanNotMoveCards();
        }
      }
        }           
    }
  
  //this method control the game and show users how the game going
  public void playGame(){
    this.printBoard();
    //this store the scanner
  Scanner scanner = new Scanner(System.in);
  if(scanner.hasNext()){
    String input = scanner.next();
    
    if(input.charAt(0) == 'q')
      System.out.println("quit the game");
    
    else if(input.charAt(0) == 't'){
      if(input.charAt(2) == 's'){
        this.moveTableauToStock();
        this.playGame();
      }
      else if(input.charAt(2) == 'f'){
       
        try {
           this.movePileToFoundation(this.tableau);
        } catch (NoCardException e) {
          System.err.println("No Card to Move");
        } catch (CanNotMoveCards e) {
          System.err.println("Invalid Movement");
        }

        this.playGame();
      }     
      else{
        int i;
        i = Character.getNumericValue(input.charAt(2));
       
         try {
            this.moveTableauToActive(this.active[i]);
        } catch (NoCardException e) {
          System.err.println("No Card to Move");
        } catch (CanNotMoveCards e) {
          System.err.println("Invalid Movement");
        }
        this.playGame();
      }
    }
    
    else if(input.charAt(0) == 's'){
      this.moveStockToTableau();
      this.playGame();
    }
    
    else{
      if(input.charAt(2) == 'f'){
        int i = Character.getNumericValue(input.charAt(0));
       
         try {
           this.movePileToFoundation(active[i]);
        } catch (NoCardException e) {
          System.err.println("No Card to Move");
        } catch (CanNotMoveCards e) {
          System.err.println("Invalid Movement");
        }
        this.playGame();
      }
      else{
        int i = Character.getNumericValue(input.charAt(0));
        int j = Character.getNumericValue(input.charAt(2));
        
         try {
          this.moveActiveToActive(active[i],active[j]);
        } catch (NoCardException e) {
          System.err.println("No Card to Move");
        } catch (CanNotMoveCards e) {
          System.err.println("Invalid Movement");
        }
        this.playGame();
      }
    }                               
  }
  }
  
  public void printBoard(){
   //print foundation pile
    int i = 0;
    while(i < p1.length){
      System.out.print("Foundation" + i + ":" + " " );
      p1[i].printer();
      System.out.print("\n");
      i++;
    }
    
    System.out.print("\n");
    
    //print active pile
    i = 0;
    while(i < active.length){
      System.out.print("" + i + ":" + " ");
      active[i].printer();
      System.out.print("\n");
      i++;
    }
    System.out.print("\n");
    
    //print stock
    
    System.out.print("Stock: ");
    stock.printer();
    System.out.print("\n");
    
    //print tableau
    
    System.out.print("Tableau: ");
    tableau.printer();
    System.out.print("\n");
    
    System.out.println("q quit the game"); 
      System.out.println("s t move 3 cards from the stock to the tableau") ;
      System.out.println("t s move all tableau cards back to stock");
      System.out.println("t f move top tableau card to foundation") ;
      System.out.println("# f move top card from the active pile to the foundation") ;
      System.out.println("t # move top tableau card to the top of the active pile") ;
      System.out.println("# # move the sequence starting with bottom-most visible card of the first active pile to the top of the second active pile") ;
      
      System.out.print("Move: ");
    
  }
  
  //main method
  public static void main(String[] args) {
    if (args.length == 0){
      Deck d = new Deck(Card.Face.Ace, Card.Face.King, Card.Suit.Spades, Card.Suit.Hearts, Card.Suit.Diamonds, Card.Suit.Clubs);
      Solitaire s = new Solitaire(d,7);
      s.initializeGame();
      s.playGame();
    }
    else{
      int nap = Character.getNumericValue(args[0].charAt(0));
      Card.Face fmin = Card.Face.getFaceByName(args[1]);
      Card.Face fmax = Card.Face.getFaceByName(args[2]);
      Card.Suit s1 = Card.Suit.getSuitByName(args[3]);
      Card.Suit s2 = Card.Suit.getSuitByName(args[4]);
      Card.Suit s3 = Card.Suit.getSuitByName(args[5]);
      Card.Suit s4 = Card.Suit.getSuitByName(args[6]);
      Deck d = new Deck(fmin,fmax,s1,s2,s3,s4);
      Solitaire s = new Solitaire(d,nap);
      s.initializeGame();
      s.playGame();
    }
   
   
 }
      
      
      
  
   
   


    
    
    
    
    
  
  
  public class NoCardException extends NoSuchElementException{
    }
    
    public class CanNotMoveCards extends Exception{
    } 
}