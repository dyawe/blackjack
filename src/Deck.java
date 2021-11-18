import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.stream.Stream;

public class Deck {

    Stack<Card> deck = new Stack<>();

    public Deck() {
    }

    public void clearDeck() {
        deck.clear();
    }

    public void shuffle() {
        Collections.shuffle(deck);
    }

    public Stack<Card> createDeck() {
        clearDeck();

        Stream.of(Suit.values()).forEach(suit -> {
            Stream.of(Value.values()).forEach(val -> {
                deck.push(new Card(suit, val));
            });
        });
        shuffle();
        return deck;
    }


    @Override
    public String toString() {
        return String.valueOf(deck.size());
    }




}

