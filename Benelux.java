import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.io.File;
import java.io.FileNotFoundException;

//API : http://mabe02.github.io/lanterna/apidocs/2.1/
import com.googlecode.lanterna.terminal.Terminal.SGR;
import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.input.Key.Kind;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.Terminal.Color;
import com.googlecode.lanterna.terminal.TerminalSize;
import com.googlecode.lanterna.LanternaException;
import com.googlecode.lanterna.input.CharacterPattern;
import com.googlecode.lanterna.input.InputDecoder;
import com.googlecode.lanterna.input.InputProvider;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.input.KeyMappingProfile;


public class Benelux{

	public static void putString(int r, int c,Terminal t, String s){
		t.moveCursor(r,c);
		for(int i = 0; i < s.length();i++){
			t.putCharacter(s.charAt(i));
		}
	}
  public static void drawRoom(Room r, Terminal t){
    int x = r.getTLCX();
    int y = r.getTLCY();
    t.moveCursor(y,x);
    for(int i=0;i<r.getWidth();i++){
      t.putCharacter('#');
    }
    for(int i=1;i<r.getHeight();i++){
      t.moveCursor(y,x+i);
      t.putCharacter('#');
      t.moveCursor(y+r.getWidth()-1,x+i);
      t.putCharacter('#');
    }
    t.moveCursor(y,x+r.getHeight());
    for(int i=0;i<r.getWidth();i++){
      t.putCharacter('#');
    }
  }
	public static void drawFloor(Floor fr, Terminal t){
		for (int i = 0; i<fr.getGrid().length;i++){
			t.moveCursor(i,0);
			for (int o =0; o<fr.getGrid()[i].length;o++){
				t.putCharacter(fr.getGrid()[i][o]);
			}
		}
	}
	public static void drawMonster(Monster m, Terminal t){
		int x = m.getX();
		int y = m.getY();
		t.moveCursor(y,x);
		t.putCharacter('!');
	}

	public static void eraser(int x, int y, Terminal t){
		t.moveCursor(y,x);
		t.putCharacter(' ');
	}
	public static void drawPlayer(Player p, Terminal t){
		int x = p.getX();
		int y = p.getY();
		t.moveCursor(y,x);
		t.putCharacter('@');
	}

	public static void drawItem(Item i, Terminal t){
		int x = i.getX();
		int y = i.getY();
		t.moveCursor(y,x);
		t.putCharacter('?');
	}

