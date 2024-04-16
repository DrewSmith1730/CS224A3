// jdh CS224 Spring 2023

import java.util.*;

public class Graph {
  List<Node> nodes;

  //------------------------------------------------

  public Graph() {
    nodes = new ArrayList<Node>();
  }

  //------------------------------------------------

  public void addNode(Node node) {
    for (Node n: this.nodes) {
      if (n == node) {
        System.out.println("ERROR: graph already has a node " + n.name);
        assert false;
      }
    }
    this.nodes.add(node);
  }

  //------------------------------------------------

  public void addEdge(Node n1, Node n2) {
    // outgoing edge
    int idx1 = this.nodes.indexOf(n1);
    if (idx1 < 0) {
      System.out.println("ERROR: node " + n1.name + " not found in graph");
      assert false;
    }

    int idx2 = this.nodes.indexOf(n2);
    if (idx2 < 0) {
      System.out.println("ERROR: node " + n2.name + " not found in graph");
      assert false;
    }

    n1.addEdge(n2);
  }

  //------------------------------------------------

  public List<Node> DFS(Node s) {
    // init S to be a stack with one element s
    Stack<Node> stack = new Stack<Node>();
    stack.push(s);
    // init explored[] to be false for all nodes
    List<Boolean> exploredBool = new ArrayList<>(nodes.size());
    // list all the nodes that are a connected component of the starting node for returning
    List<Node> explored = new ArrayList<>(nodes.size());
    // set all values in exploredBool to false
    for (int i = 0; i < nodes.size(); i++){
      exploredBool.add(false);
    }
    // while S is not empty
    while (stack.size() !=  0) {
      // take node u from s
      Node top = stack.pop();
      if (exploredBool.get(top.name) == false) {
        // set node as visited and add to return node list
        exploredBool.set(top.name, true);
        explored.add(top);
        // for each edge in that node add the node on the end to the stack
        for(int i = 0; i < top.adjlist.size(); i++){
          stack.push(top.adjlist.get(i));
        }
      }
    }
    return explored; // set of all the nodes visited
  } // DFS()

  //------------------------------------------------

  public List<Node> BFS(Node s) {
    // init discovered[] to be false for all nodes
    // init Q to be a queue
    List<Boolean> discoveredBool = new ArrayList<>(nodes.size());
    for (int i = 0; i < nodes.size(); i++){
      discoveredBool.add(false);
    }
    List<Node> discovered = new ArrayList<>(nodes.size());
    Queue<Node> queue = new LinkedList<>();

    discoveredBool.set(s.name, true);
    discovered.add(s);
    queue.add(s);

    while (queue.size() != 0){
      // remove the first node in the queue
      Node first = queue.remove();
      // loops all edges of first node
      for (int i = 0; i < first.adjlist.size(); i++) {
        if (discoveredBool.get(first.adjlist.get(i).name) == false) {
          // set it as discovered
          discoveredBool.set(first.adjlist.get(i).name, true);
          discovered.add(first.adjlist.get(i));
          // add node to the queue to be check its adjacency list
          queue.add(first.adjlist.get(i));
        }
      }
    }

    return discovered;
  } // BFS()

} // class Graph
