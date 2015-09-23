
import java.util.*;
import java.awt.*;

//this class standfor a deck of cards
public class Deck extends ArrayList<Card>{
  //this store the number of suits
  private int numbersuit;
  //this store the min face
  private Card.Face minf;
  //this store the max face
  private Card.Face maxf;
  //this store the suits
  private Card.Suit[] suit;
  //this store the number of cards under each suit
  private int singleSuitNum;
  
  //constructor
  public Deck(Card.Face minFace, Card.Face maxFace,Card.Suit... suits){
    minf = minFace;
    maxf = maxFace;
    numbersuit = suits.length;
    this.suit = suits;
    
    //find out the index of max face and min face
    Card.Face[] f = {Card.Face.Ace,Card.Face.Two,Card.Face.Three,Card.Face.Four,Card.Face.Five,Card.Face.Six,Card.Face.Seven,Card.Face.Eight,Card.Face.Nine,Card.Face.Ten,Card.Face.Jack,Card.Face.Queen,Card.Face.King};
     int i = 0;
     int max = 0;
     int min = 0;
     while(i < f.length){
       if(f[i].toString().equals(minFace.toString()))
         min = i;
       if(f[i].toString().equals(maxFace.toString()))
         max = i;
       i++;
     }
     
     //store the card number under each suit
     this.singleSuitNum = max - min + 1;
          
     //create the deck
     i = 0;
     int j = min;
     int k = 0;
    Card a;
     while(i < suits.length){
       while (j < max){
         this.add(k,new Card(suits[i],f[j]));
         j++;
         k++;
       }
       j = min;
       i++;         
  }
    
  }
  
  //another constructor
  public Deck(){
    this(Card.Face.Ace,Card.Face.King,Card.Suit.Spades,Card.Suit.Hearts,Card.Suit.Diamonds,Card.Suit.Clubs);
  }
  
  //return the number of suit
  public int getNumberSuit(){
    return numbersuit;
  }
  
  //return the min face
  public Card.Face getMinFace(){
    return this.minf;
  }
  
  //return the max face
  public Card.Face getMaxFace(){
    return this.maxf;
  }
  
  //shuffles the whole dec
  public void shuffles(){
    Collections.shuffle(this);
  }
  
  //return the suit 
  public Card.Suit[] getSuitArray(){
    return this.suit;
  }
  
  //return the card number under each suit
  public int getSSN(){
    return this.singleSuitNum;
  }
}
  
  