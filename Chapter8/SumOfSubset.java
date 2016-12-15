package Chapter8;

import java.util.Arrays;
import java.util.Collections;

/**
 * Created by duncan on 16-12-14.
 */
public class SumOfSubset {
    //flag用来判断是否加入,w为元素集合，M为要求之和
    public static final int[] flag = new int[100];
    public static final int[] w = {0,5,10,12,13,15,18};
    public static final int M = 30;
    public int sum(int n){
        int sum = 0;
        for(int i = 1; i <= n; i++)
            sum += w[i];
        return sum;
    }
    //current代表当前集合中已有和，k表示当前判断的第k个元素，rest代表集合中剩余元素之和
    public void sumOfsubset(int current,int k,int rest){
        //先生成左儿子,w[k]加入
        flag[k] = 1;
        if(current + w[k] == M){
            System.out.print("{\t");
            //子集找到，输出
            print(k);
            System.out.println();
        }
        else if(current + w[k] + w[k + 1] <= M)
            sumOfsubset(current + w[k],k + 1,rest - w[k]);
        //生成右儿子,w[k]不加入
        if((current + rest - w[k] >= M) && (current + w[k + 1] <= M)){
            flag[k] = 0;
            sumOfsubset(current,k + 1,rest - w[k]);
        }
    }

    public void print(int n){
        for(int i = 1; i <= n; i++)
            if(flag[i] == 1)
                System.out.print(w[i] + ",\t");
        System.out.print("}");
    }

    public static void main(String[] args) {
        System.out.println("解空间包含以下子集:");
        SumOfSubset ss = new SumOfSubset();
        ss.sumOfsubset(0,1,ss.sum(6));
    }
}
