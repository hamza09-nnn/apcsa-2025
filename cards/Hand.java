package cards;

public class Hand {
    private Card[] cards;
    private int cardCount;

    public Hand(int maxCards) {
        cards = new Card[maxCards];
        cardCount = 0;
    }

    public void add(Card card) {
        if (cardCount < cards.length) {
            cards[cardCount] = card;
            cardCount++;
        }
    }

    public int length() {
        return cardCount;
    }

    public Card get(int index) {
        if (index >= 0 && index < cardCount) {
            return cards[index];
        }
        return null; // or throw an exception
    }

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

    public String toString() {
        String handString = new String();
        for (int i = 0; i < cardCount; i++) {
            handString += cards[i].toString();
            if (i < cardCount - 1) {
                handString += " ";
            }
        }
        return handString.toString();
    }


}

