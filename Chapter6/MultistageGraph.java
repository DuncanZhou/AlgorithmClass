package Chapter6;

/**
 * Created by duncan on 16-11-25.
 */
public class MultistageGraph {
    private final static int Infinity = Integer.MAX_VALUE;
    private final static int maxsize = 100;
    public class multiStageGraph{
        //matrix为多段图的表示,n为结点数,matrix从1开始存储可以和结点的编号对应起来
        int[][] matrix = new int[maxsize][maxsize];
        int n;
    }
    public multiStageGraph generateMultistageGraph(){
        int[][] matrix = {{0,0,0,0,0,0,0,0,0,0,0,0,0},{0,0,9,7,3,2,Infinity,Infinity,Infinity,Infinity,Infinity,Infinity,Infinity},{0,Infinity,0,Infinity,Infinity,Infinity,4,2,1,Infinity,Infinity,Infinity,Infinity},{0,Infinity,Infinity,0,Infinity,Infinity,2,7,Infinity,Infinity,Infinity,Infinity,Infinity},{0,Infinity,Infinity,Infinity,0,Infinity,Infinity,Infinity,11,Infinity,Infinity,Infinity,Infinity},{0,Infinity,Infinity,Infinity,Infinity,0,Infinity,11,8,Infinity,Infinity,Infinity,Infinity},{0,Infinity,Infinity,Infinity,Infinity,Infinity,0,Infinity,Infinity,6,5,Infinity,Infinity},{0,Infinity,Infinity,Infinity,Infinity,Infinity,Infinity,0,Infinity,4,3,Infinity,Infinity},{0,Infinity,Infinity,Infinity,Infinity,Infinity,Infinity,Infinity,0,Infinity,5,6,Infinity},{0,Infinity,Infinity,Infinity,Infinity,Infinity,Infinity,Infinity,Infinity,0,Infinity,Infinity,4},{0,Infinity,Infinity,Infinity,Infinity,Infinity,Infinity,Infinity,Infinity,Infinity,0,Infinity,2},{0,Infinity,Infinity,Infinity,Infinity,Infinity,Infinity,Infinity,Infinity,Infinity,Infinity,0,5},{0,Infinity,Infinity,Infinity,Infinity,Infinity,Infinity,Infinity,Infinity,Infinity,Infinity,Infinity,0}};
        multiStageGraph g = new multiStageGraph();
        g.matrix = matrix;
        g.n = 12;
        return g;
    }
    public void FGRPATH(multiStageGraph g){
        //定义一个cost数组存储每个结点到终点的最短路径长度，定义一个Des数组存储每个结点到终点最短时下一个结点
        int[] cost = new int[g.n + 1];
        int[] des = new int[g.n + 1];
        cost[g.n] = 0;
        //计算cost数组
        for(int j = g.n - 1; j >= 1;j--){
            int min = Infinity,r = 0;
            //按照书上算法，找出r这样的一个结点，使得j到r有边且加上r到终点的路径长度最短
            for(int i = j + 1; i <= g.n; i++){
                if((g.matrix[j][i] != Infinity) && (g.matrix[j][i] + cost[i] < min)){
                    min = g.matrix[j][i] + cost[i];
                    r = i;
                }
            }
            des[j] = r;
            cost[j] = min;
        }
        System.out.println("最短路径为：" + cost[1]);
        System.out.println("最短路径path为：");
        System.out.print("1");
        int k = des[1];
        for(int i = 2; i <= g.n; i++) {
            System.out.print("->" + k);
            if(k == 12)
                break;
            k = des[k];
        }
    }

    public static void main(String[] args) {
        MultistageGraph msg =  new MultistageGraph();
        multiStageGraph g = msg.generateMultistageGraph();
        msg.FGRPATH(g);
    }
}
