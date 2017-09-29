import java.util.*;
import java.io.*;

/**
 * problem 2637
 * 장난감조립
 * https://www.acmicpc.net/problem/2637
 * written by progresivoJS on 2017.09.29
 */
public class Main
{
    public static void solve(int[][] adj, int[] ind, int[] out)
    {
        int n = ind.length;
        Queue<Integer> q = new LinkedList<>();
        int[] count = new int[n];
        
        for (int i = 0; i < n; i++)
            if (ind[i] == 0)
            {
                count[i] = 1;
                q.add(i);
            }
        
        for (int i = 0; i < n; i++)
        {
            int v = q.poll();
            
            for (int w = 0; w < n; w++)
            {
                if (adj[v][w] == 0) continue;
                count[w] += count[v] * adj[v][w];
                if (--ind[w] == 0)
                    q.add(w);
            }
        }
        
        for (int i = 0; i < n; i++)
            if (out[i] == 0)
                System.out.println(i+1 + " " + count[i]);
    }
    
    public static void main(String[] args)
    {
        In.init();
        int n = In.nextInt();
        int m = In.nextInt();
        
        // adj[v][w] = v를 만드는데에 w가 몇개 필요한가? 0 이면 v 만드는 데에 w가 필요없다.
        int[][] adj = new int[n][n];
        int[] ind = new int[n];
        int[] out = new int[n];
        
        while (m-- > 0)
        {
            int from = In.nextInt() - 1;
            int to = In.nextInt() - 1;
            adj[from][to] = In.nextInt();
            ind[to]++;
            out[from]++;
        }
        
        solve(adj, ind, out);
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