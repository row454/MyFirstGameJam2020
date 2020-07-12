package com.row666.world;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
import java.util.List;

public class Floor {
	private int currentFloor;
	private ArrayList<ArrayList<Room>> currentFloorLayout;
	private int floorSize, maxLength, minLength, maxRooms;
	private Tuple startingPosition, endPosition;
	private boolean[][] toBeGenerated;
	private int counter = 0;
	
	public Floor(int currentFloor) {
		this.currentFloor = currentFloor;
		floorSize = currentFloor*2+5;
		maxLength = (currentFloor+1)*6;
		minLength = (currentFloor+1)*3;
		maxRooms = maxLength*2;
		toBeGenerated = new boolean[floorSize][floorSize];
		for (int i = 0; i < floorSize; i++) {
			for (int j = 0; j < floorSize; j++) {
				toBeGenerated[i][j] = false;
			}
		}
		
		
		generateFloor();
		
	}

	private void generateFloor() {
		startingPosition = new Tuple(randomBetween(0, floorSize-1), randomBetween(0, floorSize-1));
		if (randomBetween(0, 1) == 0)
			// 1+1, 3+1 2+1 3+1  
			endPosition = new Tuple(randomBetween(Math.floorDiv(minLength, 2)+startingPosition.getRow(), Math.floorDiv(maxLength, 2)+startingPosition.getRow()), randomBetween(-Math.floorDiv(-minLength, 2)+startingPosition.getCol(), -Math.floorDiv(-maxLength, 2)+startingPosition.getCol()));
		else
			endPosition = new Tuple(randomBetween(startingPosition.getRow()-Math.floorDiv(maxLength, 2), startingPosition.getRow()-Math.floorDiv(minLength, 2)), randomBetween(Math.floorDiv(-maxLength, 2)+startingPosition.getCol(), startingPosition.getCol()+Math.floorDiv(-minLength, 2)));
		System.out.println(startingPosition.getRow());
		System.out.println(startingPosition.getCol());
		if (endPosition.getCol() > floorSize-1 || endPosition.getRow() > floorSize-1 || endPosition.getCol() < 0 || endPosition.getRow() < 0 || startingPosition.getCol() > floorSize-1 || startingPosition.getRow() > floorSize-1 || startingPosition.getCol() < 0 || startingPosition.getRow() < 0) {
			System.out.println("nevermind");
			generateFloor();
		}
		toBeGenerated[startingPosition.getCol()][startingPosition.getRow()] = true;
		toBeGenerated[endPosition.getCol()][endPosition.getRow()] = true;
		nextStep(startingPosition, false, null);
		
	}
	private void nextStep(Tuple pos, boolean inABranch, Tuple startOfBranch) {
		if (countAdjacentRooms(pos) == -1)
			return;
		if (counter >= maxRooms)
			return;
		int dir = randomBetween(0, 3);
		Tuple oldPos;
		Tuple newPos;
		switch(dir) {
		case 0:
			newPos = pos;
			newPos.move(0, 1);
			if(newPos == endPosition)
				return;
			if(newPos.getCol() < 5 && newPos.getCol() >= 0 && newPos.getRow() < 5 && newPos.getRow() >= 0) {
				if(!isEmpty(newPos) && (countAdjacentRooms(newPos) == 1 || isCrossroads(newPos) || countAdjacentRooms(newPos) == -1)) {
					oldPos = pos;
					pos = newPos;
					toBeGenerated[pos.getCol()][pos.getRow()] = true;
					if(Math.abs(endPosition.getRow() - oldPos.getRow()) < Math.abs(endPosition.getRow()- pos.getRow()) || Math.abs(endPosition.getRow() - oldPos.getCol()) < Math.abs(endPosition.getCol()- pos.getCol())) {
						if (randomBetween(0, 1) == 0) {
							if (inABranch)
								pos = startOfBranch;
							else pos = oldPos;
						} else if (!inABranch) {
							startOfBranch = oldPos;
							inABranch = true;
						}
					}
				}
			}
			break;
		case 1:
			newPos = pos;
			newPos.move(0, 1);
			if(newPos == endPosition)
				return;
			if(newPos.getCol() < 5 && newPos.getCol() >= 0 && newPos.getRow() < 5 && newPos.getRow() >= 0) {
				if(!isEmpty(newPos) && (countAdjacentRooms(newPos) == 1 || isCrossroads(newPos) || countAdjacentRooms(newPos) == -1)) {
					oldPos = pos;
					pos = newPos;
					toBeGenerated[pos.getCol()][pos.getRow()] = true;
					if(Math.abs(endPosition.getRow() - oldPos.getRow()) < Math.abs(endPosition.getRow()- pos.getRow()) || Math.abs(endPosition.getRow() - oldPos.getCol()) < Math.abs(endPosition.getCol()- pos.getCol())) {
						if (randomBetween(0, 1) == 0) {
							if (inABranch)
								pos = startOfBranch;
							else pos = oldPos;
						} else if (!inABranch) {
							startOfBranch = oldPos;
							inABranch = true;
						}
					}
				}
			}
			break;
		case 2:
			newPos = pos;
			newPos.move(1, 0);
			if(newPos == endPosition)
				return;
			if(newPos.getCol() < 5 && newPos.getCol() >= 0 && newPos.getRow() < 5 && newPos.getRow() >= 0) {
				if(!isEmpty(newPos) && (countAdjacentRooms(newPos) == 1 || isCrossroads(newPos) || countAdjacentRooms(newPos) == -1)) {
					oldPos = pos;
					pos = newPos;
					toBeGenerated[pos.getCol()][pos.getRow()] = true;
					if(Math.abs(endPosition.getRow() - oldPos.getRow()) < Math.abs(endPosition.getRow()- pos.getRow()) || Math.abs(endPosition.getRow() - oldPos.getCol()) < Math.abs(endPosition.getCol()- pos.getCol())) {
						if (randomBetween(0, 1) == 0) {
							if (inABranch)
								pos = startOfBranch;
							else pos = oldPos;
						} else if (!inABranch) {
							startOfBranch = oldPos;
							inABranch = true;
						}
					}
				}
			}
			break;
		case 3:
			newPos = pos;
			newPos.move(0, -1);
			if(newPos == endPosition)
				return;
			if(newPos.getCol() < 5 && newPos.getCol() >= 0 && newPos.getRow() < 5 && newPos.getRow() >= 0) {
				if(!isEmpty(newPos) && (countAdjacentRooms(newPos) == 1 || isCrossroads(newPos) || countAdjacentRooms(newPos) == -1)) {
					oldPos = pos;
					pos = newPos;
					toBeGenerated[pos.getCol()][pos.getRow()] = true;
					if(Math.abs(endPosition.getRow() - oldPos.getRow()) < Math.abs(endPosition.getRow()- pos.getRow()) || Math.abs(endPosition.getRow() - oldPos.getCol()) < Math.abs(endPosition.getCol()- pos.getCol())) {
						if (randomBetween(0, 1) == 0) {
							if (inABranch)
								pos = startOfBranch;
							else pos = oldPos;
						} else if (!inABranch) {
							startOfBranch = oldPos;
							inABranch = true;
						}
					}
				}
			} else 
			break;
			
		}
		counter++;
		nextStep(pos, inABranch, startOfBranch);
		return;
			
	}
	
