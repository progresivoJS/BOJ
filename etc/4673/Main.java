import java.util.Scanner;

public class _4673
{
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        
        for ( int i = 1 ; i < 10000 ; i++ )
        {
            int j;
            for ( j = 1 ; j < 10000 ; j++ )
            {
                if ( i == selfNumber(j))
                {
                    break;
                }
            }
            if ( j == 10000 ) System.out.println(i);
        }
        
    }
    
    static int selfNumber(int number)
    {
        int result = 0;
        result += number;
        result += seperateSum(number);
        return result;
    }
    
    static int seperateSum(int number)
    {
        int result = 0;
        while(number > 0)
        {
            result += number%10;
            number /= 10;
        }
        return result;
    }
}