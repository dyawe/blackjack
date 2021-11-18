import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.stream.Stream;

public class Deck {
    List<Player> players = new ArrayList();
    Stack<Card> deck = new Stack<>();

    public Deck(List<Player> players) {
        this.players = players;
    }

    public Stack<Card> createDeck() {
        clearDeck();
        Stream.of(Suit.values()).forEach(suit -> {
            Stream.of(Value.values()).forEach(val -> {
                deck.push(new Card(suit, val));
            });
        });

        return deck;
    }

    public void shuffle() {
        Collections.shuffle(deck);
    }

    public void dealCards() {
//        players.stream().forEach(player -> player.setMyHand(deck.pop()));
        players.stream().forEach(player -> {
            if (player.getMyHand().isEmpty()) {
                player.setMyHand(deck.pop());
                player.setMyHand(deck.pop());
            } else {
                player.setMyHand(deck.pop());
            }
        });

    }

    public void clearDeck() {
        deck.clear();
    }
}

