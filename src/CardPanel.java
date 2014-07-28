import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CardPanel extends JPanel implements MouseListener, KeyListener
{
	private static final long serialVersionUID = 1L;
	private Table table;
    private Deck deck;
    private static Card[] cards;
    private static boolean[] chosen;
    private static int size;
    private static int limit;
    private static Dimension[] corners;
    
    static {
        CardPanel.size = 40;
        CardPanel.corners = new Dimension[] { new Dimension((CardPanel.size * 3 * 1 + 60) / 2, CardPanel.size * 3 * 0 + 20), new Dimension((CardPanel.size * 3 * 3 + 100) / 2, CardPanel.size * 3 * 0 + 20), new Dimension((CardPanel.size * 3 * 0 + 40) / 2, CardPanel.size * 4 * 1 + 40), new Dimension((CardPanel.size * 3 * 2 + 80) / 2, CardPanel.size * 4 * 1 + 40), new Dimension((CardPanel.size * 3 * 4 + 120) / 2, CardPanel.size * 4 * 1 + 40), new Dimension((CardPanel.size * 3 * 1 + 60) / 2, CardPanel.size * 4 * 2 + 60), new Dimension((CardPanel.size * 3 * 3 + 100) / 2, CardPanel.size * 4 * 2 + 60) };
    }
    
    public CardPanel(final Table t) {
        super();
        this.table = t;
        this.deck = t.deck;
        this.setPreferredSize(new Dimension(CardPanel.size * 3 * 3 + 80, CardPanel.size * 4 * 3 + 80));
        this.setBackground(Color.black);
        this.setVisible(true);
        CardPanel.chosen = new boolean[7];
        CardPanel.cards = new Card[7];
        CardPanel.limit = 6;
        for (int i = 0; i < 7; ++i) {
            CardPanel.cards[i] = this.deck.cards[i];
        }
        this.addMouseListener(this);
        this.addKeyListener(this);
    }
    
    public int getLimit() {
        return CardPanel.limit;
    }
    
    public int setLimit(final int newLimit) {
        return CardPanel.limit = newLimit;
    }
    
    public boolean getChosen(final int index) {
        return CardPanel.chosen[index];
    }
    
    public boolean setChosen(final boolean choosen, final int index) {
        return CardPanel.chosen[index] = choosen;
    }
    
    public Deck getDeck() {
        return this.deck;
    }
    
    public Card getCard(final int index) {
        return CardPanel.cards[index];
    }
    
    public void setCard(final Card c, final int index) {
        CardPanel.cards[index] = c;
    }
    
    public void resetChosen() {
        CardPanel.chosen = new boolean[7];
    }
    
    @Override
    public void paint(final Graphics g) {
        for (int i = 0; i < CardPanel.corners.length; ++i) {
            drawCard(g, i);
        }
    }
    
    public static void drawCard(final Graphics g, final int cardNumber) {
        final Card c = CardPanel.cards[cardNumber];
        final int x = CardPanel.corners[cardNumber].width;
        final int y = CardPanel.corners[cardNumber].height;
        if (c == null) {
            g.setColor(Color.black);
            g.fillRect(x, y, CardPanel.size * 3, CardPanel.size * 4);
            return;
        }
        g.setColor(Color.white);
        g.fillRect(x, y, CardPanel.size * 3, CardPanel.size * 4);
        if (CardPanel.chosen[cardNumber]) {
            g.setColor(Color.blue);
        }
        else {
            g.setColor(Color.black);
        }
        g.fillRect(x + 5, y + 5, CardPanel.size * 3 - 10, CardPanel.size * 4 - 10);
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 2; ++j) {
                if (c.dots[2 * i + j]) {
                    g.setColor(c.colors[2 * i + j]);
                    g.fillOval(x + CardPanel.size * (j + 1) - CardPanel.size / 4, y + CardPanel.size * (i + 1) - CardPanel.size / 4, CardPanel.size / 2, CardPanel.size / 2);
                    g.setColor(Color.black);
                    g.drawOval(x + CardPanel.size * (j + 1) - CardPanel.size / 4, y + CardPanel.size * (i + 1) - CardPanel.size / 4, CardPanel.size / 2, CardPanel.size / 2);
                }
            }
        }
    }
    
    public int cardClicked(final int x, final int y) {
        for (int i = 0; i < 7; ++i) {
            final int xLimitSmall = CardPanel.corners[i].width;
            final int xLimitLarge = xLimitSmall + 3 * CardPanel.size;
            final int yLimitSmall = CardPanel.corners[i].height;
            final int yLimitLarge = yLimitSmall + 4 * CardPanel.size;
            if (x >= xLimitSmall && x <= xLimitLarge && y >= yLimitSmall && y <= yLimitLarge) {
                return i;
            }
        }
        return -1;
    }
    
    int checkCards() {
        final int[] colors = new int[6];
        int count = 0;
        for (int i = 0; i < 7; ++i) {
            if (CardPanel.chosen[i]) {
                final boolean[] dots = CardPanel.cards[i].dots;
                for (int j = 0; j < 6; ++j) {
                    if (dots[j]) {
                        final int[] array = colors;
                        final int n = j;
                        ++array[n];
                    }
                }
                ++count;
            }
        }
        for (int i = 0; i < 6; ++i) {
            if (colors[i] % 2 == 1) {
                return 0;
            }
        }
        return count;
    }
    
    int update() {
        final int replacing = this.checkCards();
        if (replacing > 0) {
            for (int i = 0; i < 7; ++i) {
                if (this.getChosen(i)) {
                    if (this.getLimit() < 62) {
                        this.setCard(this.getDeck().cards[this.setLimit(this.getLimit() + 1)], i);
                    }
                    else {
                        int index;
                        for (index = i + 1; index < 7 && this.getChosen(index); ++index) {}
                        if (index == 7) {
                            while (i < 7) {
                                this.setCard(null, i);
                                ++i;
                            }
                            break;
                        }
                        this.setCard(this.getCard(index), i);
                        this.setChosen(true, index);
                        this.setCard(null, index);
                    }
                }
            }
        }
        this.resetChosen();
        this.repaint();
        this.requestFocus();
        return replacing;
    }
    
    @Override
    public void mouseClicked(final MouseEvent arg0) {
    }
    
    @Override
    public void mouseEntered(final MouseEvent arg0) {
    }
    
    @Override
    public void mouseExited(final MouseEvent arg0) {
    }
    
    @Override
    public void mousePressed(final MouseEvent arg0) {
        final int cardClicked = this.cardClicked(arg0.getX(), arg0.getY());
        if (cardClicked != -1) {
            final boolean[] chosen = CardPanel.chosen;
            final int n = cardClicked;
            chosen[n] ^= true;
            this.repaint();
        }
        this.table.requestFocus();
    }
    
    @Override
    public void mouseReleased(final MouseEvent arg0) {
    }
    
    @Override
    public void keyPressed(final KeyEvent arg0) {
    }
    
    @Override
    public void keyReleased(final KeyEvent arg0) {
    }
    
    @Override
    public void keyTyped(final KeyEvent arg0) {
    }
}
