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

  public static void putString(int r, int c,Terminal t, String s){
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
  public static void main(String[] args) {

    Terminal terminal = TerminalFacade.createTextTerminal();
    terminal.enterPrivateMode();

    TerminalSize size = terminal.getTerminalSize();
    terminal.setCursorVisible(false);

    boolean running = true;
    String mode = "Start Menu";
    long lastTime =  System.currentTimeMillis();
    long currentTime = lastTime;
    long timer = 0;


    while(running){
      Key key = terminal.readInput();
      while (mode.equals("Start Menu")) {
        putString(1,3,terminal, "Start Menu \n -------- \n ");
      }
      if (key != null)
      {

        //YOU CAN PUT DIFFERENT SETS OF BUTTONS FOR DIFFERENT MODES!!!

        //only for the game mode.
        if(mode.equals("Pause Menu")){
          putString(1,3,terminal, "Press Escape to Close");
          putString(1,4,terminal, "Inventory");
          if (key.getKind() == Key.Kind.Escape) {
            terminal.exitPrivateMode();
            running = false;
          }
          if (key.getKind() == Key.Kind.UpArrow) {
            t.moveCursor();
          }
        }

        //for all modes
        if (key.getCharacter() == 'P' && mode.equals("Game Mode")) {
          mode = "Pause Menu";

          terminal.clearScreen();
          lastTime = System.currentTimeMillis();
          currentTime = System.currentTimeMillis();
        }

      }


      if(mode.equals("Game Mode")){
        lastTime = currentTime;
        currentTime = System.currentTimeMillis();
        timer += (currentTime -lastTime);//add the amount of time since the last frame.
        //DO GAME STUFF HERE
        putString(1,3,terminal, "Game here...",Terminal.Color.WHITE,Terminal.Color.RED);
        putString(3,5,terminal, "Time: "+timer,Terminal.Color.WHITE,Terminal.Color.RED);

      }else{

        terminal.applySGR(Terminal.SGR.ENTER_BOLD,Terminal.SGR.ENTER_BLINK);
        putString(1,3,terminal, "Not game, just a pause!",Terminal.Color.RED,Terminal.Color.WHITE);
        terminal.applySGR(Terminal.SGR.RESET_ALL);

      }

    }


  }
}
