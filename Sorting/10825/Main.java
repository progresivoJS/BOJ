import java.util.*;
import java.io.*;

/**
 * problem 10825
 * 국영수
 * https://www.acmicpc.net/problem/10825
 * written by progresivoJS on 2017.10.29
 */
public class Main
{
    public static void main(String[] args)
    {
        In.init();
        int n = In.nextInt();
        Student[] students = new Student[n];
        for (int i = 0; i < n; i++)
            students[i] = new Student(In.next(), In.nextInt(), In.nextInt(), In.nextInt());
        Arrays.sort(students);
        
        StringBuilder str = new StringBuilder();
        for (Student s : students)
            str.append(s.name).append('\n');
        System.out.println(str);
    }
    
    private static class Student implements Comparable<Student>
    {
        String name;
        int a, b, c;
        public Student(String name, int a, int b, int c)
        {
            this.name = name;
            this.a = a;
            this.b = b;
            this.c = c;
        }
        
        public int compareTo(Student other)
        {
            int diff;
            
            diff = other.a - this.a;
            if (diff != 0)
                return diff;
            
            diff = this.b - other.b;
            if (diff != 0)
                return diff;
            
            diff = other.c - this.c;
            if (diff != 0)
                return diff;
                
            return this.name.compareTo(other.name);
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