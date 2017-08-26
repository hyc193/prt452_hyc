package test1;

import java.util.ArrayList;

/**
 * Created by HAN on 2017/8/22.
 */
public class Graph {

	private int mGraph;
	private ArrayList<Integer>[] adj;

	Graph(int graph) {
		this.mGraph = graph;
		this.adj = new ArrayList[graph];
		for (int i = 0; i < graph; i++) {
			adj[i] = new ArrayList<>();
		}
	}

	public void addCoo(int v, int w) {
		this.adj[v].add(w);
	}

	public Graph getTranspose() {
		Graph graph = new Graph(this.mGraph);
		for (int v = 0; v < this.mGraph; v++) {
			for (int i : this.adj[v]) {
				graph.adj[i].add(v);
			}
		}
		return graph;
	}

	private boolean[] initArray(boolean[] b, boolean flag) {
		for (int i = 0; i < b.length; i++) {
			b[i] = flag;
		}
		return b;
	}

	public boolean isSC() {
		boolean[] visited = new boolean[this.mGraph];

		this.initArray(visited, false);
		this.DFSUtils(0, visited);
		for (int i = 0; i < visited.length; i++) {
			if (!visited[i])
				return false;
		}

		Graph gr = this.getTranspose();

		this.initArray(visited, false);
		gr.DFSUtils(0, visited);
		for (int i = 0; i < visited.length; i++) {
			if (!visited[i])
				return false;
		}
		return true;
	}
	
	protected void DFSUtils(int v, boolean[] isVisited) {
		isVisited[v] = true;
		for (int i : this.adj[v]) {
			if (!isVisited[i]) {
				this.DFSUtils(i, isVisited);
			}
		}
	}

	public static void main(String[] args) {
		Graph graph1 = new Graph(5);
		graph1.addCoo(0, 1);
		graph1.addCoo(1, 2);
		graph1.addCoo(2, 3);
		graph1.addCoo(3, 0);
		graph1.addCoo(2, 4);
		graph1.addCoo(4, 2);
		if (graph1.isSC()) {
			System.out.println("Yes");
		} else {
			System.out.println("No");
		}
		Graph graph2 = new Graph(4);
		graph2.addCoo(0, 1);
		graph2.addCoo(1, 2);
		graph2.addCoo(2, 3);
		if (graph2.isSC()) {
			System.out.println("Yes");
		} else {
			System.out.println("No");
		}
	}

}