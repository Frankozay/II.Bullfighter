import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int numbers;//人数
        System.out.println("请输入玩家人数：");
        numbers = input.nextInt();
        input.nextLine();
        Player[] players = new Player[numbers];
        System.out.println("请输入各玩家姓名:");
        for (int i = 0; i < numbers; i++) {
            players[i] = new Player(input.nextLine());
        }
        System.out.println("开始游戏！");
        BullFighter bullFighter = new BullFighter(players);
        System.out.println("洗牌中");
        bullFighter.shuffle();
        System.out.println("发牌中");
        bullFighter.deal();
        System.out.println("各玩家的手牌为:");
        for (int i = 0; i < numbers; i++) {
            System.out.print(players[i].getPlayerNumber() + "." + players[i].getPlayerName() + ":      ");
            for (Poker temp : players[i].getPokers()
                    ) {
                System.out.print(temp.getCard() + " ");
            }
            System.out.println();
        }
        bullFighter.compare();
        System.out.println();
        System.out.println("各玩家的牌型为:");
        for (int i = 0; i < numbers; i++) {
            System.out.println(players[i].getPlayerNumber() + "." + players[i].getPlayerName() + ":   " + players[i].getShape());
        }
        System.out.println("比大小！");
        System.out.println("各玩家名次:");
        for (int i = 0; i < numbers; i++) {
            System.out.println(players[i].getPlayerNumber() + "." + players[i].getPlayerName() + ":" + players[i].getRank());
        }
        for (int i = 0; i < numbers; i++) {
            if (players[i].getRank() == 1) {
                System.out.println("赢家是:" + players[i].getPlayerName() + "\n" + "他的牌面和牌型是:");
                for (Poker temp : players[i].getPokers()
                        ) {
                    System.out.print(temp.getCard() + " ");
                }
                System.out.println(players[i].getShape());
            }

        }
    }
}
