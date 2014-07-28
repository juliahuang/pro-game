import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MultiPlayerPanel extends JPanel implements MouseListener
{
	private static final long serialVersionUID = 1L;
    private Table table;
    static Player[] players;
    static int size;
    static int border;
    static int chosen;
    
    static {
        MultiPlayerPanel.size = 20;
        MultiPlayerPanel.border = 20;
        MultiPlayerPanel.chosen = 0;
    }
    
    public MultiPlayerPanel(final int number, final String[] names, final Table t) {
        super();
        this.table = t;
        this.setPreferredSize(new Dimension(300, 500));
        this.setBackground(Color.black);
        this.setVisible(true);
        MultiPlayerPanel.players = new Player[number];
        for (int i = 0; i < number; ++i) {
            MultiPlayerPanel.players[i] = new Player(i + 1, names[i], t.cardPanel);
        }
        this.addMouseListener(this);
    }
    
    @Override
    public void paint(final Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, 1, 30);
        g.setColor(Color.white);
        g.setFont(new Font(this.getName(), 1, 32));
        g.drawString("PLAYERS", 0, 60);
        g.setFont(new Font(this.getName(), 1, 16));
        for (int i = 0; i < MultiPlayerPanel.players.length; ++i) {
            drawPlayer(g, i);
        }
    }
    
    public static void drawPlayer(final Graphics g, final int playerNumber) {
        final int x = MultiPlayerPanel.border;
        final int y = 60 + MultiPlayerPanel.border * (playerNumber + 1) + MultiPlayerPanel.size * playerNumber;
        g.setColor(MultiPlayerPanel.players[playerNumber].chosenPlayer ? Color.blue : Color.black);
        g.fillRect(x, y, 250, 20);
        g.setColor(Color.white);
        g.drawRect(x, y, 20, 20);
        g.drawString(new StringBuilder().append(playerNumber + 1).toString(), x + 5, y + 16);
        g.drawRect(x + 20, y, 200, 20);
        g.drawString(MultiPlayerPanel.players[playerNumber].name, x + 20 + 5, y + 16);
        g.drawRect(x + 220, y, 30, 20);
        g.drawString(new StringBuilder().append(MultiPlayerPanel.players[playerNumber].score).toString(), x + 220 + 5, y + 16);
    }
    
    public int playerClicked(final int x, final int y) {
        for (int i = 0; i < MultiPlayerPanel.players.length; ++i) {
            final int xLimitSmall = MultiPlayerPanel.border;
            final int xLimitLarge = xLimitSmall + 200;
            final int yLimitSmall = 60 + MultiPlayerPanel.border * (i + 1) + MultiPlayerPanel.size * i;
            final int yLimitLarge = yLimitSmall + 20;
            if (x >= xLimitSmall && x <= xLimitLarge && y >= yLimitSmall && y <= yLimitLarge) {
                return i;
            }
        }
        return -1;
    }
    
    public void update(final int cards) {
        final Player player = MultiPlayerPanel.players[MultiPlayerPanel.chosen];
        player.score += cards;
        this.repaint();
        this.requestFocus();
    }
    
    public void updateChosenPlayer(final int newPlayer) {
        if (newPlayer >= 0 && newPlayer < MultiPlayerPanel.players.length) {
            MultiPlayerPanel.players[MultiPlayerPanel.chosen].chosenPlayer = false;
            MultiPlayerPanel.players[newPlayer].chosenPlayer = true;
            MultiPlayerPanel.chosen = newPlayer;
        }
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
        final int playerClicked = this.playerClicked(arg0.getX(), arg0.getY());
        if (playerClicked != -1) {
            this.updateChosenPlayer(playerClicked);
        }
        this.repaint();
        this.table.requestFocus();
    }
    
    @Override
    public void mouseReleased(final MouseEvent arg0) {
    }
}
