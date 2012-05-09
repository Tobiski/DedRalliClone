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

	private final int numberOfTiles = 100;
	
	private ArrayList<String> levelTilesString = new ArrayList<String>();
	private ArrayList<LevelTile> levelTiles = new ArrayList<LevelTile>();
	private PlayerCar pCar = new PlayerCar();
	
	public Race() {			
	}

	@Override
	public void pollInput() {
		while (Keyboard.next()) {
			
		    if (Keyboard.getEventKeyState()) {
		        if (Keyboard.getEventKey() == Keyboard.KEY_UP) {
		        	pCar.up(true);
		        }
		        if (Keyboard.getEventKey() == Keyboard.KEY_DOWN) {
		        	pCar.down(true);
		        }
		        if (Keyboard.getEventKey() == Keyboard.KEY_LEFT) {
		        	pCar.left(true);
		        }
		        if (Keyboard.getEventKey() == Keyboard.KEY_RIGHT) {
		        	pCar.right(true);
		        }
				if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE))
					changeState();
		    }
		    else {
		        if (Keyboard.getEventKey() == Keyboard.KEY_UP) {
		        	pCar.up(false);
		        }
		        if (Keyboard.getEventKey() == Keyboard.KEY_DOWN) {
		        	pCar.down(false);
		        }
		        if (Keyboard.getEventKey() == Keyboard.KEY_LEFT) {
		        	pCar.left(false);
		        }
		        if (Keyboard.getEventKey() == Keyboard.KEY_RIGHT) {
		        	pCar.right(false);
		        }
		    }
		}
	}

	@Override
	public void update() {

	}

	@Override
	public void draw() {
		/* Draw background from road tiles */
		for(int i = 0; i < levelTiles.size(); i++) {
			levelTiles.get(i).draw();
		}
	}

	@Override
	public void changeState() {
		Game.changeState(0);
	}
	
	private void generateLevel(int newLevel) {
		level = newLevel;
		String str;
		
		/* Clear old level */
		levelTiles.clear();
		levelTilesString.clear();
		
		/* Generate new level */
		switch(level) {
			case 0: 
				str = FileLoader.read("levels/level1.txt");
				break;
			default:
				str = FileLoader.read("levels/level1.txt");
		}

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

	@Override
	public void init() {		
		generateLevel(0);
	}
}
