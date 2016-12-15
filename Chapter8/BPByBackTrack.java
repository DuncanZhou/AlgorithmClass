package Chapter8;
/**
 * Created by duncan on 16-12-14.
 */
public class BPByBackTrack {
    int bestvalue = 0;
    int[] lastfalg;
    public void swap(int[] x,int m,int n){
        int temp;
        temp = x[m];
        x[m] = x[n];
        x[n] = temp;
    }
    //先将物品效益和重量按照P/W的大小非递减排序,p和w都从1开始存储
    public void sort(int[] p,int[] w){
        int len = p.length -1 ;
        for(int i = 1; i <= len; i++){
            for(int j = 1; j <= len - i; j++){
                    if(((float)p[j] / w[j]) > ((float)p[j + 1] / w[j + 1])){
                        swap(p,j,j + 1);
                        swap(w,j,j + 1);
                    }
            }
        }
    }

    //限界函数,k为上次去掉的物品，M为背包容量,currentp为当前背包中效益值，currentw为背包中当前重量，返回值为当前最大值上限
    public int Bound(int[] p,int[] w,int k,int M,int currentp,int currentw){
        int tempp = currentp,tempw = currentw;
        int len = p.length - 1;
        for(int i = k + 1; i <= len; i++){
            tempw += w[i];
            if(tempw < M)
                tempp += p[i];
            else
                return (tempp + (1 - (tempw - M) / w[i] * p[i]));
        }
        return tempp;
    }

    //回溯法求解背包问题,p,w为效益值和重量数组，M为背包容量，k为当前处理的物品，flag为记录物品放或不放的标志数组,currentp为当前效益，currentw为当前重量
    public void BKNAPBT(int[] p, int[] w,int M,int k,int[] flag,int currentp,int currentw){
        int n = p.length - 1;
        if(k > n){
                if(currentp > bestvalue) {
                    bestvalue = currentp;
                    lastfalg = flag.clone();
                    return;
                }
        }
        else{
            //进入左子树
            if(currentw + w[k] <= M)
            {
                flag[k] = 1;
                BKNAPBT(p,w,M,k + 1,flag,currentp + p[k],w[k] + currentw);
            }
            //进入右子树前先判断右子树的最大上限是否能够比当前最优值大，如果没有则减去右子树
            if(Bound(p,w,k,M,currentp,currentw) >= currentp) {
                flag[k] = 0;
                BKNAPBT(p, w, M, k + 1, flag, currentp, currentw);
            }
        }
    }
    public void print(int[] p, int[] w){
        int len = p.length -1;
        int m = 0,v = 0;
        System.out.println("\n最终放入背包的物品的价值为:");
        for(int i = 1; i <= len; i++){
            if(lastfalg[i] == 1) {
                m += w[i];
                v += p[i];
                System.out.print(p[i] + " ");
            }
        }
        System.out.println("\n\n最终重量为：" + m);
        System.out.println("\n最优解价值为:" + v);
    }
    public static void main(String[] args) {
        int m = 0,M = 110;
        BPByBackTrack bp = new BPByBackTrack();
        int[] w = {0,1,11,21,23,33,43,45,55};
        int[] p = {0,11,21,31,33,43,53,55,65};
        int[] flag = {0,0,0,0,0,0,0,0,0};
        System.out.println("物品价值为");
        for(int i = 1 ; i <= p.length -1 ;i++){
            System.out.print(p[i] + "\t");
        }
        System.out.println("\n物品重量为");
        for(int i = 1 ; i <= p.length -1 ;i++){
            System.out.print(w[i] + "\t");
        }
        System.out.println();
        bp.sort(p,w);
        bp.BKNAPBT(p,w,M,1,flag,0,0);
        System.out.println("背包容量为:" + M);
        bp.print(p,w);
    }
}
