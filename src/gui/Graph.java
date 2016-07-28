package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import fa.State;

import fa.Tree;

@SuppressWarnings("serial")
public class Graph extends JPanel{

	
	private static final int BLOCK_WIDTH = 15, BLOCK_HEIGHT = 15;
	
	private Tree tree;
	private TreeInfo ti;
	private int width, height;
	private char[][] table;
	private int[][] edgePoints;
	private int[][] edges;
	
	public Graph(Tree t){
		this.tree = t;
		ti = tree.getTreeInfo();
		table = ti.getTable();
		edges =  tree.getEdges();
		width = table[0].length;
		height = table.length;
		
		setEdgePoints();
		
		JFrame frame = new JFrame("Three Graph");
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Dimension dim = new Dimension(width*BLOCK_WIDTH, (height+1)*BLOCK_HEIGHT);
		setPreferredSize(dim);
		setMaximumSize(dim);
		setMinimumSize(dim);
		
		frame.setResizable(false);
		frame.add(this);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.GRAY);
		int font_size = g.getFont().getSize();
		for(int i=0; i<edges.length; i++){
			int edge1 = edges[i][0];
			int edge2 = edges[i][1];
			int x1 = edgePoints[edge1][0]*BLOCK_WIDTH	+   font_size/3;
			int y1 = (edgePoints[edge1][1]+1)*BLOCK_HEIGHT   -   font_size/2;
			int x2 = edgePoints[edge2][0]*BLOCK_WIDTH	+   font_size/3;
			int y2 = (edgePoints[edge2][1]+1)*BLOCK_HEIGHT  -   font_size/2;
			g.drawLine(x1, y1, x2, y2);
		}
		g.setColor(Color.BLUE);
		for(int i=0; i<width; i++){
			for(int j=0; j<height; j++){
				
				int x = i*BLOCK_WIDTH;
				int y = (j+1)*BLOCK_HEIGHT;
				if(table[j][i]!=TreeInfo.UND){
					g.drawString(table[j][i]+"",  x, y);
				}
			}
		}
	}
	
	private void setEdgePoints(){
		edgePoints = new int[State.NUMBER_OF_VERTEXS][2];
		int[][] idtable = ti.getIdTable();
		for(int i=0; i<idtable.length; i++){
			for(int j=0; j<idtable[i].length; j++){
				int k = idtable[i][j];
				if(k!=TreeInfo.UND_ID){
					edgePoints[k][0] = j;
					edgePoints[k][1] = i;
				}
			}
		}
	}

}
