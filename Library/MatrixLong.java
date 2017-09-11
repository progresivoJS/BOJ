public static class Matrix
{
    public static long[][] identity(int n)
    {
        long[][] a = new long[n][n];
        for (int i = 0; i < n; i++)
            a[i][i] = 1;
        return a;
    }
    
    public static long[][] mul(long[][] a, long[][] b)
    {
        int m1 = a.length;
        int n1 = a[0].length;
        int m2 = b.length;
        int n2 = b[0].length;
        if (n1 != m2) throw new RuntimeException("Illegal matrix dimensions");
        
        long[][] c = new long[m1][n2];
        for (int i = 0; i < m1; i++)
            for (int j = 0; j < n2; j++)
                for (int k = 0; k < n1; k++)
                    c[i][j] += a[i][k] * b[k][j];
        return c;
    }
    
    public static long[][] pow(long[][] a, int m)
    {
        if (a.length != a[0].length)
            throw new RuntimeException("Illegal matrix dimensions");
        
        if (m == 0)
            return Matrix.identity(a.length);
        
        if (m % 2 == 1)
            return Matrix.mul(pow(a, m-1), a);
        
        long[][] half = pow(a, m/2);
        return Matrix.mul(half, half);
    }
}