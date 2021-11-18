import java.util.Stack;

public class Player {
    private Stack <Card> myHand = new Stack<>();

    public Player(Stack<Card> myHand) {
        this.myHand = myHand;
    }

    public void setMyHand(Card card) {
        myHand.push(card);
    }

    public long getMyHandValue() {
        return myHand.stream().mapToInt(Card::getValue).summaryStatistics().getSum();
    }


    public Stack<Card> getMyHand() {
        return myHand;
    }
}
