import java.util.Scanner;

public class _2439
{
    public static void main (String[] args) {
        Scanner scan = new Scanner(System.in);
        int number = scan.nextInt();
        int i = 0;
        int j = 0;
        
        for (i = 0; i < number ; i ++) 
        {
            for (j=0; j<number-(i+1); j++)
            {
                System.out.print(" ");
            }
            
            for (j = 0 ; j < i+1 ; j++)
            {
                System.out.print("*");
            }
            System.out.println();
            
        }
        
        
    }
}