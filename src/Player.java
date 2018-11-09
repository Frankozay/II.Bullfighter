public class Player {
    private int playerNumber;//玩家序号
    private int rank;//排名
    private String[] cardNums;//牌数，按从小到大排序
    private String[] cardColors;//牌花，按牌数顺序对应
    private String playerName;//玩家姓名
    private Poker[] pokers = new Poker[5];//牌面，如♠5♠8♠9♠10♠J
    private String shape;//牌型，如牛七
    private static int nextNumber = 0;//编号

    public Player(String name) {
        playerNumber = ++nextNumber;
        playerName = name;
    }//构造

    public void setPokers(Poker[] pokers) {
        for(int i=0;i<5;i++){
            this.pokers[i] = pokers[i];
        }
    }//牌面

    public void setShape(String shape) {
        this.shape = shape;
    }//牌型

    public void setRank(int rank) {
        this.rank = rank;
    }//排名

    public void setCardColors(String[] cardColors) {
        this.cardColors = cardColors;
    }//牌的花色

    public void setCardNums(String[] cardNums) {
        this.cardNums = cardNums;
    }//牌的点数

    public String[] getCardColors() {
        return cardColors;
    }//一组牌花色

    public String[] getCardNums() {
        return cardNums;
    }//一组牌点数

    public String getShape() {
        return shape;
    }//牌型

    public Poker[] getPokers() {
        return pokers;
    }//牌面

    public int getRank() {
        return rank;
    }//得到排名

    public int getPlayerNumber() {
        return playerNumber;
    }//得到玩家序号

    public String getPlayerName() {
        return playerName;
    }//得到玩家姓名
}
