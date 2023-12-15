// GoFish! by Josie Lee

// Import Scanner and ArrayList classes
import java.util.Scanner;
import java.util.ArrayList;

// Game class
public class Game {
    // Declare instance variables
    private Player player1;
    private Player player2;
    private Deck deck;

    // Constructor
    public Game() {
        // Initialize ranks array
        char[] ranks = {'1', '2'};
        // Initialize suits of fish array
        String[] suits = {"yellow tang", "angelfish", "purple tang",
                            "clownfish", "angler fish", "swordfish", "whale",
                                "shark", "seahorse", "dolphin", "jellyfish",
                                    "flounder", "octopus"};
        // Initialize points array
        int[] points = {1, 1};
        // Create a new deck with the cards for GoFish
        deck = new Deck(ranks, suits, points);

        // Prompt users for names
        Scanner input = new Scanner(System.in);
        System.out.print("Player 1 enter a name: ");
        String name1 = input.nextLine();
        System.out.print("Player 2 enter a name: ");
        String name2 = input.nextLine();

        // Create hands for each player
        ArrayList<Card> hand1 = new ArrayList<Card>();
        ArrayList<Card> hand2 = new ArrayList<Card>();

        // Deal 5 cards per hand
        for (int i = 0; i < 5; i++) {
            hand1.add(deck.deal());
            hand2.add(deck.deal());
        }

        // Create two players
        player1 = new Player(name1, hand1);
        player2 = new Player(name2, hand2);
    }

