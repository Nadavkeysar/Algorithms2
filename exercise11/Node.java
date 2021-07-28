package algorithms_1.exercise11;
/**
 * class Node:
 * x,y - the price for get down (y) or left (x)
 * price - the best price from (0,0) to this node
 * price2 - the second best price from (0,0) to this node
 * priceFromTheEnd - the best price from (m,n) to this node
 * numOfPaths - number of shortest paths until this node 
 */
public class Node{
	int x, y, price, numOfPaths, priceFromTheEnd, price2;
	public Node(int x, int y){
		this.x = x;
		this.y = y;
		this.price = 0;	
		this.numOfPaths = 0;
		this.priceFromTheEnd = 0;
		this.price2 = 0;
	}
	public String toString(){
		return "("+x+","+y+") ";
        //return "x="+x+", y="+y+", price="+price+", numPath="+numOfPaths+"; ";
	}
}// class Node

