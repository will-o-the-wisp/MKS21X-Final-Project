# MKS21X-FinalProject
--------------------------------------------------------------------------------
## Some sort of rogue-like game

An attempt to make a rogue-like using java and terminal. <br/>
Supposed model: https://en.wikipedia.org/wiki/NetHack   <br/>

Bare Bones? Random array generator that has wall detection

Features:
  -Movement
  -Random generation of rooms and Floors
  -Random paths
  -Monsters
  -Player
  -Items
  -Progression

## Setting up the GAME
#### Assuming it's a Linux machine:

Pull or download the repository.

In the terminal, navigate to the git repository of this project.

Run by copy+paste:
chmod +x help.sh  <br/>
./help.sh  <br/>

Compile using: javac -cp lanterna.jar:. Benelux.java

Run the game using: java -cp lanterna.jar:. Benelux

Enjoy?

#### Windows

Essentially the same as Linux, except that lanterna.jar;. needs to be in quotes

#### Macs

I have no idea what this machine is.

## Playing the GAME
Arrow Keys are used for cardinal direction movement <br/>
Insert Button Here used for attack                  <br/>
P is used for Pause                                 <br/>
Escape can be used at any point to leave the game   <br/>
Other buttons are shown in the menus

--------------------------------------------------------------------------------
Development Diary Entries-

01/03/19|

Brandon C.

  Implemented the skeletons for all the initial classes of entity we made up,
  just so that it wouldn't be necessary to look at UML diagram just for what we
  thought of initially.

Kenson Li

  Implemented the skeletons for Room and Floor, started working on drawRoom

01/04/19

Brandon C.

  Some discussion in class about issues.

1/7/19

Kenson Li

  completed drawRoom, reorganizing code of the layout

Brandon C.

  Attempted start to menu, various constructors and gets/sets for entity classes

1/8/19

Kenson Li

  Floors can now be filled with Rooms which contain data that corresponds to that of Floor, which will be able to be sent to the terminal

1/9/19

Kenson Li

  added Floor entrances and exits, started randomization of Floors and Rooms

1/10/19

BC

  various touch ups to entity classes such as making items not have "alive" boolean, adding txt files to be read from, atk + def calcs

1/11/19

BC

  Test Day oops

1/13/19

BC

  inputs for terminal layout, need touch ups to various modes, movement and placement of an entity is okay

1/14/19

BC

  fix for wall detection, player starts at entrance, no longer flickers due to floor being drawn once, still lags for some reason probably need better screen work, also added get methods for exit and entrance

1/15/19

BC

  Failed to find a reasonable fix for Screen

1/16/19

BC

  More attempts at fixing Screen but failed.

1/17/19

BC

  Tried making the screen only write one string for floor where the array is one string, still failed to produce anything meaningful.

1/18/19

BC

  Reverts and fails, no progress and frustration. Considering giving up on screen or is it the coordinate system I'm missing.

1/19/19

BC

  Out all day for various things that shouldn't go public for safety reasons.

1/20/19

BC

  Fever day, woozy after effects. Couldn't really hold head up well to do much.

1/21/19

BC

  Screen works (after adding "screen.startScreen()") 😠, except that the 2nd row of the array gets duplicated, honestly not even sure where that comes from. Basic functionality, so not sure how the game is going to be. No one to blame except myself really, also I am incredibly dumb.
