import java.util.List;

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
        players.stream().forEach(player -> {
            if (player.getMyHand().isEmpty()) {
                player.setMyHand(dealerDeck.deck.pop());
                player.setMyHand(dealerDeck.deck.pop());
            } else {
                player.setMyHand(dealerDeck.deck.pop());
            }
        });
    }
//probably works...or not...test and see
    public void removeBustPlayers(List<Player> players){
        players.stream().
                filter(player -> player.getStatus().equals(Status.GO_BUST)).
                forEach(player -> players.remove(player));

    }

    //check who won
}
