public class Poker {
    private String color;
    private String num;


    public Poker(String color, String num) {
        this.color = color;
        this.num = num;
    }

    public String getNum() {//得到牌点数,如♠8得到8
        return this.num;
    }

    public String getColor() {
        return this.color;
    }//得到牌花色
    public String getCard() {
        return this.color+this.num;
    }
}
