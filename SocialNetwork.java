/* 
 * SocialNetwork.java
 *
 * Defines a new "SocialNetwork" type, which is a directed graph that
 * represents the connections between users in a social network
 *
 * Note that the SocialNetwork type is immutable: after the fields are initialized
 * in the constructor, they cannot be modified.
 *
 * Students may only use functionality provided in the packages
 *     java.lang
 *     java.util 
 *     java.io
 *     
 * Use of any additional Java Class Library components is not permitted 
 * 
 * David Palzer
 *
 */

import java.lang.*;
import java.util.*;
import java.io.*;

public class SocialNetwork {
    private HashMap<String,LinkedList<String>> adjacencyList;        // adj. list representation of graph
    private HashMap<String,LinkedList<String>> reverseAdjacencyList; // rev. adj. list representation of graph
    private double d;                                                // parameter for PageRank
    private double theta;                                            // parameter for PageRank
    private LinkedList<String> sortedNodes;                          // a list of the nodes, sorted

    // Constructor

    // SocialNetwork 
    // Preconditions:
    //     - networkFilename contains the path of a valid social network file
    //     - d is a PageRank parameter 
    //     - theta is the PageRank convergence criterion
    // Post-conditions
    //     - this.adjacencyList is populated
    //     - this.reverseAdjacencyList is populated
    //     - this.d is set to d
    //     - this.theta is set to theta
    //     - this.sortedNodes is a a linked list containing all of the nodes, sorted alphabetically ascending
    // Notes:
    //     - You should call loadNetworkFile to read networkFilename and create the graph
    //     - After loadNetworkFile, you should call sortAllLists
    //     - this.adjacencyList and this.reverseAdjacencyList should have an entry for every node in the graph
    //       Nodes with zero out-degree should map to an empty LinkedList (not null!) 
    public SocialNetwork(String networkFilename, double d, double theta) { //DONE
    }

    // Accessors

    // getSortedNodes
    // Preconditions:
    //    - None
    // Postconditions:
    //    - Returns this.sortedNodes
    public LinkedList<String> getSortedNodes() { //DONE
    }

    // outDegreeOfNode
    // Preconditions:
    //    - None
    // Postconditions:
    //    - If name is the name of a node in the graph, it returns name's out-degree
    //    - If name is NOT the name of a node in the graph, it returns -1
    // Notes:
    //    - You may wish to implement a private helper method used by outDegreeOfNode and inDegreeOfNode
    public int outDegreeOfNode(String name) { //DONE
    }

    // inDegreeOfNode
    // Preconditions:
    //    - None
    // Postconditions:
    //    - If name is the name of a node in the graph, it returns name's in-degree
    //    - If name is NOT the name of a node in the graph, it returns -1
    // Notes:
    //    - You may wish to implement a private helper method used by outDegreeOfNode and inDegreeOfNode
    public int inDegreeOfNode(String name) { //DONE
    }

    // computeAllInOutDegrees
    // Preconditions:
    //    - None
    // Postconditions:
    //    - A new DegreeResult object is returned where
    //            the minOutDegree field contains the minimum out degree over all nodes
    //            the argminOutDegree field contains the name of the node with minimum out degree 
    //            the maxOutDegree field contains the maximum out degree over all nodes
    //            the argmaxOutDegree field contains the name of the node with maximum out degree 
    //            the minInDegree field contains the minimum in degree over all nodes
    //            the argminInDegree field contains the name of the node with minimum in degree 
    //            the maxInDegree field contains the maximum in degree over all nodes
    //            the argmaxInDegree field contains the name of the node with maximum in degree 
    //            the avgOutDegree field contains the average out degree over all nodes
    // Notes:
    //    - In any case of a tie, the alphabetically first node should be used
    public DegreeResult computeAllInOutDegrees() { //DONE
    }

    // checkClique 
    // Preconditions:
    //    - names is a linked list of node names
    // Postconditions:
    //    - Returns true if the nodes in the names list form a clique, else returns false
    /*public boolean checkClique( LinkedList<String> names ) { // DO LATER?
        for(int i = 0; i < names.size(); i++){
            String currName = names.remove();
            LinkedList<String> friends = adjacencyList.get(currName);
            
            names.add(currName);
        }
        return true;
    }*/

