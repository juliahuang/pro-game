import javax.swing.*;
import java.awt.event.*;

public class Game extends JPanel implements WindowListener
{
	private static final long serialVersionUID = 1L;
	static Table t;
    
    public Game(final String gameType, final String[] names) {
        super();
        this.add(Game.t = new Table(gameType, new Deck(), names));
    }
    
    @Override
    public void setVisible(final boolean b) {
        super.setVisible(b);
        Game.t.requestFocus();
    }
    
    @Override
    public void windowActivated(final WindowEvent arg0) {
        if (Game.t.type.equals("Speed")) {
            Game.t.speedPanel.unpause();
        }
    }
    
    @Override
    public void windowClosed(final WindowEvent arg0) {
    }
    
    @Override
    public void windowClosing(final WindowEvent arg0) {
    }
    
    @Override
    public void windowDeactivated(final WindowEvent arg0) {
        if (Game.t.type.equals("Speed")) {
            Game.t.speedPanel.pause();
        }
    }
    
    @Override
    public void windowDeiconified(final WindowEvent arg0) {
        if (Game.t.type.equals("Speed")) {
            Game.t.speedPanel.pause();
        }
    }
    
    @Override
    public void windowIconified(final WindowEvent arg0) {
        if (Game.t.type.equals("Speed")) {
            Game.t.speedPanel.unpause();
        }
    }
    
    @Override
    public void windowOpened(final WindowEvent arg0) {
    }
}
