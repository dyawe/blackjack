import java.util.Stack;

public class Player {

    private String name;
    private Stack<Card> myHand = new Stack<>();
    private Status status;


    public Player(String name, Stack<Card> myHand) {
        this.myHand = myHand;
        this.name = name;
        status = Status.PLAY;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMyHand(Card card) {
        myHand.push(card);
    }

    public int getMyHandValue() {
        return (int) myHand.stream().mapToInt(Card::getValue).summaryStatistics().getSum();
    }


    public Stack<Card> getMyHand() {
        return myHand;
    }

    public void canPlayerPlay() {
        int handValue = getMyHandValue();
        if (handValue<17){
            setStatus(Status.HIT);
        } else if (handValue>17 && handValue<21){
            setStatus(Status.STICK);
        } else{
            setStatus(Status.GO_BUST);
        }
    }
}
