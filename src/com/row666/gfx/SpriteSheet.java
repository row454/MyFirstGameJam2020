package com.row666.gfx;

import java.awt.image.BufferedImage;

public class SpriteSheet {
	
	private BufferedImage sheet;
	
	public SpriteSheet(BufferedImage sheet) {
		this.sheet = sheet;
	}
	
	public BufferedImage crop(int vrow, int hrow, boolean isButton) {
		if (isButton){
			return sheet.getSubimage(vrow, hrow, 32, 16);
		} else {
			return sheet.getSubimage(vrow, hrow, 16, 16);
		}
		
	}
	
}
