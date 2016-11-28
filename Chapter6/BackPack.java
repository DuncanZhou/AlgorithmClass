package Chapter6;

import Chapter5.BasedTimeJobs;

/**
 * Created by duncan on 16-11-28.
 */
public class BackPack {
    private static final int maxsize = 100;
    //p为物品价值，w为物品重量，M为背包总重量，n为物品个数,p和w从1开始存放
    public void DKNAP(int[] p,int[] w,int n,int M){
        //P和W用来存放相应的序偶对,First为序偶对相应的开始处,start指向当前序偶对开始处，end指向结束位置，next为下一序偶对开始处，pp，ww为暂存当前物品价值和重量，ｘ数组存储物品的存储状况
        //P和W从1开始存放,x从１开始存储
        int[] x = new int[n + 1];
        int[] P = new int[maxsize];
        int[] W = new int[maxsize];
        //First[0:n]
        int[] First = new int[n + 2];
        int start,end,current,pp,ww,next;
        //初始化,完成S0
        First[0] = 1;
        P[1] = 0;
        W[1] = 0;
        start = end = 1;
        First[1] = next = 2;
        //向下继续生成Si,还要试探加入n个物品，所以n次循环
        for(int i = 1; i <= n ; i++){
            //每次从Si-1的第一个开始加入，边加入边进行判断删减,current指向Si-1中当前要判断的物品
            current = start;
            //找出不大于M的最大的下标处
            int u = end;
            while(u >= start)
            {
                if(W[u] + w[i] > M)
                    u--;
                else
                    break;
            }
            //物品加不了了，不能再加了
            if(u < start)
                break;
            //生成Si并归并
            for(int j = start; j <= u; j++){
                pp = P[j] + p[i];
                ww = W[j] + w[i];
                //开始判断
                //首先比ww小的序偶对都可以加入
                while(current <= end && W[current] < ww){
                    P[next] = P[current];
                    W[next] = W[current];
                    next++;
                    current++;
                }
                //和ww一样大，将大的赋值给pp和ww
                if(current <= end && W[current] == ww) {
                    pp = Math.max(P[current], pp);
                    current++;
                }
                //还要再判断pp和已经加入进来的，价值是不是比他大，如果价值大于则可以加入，否则重量大价值小不能加入
                if(pp > P[next - 1]){
                    P[next] = pp;
                    W[next] = ww;
                    next++;
                }
                //清除重量大价值小的，不加入进来
                while(current <= end && P[current] <= P[next - 1]) {
                    current++;
                }
            }
            //将Si-1中剩余的序偶对加入
            while(current <= end){
                P[next] = P[current];
                W[next] = W[current];
                next++;
                current++;
            }
            //对Si+1属性赋值
            start = end + 1;
            end = next - 1;
            First[i + 1] = next;
            System.out.println("S" + i);
            System.out.print("价值 ");
            for(int s = start; s <= end; s++)
                System.out.print(P[s]+" ");
            System.out.println();
            System.out.print("重量 ");
            for(int s = start; s <= end; s++)
                System.out.print(W[s]+" ");
            System.out.println("\n");
        }
        //回溯求ｘ1-xn的解
        int px,wx,py,wy;
        for(int count = n; count >= 1; count--){
            //ｐｘ和ｗｘ为Sn-1的最末序偶对
            px = P[First[count] - 1];
            wx = W[First[count] - 1];
            int j;
            for(j = First[count] - 1; j >= First[count - 1]; j--){
                //找出Ｓn-1中与pn加起来最大且不超过M的序偶对
                if(W[j] + w[count] <= M)
                    break;
            }
            py = P[j] + p[count];
            wy = W[j] + w[count];
            if(px > py || wy > M)
                x[count] = 0;
            else{
                x[count] = 1;
                M -= w[count];
            }
        }
        System.out.println("最大价值为:" + P[end] + "\n");
        System.out.println("物品1~n存放情况为:");
        for(int i = 1; i <= n; i++) {
            if(x[i] == 1)
                System.out.print("物品" + i + ":放入\t");
            else
                System.out.print("物品" + i + ":不放\t");
        }
    }

    public static void main(String[] args) {
        int[] p = {0,1,2,5};
        int[] w = {0,2,3,4};
        int M = 6;
        BackPack bp = new BackPack();
        bp.DKNAP(p,w,3,M);
    }
}
