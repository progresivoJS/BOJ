import java.util.*;
import java.io.*;

/**
 * problem 1005
 * ACM Craft
 * https://www.acmicpc.net/problem/1005
 * written by progresivoJS on 2017.09.29
 */
public class Main
{
    private static StringBuilder str;
    public static void solve(int[] time, LinkedList<Integer>[] adj, int[] ind, int target)
    {
        int n = time.length;
        Queue<Integer> q = new LinkedList<>();
        int[] takenTime = new int[n];
        
        for (int i = 0; i < n; i++)
            if (ind[i] == 0)
            {
                takenTime[i] = time[i];
                q.add(i);
            }
        
        for (int i = 0; i < n; i++)
        {
            int v = q.poll();
            
            for (int w : adj[v])
            {
                takenTime[w] = Math.max(takenTime[w], takenTime[v] + time[w]);
                if (--ind[w] == 0)
                    q.add(w);
            }
        }
        
        str.append(takenTime[target]).append('\n');
    }
    
    public static void main(String[] args)
    {
        In.init();
        str = new StringBuilder();
        
        int test = In.nextInt();
        while (test-- > 0)
        {
            int n = In.nextInt();
            int k = In.nextInt();
            
            int[] time = new int[n];
            for (int i = 0; i < n; i++)
                time[i] = In.nextInt();
            
            LinkedList<Integer>[] adj = (LinkedList<Integer>[]) new LinkedList[n];
            for (int i = 0; i < n; i++)
                adj[i] = new LinkedList<>();
            
            int[] ind = new int[n];
            
            while (k-- > 0)
            {
                int from = In.nextInt() - 1;
                int to = In.nextInt() - 1;
                adj[from].add(to);
                ind[to] ++;
            }
            
            int target = In.nextInt() - 1;
            
            solve(time, adj, ind, target);
        }
        
        System.out.println(str.toString());
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