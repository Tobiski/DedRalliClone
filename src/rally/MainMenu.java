package rally;

import java.io.IOException;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class MainMenu implements State {
	Texture background;
	
	public MainMenu() {
	}
	
	public void init() {
		try {
			background = TextureLoader.getTexture("JPG", ResourceLoader.getResourceAsStream("res/mainMenu.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void pollInput() {

	}

	@Override
	public void update() {

	}

	@Override
	public void draw() {
		
	}

	@Override
	public void changeState() {
		
	}

}
