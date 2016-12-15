package Chapter8;

/**
 * Created by duncan on 16-12-14.
 */
public class NQueen {
    public static final int maxsize = 100;
    //定义一个全局数组,表示皇后的列号
    public static final int [] col =  new int[maxsize];
    //判断第k个皇后是否能放
    public boolean Place(int k){
        int i = 1;
        while(i < k){
            //检测是否在同一列或者在同一对角线上
            if(col[i] == col[k] || (Math.abs(i - k) == (Math.abs(col[i] - col[k]))))
                return false;
            i ++;
        }
        return true;
    }
    public void print(int n){
        for(int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (j != col[i])
                    System.out.print('N' + " ");
                else
                    System.out.print("Q" + " ");
            }
            System.out.println();
        }
    }
    //回溯法求解n-queen问题,k为当前要放置的皇后
    public void NQ(int n,int k){
        col[k] = 1;
        int i = 1;
        while(col[k] <= n){
            if(Place(k)){
                if(k == n) {
                    print(n);
                    System.out.println();
                }
                else
                    NQ(n,k + 1);
            }
            //换个列放
            col[k]++;
        }
    }

    public static void main(String[] args) {
        System.out.println("N表示未放置皇后，Q表示放置皇后");
        NQueen nq = new NQueen();
        nq.NQ(6,1);
    }
}
