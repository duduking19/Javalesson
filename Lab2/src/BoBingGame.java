import java.util.Random;
import java.util.Scanner;

/**
 * 博饼游戏主程序。
 * 规则实现思路：
 * 1. 每位玩家每轮掷 6 个骰子。
 * 2. 用 countArr 统计 1~6 每个点数出现的次数。
 * 3. 调用 judgePrize 按“从大到小”的顺序判奖。
 * 4. 如果对应奖项库存 remainArr 还有剩余，就给玩家发奖。
 * 5. 所有奖项发完后，游戏结束并输出每位玩家的获奖情况。
 */
public class BoBingGame {
    /**
     * 根据本轮 6 个骰子的统计结果判定奖项。
     *
     * @param countArr 点数计数数组。下标 1~6 分别表示点数 1~6 出现了几次，
     *                 下标 0 不使用，这样可以直接写 countArr[4] 表示“4 出现次数”。
     * @param prizeArr 本轮命中的奖项标记数组，和 prizeNameArr/remainArr 的下标一一对应：
     *                 0=状元，1=对堂，2=三红，3=四进，4=二举，5=一秀。
     *                 某些情况下本轮可能同时命中多个普通奖项，所以这里用数组而不是单个变量。
     * @return 本轮判奖结果的文字说明。
     */
    public static String judgePrize(int[] countArr, int[] prizeArr){
        // 判奖一定要从大奖往小奖判断。
        // 原因是：很多小奖的特征会被大奖“包含”，如果先判小奖，会把大奖误判成小奖。

        // 以下几类都归入“状元”这一档，所以都记录到 prizeArr[0]。
        if(countArr[4]==6){
            prizeArr[0]++;
            return "六勃红";
        }
        else if(countArr[4]==5){
            prizeArr[0]++;
            return "五王";
        }
        else if(countArr[4]==4){
            prizeArr[0]++;
            if(countArr[1]==2){
                return "状元插金花";
            }
            else {
                return "四红";
            }
        }
        else if(countArr[1]==6) {
            prizeArr[0]++;
            return "遍地锦";
        }
        else if(countArr[2]==6 || countArr[3]==6 ||countArr[5]==6 || countArr[6]==6){
            prizeArr[0]++;
            return "六勃黑";
        }
        else if(countArr[2]==5 || countArr[3]==5 ||countArr[5]==5 || countArr[6]==5 || countArr[1]==5){
            prizeArr[0]++;
            return "五子";
        }
        else if(countArr[1]==1 && countArr[2]==1 && countArr[3]==1 && countArr[4]==1 && countArr[5]==1 && countArr[6]==1){
            prizeArr[1]++;
            return "对堂";
        }
        else {
            // 走到这里，说明没有命中“状元类”和“对堂”。
            // 接下来继续判断普通奖项。
            // 注意：普通奖项里可能同时命中多个，例如“四进 + 二举”，
            // 所以这里不能直接 return，而是把命中的结果拼接起来。
            String result = "";

            // 四进：出现 4 个相同点数。
            // 其中“4 个 4”已经在前面的“四红/状元插金花”里处理过，
            // 所以这里要排除 countArr[4] == 4 的情况。
            if((countArr[1]==4 && countArr[4]!=4) || (countArr[2]==4) || (countArr[3]==4) || (countArr[5]==4) || (countArr[6]==4)){
                prizeArr[3]++;
                result += "四进 ";
            }

            // 三红 / 二举 / 一秀 都只看“4 出现了几次”，三者互斥，所以这里用 if ... else if ... else if。
            if(countArr[4]==3){
                prizeArr[2]++;
                result += "三红 ";
            }
            else if(countArr[4]==2){
                prizeArr[4]++;
                result += "二举 ";
            }
            else if(countArr[4]==1){
                prizeArr[5]++;
                result += "一秀 ";
            }
            if(result.equals("")){
                // 一个奖项都没命中。
                return "未得奖";
            }
            return result.trim();
        }
    }

    /**
     * 判断游戏是否结束。
     *
     * @param remainArr 每种奖项的剩余数量。
     * @return 只要还有任意一个奖项没发完，游戏就不能结束；全部为 0 才返回 true。
     */
    public static boolean isGameOver(int[] remainArr){
        for(int i=0;i<remainArr.length;i++){
            if(remainArr[i]>0){
                return false;
            }
        }
        return true;
    }

