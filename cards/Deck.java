package cards;

import java.util.Random;

public class Deck {
    private Card[] cards;
    private int topCardIndex; // index of the next card to deal

    public Deck() {
        cards = new Card[52];

        int index = 0;
        for (int suit = 0; suit < 4; suit++) {
            for (int value = 0; value < 13; value++) {
                cards[index] = new Card(suit, value);
                index++;
            }
        }
        topCardIndex = 0;
    }

    public void print(int numCards) {
        for (int i = 0; i < numCards && i < cards.length; i++) {
            // print the card and a space  
            System.out.print(cards[i].toString());
            if (i < numCards - 1 && i < cards.length - 1) {
                System.out.print(" ");
            }
        }
    }

    public void shuffle() {
       Random random = new Random();

       // iterate through each card in the deck
       for( int i=0; i < cards.length; i++) {
            // generate a random index between 0 and 51
            int randomIndex = random.nextInt(cards.length);

            // swap the cards at index i and randomIndex
            swapCards(i, randomIndex);
        }
    } // end shuffle

    private void swapCards(int index1, int index2) {
        // System.out.println("Swapping cards at index " + index1 + " and " + index2);
        // System.out.println("Before swap: " + cards[index1] + " <-> " + cards[index2]);

        Card temp = cards[index1];
        cards[index1] = cards[index2];
        cards[index2] = temp;
        
        // System.out.println("After swap: " + cards[index1] + " <-> " + cards[index2]);
    }// end swapCards

    // cut the deck at the specified index
    // cards at positions from the top of the deck up to, but not including cutIndex 
    // are moved to the end of the deck.
    // pre-condition: works only on the complete deck of 52 cards
    public void cut(int cutIndex){

        if( cards.length != 52 ){
            return; // do nothing if the deck is not complete
        }

        // check if cutIndex is valid to prevent out of bounds error
        if( cutIndex < 0 || cutIndex >= cards.length ){
            return; // invalid cut index, do nothing
        }

        // move cards from the top of the deck up to, but not including cutIndex 
        // to the end of the deck.

        
        for( int i=0; i < cutIndex; i++ ){
            Card temp = cards[0];
            // shift all cards one position to the left 
            for( int j=0; j < cards.length - 1; j++ ){
                cards[j] = cards[j+1];
            }
            // place the removed card at the end of the deck
            cards[cards.length - 1] = temp;
        }

    }

    // draw returns the card at the top of the deck
    // subsequennt calls to draw shall return subsequent cards
    // when the deck is empty, draw shall return null
    public Card draw() {
        if (topCardIndex < cards.length) {
            return cards[topCardIndex++];
        } else {
            return null; // no more cards to deal
        }
    }

    
}
