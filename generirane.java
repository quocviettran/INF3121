package telerik;

import java.util.Random;
import java.util.Scanner;

public class generirane {
	public boolean isVisited[][] = new boolean[7][7]; //A variable to check if player has been at this row/column
	public char maze[][] = new char[7][7];	//The maze
	public int playersCurrentRow;	//An variable to check which row the player is standing in
	public int playersCurrentColumn;	//An variable to check which column the player is standing in
	public String command;			
	public boolean isExit = false;
	public int playersMovesCount = 0;
	HighScoreBoard board = new HighScoreBoard();
	Random randomgenerator = new Random();

	void generateMaze(int row, int column){
		if(randomgenerator.nextInt(2)==1){
			maze[row][column] = 'X';
		}
		else {
			maze[row][column] = '-';
		}
	}

	void initializeMaze(){		
		// Generates a new maze until at least one solution is found
		do{
			for(int row=0;row<7;row++){
				for(int column=0;column<7;column++){
					isVisited[row][column]=false;
					generateMaze(row,column);
				}
			}
		}while(isSolvable(3, 3)==false);	//Player start point cannot contain an X block
		playersCurrentRow = 3;
		playersCurrentColumn = 3;

		maze[playersCurrentRow][playersCurrentColumn] = '*';
		printMaze();
	}	
	public void initializeScoreBoard(){
		board = new HighScoreBoard();
	}
	public void rowLeft(int row, int col){
		if((isVisited[row-1][col]==false)) {
			isVisited[row][col] = true;
			isSolvable(row - 1, col);
		}
	}

	public void rowRight(int row, int col){
		if((isVisited[row+1][col]==false)){
			isVisited[row][col]=true;
			isSolvable(row+1, col);
		}
	}
	public void colDown(int row, int col){
		if((isVisited[row][col-1]==false)) {
			isVisited[row][col] = true;
			isSolvable(row, col - 1);
		}
	}
	public void colUp(int row, int col){
		if((isVisited[row][col+1]==false)) {
			isVisited[row][col] = true;
			isSolvable(row, col + 1);
		}
	}

	public boolean isSolvable(int row, int col){
		if((row==6)||(col==6)||(row==0)||(col==0)){
			isExit = true;
			return isExit;
		}
		if((maze[row-1][col]=='-')){
			rowLeft(row,col);
		}
		if((maze[row+1][col]=='-')){
			rowRight(row,col);
		}
		if((maze[row][col-1]=='-')){
			colDown(row,col);
		}
		if((maze[row][col+1]=='-')){
			colUp(row,col);
		}
		return isExit;
	}
	void printMaze(){
		for(int row=0;row<7;row++){
			for(int column=0;column<7;column++){
				System.out.print(maze[row][column]+" ");
			}
			System.out.println();
		}
	}	
	public void inputCommand(){						//A method to recieve commands through the terminal
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter Help for more commands");
		System.out.println("Enter your next move : L(left), " +	"R(right), U(up), D(down) ");
		command = scanner.next();
		int size = command.length();
		if (command.equalsIgnoreCase("exit")) {	//Entering exit will exit the program to run/Break the endless loop
			System.out.println("Good bye!");
			System.exit(0);
		}
		else if(command.equalsIgnoreCase("help")){	//Giving out the help instructions
			commandHelp();
			printMaze();
		}
		else if(command.equalsIgnoreCase("restart")){ //Finding a new maze
			isExit = false;
			initializeMaze();
		}
		else if(command.equalsIgnoreCase("top")){	//Viewing the high score board
			if(board.list.size()>0){
				board.printBoard(board.list);
				printMaze();
			}
			else{
				System.out.println("The High score board is empty!");	//If the board does not have any players in it yet, a message will print out instead
				printMaze();
			}
		}
		else if(size>1){
			System.out.println("Invalid command!");
		}
		else {
			movePlayer(command.substring(0,1));
		}
	}

	public void moveLeft(){ //Method for moving left
		if (maze[playersCurrentRow][playersCurrentColumn - 1] != 'X') {
			swapCells(playersCurrentRow, playersCurrentRow,playersCurrentColumn, playersCurrentColumn - 1);
			playersCurrentColumn--;
			playersMovesCount++;
			System.out.println();
		} else {
			System.out.println("Invalid move!");
			printMaze();
		}
	}

	public void moveRight(){	//Method for moving right
		if (maze[playersCurrentRow][playersCurrentColumn + 1] != 'X') {
			swapCells(playersCurrentRow, playersCurrentRow,playersCurrentColumn, playersCurrentColumn + 1);
			playersCurrentColumn++;
			playersMovesCount++;
			System.out.println();
		} else {
			System.out.println("Invalid move!");
			printMaze();
		}
	}

	public void moveUp(){		//Method for moving upwards
		if (maze[playersCurrentRow - 1][playersCurrentColumn] != 'X') {
			swapCells(playersCurrentRow, playersCurrentRow - 1,playersCurrentColumn, playersCurrentColumn);
			playersCurrentRow--;
			playersMovesCount++;
			System.out.println();
		} else {
			System.out.println("Invalid move!");
			printMaze();
		}
	}

	public void moveDown(){		//Method for moving downwards
		if (maze[playersCurrentRow + 1][playersCurrentColumn] != 'X') {
			swapCells(playersCurrentRow, playersCurrentRow + 1,playersCurrentColumn, playersCurrentColumn);
			playersCurrentRow++;
			playersMovesCount++;
			System.out.println();
		} else {
			System.out.println("Invalid move!");
			printMaze();
		}
	}

	public  void movePlayer(String firstLetter){
		if (firstLetter.equalsIgnoreCase("L")) {	//Moving player to the Left
			moveLeft();
		} else if (firstLetter.equalsIgnoreCase("R")) { //Moving player to the Right
			moveRight();
		} else if (firstLetter.equalsIgnoreCase("U")) {	//Moving player Upwards
			moveUp();
		} else if (firstLetter.equalsIgnoreCase("D")) {	//Moving player Down
			moveDown();
		} else {
			System.out.println("Invalid command!");
		}
	}

	void swapCells(int currentRow, int newRow, int currentColumn, int newColumn){	//A method to move the character between cells

		char previousCell = maze[currentRow][currentColumn];
		maze[currentRow][currentColumn] = maze[newRow][newColumn];
		maze[newRow][newColumn] = previousCell;
		System.out.println();
		printMaze();		
	}
	void commandHelp(){	//A command to show the commands
		System.out.println("Enter one of the following commands 'L,R,D,U' to move your player");
		System.out.println("Enter 'Restart' to remake maze");
		System.out.println("Enter 'Exit' to quit the program");
		System.out.println("Enter 'Top' to view score board\n");
	}
	public void endlessLoop(){	//Method that makes an endless input command until the player is on the edge
		while((playersCurrentColumn!=0) && (playersCurrentColumn!=6)
				&&(playersCurrentRow!=0)&&(playersCurrentRow!=6)){
			inputCommand(); //Method that ask for which command to input	
		}
	}

}