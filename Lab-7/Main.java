class Main {
  public static void main(String[] args) {
    String[] vertices = {
      "Liberal Arts",
      "Student Services",
      "Health Careers & Sciences",
      "Health Technologies Center",
      "Recreation Center",
      "Technology Learning Center",
      "Business & Technology",
      "Theatre"
    };

    int[][] edges = {
      {0, 7}, // Liberal Arts to Theatre
      {0, 1}, // Liberal Arts to Student Services
      {0, 5}, // Liberal Arts to Technology Learning Center
      {7, 6}, // Theatre to Business & Technology
      {6, 1}, // Business & Technology to Student Services
      {6, 5}, // Business & Technology to Technology Learning Center
      {1, 4}, // Student Services to Recreation Center
      {1, 5}, // Student Services to Technology Learning Center
      {1, 2}, // Student Services to Health Careers & Sciences
      {4, 5}, // Recreation Center to Technology Learning Center
      {4, 2}, // Recreation Center to Health Careers & Sciences
      {2, 3}  // Health Careers & Sciences to Health Technologies Center
    };

    Graph<String> graph = new UnweightedGraph<>(vertices, edges);
    UnweightedGraph<String>.SearchTree dfs = graph.dfs(graph.getIndex("Business & Technology")); // Get a dfs starting at the Business and Technology Building. Change this is you called it something different in your vertices!

    //paths
    System.out.println("Paths from Business & Technology: ");
    dfs.printPath(graph.getIndex("Health Technologies Center "));
    System.out.println();
    dfs.printPath(graph.getIndex("Student Services "));
    System.out.println();
    dfs.printPath(graph.getIndex("Recreation Center "));
    System.out.println();

    //entire tree
    System.out.println("\nThe entire DFS tree: ");
    dfs.printTree();


    java.util.List<Integer> searchOrders = dfs.getSearchOrder();
    System.out.println(dfs.getNumberOfVerticesFound() + " vertices are searched in this DFS order:");
    for (int i = 0; i < searchOrders.size(); i++)
      System.out.print(graph.getVertex(searchOrders.get(i)) + " ");
    System.out.println();

    for (int i = 0; i < searchOrders.size(); i++)
      if (dfs.getParent(i) != -1)
        System.out.println("parent of " + graph.getVertex(i) +
          " is " + graph.getVertex(dfs.getParent(i)));

    
  }
}