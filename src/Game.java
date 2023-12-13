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
        String[] suits = {"Yellow Tang", "Angelfish", "Purple Tang",
                            "Clownfish", "Angler Fish", "Swordfish", "Whale",
                                "Shark", "Seahorse", "Dolphin", "Jellyfish",
                                    "Flounder", "Octopus"};
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

    public boolean hasCard(Player player, String guess) {
        for (int i = 0; i < player.getHand().size(); i++) {
            if (guess.equals(player.getHand().get(i).getSuit())) {
                return true;
            }
        }
        return false;
    }

    public static void removeInitialPairs(Player player) {
        for (int i = 0; i < player.getHand().size(); i++) {
            for (int j = 0; j < player.getHand().size(); j++) {
                if (player.getHand().get(i).getSuit().equals(player.getHand().get(j).getSuit()) && i != j) {
                    player.getHand().remove(player.getHand().get(i));
                    player.getHand().remove(player.getHand().get(j));
                    player.addPoints(1);
                    i++;
                    j++;
                }
            }
        }
    }

    public void printPoints() {
        System.out.println(player1.getName() + "'s points: " + player1.getPoints());
        System.out.println(player2.getName() + "'s points: " + player2.getPoints());
    }

    public String pickWinner() {
        if (player1.getPoints() > player2.getPoints()) {
            return player1.getName();
        }
        else if (player1.getPoints() == player2.getPoints()) {
            return "tie";
        }
        else {
            return player2.getName();
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
            if (turn % 2 == 1) {
                printPoints();
                for (int i = 0; i < player1.getHand().size(); i++) {
                    System.out.println(player1.getHand().get(i).getSuit());
                }
                Scanner input = new Scanner(System.in);
                System.out.print(player1.getName() + "'s turn: Do you have a ");
                String guess = input.nextLine();
                if (hasCard(player2, guess)) {
                    player1.addPoints(1);
                    for (int i = 0; i < player1.getHand().size(); i++) {
                        if (player1.getHand().get(i).getSuit().equals(guess)) {
                            player1.getHand().remove(player1.getHand().get(i));
                        }
                    }
                    for (int i = 0; i < player2.getHand().size(); i++) {
                        if (player2.getHand().get(i).getSuit().equals(guess)) {
                            player2.getHand().remove(player2.getHand().get(i));
                        }
                    }
                    System.out.println(player2.getName() + ": Yes");
                } else {
                    System.out.println(player2.getName() + ": Go Fish!");
                    Card card = deck.deal();
                    for (int i = 0; i < player1.getHand().size(); i++) {
                        if (card.getSuit().equals(player1.getHand().get(i).getSuit())) {
                            player1.getHand().remove(player1.getHand().get(i));
                            player1.addPoints(1);
                        }
                        else {
                            player1.getHand().add(card);
                            for (int j = 0; j < player1.getHand().size(); j++) {
                                Card tempCard = player1.getHand().get(j);
                                if (tempCard.getSuit().equals(card.getSuit()) && tempCard.getRank() != card.getRank()) {
                                    player1.getHand().remove(j);
                                    player1.getHand().remove(card);
                                    player1.addPoints(1);
                                    break;
                                }
                            }
                            break;
                        }
                    }
                    turn++;
                }
            }
            else {
                printPoints();
                for (int i = 0; i < player2.getHand().size(); i++) {
                    System.out.println(player2.getHand().get(i).getSuit());
                }
                Scanner input = new Scanner(System.in);
                System.out.print(player2.getName() + "'s turn: Do you have a ");
                String guess = input.nextLine();
                if (hasCard(player1, guess)) {
                    player2.addPoints(1);
                    for (int i = 0; i < player1.getHand().size(); i++) {
                        if (player1.getHand().get(i).getSuit().equals(guess)) {
                            player1.getHand().remove(player1.getHand().get(i));
                        }
                    }
                    for (int i = 0; i < player2.getHand().size(); i++) {
                        if (player2.getHand().get(i).getSuit().equals(guess)) {
                            player2.getHand().remove(player2.getHand().get(i));
                        }
                    }
                    System.out.println(player1.getName() + ": Yes");
                } else {
                    System.out.println(player1.getName() + ": Go Fish!");
                    Card card = deck.deal();
                    for (int i = 0; i < player2.getHand().size(); i++) {
                        if (card.getSuit().equals(player2.getHand().get(i).getSuit())) {
                            player2.getHand().remove(player2.getHand().get(i));
                            player2.addPoints(1);
                        }
                        else {
                            player2.getHand().add(card);
                            for (int j = 0; j < player2.getHand().size(); j++) {
                                Card tempCard = player2.getHand().get(j);
                                if (tempCard.getSuit().equals(card.getSuit()) && tempCard.getRank() != card.getRank()) {
                                    player2.getHand().remove(j);
                                    player2.getHand().remove(card);
                                    player2.addPoints(1);
                                    break;
                                }
                            }
                            break;
                        }
                    }
                    turn++;
                }
            }
            System.out.print("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
        }
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.playGame();
    }
}
