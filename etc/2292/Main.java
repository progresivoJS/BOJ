import java.util.Scanner;

public class _2292
{
    public static void main (String[] args) {
        Scanner scan = new Scanner(System.in);
        int number = scan.nextInt();
        
        int range=1;
        int result=0;
        for (int i = 0 ; number > range ; i++ )
        {
            range+=6*(i+1);
            result = i+1;
        }
        System.out.println(result+1);
    }
}