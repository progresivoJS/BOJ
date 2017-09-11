public static class Matrix
{
    public static double[][] identity(int n)
    {
        double[][] a = new double[n][n];
        for (int i = 0; i < n; i++)
            a[i][i] = 1;
        return a;
    }
    
    public static double[][] mul(double[][] a, double[][] b)
    {
        int m1 = a.length;
        int n1 = a[0].length;
        int m2 = b.length;
        int n2 = b[0].length;
        if (n1 != m2) throw new RuntimeException("Illegal matrix dimensions");
        
        double[][] c = new double[m1][n2];
        for (int i = 0; i < m1; i++)
            for (int j = 0; j < n2; j++)
                for (int k = 0; k < n1; k++)
                    c[i][j] += a[i][k] * b[k][j];
        return c;
    }
    
    public static double[][] pow(double[][] a, int m)
    {
        if (a.length != a[0].length)
            throw new RuntimeException("Illegal matrix dimensions");
        
        if (m == 0)
            return Matrix.identity(a.length);
        
        if (m % 2 == 1)
            return Matrix.mul(pow(a, m-1), a);
        
        double[][] half = pow(a, m/2);
        return half * half;
    }
}