	public static void main(String[] args) throws FileNotFoundException{
		/*
		Room r1 = new Room(1,1,7,5);
    Room r2 = new Room(8,8,3,4);
    ArrayList<Room> rs = new ArrayList<Room>();
    rs.add(r1);
    rs.add(r2);
		Floor f = new Floor(rs, 1, 1, 1, 1, 30, 20);
    Room r = new Room(9, 5, 10, 4);
		Monster monster1 = new Monster("Alpha"); //this species needs a random gen, will work on later
		monster1.setX(15);
		monster1.setY(15);
		Monster monster2 = new Monster("Alpha");
		monster2.setX(20);
		monster2.setY(45);
		Monster monster3 = new Monster("Alpha");
		monster3.setX(30);
		monster3.setY(50);
		Consumable help = new Consumable();
		help.setX(40);
		help.setY(30);
		*/
		ArrayList<Room> rs = new ArrayList<Room>();
    Random r = new Random();
    Floor f = new Floor(rs, 35, 20, r);
    Room r1 = new Room(3, 5);
    Room r2 = new Room(5, 3);
    f.addRoom(r1,1,1);
    f.addRoom(r2,8,4);
    f.addPath(8,3,6,2,-1,1);
    f.addAllRooms();
    f.addEntrance();
    f.addExit();

		Player playerM = new Player();
		playerM.setX(10);
		playerM.setY(10);

		Terminal terminal = TerminalFacade.createTextTerminal();
		terminal.enterPrivateMode();

		TerminalSize size = terminal.getTerminalSize();
		terminal.setCursorVisible(false);

		boolean running = true;

		long tStart = System.currentTimeMillis();
		long lastSecond = 0;

		while(running){
			/*
			terminal.moveCursor(x,y);
			terminal.applyBackgroundColor(Terminal.Color.WHITE);
			terminal.applyForegroundColor(Terminal.Color.BLACK);
			//applySGR(a,b) for multiple modifiers (bold,blink) etc.
			terminal.applySGR(Terminal.SGR.ENTER_UNDERLINE);
			terminal.putCharacter('x');
			//terminal.putCharacter(' ');
			terminal.applyBackgroundColor(Terminal.Color.DEFAULT);
			terminal.applyForegroundColor(Terminal.Color.DEFAULT);
			terminal.applySGR(Terminal.SGR.RESET_ALL);

			terminal.moveCursor(size.getColumns()-5,5);
			terminal.applyBackgroundColor(Terminal.Color.RED);
			terminal.applyForegroundColor(Terminal.Color.YELLOW);
			terminal.applySGR(Terminal.SGR.ENTER_BOLD);
			terminal.putCharacter(' ');
			terminal.putCharacter(' ');
			terminal.putCharacter('0');
			terminal.putCharacter(' ');
			terminal.moveCursor(size.getColumns()-5,6);
			terminal.putCharacter(' ');
			terminal.putCharacter(' ');
			terminal.putCharacter(' ');
			terminal.putCharacter(' ');
			terminal.applyBackgroundColor(Terminal.Color.DEFAULT);
			terminal.applyForegroundColor(Terminal.Color.DEFAULT);
			*/
			/*
      drawRoom(r,terminal);
			drawRoom(r1, terminal);
			drawRoom(r2, terminal);
			drawMonster(monster1, terminal);
			drawMonster(monster2, terminal);
			drawMonster(monster3, terminal);
			drawItem(help, terminal);
			*/
			drawFloor(f, terminal);
			drawPlayer(playerM,terminal);

			//r.drawRoom(terminal);

	    boolean updating = true;
	    String mode = "Game Mode";
	    long lastTime =  System.currentTimeMillis();
	    long currentTime = lastTime;
	    long timer = 0;
			long tEnd = System.currentTimeMillis();
			long millis = tEnd - tStart;
			int currentX = playerM.getX();
			int currentY = playerM.getY();

	/*
	    while (mode.equals("Start Menu") && running == true) {
	      Key key = terminal.readInput();
	      putString(1,3,terminal, "Start Menu \n Press the Corresponding Number \n -------- \n 1.Start Game \n 2. Exit Game");
	        if (key != null){
	          if (key.getCharacter() == '1') {
	            mode = "Game Mode";
	          }
	          if (key.getCharacter() == '2') {
	            terminal.exitPrivateMode();
	            running = false;
	          }
	        }
	      }
	*/
	    //while(millis/1000 != lastSecond + 1){ //check for one second?

	      Key key = terminal.readInput();

	      if (key != null){
	        //YOU CAN PUT DIFFERENT SETS OF BUTTONS FOR DIFFERENT MODES!!!

	        //pause mode
	        if(mode.equals("Pause Menu")){
	          if (key.getKind() == Key.Kind.Escape) {
	            terminal.exitPrivateMode();
	            running = false;
	          }
	          if (key.getCharacter() == '1') {
	            mode = "Inventory Mode";
							terminal.clearScreen();
	          }
	          if (key.getCharacter() == 'p') {
	            mode = "Game Mode";
							terminal.clearScreen();
	          }
	        }

	        if (mode.equals("Inventory Mode")) {
	          if (key.getCharacter() == 'p') {
	            mode = "Game Mode";
							terminal.clearScreen();
	          }
	          if (key.getCharacter() == '1') {
	            playerM.getInventory().get(0);
	          }
						if (key.getCharacter() == '2') {
	            playerM.getInventory().get(1);
	          }
						if (key.getCharacter() == '3') {
	            playerM.getInventory().get(2);
	          }
						if (key.getCharacter() == '4') {
	            playerM.getInventory().get(3);
	          }
						if (key.getCharacter() == '5') {
	            playerM.getInventory().get(4);
	          }
	        }
	        if (mode.equals("Game Mode")) {
	          if (key.getKind() == Key.Kind.Escape) {
	            terminal.exitPrivateMode();
	            running = false;
	          }
	          if (key.getCharacter() == 'p'){
	            mode = "Pause Menu";
							terminal.clearScreen();
	          }
	          if (key.getKind() == Key.Kind.ArrowDown) {
	            eraser(currentX, currentY, terminal);
	            playerM.changeDirection("South");
	            playerM.moveForward(f);
	          }
	          else if (key.getKind() == Key.Kind.ArrowLeft) {
							eraser(currentX,currentY, terminal);
	            playerM.changeDirection("West");
	            playerM.moveForward(f);
	          }
	          else if (key.getKind() == Key.Kind.ArrowUp) {
	            eraser(currentX,currentY, terminal);
	            playerM.changeDirection("North");
	            playerM.moveForward(f);
	          }
	          else if (key.getKind() == Key.Kind.ArrowRight) {
	            eraser(currentX,currentY, terminal);
	            playerM.changeDirection("East");
	            playerM.moveForward(f);
	          }
	        }
	      }

	      if(mode.equals("Game Mode")){
	        lastTime = currentTime;
	        currentTime = System.currentTimeMillis();
	        timer += (currentTime -lastTime);//add the amount of time since the last frame.
	        //DO GAME STUFF HERE
					if (millis/1000 > lastSecond){
						/*
						eraser(monster1.getX(), monster1.getY(), terminal);
						monster1.changeDirection(monster1.getRandomDirection());
						monster1.moveForward(f);
						eraser(monster2.getX(), monster2.getY(), terminal);
						monster2.changeDirection(monster2.getRandomDirection());
						monster2.moveForward(f);
						eraser(monster3.getX(), monster3.getY(), terminal);
						monster3.changeDirection(monster3.getRandomDirection());
						monster3.moveForward(f);
						*/
					}
	        //putString(1,3,terminal, "Game here...",Terminal.Color.WHITE,Terminal.Color.RED);
	        //putString(3,5,terminal, "Time: "+timer,Terminal.Color.WHITE,Terminal.Color.RED);

	      }else if (mode.equals("Inventory Mode")) {
	        putString(1,5,terminal, "Press P to return");
	        putString(1,7,terminal, "1. ");
	      }else if (mode.equals("Pause Menu")) {
	        putString(1,5,terminal, "Press Escape to Close");
	        putString(1,6,terminal, "1. Inventory");
	        putString(1,7,terminal, "Press P to return to the Game");
	      }


			//DO EVEN WHEN NO KEY PRESSED:
			/*
			putString(1,2,terminal,"Milliseconds since start of program: "+millis);
			if(millis/1000 > lastSecond){
				lastSecond = millis / 1000;
				//one second has passed.
			putString(1,3,terminal,"Seconds since start of program: "+lastSecond);
			}
			*/
		}
	}
}
