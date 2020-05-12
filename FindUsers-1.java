/*

Alex Petralia
CSCI 241
7/13/2017
Assignment 2
Purpose of Program:
--Creates an implimentation of Dijkstra's Algorithm using Linked Lists and Nodes
--uses a text file for input and prompts user for which person to use as the "source"
and which person to use as the "destination", then the progam will use the algorithm
to figure out the shortest path (total weight) with the given "weights" from person to person
in the text file 








*/






import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;

import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.LinkedList;


class FindUsers {
   
   
   //Linked List
   public String curName;
   public int weight;
   public String nextName;
   public FindUsers next;
   
   static String nodeCountHelper = " ";
   static int nodeCount = 1; //number of nodes in the whole graph, set to one to count for the first node in graph
   static String curNamePointer;
   static String nextNamePointer;
   static int weightPointer;
   static int lineCount = 0; //counter for iterating through each line (1 to 3)
   
   
   //static values for making the graph
   static int[][] theGraph;
   static int rowIteration = 0;
   static int columnIteration = 0; 
   static int sizeIteration = 0;
   static int edgeCount = 0;
   
   
   
   
   
   
   public FindUsers(String curName, String nextName, int weight) {
      this.curName = curName;
      this.nextName = nextName;
      this.weight = weight;
   }
	public void display(){
		System.out.println(curName + " " + nextName + " " + weight);
	}
	public String toString(){
		return curName;
	}
   
   public static void addToMatrix(int nodeCount){
      sizeIteration++;
      theGraph = new int[nodeCount][nodeCount];
   }
   
   //Main Method execution
   public static void main(String[] args) throws Exception, IOException{
      
      
      /*
      //FileReader Logic
      FileReader file = new FileReader("//fs1/home/petrala/Desktop/CSCI241/Assign2/people.txt");
      BufferedReader reader = new BufferedReader(file);
   
      String text = "";
      String line = reader.readLine();
      while (line != null) {
         text += line;
         text += " ";
         line = reader.readLine();
      }
      */
      //System.out.println(text);
      
      
      
      //creates LinkedList
      LinkList theLinkedList = new LinkList();
      
      BufferedReader br = new BufferedReader(new FileReader("people.txt"));
       try {
           StringBuilder sb = new StringBuilder();
           String lineSB = br.readLine();
           //Reads the file WORD by WORD, not Line by Line, 
           while (lineSB != null) {
               sb.append(lineSB);
               sb.append("\n");
               //lineSB = br.readLine();
               
               //Node Counter logic, checks if different name is used then nodeCount++ is applied
               if (lineSB != null){
                  String[] arr = lineSB.split(" ");
                  for(String ss : arr){
                     lineCount++;
                     if (nodeCountHelper == " ") {
                        nodeCountHelper = ss;
                     //Logic for Counting Total number of nodes in the graph 
                     //and checks if new node is being pointed to (ex: Fred node >>to>> Susan Node)
                     }else if (nodeCountHelper.equals(ss) == false && lineCount == 1) {
                        //columnIteration++;
                        nodeCount++;
                        //addToMatrix();
                        nodeCountHelper = ss;
                     }
                     //IMPORTANT: curWord => points to current word, iterating through the whole text file
                     String curWord = ss;
                     //adds curWord's to Linked List and moves pointers for each line in the text file
                     if (lineCount == 1){
                        curNamePointer = curWord;
                     }else if (lineCount == 2) { 
                        //rowIteration++;
                        nextNamePointer = curWord;
                     //Parsing to the weight in the current line
                     }else if (lineCount == 3) {
                        edgeCount++;
                        int intConvert = Integer.parseInt(curWord);
                        weightPointer = intConvert;
                        //theGraph[rowIteration][columnIteration] = weightPointer;
                        //PRINT STATEMENT
                        System.out.println(curNamePointer + " " + nextNamePointer + " " + weightPointer);
                        //ADDS NODE TO LINKEDLIST
                        theLinkedList.insertFirstLink(curNamePointer, nextNamePointer, weightPointer);
                        curNamePointer = " ";
                        nextNamePointer = " ";
                        weightPointer = 0;
                        lineCount = 0;
                        lineSB = br.readLine();
                     }
                     //System.out.println(ss);
                  }
               }
           }
      } finally {
           br.close();
      }
   
      /*
      for(Object o : FindUsers){
         System.out.println(o);
      }
      */
      addToMatrix(nodeCount);
      System.out.println(" ");
      System.out.println("TOTAL NODE COUNT: " + nodeCount);
      System.out.println(" ");
      //System.out.println("Graph: " + theGraph);
      
      theLinkedList.display();
      
      ArrayList<FindUsers> graph = new ArrayList<FindUsers>(edgeCount);
      LinkList.createGraph(graph, edgeCount);
      String source = "Fred";
      LinkList.dijkstra(graph, source, edgeCount);
   }
   
}




