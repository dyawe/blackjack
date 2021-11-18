import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class Blackjack {
    //polish up the main to get a fully functional program
    public static void main(String[] args) {
        var numberOfPlayers=0;
        List<Player> playerList = new ArrayList<>();
        Deck deck = new Deck();
        Dealer dealer = Dealer.getInstance();

        do {
            System.out.println("Welcome to Blackjack World\nPlease enter the number of players");
            Scanner sc = new Scanner(System.in);
            numberOfPlayers = sc.nextInt();
        }
        while (numberOfPlayers<1);

        for(int i = 0; i<numberOfPlayers;i++){
            playerList.add(new Player(String.format("Player%d",i),new Stack<>()));
        }

        var canPlay = true;
        deck.createDeck();
        dealer.setDealerDeck(deck);
        int turn = 1;
        while (canPlay){

            canPlay = false;
//            System.out.println("@@@@@@@@@@@@@@@@@");
            System.out.println(dealer.getDealerDeck());
            dealer.dealCards(playerList);
            turn++;
            /* playerList.stream().forEach(player -> System.out.println(player.getName()
                    +" -> " +player.getMyHand() + " -> "+  player.getMyHandValue() ));*/
        }

    }
}
