/**
 * problem 9654
 * 나부함대 데이터
 * https://www.acmicpc.net/problem/9654
 * written by progresivoJS
 */
public class Main
{
    public static void main(String[] args)
    {
        String[][] str = {
            {"SHIP NAME", "CLASS", "DEPLOYMENT", "IN SERVICE"},
            {"N2 Bomber", "Heavy Fighter", "Limited", "21"},
            {"J-Type 327", "Light Combat" ,"Unlimited", "1"},
            {"NX Cruiser", "Medium Fighter", "Limited", "18"},
            {"N1 Starfighter" ,"Medium Fighter", "Unlimited" ,"25"},
            {"Royal Cruiser", "Light Combat", "Limited", "4"}};
        
        for (int i = 0; i < str.length; i++)
        {
            System.out.printf("%-15s", str[i][0]);
            System.out.printf("%-15s", str[i][1]);
            System.out.printf("%-11s", str[i][2]);
            System.out.printf("%-10s", str[i][3]);
            System.out.println();
        }
    }
}