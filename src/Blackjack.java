import java.util.*;

public class Blackjack {
    //polish up the main to get a fully functional program

    public static void main(String[] args) {

        var numberOfPlayers = 0;
        int shufflingMethod;
        List<Player> playerList = new ArrayList<>();
        Deck deck = new Deck();
        Dealer dealer = Dealer.getInstance();

        do {
            numberOfPlayers = takeNumPlayersFromUser();
            shufflingMethod = takeShufflingMethodFromUser();
        }
        while ((numberOfPlayers < 2 || numberOfPlayers > 6) && (shufflingMethod < 1 || shufflingMethod > 2));

        addPlayersToGame(numberOfPlayers, playerList);
        setStrategyForPlayers(playerList);
        createDeck(deck, dealer,shufflingMethod);

        int turn = 1;
        while (!dealer.checkIfGameEnded(playerList)) {
            playerCardsLog(playerList, "value of turn at the start:" + turn);

            if (turn == 1) {
                dealToPlayers(dealer, playerList);
                updatePlayersStatus(playerList);

            } else {
                dealer.applyHitOnPlayer(playerList);
                updatePlayersStatus(playerList);
                playerCardsLog(playerList, "Players before bust method is applied");
                removeBustPlayersFromGame(playerList, dealer);

            }
            playerCardsLog(playerList, "value of turn at the end:" + turn);
            turn++;

        }

        dealer.checkIfPlayerWon(playerList);
    }

    private static void setStrategyForPlayers(List<Player> playerList) {

        playerList.forEach(player -> {
            int randomNum = new Random().nextInt(3 + 1);
            printMessage("value of random number for strategy:" + randomNum);
            switch (randomNum) {
                case 0 -> player.setStrategy(Strategy.STRATEGY_DEFAULT);
                case 1 -> player.setStrategy(Strategy.STRATEGY_HIT);
                case 2 -> player.setStrategy(Strategy.STRATEGY_STICK);
                default -> player.setStrategy(Strategy.STRATEGY_CALCULATOR);
            }
        });
    }

    public static void removeBustPlayersFromGame(List<Player> playerList, Dealer dealer) {
        playerList.removeAll(dealer.IdentifyBustPlayers(playerList));

    }

    private static void printMessage(String message) {
        System.out.println(message);
    }

    private static int takeNumPlayersFromUser() {
        printMessage("Welcome to Blackjack World\nPlease enter the number of players");
        Scanner sc = new Scanner(System.in);
        var input = sc.next();
        return input.isEmpty()? 3: Integer.parseInt(input);

    }

    private static int takeShufflingMethodFromUser() {
        printMessage("Please choose the shuffling mechanism to be performed by entering the number associated:");
        printMessage("1. Riffle Shuffle \n2. Pharaoh Shuffle");
        Scanner sc = new Scanner(System.in);
        return sc.nextInt();
    }

    private static void playerCardsLog(List<Player> list, String logMessage) {
        printMessage(logMessage);
        list.forEach(player -> System.out.println(player.getName()
                + " -> " + player.getMyHand() + " -> " + player.getMyHandValue() + " -->" + player.getStatus()
                + " -->" + player.getStrategy()))
        ;
    }

    public static void addPlayersToGame(int numPlayers, List<Player> list) {
        for (int i = 0; i < numPlayers; i++) {
            list.add(new Player(String.format("Player%d", i), new Stack<>()));
        }
    }

    private static void createDeck(Deck deck, Dealer dealer, int shufflingMethod) {
        //If we had to implement real shuffling mechanism we would have called two methods, one for each if statement
        if (shufflingMethod ==1){
            deck.createDeck();
            dealer.setDealerDeck(deck);
        } else{
            deck.createDeck();
            dealer.setDealerDeck(deck);
        }

    }

    private static void dealToPlayers(Dealer dealer, List<Player> list) {
        dealer.dealCards(list);
    }

    private static void updatePlayersStatus(List<Player> list) {
        list.forEach(Player::applyStrategy);
    }

}
