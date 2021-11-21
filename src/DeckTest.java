import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeckTest {
    Deck deck = new Deck();

    @Test
    void clearDeck() {
        deck.clearDeck();
        assertEquals(0,deck.deck.size());
    }


    @Test
    void createDeck() {
        deck.createDeck();
        assertEquals(52,deck.deck.size());
    }
}