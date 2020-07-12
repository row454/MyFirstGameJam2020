package com.row666.gfx;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class ImagePreLoader {
	private static final int size = 16;
	private static final int buttonX = 32;
	private static final int sheetSide = size * 16;
	public static BufferedImage inventory;
	private static ArrayList<BufferedImage> assets = new ArrayList<>();




		public static void init(){
			
			
			// inventory = ImageLoader.loadImage("/textures/inventory_screen.png");
	        SpriteSheet entities = new SpriteSheet(ImageLoader.loadImage("/textures/sheet_entities.png"));
	        for(int y = 0; y < sheetSide / size; y++){
	            for(int x = 0; x < sheetSide / size; x++) {
	                assets.add(entities.crop(x * size, y * size, false));
	            }
	        }
	        SpriteSheet tiles = new SpriteSheet(ImageLoader.loadImage("/textures/sheet_tiles.png"));
	        for(int y = 0; y < sheetSide / size; y++){
	            for(int x = 0; x < sheetSide / size; x++) {
	                assets.add(tiles.crop(x * size, y * size, false));
	            }
	        }
	        SpriteSheet staticEnt = new SpriteSheet(ImageLoader.loadImage("/textures/skills_and_stats_sheet.png"));
	        for(int y = 0; y < sheetSide / size; y++){
	            for(int x = 0; x < sheetSide / size; x++) {
	                assets.add(staticEnt.crop(x * size, y * size, false));
	            }
	        }
	        /*
	        SpriteSheet buttons = new SpriteSheet(ImageLoader.loadImage("/textures/sheet_buttons.png"));
	        
	        for(int y = 0; y < sheetSide / size; y++){
	            for(int x = 0; x < sheetSide / buttonX; x++) {
	                assets.add(buttons.crop(x * buttonX, y * size, true));
	            }
	        }
	        
	        SpriteSheet items = new SpriteSheet(ImageLoader.loadImage("/textures/sheet_items.png"));
	        for(int y = 0; y < sheetSide / size; y++){
	            for(int x = 0; x < sheetSide / size; x++) {
	                assets.add(items.crop(x * size, y * size, false));
	            }
	        }
	         */
	        System.out.println(ImagePreLoader.getAssetsStatic().size());
	    }

	    public static ArrayList<BufferedImage> getAssetsStatic() {
	        return assets;
	    }
	    public ArrayList<BufferedImage> getAssets() {
	        return assets;
	    }
}
