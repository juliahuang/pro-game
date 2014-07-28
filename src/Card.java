import java.awt.*;

public class Card
{
    Color[] colors;
    boolean[] dots;
    
    public Card(final boolean[] array) {
        this.colors = new Color[] { Color.red, Color.orange, Color.yellow, Color.green, Color.blue, Color.magenta };
        this.dots = array.clone();
    }
}
