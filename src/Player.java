public class Player
{
    int playerNumber;
    String name;
    int score;
    CardPanel c;
    boolean chosenPlayer;
    
    public Player(final int number, final String s, final CardPanel cp) {
        super();
        this.playerNumber = number;
        this.name = s;
        this.score = 0;
        this.c = cp;
        this.chosenPlayer = (this.playerNumber == 1);
    }
}
