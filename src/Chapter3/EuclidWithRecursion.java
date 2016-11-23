package Chapter3;

/**
 * Created by DuncanZhou on 2016/10/20.
 */
public class EuclidWithRecursion {
    public int GCD(int a,int b){
        if(b == 0)
            return a;
        else
            return GCD(b,a % b);
    }

    public static void main(String[] args) {
        EuclidWithRecursion er = new EuclidWithRecursion();
        System.out.println(er.GCD(24,12));
    }
}
