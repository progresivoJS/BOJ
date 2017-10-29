import java.util.*;
import java.io.*;

/**
 * problem 11651
 * 좌표 정렬하기 2
 * https://www.acmicpc.net/problem/11651
 * written by progresivoJS on 2017.10.29
 */
public class Main
{
    public static void main(String[] args)
    {
        In.init();
        int n = In.nextInt();
        Point[] array = new Point[n];
        for (int i = 0; i < n; i++)
            array[i] = new Point(In.nextInt(), In.nextInt());
        Arrays.sort(array);
        
        StringBuilder str = new StringBuilder();
        for (Point p : array)
            str.append(p.x).append(' ').append(p.y).append('\n');
        System.out.println(str);
    }
    
    private static class Point implements Comparable<Point>
    {
        int x, y;
        public Point(int x, int y)
        {
            this.x = x;
            this.y = y;
        }
        
        public int compareTo(Point other)
        {
            int diff = this.y - other.y;
            if (diff == 0)
                return this.x - other.x;
            else
                return diff;
        }
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