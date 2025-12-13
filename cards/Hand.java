package cards;

public class Hand {
    // Using array to store cards in a hand
    // array size will be set in the constructor to maximum cards in the hand 
    private Card[] cards;
    // cardCount represents cards available in the hand (we can't resize the array)
    // gets incremented by add() and decremented by remove() method.
    private int cardCount;

    // create a hand and specify maximum number of cards in the hand
    public Hand(int maxCards) {
        cards = new Card[maxCards];
        cardCount = 0;
    }

    // adds one card to the hand
    // checks if hand is already full
    // does nothing if hand is full -- ignores the card passed as parameter
    public void add(Card card) {
        if (cardCount < cards.length) {
            cards[cardCount] = card;
            cardCount++;
        }
    }

    // returns the number of cardds currently stored in the hand
    public int length() {
        return cardCount;
    }

    // returns the card with the specified index. does not remove the card. hand remains unchanged
    // returns null if hand is empty or index is invalid
    public Card get(int index) {
        if (index >= 0 && index < cardCount) {
            return cards[index];
        }
        return null;
    }

    // removes the card with the specified index from the hand and returns the removed card
    // hand length is reduced by one
    // returns null if hand is empty or index is invalid
    public Card remove(int index) {
        if (index >= 0 && index < cardCount) {
            Card removedCard = cards[index];
            // Shift cards to fill the gap
            for (int i = index; i < cardCount - 1; i++) {
                cards[i] = cards[i + 1];
            }
            cards[cardCount - 1] = null; // Clear the last card
            cardCount--;
            return removedCard;
        }
        return null; // or throw an exception
    }

    // returns a string representation of all the cards in the hand
    public String toString() {
        String handString = new String();
        for (int i = 0; i < cardCount; i++) {
            handString += cards[i].toString();
            // do not add a space after the last card
            if (i < cardCount - 1) {
                handString += " ";
            }
        }
        return handString;
    }


}

