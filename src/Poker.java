import java.util.Random;

public class Poker {
    final private String[] colors = {"♠", "♥", "♣", "♦"};
    final private String[] nums = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
    private String[] cards = new String[52];

    {
        int k = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 13; j++) {
                cards[k++] = colors[i] + nums[j];
            }
        }

    }//将牌入库

    public Poker() {
    }

    public String[] Shuffle() {
        int len = 52;
        int index;
        String[] newCards = new String[52];
        Random random = new Random();

        for (int i = 0; i < cards.length; i++) {
            index = Math.abs(random.nextInt() % len--);
            newCards[i] = cards[index];
            cards[index] = cards[len];
        }
        return newCards;
    }//洗牌

    public String[][] Deal(int groupCount, int frame, String[] card) {//发牌，几组几张什么牌
        String[][] dealCard = new String[groupCount][frame];
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

    public String getNums(String card) {//得到牌点数,如♠8得到8
        return card.substring(1, card.length());
    }

    public String getColors(String card) {
        return card.substring(0, 1);
    }//得到牌花色

}
