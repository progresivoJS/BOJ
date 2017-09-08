import java.util.Scanner;

public class _2742
{
    public static void main (String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        
        for ( int i = 0 ; i < n ; i++ )
        {
            System.out.println(n-i);
        }
    }
}