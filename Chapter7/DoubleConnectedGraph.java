package Chapter7;

import java.util.Stack;

/**
 * Created by duncan on 16-12-6.
 */
public class DoubleConnectedGraph {
    private static final int maxsize = 100;
    //全局变量，因为方法是递归方法
    private int[] DFN = new int[maxsize];
    private int[] L = new int[maxsize];
    private int num = 1;
    private Stack<Edge> edge = new Stack<>();
    //结点类，存储结点信息
    class node{
        int id;
        String description;
    }
    //Edge为边类，a，b为边的两边的结点编号
    class Edge{
        Edge(int i,int j){
            a = i;
            b= j;
        }
        public int compare(Edge c){
            if(a == c.a && b == c.b)
                return 1;
            else
                return 0;
        }
        public int compare(int c,int d){
            if((a == c && b == d) || (a == d && b == c))
                return 1;
            else
                return 0;
        }
        int a;
        int b;
    }
    class DoubleGraph{
        //matrix存储双向图的结构，1为结点有边，0表示结点之间没有边,n为点的个数，e为边的个数
        int[][] matrix = new int[maxsize][maxsize];
        int n,e;
        node[] nodes = new node[maxsize];
    }
    //生成测试图
    public DoubleGraph generate(){
        DoubleGraph g = new DoubleGraph();
        int[][] m = {{0,0,0,0,0,0,0,0,0,0},{0,1,1,0,1,0,0,0,0,0,0},{0,1,1,1,0,1,0,1,1,0,0},{0,0,1,1,1,0,0,0,0,1,1},{0,1,0,1,1,0,0,0,0,0,0},{0,0,1,0,0,1,1,1,1,0,0},{0,0,0,0,0,1,1,0,0,0,0},{0,0,1,0,0,1,0,1,1,0,0},{0,0,1,0,0,1,0,1,1,0,0},{0,0,0,1,0,0,0,0,0,1,0},{0,0,0,1,0,0,0,0,0,0,1}};
        g.matrix = m;
        g.n = 10;
        g.e = 13;
        return g;
    }
    //输出双向联通图分量,start为深度优先检索的开始结点，parent为start的父结点
    public void PrintDoubleConnectedGraph(DoubleGraph g,int start,int parent){
        //DFN存储深度优先搜索生成树中结点的序号，L为按照公式计算得到的最低深度优先数，edge为栈存储树边和逆边,num计数用,count统计联通分图的数目
        int count = 0;
        DFN[start] = num;
        L[start] = num;
        num++;
        //遍历和start相关的每个结点
        for(int i = 1; i <= g.n; i++){
            //和start是相邻的
            if(i != start && g.matrix[start][i] == 1) {
                int w = i;
                //加入到栈中
                if(w != parent && DFN[w] < DFN[start]) {
                    Edge ab = new Edge(start, w);
                    edge.push(ab);
                   // System.out.println(ab.a + " -> "+ ab.b + "入栈");
                }
                //w未访问过
                if(DFN[w] == 0){
                    PrintDoubleConnectedGraph(g,w,start);

                    if(L[w] >= DFN[start]){
                        count++;
                        //找到一个割点，打印深度优先生成树的边
                        System.out.println("new biconnected componet" + " and 割点为：" + start);
                        Edge cd;
                        do{
                                cd = edge.pop();
                                System.out.println(cd.a + " -> " + cd.b);
                        }while(cd.compare(start,w) != 1);
                    }
                    L[start] = Math.min(L[start],L[w]);
                }
                else if(w != parent)
                    L[start] = Math.min(L[start],DFN[w]);
            }
        }
    }
    public void print(int n)
    {
        for(int i = 1; i <= n; i++)
            System.out.println("L" + i + "= " + L[i]);
        for(int i = 1; i <= n; i++)
            System.out.println("DFN" + i + "= " + DFN[i]);
    }
    public static void main(String[] args) {
        DoubleConnectedGraph dcg = new DoubleConnectedGraph();
        DoubleGraph g = dcg.generate();
        dcg.PrintDoubleConnectedGraph(g,1,0);
    //  dcg.print(g.n);
    }
}
