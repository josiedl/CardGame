import java.awt.*;

// Card class
public class Card {
    // Declare instance variables
    private char rank;
    private String suit;
    private int point;
    private Image image;
    private GameViewer window;
    // Magic numbers
    private final int CARD_WIDTH = 100;
    private final int CARD_HEIGHT = 150;

    // Constructor
    // Takes in a char, String, int, image, and GameViewer to initialize a card's values
    public Card(char newRank, String newSuit, int newPoint, Image newImage, GameViewer window) {
        rank = newRank;
        suit = newSuit;
        point = newPoint;
        image = newImage;
        this.window = window;
    }

    // Returns the rank
    public char getRank() {
        return rank;
    }

    // Sets the rank to a given char
    public void setRank(char rank) {
        this.rank = rank;
    }

    // Returns the suit
    public String getSuit() {
        return suit;
    }

    // Sets the suit to a given String
    public void setSuit(String suit) {
        this.suit = suit;
    }

    // Returns the point
    public int getPoint() {
        return point;
    }

    // Sets the point to a given in
    public void setPoint(int point) {
        this.point = point;
    }

    // Returns a string with the rank and suit of the card
    public String toString() {
        return rank + " of " + suit;
    }

    // Draws itself
    public void draw(Graphics g, int x, int y) {
        g.drawImage(this.image, x, y, CARD_WIDTH, CARD_HEIGHT, window);
    }
}