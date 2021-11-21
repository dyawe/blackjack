import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class Dealer {
    private Deck dealerDeck = new Deck();
    private static Dealer single_instance = null;

    public Dealer(Deck deck) {
        this.dealerDeck = deck;

    }

    public static Dealer getInstance() {
        if (single_instance == null) {
            single_instance = new Dealer(new Deck());
        }
        return single_instance;
    }

    public Deck getDealerDeck() {
        return dealerDeck;
    }

    public void setDealerDeck(Deck dealerDeck) {
        this.dealerDeck = dealerDeck;
    }

    public void dealCards(List<Player> players) {
//        players.stream().forEach(player -> player.setMyHand(deck.pop()));
        players.forEach(player -> {
            if (player.getMyHand().isEmpty()) {
                player.setMyHand(dealerDeck.deck.pop());
                player.setMyHand(dealerDeck.deck.pop());
            }

        });
    }

    //probably works...or not...test and see
    public List<Player> IdentifyBustPlayers(List<Player> players) {
        List<Player> playerList = new ArrayList<>();
        for (Player player: players){
            if (player.getStatus().equals(Status.GO_BUST)){
             playerList.add(player);
            }
        }
        return playerList;
    }

    public void applyHitOnPlayer(List<Player> playerList) {
        playerList.forEach(player -> {
            if (player.getStatus().equals(Status.HIT)) {
                player.setMyHand(dealerDeck.deck.pop());
            }
        });
    }

    public void checkIfPlayerWon(List<Player> playerList) {
        Optional<Player> winner = playerList.stream().filter(i -> i.getMyHandValue() <= 21)
                .max(Comparator.comparing(Player::getMyHandValue));
        if (winner.isPresent()) {
            System.out.println("The winner is : " + winner.get().getName() + " " + "with a hand value of:" + winner.get().getMyHandValue());
        } else {
            System.out.println("There are no winner.");
        }
    }

    public boolean checkIfGameEnded(List<Player> list) {
        if (playerHit21(list) || checkIfAllPlayersStick(list) || checkIfOnlyOnePlayerRemaining(list)) {
            System.out.println("The game has ended");
            System.out.println("Result incoming......");
            return true;
        }
        return false;
    }

    public boolean playerHit21(List<Player> list) {

        for (Player player : list) {
            if (player.getMyHandValue() == 21) {
                return true;
            }
        }
        return false;
    }

    public boolean checkIfAllPlayersStick(List<Player> list) {
        long playersOnStick = list.stream().map(Player::getStatus)
                .filter(i -> i.equals(Status.STICK))
                .count();
        return playersOnStick == list.size();
    }

    public boolean checkIfOnlyOnePlayerRemaining(List<Player> list) {
        return list.size() == 1;
    }


    //check who won

    static class DealerTest {

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
            Assertions.assertNotEquals(0, player1.getMyHand().size());
        }

        @Test
        void IdentifyBustPlayers() {
            player2.setStatus(Status.GO_BUST);
            player3.setStatus(Status.GO_BUST);
            Assertions.assertEquals(2, dealer.IdentifyBustPlayers(playerList).size());
        }

        @Test
        void applyHitOnPlayer() {
            dealerDeck.createDeck();
            dealer.setDealerDeck(dealerDeck);
            player3.setStatus(Status.HIT);
            dealer.applyHitOnPlayer(playerList);
            Assertions.assertEquals(1,player3.getMyHand().size());

        }

        @Test
        void checkIfPlayerWon() {
        }

        @Test
        void checkIfGameEnded() {
        }

        @Test
        void playerHit21() {
        }

        @Test
        void checkIfAllPlayersStick() {
        }

        @Test
        void checkIfOnlyOnePlayerRemaining() {
        }
    }
}
