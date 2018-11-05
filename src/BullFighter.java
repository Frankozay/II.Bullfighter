import java.util.Random;
import java.util.regex.Pattern;

public class BullFighter {
    private Poker poker = new Poker();
    private Player[] players;
    private int numbers;//玩家人数
    private String card[];
    final static private String[] cardShape = {"炸弹", "五小", "五花", "四花", "牛牛", "牛九", "牛八", "牛七", "牛六", "牛五", "牛四", "牛三",
            "牛二", "牛一", "没牛"};//最小牌型22233
    final static private String[] colors = {"♦", "♣", "♥", "♠"};
    final static private String[] nums = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};

    public BullFighter(Player[] players) {//构造方法
        this.players = players;
        numbers = players.length;
    }

    public void shuffle() {
        card = poker.Shuffle();
    }//洗牌

    public void deal() {
        String[][] dealCards = poker.Deal(numbers, 5, card);
        for (int i = 0; i < numbers; i++) {
            players[i].setPatterns(dealCards[i]);
        }

    }//发牌

    private static String Judge(String[] num) {//根据牌面判断牌型
        //判断炸弹
        for (int i = 0; i < 2; i++) {
            int count = 0;
            for (int j = 0; j < 5; j++) {
                if (num[i].equals(num[j])) count++;
            }
            if (count == 4) return cardShape[0];//若有四张同样点数
        }

        //判断五小
        int count = 0;
        boolean bigger = false;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 13; j++) {
                if (num[i].equals(nums[j])) {
                    if (j > 3) {
                        bigger = true;
                    } else count += j + 1;
                }
            }
            if (bigger) break;//若有比五大的则退出
        }
        if (!bigger && count <= 10) return cardShape[1];

        //判断五花
        boolean isColor = true;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 13; j++) {
                if (num[i].equals(nums[j])) {
                    if (j < 10) isColor = false;//若有比j小的则跳过
                }
            }
            if (!isColor) break;
        }
        if (isColor) return cardShape[2];

        //判断四花
        int colorCount = 0;
        int tenCount = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 13; j++) {
                if (num[i].equals(nums[j])) {
                    if (j == 9) tenCount++;
                    else if (j > 9) colorCount++;
                }
            }
        }
        if (colorCount == 4 && tenCount == 1) return cardShape[3];


        int[] cardPoint = new int[5];//应该写在开头的。。上面都可以用
        int bullPoints = 0;//除去三张后剩余两张点数之和
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 13; j++) {
                if (num[i].equals(nums[j])) {
                    if (j >= 9) cardPoint[i] = 10;
                    else cardPoint[i] = j + 1;//将扑克牌面转化为点数，A为1，JQK为10
                }
            }
        }

        //判断牛牛
        for (int i = 0; i < 3; i++) {
            for (int j = i + 1; j < 4; j++) {
                for (int k = j + 1; k < 5; k++) {
                    if ((cardPoint[i] + cardPoint[j] + cardPoint[k]) % 10 == 0) {//其中三张为10倍数时
                        bullPoints = 0;//剩余两张置0
                        for (int temp = 0; temp < 5; temp++) {
                            if (temp != i && temp != j && temp != k) {
                                bullPoints += cardPoint[temp];
                            }
                        }
                        if (bullPoints % 10 == 0) return cardShape[4];
                    }
                }
            }
        }

        //判断牛几
        bullPoints = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = i + 1; j < 4; j++) {
                for (int k = j + 1; k < 5; k++) {
                    if ((cardPoint[i] + cardPoint[j] + cardPoint[k]) % 10 == 0) {
                        bullPoints = 0;
                        for (int temp = 0; temp < 5; temp++) {
                            if (temp != i && temp != j && temp != k) {
                                bullPoints += cardPoint[temp];
                            }
                        }
                    }
                    bullPoints %= 10;
                }
            }
        }
        if (bullPoints > 0) return cardShape[14 - bullPoints];

        //没牛
        return cardShape[14];


    }

    public void Assign() {//先将牌型赋值给players。
        for (int i = 0; i < numbers; i++) {
            String[] pattern = players[i].getPatterns();//得到牌面
            String[] color = new String[5];//牌花色
            String[] num = new String[5];//牌点数
            int j = 0;
            for (String temp : pattern
                    ) {
                color[j] = poker.getColors(temp);
                num[j++] = poker.getNums(temp);
            }//赋值
            for (int i1 = 0; i1 < num.length - 1; i1++) {
                for (int j1 = i1; j1 < num.length; j1++) {
                    int qian = 0;
                    int hou = 0;
                    for (int k = 0; k < nums.length; k++) {
                        if (num[i1].equals(nums[k])) {
                            qian = k;
                        }
                        if (num[j1].equals(nums[k])) {
                            hou = k;
                        }
                    }
                    if (qian >= hou) {
                        String temp = num[i1];
                        num[i1] = num[j1];
                        num[j1] = temp;
                        temp = color[i1];
                        color[i1] = color[j1];
                        color[j1] = temp;
                    }
                }
            }//依点数大小排序
            for (int i1 = 0; i1 < num.length - 1; i1++) {
                for (int j1 = i1 + 1; j1 < num.length; j1++) {
                    if (num[i1].equals(num[j1])) {
                        int qian = 0;
                        int hou = 0;
                        for (int k = 0; k < colors.length; k++) {
                            if (color[i1].equals(colors[k])) {
                                qian = k;
                            }
                            if (color[j1].equals(colors[k])) {
                                hou = k;
                            }
                        }
                        if (qian >= hou) {
                            String temp = num[i1];
                            num[i1] = num[j1];
                            num[j1] = temp;
                            temp = color[i1];
                            color[i1] = color[j1];
                            color[j1] = temp;
                        }
                    }
                }
            }//如有相同大小牌，依花色排序。
            players[i].setCardColors(color);
            players[i].setCardNums(num);
            players[i].setShape(Judge(num));
        }
    }

    public void compare() {//比较
        Assign();
        int[] tempRank = new int[numbers];//储存各玩家排名
        int[] type = new int[numbers];//储存各玩家牌型对应cardShape数组的下标
        for (int i = 0; i < numbers; i++) {
            for (int j = 0; j < cardShape.length; j++) {
                if (cardShape[j].equals(players[i].getShape())) {
                    type[i] = j;
                }
            }
        }//赋值下标
        for (int i = 0; i < numbers; i++) {
            int rank = 1;
            for (int j = 0; j < numbers; j++) {
                if (i != j) {
                    if (type[i] > type[j]) rank++;
                }
            }
            tempRank[i] = rank;
        }//暂时赋值排名，先将每个玩家设为第一，与数组中除自己的所有元素比较，若下标比一个大则加一。

        for (int i = 0; i < numbers; i++) {//对有多个名次相等，即牌型相等的进行细化处理。
            for (int j = 0; j < numbers; j++) {
                String[] t1 = players[i].getCardNums();
                String[] t2 = players[j].getCardNums();
                String[] c1 = players[i].getCardColors();
                String[] c2 = players[j].getCardColors();
                if (i != j) {
                    if (tempRank[i] == tempRank[j]) {
                        if (type[i] == 0) {//炸弹单独处理
                            String mark1;
                            String mark2;
                            int markOne = 0;
                            int markTwo = 0;
                            if (t1[0].equals(t1[1])) mark1 = t1[0];
                            else mark1 = t1[2];
                            if (t2[0].equals(t2[1])) mark2 = t2[0];
                            else mark2 = t2[2];
                            for (int i1 = 0; i1 < nums.length; i1++) {
                                if (mark1.equals(nums[i1])) markOne = i1;
                                if (mark2.equals(nums[i1])) markTwo = i1;
                            }
                            if (markOne > markTwo) tempRank[j]++;
                            else tempRank[i]++;
                        } else {
                            int numMark1 = 0;
                            int numMark2 = 0;
                            for (int i1 = 0; i1 < nums.length; i1++) {
                                if (t1[4].equals(nums[i1])) numMark1 = i1;
                                if (t2[4].equals(nums[i1])) numMark2 = i1;
                            }
                            if (numMark1 > numMark2) tempRank[j]++;
                            else if (numMark1 < numMark2) tempRank[i]++;//牌型相同时比最大牌大小
                            else {//若最大牌相同则比较花色
                                int colorMark1 = 0;
                                int colorMark2 = 0;
                                String[] color1 = players[i].getCardColors();
                                String[] color2 = players[j].getCardColors();
                                for (int i1 = 0; i1 < colors.length; i1++) {
                                    if (c1[4].equals(colors[i1])) colorMark1 = i1;
                                    if (c2[4].equals(colors[i1])) colorMark2 = i1;
                                }
                                if (colorMark1 > colorMark2) tempRank[j]++;
                                else if (colorMark1 < colorMark2) tempRank[i]++;
                            }
                        }
                    }
                }
            }
        }
        for (int i = 0; i < numbers; i++) {
            players[i].setRank(tempRank[i]);
        }
    }


}
