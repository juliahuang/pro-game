import javax.swing.*;
import java.awt.event.*;

public class NewGameButton extends JButton implements ActionListener
{
	private static final long serialVersionUID = 1L;
    Table t;
    
    public NewGameButton(final Table t) {
        super("New Game");
        this.t = t;
        this.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(final ActionEvent e) {
        this.t.gameOver = true;
        this.repaint();
        this.t.requestFocus();
    }
}
