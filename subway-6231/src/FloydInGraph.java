
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class FloydInGraph {

    private static int INF = Integer.MAX_VALUE;
    //dist[i][j]=INF<==>i 和 j之间没有边
    private int[][] dist;
    //顶点i 到 j的最短路径长度，初值是i到j的边的权重
    private int[][] path;
    public int [] path1;
    private List<Integer> result = new ArrayList<Integer>();

    public List<Integer> getPath(int num1,int num2) {
        FloydInGraph graph = new FloydInGraph(15);
        int i=0;
        int[][] matrix =
               /* {{INF, 30, INF, 10, 50}, {INF, INF, 60, INF, INF}, {INF, INF, INF, INF, INF}, {INF, INF, INF, INF, 30},
                        {50, INF, 40, INF, INF},};*/
               {{INF,INF,2,4,INF,INF,INF,INF,INF,INF,INF,INF,INF,INF,INF},
                {INF,INF,INF,INF,3,2,INF,INF,INF,INF,INF,INF,INF,INF,INF},
                {INF,6,INF,INF,INF,INF,3,INF,INF,INF,INF,INF,INF,INF,INF},
                {INF,INF,3,INF,INF,INF,INF,3,INF,INF,INF,INF,INF,INF,INF},
                {INF,INF,INF,INF,INF,2,INF,INF,INF,INF,5,INF,INF,INF,INF},
                {INF,INF,INF,INF,INF,INF,4,INF,4,INF,INF,INF,INF,INF,INF},
                 {INF,INF,INF,INF,INF,INF,INF,3,3,3,INF,INF,INF,INF,INF},
                 { INF,INF,INF,INF,INF,INF,INF,INF,INF,3,INF,INF,INF,INF,INF},
                       { INF,INF,INF,INF,INF,INF,INF,INF,INF,INF,5,2,INF,INF,INF},
        { INF,INF,INF,INF,INF,INF,INF,INF,INF,INF,INF,1,INF,INF,INF},
        {INF,INF,INF,INF,INF,INF,INF,INF,INF,INF,INF,INF,4,INF,INF},
        {INF,INF,INF,INF,INF,INF,INF,INF,INF,INF,INF,INF,INF,INF,2},
        {INF,INF,INF,INF,INF,INF,INF,INF,INF,INF,INF,INF,INF,1,INF},
        {INF,INF,INF,INF,INF,INF,INF,INF,INF,INF,INF,INF,1,INF,1},
        {INF,INF,INF,INF,INF,INF,INF,INF,INF,INF,INF,INF,INF,1,INF},};

        int begin = num1;
        int end = num2;
        graph.findCheapestPath(begin, end, matrix);
        List<Integer> list = graph.result;
       /* System.out.println(begin + " to " + end + ",the cheapest path is:");
        System.out.println(list.toString());
        System.out.println(graph.dist[begin][end]);*/
        Iterator it=list.iterator();
       /* while(it.hasNext()){
            Object obj=it.next();
           System.out.println(obj);

        }*/

        return list;
    }

    void findCheapestPath(int begin, int end, int[][] matrix) {
        floyd(matrix);
        result.add(begin);
        findPath(begin, end);
        result.add(end);
    }

    void findPath(int i, int j) {
        int k = path[i][j];
        if (k == -1) {
            return;
        }
        findPath(i, k);   //递归
        result.add(k);
        findPath(k, j);
    }

    void floyd(int[][] matrix) {
        int size = matrix.length;
        //initialize dist and path
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                path[i][j] = -1;
                dist[i][j] = matrix[i][j];
            }
        }
        for (int k = 0; k < size; k++) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (dist[i][k] != INF &&
                            dist[k][j] != INF &&
                            dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                        path[i][j] = k;
                    }
                }
            }
        }

    }

    public FloydInGraph(int size) {
        this.path = new int[size][size];
        this.dist = new int[size][size];
    }
}