    // Prints the instructions for GoFish
    public static void printInstructions() {
        System.out.println("~~~~~~~~~~ How to Play GoFish ~~~~~~~~~~" +
                "\n∆ Each player starts with a hand of 5 cards no matter what (players draw until they get 5 cards with no pairs)" +
                "\n∆ The objective of the game is to collect the most pairs" +
                "\n   ∆ Collect pairs by creating matches of the same fish" +
                "\n∆ Take turns asking each other if they have a card you want" +
                "\n   ∆ ex: \"Do you have a...?\"" +
                "\n      ∆ If yes, they must give you that card, you may create a pair, and then you may continue asking for cards until they respond no" +
                "\n         ∆ Players draw cards whenever a card is used (you may get lucky and make a pair!)" +
                "\n      ∆ If no, they will respond \"Go Fish!\"---then you must draw a card" +
                "\n∆ Play until there are no cards left in play (every card has a pair)" +
                "\n∆ Whoever has the most pairs wins!" +
                "\n∆ Have fun!" +
                "\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    // Returns true if a given player has a given suit of card
    public boolean hasCard(Player player, String cardSuit) {
        // Traverses over every card in player's hand
        for (int i = 0; i < player.getHand().size(); i++) {
            // If that card's suit equals the inputted suit, return true
            if (cardSuit.equals(player.getHand().get(i).getSuit())) {
                return true;
            }
        }
        // Otherwise return false
        return false;
    }

    // Removes a given suit of card from a given player
    public void removeCard(Player player, String cardSuit) {
        // Traverses over every card in player's hand
        for (int i = 0; i < player.getHand().size(); i++) {
            // If that card's suit equals the inputted suit, remove it
            if (player.getHand().get(i).getSuit().equals(cardSuit)) {
                player.getHand().remove(player.getHand().get(i));
            }
        }
    }

    // Removes pairs from a given player's hand
    public void removePairs(Player player) {
        // Compare every card in player's hand to every other card
        for (int i = 0; i < player.getHand().size(); i++) {
            for (int j = 0; j < player.getHand().size(); j++) {
                String cardSuit1 = player.getHand().get(i).getSuit();
                String cardSuit2 = player.getHand().get(j).getSuit();
                // If any two cards have matching suits (and they aren't the same card)
                if (cardSuit1.equals(cardSuit2) && i != j) {
                    // Remove the pair
                    player.getHand().remove(player.getHand().get(i));
                    player.getHand().remove(player.getHand().get(j - 1));
                    // Give player a point
                    player.addPoints(1);
                    break;
                }
            }
        }
    }

    // Prints the points of both players
    public void printPoints() {
        System.out.println("\n" + player1.getName() + "'s points: " + player1.getPoints());
        System.out.println(player2.getName() + "'s points: " + player2.getPoints() + "\n");
    }

    // Checks for and prints winner of game
    public void printWinner() {
        // If player1 has more points, print player1 wins
        if (player1.getPoints() > player2.getPoints()) {
            System.out.println(player1.getName() + " wins!");
        }
        // Otherwise print player2 wins
        else {
            System.out.println(player2.getName() + " wins!");
        }
    }

    // Returns true if a given player has 0 cards in their hand
    public boolean hasEmptyHand(Player player) {
        if (player.getHand().isEmpty()) {
            return true;
        }
        return false;
    }

    // Deals a card to a given player's hand
    public void drawCard(Player player) {
        // If the deck is not empty
        if (!deck.isEmpty()) {
            // Deal a card to player's hand
            player.getHand().add(deck.deal());
        }
        // Remove any pairs that are made when cards are drawn
        removePairs(player);
    }

    // Prints a given player's hand
    public void printHand(Player player) {
        // Prints the suits of every card in player's hand
        for (int i = 0; i < player.getHand().size(); i++) {
            System.out.println(player.getHand().get(i).getSuit());
        }
    }

    // Prompts player for a guess (of a suit the other player might have)
    // Returns the guess
    public String promptGuess(Player player) {
        // Initialize scanner
        Scanner input = new Scanner(System.in);
        // Prompts player for their move
        System.out.print("\n" + player.getName() + "'s turn: Do you have a ");
        // Stores their guess in a string variable
        String guess = input.nextLine();
        return guess;
    }

    // Makes sure each player has 5 cards at all times unless the deck is empty
    public void fiveCardsInHand() {
        // While the deck isn't empty and either player has less than 5 cards
        while (player1.getHand().size() < 5 || player2.getHand().size() < 5 && !deck.isEmpty()) {
            // If player1 has less than 5 cards
            if (player1.getHand().size() < 5) {
                // Deal a card to player1
                drawCard(player1);
            }
            // If player2 has less than 5 cards
            if (player2.getHand().size() < 5) {
                // Deal a card to player2
                drawCard(player2);
            }
        }
    }

    // Plays GoFish!
    public void playGame() {
        // Check for pairs in initial hands and remove them
        removePairs(player1);
        removePairs(player2);
        // Make sure each player has 5 cards in hand to start
        fiveCardsInHand();

        // Initialize a variable to keep track of who's turn it is
        int turn = 1;

        // While there are cards still in play (cards in the deck or in either players hand)
        while (!(deck.isEmpty()) || !(hasEmptyHand(player1)) || !(hasEmptyHand(player2))) {
            // Before each turn print both player's points
            printPoints();
            // If the turn is odd, it is player1's turn
            if (turn % 2 == 1) {
                // Print player1's hand
                printHand(player1);
                // Prompt player1 for their guess
                String guess = promptGuess(player1);
                // If player2 has the guess, give a point to player1 and remove the pair from the field of play
                if (hasCard(player2, guess)) {
                    System.out.println(player2.getName() + ": Yes");
                    // Add point to player1's score
                    player1.addPoints(1);
                    // Remove card from both hands
                    removeCard(player1, guess);
                    removeCard(player2, guess);
                    // Each player draws a card
                    drawCard(player1);
                    drawCard(player2);
                // If player2 doesn't have the guess, player1 must draw a card
                } else {
                    System.out.println(player2.getName() + ": Go Fish!");
                    // Player1 draws a card
                    drawCard(player1);
                    // Go to next player's turn
                    turn++;
                }
            }
            // Otherwise the turn is even, so it's player2's turn
            else {
                // Print player2's hand
                printHand(player2);
                // Prompt player2 for their guess
                String guess = promptGuess(player2);
                // If player1 has the guess, give a point to player2 and remove the pair from the field of play
                if (hasCard(player1, guess)) {
                    System.out.println(player1.getName() + ": Yes");
                    // Add point to player2's score
                    player2.addPoints(1);
                    // Remove card from both hands
                    removeCard(player1, guess);
                    removeCard(player2, guess);
                    // Each player draws a card
                    drawCard(player1);
                    drawCard(player2);
                }
                // If player1 doesn't have the guess, player2 must draw a card
                else {
                    System.out.println(player1.getName() + ": Go Fish!");
                    // Player2 draws a card
                    drawCard(player2);
                    // Go to next player's turn
                    turn++;
                }
            }
            // Print a break between each turn
            System.out.print("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
        }
        // Print final points
        printPoints();
        // When all the cards are paired up, print the winner (player with more cards)
        printWinner();
    }

    // Main
    public static void main(String[] args) {
        // Print instructions
        printInstructions();
        // Create a new game
        Game game = new Game();
        // Play game
        game.playGame();
    }
}
