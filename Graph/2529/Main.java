import java.util.*;
import java.io.*;

/**
 * problem 2529
 * 부등호
 * https://www.acmicpc.net/problem/2529
 * written by progresivoJS on 2017.10.01
 */
public class Main
{
    public static void solve(boolean[][] adj, int[] ind)
    {
        int[] ind_max = new int[ind.length];
        for (int i = 0; i < ind.length; i++)
            ind_max[i] = ind[i];
            
        int[] ind_min = new int[ind.length];
        for (int i = 0; i < ind.length; i++)
            ind_min[i] = ind[i];
        
        System.out.println(getMax(adj, ind_max));
        System.out.println(getMin(adj, ind_min));
    }
    
    public static String getMax(boolean[][] adj, int[] ind)
    {
        int n = adj.length;
        
        boolean[] marked = new boolean[10];
        PriorityQueue<Integer> q = new PriorityQueue<>();
        
        for (int i = 0; i < n; i++)
            if (ind[i] == 0)
                q.add(i);
        
        int[] result_max = new int[n];
        for (int i = 0; i < n; i++)
        {
            int v = q.poll();
            for (int j = 9; j >= 0; j--)
                if (!marked[j])
                {
                    result_max[v] = j;
                    marked[j] = true;
                    break;
                }
            
            for (int w = 0; w < n; w++)
                if (adj[v][w] && --ind[w] == 0)
                    q.add(w);
        }
        
        StringBuilder str = new StringBuilder();
        for (int i : result_max)
            str.append(i);
        return str.toString();
    }
    
    public static String getMin(boolean[][] adj, int[] ind)
    {
        int n = adj.length;
        
        boolean[] marked = new boolean[10];
        PriorityQueue<Integer> q = new PriorityQueue<>();

        for (int i = 0; i < n; i++)
            if (ind[i] == 0)
                q.add(-i);
                
        int[] result_min = new int[n];
        for (int i = 0; i < n; i++)
        {
            int v = -q.poll();
            for (int j = n - 1; j >= 0; j--)
                if (!marked[j])
                {
                    result_min[v] = j;
                    marked[j] = true;
                    break;
                }
            
            for (int w = 0; w < n; w++)
                if (adj[v][w] && --ind[w] == 0)
                    q.add(-w);
        }
        
        StringBuilder str = new StringBuilder();
        for (int i : result_min)
            str.append(i);
        return str.toString();
    }
    
    public static void main(String[] args)
    {
        In.init();
        int k = In.nextInt();
        boolean[][] adj = new boolean[k + 1][k + 1];
        int[] ind = new int[k + 1];
        for (int i = 0; i < k; i++)
        {
            String str = In.next();
            // '>'
            if (str.charAt(0) == '>')
            {
                adj[i][i + 1] = true;
                ind[i + 1]++;
            }
            // '<'
            else
            {
                adj[i + 1][i] = true;
                ind[i]++;
            }
        }
        
        solve(adj, ind);
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