import java.util.Scanner;

public class _10950
{
    public static void main (String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int a,b;
        for ( int i = 0 ; i < n ; i++)
        {
            a = scan.nextInt();
            b = scan.nextInt();
            
            System.out.println(a+b);
        }
    }
}