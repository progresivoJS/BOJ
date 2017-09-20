import java.util.*;
import java.io.*;

/**
 * problem 1697
 * 숨바꼭질
 * https://www.acmicpc.net/problem/1697
 * written by progresivoJS on 2017.09.20
 */
public class Main
{
    private static void bfs(int n, int k)
    {
        int M = 100000;
        boolean[] marked = new boolean[M + 1];
        LinkedList<Integer> q = new LinkedList<>();
        q.add(n);
        marked[n] = true;
        
        int count = 0;
        
        while (!q.isEmpty())
        {
            int size = q.size();
            while (size -- > 0)
            {
                int v = q.remove();
                if (v == k)
                {
                    System.out.println(count);
                    return;
                }
                
                if (v + 1 <= M && !marked[v + 1])
                {
                    q.add(v + 1);
                    marked[v + 1] = true;
                }
                if (v - 1 >= 0 && !marked[v - 1])
                {
                    q.add(v - 1);
                    marked[v - 1] = true;
                }
                if (2 * v <= M && !marked[v * 2])
                {
                    q.add(2 * v);
                    marked[2 * v] = true;
                }
            }
            
            count ++;
        }
    }
    
    public static void main(String[] args)
    {
        In.init();
        int n = In.nextInt(); // 수빈이의 위치
        int k = In.nextInt(); // 동생의 위치
        
        bfs(n, k);
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