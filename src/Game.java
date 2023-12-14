import java.util.Scanner;
import java.util.ArrayList;

public class Game {
    // Instance Variables
    private Player player1;
    private Player player2;
    private Deck deck;

    // Constructor
    public Game() {
        char[] ranks = {'1', '2'};
        String[] suits = {"yellow tang", "angelfish", "purple tang",
                            "clownfish", "angler fish", "swordfish", "whale",
                                "shark", "seahorse", "dolphin", "jellyfish",
                                    "flounder", "octopus"};
        int[] points = {1, 1};
        deck = new Deck(ranks, suits, points);

        Scanner input = new Scanner(System.in);
        System.out.print("Player 1 enter a name: ");
        String name1 = input.nextLine();
        System.out.print("Player 2 enter a name: ");
        String name2 = input.nextLine();

        // Create a hand for each player
        ArrayList<Card> hand1 = new ArrayList<Card>();
        ArrayList<Card> hand2 = new ArrayList<Card>();

        // Deal 5 cards per hand
        for (int i = 0; i < 5; i++) {
            hand1.add(deck.deal());
            hand2.add(deck.deal());
        }

        player1 = new Player(name1, hand1);
        player2 = new Player(name2, hand2);
    }

    // Prints the instructions
    public void printInstructions() {
        System.out.println("~~~~~~~~~~ How to Play GoFish ~~~~~~~~~~" +
                "\n∆ Each player starts with a hand of 5 cards" +
                "\n∆ The objective of the game is to collect the most pairs" +
                "\n   ∆ Collect pairs by creating matches of the same fish" +
                "\n∆ Take turns asking each other if they have a card you want" +
                "\n   ∆ ex: \"Do you have a...?\"" +
                "\n      ∆ If yes, they must give you that card, you may create a pair, and then you may continue asking for cards until they respond no" +
                "\n      ∆ If no, they will respond \"Go Fish!\"---then you must draw a card" +
                "\n∆ Play until there are no cards left in play (every card should have a pair)" +
                "\n∆ Whoever has the most pairs wins!" +
                "\n∆ Have fun!" +
                "\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    public boolean hasCard(Player player, String card) {
        for (int i = 0; i < player.getHand().size(); i++) {
            if (card.equals(player.getHand().get(i).getSuit())) {
                return true;
            }
        }
        return false;
    }

    public void removeCard(Player player, String card) {
        for (int i = 0; i < player.getHand().size(); i++) {
            if (player.getHand().get(i).getSuit().equals(card)) {
                player.getHand().remove(player.getHand().get(i));
            }
        }
    }

    public static void removeInitialPairs(Player player) {
        for (int i = 0; i < player.getHand().size(); i++) {
            for (int j = 0; j < player.getHand().size(); j++) {
                if (player.getHand().get(i).getSuit().equals(player.getHand().get(j).getSuit()) && i != j) {
                    player.getHand().remove(player.getHand().get(i));
                    player.getHand().remove(player.getHand().get(j - 1));
                    player.addPoints(1);
//                    i++;
//                    j++;
                    break;
                }
            }
        }
    }

    public void printPoints() {
        System.out.println("\n" + player1.getName() + "'s points: " + player1.getPoints());
        System.out.println(player2.getName() + "'s points: " + player2.getPoints() + "\n");
    }

    public void printWinner() {
        if (player1.getPoints() > player2.getPoints()) {
            System.out.println(player1.getName() + " wins!");
        }
        else if (player1.getPoints() == player2.getPoints()) {
            System.out.println("It's a tie!");
        }
        else {
            System.out.println(player2.getName() + " wins!");
        }
    }

    public boolean hasEmptyHand(Player player) {
        if (player.getHand().isEmpty()) {
            return true;
        }
        return false;
    }

    public void dealNewHand(Player player) {
        for (int i = 0; i < 5; i++) {
            if (!deck.isEmpty()) {
                player.getHand().add(deck.deal());
            }
        }
    }

    public void drawCard(Player player) {
        if (!deck.isEmpty()) {
            player.getHand().add(deck.deal());
        }
    }

    // Play Game Function
    public void playGame() {
        // Print instructions
        printInstructions();

        // Check for pairs in initial hands and remove them
        removeInitialPairs(player1);
        removeInitialPairs(player2);

        // Initialize a variable to keep track of who's turn it is
        int turn = 1;
        // While there are cards still in play (cards in the deck or in either players hand)
        while (!(deck.isEmpty()) || !(player1.getHand().isEmpty()) || !(player2.getHand().isEmpty())) {
            // If the turn it odd, it is player1's turn
            if (turn % 2 == 1) {
                // Print both players points
                printPoints();
                // Print player1's hand
                for (int i = 0; i < player1.getHand().size(); i++) {
                    System.out.println(player1.getHand().get(i).getSuit());
                }
                // Initialize scanner
                Scanner input = new Scanner(System.in);
                // Prompts player1 for their move
                System.out.print("\n" + player1.getName() + "'s turn: Do you have a ");
                // Stores their guess in a string variable
                String guess = input.nextLine();
                // If player2 has the guess, give a point to player1 and remove the pair from play
                if (hasCard(player2, guess)) {
                    // Player2 responds yes
                    System.out.println(player2.getName() + ": Yes");
                    // Add point to player1's score
                    player1.addPoints(1);
                    // Remove card from player1's hand
                    removeCard(player1, guess);
                    // Remove card from player2's hand
                    removeCard(player2, guess);
                    // Deal cards back to each player, so they still have 5 cards
                    drawCard(player1);
                    drawCard(player2);
                    // Remove any pairs that are made when cards are drawn
                    removeInitialPairs(player1);
                    removeInitialPairs(player2);
                // If player2 doesn't have the guess, player1 must draw a card
                } else {
                    // Player2 responds "go fish" indicating to draw a card
                    System.out.println(player2.getName() + ": Go Fish!");
                    // While player1 has less than 5 cards, and the deck is not empty
                    while (player1.getHand().size() < 5 && !deck.isEmpty()) {
                        // Deal a card
                        Card card = deck.deal();
                        // If player1 already has that card
                        if (hasCard(player1, card.getSuit())) {
                            // Remove it and count it as a new pair
                            removeCard(player1, card.getSuit());
                            player1.addPoints(1);
                        }
                        // Otherwise, just add the card to their hand
                        else {
                            player1.getHand().add(card);
                        }
                    }
                    // Go to next player's turn
                    turn++;
                }
            }
            else {
                printPoints();
                for (int i = 0; i < player2.getHand().size(); i++) {
                    System.out.println(player2.getHand().get(i).getSuit());
                }
                Scanner input = new Scanner(System.in);
                System.out.print("\n" + player2.getName() + "'s turn: Do you have a ");
                String guess = input.nextLine();
                if (hasCard(player1, guess)) {
                    System.out.println(player1.getName() + ": Yes");
                    player2.addPoints(1);
                    // Remove card from player1's hand
                    removeCard(player1, guess);
                    // Remove card from player2's hand
                    removeCard(player2, guess);
                    drawCard(player1);
                    drawCard(player2);
                    removeInitialPairs(player1);
                    removeInitialPairs(player2);
                }
                else {
                    System.out.println(player1.getName() + ": Go Fish!");
                    // While player2 has less than 5 cards, and the deck is not empty
                    while (player2.getHand().size() < 5 && !deck.isEmpty()) {
                        // Deal a card
                        Card card = deck.deal();
                        // If player1 already has that card
                        if (hasCard(player2, card.getSuit())) {
                            // Remove it and count it as a new pair
                            removeCard(player2, card.getSuit());
                            player2.addPoints(1);
                        }
                        // Otherwise, just add the card to their hand
                        else {
                            player2.getHand().add(card);
                        }
                    }
                    turn++;
                }
            }
            System.out.print("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
        }
        printWinner();
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.playGame();
    }
}
