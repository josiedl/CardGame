import javax.swing.*;
import java.awt.*;

public class GameViewer extends JFrame {
    private final int WINDOW_WIDTH = 1000;
    private final int WINDOW_HEIGHT = 800;
    private Image[] cards;
    private Game table;
    public GameViewer(Game table) {
        this.table = table;

        cards = new Image[14];
        cards[0] = new ImageIcon("Resources/backofcard.png").getImage();
        cards[1] = new ImageIcon("Resources/angelfish.png").getImage();
        cards[2] = new ImageIcon("Resources/anglerfish.png").getImage();
        cards[3] = new ImageIcon("Resources/clownfish.png").getImage();
        cards[4] = new ImageIcon("Resources/dolphin.png").getImage();
        cards[5] = new ImageIcon("Resources/flounder.png").getImage();
        cards[6] = new ImageIcon("Resources/jellyfish.png").getImage();
        cards[7] = new ImageIcon("Resources/octopus.png").getImage();
        cards[8] = new ImageIcon("Resources/purpletang.png").getImage();
        cards[9] = new ImageIcon("Resources/seahorse.png").getImage();
        cards[10] = new ImageIcon("Resources/shark.png").getImage();
        cards[11] = new ImageIcon("Resources/swordfish.png").getImage();
        cards[12] = new ImageIcon("Resources/whale.png").getImage();
        cards[13] = new ImageIcon("Resources/yellowtang.png").getImage();

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("GoFish!");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);
    }

    public void paint(Graphics g) {
        if (table.getTurn() == 0) {
            g.setColor(new Color(3, 121, 148, 255));
            g.setFont(new Font("Serif", Font.BOLD, 50));
            g.drawString("Welcome to GoFish!", 250, 100);

            g.setColor(new Color(19, 181, 187));
            g.setFont(new Font("Serif", Font.PLAIN, 15));
            g.drawString("~~~~~~~~~~ How to Play GoFish ~~~~~~~~~~" +
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
                    "\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~", 100, 150);
        }
    }

    // TODO
    // Add a turn variable in Game (0 - intro, 1 - player1, 2 - player2)
        // Abstraction: reduce repetitive code (look at feedback)
    // Draw instructions (get window to show up)
        // Check if turn is 0
    // Create draw methods for card
    // Have each hand draw itself based on turn
    // Animations and repaint
}
