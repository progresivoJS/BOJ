import java.util.*;
import java.io.*;

/**
 * problem 2623
 * 음악프로그램
 * https://www.acmicpc.net/problem/2623
 * written by progresivoJS on 2017.09.29
 */
public class Main
{
    public static void solve(LinkedList<Integer>[] adj, int[] ind)
    {
        StringBuilder str = new StringBuilder();
        
        int n = adj.length;
        Queue<Integer> q = new LinkedList<>();
        int[] result = new int[n];
        
        for (int i = 0; i < n; i++)
            if (ind[i] == 0)
                q.add(i);
        
        for (int i = 0; i < n; i++)
        {
            if (q.isEmpty())
            {
                str.append(0).append('\n');
                System.out.println(str.toString());
                return;
            }
            
            int v = q.poll();
            result[i] = v;
            for (int w : adj[v])
                if (--ind[w] == 0)
                    q.add(w);
        }
        
        for (int i : result)
            str.append(i + 1).append('\n');
        System.out.println(str.toString());
    }
    
    public static void main(String[] args)
    {
        In.init();
        int n = In.nextInt();
        int m = In.nextInt();
        
        LinkedList<Integer>[] adj = (LinkedList<Integer>[]) new LinkedList[n];
        for (int i = 0; i < n; i++)
            adj[i] = new LinkedList<>();
            
        int[] ind = new int[n];
            
        while (m-- > 0)
        {
            int[] order = new int[In.nextInt()];
            for (int i = 0; i < order.length; i++)
                order[i] = In.nextInt() - 1;
            
            for (int i = 0; i < order.length; i++)
                for (int j = i + 1; j < order.length; j++)
                {
                    ind[order[j]] ++;
                    adj[order[i]].add(order[j]);
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