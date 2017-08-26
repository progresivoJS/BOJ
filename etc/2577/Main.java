import java.util.Scanner;

public class _2577
{
    public static void main (String[] args) {
        Scanner scan = new Scanner(System.in);
        int result;
        int[] numberOfFrequency = {0,0,0,0,0,0,0,0,0,0};
        result = scan.nextInt()*scan.nextInt()*scan.nextInt();
        
        for(;result/10 > 0; result/=10 )
        {
            numberOfFrequency[result%10]++;
        }
        numberOfFrequency[result]++;
        
        for(int i = 0 ; i < numberOfFrequency.length ; i++)
        {
            System.out.println(numberOfFrequency[i]);
        }
    }
}