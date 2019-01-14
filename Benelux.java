import java.util.List;

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
    t.moveCursor(x,y);
    for(int i=0;i<r.getWidth();i++){
      t.putCharacter('#');
    }
    for(int i=1;i<r.getHeight();i++){
      t.moveCursor(x,y+i);
      t.putCharacter('#');
      t.moveCursor(x+r.getWidth()-1,y+i);
      t.putCharacter('#');
    }
    t.moveCursor(x,y+r.getHeight());
    for(int i=0;i<r.getWidth();i++){
      t.putCharacter('#');
    }
  }

	public static void drawMonster(Monster m, Terminal t){
		int x = m.getX();
		int y = m.getY();
		t.moveCursor(x,y);
		t.putCharacter('!');
	}

	public static void eraser(int x, int y, Terminal t){
		t.moveCursor(x,y);
		t.putCharacter(' ');
	}
	public static void drawPlayer(Player p, Terminal t){
		int x = p.getX();
		int y = p.getY();
		t.moveCursor(x,y);
		t.putCharacter('@');
	}

	public static void main(String[] args) {

    Room r = new Room(9, 5, 10, 4);
		Monster monster1 = new Monster("Alpha");
		monster1.setX(15);
		monster1.setY(15);
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
      drawRoom(r,terminal);
			drawMonster(monster1, terminal);
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
	          if (key.getCharacter() == 'P') {
	            mode = "Game Mode";
							terminal.clearScreen();
	          }
	        }

	        if (mode.equals("Inventory Mode")) {
	          if (key.getCharacter() == 'P') {
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
	          if (key.getCharacter() == 'P'){
	            mode = "Pause Menu";
							terminal.clearScreen();
	          }
	          if (key.getKind() == Key.Kind.ArrowDown) {
	            eraser(currentX, currentY, terminal);
	            playerM.changeDirection("South");
	            playerM.moveForward();
	          }
	          else if (key.getKind() == Key.Kind.ArrowLeft) {
							eraser(currentX,currentY, terminal);
	            playerM.changeDirection("West");
	            playerM.moveForward();
	          }
	          else if (key.getKind() == Key.Kind.ArrowUp) {
	            eraser(currentX,currentY, terminal);
	            playerM.changeDirection("North");
	            playerM.moveForward();
	          }
	          else if (key.getKind() == Key.Kind.ArrowRight) {
	            eraser(currentX,currentY, terminal);
	            playerM.changeDirection("East");
	            playerM.moveForward();
	          }
	        }
	      }

	      if(mode.equals("Game Mode")){
	        lastTime = currentTime;
	        currentTime = System.currentTimeMillis();
	        timer += (currentTime -lastTime);//add the amount of time since the last frame.
	        //DO GAME STUFF HERE
					eraser(monster1.getX(), monster1.getY(), terminal);
					monster1.changeDirection(monster1.getRandomDirection());
					monster1.moveForward();
	        //monster2.changeDirection(getRandomDirection());
	        //monster3.changeDirection(getRandomDirection());
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
			putString(1,2,terminal,"Milliseconds since start of program: "+millis);
			if(millis/1000 > lastSecond){
				lastSecond = millis / 1000;
				//one second has passed.
			putString(1,3,terminal,"Seconds since start of program: "+lastSecond);


		}
	}
}
}
