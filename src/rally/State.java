package rally;

public interface State {
	
	public void init();
	public void pollInput();
	public void update();
	public void draw();
	public void changeState();
	
}
