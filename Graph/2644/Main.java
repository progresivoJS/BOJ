import java.util.*;
import java.io.*;

/**
 * problem 2644
 * 촌수계산
 * https://www.acmicpc.net/problem/2644
 * written by progresivoJS on 2017.09.23
 */
public class Main
{
    private static int from, to;
    private static int[] parentOf;
    private static boolean[] marked;
    private static int[] distTo;
    
    public static void solve(int from, int to, LinkedList<Integer>[] childrenOf, int[] parentOf)
    {
        Main.from = from;
        Main.to = to;
        Main.parentOf = parentOf;
        
        int n = parentOf.length;
        marked = new boolean[n];
        distTo = new int[n];
        
        bfs(childrenOf, from);
    }
    
    private static void bfs(LinkedList<Integer>[] childrenOf, int s)
    {
        marked[s] = true;
        Queue<Integer> q = new LinkedList<>();
        q.add(s);
        distTo[s] = 0;
        
        while (!q.isEmpty())
        {
            int v = q.poll();
            if (v == to)
            {
                System.out.println(distTo[v]);
                return;
            }
            
            // case 1 : 자신의 자식 쪽.
            for (Integer child : childrenOf[v])
                if (!marked[child])
                {
                    marked[child] = true;
                    distTo[child] = distTo[v] + 1;
                    q.add(child);
                }
            
            // 부모가 있는 경우
            if (parentOf[v] >= 0)
            {
                // case 2 : 자신의 부모
                if (!marked[parentOf[v]])
                {
                    marked[parentOf[v]] = true;
                    distTo[parentOf[v]] = distTo[v] + 1;
                    q.add(parentOf[v]);
                }
            }
        }
        
        System.out.println(-1);
    }
    
    public static void main(String[] args)
    {
        In.init();
        int n = In.nextInt();
        int from = In.nextInt() - 1;
        int to = In.nextInt() - 1;
        int m = In.nextInt();
        
        // childrenOf[i] = 부모 i의 자식 리스트
        LinkedList<Integer>[] childrenOf = (LinkedList<Integer>[]) new LinkedList[n];
        for (int i = 0; i < n; i++)
            childrenOf[i] = new LinkedList<Integer>();
        
        // parent[i] = i의 부모
        int[] parentOf = new int[n];
        Arrays.fill(parentOf, -1);
        
        for (int i = 0; i < m; i++)
        {
            int parent = In.nextInt() - 1;
            int child = In.nextInt() - 1;
            childrenOf[parent].add(child);
            parentOf[child] = parent;
        }
        
        solve(from, to, childrenOf, parentOf);
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