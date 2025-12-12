package cards;

public class Card {
    // instance variables - also known as fields, attributes, or properties   
    // diamonds, clubs, hearts, and spades are represented by 0, 1, 2, and 3
    // 0 -> Diamonds
    // 1 -> Clubs
    // 2 -> Hearts
    // 3 -> Spades
    private final int suit;
   //Diamond (♦): \u2666, Club (♣): \u2663, Heart (♥): \u2665, Spade (♠): \u2660
    private static final String[] SUIT_SYMBOLS = {"♦", "♣", "♥", "♠"};
    //private static final String[] SUIT_SYMBOLS = {"D", "C", "H", "S"};

    // 0-1
    // 0 -> Ace 
    // 1 -> 2
    // .....
    // 9 -> 10
    // 10 -> Jack
    // 11 -> Queen
    // 12 -> King
    private final int value;
    private static final String[] VALUE_SYMBOLS = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};   

    // constructor method
    public Card(int suit, int value) {
        // clamp suit value between 0 and 3
        if(  suit < 0 ){
            suit = 0;
        } else if( suit > 3 ){
            suit = 3;
        } 
        
        //clamp card value between 0 and 12
        if( value < 0 ){
            value = 0;
        } else if( value > 12 ){
            value = 12;
        }
        
        this.suit = suit;
        this.value = value;
    }

    // accessor methods - also known as getters
    public int getValue() {
        return value;
    }    

    public String toString() {
        //suit symbol followed by value symbol
        return SUIT_SYMBOLS[suit] + VALUE_SYMBOLS[value];
    }

}
