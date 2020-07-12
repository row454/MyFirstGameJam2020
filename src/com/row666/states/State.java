package com.row666.states;

import java.awt.Graphics;

import com.row666.game.Handler;

public abstract class State {
	
	public abstract void tick();
	
	public abstract void render(Graphics g);
	
	protected Handler handler;
	
	public State(Handler handler) {
		this.handler = handler;
	}
	
}
