import javax.swing.*;
import java.awt.event.*;

public class CheckButton extends JButton implements ActionListener
{
	private static final long serialVersionUID = 1L;
	Table t;
    
    public CheckButton(final Table table) {
        super("Verify!");
        this.t = table;
        this.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(final ActionEvent e) {
        this.t.update();
    }
}
