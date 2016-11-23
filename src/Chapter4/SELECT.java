package Chapter4;

import java.text.DecimalFormat;

/**
 * Created by duncan on 16-11-7.
 */
public class SELECT {
    private static final int N = 100000;
    //分组长度为R
    private static final int R = 1000;
    private INSERTIONSORT insertionsort = new INSERTIONSORT();

    //生成100000条测试数据
    public double[] generateRandomNumber(){
        double[] result = new double[N];
        //用来取小数点后两位
        DecimalFormat df = new DecimalFormat("#.00");
        for(int i = 0; i < N;i++) {
            int k = (int)(Math.random() * 10);
            result[i] = Double.parseDouble(df.format(1000 * Math.random() + 1000 * k));
        }
        return result;
    }
    public void interchange(double[] A,int x,int y){
        double temp = A[x];
        A[x] = A[y];
        A[y] = temp;
    }
    //len为共计的长度，start为数组开始的起点，找出A中第k小的元素，并将第k小元素置于A[k]处，返回该第k小元素的下标
    public int select(double[] A,int start,int end,int k){
        int res = 0;
        //如果数组长度小于或等于分组长度，则直接插入排序
        if(end - start + 1 <= R) {
            insertionsort.insertsort(A, 0, end + 1);
         //   System.out.println(A[start + k -1]);
            return (start + k - 1);
        }

        while (true){
            int n = end - start + 1;
            for(int i = 1;i < n / R;i++){
                //循环分组求中值，并将每组中值移至数组前端
                //对每组进行插入排序
                insertionsort.insertsort(A,start + (i - 1) * R,start + i * R - 1);
                //将中间值放到数组前端,与前端原来的值进行交换
                interchange(A,start + (i - 1) * R + R / 2 - 1,start + i -1);
            }
            //最后得到中间值的中间值
            int mm = select(A,start,start + n / R - 1,(int)Math.ceil((double)(n / R) / 2));
            //把中值的中值放到最前端
            interchange(A,start,mm);
            //打印中值的中值
           // System.out.println("中值的中值为："+A[start]);
            //切分
            QuickSortByDividing qbd = new QuickSortByDividing();
            res = qbd.Partition(A,start,end);
            if(res - start + 1 == k)
                return res;
            else if(res - start + 1 > k)
                end = res - 1;
            else
            {
                k = k - (res - start + 1);
                start = res + 1;
            }
        }
    }

    public static void main(String[] args) {
        SELECT select = new SELECT();
        for(int i = 0; i< 5;i++) {
            //共6组测试数据
            double[] testarray = select.generateRandomNumber();
            double starttime = System.currentTimeMillis();
            System.out.println("第k小元素为"+testarray[select.select(testarray, 0, testarray.length - 1, 9999)]);
            double endtime = System.currentTimeMillis();
            System.out.println("共消耗" + (endtime - starttime) / 1000 + "s");
        }
    }
}
