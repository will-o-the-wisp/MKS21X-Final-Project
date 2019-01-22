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
		for (int i = 0; i<fr.getGrid().length;i++){
			for (int o =0; o<fr.getGrid()[i].length;o++){
				if (fr.getGrid()[i][o] == '_') {
					row += "   ";
				}
				else {
					row += " " + fr.getGrid()[i][o] + " ";
				}
				if (o == fr.getGrid()[i].length -1){
					row += "\n";
				}
			}
		}

		return row;
	}

	public static void drawMonster(Monster m, Floor ff){
		ff.setGridPos(m.getX(),m.getY(),'!');
	}

	public static void eraser(int x, int y, Floor ff, Creature e){
		if (e.onExit(ff.getExitX(),ff.getExitY())) {
			ff.setGridPos(x,y,'*');
		}
		else {
			ff.setGridPos(x,y,'.');
		}
	}

	public static void drawPlayer(Player p, Floor ff ){
		ff.setGridPos(p.getX(),p.getY(),'$');
	}

	public static void drawItem(Item i,Floor ff){
		ff.setGridPos(i.getX(),i.getY(),'!');
	}


	public static void statusOfPlayer(Player p, Terminal s) {
		String health =  "Health Points: " + p.getHP();
		String offense = "Attack Points: " + p.getATK();
		String defense = "Defense Points: " + p.getDEF();

		putString(0,40,s,health);
		putString(0,41,s,offense);
		putString(0,42,s,defense);
	}

	public static Floor createFloor(){
		ArrayList<Room> rs = new ArrayList<Room>();
    Random r = new Random();
    Floor f = new Floor(rs, 35, 20, r);
    f.addAllRooms();
    f.addEntrance();
    f.addExit();
		f.addAllPaths();
		return f;
	}

	public static Player createPlayer(Floor ff){
		Player playerM = new Player();
		playerM.setX(ff.getEntranceX());
		playerM.setY(ff.getEntranceY());
		playerM.changeDirection("North");
		return playerM;
	}

	public static ArrayList<Monster> createMonsters(Floor ff, int diff){
		ArrayList<Monster> mn =new ArrayList<Monster>();
		Random r = new Random();
		int counter = r.nextInt(10+diff);
		Monster dummy = new Monster("");
		for (int i = 0; i < counter; i++) {
			dummy.setSpecies("giraffe");
			dummy.setHP(r.nextInt(20+diff));
			dummy.setATK(r.nextInt(10+diff));
			dummy.setDEF(r.nextInt(10+diff));
			dummy.setX(ff.getExitX());
			dummy.setY(ff.getExitY());
			mn.add(dummy);
		}
		return mn;
	}
	public static void run() throws FileNotFoundException{
		int depth=0;
		Floor ffs = createFloor();
		Player playerM = createPlayer(ffs);
		ArrayList<Monster> hoard = createMonsters(ffs,depth);

		Terminal terminal = TerminalFacade.createTextTerminal();
		Screen s = new Screen(terminal);
		//terminal.enterPrivateMode();
		s.startScreen();
		s.setCursorPosition(null);
		terminal.setCursorVisible(false);

		boolean running = false;


	  String mode = "Start Menu";

		while (mode.equals("Start Menu") && running == false) {
			Key key = s.readInput();
			putString(1,3,terminal, "Start Menu \n Press the Corresponding Number \n -------- \n 1.Start Game \n 2. Exit Game");
				if (key != null){
					if (key.getCharacter() == '1') {
						mode = "Game Mode";
						terminal.clearScreen();
						running = true;
					}
					if (key.getCharacter() == '2') {
						terminal.exitPrivateMode();
						s.stopScreen();
						running = false;
					}
					if (key.getKind() == Key.Kind.Escape) {
						terminal.exitPrivateMode();
						s.stopScreen();
						running = false;
						}
					}
				}
			while(running){

	      Key key = s.readInput();


					drawPlayer(playerM,ffs);
					for (int i = 0; i < hoard.size(); i++) {
						drawMonster(hoard.get(i),ffs);
					}
					putString(0,0, terminal,drawFloor(ffs));
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
					/*
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
					*/
	        if (mode.equals("Game Mode")) {


						eraser(playerM.currentX(),playerM.currentY(),ffs,playerM);
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
	            playerM.moveForward(ffs);

	          }
	          else if (key.getKind() == Key.Kind.ArrowLeft) {

	            playerM.changeDirection("West");
	            playerM.moveForward(ffs);

	          }
	          else if (key.getKind() == Key.Kind.ArrowUp) {

	            playerM.changeDirection("North");
	            playerM.moveForward(ffs);

	          }
	          else if (key.getKind() == Key.Kind.ArrowRight) {

	            playerM.changeDirection("East");
	            playerM.moveForward(ffs);

	          }
	        }
	      }

	      if(mode.equals("Game Mode")){
					/*
					try	{
						Thread.sleep(1000);
					}
					catch(Exception ex){}
					*/
	        //DO GAME STUFF HERE

					drawPlayer(playerM,ffs);
					if (playerM.onExit(ffs.getExitX(),ffs.getExitY())){
						ffs = createFloor();
						playerM = createPlayer(ffs);
						hoard = createMonsters(ffs,depth);
						depth+=1;
					}
					for (int i = 0; i < hoard.size(); i++) {
						eraser(hoard.get(i).currentX(),hoard.get(i).currentY(),ffs,hoard.get(i));
						hoard.get(i).movement(ffs);
						drawMonster(hoard.get(i),ffs);
					}
					s.putString(0,0,drawFloor(ffs),Terminal.Color.DEFAULT,Terminal.Color.DEFAULT);
					s.refresh();

	      }else if (mode.equals("Inventory Mode")) {
	        s.putString(1,5, "Press P to return",Terminal.Color.DEFAULT,Terminal.Color.DEFAULT);
	        s.putString(1,7, "1. ",Terminal.Color.DEFAULT,Terminal.Color.DEFAULT);
					s.refresh();
	      }else if (mode.equals("Pause Menu")) {
	        s.putString(1,5, "Press Escape to Close",Terminal.Color.DEFAULT,Terminal.Color.DEFAULT);
	        //s.putString(1,6, "1. Inventory",Terminal.Color.DEFAULT,Terminal.Color.DEFAULT);
	        s.putString(1,7, "Press P to return to the Game",Terminal.Color.DEFAULT,Terminal.Color.DEFAULT);
					s.refresh();
	      }

				putString(0,35,terminal, mode);
				if(key!=null){putString(0,43,terminal, ""+key.getCharacter());}
				putString(0,36,terminal, "X " + playerM.getX() + " Y " + playerM.getY());
				putString(0,37,terminal, "direction" + playerM.directionTest());
				putString(0,38,terminal, "X " + ffs.getEntranceX() + " Y " + ffs.getEntranceY());
				putString(0,39,terminal, "" +playerM.lookInFront(ffs));

			//DO EVEN WHEN NO KEY PRESSED:

			statusOfPlayer(playerM,terminal);
		}
	}

	public static void main(String[] args) throws FileNotFoundException{
		run();
	}
}

//resize -s height getWidth

//#!/bin/bash
//run commands
