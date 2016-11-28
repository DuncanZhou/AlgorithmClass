package Chapter4;

/**
 * Created by duncan on 16-11-7.
 */
public class INSERTIONSORT {
    public void insertsort(double[] a,int start,int len){
        for(int i = start + 1;i < len;i++){
            int j = i - 1;
            double temp = a[i];
            while(j >= start) {
                if (a[j] > temp){
                    a[j + 1] = a[j];
                    j--;
            }else
                break;
            }
            a[j + 1] = temp;
        }
    }

    public static void main(String[] args) {
        double[] a = {9,5,3,2,7,8,6,1,4};
        INSERTIONSORT is = new INSERTIONSORT();
        is.insertsort(a,0,a.length);
        for(int i = 0; i < a.length;i++)
            System.out.print(a[i]+" ");
    }
}
