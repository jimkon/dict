package fa;

import java.util.ArrayList;

import gui.Graph;

import gui.TreeInfo;

public class Tree {

	public static void main(String[] args) {
		Tree tree = new Tree();
		tree.addWord("abgo");
		tree.addWord("abga");
		tree.addWord("abgothiki");
		tree.addWord("abgothikes");
		tree.addWord("banana");
		tree.addWord("bananoflouda");
		tree.addWord("aa");
		tree.print();
		//tree.graph();
	
		tree.print();
		System.out.println(tree.gotoSubWord("abg"));
		Tree t = new Tree(tree.gotoSubWord("abg"));
		t.print();
	}
	
	private State initial;
	
	public Tree(){
		State.NUMBER_OF_VERTEXS = 0;
		initial = new State();
		
	}
	
	public Tree(State s){
		initial = s;
	}
	
	public void addWord(String s){
		initial.addWord(s);
	}
	
	public void deleteWord(String s){
		initial.deleteWord(s);
	}
	
	public boolean searchWord(String s){
		State state = initial.searchSubWord(s);
		return state!=null && state.isFinal();
	}
	
	public boolean searchSubWord(String s){
		State state = initial.searchSubWord(s);
		return state!=null;
	}
	
	public State gotoSubWord(String s){
		State state = initial.searchSubWord(s);
		return state;
	}
	
	public void print(){
		TreeInfo ct = initial.getCR('^');
		ct.print();
	}
	
	public void printIdTable(){
		TreeInfo ct = initial.getCR('^');
		ct.printIdTable();
	}
	
	public void graph(){
		new Graph(this);
	}
	
	public TreeInfo getTreeInfo(){
		return initial.getCR('^');
	}
	
	public int[][] getEdges(){
		ArrayList<int[]> temp = new ArrayList<int[]>();
		initial.getEdgesList(temp);
		int[][] res = new int[temp.size()][];
		for(int i=0; i<res.length; i++){
			res[i] = temp.get(i);
//			System.out.println(res[i][0]+"->"+res[i][1]);
		}
		return res;
	}

}
