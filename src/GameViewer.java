// Graphics for GoFish! by Josie Lee

// Import Graphics classes
import javax.swing.*;
import java.awt.*;

// GameViewer class
public class GameViewer extends JFrame {
    // Declare instance variables
    private final int WINDOW_WIDTH = 1000;
    private final int WINDOW_HEIGHT = 800;
    private final int CARD_WIDTH = 100;
    private final int CARD_HEIGHT = 150;
    private final int SPACE_BETWEEN_CARDS = 110;
    private Image[] cards;
    private Image backOfCard;
    // Reference to Game class, so the viewer can access the data of the game
    private Game table;

    // Constructor
    public GameViewer(Game table) {
        // Initialize the Game object so viewer can access all the data
        this.table = table;

        // Initialize all the images for the cards
        cards = new Image[13];
        cards[0] = new ImageIcon("Resources/yellowtang.png").getImage();
        cards[1] = new ImageIcon("Resources/angelfish.png").getImage();
        cards[2] = new ImageIcon("Resources/purpletang.png").getImage();
        cards[3] = new ImageIcon("Resources/clownfish.png").getImage();
        cards[4] = new ImageIcon("Resources/anglerfish.png").getImage();
        cards[5] = new ImageIcon("Resources/swordfish.png").getImage();
        cards[6] = new ImageIcon("Resources/whale.png").getImage();
        cards[7] = new ImageIcon("Resources/shark.png").getImage();
        cards[8] = new ImageIcon("Resources/seahorse.png").getImage();
        cards[9] = new ImageIcon("Resources/dolphin.png").getImage();
        cards[10] = new ImageIcon("Resources/jellyfish.png").getImage();
        cards[11] = new ImageIcon("Resources/flounder.png").getImage();
        cards[12] = new ImageIcon("Resources/octopus.png").getImage();

        // Initialize the backOfCard image
        backOfCard = new ImageIcon("Resources/backofcard.png").getImage();

        // Construct basic requirements for a frontend
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("GoFish! by Josie Lee");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);
    }

    // Returns the images of the cards
    public Image[] getCards() {
        return cards;
    }

    // Paints the window/frontend of GoFish
    public void paint(Graphics g) {
        // If the turn is 0, the game hasn't begun so draw the introduction screen
        if (table.getTurn() == 0) {
            // Set background
            g.setColor(new Color(204, 245, 255));
            g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);

            // Write title
            g.setColor(new Color(3, 121, 148, 255));
            g.setFont(new Font("Serif", Font.BOLD, 50));
            g.drawString("Welcome to GoFish!", 250, 100);

            // Write instructions on how to play GoFish
            g.setColor(new Color(19, 181, 187));
            g.setFont(new Font("Serif", Font.PLAIN, 30));
            g.drawString("~~~~~~~~~~ How to Play GoFish ~~~~~~~~~~", 120, 150);
            g.setFont(new Font("Serif", Font.PLAIN, 13));
            g.drawString("∆ Each player starts with a hand of 5 cards no matter what (players draw until they get 5 cards with no pairs)", 50, 210);
            g.drawString("∆ The objective of the game is to collect the most pairs", 50, 240);
            g.drawString("   ∆ Collect pairs by creating matches of the same fish", 50, 270);
            g.drawString("∆ Take turns asking each other if they have a card you want", 50, 300);
            g.drawString("   ∆ ex: \"Do you have a...?\"", 50, 330);
            g.drawString("      ∆ If yes, they must give you that card, you may create a pair, and then you may continue asking for cards until they respond no", 50, 360);
            g.drawString("         ∆ Players draw cards whenever a card is used (you may get lucky and make a pair!)", 50, 390);
            g.drawString("      ∆ If no, they will respond \"Go Fish!\"---then you must draw a card", 50, 420);
            g.drawString("∆ Play until there are no cards left in play (every card has a pair)", 50, 450);
            g.drawString("∆ Whoever has the most pairs wins!", 50, 480);
            g.drawString("∆ Have fun!", 50, 510);
            g.setFont(new Font("Serif", Font.PLAIN, 30));
            g.drawString("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~", 120, 570);
        }
        // If the turn is 100, the game has ended so draw the endgame screen
        else if (table.getTurn() == 100) {
            // Set background
            g.setColor(new Color(172, 239, 231, 255));
            g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);

            // Write the winner
            g.setColor(new Color(3, 121, 148, 255));
            g.setFont(new Font("Serif", Font.BOLD, 100));
            g.drawString(table.getWinner() + " wins!", 250, 450);
        }
        // Otherwise, the game is in play so draw the main screen
        else {
            // Set background
            g.setColor(new Color(172, 239, 231, 255));
            g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);

            // Write which player's turn it is
            g.setColor(new Color(3, 121, 148, 255));
            g.setFont(new Font("Serif", Font.BOLD, 30));
            g.drawString(table.getPlayerInTurn().getName() + "'s turn", 125, 400);

            // If the deck isn't empty, draw the deck in the middle of the screen
            if (!table.getDeck().isEmpty()) {
                g.drawImage(backOfCard, 450, 350, 100, 150, this);
            }

            // Create variables for the x coordinates of the cards in each player's hand
            int playerInTurnX = 100;
            int otherPlayerX = 100;

            // For each card in playerInTurn's hand
            for (int i = 0; i < table.getPlayerInTurn().getHand().size(); i++) {
                // The card draws itself at the player's designated x and y coordinates
                table.getPlayerInTurn().getHand().get(i).draw(g, playerInTurnX, table.getPlayerInTurn().getYCards());
                // Move x coordinate over to the right for the next card
                playerInTurnX += SPACE_BETWEEN_CARDS;
            }
            // For each card in otherPlayer's hand
            for (int i = 0; i < table.getOtherPlayer().getHand().size(); i++) {
                // The card draws itself at the player's designated x and y coordinates
                g.drawImage(backOfCard, otherPlayerX, table.getOtherPlayer().getYCards(), CARD_WIDTH, CARD_HEIGHT, this);
                // Move x coordinate over to the right for the next card
                otherPlayerX += SPACE_BETWEEN_CARDS;
            }

            // Write the points for each player
            g.drawString("Points:", 600, 325);
            g.drawString("Player 1:", 600, 400);
            g.drawString(Integer.toString(table.getPlayer1().getPoints()), 750, 400);
            g.drawString("Player 2:", 600, 475);
            g.drawString(Integer.toString(table.getPlayer2().getPoints()), 750, 475);
        }
    }
}
