package states;

import java.awt.Font;

import main.Main;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.*;

public class SplashScreen extends BasicGameState {

	Image splash;
	
	TrueTypeFont font;
	private int stateID;
	private int elapsedTime;
	private boolean draw;
	private final int DELAY = 3000;
	
	public SplashScreen(int stateID) {
		this.stateID = stateID;
	}
	
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		Font awtFont = new Font("Times New Roman", Font.BOLD, 24);
		font = new TrueTypeFont(awtFont, false);
		splash = new Image("assets/pictures/city.jpg");
	}
	
	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException {
		arg2.drawImage(splash, 0, 0, 0, 0, (int)Main.options.resolution.getWidth(), (int)Main.options.resolution.getHeight());
		if (elapsedTime % 50 == 0)
			draw = !draw;
		if (draw) {
			arg2.setColor(new Color(255, 255, 255));
			for (int i = 0; i < 20; i++) {
				arg2.drawRect((int)(Main.options.resolution.getWidth()-font.getWidth("- ~ - Press return to start - ~ -"))/2-10, (int)Main.options.resolution.getHeight()/2, font.getWidth("- ~ - Press return to start - ~ -")+20, i);
			}
			arg2.setColor(new Color(0, 0, 0));
			arg2.drawString("- ~ - Press return to start - ~ -", (int)(Main.options.resolution.getWidth()-font.getWidth("- ~ - Press return to start - ~ -"))/2, (int)Main.options.resolution.getHeight()/2);
		}
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {
		elapsedTime++;
		if (arg0.getInput().isKeyPressed(Input.KEY_ENTER)) 
			arg1.enterState(Main.MAINMENU);
	}

	@Override
	public int getID() {
		return stateID;
	}
}
