package rally;

public class Race implements State {
	private int level = 0;

	State currentState;
	RaceOver raceOver = new RaceOver();
	Shop shop = new Shop();
	ChampionOver cO = new ChampionOver();
	Options options = new Options();
	Pause pause = new Pause();
	
	public Race() {
	}

	@Override
	public void pollInput() {

	}

	@Override
	public void update() {

	}

	@Override
	public void draw() {

	}

	@Override
	public void changeState() {
		
	}
	
	private void changeLevel() {
		
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

}
