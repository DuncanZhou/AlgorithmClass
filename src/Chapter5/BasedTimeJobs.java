package Chapter5;

import java.util.Date;

/**
 * Created by duncan on 16-11-22.
 */
public class BasedTimeJobs{
    //Deadline为作业期限，Job为作业的效益值，n为作业个数，返回最大效益
    public int GetBestJobSequence(int[] Deadline,int[] Job,int n){
        //先按照作业的效益值递减排序,冒泡排序
        //count统计最优解中的作业个数，bestJobSet为最优解的效益集合
        int flag = 0,count = 0,best = 0;
        int[] bestJobSet = new int[n];
        int[] resultDeadline = new int[n];
        for(int i = 0; i < n - 1; i ++){
            for(int j = 0; j < n - i - 1; j++){
                if(Job[j] < Job[j + 1]){
                    int temp = Job[j];
                    int tempd = Deadline[j];
                    Deadline[j] = Deadline[j + 1];
                    Job[j] = Job[j + 1];
                    Job[j + 1] = temp;
                    Deadline[j + 1] = tempd;
                    flag = 1;
                }
                if(flag == 0)
                    break;
                else
                    flag = 0;
            }
        }
        //从1的位置开始放
        bestJobSet[0] = 0;
        resultDeadline[0] = 0;
        //放入作业1
        count++;
        bestJobSet[1] = Job[0];
        resultDeadline[1] = Deadline[0];
        //从Job的第二个作业开始判断
        for(int i = 1; i < n; i ++){
            int r = count;
            //如果当前结果中的期限比当前要插入的作业期限大&&结果中的期限还有向后推的空间，则当前作业可以插入
            while(resultDeadline[r] > Deadline[i] && resultDeadline[r] != r)
                r--;
            //在r+1处插入作业i
            if(resultDeadline[r] <= Deadline[i] && Deadline[i] > r){
                //可以插入
                for(int j = count; j >= r + 1; j--){
                    bestJobSet[j + 1] = bestJobSet[j];
                    resultDeadline[j + 1] = resultDeadline[j];
                }
                //在r+1位置插入作业
                bestJobSet[r + 1] = Job[i];
                resultDeadline[r + 1] = Deadline[i];
                count++;
            }
        }
        //统计总的效益
        System.out.println("最优解作业序列为：");
        for(int i = 1; i <= count;i++){
            System.out.print(bestJobSet[i] + " ");
            best += bestJobSet[i];
        }
        System.out.println();
        System.out.println("最优解作业个数为："+ count + "\n" +"最大效益为：" + best);
        return best;
    }

    public static void main(String[] args) {
        int[] Deadline = {1,3,4,2,1,2};
        int[] Job = {3,8,20,6,7,5};
        BasedTimeJobs btj = new BasedTimeJobs();
        btj.GetBestJobSequence(Deadline,Job,6);
    }
}
