package Chapter5;

import java.util.*;

/**
 * Created by duncan on 16-11-22.
 */
//继承Prim算法，用到结点和图的结构，还是用邻接矩阵来计算最小生成树
public class Kruskal extends Prim{
    //先完成集合的并和找根操作,合并根为i和j的两个树,set为结点编号和parent编号对应的二元数组
    public void union(int i,int j,int[][] set){
        int x;
        x = set[i][1] + set[j][1];
        if(set[i][1] > set[j][1]) {
            set[i][1] = j;
            set[j][1] = x;
        }
        else {
            set[j][1] = i;
            set[i][1] = x;
        }
    }
    //在set集合中找出i的根
    public int findRoot(int i,int[][] set){
        int k = i;
        while(set[k][1] > 0)
            k = set[k][1];
        return k;
    }
    //将Graph中的边按权值存储在队列中
    public Queue<Edge> sortEdges(Graph g){
        Queue<Edge> queue = new LinkedList<>();
        int[][] matrix = g.edges;
        //将e条边按递增排序
        for(int k = 0; k < g.e ; k++) {
            //记录最小边和最小边的两个结点编号
            int min = Infinity,a = -1,b = -1;
            for (int i = 0; i < g.n; i++) {
                for (int j = i; j < g.n; j++) {
                    if (matrix[i][j] != 0 && matrix[i][j] < min) {
                        min = matrix[i][j];
                        a = i;
                        b = j;
                    }
                }
            }
            if(a  == -1 || b == -1)
                break;
            matrix[a][b] = 0;
            Edge edge = new Edge(a,b);
            queue.offer(edge);
        }
        return queue;
    }
    //Kruskal找出最小生成树
    public void kruskal(Graph g){
        //mincost为最小生成树的成本,count为记录最小生成树中的边的条数
        int mincost = 0,count = 0;
        int[][] set = new int[g.n][2];
        List<Edge> edges = new ArrayList<>();
        //先将set集合初始化,并且从1开始存储
        for(int i = 0; i <= g.n; i++) {
            set[i][0] = i;
            set[i][1] = -1;
        }
        Queue<Edge> queue = sortEdges(g);
        //还要加n-1条边进来
        while(!queue.isEmpty() && count < g.n -1){
            Edge edge = queue.poll();
            int a = findRoot(edge.a,set);
            int b = findRoot(edge.b,set);
            //不构成回路
            if(a != b)
            {
                //加一条边进来
                count++;
                edges.add(edge);
                mincost += g.edges[edge.a][edge.b];
                //合并两个集合
                union(edge.a,edge.b,set);
            }
        }
        if(count != g.n - 1) {
            System.out.println("No Spanning Tree!");
            return;
        }
        System.out.println("最小生成树的权值：" + mincost);
        System.out.println("最小生成树的各边为：");
        for(Iterator<Edge> iterator = edges.iterator();iterator.hasNext();)
        {
            Edge edge = iterator.next();
            System.out.println(g.nodes[edge.a].name + "->" + g.nodes[edge.b].name);
        }
    }

    public static void main(String[] args) {
        Kruskal kk = new Kruskal();
//        int[][] set = {{0,0},{1,-2},{2,-3},{3,1},{4,2},{5,4}};
//        kk.union(1,2,set);
//        System.out.println(kk.findRoot(4,set));
        Prim prim = new Prim();
        //生成一个图
        Graph g = prim.generateGraph();
//        Queue<Edge> queue = kk.sortEdges(g);
//        for(Edge edge : queue){
//            System.out.println(g.nodes[edge.a].name + " -> " + g.nodes[edge.b].name);
//        }
        kk.getSpanningTree(g);
    }
}
