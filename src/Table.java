import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Table extends JPanel implements KeyListener
{
	private static final long serialVersionUID = 1L;
    String type;
    Deck deck;
    CardPanel cardPanel;
    MultiPlayerPanel multiplayerPanel;
    SpeedPanel speedPanel;
    NewGameButton newGameButton;
    CheckButton checkButton;
    boolean gameOver;
    static String nextGame;
    int nextSize;
    int size;
    
    public Table(final String type, final Deck d, final String[] names) {
        super();
        this.gameOver = false;
        this.size = 40;
        this.type = type;
        this.deck = d;
        this.setSize(this.size * 3 * 3 + 80, this.size * 4 * 3 + 120);
        this.setBackground(Color.black);
        this.setVisible(true);
        this.setLayout(new BoxLayout(this, 0));
        final JPanel leftPanel = new JPanel();
        leftPanel.setBackground(Color.black);
        leftPanel.setSize(this.size * 3 * 3 + 80, this.size * 4 * 3 + 120);
        leftPanel.setLayout(new BoxLayout(leftPanel, 1));
        leftPanel.add(this.cardPanel = new CardPanel(this));
        final JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.black);
        buttonPanel.setSize(this.size * 3 * 3 + 80, 17);
        buttonPanel.setLayout(new BoxLayout(buttonPanel, 0));
        this.newGameButton = new NewGameButton(this);
        buttonPanel.add(this.checkButton = new CheckButton(this));
        leftPanel.add(buttonPanel);
        leftPanel.add(Box.createRigidArea(new Dimension(this.size * 3 * 3 + 80, 10)));
        this.add(leftPanel);
        final JPanel rightPanel = new JPanel();
        rightPanel.setBackground(Color.black);
        rightPanel.setLayout(new BorderLayout());
        if (type.equals("Multiplayer")) {
            rightPanel.add(this.multiplayerPanel = new MultiPlayerPanel(names.length, names, this), "North");
        } else {
            rightPanel.add(this.speedPanel = new SpeedPanel(new Player(0, names[0], this.cardPanel), this), "North");
        }
        final JPanel bottomRightPanel = new JPanel();
        bottomRightPanel.setBackground(Color.black);
        bottomRightPanel.add(Box.createHorizontalGlue());
        bottomRightPanel.add(new Menu(this));
        final JPanel br = new JPanel();
        br.setBackground(Color.black);
        br.setSize(1, this.size * 4 * 3 + 120);
        br.setLayout(new BoxLayout(br, 1));
        br.add(bottomRightPanel);
        br.add(Box.createRigidArea(new Dimension(1, 7)));
        rightPanel.add(br, "South");
        this.add(rightPanel);
        this.requestFocus();
        this.addKeyListener(this);
    }
    
    public void updateNext(final String s, final int x) {
        Table.nextGame = s;
        this.nextSize = x;
    }
    
    public boolean gameOver() {
        if (this.type.equals("Speed")) {
            SpeedPanel.finalTime = System.currentTimeMillis() - SpeedPanel.startTime + SpeedPanel.pastTime;
            this.speedPanel.repaint();
        }
        else {
            this.multiplayerPanel.repaint();
        }
        return this.cardPanel.getCard(0) == null;
    }
    
    public void update() {
        if (this.type.equals("Speed")) {
            this.speedPanel.update(this.cardPanel.update());
        }
        else {
            this.multiplayerPanel.update(this.cardPanel.update());
        }
        this.requestFocus();
    }
    
    @Override
    public void keyPressed(final KeyEvent arg0) {
        char c = arg0.getKeyChar();
        final int direction = arg0.getKeyCode();
        if (c == '\n' || direction == 16) {
            this.update();
        }
        else if (this.type.equals("Multiplayer")) {
            if (Character.isDigit(c)) {
                this.multiplayerPanel.updateChosenPlayer(c - '1');
            }
            else if (38 == direction) {
                this.multiplayerPanel.updateChosenPlayer((MultiPlayerPanel.chosen + MultiPlayerPanel.players.length - 1) % MultiPlayerPanel.players.length);
            }
            else if (40 == direction) {
                this.multiplayerPanel.updateChosenPlayer((MultiPlayerPanel.chosen + 1) % MultiPlayerPanel.players.length);
            }
            this.multiplayerPanel.repaint();
        }
        c = Character.toLowerCase(c);
        int cardPressed = -1;
        switch (c) {
            case 'w': {
                cardPressed = 0;
                break;
            }
            case 'e': {
                cardPressed = 1;
                break;
            }
            case 'a': {
                cardPressed = 2;
                break;
            }
            case 's': {
                cardPressed = 3;
                break;
            }
            case 'd': {
                cardPressed = 4;
                break;
            }
            case 'z': {
                cardPressed = 5;
                break;
            }
            case 'x': {
                cardPressed = 6;
                break;
            }
            case 't': {
                cardPressed = 0;
                break;
            }
            case 'y': {
                cardPressed = 1;
                break;
            }
            case 'f': {
                cardPressed = 2;
                break;
            }
            case 'g': {
                cardPressed = 3;
                break;
            }
            case 'h': {
                cardPressed = 4;
                break;
            }
            case 'v': {
                cardPressed = 5;
                break;
            }
            case 'b': {
                cardPressed = 6;
                break;
            }
            case 'i': {
                cardPressed = 0;
                break;
            }
            case 'o': {
                cardPressed = 1;
                break;
            }
            case 'j': {
                cardPressed = 2;
                break;
            }
            case 'k': {
                cardPressed = 3;
                break;
            }
            case 'l': {
                cardPressed = 4;
                break;
            }
            case 'm': {
                cardPressed = 5;
                break;
            }
            case ',': {
                cardPressed = 6;
                break;
            }
        }
        if (cardPressed >= 0) {
            this.cardPanel.setChosen(this.cardPanel.getChosen(cardPressed) ^ true, cardPressed);
        }
        this.cardPanel.repaint();
        this.requestFocus();
    }
    
    @Override
    public void keyReleased(final KeyEvent arg0) {
    }
    
    @Override
    public void keyTyped(final KeyEvent arg0) {
    }
}
