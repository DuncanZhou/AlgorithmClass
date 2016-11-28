package Chapter6;

/**
 * Created by duncan on 16-11-28.
 */
public class OBSTree {
    //P为检索成功概率，Q为检索不成功概率，为方便计算，P和Q都乘上一定整数,P从1开始存储，其余数组都从0开始存储
    public void OBST(int[] P,int[] Q,int n){
        //C用来存储树的成本，W存储树的权值,R存储每一步树的根
        int[][] C = new int[n + 1][n + 1];
        int[][] W = new int[n + 1][n + 1];
        int[][] R = new int[n + 1][n + 1];
        //初始化,将j-i=0和j-i=1计算完成
        for(int i = 0; i <= n - 1; i++){
            W[i][i] = Q[i];
            R[i][i] = C[i][i] = 0;
            W[i][i + 1] = Q[i] + Q[i + 1] + P[i + 1];
            R[i][i + 1] = i + 1;
            C[i][i + 1] = Q[i] + Q[i + 1] + P[i + 1];
        }
        W[n][n] = Q[n];
        R[n][n] = 0;
        C[n][n] = 0;
        //计算j-i=m(m>=2 && m<= n)
        for(int m = 2; m <= n; m++){
            for(int i = 0; i <= n - m; i++){
                int j = i + m;
                //计算W[i][j]
                W[i][j] = W[i][j - 1] + P[j] + Q[j];
                int k = 0,l;
                //在R[i][j - 1]～R[i + 1][j]中找出是C[i][l - 1] + C[l][j]最小的值k
                int min = Integer.MAX_VALUE;
                for(l = R[i][j - 1]; l <= R[i + 1][j]; l++)
                {
                    if(C[i][l - 1] + C[l][j] < min)
                    {
                        min = C[i][l - 1] + C[l][j];
                        k = l;
                    }
                }
                C[i][j] = W[i][j] + C[i][k - 1] + C[k][j];
                R[i][j] = k;
            }
        }
        System.out.println("最优二叉搜索树成本为：" + C[0][4] + "\n根为：" + R[0][4] + "\n成本为:" + W[0][4]);
    }

    public static void main(String[] args) {
        int[] P = {0,3,3,1,1};
        int[] Q = {2,3,1,1,1,};
        OBSTree obst = new OBSTree();
        obst.OBST(P,Q,4);
    }
}