    // degreesOfSeparation
    // Preconditions:
    //    - None
    // Postconditions:
    //    - If there is no node in the graph with the name srcName, returns null
    //    - For every node x reachable from srcName, the returns HashMap maps x to the
    //      length of the shortest path from srcName to x
    //      (We can assume each edge has weight 1; i.e. we want shortest in terms of the number of edges)
    public HashMap<String,Integer> degreesOfSeparation( String srcName ) { // DONE
    }
    
    // pageRank 
    // Preconditions:
    //    - (1-this.d) is the "probability of getting bored"
    //    - this.theta is the threshold for determining when page rank has converged 
    // Postconditions:
    //    - A HashMap is return mapping nodes to their page rank value
    // Notes:
    //    - The page rank algorithm should run until the following convergence criterion is met:
    //      For EVERY node x, abs(newPageRank(x)-oldPageRank(x))/oldPageRank(x) <= theta
    //    - See assignment description for PageRank algorithm details
    public HashMap<String,Double> pageRank() {
    }
    
    // adjacencyListToString
    // Preconditions:
    //    - None
    // Postconditions:
    //    - A string representation of the adjacency list will be created in sorted order:
    //      the nodes should be sorted, and their adjacency sets should also be sorted. 
    //      Each line should look like
    //      NodeName: FirstNodeInAdjSet SecondNodeInAdjSet ... LastNodeInAdjSet
    // Notes:
    //    - This should just call listToString
    public String adjacencyListToString() { //Done
    }

    // reverseAdjacencyListToString
    // Preconditions:
    //    - None
    // Postconditions:
    //    - A string representation of the reverse adjacency list will be created in sorted order:
    //      the nodes should be sorted, and their reverse adjacency sets should also be sorted. 
    //      Each line should look like
    //      NodeName: FirstNodeInRevAdjSet SecondNodeInRevAdjSet ... LastNodeInRevAdjSet
    // Notes:
    //    - This should just call listToString
    public String reverseAdjacencyListToString() { //Done
    }
    
    // listToString
    // Preconditions:
    //    - map is a HashMap from Strings to LinkedLists of Strings
    // Postconditions:
    //    - A string representation of the map will be created in sorted order:
    //      the keys of the map should be sorted, and Strings in each LinkedList should also be sorted. 
    //      For example, if map denotes an adjacency set representation, each line should look like
    //      NodeName: FirstNodeInAdjSet SecondNodeInAdjSet ... LastNodeInAdjSet
    // Notes:
    //    - Use a StringBuilder rather than repeated concatenation for efficiency
    private String listToString( HashMap<String,LinkedList<String>> map ) { // DONE
    }

    // Modifiers

    // loadNetworkFile 
    // Preconditions:
    //    - networkFilename is the name of a file encoding a social network
    // Postconditions:
    //    - this.adjacencyList an adjacency list representation of the social network graph 
    //       - specifically, it is a HashMap mapping each node name to its linked list adjacency set
    //    - this.reverseAdjacencyList a reverse adjacency list representation of the social network graph 
    //       - specifically, a HashMap mapping each node name to its linked list of nodes it is adjacent to
    // Notes:
    //    - If you encounter a FileNotFoundException, print to standard error the message:
    //      "Error: Not able to open file " + networkFilename
    //      and exit with status 1 (i.e. System.exit(1))
    //    - You should use the helper function addToList to avoid inadvertent duplicates
    private void loadNetworkFile( String networkFilename ) { // DONE
    }

    // sortAllLists
    // Preconditions:
    //    - The adjacencyList and reverseAdjacencyList have been created
    // Postconditions:
    //    - All lists in the adjacencyList and reverseAdjacencyList are sorted alphabetically
    //    - this.sortedNodes is a linked list containing all of the nodes, sorted alphabetically ascending
    // Notes:
    //    - You can use Java's built-in sorting methods to do the actual sorting
    private void sortAllLists() { // DONE
    }
    
    // addToAdjList
    // Preconditions:
    //    - The adjList has been created
    // Postconditions:
    //    - The edge from key to value has been added to the adjList
    private void addToAdjList(HashMap<String, LinkedList<String>> adjList, String key, String value){ // DONE
    }
    // Static

    // addToList
    // Preconditions:
    //    - nodeName contains a new node to add to list
    // Postconditions:
    //    - If list already contains nodeName, no change is made
    //    - If list does not contain nodeName, nodeName is added to the end of list
    private static void addToList(String nodeName,LinkedList<String> list) { //DONE
    }
}
