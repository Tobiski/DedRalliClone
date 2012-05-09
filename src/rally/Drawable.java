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
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public abstract class Drawable {
	private int vboVertexHandle;
	private int vboTextureHandle;
	private int vboColorHandle;
	private FloatBuffer vertexData;
	private FloatBuffer textureData;
	private FloatBuffer colorData;
	public Texture texture;
	private final int amountOfVertices = 4;
	private final int vertexSize = 3;
	public float offsetX = 0;
	public float offsetY = 0;
	public float staticOffset = 0;
	public Position position = new Position();
	public Color color = new Color("White");
	public int textureSize = 0;
	public boolean isBackground = false;
	
	protected class Position {
		public float x = 0;
		public float y = 0;
	}
	
	protected void init(String file) {
		try {
			texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(file));
		} catch (IOException e) {
			e.printStackTrace();
		}

		writeDataToBuffer();
		setColor();
	}
	
	public void setColor() {
		colorData = BufferUtils.createFloatBuffer(amountOfVertices * vertexSize + 4);
		
		colorData.put(new float[]{ color.R, color.G, color.B, color.A,
				color.R, color.G, color.B, color.A,
				color.R, color.G, color.B, color.A,
				color.R, color.G, color.B, color.A });

		colorData.flip();
		
		vboColorHandle = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, vboColorHandle);
		glBufferData(GL_ARRAY_BUFFER, colorData, GL_STATIC_DRAW);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
	}
	
	protected void writeDataToBuffer() {
		vertexData = BufferUtils.createFloatBuffer(amountOfVertices * vertexSize);
		textureData = BufferUtils.createFloatBuffer(8);
		if(isBackground) {
			vertexData.put(new float[]{ 0f, 0f, 0,
										Game.winWidth, 0f, 0f,
										Game.winWidth, Game.winHeight, 0f,
										0f, Game.winHeight, 0f });
			textureData.put(new float[]{0, 0, 1, 0, 1, 1, 0, 1});
		}
		else {
			vertexData.put(new float[]{ position.x, position.y, 0f, // TopLeft
										textureSize + position.x, position.y, 0f, // TopRight
										textureSize + position.x, textureSize + position.y, 0f, // BottomRight
										position.x, textureSize + position.y, 0f }); // BottomLeft
			textureData.put(new float[]{offsetX, offsetY,
					offsetX + staticOffset, offsetY,
					offsetX + staticOffset, offsetY + staticOffset,
					offsetX, offsetY + staticOffset});
		}
		
		vertexData.flip();
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

	public void draw() {
		GL11.glEnable(GL11.GL_TEXTURE_2D);

		glEnableClientState(GL_VERTEX_ARRAY);
		glEnableClientState(GL11.GL_TEXTURE_COORD_ARRAY);
		glEnableClientState(GL11.GL_COLOR_ARRAY);

		texture.bind();

		glBindBuffer(GL_ARRAY_BUFFER, vboColorHandle);
		GL11.glColorPointer(4, GL_FLOAT, 0, 0L);

		glBindBuffer(GL_ARRAY_BUFFER, vboVertexHandle);
		glVertexPointer(3, GL_FLOAT, 0, 0L);

		glBindBuffer(GL_ARRAY_BUFFER, vboTextureHandle);
		GL11.glTexCoordPointer(2, GL_FLOAT, 0, 0L);

		glDrawArrays(GL11.GL_QUADS, 0, amountOfVertices);

		glDisableClientState(GL11.GL_COLOR_ARRAY);
		glDisableClientState(GL11.GL_TEXTURE_COORD_ARRAY);
		glDisableClientState(GL_VERTEX_ARRAY);

		GL11.glDisable(GL11.GL_TEXTURE_2D);
	}
}
