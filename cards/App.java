package cards;

public class App {
    
   public static void main(String[] args) {
     // *******  test Card class *******

        /*Card c = new Card(1,10);
        int value = c.getValue();
        System.out.println("value is: " + value);
        
        String str = c.toString();
        System.out.println("card is: " + str);
        */
       // ****** test Deck class *******
        /*System.out.println();
        System.out.println("******* Testing Deck class *******");

        // A deck is constructed in the default order
        System.out.println("Creating a new deck...");
        Deck d = new Deck(); 
        // print the first 5 cards in the deck - expcted "DA D2 D3 D4 D5"
        d.print(5);
        System.out.println();

        Card top = d.draw();
        System.out.println("top card: " + top);
        top = d.draw();
        System.out.println("next card: " + top);

        // shuffle the deck
        System.out.println("Shuffling the deck...");
        d.shuffle();

        // print output should show values different than the default order
        d.print(5);
        System.out.println();

        // A new deck constructed in the default order
        System.out.println("Creating a new deck...");
        d = new Deck();

        // print the first 5 cards in the deck - expcted "DA D2 D3 D4 D5"
        d.print(5);
        System.out.println();

        // cut at index 2 
        // DA, D2 -> moved to the end of the deck
        System.out.println("Cutting the deck at index 2...");
        d.cut(2);

        // print the first 5 cards in the deck - expected "D3 D4 D5 D6 D7"
        System.out.println("Five cards after cut at index 2:");
        d.print(5);
        System.out.println();*/

        CardTable table = new CardTable();

        table.startPlaying();
    }

}
