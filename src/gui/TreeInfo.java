package gui;


public class TreeInfo{

	public static final char UND = '.';
	public static final int UND_ID = -1;
	
	private int width = 0, height = 0;
	private char[][] table;
	private int[][] id_table;
	
	public TreeInfo(char c, int id){
		width = 3;
		height = 1;
		initTable();
		table[0][1] = c;
		id_table[0][1] = id;
	}
		
	public TreeInfo(TreeInfo ct, int h){
		if(height>h){
			System.err.println("CharTable: Wrong height");
			System.exit(1);
		}
		width = ct.width;
		height = h;
		initTable();
		for(int i=0; i<ct.width; i++){
			for(int j=0; j<ct.height; j++){
				table[j][i] = ct.table[j][i];
				id_table[j][i] = ct.id_table[j][i];
			}
		}
		//table[0][0] = '|';
		//table[0][table[0].length-1] = '|';
	}
	
	public TreeInfo(TreeInfo ct1, TreeInfo ct2){
		int maxHeight = ct1.height>ct2.height?ct1.height:ct2.height;
		TreeInfo c1 = new TreeInfo(ct1, maxHeight);
		TreeInfo c2 = new TreeInfo(ct2, maxHeight);
		width = c1.width+c2.width;
		height = maxHeight;
		initTable();
		for(int i=0; i<width; i++){
			for(int j=0; j<height; j++){
				if(i<c1.width){
					table[j][i] = c1.table[j][i];
					id_table[j][i] = c1.id_table[j][i];
				}
				else{
					table[j][i] = c2.table[j][i-c1.width];
					id_table[j][i] = c2.id_table[j][i-c1.width];
				}
			}
		}
	}
	
	public TreeInfo(char c, int id, TreeInfo ct){
		width = ct.width;
		height = ct.height+1;
		initTable();
		table[0][width/2] = c;
		id_table[0][width/2] = id;
		for(int i=0; i<width; i++){
			for(int j=1; j<height; j++){
				table[j][i] = ct.table[j-1][i];
				id_table[j][i] = ct.id_table[j-1][i];
			}
		}
	}

	public char[][] getTable(){
		return table;
	}
	
	public int[][] getIdTable(){
		return id_table;
	}
	
	private void initTable(){
		table = new char[height][width];
		id_table = new int[height][width];
		for(int i=0; i<width; i++){
			for(int j=0; j<height; j++){
				table[j][i] = UND;
				id_table[j][i] = UND_ID;
			}
		}
		
	}
	
	public void print(){
		for(int i=0; i<height; i++){
			System.out.println(String.copyValueOf(table[i]));
		}
	}
	
	public void printIdTable(){
		for(int i=0; i<id_table.length; i++){
			for(int j=0; j<id_table[i].length; j++){
				if(id_table[i][j]==-1){
					System.out.print("\t");
				}
				else{
					System.out.print("\t"+id_table[i][j]);
				}
			}
			System.out.println("");
		}
	}
}


