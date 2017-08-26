import  java.util.Scanner;

public class _2441
{
    public static void main (String[] args) {
        Scanner scan = new Scanner(System.in);
        int number;
        
        number = scan.nextInt();
        
        for (int i =0; i<number; i++)
        {
            for ( int k = 0; k < i ; k++)
            {
                System.out.print(" ");
            }
            
            for ( int j = 0; j < number-i ; j++)
            {
                System.out.print("*");
            }
            
            System.out.println();
        } 
    }
}