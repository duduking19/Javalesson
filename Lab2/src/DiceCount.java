import java.util.Random;
public class DiceCount {
    public static void main(String[] args){
        Random random = new Random();
        int[] countArr = new int[7];
        int[] diceArr = new int[6];
        for(int i=0;i<6;i++){
            int dice = random.nextInt(6)+1;
            diceArr[i] = dice;
            countArr[dice]++;
        }
        System.out.print("骰子点数为：");
        for(int i=0;i<6;i++){
            System.out.print(diceArr[i] + " ");
        }
        System.out.println();
        boolean flag = false;
        if(countArr[4]==6){
            System.out.println("六勃红");
            flag = true;
        }
        else if(countArr[4]==5){
            System.out.println("五王");
            flag = true;
        }
        else if(countArr[4]==4){
            if(countArr[1]==2){
                System.out.println("状元插金花");
            }
            else {
                System.out.println("四红");
            }
            flag = true;
        }
        else if(countArr[1]==6) {
            System.out.println("遍地锦");
            flag = true;
        }
        else if(countArr[2]==6 || countArr[3]==6 ||countArr[5]==6 || countArr[6]==6){
             System.out.println("六勃黑");
             flag = true;
        }
        else if(countArr[2]==5 || countArr[3]==5 ||countArr[5]==5 || countArr[6]==5 || countArr[1]==5){
             System.out.println("五子");
             flag = true;
        }
        else if(countArr[1]==1 && countArr[2]==1 && countArr[3]==1 && countArr[4]==1 && countArr[5]==1 && countArr[6]==1){
            System.out.println("对堂");
            flag = true;
        }
        else {
            if((countArr[1]==4 && countArr[4]!=4) || (countArr[2]==4) || (countArr[3]==4) || (countArr[5]==4) || (countArr[6]==4)){
                System.out.println("四进");
                flag = true;
            }
            if(countArr[4]==3){
                 System.out.println("三红");
                 flag = true;
            }
            else if(countArr[4]==2){
                System.out.println("二举");
                flag = true;
            }
            else if(countArr[4]==1){
                 System.out.println("一秀");
                 flag = true;
            }
        }
        if(!flag){
            System.out.println("未得奖");
        }
    }
}
