import java.util.ArrayList;
import java.lang.Math;

public class Deck {
    private ArrayList<Card> cards;
    private int cardsLeft;

    public Deck(char[] ranks, String[] suits, int[] points) {
        cards = new ArrayList<Card>();
        for (char rank : ranks) {
            for (String suit : suits) {
                for (int point : points) {
                    Card newCard = new Card(rank, suit, point);
                    cards.add(newCard);
                    cardsLeft++;
                }
            }
        }
    }

    public boolean isEmpty() {
        return cardsLeft == 0;
    }

    public int getCardsLeft() {
        return cardsLeft;
    }

    public Card deal() {
        if (cards.isEmpty()) {
            return null;
        }
        cardsLeft--;
        return cards.get(cardsLeft);
    }

    public void shuffle(){
        for (int i = cardsLeft-1; i >= 0; i--) {
            int randomIndex = (int) (Math.random() * i);
            Card temp = cards.get(randomIndex);
            cards.set(randomIndex, cards.get(i));
            cards.set(i, temp);
        }
        cardsLeft = cards.size();
    }
}
