package Chapter3;

/**
 * Created by DuncanZhou on 2016/10/20.
 */
public class MaxWithRecursion {
    public int Max(int [] array,int len){
        if(len == 1)
            return array[len - 1];
        else
            return (array[len - 1] > Max(array,len - 1) ? array[len - 1] : Max(array,len - 1));
    }

    public static void main(String[] args) {
        int[] array = {2,2,3,4,5,7,2,3,4,9};
        MaxWithRecursion mr = new MaxWithRecursion();
        System.out.println(mr.Max(array,array.length));
    }
}
