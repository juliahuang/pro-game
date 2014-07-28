import javax.swing.*;

import java.awt.event.*;
import java.util.*;

public class Menu extends JMenuBar implements ActionListener
{
	private static final long serialVersionUID = 1L;
	Table t;
    
    public Menu(final Table t) {
        super();
        this.t = t;
        final JRadioButtonMenuItem[] rbMenuItem = new JRadioButtonMenuItem[10];
        final JMenu menu = new JMenu("Mode");
        this.add(menu);
        final ButtonGroup group = new ButtonGroup();
        final JRadioButtonMenuItem rbMenuItem2 = new JRadioButtonMenuItem("Speed: 1 player");
        if (t.type.equals("Speed")) {
            rbMenuItem2.setSelected(true);
        }
        group.add(rbMenuItem2);
        rbMenuItem2.addActionListener(this);
        menu.add(rbMenuItem2);
        for (int i = 2; i <= 9; ++i) {
            (rbMenuItem[i - 2] = new JRadioButtonMenuItem("Multiplayer: " + i + " players")).setSelected(false);
            if (t.type.equals("Multiplayer")) {
            	@SuppressWarnings("unused")
                final MultiPlayerPanel multiplayerPanel = t.multiplayerPanel;
                if (MultiPlayerPanel.players.length == i) {
                    rbMenuItem[i - 2].setSelected(true);
                }
            }
            group.add(rbMenuItem[i - 2]);
            rbMenuItem[i - 2].addActionListener(this);
            menu.add(rbMenuItem[i - 2]);
        }
    }
    
    @Override
    public void actionPerformed(final ActionEvent e) {
        final String s = e.getActionCommand();
        final StringTokenizer st = new StringTokenizer(s);
        String type = st.nextToken();
        type = type.substring(0, type.length() - 1);
        final int players = Integer.parseInt(st.nextToken());
        if (this.t.type.equals(type) && type.equals("Multiplayer")) {
            final int n = players;
            @SuppressWarnings("unused")
            final MultiPlayerPanel multiplayerPanel = this.t.multiplayerPanel;
            if (n == MultiPlayerPanel.players.length) {
                return;
            }
        }
        if (!this.t.type.equals(type) || !type.equals("Speed")) {
            this.t.updateNext(type, players);
            this.t.gameOver = true;
            this.repaint();
            this.t.requestFocus();
        }
    }
}
