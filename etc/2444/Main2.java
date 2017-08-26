import java.util.Scanner;

public class _2444_v2
{
    public static void main (String[] args) {
        Scanner scan = new Scanner(System.in);
        int number = scan.nextInt();
        int numberOfLine = 2*number-1;
        int numberOfStar = 1;           // 2개씩 증가해야돼
        int numberOfBlank = number-1;   // 1개씩 감소해야돼
        
        for ( int i = 1 ; i <= numberOfLine ; i++ )
        {
            for ( int j = 0 ; j < numberOfBlank ; j++)
            {
                System.out.print(" ");
            }
            
            for ( int j = 0 ; j < numberOfStar ; j++)
            {
                System.out.print("*");
            }
            System.out.println();
            
            if ( i < number)
            {
                numberOfStar+=2;
                numberOfBlank-=1;
            }
            else
            {
                numberOfStar-=2;
                numberOfBlank+=1;
            }
        }
    }
}