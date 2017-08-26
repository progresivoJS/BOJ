import java.util.Scanner;

public class _1193
{
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        int number = sc.nextInt();
        
        int i;
        for ( i = 1; ; i++)
        {
            if ( number <= sum(i) ) break;
        }
        number -= sum(i-1);
        
        if ( i % 2 == 0)
        {
            System.out.println(number+"/"+(i+1-number));
        }
        else
        {
            System.out.println((i+1-number)+"/"+number);
        }
        
    }
    
    static int sum(int number)
    {
        int result=0;
        for ( int i = 1 ; i <= number ; i++)
        {
            result+=i;
        }
        
        return result;
    }
}