class LinkList {

   public static FindUsers firstLink;

   //creates the Graph to be inputed in dijkstra
   public static void createGraph(ArrayList<FindUsers> graph, int edgeCount){
      FindUsers helperFirstLink = firstLink;
      graph.add(helperFirstLink);
      for(int i = 1; i < edgeCount; i++){
         graph.add(helperFirstLink.next);
         helperFirstLink = helperFirstLink.next;
      }
   }
   
   //finds the minimum weight for a node in the Queue
   public static FindUsers findSmallestWeight(FindUsers curNode, int edgeCount){
      String targetName = curNode.curName;
      FindUsers helperLinker = firstLink;
      FindUsers finalNode = null;
      int curSmallestWeight = 0;
      for(int i = 0; i < edgeCount; i++){
         if(((helperLinker.curName).equals(curNode.curName)) && (curSmallestWeight < helperLinker.weight)){
            curSmallestWeight = helperLinker.weight;
            finalNode = helperLinker;
         }else{
            helperLinker = helperLinker.next;
         }
      }
      return finalNode;
   }
   //finds out the total number of neighbors with an inputed node
   public static int numOfNeighbors(FindUsers curNode, int edgeCount){
      String targetName = curNode.curName;
      FindUsers helperLinker = firstLink;
      int neighborCount = 0;
      for(int i = 0; i < edgeCount; i++){
         if((helperLinker.curName).equals(targetName)){
            neighborCount++;
            helperLinker = helperLinker.next;
         }else{
            helperLinker = helperLinker.next;
         }
      }
      return neighborCount;
   }
   
   //returns a node, from a given index of the list of neighbors of the inputed node
   //takes an index from the numOfNeighbors used on the node(2 neighbors = indices:{0, 1})
   public static FindUsers pickNeighbor(FindUsers curNode, int index, int edgeCount){
      String targetName = curNode.curName;
      FindUsers helperLinker = firstLink;
      int indexTracker = 0;
      for(int i = 0; i < edgeCount; i++){
         if(((helperLinker.curName).equals(targetName)) && (index == indexTracker)){
            return helperLinker;
         }else if((helperLinker.curName).equals(targetName)){
            helperLinker = helperLinker.next;
            indexTracker++;
         }
      }
      //may change this if it causes problems
      //don't know if this ret statement would ever get reached
      return null;
   }
   
   
   
   
   
   
   //method for finding/removing targetNode and then restores back the Q and returns it
   public static Queue<FindUsers> deleteTargetNodeInQ (FindUsers targetNode, Queue<FindUsers> q, int edgeCount, int qLength) {
      ArrayList<FindUsers> tempGraph = new ArrayList<FindUsers>(edgeCount);
      int tempGraphLength = 0;
      for(int i = 0; i < qLength; i++){
         if (targetNode == q.peek()){
            q.remove();
         //logic for restoring the Q
         }else{
            tempGraph.add(q.peek());
            tempGraphLength++;
            q.remove();
         }
      }
      for(int j = 0; j < tempGraphLength; j++){
         if (tempGraph.isEmpty()){
            return q;
         }else{
            q.add(tempGraph.get(0));
         }
      }
      return q;
   }
   
   
   /*
   public static int weightfromSource(FindUsers source, FindUsers curNode){
   
   
   
   } 
   
   
   // logic for the relax() method in dijkstra
   public static void relax(FindUsers curNode, FindUsers neighborNode, int neighborAndCurWeight){
      if v.
   
   
   
   
   
   }
   */
   
   
   
   
   
   
   
   
   
