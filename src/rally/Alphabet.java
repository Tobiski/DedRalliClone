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

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.opengl.Texture;

public class Alphabet {
	private Texture texture;
	private final int amountOfVertices = 4;
	private final int vertexSize = 3;
	private int textureSize = 32;
	private int vboVertexHandle;
	private int vboTextureHandle;
	private FloatBuffer vertexData;
	private Vector2f position = new Vector2f();
	
	public Alphabet(char alphabet, float x, float y, int index, Texture texture) {
		this.texture = texture;
		
		position.x = x;
		position.y = y;
		
		if(index != 0)
			position.x += textureSize * index;
		
		vertexData = BufferUtils.createFloatBuffer(amountOfVertices * vertexSize);
		vertexData.put(new float[]{ position.x, position.y, 0, // TopLeft
									textureSize + position.x, position.y, 0f, // TopRight
									textureSize + position.x, textureSize + position.y, 0f, // BottomRight
									position.x, textureSize + position.y, 0f }); // BottomLeft
		vertexData.flip();
				
		FloatBuffer textureData = BufferUtils.createFloatBuffer(8);
		if(alphabet == 'A') 
			textureData.put(new float[]{0, 0,
										0.0625f, 0,
										0.0625f, 0.0625f,
										0, 0.0625f});
		if(alphabet == 'B') 
			textureData.put(new float[]{0.0625f, 0,
										0.125f, 0,
										0.125f, 0.0625f,
										0.0625f, 0.0625f});
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
