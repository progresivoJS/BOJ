import java.util.Scanner;

public class _2443
{
    public static void main (String[] args) {
        Scanner scan = new Scanner(System.in);
        int number = scan.nextInt();
        
        for (int i = 0; i<number; i++)
        {
            for ( int j = 0; j<i; j++)
            {
                System.out.print(" ");
            }
            
            for (int j =0 ; j<2*(number-i)-1 ; j++ ) 
            {
                System.out.print("*");
            }
            System.out.println();
        }
    }
}