    /**
     * 输出当前所有奖项的剩余数量。
     * prizeNameArr 和 remainArr 下标必须严格对应，否则显示会错位。
     */
    public static void printRemain(String[] prizeNameArr, int[] remainArr){
        System.out.print("剩余奖项：");
        for(int i=0;i<prizeNameArr.length;i++){
            System.out.print(prizeNameArr[i] + "：" + remainArr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int playerNum;

        // 先读取玩家人数。
        // 外层 while(true) 负责“范围是否合法”，内层 while 负责“输入类型是不是整数”。
        System.out.print("请输入玩家数（6-10）：");
        while(true){
            while(!scanner.hasNextInt()){
                System.out.print("输入有误，请重新输入玩家数（6-10）：");
                scanner.next();
            }
            playerNum = scanner.nextInt();
            if(playerNum>=6 && playerNum<=10){
                break;
            }
            System.out.print("输入有误，请重新输入玩家数（6-10）：");
        }

        // 三个数组下标都使用同一套奖项顺序，后面所有逻辑都依赖这个约定：
        // 0=状元，1=对堂，2=三红，3=四进，4=二举，5=一秀。
        String[] prizeNameArr = {"状元","对堂","三红","四进","二举","一秀"};

        // 每种奖项的库存数量。
        int[] remainArr = {1,2,4,8,16,32};

        // playerPrizeArr[i][j] 表示“第 i 位玩家获得了第 j 类奖项多少次”。
        int[][] playerPrizeArr = new int[playerNum][6];
        int round = 1;

        // 只要奖池里还有奖品，就持续进行下一轮。
        while(!isGameOver(remainArr)){
            System.out.println("第" + round + "轮：");
            for(int i=0;i<playerNum;i++){
                // 有可能奖品在本轮前面的玩家这里就发完了，
                // 所以后面的玩家进入循环后还要再检查一次。
                if(isGameOver(remainArr)){
                    break;
                }

                // countArr：统计本轮 1~6 各自出现了几次。
                // diceArr：按掷出顺序保存 6 个骰子的点数，用于展示给玩家看。
                int[] countArr = new int[7];
                int[] diceArr = new int[6];
                for(int j=0;j<6;j++){
                    int dice = random.nextInt(6)+1;
                    diceArr[j] = dice;
                    countArr[dice]++;
                }

                System.out.print("玩家" + (i+1) + "掷出的骰子点数为：");
                for(int j=0;j<6;j++){
                    System.out.print(diceArr[j] + " ");
                }
                System.out.println();

                // prizeArr 只表示“本轮理论上命中了哪些奖项”，
                // 真正能不能拿到，还要看奖池 remainArr 是否还有库存。
                int[] prizeArr = new int[6];
                String result = judgePrize(countArr, prizeArr);
                System.out.println("判定结果：" + result);

                // getPrize 记录“本轮最终是否真的领到了奖品”。
                // 因为存在一种情况：明明判中了，但该奖项已经发完，所以最后并没有拿到奖。
                boolean getPrize = false;
                for(int j=0;j<prizeArr.length;j++){
                    if(prizeArr[j]>0){
                        if(remainArr[j]>0){
                            // 有库存：奖池减 1，玩家对应奖项加 1。
                            remainArr[j]--;
                            playerPrizeArr[i][j]++;
                            System.out.println("玩家" + (i+1) + "获得：" + prizeNameArr[j]);
                            getPrize = true;
                        }
                        else {
                            // 判中了，但奖品已经发完，只能提示本轮拿不到。
                            System.out.println(prizeNameArr[j] + "已发完，本轮未获得该奖项");
                        }
                    }
                }

                if(result.equals("未得奖")){
                    System.out.println("玩家" + (i+1) + "本轮未得奖");
                }
                else if(!getPrize){
                    System.out.println("玩家" + (i+1) + "本轮没有拿到奖品");
                }

                // 每位玩家操作结束后，都打印一次实时库存，方便观察游戏进度。
                printRemain(prizeNameArr, remainArr);
            }
            round++;
        }

        // 游戏结束后，按玩家逐个汇总最终获奖情况。
        System.out.println("游戏结束，所有奖项已经发完。");
        for(int i=0;i<playerNum;i++){
            System.out.println("玩家" + (i+1) + "获得的奖项如下：");
            for(int j=0;j<prizeNameArr.length;j++){
                System.out.println(prizeNameArr[j] + "：" + playerPrizeArr[i][j]);
            }
        }
        scanner.close();
    }
}
