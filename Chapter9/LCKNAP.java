package Chapter9;
import java.util.Comparator;
import java.util.PriorityQueue;


public class LCKNAP {
    class Node {
        Node parent;
        int level;
        int tag;
        double capcity;
        double prof;
        double upperBound;
        @Override
        public String toString() {
            return "Node{" +
                    "level=" + level +
                    ", tag=" + tag +
                    ", capcity=" + capcity +
                    ", prof=" + prof +
                    ", upperBound=" + upperBound +
                    '}';
        }
    }
    /**
     * 物品数量
     */
    private int n;
    /**
     * 物品效益
     */
    private int[] p;
    /**
     * 物品重量
     */
    private int[] w;

    /**
     * 最优解
     */
    private int[] x;

    private PriorityQueue<Node> liveNodes;

    private double e = 0.1;

    public LCKNAP(int n, int[] p, int[] w) {
        set(n, p, w);
    }

    /**
     * 设置值，p/w的值按从大到小排
     * @param n
     * @param p
     * @param w
     */
    public void set(int n, int[] p, int[] w) {
        this.n = n;
        this.p = p;
        this.w = w;
        this.x = new int[n];
    }
    public void LCKNAP(int M) {
        Node ans = null;
        init();
        newNode(null, 0, -1, M, 0, 0);
        Node peek = liveNodes.poll();
        double[] lubound = LUBOUND(M, 0, 0);
        double l = lubound[0] - e;
        peek.upperBound = lubound[1];
//      System.out.println(peek);
        do{
            int i = peek.level;
            double cap = peek.capcity;
            double prof = peek.prof;
            if(i == n){//解节点
                if(prof > l){
                    l = prof;
                    ans = peek;
                }
            }else{
                if(cap>=w[i]){
                    newNode(peek,i+1,1,cap - w[i],prof+p[i],peek.upperBound);
                }
                lubound = LUBOUND(cap,prof,i+1);
                if(lubound[1] > l){
                    newNode(peek,i+1,0,cap,prof,lubound[1]);
                    l = Math.max(l,lubound[0] - e);
                }
            }
            if(liveNodes.isEmpty())
                break;
            else
                peek = liveNodes.poll();
//            System.out.println(peek);
        }while (peek.upperBound > l);
        finish(l,ans);
    }
    private void finish(double l, Node ans) {
        System.out.println("max prof:" + l);
        for (int i = n-1; i >=0 ; i--) {
            if(ans == null)
                break;
            if(ans.tag == 1) {
                System.out.print(i + " ");
                x[i] = 1;
            }
            ans = ans.parent;
        }
        System.out.println();
    }
    /**
     * 初始化活节点表
     */
    private void init() {
        x=new int[n];
        liveNodes = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                if (o1.upperBound > o2.upperBound)
                    return -1;
                else if (o1.upperBound == o2.upperBound)
                    return 0;
                else
                    return 1;
            }
        });
    }
    /**
     * 新增活节点
     *
     * @param parent 父节点
     * @param level  层级
     * @param t      左孩子or右孩子标志
     * @param cap    容量
     * @param prof   利益
     * @param ub     上界
     */
    private void newNode(Node parent, int level, int t, double cap, double prof, double ub) {
        Node node = new Node();
        node.upperBound = ub;
        node.capcity = cap;
        node.parent = parent;
        node.level = level;
        node.tag = t;
        node.prof = prof;
        liveNodes.add(node);
    }

    /**
     * 求上下界
     *
     * @param rw 剩余容量
     * @param cp 当前效益
     * @param k
     * @return [lbb, ubb]
     */
    private double[] LUBOUND(double rw, double cp, int k) {
        double l = cp;
        double u = 0;
        double c = rw;
        for (int i = k; i < n; i++) {
            if (c < this.w[i]) {
                u = l + c * p[i] / w[i];
                for (int j = i + 1; j < n; j++) {
                    if (c >= w[j]) {
                        c -= w[j];
                        l += p[j];
                    }
                }
                return new double[]{l, u};
            }
            c -= w[i];
            l += p[i];
        }
        u = l;
        return new double[]{l, u};
    }
    public void print() {
        System.out.print("X: ");
        for (int i = 0; i < n; i++) {
            System.out.print(x[i] + " ");
        }
        System.out.println();
    }
    public static void main(String[] args) {
        LCKNAP lcknap  = new LCKNAP(4,new int[]{10,10,12,18}, new int[]{2,4,6,9});
        lcknap.LCKNAP(15);
        lcknap.print();
    }
}
