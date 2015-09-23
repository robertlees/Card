//Linhan Li, this class standfor a particular card
import java.awt.*;
public class Card {
  
  //this field store the suit of card
  private Suit csuit;
  //this field store the face of card
  private Face cface;
  //this store the face up or down
  private boolean cside;
  
  //constructor,each card created with facing back
  public Card(Card.Suit cs, Card.Face cf){
    this.csuit = cs;
    this.cface = cf;
    this.cside = false;
  }
  
  //this method get suit
  public Suit getSuit(){
    return this.csuit;
  }
  
  //this method get face
  public Face getFace(){
    return this.cface;
  }
  
  //this method get up or down
  public boolean getSide(){
    return this.cside;
  }
  
  //this method set the side
  public void setSide(boolean si){
    this.cside = si;
  }
  
  //this method standfor string representation of a card
  public String toString(){
    if (this.cside)
      return getSuit().toString() + " " + getFace().toString();
    else
      return "Card Back";
  }
  
  
    
  //this method standfor the suit of one card
 enum Suit {
  Spades("Spades"), Hearts("Hearts"), Diamonds("Diamonds"), Clubs("Clubs");

  //store the name of the suit
  private String name;

  //constructor take the name of suit
  private Suit(String name) {
    this.name = name;
  }

  //get the color of one card
  public Color getColor(){
    if(this.name.equals("Hearts") ||  this.name.equals("Diamonds"))
         return Color.RED;
    else
      return Color.BLACK;
  }
  
  //return the name of suit
  public String toString(){
    return this.name;
  }
  
  //get a new suit
  public static Suit getSuitByName(String n){
   if(n.equals("Spades"))
     return Suit.Spades;
   else if(n.equals("Hearts"))
     return Suit.Hearts;
   else if(n.equals("Diamonds"))
     return Suit.Diamonds;
   else
     return Suit.Clubs;             
  }
       
  
}

  //this class standfor face of a card
  enum Face{
    Ace("1"),Two("2"),Three("3"),Four("4"),Five("5"),Six("6"),Seven("7"),Eight("8"),Nine("9"),Ten("10"),Jack("J"),Queen("Q"),King("K");
    
    //store the name of face
    private String name;
    
    //constructor
    private Face(String name){
      this.name = name;
    }
    
    //give string representation of a face
    public String toString(){
      return this.name();
    }
    
    //give back a new face
    public static Face getFaceByName(String s){
      if (s.equals("1"))
        return Face.Ace;
      else if(s.equals("2"))
        return Face.Two;
      else if(s.equals("3"))
        return Face.Three;
      else if(s.equals("4"))
        return Face.Four;
      else if(s.equals("5"))
        return Face.Five;
      else if(s.equals("6"))
        return Face.Six;
      else if(s.equals("7"))
        return Face.Seven;
      else if(s.equals("8"))
        return Face.Eight;
      else if(s.equals("9"))
        return Face.Nine;
      else if(s.equals("10"))
        return Face.Ten;
      else if(s.equals("J"))
        return Face.Jack;
      else if(s.equals("Q"))
        return Face.Queen;
      else 
        return Face.King;
    }
    
    //this method shows numeric represent of the face
    public int getNValue(){
      
        if (this.toString().equals("J"))
          return 11;
        else if(this.toString().equals("Q"))
          return 12;
        else if(this.toString().equals("K"))
          return 13;
      return Character.getNumericValue(this.toString().charAt(0));
    }
              
    
  }
  
}
  