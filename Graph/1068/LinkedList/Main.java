import java.util.*;
import java.io.*;

/**
 * problem 1068
 * 트리
 * https://www.acmicpc.net/problem/1068
 * written by progresivoJS on 2017.10.19
 */
public class Main
{
    public static void main(String[] args)
    {
        In.init();
        int n = In.nextInt();
        LinkedList<Integer>[] childrenOf = (LinkedList<Integer>[]) new LinkedList[n];
        for (int i = 0; i < n; i++)
            childrenOf[i] = new LinkedList<>();
        
        int root = -1;
        for (int i = 0; i < n; i++)
        {
            int parent = In.nextInt();
            if (parent == -1)
            {
                root = i;
                continue;
            }
            childrenOf[parent].add(i);
        }
        
        Integer target = In.nextInt();
        if (target == root)
        {
            System.out.println(0);
            return;
        }
        for (int i = 0; i < n; i++)
            childrenOf[i].removeFirstOccurrence(target);
        System.out.println(countLeefNode(childrenOf, root));
    }
    
    private static int countLeefNode(LinkedList<Integer>[] childrenOf, int root)
    {
        if (childrenOf[root].size() == 0)
            return 1;
        
        int count = 0;
        for (int child : childrenOf[root])
            count += countLeefNode(childrenOf, child);
        return count;
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