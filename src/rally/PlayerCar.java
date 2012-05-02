package rally;

public class PlayerCar extends Entity {
	private boolean left;
	private boolean right;
	private boolean down;
	private boolean up;
	
	public void up(boolean up) {
		this.up = up;
	}
	
	public void down(boolean down) {
		this.down = down;
	}
	
	public void left(boolean left) {
		this.left = left;
	}
	
	public void right(boolean right) {
		this.right = right;
	}
	
	public void update() {
		
	}
}
