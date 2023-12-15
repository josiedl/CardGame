// Import ArrayList class
import java.util.ArrayList;

// Player class
public class Player {
    // Declare instance variables
    private String name;
    private ArrayList<Card> hand;
    private int points;

    // Constructors
    // Takes in name, sets points to 0
    public Player(String name) {
        this.name = name;
        points = 0;
    }
    // Takes in name and hand, sets points to 0
    public Player(String name, ArrayList<Card> hand) {
        this.name = name;
        this.hand = hand;
        points = 0;
    }

    // Returns the name
    public String getName() {
        return name;
    }

    // Returns the hand
    public ArrayList<Card> getHand() {
        return hand;
    }

    // Returns the points
    public int getPoints() {
        return points;
    }

    // Takes in an int and adds to points
    public void addPoints(int morePoints) {
        points += morePoints;
    }

    // Takes in a card and adds to hand
    public void addCard(Card card) {
        hand.add(card);
    }

    // Returns a string of the player's information
    public String toString() {
        return name + " has " + points + " points\n" + name + "'s cards: " + hand;
    }
}