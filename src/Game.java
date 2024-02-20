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
    private int turn = 0;
    private GameViewer window;
    private String winner;
    private Player playerInTurn;
    private Player otherPlayer;
    // Magic numbers
    private final int PLAYER1_YCARDS_COORDINATE = 100;
    private final int PLAYER2_YCARDS_COORDINATE = 600;

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

        // Initialize GameViewer window
        window = new GameViewer(this);

        // Create a new deck with the cards for GoFish
        deck = new Deck(ranks, suits, points, window.getCards(), window);

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
        player1 = new Player(name1, hand1, PLAYER1_YCARDS_COORDINATE);
        player2 = new Player(name2, hand2, PLAYER2_YCARDS_COORDINATE); // make magic numbers

        // Winner will be the name of the player who wins when the game is over
        winner = "";

        // It is player1's turn first
        playerInTurn = player1;
        // It is not player2's turn to start
        otherPlayer = player2;
    }

    // Returns the player out of turn
    public Player getOtherPlayer() {
        return otherPlayer;
    }

    // Returns the player in turn
    public Player getPlayerInTurn() {
        return playerInTurn;
    }

    // Returns player1
    public Player getPlayer1() {
        return player1;
    }

    // Returns player2
    public Player getPlayer2() {
        return player2;
    }

    // Returns the deck
    public Deck getDeck() {
        return deck;
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

    // Returns the winner
    public String getWinner() {
        return winner;
    }

    // Returns the turn
    public int getTurn() {
        return turn;
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
            winner = player1.getName();
        }
        // Otherwise print player2 wins
        else {
            winner = player2.getName();
        }
        System.out.println(winner + " wins!");
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
        // Keep track of who's turn it is (starts with player 1)
        turn = 1;

        // While there are cards still in play (cards in the deck or in either players hand)
        while (!(deck.isEmpty()) || !(hasEmptyHand(player1)) || !(hasEmptyHand(player2))) {
            // Repaint the window every turn
            window.repaint();
            // Before each turn print both player's points
            printPoints();
            // If the turn is odd, it is player1's turn
            if (turn % 2 == 1) {
                playerInTurn = player1;
                otherPlayer = player2;
            }
            // Otherwise the turn is even, so it's player2's turn
            else {
                playerInTurn = player2;
                otherPlayer = player1;
            }

            // Print playerInTurn's hand
            printHand(playerInTurn);
            // Prompt playerInTurn for their guess
            String guess = promptGuess(playerInTurn);
            // If otherPlayer has the guess, give a point to playerInTurn and remove the pair from the field of play
            if (hasCard(otherPlayer, guess)) {
                System.out.println(otherPlayer.getName() + ": Yes");
                // Add point to playerInTurn's score
                playerInTurn.addPoints(1);
                // Remove card from both hands
                removeCard(playerInTurn, guess);
                removeCard(otherPlayer, guess);
                // Each player draws a card
                drawCard(playerInTurn);
                drawCard(otherPlayer);
                // If otherPlayer doesn't have the guess, playerInTurn must draw a card
            } else {
                System.out.println(otherPlayer.getName() + ": Go Fish!");
                // playerInTurn draws a card
                drawCard(playerInTurn);
                // Go to next player's turn
                turn++;
            }
            // Print a break between each turn
            System.out.print("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
        }
        // Print final points
        printPoints();
        // When all the cards are paired up, print the winner (player with more cards)
        printWinner();
        // Set turn to 100 to trigger the endgame screen
        turn = 100;
        // Repaint the window to endgame screen
        window.repaint();
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
