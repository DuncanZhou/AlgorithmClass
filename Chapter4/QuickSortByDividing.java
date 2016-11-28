package Chapter4;

/**
 * Created by DuncanZhou on 2016/11/2.
 */
public class QuickSortByDividing {
    //Partition,return the dividing point index
    public int Partition(double[] A,int start,int end){
        double temp = A[start];
        int p = start, q = end;
        while( p < q ){
            while(A[q] >= temp && q > start)
                q--;
            if(p < q) {
                A[p] = A[q];
                p++;
            }
            while(A[p] <= temp && p < end)
                p++;
            if(p < q){
                A[q] = A[p];
                q--;
            }
        }
        A[q] = temp;
        return q;
    }
    //QuickSort
    public void QuickSort(double[] A,int start,int end){
        if(start >= end)
            return;
        int point = Partition(A,start,end);
        QuickSort(A, start, point - 1);
        QuickSort(A, point + 1, end);

    }

    public static void main(String[] args) {
        QuickSortByDividing qs = new QuickSortByDividing();
        double[] A = {6,2,5,8,1,3,7};
        qs.QuickSort(A,0,A.length - 1);
        for(int i = 0; i < A.length; i++)
            System.out.print(A[i]+" ");
    }
}
