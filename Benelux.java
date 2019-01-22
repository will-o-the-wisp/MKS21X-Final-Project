import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

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
import com.googlecode.lanterna.screen.*;
import com.googlecode.lanterna.*;


public class Benelux{

	public static void putString(int r, int c,Terminal t, String str){
		t.moveCursor(r,c);
		for(int i = 0; i < str.length();i++){
			t.putCharacter(str.charAt(i));
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
	public static String drawFloor(Floor fr){
		String row = "";
		for (int i = 0; i<fr.getGrid().length-1;i++){
			for (int o =0; o<fr.getGrid()[i].length-1;o++){
				row += " " + fr.getGrid()[i][o] + " ";
				if (o == fr.getGrid()[i].length -2){
					row += i + "\n";
				}
			}
		}
		return row;
	}
	/*
	public static void drawMonster(Monster m){
		int x = m.getX();
		int y = m.getY();
		s.putString(x,y,terminal," ! ");
	}
	*/

	public static void eraser(int x, int y, Floor ff){
		ff.setGridPos(x,y,'.');
	}

	public static void drawPlayer(Player p, Floor ff ){
		ff.setGridPos(p.getX(),p.getY(),'$');
	}
/*
	public static void drawItem(Item i){
		int x = i.getX();
		int y = i.getY();
		s.putString(x,y,terminal, " ? ");
	}
*/

	public static void statusOfPlayer(Player p, Terminal s) {
		String health =  "Health Points: " + p.getHP();
		String offense = "Attack Points: " + p.getATK();
		String defense = "Defense Points: " + p.getDEF();

		putString(0,40,s,health);
		putString(0,41,s,offense);
		putString(0,42,s,defense);
	}
	public static void main(String[] args) throws FileNotFoundException{

		ArrayList<Room> rs = new ArrayList<Room>();
    Random r = new Random();
    Floor f = new Floor(rs, 35, 20, r);
    f.addAllRooms();
    f.addEntrance();
    f.addExit();
		f.addAllPaths();

		ArrayList<Monster> mn =new ArrayList<Monster>();
		int numbers = r.nextInt(10);

		Player playerM = new Player();
		playerM.setX(f.getEntranceX());
		playerM.setY(f.getEntranceY());
		playerM.changeDirection("North");
		drawPlayer(playerM,f);

		Terminal terminal = TerminalFacade.createTextTerminal();
		Screen s = new Screen(terminal);
		//terminal.enterPrivateMode();
		s.startScreen();
		s.setCursorPosition(null);

		boolean running = true;

		int depth=0;
	  String mode = "Game Mode";


		//putString(playerM.getX(),playerM.getY(), terminal,drawPlayer(playerM));

	    //while(millis/1000 != lastSecond + 1){ //check for one second?
			while(running){
				putString(0,0, terminal,drawFloor(f));
				int currentX = playerM.getX();
				int currentY = playerM.getY();
	      Key key = s.readInput();
				/*
				while (mode.equals("Start Menu") && running == true) {
		      putString(1,3,terminal, "Start Menu \n Press the Corresponding Number \n -------- \n 1.Start Game \n 2. Exit Game");
		        if (key != null){
		          if (key.getCharacter() == '1') {
		            mode = "Game Mode";
								terminal.clearScreen();
		          }
		          if (key.getCharacter() == '2') {
		            terminal.exitPrivateMode();
		            running = false;
		          }
							if (key.getKind() == Key.Kind.Escape) {
								terminal.exitPrivateMode();
								running = false;
							}
		        }
		      }
				*/
	      if (key != null){
	        //YOU CAN PUT DIFFERENT SETS OF BUTTONS FOR DIFFERENT MODES!!!

	        //pause mode
	        if(mode.equals("Pause Menu")){
	          if (key.getKind() == Key.Kind.Escape) {
							s.stopScreen();
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
						eraser(currentX,currentY,f);
	          if (key.getKind() == Key.Kind.Escape) {
							s.stopScreen();
	            terminal.exitPrivateMode();
	            running = false;
	          }
	          if (key.getCharacter() == 'p'){
	            mode = "Pause Menu";
							terminal.clearScreen();
	          }
	          if (key.getKind() == Key.Kind.ArrowDown) {

	            playerM.changeDirection("South");
	            playerM.moveForward(f);

	          }
	          else if (key.getKind() == Key.Kind.ArrowLeft) {

	            playerM.changeDirection("West");
	            playerM.moveForward(f);

	          }
	          else if (key.getKind() == Key.Kind.ArrowUp) {

	            playerM.changeDirection("North");
	            playerM.moveForward(f);

	          }
	          else if (key.getKind() == Key.Kind.ArrowRight) {

	            playerM.changeDirection("East");
	            playerM.moveForward(f);

	          }
	        }
	      }

	      if(mode.equals("Game Mode")){

	        //DO GAME STUFF HERE

					drawPlayer(playerM,f);
					s.putString(0,0,drawFloor(f),Terminal.Color.DEFAULT,Terminal.Color.DEFAULT);
					s.refresh();

	      }else if (mode.equals("Inventory Mode")) {
	        s.putString(1,5, "Press P to return",Terminal.Color.DEFAULT,Terminal.Color.DEFAULT);
	        s.putString(1,7, "1. ",Terminal.Color.DEFAULT,Terminal.Color.DEFAULT);
					s.refresh();
	      }else if (mode.equals("Pause Menu")) {
	        s.putString(1,5, "Press Escape to Close",Terminal.Color.DEFAULT,Terminal.Color.DEFAULT);
	        s.putString(1,6, "1. Inventory",Terminal.Color.DEFAULT,Terminal.Color.DEFAULT);
	        s.putString(1,7, "Press P to return to the Game",Terminal.Color.DEFAULT,Terminal.Color.DEFAULT);
					s.refresh();
	      }

				putString(35,0,terminal, mode);
				if(key!=null){putString(35,2,terminal, ""+key.getCharacter());}
				putString(35,3,terminal, "X " + playerM.getX() + " Y " + playerM.getY());
				putString(35,4,terminal, "direction" + playerM.directionTest());
				putString(35,5,terminal, "X " + f.getEntranceX() + " Y " + f.getEntranceY());
				putString(35,6,terminal, "" +playerM.lookInFront(f));
			//DO EVEN WHEN NO KEY PRESSED:

			statusOfPlayer(playerM,terminal);
		}
	}
}

//resize -s height getWidth

//#!/bin/bash
//run commands
