package Day27.MyProject03.view;

import java.util.*;

public class TSUtility {
    private static Scanner scanner = new Scanner(System.in);

    /**
     * 读取键盘
     */
    public static char readMenuSelection() {
        char c;
        for (; ; ) {
            String str = readKeyBoard(1, false);
            c = str.charAt(0);
            if (c != '1' && c != '2' && c != '3' && c != '4') {
                System.out.println("输入错误，请重新选择！");
            } else break;
        }
        return c;
    }

    public static void readReturn() {
        System.out.println("请按回车键继续。。。");
        ;
        readKeyBoard(100, true);  //如果成功
    }

    public static int readInt() {
        int n;
        for (; ; ) {
            String str = readKeyBoard(2, false);
            try {
                n = Integer.parseInt(str);
                break;
            } catch (NumberFormatException e) {
                System.out.println("数字输入错误，请重新输入！");
            }
        }
        return n;
    }

    /**
     * 选择退出后继续
     *
     *
     * @return
     */
    public static char readConfirmSelection() {
        char c;
        for (; ; ) {
            String str = readKeyBoard(1, false).toUpperCase();
            c = str.charAt(0);
            if (c == 'Y' || c == 'N') {
                break;
            } else {
                System.out.println("输入错误，请重写输入！");
            }
        }
        return c;
    }

    /**
     * 从键盘获取数据
     *
     *
     * @param limit
     * @param blankReturn
     * @return
     */
    private static String readKeyBoard(int limit,boolean blankReturn){
        String line="";
        while(scanner.hasNextLine()){
            line = scanner.nextLine();
            if(line.length()==0){
                if(blankReturn)return line;
                else continue;
            }
            if(line.length()<1||line.length()>limit){
                System.out.println("输入长度(不大于"+limit+")错误，请重新输入：");
                continue;
            }
            break;
        }
        return line;
    }

}
