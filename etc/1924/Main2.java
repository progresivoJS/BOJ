import java.util.Scanner;

public class _1924_ver2
{
    public static void main (String[] args) {
        Scanner scan = new Scanner(System.in);
        int day,mon;
        mon = scan.nextInt();
        day = scan.nextInt();
        
        String[] Month={"SUN","MON","TUE","WED","THU","FRI","SAT"};
        int[] dayOfMonth={31,28,31,30,31,30,31,31,30,31,30,31};
        
        int sumData=0;
        for ( int i = 0; i < mon-1 ; i ++)
        {
            sumData += dayOfMonth[i];
        }
        sumData+=day;
        
        System.out.println(Month[sumData%7]);
    }
}