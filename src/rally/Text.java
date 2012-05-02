package rally;

import java.io.IOException;
import java.util.ArrayList;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class Text {
	private Texture texture;

	private ArrayList<Alphabet> textList = new ArrayList<Alphabet>();
	
	public Text() {

	}
	
	public void init() {
		try {
			texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/images/ascii.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setPosition(float x, float y) {
	}
	
	public void setText(String text, float x, float y) {
		for(int i = 0; i < text.length(); i++) {
			textList.add(new Alphabet(text.charAt(i), x, y, i, texture));	
		}
	}
	
	public void draw() {
		for(int i = 0; i < textList.size(); i++) {
			textList.get(i).draw();
		}
	}
}
