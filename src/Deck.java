import java.util.ArrayList;

public class Deck {
    private ArrayList<Card> cards;
    private int cardsLeft;

    public Deck(char[] rank, String[]suit, int[]point) {
        cards = new ArrayList<Card>();
        // DON'T KNOW HOW TO YET
        for (int i = 0; i < 5; i++) {
            Card newCard = new Card(rank[i], suit[i], point[i]);
            cards.add(newCard);
        }
    }

    public boolean isEmpty() {
        return cardsLeft == 0;
    }

    public int getCardsLeft() {
        return cardsLeft;
    }

    public Card deal() {
        // LEFT OFF HERE
        return null;
    }
}
