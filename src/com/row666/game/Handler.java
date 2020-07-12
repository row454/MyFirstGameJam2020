package com.row666.game;

import java.awt.image.BufferedImage;

import com.row666.gfx.ImagePreLoader;

public class Handler {
	
	private Game game;
	private ImagePreLoader imagePreLoader;
	
	public Handler(Game game) {
		this.game = game;
	}
	public int getWidth() {
		return game.getWidth();
			
	}
	public BufferedImage getAsset(int index) {
		return imagePreLoader.getAssets().get(index);
	}
	public int getHeight() {
		return game.getHeight();
			
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

}
