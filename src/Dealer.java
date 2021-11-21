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
}
