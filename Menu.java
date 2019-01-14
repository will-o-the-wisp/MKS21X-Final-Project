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


public class Menu {

  public static void putString(int r, int c, Terminal t, String s){
    t.moveCursor(r,c);
    for(int i = 0; i < s.length();i++){
      t.putCharacter(s.charAt(i));
    }
  }

  public static void putString(int r, int c,Terminal t,
        String s, Terminal.Color forg, Terminal.Color back ){
    t.moveCursor(r,c);
    t.applyBackgroundColor(forg);
    t.applyForegroundColor(Terminal.Color.BLACK);

    for(int i = 0; i < s.length();i++){
      t.putCharacter(s.charAt(i));
    }
    t.applyBackgroundColor(Terminal.Color.DEFAULT);
    t.applyForegroundColor(Terminal.Color.DEFAULT);
  }

  public static void drawCharacter(int r, int c, Terminal t, char ca, Terminal.Color hey){
    t.moveCursor(r,c);
    t.applyBackgroundColor(hey);
    t.putCharacter(ca);
  }
  public static void main(String[] args) {

    int x = 1;
    int y = 1;
    boolean updating = true;
    String mode = "Game Mode";
    long lastTime =  System.currentTimeMillis();
    long currentTime = lastTime;
    long timer = 0;

    Terminal terminal = TerminalFacade.createTextTerminal();
    terminal.enterPrivateMode();

    TerminalSize size = terminal.getTerminalSize();
    terminal.setCursorVisible(false);


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
    while(updating){
      terminal.moveCursor(x,y);
			terminal.applyBackgroundColor(Terminal.Color.WHITE);
			terminal.applyForegroundColor(Terminal.Color.BLACK);
			//applySGR(a,b) for multiple modifiers (bold,blink) etc.
			terminal.applyBackgroundColor(Terminal.Color.DEFAULT);
			terminal.applyForegroundColor(Terminal.Color.DEFAULT);
			terminal.applySGR(Terminal.SGR.RESET_ALL);

      Key key = terminal.readInput();

      if (key != null){
        //YOU CAN PUT DIFFERENT SETS OF BUTTONS FOR DIFFERENT MODES!!!

        //pause mode
        if(mode.equals("Pause Menu")){
          if (key.getKind() == Key.Kind.Escape) {
            terminal.exitPrivateMode();
            running = updating;
          }
          if (key.getKind() == Key.Kind.F1) {
            mode = "Inventory Mode";
          }
          if (key.getCharacter() == 'P') {
            mode = "Game Mode";
          }
        }

        //pausing
        if (key.getCharacter() == 'P' && mode.equals("Game Mode")) {
          mode = "Pause Menu";
          terminal.clearScreen();
          lastTime = System.currentTimeMillis();
          currentTime = System.currentTimeMillis();
        }

        if (mode.equals("Inventory Mode")) {
          if (key.getCharacter() == 'P') {
            mode = "Game Mode";
          }
          if (key.getCharacter() == '1') {
            //uses Item and repeat for 10 slots
          }
        }
        if (mode.equals("Game Mode")) {
          if (key.getKind() == Key.Kind.Escape) {
            terminal.exitPrivateMode();
            updating = false;
          }
          if (key.getKind() == Key.Kind.ArrowDown) {
            drawCharacter(playerM.getX(),playerM.getY(),terminal,' ',Terminal.Color.BLACK);
            playerM.changeDirection("South");
            playerM.moveForward();
          }
          else if (key.getKind() == Key.Kind.ArrowLeft) {
            drawCharacter(playerM.getX(),playerM.getY(),terminal,' ',Terminal.Color.BLACK);
            playerM.changeDirection("West");
            playerM.moveForward();
          }
          else if (key.getKind() == Key.Kind.ArrowUp) {
            drawCharacter(playerM.getX(),playerM.getY(),terminal,' ',Terminal.Color.BLACK);
            playerM.changeDirection("North");
            playerM.moveForward();
          }
          else if (key.getKind() == Key.Kind.ArrowRight) {
            drawCharacter(playerM.getX(),playerM.getY(),terminal,' ',Terminal.Color.BLACK);
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
        monster1.changeDirection(getRandomDirection());
        //monster2.changeDirection(getRandomDirection());
        //monster3.changeDirection(getRandomDirection());
        //putString(1,3,terminal, "Game here...",Terminal.Color.WHITE,Terminal.Color.RED);
        //putString(3,5,terminal, "Time: "+timer,Terminal.Color.WHITE,Terminal.Color.RED);
        //benelux stuff
        //also how do you exactly pause the game?

      }else if (mode.equals("Inventory Mode")) {
        putString(1,3,terminal, "Press P to return");
        putString(1,3,terminal, ""); //put player inventory in here and somehow make it selectable?
      }else if (mode.equals("Pause Menu")) {
        putString(1,3,terminal, "Press Escape to Close");
        putString(1,4,terminal, "1. Inventory");
        putString(1,5,terminal, "Press P to return to the Game");
      }
      updating = false;
    }
  }
}
