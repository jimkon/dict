package fa;

import java.util.ArrayList;

import gui.TreeInfo;

public class State {
	
	public static int NUMBER_OF_VERTEXS = 0;
	
	private ArrayList<Edge> edges;
	private boolean isFinal;
	private int id;
	
	public State(){
		edges = new ArrayList<Edge>();
		isFinal = false;
		id = NUMBER_OF_VERTEXS++;
	}
	
	public void addWord(String s){
		if(s.length()>0){
			addLetter(s.charAt(0)).addWord(s.substring(1));;
		}
		else{
			isFinal = true;
		}
	}
	
	public State searchSubWord(String s){
		if(s.length()>0){
			State state = getNext(s.charAt(0));
			if(state==null){
				return null;
			}
			return state.searchSubWord(s.substring(1));
		}
		return this;
	}
	
	public boolean deleteWord(String s){
//		System.out.println(this+" del:"+s);
		if(s.length()>0){
			int next = searchLetter(s.charAt(0));
//			System.out.println(this+" next:"+next);
			if(next==-1){
				return false;
			}
			boolean res =  getNext(next).deleteWord(s.substring(1));
//			System.out.println(this+" res:"+res);
			if(res){
				edges.remove(next);
//				System.out.println(this+" size:"+edges.size());
				return edges.size()==0;
			}
			return false;
		}
		else{
			if(isDead()){
//				System.out.println(this+" isdead");
				return true;
			}
			if(isFinal()){
//				System.out.println(this+" isfinal");
				isFinal = false;
			}
//			System.out.println(this+" isnotfinal");
			return false;
		}
	}
	
	/*
	 * check if the letter l exists. if not, it will be added as a new vertex with is edge. else nothing will happen
	 * return next state
	 */
	private State addLetter(char l){
		int temp = searchLetter(l);
		if(temp==-1){
			edges.add(new Edge(l, this));
			temp = edges.size()-1;
		}
		return edges.get(temp).getNext();
	}
	
	public State getNext(char l){
		int temp = searchLetter(l);
		if(temp==-1){
			return null;
		}
		return edges.get(temp).getNext();
	}
	
	public State getNext(int i){
		if(!isDead()){
			return edges.get(i).getNext();
		}
		return null;
	}
	
	private int searchLetter(char l){
		for(int i=0; i<edges.size(); i++){
			if(edges.get(i).getLetter()==l){
				return i;
			}
		}
		return -1;
	}
	
	public boolean isFinal(){
		return isFinal;
	}
	
	public boolean isDead(){
		return edges.size()==0;
	}
	
	public String toString(){
		return "Q"+id+"("+edges.size()+")";
	}
	
	protected TreeInfo getCR(char r){
		if(isDead()){
			return new TreeInfo(isFinal()?Character.toUpperCase(r):r, id);
		}
		TreeInfo ct = edges.get(0).getNext().getCR(edges.get(0).getLetter());
		for(int i=1; i<edges.size(); i++){
			ct = new TreeInfo(ct, edges.get(i).getNext().getCR(edges.get(i).getLetter()));
		}
		return new TreeInfo(isFinal()?Character.toUpperCase(r):r, id, ct);
	}
	
	protected void getEdgesList(ArrayList<int[]> list){
		for(int i=0; i<edges.size(); i++){
			State s = edges.get(i).getNext();
			list.add(new int[]{this.id, s.id});
			s.getEdgesList(list);
		}
	}

}
