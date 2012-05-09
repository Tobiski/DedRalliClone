package rally;

public class Alphabet extends Drawable {
	private char[] alphabets = new char[] {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
											'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
											'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', ' '};
	
	public Alphabet(char alphabet, float x, float y, int index, Color color) {		
		position.x = x;
		position.y = y;
		this.color = color;
				
		offsetX = 0.0625f;
		offsetY = 0.0625f;
		staticOffset = 0.0625f;
		textureSize = Game.winWidth / 64;
		
		if(index != 0)
			position.x += textureSize * index;
		
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
		super.init("res/images/ascii.png");
	}
	
	public void setColor(Color color) {
		this.color = color;
		super.setColor();
	}
}
