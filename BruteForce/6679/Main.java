import java.util.*;
import java.io.*;

/**
 * problem 6679
 * 싱기한 네자리 숫자
 * https://www.acmicpc.net/problem/6679
 * written by progresivoJS on 2017.09.15
 */
public class Main
{
    public static void main(String[] args) throws IOException
    {
        In.init();
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 1000; i <= 9999; i++)
        {
            int sum = sum(i, 10);
            if (sum == sum(i, 12) && sum == sum(i, 16))
            {
                out.write(String.valueOf(i));
                out.write("\n");
            }
        }
            
        out.close();
    }
    
    public static int sum(int number, int radix)
    {
        int result = 0;
        while (number > 0)
        {
            result += number % radix;
            number /= radix;
        }
        
        return result;
    }
}