import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;

class BlackjackTest {
    Dealer dealer;
    Deck dealerDeck;
    Blackjack blackjack;
    Player player1, player2, player3;
    List<Player> playerList;

    @BeforeEach
    void setUp() {
        dealer = Dealer.getInstance();
        dealerDeck = new Deck();
        player1 = new Player("Player1", new Stack<Card>());
        player2 = new Player("Player2", new Stack<Card>());
        player3 = new Player("Player1", new Stack<Card>());
        playerList = new ArrayList<>();
        playerList.add(player1);
        playerList.add(player2);
        playerList.add(player3);
    }

    @Test
    public void removeBustPlayers() {
        player2.setStatus(Status.GO_BUST);
        Blackjack.removeBustPlayersFromGame(playerList,dealer);
        assertEquals(2,playerList.size());
    }

    @Test
    void addPlayersToGame() {
    }
}