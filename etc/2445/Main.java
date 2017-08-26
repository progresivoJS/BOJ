import java.util.Scanner;

public class _2445
{
    public static void main (String[] args) {
        Scanner scan = new Scanner(System.in);
        int number;
        number = scan.nextInt();
        
        for ( int i = 0 ; i < 2* number-1 ; i++)
        {
            if ( i < number )
            {
                for ( int k = 0; k < i+1 ; k ++)
                {
                    System.out.print("*");
                }
                
                for ( int k = 0 ; k < 2*number-2*(i+1) ; k++ )
                {
                    System.out.print(" ");
                }
                
                for ( int k = 0 ; k < i+1 ; k++)
                {
                    System.out.print("*");
                }
            }
            else
            {
                for ( int k = 0; k < 2*number-i-1 ; k ++)
                {
                    System.out.print("*");
                }
                
                for ( int k = 0 ; k < 2*(i-number)+2 ; k++ )
                {
                    System.out.print(" ");
                }
                
                for ( int k = 0 ; k < 2*number-i-1 ; k++)
                {
                    System.out.print("*");
                }
            }
            
            System.out.println();
        }
    }
}