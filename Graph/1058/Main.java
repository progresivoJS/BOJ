import java.util.*;
import java.io.*;

/**
 * problem 1058
 * 친구
 * https://www.acmicpc.net/problem/1058
 * written by progresivoJS on 2017.11.04
 */
public class Main
{
    public static void solve(LinkedList<Integer>[] adj)
    {
        int n = adj.length;
        int max = -1;
        for (int s = 0; s < n; s++)
        {
            boolean[] marked = new boolean[n];
            marked[s] = true;
            Queue<Integer> q = new LinkedList<>();
            q.add(s);
            
            int dist = 0;
            int count = 0;
            while (!q.isEmpty())
            {
                int size = q.size();
                while (size-- > 0)
                {
                    int v = q.poll();
                    for (int w : adj[v])
                        if (!marked[w])
                        {
                            q.add(w);
                            marked[w] = true;
                            count++;
                        }
                }
                dist++;
                if (dist >= 2)
                    break;
            }
            
            max = Math.max(max, count);
        }
        
        System.out.println(max);
    }
    
    public static void main(String[] args)
    {
        In.init();
        int n = In.nextInt();
        LinkedList<Integer>[] adj = (LinkedList<Integer>[]) new LinkedList[n];
        for (int i = 0; i < n; i++)
            adj[i] = new LinkedList<>();
            
        for (int i = 0; i < n; i++)
        {
            String s = In.next();
            for (int j = 0; j < n; j++)
                if (s.charAt(j) == 'Y')
                    adj[i].add(j);
        }
        
        solve(adj);
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