package rally;

public class Color{
	public float R = 0;
	public float G = 0;
	public float B = 0;
	public float A = 1;
	
	Color(String color) {
		
		if(color.equals("Red"))
			R = 1;
		else if(color.equals("Green"))
			G = 1;
		else if(color.equals("Blue"))
			B = 1;
		else if(color.equals("White")) {
			R = 1;
			G = 1;
			B = 1;
		}
	}
}