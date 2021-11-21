import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;

class DealerTest {

    Dealer dealer = Dealer.getInstance();
    Deck dealerDeck = new Deck();
    Player player1 = new Player("Player1", new Stack<Card>());
    Player player2 = new Player("Player2", new Stack<Card>());
    Player player3 = new Player("Player1", new Stack<Card>());
    List<Player> playerList = List.of(player1,
            player2, player3);


    /*@Test
    void getDealerDeck() {
    }

    @Test
    void setDealerDeck() {
    }*/

    @Test
    void dealCards() {
        dealerDeck.createDeck();
        dealer.setDealerDeck(dealerDeck);
        dealer.dealCards(playerList);
        assertNotEquals(0, player1.getMyHand().size());
    }

    @Test
    void IdentifyBustPlayers() {
        player2.setStatus(Status.GO_BUST);
        player3.setStatus(Status.GO_BUST);
        assertEquals(2, dealer.IdentifyBustPlayers(playerList).size());
    }

    @Test
    void applyHitOnPlayer() {
        dealerDeck.createDeck();
        dealer.setDealerDeck(dealerDeck);
        player3.setStatus(Status.HIT);
        dealer.applyHitOnPlayer(playerList);
        assertEquals(1,player3.getMyHand().size());

    }

    @Test
    void checkIfPlayerWon() {
        /*player2.setMyHand(new Card(Suit.CLUB,Value.ACE));
        player2.setMyHand(new Card(Suit.CLUB,Value.NINE));

        player1.setMyHand(new Card(Suit.CLUB,Value.TWO));
        player1.setMyHand(new Card(Suit.CLUB,Value.TEN));

        player3.setMyHand(new Card(Suit.CLUB,Value.FIVE));
        player3.setMyHand(new Card(Suit.CLUB,Value.TEN));

        List<Player> testList = List.of(player2);*/
    }

    @Test
    void checkIfGameEnded() {
        player2.setMyHand(new Card(Suit.CLUB,Value.ACE));
        player2.setMyHand(new Card(Suit.CLUB,Value.TEN));
        List<Player> testList = List.of(player2);
        assertTrue(dealer.checkIfGameEnded(testList));
    }

    @Test
    void playerHit21() {
        player2.setMyHand(new Card(Suit.CLUB,Value.ACE));
        player2.setMyHand(new Card(Suit.CLUB,Value.TEN));
        List<Player> testList = List.of(player2);
        assertTrue(dealer.playerHit21(testList));
    }

    @Test
    void playerDontHit21() {
        player2.setMyHand(new Card(Suit.CLUB,Value.ACE));
        player2.setMyHand(new Card(Suit.CLUB,Value.NINE));
        List<Player> testList = List.of(player2);
        assertFalse(dealer.playerHit21(testList));
    }

    @Test
    void checkIfAllPlayersStick() {
        player2.setStatus(Status.STICK);
        player3.setStatus(Status.STICK);
        List<Player> testList = List.of(player2,player3);
        assertTrue(dealer.checkIfAllPlayersStick(testList));
    }

    @Test
    void checkIfAllPlayersStickFalse() {
        player2.setStatus(Status.PLAY);
        player3.setStatus(Status.STICK);
        List<Player> testList = List.of(player2,player3);
        assertFalse(dealer.checkIfAllPlayersStick(testList));
    }

    @Test
    void checkIfOnlyOnePlayerRemaining() {
        List<Player> testList = List.of(player2);
        assertTrue(dealer.checkIfOnlyOnePlayerRemaining(testList));
    }
}