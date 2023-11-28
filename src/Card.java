public class Card {
    private char rank;
    private String suit;
    private int point;

    public Card(char newRank, String newSuit, int newPoint) {
        rank = newRank;
        suit = newSuit;
        point = newPoint;
    }

    public char getRank() {
        return rank;
    }

    public void setRank(char rank) {
        this.rank = rank;
    }

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public String toString() {
        return rank + " of " + suit;
    }
}