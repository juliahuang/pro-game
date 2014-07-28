import java.awt.*;

import javax.swing.*;

import java.io.*;

public class GameRunner
{
    public static void main(String[] Args) throws IOException {
    	String playerName = (String)JOptionPane.showInputDialog("What is your name?");
    	if (playerName == null)
    		return;
        final JFrame frame = new JFrame("Pro!");
        frame.setDefaultCloseOperation(3);
        frame.setVisible(true);
        String type = "Speed";
        int numPlayers = 1;
        while (type.length() > 0) {
            if (type.equals("Speed")) {
                final String[] names = { playerName };
                final Game g = new Game("Speed", names);
                frame.add(g);
                frame.pack();
                g.setVisible(true);
                g.requestFocus();
                while (!Game.t.gameOver() && !Game.t.gameOver) {}
                @SuppressWarnings("unused")
                final Table t = Game.t;
                type = Table.nextGame;
                numPlayers = Game.t.nextSize;
                if (Game.t.gameOver) {
                    final Component[] comp = frame.getContentPane().getComponents();
                    for (int i = 0; i < comp.length; ++i) {
                        if (comp[i] instanceof Game) {
                            frame.remove(comp[i]);
                        }
                    }
                    continue;
                }
                @SuppressWarnings("unused")
                final SpeedPanel speedPanel = Game.t.speedPanel;
                final int score = (int)(SpeedPanel.finalTime / 1000L);
                int rank = 10;
                @SuppressWarnings("resource")
				final BufferedReader in = new BufferedReader(new FileReader("speedHighScores"));
                final Player[] highScorers = new Player[10];
                for (int j = 0; j < 10; ++j) {
                    final String info = in.readLine();
                    final String name = info.substring(0, info.indexOf(59));
                    final int playerScore = Integer.parseInt(info.substring(info.indexOf(59) + 1));
                    highScorers[j] = new Player(playerScore, name, null);
                }
                while (rank > 0 && highScorers[rank - 1].playerNumber > score) {
                    --rank;
                }
                if (rank < 10) {
                	@SuppressWarnings("unused")
                    final SpeedPanel speedPanel2 = Game.t.speedPanel;
                    final Player player = SpeedPanel.player;
                    final Component parentComponent = null;
                    final String message = "Please verify your name:";
                    final String title = "Name:";
                    final int messageType = 3;
                    final Icon icon = null;
                    final Object[] selectionValues = null;
                    @SuppressWarnings("unused")
                    final SpeedPanel speedPanel3 = Game.t.speedPanel;
                    player.name = (String)JOptionPane.showInputDialog(parentComponent, message, title, messageType, icon, selectionValues, SpeedPanel.player.name);
                }
                @SuppressWarnings("unused")
                final SpeedPanel speedPanel4 = Game.t.speedPanel;
                Label_0417: {
                    if (SpeedPanel.player.name != null) {
                    	@SuppressWarnings("unused")
                        final SpeedPanel speedPanel5 = Game.t.speedPanel;
                        if (SpeedPanel.player.name.length() != 0) {
                            break Label_0417;
                        }
                    }
                    @SuppressWarnings("unused")
                    final SpeedPanel speedPanel6 = Game.t.speedPanel;
                    SpeedPanel.player.name = "Unknown";
                }
                final Player[] array = highScorers;
                final int n = rank;
                final int number = score;
                @SuppressWarnings("unused")
                final SpeedPanel speedPanel7 = Game.t.speedPanel;
                array[n] = new Player(number, SpeedPanel.player.name, null);
                final PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("speedHighScores")));
                for (int k = 0; k < 10; ++k) {
                    final Player temp = highScorers[k];
                    final String name2 = temp.name;
                    final int playerScore2 = temp.playerNumber;
                    out.println(String.valueOf(name2) + ";" + playerScore2);
                }
                out.close();
                final JPanel leaderboard = new JPanel();
                leaderboard.setLayout(new GridBagLayout());
                final GridBagConstraints c = new GridBagConstraints();
                c.fill = 2;
                c.weightx = 1.0;
                c.gridwidth = 3;
                c.gridx = 0;
                c.gridy = 0;
                leaderboard.add(new JLabel("Leaderboard:"), c);
                c.gridwidth = 1;
                final JLabel tempTempRank = new JLabel("Rank");
                c.fill = 2;
                c.weightx = 0.1;
                c.gridx = 0;
                c.gridy = 1;
                leaderboard.add(tempTempRank, c);
                final JLabel tempTempName = new JLabel("Name");
                c.fill = 2;
                c.weightx = 0.6;
                c.gridx = 1;
                c.gridy = 1;
                leaderboard.add(tempTempName, c);
                final JLabel tempTempScore = new JLabel("Score");
                c.fill = 2;
                c.weightx = 0.3;
                c.gridx = 2;
                c.gridy = 1;
                leaderboard.add(tempTempScore, c);
                for (int l = 0; l < 10; ++l) {
                    Font f = new Font(Game.t.getName(), 0, 12);
                    if (rank == l) {
                        f = new Font(Game.t.getName(), 1, 12);
                    }
                    final JLabel tempRank = new JLabel(new StringBuilder().append(l + 1).toString());
                    tempRank.setFont(f);
                    c.fill = 2;
                    c.weightx = 0.2;
                    c.gridx = 0;
                    c.gridy = l + 2;
                    leaderboard.add(tempRank, c);
                    final JLabel tempName = new JLabel(highScorers[l].name);
                    tempName.setFont(f);
                    c.fill = 2;
                    c.weightx = 0.5;
                    c.gridx = 1;
                    c.gridy = l + 2;
                    leaderboard.add(tempName, c);
                    final JLabel tempScore = new JLabel(new StringBuilder().append(highScorers[l].playerNumber).toString());
                    tempScore.setFont(f);
                    c.fill = 2;
                    c.weightx = 0.3;
                    c.gridx = 2;
                    c.gridy = l + 2;
                    leaderboard.add(tempScore, c);
                }
                c.fill = 2;
                c.weightx = 0.0;
                c.gridwidth = 3;
                c.gridx = 0;
                c.gridy = 12;
                leaderboard.add(new JLabel(" "), c);
                final Font f2 = new Font(Game.t.getName(), 0, 12);
                JLabel finalLine;
                if (rank == 10) {
                    finalLine = new JLabel("Sorry, your score of " + score + " was not high enough to make the leaderboard.");
                }
                else {
                    finalLine = new JLabel("Congratulations! Your score of " + score + " ranks " + (rank + 1) + ((rank == 0) ? "st" : ((rank == 1) ? "nd" : ((rank == 2) ? "rd" : "th"))) + " out of all players!");
                }
                finalLine.setFont(f2);
                c.fill = 2;
                c.gridy = 13;
                leaderboard.add(finalLine, c);
                final JLabel finalFinalLine = new JLabel("Play again?");
                finalFinalLine.setFont(f2);
                c.fill = 2;
                c.gridy = 14;
                leaderboard.add(finalFinalLine, c);
                final Component[] comp2 = frame.getContentPane().getComponents();
                for (int m = 0; m < comp2.length; ++m) {
                    if (comp2[m] instanceof Game) {
                        frame.remove(comp2[m]);
                    }
                }
            }
            else if (type.equals("Multiplayer")) {
            	@SuppressWarnings("unused")
                final boolean ok = false;
            	@SuppressWarnings("unused")
                final String result = "";
                final String[] names2 = new String[numPlayers];
                for (int i = 0; i < numPlayers; ++i) {
                    names2[i] = "Player " + (i + 1);
                }
                final Game g2 = new Game("Multiplayer", names2);
                frame.add(g2);
                frame.pack();
                g2.setVisible(true);
                g2.requestFocus();
                while (!Game.t.gameOver() && !Game.t.gameOver) {}
                @SuppressWarnings("unused")
                final Table t2 = Game.t;
                type = Table.nextGame;
                numPlayers = Game.t.nextSize;
                if (Game.t.gameOver) {
                    final Component[] comp3 = frame.getContentPane().getComponents();
                    for (int i2 = 0; i2 < comp3.length; ++i2) {
                        if (comp3[i2] instanceof Game) {
                            frame.remove(comp3[i2]);
                        }
                    }
                    continue;
                }
                @SuppressWarnings("unused")
                final MultiPlayerPanel multiplayerPanel = Game.t.multiplayerPanel;
                final Player[] players = MultiPlayerPanel.players;
                int highScore = players[0].score;
                @SuppressWarnings("unused")
                String winner = players[0].name;
                for (int k = 0; k < players.length; ++k) {
                    if (players[k].score > highScore) {
                        highScore = players[k].score;
                        winner = players[k].name;
                    }
                }
                final Component[] comp4 = frame.getContentPane().getComponents();
                for (int i3 = 0; i3 < comp4.length; ++i3) {
                    if (comp4[i3] instanceof Game) {
                        frame.remove(comp4[i3]);
                    }
                }
            }
            if (type == null) {
                break;
            }
        }
    }
}
