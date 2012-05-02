package rally;

import java.util.ArrayList;
import org.lwjgl.input.Keyboard;

public class Race implements State {
	private int level = 0;

	State currentState;
	RaceOver raceOver = new RaceOver();
	Shop shop = new Shop();
	ChampionOver cO = new ChampionOver();
	Options options = new Options();
	Pause pause = new Pause();

	private int numberOfTiles = 100;
	
	private ArrayList<String> levelTilesString = new ArrayList<String>();
	private ArrayList<LevelTile> levelTiles = new ArrayList<LevelTile>();
	
	public Race() {			
	}

	@Override
	public void pollInput() {
		if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE))
			Game.endGame();
	}

	@Override
	public void update() {

	}

	@Override
	public void draw() {
		for(int i = 0; i < levelTiles.size(); i++) {
			levelTiles.get(i).draw();
		}
	}

	@Override
	public void changeState() {
		
	}
	
	private void changeLevel() {
		
	}

	@Override
	public void init() {		
		String str;
		str = FileLoader.read("levels/level1.txt");
		for(String tile : str.split(";", numberOfTiles)) {
		levelTilesString.add(tile);
		}
		
		for(int counter = 0, i = 0, j = 0; counter < levelTilesString.size()-1; i++, counter++) {
			if(i == 10) {
				j++;
				i = 0;
			}
			levelTiles.add(new LevelTile(i, j, Integer.parseInt(levelTilesString.get(counter))));
		}
	}

}
