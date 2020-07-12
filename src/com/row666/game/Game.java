package com.row666.game;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import com.row666.display.Display;
import com.row666.gfx.ImagePreLoader;
import com.row666.input.KeyManager;
import com.row666.world.Floor;

public class Game implements Runnable {
	
	private String title;
	private Display display;
	public int width, height;
	
	private boolean running = false;
	private Thread thread;
	
	private BufferStrategy bs;
	private Graphics g;
	
	private Handler handler;
	
	private KeyManager keyManager;
	private Floor floor = new Floor(0);
	
	
	public Game(String title, int width, int height) {
		this.title = title;
		this.width = width;
		this.height = height;
		keyManager = new KeyManager();
		
	}
	
	private void init() {
		display = new Display(title, width, height);
		display.getJFrame().addKeyListener(keyManager);
		ImagePreLoader.init();
		handler = new Handler(this);
	}



	@Override
	public void run() {
		
		init();
		
		int fps = 60;
		int tps = 60;
		double timePerTick = 1000000000 / tps;
		double timePerFrame = 1000000000 / fps;
		double tickDelta = 0;
		double frameDelta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;
		int frames = 0;
		while(running) {
			now = System.nanoTime();
			tickDelta += (now - lastTime) / timePerTick;
			frameDelta += (now - lastTime) / timePerFrame;
			timer += now - lastTime;
			lastTime = now;
			
			if(tickDelta >= 1) {
				tick();
				ticks++;
				tickDelta--;
			}
			if(frameDelta >= 1) {
				render();
				frames++;
				frameDelta--;
			}
			
			if(timer>=1000000000) {
				System.out.println("FPS:" + frames);
				System.out.println("TPS:" + ticks);
				ticks = 0;
				frames = 0;
				timer = 0;
			}
		}
		
		stop();
		
		
	}
	
	private void tick() {
		
	}
	
	private void render() {
		bs = display.getCanvas().getBufferStrategy();
		if(bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		//clear
		g.clearRect(0, 0, width, height);
		//draw	
		floor.render(g);
		// if(StateManager.getState() != null)
		// 	StateManager.getState().render(g);
		
		//end
		bs.show();
		g.dispose();
	}
	
	public synchronized void start() {
		if(running)
			return;
		
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop() {
		if(!running)
			return;
		
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
}