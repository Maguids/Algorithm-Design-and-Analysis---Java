// A: Sociologia

import java.util.*;

class Edge {
    private int enode;
    
    Edge(int endv){
	enode = endv;
    }

    public int endnode() {
	return enode;
    }

}


class Node {
    //private int label;
    private LinkedList<Edge> neighbours;

    Node() {
	neighbours  = new LinkedList<Edge>();
    }

    public LinkedList<Edge> adjs() {
	return neighbours;
    }
   
}


class Graph0 {
    private Node verts[];
    private int nverts, nedges;
			
    public Graph0(int n) {
	nverts = n;
	nedges = 0;
	verts  = new Node[n+1];
	for (int i = 0 ; i <= n ; i++)
	    verts[i] = new Node();
        // vertex labels are integers from 1 to n (position 0 is not used)
    }
    
    public int num_vertices(){
	return nverts;
    }

    public int num_edges(){
	return nedges;
    }

    public LinkedList<Edge> adjs_no(int i) {
	return verts[i].adjs();
    }
    
    public void insert_new_edge(int i, int j){
	verts[i].adjs().addFirst(new Edge(j));
        nedges++;
    }

    public Edge find_edge(int i, int j){
	for (Edge adj: adjs_no(i))
	    if (adj.endnode() == j) return adj;
	return null;
    }
}



public class A {


    // fazer dfs todo (grafo todo)
    public static Stack<Integer> dfs(Graph0 g) {
        Stack<Integer> s = new Stack<>();
        boolean[] visited = new boolean[g.num_vertices() + 1];
        for (int i = 0; i <= g.num_vertices(); i++) {visited[i] = false;}

        for (int v = 1; v <= g.num_vertices(); v++) {
            if(!visited[v]) {dfs_visit(v, g, s, visited);}
        }
        return s;
    }

    // faz o dfs só a partir de um vértice
    public static void dfs_visit(int v, Graph0 g, Stack<Integer> s, boolean[] visited) {
        visited[v] = true;
        for (Edge adj : g.adjs_no(v)) {
            int neighbor = adj.endnode();
            if (!visited[neighbor]) {dfs_visit(neighbor, g, s, visited);}
        }
        s.push(v);
    }

    public static void kosaraju(Graph0 g, Graph0 gt) {

        Stack<Integer> s = dfs(g);

        boolean[] visited = new boolean[g.num_vertices() + 1];
        for (int i = 0; i <= g.num_vertices(); i++) {visited[i] = false;}

        int n_grupos = 0;
        int n_pessoas = 0;

        while (!s.isEmpty()) {
            int counter = 0;
            int v = s.pop();
            if (!visited[v]) {
                Stack<Integer> temp = new Stack<>();    // guarda os vértices da componente conexa
                dfs_visit(v, gt, temp, visited);
                while(!temp.isEmpty()) {
                    temp.pop();
                    counter += 1;
                }
                if (counter >= 4) {n_grupos += 1;}
                else {n_pessoas += counter;}
            }
        }

        System.out.println(n_grupos + " " + n_pessoas);
    }

    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        int n_casos = stdin.nextInt();
        for (int i = 1; i<= n_casos; i++) {
            int n = stdin.nextInt();
            Graph0 g = new Graph0(n);
            Graph0 gt = new Graph0(n);
            for (int j = 1; j <= n; j++) {
                int v = stdin.nextInt();
                int m = stdin.nextInt();
                for(int k = 0; k < m; k++) {
                    int cur = stdin.nextInt();
                    g.insert_new_edge(v, cur);
                    gt.insert_new_edge(cur, v);
                }
            }
            System.out.println("Caso #" + i);
            kosaraju(g, gt);
        }

    }
}