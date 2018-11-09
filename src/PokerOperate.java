import java.util.Random;

public class PokerOperate {
    final private String[] colors = {"♠", "♥", "♣", "♦"};
    final private String[] nums = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
    private Poker[] cards = new Poker[52];



    public PokerOperate() {

    }

    public Poker[] newCard(){
        int k = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 13; j++) {
                cards[k++] = new Poker(colors[i],nums[j]);
            }
        }
        //将牌按顺序入库,得到一副新牌.
        return cards;
    }

    public Poker[] Shuffle(Poker[] cards) {
        int len = 52;
        int index;
        Poker[] newCards = new Poker[52];
        Random random = new Random();

        for (int i = 0; i < cards.length; i++) {
            index = Math.abs(random.nextInt() % len--);
            newCards[i] = cards[index];
            cards[index] = cards[len];
        }
        return newCards;
    }//洗牌

    public Poker[][] Deal(int groupCount, int frame, Poker[] card) {//发牌，几组几张什么牌
        Poker[][] dealCard = new Poker[groupCount][frame];
        int len = card.length;
        int index;
        Random random = new Random();

        for (int i = 0; i < groupCount; i++) {
            for (int j = 0; j < frame; j++) {
                index = Math.abs(random.nextInt() % len--);
                dealCard[i][j] = card[index];
                card[index] = card[len];
            }
        }
        return dealCard;
    }



}
