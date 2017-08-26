import java.util.Scanner;

public class _1924
{
    public static void main (String[] args) {
        Scanner scan = new Scanner(System.in);
        int month,day,sumData;
        month = scan.nextInt();
        day = scan.nextInt();
        sumData = 0;
        
        int[] dayOfMonth = new int[12];
        
        dayOfMonth[0]=31;dayOfMonth[2]=31;dayOfMonth[4]=31;dayOfMonth[6]=31;dayOfMonth[7]=31;dayOfMonth[9]=31;dayOfMonth[11]=31;
        dayOfMonth[3]=30;dayOfMonth[5]=30;dayOfMonth[8]=30;dayOfMonth[10]=30;
        dayOfMonth[1]=28;
        
        
        for ( int i = 0 ; i < month-1 ; i++ )
        {
            sumData += dayOfMonth[i];
        }
        sumData+=day;
        
        String theDay = "";
        switch (sumData%7) {
            case 0: theDay = "SUN"; break;
            case 1: theDay = "MON"; break;
            case 2: theDay = "TUE"; break;
            case 3: theDay = "WED"; break;
            case 4: theDay = "THU"; break;
            case 5: theDay = "FRI"; break;
            case 6: theDay = "SAT"; break;
            default:
        }
        System.out.println(theDay);
    }
}