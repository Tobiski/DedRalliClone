package rally;

import java.util.ArrayList;

public class Text {

	private ArrayList<Alphabet> textList = new ArrayList<Alphabet>();
	private Color color;

	public Text() {

	}

	public Text(Color color) {
		this.color = color;
	}
	
	public void setPosition(float x, float y) {
	}
	
	public void setText(String text, float x, float y) {
		for(int i = 0; i < text.length(); i++) {
			textList.add(new Alphabet(text.charAt(i), x, y, i, color));
		}
	}
	
	public void draw() {
		for(int i = 0; i < textList.size(); i++) {
			textList.get(i).draw();
		}
	}
	
	public void setColor(Color color) {
		for(int i = 0; i < textList.size(); i++) {
			textList.get(i).setColor(color);
		}
	}
}
