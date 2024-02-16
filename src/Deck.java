// Import ArrayList and Math classes
import java.awt.*;
import java.util.ArrayList;
import java.lang.Math;

// Deck class
public class Deck {
    // Declare instance variables
    private ArrayList<Card> cards;
    private int cardsLeft;

    // Constructor
    // Takes in an array of chars, strings, and ints and creates a card for each combination
    public Deck(char[] ranks, String[] suits, int[] points, Image[] images) {
        cards = new ArrayList<Card>();
        // Two is the number of suits and ranks
        // For each suit/rank
        for (int i = 0; i < 2; i++) {
            // For each suit
//            for (String suit : suits) {
//                // Initialize a card
//                Card newCard = new Card(ranks[i], suit, points[i]);
//                // Add it to the deck
//                cards.add(newCard);
//                cardsLeft++;
//            }
            for (int j = 0; j < 13; j++) {
                // Initialize a card
                Card newCard = new Card(ranks[i], suits[j], points[i], images[j]);
                // Add it to the deck
                cards.add(newCard);
                cardsLeft++;
            }
        }
        // Shuffle the new deck
        shuffle();
    }

    // Returns true is there are no cards left in the deck
    public boolean isEmpty() {
        return cardsLeft == 0;
    }

    // Returns the number of cards left
    public int getCardsLeft() {
        return cardsLeft;
    }

    // returns a card from the deck
    public Card deal() {
        // If the deck is empty, return null
        if (cards.isEmpty()) {
            return null;
        }
        // Reduce the number of cards left in the deck
        cardsLeft--;
        // Return a card from the deck
        return cards.get(cardsLeft);
    }

    // Randomizes the order of the cards
    public void shuffle(){
        // For the number of cards in the deck
        for (int i = cardsLeft-1; i > 0; i--) {
            // Generate a random integer from 0-numberOfCardsLeft
            int randomIndex = (int) (Math.random() * i);
            // Swap card with the card at the random index
            Card temp = cards.get(randomIndex);
            cards.set(randomIndex, cards.get(i));
            cards.set(i, temp);
        }
        // Reset cards left to the number of cards in the deck
        cardsLeft = cards.size();
    }
}
