import javax.swing.*;

import java.awt.*;

public class SpeedPanel extends JPanel
{
    @SuppressWarnings("unused")
	private Table table;
    boolean paused;
    static long pastTime;
    static long startTime;
    static long finalTime;
    static Player player;
    static int size;
    static int border;
    static int chosen;
    
    private static final long serialVersionUID = 1L;
    
    static {
        SpeedPanel.size = 20;
        SpeedPanel.border = 20;
        SpeedPanel.chosen = 0;
    }
    
    public SpeedPanel(final Player p, final Table t) {
        super();
        this.paused = false;
        SpeedPanel.pastTime = 0L;
        SpeedPanel.startTime = System.currentTimeMillis();
        this.setPreferredSize(new Dimension(300, 500));
        this.setBackground(Color.black);
        this.setVisible(true);
        SpeedPanel.player = p;
    }
    
    @Override
    public void paint(final Graphics g) {
        if (!this.paused) {
            g.setColor(Color.black);
            g.fillRect(0, 0, 300, 500);
            int t = (int)(System.currentTimeMillis() - SpeedPanel.startTime + SpeedPanel.pastTime);
            final int ms = t % 1000;
            t /= 1000;
            final int s = t % 60;
            t /= 60;
            final int m = t % 60;
            @SuppressWarnings("unused")
			final int h;
            t = (h = t / 60);
            final String str = String.format("Time: %02d:%02d:%03d", m, s, ms);
            g.setColor(Color.white);
            g.setFont(new Font("Century Gothic", 1, 32));
            g.drawString("Name: " + SpeedPanel.player.name.toUpperCase(), 0, 60);
            g.drawString("Score: " + SpeedPanel.player.score, 0, 100);
            g.drawString(str, 0, 140);
        }
    }
    
    public void update(final int cards) {
        final Player player = SpeedPanel.player;
        player.score += cards;
        this.repaint();
        this.requestFocus();
    }
    
    public void pause() {
        if (!this.paused) {
            this.paused = true;
            SpeedPanel.pastTime += System.currentTimeMillis() - SpeedPanel.startTime;
        }
    }
    
    public void unpause() {
        if (this.paused) {
            this.paused = false;
            SpeedPanel.startTime = System.currentTimeMillis();
        }
    }
}