   public static void dijkstra (ArrayList<FindUsers> graph, String source, int edgeCount) {
      //for each vertex v in graph
      int distanceV;
      int distanceS;
      FindUsers previousV;
      for (int i = 0; i <= edgeCount; i++){
         //dist[v] = infinity
         distanceV = 999999;
         //previous[v] = undefined
         previousV = null;
      }
      //dist[source] = 0
      distanceS = 0;

      
      
      //Logic for filling the Queue (q)
      Queue<FindUsers> q = new LinkedList<FindUsers>();
      FindUsers helperLink = firstLink;
      FindUsers helperLink2 = firstLink;
      int qLength = 0;
      boolean checker = false;
      String curNameInLoop = " ";
      //logic for finding the source node
      while(checker == false){
         if((helperLink.curName).equals(source)){
            q.add(helperLink);
            qLength++;
            //keeps track of the head of Q w/ the curName
            curNameInLoop = helperLink.curName;
            //iterates helperLink forward
            helperLink = helperLink.next;
            checker = true;
         }else{
            helperLink = helperLink.next;
         }
      }
      for(int i = 0; i < edgeCount; i++){
         //checks if a new vertex appears and adds to Q if so
         if(curNameInLoop.equals(helperLink2.curName)){
            helperLink2 = helperLink2.next;
         }else{
            curNameInLoop = helperLink2.curName;
            q.add(helperLink2);
            qLength++;
         }
      }
      
      
      
      
      //While loop logic
      while (q.isEmpty() == false) {
         //finds "u": smallest dist. node
         FindUsers u = findSmallestWeight(q.peek(), edgeCount);
         //removes "u" from the Q
         q = deleteTargetNodeInQ(u, q, edgeCount, qLength);
      }
      /*
      for(int i = 0; i < numOfNeighbors(u, edgeCount); i++){
         int alt = 
      
      }
      */
      
      
   }
   
   
   
   
   
   
   
   
   
   
   LinkList() {
      firstLink = null;   
   }
   
   public boolean isEmpty() {
      return (firstLink == null);
   }
   
   //Insert Links into theLinkedList
   public void insertFirstLink(String curName, String nextName, int weight){
      FindUsers newLink = new FindUsers(curName, nextName, weight);
      newLink.next = firstLink;
      
      /*
      if (firstLink.next == null){
         head = firstLink;
      }
      */
      
      firstLink = newLink;
   }
   /*
   public FindUsers removeFirst() {
   
      FindUsers linkReference = firstLink;
      
      if(!isEmpty()){
         // Removes the Link from the List
         firstLink = firstLink.next; 
      }else{
         System.out.println("Empty LinkedList");
      } 
      return linkReference;
   }
   */
   public void display(){
		
		FindUsers theLink = firstLink;
		
		// Start at the reference stored in firstLink and
		// keep getting the references stored in next for
		// every Link until next returns null
		
		while(theLink != null){
			
			theLink.display();
			
			System.out.println("Next Link: " + theLink.next);
			
			theLink = theLink.next;
			
			System.out.println();
			
		}
		
	}
	/*
	public FindUsers find(String curName){
		
		FindUsers theLink = firstLink;
		
		if(!isEmpty()){
		
			while(theLink.curName != curName){
			
				// Checks if at the end of the LinkedList
			
				if(theLink.next == null){
				
					// Got to the end of the Links in LinkedList
					// without finding a match
				
					return null;
				
				} else {
				
					// Found a matching Link in the LinkedList
				
					theLink = theLink.next;
				
				}
			
			}
			
		} else {
			
			System.out.println("Empty LinkedList");
			
		}
		
		return theLink;
		
	}
	
	public FindUsers removeLink(String curName){
		
		FindUsers currentLink = firstLink;
		FindUsers previousLink = firstLink;
		
		// Keep searching as long as a match isn't made
		
		while(currentLink.curName != curName){
			
			// Check if at the last Link in the LinkedList
			
			if(currentLink.next == null){
				
				// bookName not found so leave the method
				
				return null; 
				
			} else {
				
				// We checked here so let's look in the
				// next Link on the list
				
				previousLink = currentLink; 
				
				currentLink = currentLink.next;
				
			}
			
		}
		
		if(currentLink == firstLink){
			
			// If you are here that means there was a match
			// in the reference stored in firstLink in the
			// LinkedList so just assign next to firstLink
			
			firstLink = firstLink.next;
			
		} else {
			
			// If you are here there was a match in a Link other 
			// than the firstLink. Assign the value of next for
			// the Link you want to delete to the Link that's 
			// next previously pointed to the reference to remove
			
			System.out.println("FOUND A MATCH");
			System.out.println("currentLink: " + currentLink);
			System.out.println("firstLink: " + firstLink);
			
			previousLink.next = currentLink.next;
			
		}
		
		return currentLink;
		
	}
	

   
   
}

//The Node Class
class Node {

   private String data;
   private Node next;
   
   public Node(String data, Node next) {
      this.data = data;
      this.next = next;
   }
   public String getData() {
      return data;
   }
   public Node getNext(){
      return next;
   }
   public void setData(String d){
      data = d;
   }
   public void setNext(Node n){
      next = n;
   }
   
*/



}



   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
      
      
      
      
