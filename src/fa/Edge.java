package fa;

public class Edge {

	private State start, end;
	private char letter;
	
	public Edge(char l){
		letter = l;
	}
	
	public Edge(char l, State s){
		start = s;
		end = new State();
		letter = l;
	}	
	
	public char getLetter(){
		return letter;
	}
	
	public State getNext(){
		return end;
	}
}
