import java.util.Scanner;

public class Game {
    // Instance Variables
    private Player player1;
    private Player player2;
    private Deck deck;
    Scanner input = new Scanner(System.in);
    String name1 = input.nextLine();
    System.out.println("Player 1 enter a name: ");
    String name2 = input.nextLine();
    System.out.println("Player 2 enter a name: ");
    // Constructor
    public Game() {
        char ranks[] = {'1', '2', '3', '4', '5', '6', '7', '8', '9',};
        String suits[] = {"Yellow Tang", "Angelfish", "Purple Tang",
                            "Clownfish", "Angler Fish", "Swordfish", "Whale",
                                "Shark", "Seahorse", "Dolphin", "Jellyfish",
                                    "Flounder", "Octopus"};
        int points[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        deck = new Deck(ranks[], suits[], points[]);
        player1 = new Player(name1);
        player2 = new Player(name2);
    }
    // MAIN
    // public static void main(String[] args) {
    //
    // }
}
