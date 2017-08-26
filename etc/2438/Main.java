import java.util.Scanner;

public class _2438
{
    public static void main (String[] args) {
        Scanner scan = new Scanner(System.in);
        int number;
        
        number = scan.nextInt();
        
        for (int i = 0;i<number; i++)
        {
            for (int j=0; j<i+1; j++)
            {
                System.out.print("*");
            }
            System.out.println();
        } 
    }
}