	private boolean isCrossroads(Tuple pos) {
		if (countAdjacentRooms(pos) <= 1) 
			return false;
		Tuple newPos;
		boolean[] adjacentRooms = {false, false, false, false, false, false, false, false};
		for(int i = 0; i < 8; i++) {
			switch (i) {
			case 0:
				newPos = pos;
				newPos.move(-1, -1);
				adjacentRooms[i] = !isEmpty(newPos);
				break;
			case 1:
				newPos = pos;
				newPos.move(-1, 0);
				adjacentRooms[i] = !isEmpty(newPos);
				break;
			case 2:
				newPos = pos;
				newPos.move(-1, 1);
				adjacentRooms[i] = !isEmpty(newPos);
				break;
			case 3:
				newPos = pos;
				newPos.move(0, -1);
				adjacentRooms[i] = !isEmpty(newPos);
				break;
			case 4:
				newPos = pos;
				newPos.move(0, 1);
				adjacentRooms[i] = !isEmpty(newPos);
				break;
			case 5:
				newPos = pos;
				newPos.move(1, -1);
				adjacentRooms[i] = !isEmpty(newPos);
				break;
			case 6:
				newPos = pos;
				newPos.move(1, 0);
				adjacentRooms[i] = !isEmpty(newPos);
				break;
			case 7:
				newPos = pos;
				newPos.move(1, 1);
				adjacentRooms[i] = !isEmpty(newPos);
				break;
			}
		}
		boolean[] adjacentRoomsOneToFour = Arrays.copyOfRange(adjacentRooms, 0, 3);
		boolean[] adjacentRoomsOneToThree = Arrays.copyOfRange(adjacentRooms, 0, 2);
		boolean[] adjacentRoomsSixToEight = Arrays.copyOfRange(adjacentRooms, 5, 7);
		boolean[] adjacentRoomsTwoToFive = Arrays.copyOfRange(adjacentRooms, 1, 4);
		boolean[] adjacentRoomsFiveToEight = Arrays.copyOfRange(adjacentRooms, 4, 7);
		boolean[] adjacentRoomsFourToSeven = Arrays.copyOfRange(adjacentRooms, 3, 6);
		boolean[] adjacentRoomsOneToSix = Arrays.copyOfRange(adjacentRooms, 0, 5);
		boolean[] adjacentRoomsThreeToEight = Arrays.copyOfRange(adjacentRooms, 3, 7);
		boolean[] notCrossroadsOne = {true, true, false, true};
		boolean[] notCrossroadsTwo = {true, false, true, true};
		boolean[] notCrossroadsThree = {true, true, true};
		boolean[] notCrossroadsFour = {true, false, false, true, false, true};
		boolean[] notCrossroadsFive = {true, false, true, false, false, true};
		if (adjacentRoomsOneToFour == notCrossroadsOne || adjacentRoomsTwoToFive == notCrossroadsOne || adjacentRoomsFiveToEight == notCrossroadsTwo || adjacentRoomsFourToSeven == notCrossroadsTwo || adjacentRoomsOneToThree == notCrossroadsThree || adjacentRoomsSixToEight == notCrossroadsThree || adjacentRoomsOneToSix == notCrossroadsFour || adjacentRoomsThreeToEight == notCrossroadsFive)
			return false;
		else return true;
		
	}
	private int countAdjacentRooms(Tuple pos) {
		Tuple newPos;
		int adjacentRooms = 0;
		for(int i = 0; i < 4; i++) {
			switch (i) {
			case 0:
				newPos = pos;
				newPos.move(-1, 0);
				if (newPos == endPosition)
					return -1;
				adjacentRooms += boolToInt(!isEmpty(newPos));
				break;
			case 1:
				newPos = pos;
				newPos.move(0, 1);
				if (newPos == endPosition)
					return -1;
				adjacentRooms += boolToInt(!isEmpty(newPos));
				break;
			case 2:
				newPos = pos;
				newPos.move(1, 0);
				if (newPos == endPosition)
					return -1;
				adjacentRooms += boolToInt(!isEmpty(newPos));
				break;
			case 3:
				newPos = pos;
				newPos.move(0, -1);
				if (newPos == endPosition)
					return -1;
				adjacentRooms += boolToInt(!isEmpty(newPos));
				break;
			}
		}
		return adjacentRooms;
	}
	private boolean isEmpty(Tuple pos) {
		if (pos != null) {
			if(pos.getCol() < 5 && pos.getCol() >= 0 && pos.getRow() < 5 && pos.getRow() >= 0)
				return !toBeGenerated[pos.getCol()][pos.getRow()];
		}
		return true;
	}
	
	private int randomBetween(int min, int max){
        Random r = new Random();
        return r.nextInt((max-min)+1) + min;
    }
	private int boolToInt(boolean bool) {
		if (bool) {
			return 1;
		} else return 0;
	}
	public void render(Graphics g) {
		Tuple pos;
		for (int i = 0; i < floorSize; i++) {
			for (int j = 0; j < floorSize; j++) {
				if (toBeGenerated[i][j])
					g.fillRect(i*80+200, j*50+125, 80, 50);
			}
		}
		g.setColor(Color.green);
		g.fillRect(startingPosition.getCol()*80+200, startingPosition.getRow()*50+125, 80, 50);
		g.setColor(Color.red);
		g.fillRect(endPosition.getCol()*80+200, endPosition.getRow()*50+125, 80, 50);
		
	}

	
}
