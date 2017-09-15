import java.util.*;
import java.io.*;

/**
 * problem 6603
 * 로또
 * https://www.acmicpc.net/problem/6603
 * written by progresivoJS on 2017.09.15
 */
public class Main
{
    private static int[] seq;
    private static int n;
    private static BufferedWriter out;
    public static void solve(int[] seq) throws IOException
    {
        Main.seq = seq;
        n = seq.length;
        
        for (int i = 0; i < n; i++)
        {
            LinkedList<Integer> list = new LinkedList<>();
            list.add(seq[i]);
            combination(i, list);
        }
    }
    
    private static void combination(int index, LinkedList<Integer> list) throws IOException
    {
        if (list.size() == 6)
        {
            for (Integer i : list)
            {
                out.write(String.valueOf(i));
                out.write(" ");
            }
            out.write("\n");
            return;
        }
        
        for (int next = index + 1; next < n; next++)
        {
            list.add(seq[next]);
            combination(next, list);
            list.removeLast();
        }
    }
    
    public static void main(String[] args) throws IOException
    {
        In.init();
        out = new BufferedWriter(new OutputStreamWriter(System.out));
        while (true)
        {
            int n = In.nextInt();
            if (n == 0)
                break;
            int[] seq = new int[n];
            for (int i = 0; i < n; i++)
                seq[i] = In.nextInt();
            solve(seq);
            out.write("\n");
        }
        out.close();
    }
    
    private static class In
    {
        private static BufferedReader br;
        private static StringTokenizer st;
    
        public static void init()
        {
            br = new BufferedReader(new InputStreamReader(System.in));
            try
            {
                br = new BufferedReader(new FileReader("/home/ubuntu/workspace/data.txt"));
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
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