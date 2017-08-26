import java.util.Scanner;
import java.util.Arrays;

public class _10808
{
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        int[] alphabet = new int[26];
        
        Arrays.fill(alphabet,0);
        
        for ( int i = 0 ; i < str.length() ; i ++)
        {
            alphabet[(int)str.charAt(i)-97]++;
        }
        
        for (int i = 0 ; i < alphabet.length ; i++)
        {
            System.out.print(alphabet[i]+" ");
        }
    }
}