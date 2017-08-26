import java.util.Scanner;

public class _2442
{
    public static void main (String[] args) {
        Scanner scan = new Scanner(System.in);
        int number = scan.nextInt();
        
        for (int i = 0; i<number; i++)
        {
            for ( int j = 0; j<number-(i+1); j++)
            {
                System.out.print(" ");
            }
            
            for (int j =0 ; j<2*i+1 ; j++ ) 
            {
                System.out.print("*");
            }
            System.out.println();
        }
    }
}