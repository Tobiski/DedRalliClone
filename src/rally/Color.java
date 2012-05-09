package rally;

public class Color{
	public float R = 0;
	public float G = 0;
	public float B = 0;
	public float A = 1;
	
	Color(String color) {
		switch(color)
		{
		case "Red":
			R = 1;
			break;
		case "Green":
			G = 1;
			break;
		case "Blue":
			B = 1;
			break;
		case "White":
			R = 1; G = 1; B = 1;
			break;
		default:
			break;
		}
	}
}