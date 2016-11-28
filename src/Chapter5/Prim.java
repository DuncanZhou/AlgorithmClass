package Chapter5;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by duncan on 16-11-22.
 */

public class Prim {
    public final int Infinity = Integer.MAX_VALUE;
    public final int maxsize = 100;
    //图结点
    protected class node{
        //结点的编号
        int nodeid;
        //结点名称
        char name;
    }
    protected class Edge{
        //边存储两个结点的编号
        int a;
        int b;
        public Edge(int p,int q){
            a = p;
            b = q;
        }
    }
    protected class Graph{
        //邻接矩阵
        int[][] edges = new int[maxsize][maxsize];
        //n为顶点数，e为边的条数
        int n,e;
        //nodes数组存放结点信息
        node[] nodes = new node[maxsize];
    }
    public Graph generateGraph(){
        Graph g = new Graph();
        int[][] edges = {{0,16,Infinity,Infinity,19,21},{16,0,5,6,Infinity,11},{Infinity,5,0,10,Infinity,Infinity},{Infinity,6,10,0,18,14},{19,Infinity,Infinity,18,0,33},{21,11,Infinity,14,33,0}};
        node[] nodes = new node[6];
        for(int i = 0; i < 6; i++){
            node pnode = new node();
            pnode.nodeid = i;
            pnode.name = (char)((i + 1) + '0');
            nodes[i] = pnode;
        }
        g.edges = edges;
        g.nodes = nodes;
        g.n = 6;
        g.e = 10;
        return g;
    }
    //用prim算法生成最小生成树
    public void getSpanningTree(Graph g){
        //mincost为最小生成树的权值，edges列表存储最小生成树中的边,near存储每个点到当前生成树中最短距离的结点的编号
        int mincost,minlen = Infinity,nodeid = -1;
        int[] near = new int[maxsize];
        List<Edge> edges = new ArrayList<>();
        //先把第一个点加入，并把和第一个点相邻最近的点也加入
        for(int i = 1; i < g.n; i++){
            if(g.edges[0][i] < minlen){
                minlen = g.edges[0][i];
                nodeid = i;
            }
        }
        //将这条边加入列表中
        Edge edge = new Edge(g.nodes[0].nodeid,g.nodes[nodeid].nodeid);
        edges.add(edge);
        //最小生成树的花销初始化
        mincost = g.edges[0][nodeid];
        //初始化near数组
        for(int i = 1; i < g.n; i++){
            if(i != nodeid) {
                if (g.edges[0][i] < g.edges[nodeid][i])
                    near[i] = 0;
                else
                    near[i] = nodeid;
            }
        }
        //因为这两个点已经加入生成树中
        near[0] = near[nodeid] = -1;
        //将其余n-2条边加入树中,所以有n-2次循环
        for(int i = 2; i < g.n; i++){
            //找出near中最小的结点的编号
            int min = Infinity,nearestnode = -1;
            for(int j = 0; j < g.n; j++){
                if(near[j] != -1 && g.edges[j][near[j]] < min) {
                    min = g.edges[j][near[j]];
                    nearestnode = j;
                }
            }
            //把结点加入树中
            if(nearestnode == -1) {
                System.out.println("No Spanning Tree!");
                return;
            }
            edge = new Edge(g.nodes[nearestnode].nodeid,g.nodes[near[nearestnode]].nodeid);
            edges.add(edge);
            mincost += min;
            near[nearestnode] = -1;
            //修改near数组
            for(int k = 0; k < g.n; k++){
                if(near[k] != -1 && g.edges[k][near[k]] > g.edges[k][nearestnode])
                    near[k] = nearestnode;
            }
        }
        if(mincost == Infinity || mincost < 0)
            System.out.println("No Spanning Tree!");
        System.out.println("最小生成树的权值：" + mincost);
        System.out.println("最小生成树的各边为：");
        for(Iterator<Edge> iterator = edges.iterator();iterator.hasNext();)
        {
            edge = iterator.next();
            System.out.println(g.nodes[edge.a].name + "->" + g.nodes[edge.b].name);
        }
    }

    public static void main(String[] args) {
        Prim prim = new Prim();
        Graph g = prim.generateGraph();
        prim.getSpanningTree(g);
    }
}
