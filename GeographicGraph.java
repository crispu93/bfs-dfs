import java.io.IOException;

public class GeographicGraph {
    Graph G;    // Inner graph
    int n;  // Number of nodes
    double d;    // Max distance in every arist 0<=d<=1
    int side;   // Division in side x side cells of unitary rectangle

    public GeographicGraph(int n, double d, boolean directed, boolean self) {
        this.n = n;
        this.d = d;

        G = new Graph(directed, self);

        while(n-- != 0) {
            G.addNode("N" + (this.n-n));
        }

        side = (int) Math.ceil(Math.sqrt(this.n));  

        for(int i=1; i<=this.n; i++) {
            for(int j=1; j<=this.n; j++) {
                if (i == j) {
                    if (G.isMultiGraph()) {
                        G.addEdge("N"+i,"N"+j);
                    }
                }
                else if (this.distance(i,j) <= this.d) {
                    G.addEdge("N"+i,"N"+j);
                }
            }
        }
    }

    public double distance(int n1, int n2) {
        int x0 = n1 % this.side;
        int y0 = (int) Math.floor(n1 / this.side);
        int x1 = n2 % this.side;
        int y1 = (int) Math.floor(n2 / this.side);

        double dist = Math.sqrt(Math.pow(x1-x0,2) + Math.pow(y1-y0,2) );
        dist = dist/this.side;
        return dist;
    }

    public void printEdges(){
        this.G.printEdges();
    }

    public void graphToFile(String filename) throws IOException{
        try {
            this.G.graphToFile(filename);
        }catch(IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public Graph BFS(String s){
        Graph Tree = this.G.BFS(s);
        Tree.printEdges();
        return Tree;
    }

    public Graph DFS_I(String s){
        Graph Tree = this.G.DFS_I(s);
        Tree.printEdges();
        return Tree;
    }

    public Graph DFS_R(String s){
        Graph Tree = this.G.DFS_R(s);
        Tree.printEdges();
        return Tree;
    }
    
    public static void main(String[] args) {
        GeographicGraph g1 = new GeographicGraph(30,0.3,true,false);
        GeographicGraph g2 = new GeographicGraph(100,0.3,true,false);
        GeographicGraph g3 = new GeographicGraph(500,0.3,true,false);

        Graph T1_1 = g1.BFS("N1");
        Graph T1_2 = g1.DFS_I("N1");
        Graph T1_3 = g1.DFS_R("N1");

        Graph T2_1 = g2.BFS("N1");
        Graph T2_2 = g2.DFS_I("N1");
        Graph T2_3 = g2.DFS_R("N1");

        Graph T3_1 = g3.BFS("N1");
        Graph T3_2 = g3.DFS_I("N1");
        Graph T3_3 = g3.DFS_R("N1");
        try {
            g1.graphToFile("csv/Geographic1.csv");
            g2.graphToFile("csv/Geographic2.csv");
            g3.graphToFile("csv/Geographic3.csv");
            T1_1.graphToFile("csv/GeographicTree1-1.csv");
            T1_2.graphToFile("csv/GeographicTree1-2.csv");
            T1_3.graphToFile("csv/GeographicTree1-3.csv");
            T2_1.graphToFile("csv/GeographicTree2-1.csv");
            T2_2.graphToFile("csv/GeographicTree2-2.csv");
            T2_3.graphToFile("csv/GeographicTree2-3.csv");
            T3_1.graphToFile("csv/GeographicTree3-1.csv");
            T3_2.graphToFile("csv/GeographicTree3-2.csv");
            T3_3.graphToFile("csv/GeographicTree3-3.csv");
        }catch(IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }        
    }

}