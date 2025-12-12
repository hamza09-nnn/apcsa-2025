package cards;

public class App {
    
   public static void main(String[] args) {
     // *******  test Card class *******

        Card c = new Card(1,10);
        int value = c.getValue();
        System.out.println("value is: " + value);
        
        String str = c.toString();
        System.out.println("card is: " + str);

        //CardTable table = new CardTable();

       // table.startPlaying();
    }

}
