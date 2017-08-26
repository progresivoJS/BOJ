import java.util.Scanner;

public class _2475
{
    public static void main (String[] args) {
        Scanner scan = new Scanner(System.in);
        int[] number = new int[5];
        int sum = 0;
        for ( int i = 0 ; i < number.length ; i++ )
        {
            number[i] = scan.nextInt();
            sum+=number[i]*number[i];
        }
        System.out.println(sum%10);
    }
}