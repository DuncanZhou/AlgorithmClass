package Chapter3;

/**
 * Created by DuncanZhou on 2016/10/20.
 */
public class BinarySearchWithRecursion {
    //二分法检索前提，数组已有序
    public int BinarySearch(int[] array,int target,int low,int high){
        int mid = ( low + high ) / 2;
        if( low > high )
            return -1;
        if(array[mid] == target)
            return mid;
        else if(array[mid] < target )
            return BinarySearch(array,target,low + 1,high);
        else
            return BinarySearch(array,target,low,high - 1);
    }

    public static void main(String[] args) {
        int [] array = {0,1,3,4,6,7,8,9,10};
        BinarySearchWithRecursion br = new BinarySearchWithRecursion();
        System.out.println(br.BinarySearch(array,2,0,array.length - 1));
    }
}
