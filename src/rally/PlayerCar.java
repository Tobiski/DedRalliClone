package rally;

public class PlayerCar extends Entity {
	public boolean left;
	public boolean right;
	public boolean down;
	public boolean up;
	
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
