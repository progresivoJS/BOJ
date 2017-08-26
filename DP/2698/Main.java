import java.util.Scanner;
import java.io.File;
import java.util.Arrays;
public class Main
{
    public static int solve(int n, int k)
    {
        
    }
    public static void main(String[] args)
    {
        Scanner sc = null;
        try
        {
            sc = new Scanner(new File("/home/ubuntu/workspace/data.txt"));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        int test = sc.nextInt();
        for (int i = 0; i < test; i++)
        {
            int n = sc.nextInt();
            int k = sc.nextInt();
            System.out.println(solve(n, k));
        }
    }
}