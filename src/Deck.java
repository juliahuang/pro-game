import java.util.*;

public class Deck
{
    Card[] cards;
    int[] order;
    
    public Deck() {
        super();
        final boolean[] array = new boolean[6];
        this.cards = new Card[63];
        this.order = new int[63];
        for (byte i = 1; i < 64; ++i) {
            final byte j = i;
            for (int k = 0; k < 6; ++k) {
                array[k] = ((j >> k & 0x1) == 0x1);
            }
            this.cards[i - 1] = new Card(array);
        }
        this.shuffle();
    }
    
    void shuffle() {
        final LinkedList<Integer> list = new LinkedList<Integer>();
        for (int i = 0; i < 63; ++i) {
            list.add(i);
        }
        for (int i = 0; i < this.cards.length; ++i) {
            this.order[i] = list.remove((int)(Math.random() * (this.cards.length - i)));
        }
        this.reorder();
    }
    
    void reorder() {
        final Card[] temp = new Card[this.cards.length];
        for (int i = 0; i < this.cards.length; ++i) {
            temp[i] = this.cards[this.order[i]];
        }
        this.cards = temp;
    }
}
