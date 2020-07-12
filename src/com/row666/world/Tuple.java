package com.row666.world;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Tuple {
	private Integer[] pos =  new Integer[2];
	private Integer[] posI = (Integer[]) pos;
	public Tuple(int row, int column) {
		pos[0] = row; pos[1] = column;
	}
	public void invert() {
		Integer[] oldPos = pos.clone();
		pos[0] = oldPos[1];
		pos[1] = oldPos[0];
	}
	public void move(int row, int column) {
		pos[0] += row;
		pos[1] += column;
	}
	public int getRow() {
		return pos[0];
	}
	public int getCol() {
		return pos[1];
	}
	public List<Integer> getPos() {
		List<Integer> list = new ArrayList<Integer>();
		Collections.addAll(list, pos);
		return list;
	}
	
	public int hashCode(){
		String s = "000000" + pos[0] + pos[1];
		System.out.println(Integer.parseInt(s));
		return Integer.parseInt(s);
	}

}
