package rally;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL11.GL_VERTEX_ARRAY;
import static org.lwjgl.opengl.GL11.glDisableClientState;
import static org.lwjgl.opengl.GL11.glDrawArrays;
import static org.lwjgl.opengl.GL11.glEnableClientState;
import static org.lwjgl.opengl.GL11.glVertexPointer;
import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL15.glBufferData;
import static org.lwjgl.opengl.GL15.glGenBuffers;

import java.io.IOException;
import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public abstract class Drawable {
	protected Texture texture;
	protected int vboVertexHandle;
	protected int vboTextureHandle;
	protected final int amountOfVertices = 4;
	protected final int vertexSize = 3;
	protected int textureSize = Game.winWidth / 64;

	protected FloatBuffer vertexData;
	protected Vector2f position = new Vector2f();
	
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
	
	protected void writeDataToBuffer() {
		FloatBuffer vertexData = BufferUtils.createFloatBuffer(amountOfVertices * vertexSize);
		vertexData.put(new float[]{ 0f, 0f, 0,
									Game.winWidth, 0f, 0f,
									Game.winWidth, Game.winHeight, 0f,
									0f, Game.winHeight, 0f });
		vertexData.flip();
		
		FloatBuffer textureData = BufferUtils.createFloatBuffer(8);
		textureData.put(new float[]{0, 0, 1, 0, 1, 1, 0, 1});
		textureData.flip();
		
		vboVertexHandle = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, vboVertexHandle);
		glBufferData(GL_ARRAY_BUFFER, vertexData, GL_STATIC_DRAW);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		
		vboTextureHandle = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, vboTextureHandle);
		glBufferData(GL_ARRAY_BUFFER, textureData, GL_STATIC_DRAW);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
	}
	
	protected void setAsBackground() {
		textureSize = Game.winWidth / 64;
	}
	
	protected void setAsTile() {
		textureSize = Game.winWidth / 10;
	}
	
	protected void draw() {
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		
		glEnableClientState(GL_VERTEX_ARRAY);
		glEnableClientState(GL11.GL_TEXTURE_COORD_ARRAY);
		
		texture.bind();
		
		glBindBuffer(GL_ARRAY_BUFFER, vboVertexHandle);
		glVertexPointer(3, GL_FLOAT, 0, 0L);

		glBindBuffer(GL_ARRAY_BUFFER, vboTextureHandle);
		GL11.glTexCoordPointer(2, GL_FLOAT, 0, 0L);
		
		glDrawArrays(GL11.GL_QUADS, 0, amountOfVertices);

		glDisableClientState(GL11.GL_TEXTURE_COORD_ARRAY);
		glDisableClientState(GL_VERTEX_ARRAY);
		
		GL11.glDisable(GL11.GL_TEXTURE_2D);
	}
}
