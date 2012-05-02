package rally;

import java.io.IOException;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public abstract class Drawable {
	Texture texture;
	
	protected class Position {
		public float x;
		public float y;
	}
	
	protected void init(String file) {
		try {
			texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
