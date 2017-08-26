import java.util.Scanner;

public class _2739
{
    public static void main (String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        
        for ( int i = 0 ; i < 9 ; i++ )
        {
            System.out.println(n+" * "+(i+1)+" = "+n*(i+1));
        }
    }
}