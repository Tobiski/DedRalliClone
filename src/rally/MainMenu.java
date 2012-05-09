package rally;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;

public class MainMenu extends Drawable implements State {
	private ArrayList<Text> textList = new ArrayList<Text>();
		
	public static final int NEWGAME = 2;
	public static final int OPTIONS = 3;
	public static final int EXIT = 4;	
	
	private int menuChoice = NEWGAME;
	
	public MainMenu() {
		offsetX = 0f;
		offsetY = 0f;
		staticOffset = 0;
		isBackground = true;
	}
	
	@Override
	public void pollInput() {
		while(Keyboard.next()) {
			if(Keyboard.getEventKeyState()) {
				if (Keyboard.getEventKey() == Keyboard.KEY_DOWN) {
					menuChoice++;
					if(menuChoice > 4)
						menuChoice = 2;
				}
				if (Keyboard.getEventKey() == Keyboard.KEY_UP) {
					menuChoice--;
					if(menuChoice < 2)
						menuChoice = 4;
				}
				if(Keyboard.getEventKey() == Keyboard.KEY_ESCAPE)
					Game.endGame();
				if(Keyboard.getEventKey() == Keyboard.KEY_RETURN) {
					switch(menuChoice) {
						case NEWGAME:
							changeState();
							break;
						case OPTIONS:
							System.out.println("OPTIONS!");
							break;
						case EXIT:
							Game.endGame();
							break;
						default:
							break;
					}
				}
			}
		}	
	}

	@Override
	public void update() {
		for(int i = 2; i < 5; i++) {
			textList.get(i).setColor(new Color("White"));
		}
		textList.get(menuChoice).setColor(new Color("Red"));
	}

	@Override
	public void changeState() {
			Game.changeState(menuChoice);
	}

	@Override
	public void init() {
		super.init("res/images/mainMenu.png");
		
		textList.add(new Text(new Color("White")));
		textList.get(0).setText("Version 0 01", Game.winWidth-250, 10);
		
		textList.add(new Text(new Color("White")));
		textList.get(1).setText("Ded Ralli Clone", Game.winWidth / 2 - 200, 100);
		
		textList.add(new Text(new Color("Red")));
		textList.get(2).setText("New Game", Game.winWidth / 2, 300);

		textList.add(new Text(new Color("White")));
		textList.get(3).setText("Options", Game.winWidth / 2, 350);
		
		textList.add(new Text(new Color("White")));
		textList.get(4).setText("Exit", Game.winWidth / 2, 400);
	}
	
	public void draw() {
		super.draw();
		for(int i = 0; i < textList.size(); i++) {
			textList.get(i).draw();
		}
	}
}
