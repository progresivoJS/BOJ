import java.util.Scanner;

public class _9085
{
    public static void main (String[] args) {
        Scanner scan = new Scanner(System.in);
        int a = scan.nextInt();
        int b;
        int sum = 0;
        for ( int i = 0 ; i < a ; i++ )
        {
            b = scan.nextInt();
            for ( int j = 0 ; j < b ; j++ )
            {
                sum+=scan.nextInt();
            }
            System.out.println(sum);
            sum = 0;
        }
    }
}