import java.util.*;
import java.io.*;

/**
 * problem 2309
 * 일곱 난쟁이
 * https://www.acmicpc.net/problem/2309
 * written by progresivoJS on 2017.09.14
 */
public class Main
{
    private static int n;
    private static int[] seq;
    
    public static void solve(int[] seq) throws IOException
    {
        Main.n = seq.length;
        Main.seq = seq;
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        
        Arrays.sort(seq);
        for (int i = 0; i < n; i++)
            for (int j = i + 1; j < n; j++)
                if (computeLength(i, j) == 100)
                {
                    for (int k = 0; k < n; k++)
                        if (k != i && k != j)
                        {
                            out.write(String.valueOf(seq[k]));
                            out.write(" ");
                        }
                    return;
                }
        out.close();
    }
    
    private static int computeLength(int i, int j)
    {
        int sum = 0;
        for (int k = 0; k < n; k++)
            if (k != i && k != j)
                sum += seq[k];
        return sum;
    }
    
    public static void main(String[] args) throws IOException
    {
        In.init();
        int[] seq = new int[9];
        for (int i = 0; i < 9; i++)
            seq[i] = In.nextInt();
        solve(seq);
    }
    
    private static class In
    {
        private static BufferedReader br;
        private static StringTokenizer st;
    
        public static void init()
        {
            br = new BufferedReader(new InputStreamReader(System.in));
        }
    
        public static String next()
        {
            while (st == null || !st.hasMoreElements())
            {
                try
                {
                    st = new StringTokenizer(br.readLine());
                }
                catch (IOException  e)
                {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }
    
        public static int nextInt()
        {
            return Integer.parseInt(next());
        }
    }
}
