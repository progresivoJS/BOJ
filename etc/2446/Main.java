import java.util.Scanner;
public class _2446
{
    public static void main (String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int nL=2*n-1;
        int nB=0;            // 1개씩 증가했다가 1개씩 감소
        int nS=2*n-1;    // 2개씩 감소했다가 2개씩 증가
        for ( int i = 1 ; i <= nL ; i++)
        {
            for ( int j = 0 ; j < nB ; j++) System.out.print(" ");
            for ( int j = 0 ; j < nS ; j++ ) System.out.print("*");
            if ( i < n )
            {
                nS -=2;nB +=1;
            }
            else
            {
                nS +=2;nB -=1;
            }
            System.out.println();
        }
    }
}