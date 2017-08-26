import java.util.Scanner;

public class _2445_v2
{
    public static void main (String[] args) {
        Scanner scan = new Scanner(System.in);
        int number = scan.nextInt();
        int numberOfLine = 2*number-1;
        int numberOfStar = 1;
        int numberOfBlank = 2*number-2;
        
        for ( int i = 1 ; i <= numberOfLine ; i++ )
        {
            for ( int j = 0 ; j < numberOfStar ; j++ )
            {
                System.out.print("*");
            }
            
            for ( int j = 0 ; j < numberOfBlank ; j++ )
            {
                System.out.print(" ");
            }
            
            for ( int j = 0 ; j < numberOfStar ; j++ )
            {
                System.out.print("*");
            }
            
            System.out.println();
            
            if ( i < number )
            {
                numberOfStar+=1;
                numberOfBlank-=2;
            }
            else
            {
                numberOfStar-=1;
                numberOfBlank+=2;
            }
        }
    }
}