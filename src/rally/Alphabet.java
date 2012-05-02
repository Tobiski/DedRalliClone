package rally;

import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL15.glBufferData;
import static org.lwjgl.opengl.GL15.glGenBuffers;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.newdawn.slick.opengl.Texture;

public class Alphabet extends Drawable {

	
	private float offsetX = 0.0625f;
	private float offsetY = 0.0625f;
	private char[] alphabets = new char[] {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
											'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
											'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', ' '};
	
	public Alphabet(char alphabet, float x, float y, int index, Texture texture) {
		this.texture = texture;
		
		position.x = x;
		position.y = y;
		
		if(index != 0)
			position.x += textureSize * index;
		
		vertexData = BufferUtils.createFloatBuffer(amountOfVertices * vertexSize);
		vertexData.put(new float[]{ position.x, position.y, 0f, // TopLeft
									textureSize + position.x, position.y, 0f, // TopRight
									textureSize + position.x, textureSize + position.y, 0f, // BottomRight
									position.x, textureSize + position.y, 0f }); // BottomLeft
		vertexData.flip();
				
		FloatBuffer textureData = BufferUtils.createFloatBuffer(8);
		
		for(int counter = 0, i = 0, j = 0; counter < alphabets.length; counter++) {
			if(i == 10) {
				j++;
				i = 0;
			}			
			if(alphabet == alphabets[counter]) {
				offsetX *= i;
				offsetY *= j;
				break;
			}
			i++;
		}
		
		textureData.put(new float[]{offsetX, offsetY,
									offsetX + 0.0625f, offsetY,
									offsetX + 0.0625f, offsetY + 0.0625f,
									offsetX, offsetY + 0.0625f});
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
		super.draw();
	}
}
