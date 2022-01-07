
# 2PlayerChessGame_LLD 

This is a Low level design of Chess game which supports 2 player at a time.

### Requirements 🔧
* Java version 8 or higher.

### Installation 🔌
- 1. Press the **Fork** button (top right the page) to save copy of this project on your account.

- 2. Download the repository files (project) from the download section or clone this project by typing in the bash the following command:

       git clone https://github.com/g-shriram/2PlayerChess.git
- 3. Imported it in Eclipse or any other Java IDE.
- 4. Run the application :D

This JAVA project is completely developed on eclipse  
```bash
Version: 2021-06 (4.20.0)
Build id: 20210612-2011
```
It can also be run on command promt provided proper configuration of class path.
## Deployment

To deploy this project run "GameManager.class" on eclipse. 
Or

```bash
  javac GameManager.java
  java GameManager
```
on command promt.


## How to play ?

- First line of input is number of game you want to play.
- This game supports two mode
  - Real time using console
  - Using file with stored moves 
    - For input file format refer :[Sample input file](https://github.com/g-shriram/2PlayerChess/blob/e2c7556703299d83aeb1ad8145744adf8f944a52/moves.txt)

- For each game enter details like 
  - Input mode (1 - for 'console' and 2 - for 'from file')
  - Path to store scoresheet (Recording of Moves)
  - Details of player 1
  - Details of player 2
  - From now game starts...
    - It asks for moves (Player1 & Player 2 alternatively).
    - Once source coin given it returns all possible moves and possible coins that can be captured.
    - Once destination is entered the game board will update if it is a valid move.
    - In addition to coin position you can enter "print" and "exit" to print current instance of board and to exit from the game.
    - Then the cycle repeats with another player...

- After every move the score sheet(Recorded file) will be updated and stored as text file in the given path. 


## Demo

-  [For Game using console with Em-passant move](https://github.com/g-shriram/2PlayerChess/blob/546a58b174e10ff5940f7cfa6f78c272e2434668/Demo%20Videos/RealTime%20wih%20Empassant.mp4)
-  [For Game using StoredMove in file with Castling move](https://github.com/g-shriram/2PlayerChess/blob/546a58b174e10ff5940f7cfa6f78c272e2434668/Demo%20Videos/StoredMove%20with%20Casstling.mp4)
-  [For input file format](https://github.com/g-shriram/2PlayerChess/blob/546a58b174e10ff5940f7cfa6f78c272e2434668/Demo%20Videos/InputFile.mp4)
-  [For output file description](https://github.com/g-shriram/2PlayerChess/blob/546a58b174e10ff5940f7cfa6f78c272e2434668/Demo%20Videos/OutputFile.mp4)



## Special Moves Implemented

- Castling 
- Pawn promotion on reaching 8th rank
- En passant

## Special Constraint

- While finding possible moves for King Check Constraint (Results in check) is Implemented
- After every move I have checked whether the move cause check to opponent.
- Player can move only his/her coins.
- Pawn directions are fixed with their colors.
## Author
👤 **Shriram Gnanaprakasam**
- Github:  [@g_shriram](https://github.com/g-shriram)

