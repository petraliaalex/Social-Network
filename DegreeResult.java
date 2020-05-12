/* 
 * DegreeResult.java
 *
 * Defines a simple DegreeResult type that allows several related values to
 * be passed around as a single object
 *
 * The public fields are:
 *   minInDegree     The minimum in-degree (over all nodes)
 *   argminInDegree  The node with the minimum in-degree (break ties alphabetically)
 *   maxInDegree     The maximum in-degree (over all nodes)   
 *   argmaxInDegree  The node with the maximum in-degree (break ties alphabetically)   
 *   minOutDegree    The minimum out-degree (over all nodes)   
 *   argminOutDegree The node with the minimum out-degree (break ties alphabetically)    
 *   maxOutDegree    The maximum out-degree (over all nodes)      
 *   argmaxOutDegree The node with the maximum out-degree (break ties alphabetically)        
 *   avgOutDegree    The average out degree over all nodes
 * 
 * Do not modify this file.  
 *
 * Brian Hutchinson
 * October 2014
 *
 */
public class DegreeResult {
	public int minInDegree, maxInDegree, minOutDegree, maxOutDegree;
	public String argminInDegree, argmaxInDegree, argminOutDegree, argmaxOutDegree;
	public double avgOutDegree;
}
