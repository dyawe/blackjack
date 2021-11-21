import java.util.Random;
import java.util.Stack;

public class Player {

    private String name;
    private Stack<Card> myHand = new Stack<>();
    private Status status;
    private Strategy strategy;


    public Player(String name, Stack<Card> myHand) {
        this.myHand = myHand;
        this.name = name;
        status = Status.PLAY;
        strategy = Strategy.STRATEGY_DEFAULT;
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

    public Strategy getStrategy() {
        return strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public Stack<Card> getMyHand() {
        return myHand;
    }

    public void updatePlayerStatusDefaultStrategy() {
        int handValue = getMyHandValue();
        if (handValue < 17) {
            setStatus(Status.HIT);
        } else if (handValue >= 17 && handValue <= 21) {
            setStatus(Status.STICK);
        } else {
            setStatus(Status.GO_BUST);
        }
    }

    public void applyStrategy() {
        switch (strategy) {
            case STRATEGY_HIT -> {
                if (getMyHandValue() > 21) {
                    setStatus(Status.GO_BUST);
                } else {
                    setStatus(Status.HIT);
                }
            }
            case STRATEGY_STICK -> setStatus(Status.STICK);
            case STRATEGY_CALCULATOR -> riskCalculation();
            default -> updatePlayerStatusDefaultStrategy();
        }
    }

    private void riskCalculation() {
        String status;
        if (getMyHandValue() >= 1 && getMyHandValue() <= 5) {
            status = determineActionToApply(Constant.oneToFive);
            setPlayerStatus(status);
        } else if (getMyHandValue() >= 6 && getMyHandValue() <= 10) {
            status = determineActionToApply(Constant.sixToTen);
            setPlayerStatus(status);
        } else if (getMyHandValue() >= 11 && getMyHandValue() <= 15) {
            status = determineActionToApply(Constant.elevenToFifteen);
            setPlayerStatus(status);
        } else if (getMyHandValue() >= 16 && getMyHandValue() <= 20) {
            status = determineActionToApply(Constant.sixteenToTwenty);
            setPlayerStatus(status);
        } else if (getMyHandValue() > 21) {
            setStatus(Status.GO_BUST);
        }
    }

    private void setPlayerStatus(String status) {
        if ("hit".equals(status)) {
            setStatus(Status.HIT);
        } else {
            setStatus(Status.STICK);
        }
    }

    public String determineActionToApply(StrategyProbability probability) {
        Random random = new Random();
        int ranNum = random.nextInt(100 + 1);
        System.out.println("value of random number:" + ranNum);
        if (ranNum <= probability.getHit()) {
            return "hit";
        } else {
            return "stick";
        }
    }
}
