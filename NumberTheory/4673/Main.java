import java.util.*;
import java.io.*;
public class Main
{
    private static boolean[] isSelfNumber;
    public static void main(String[] args) throws IOException
    {
        isSelfNumber = new boolean[10001];
        Arrays.fill(isSelfNumber, true);

        eratosthenes();

        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 1; i <= 10000; i++)
            if (isSelfNumber[i])
            {
                out.write(String.valueOf(i));
                out.write("\n");
            }
        out.close();
    }

    public static void eratosthenes()
    {
        for (int i = 1; i <= 10000; i++)
        {
            int n = sumOfDigits(i) + i;
            if (n > 0 && n <= 10000)
                isSelfNumber[n] = false;
        }
    }

    private static int sumOfDigits(int n)
    {
        int result = 0;
        while (n > 0)
        {
            result += (n % 10);
            n /= 10;
        }
        return result;
    }
}
