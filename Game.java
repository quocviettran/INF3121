package telerik;

import java.util.Scanner;
/*
 * The main class that runs the whole game
 * This file is the one that should be ran through command prompt or other IDE
 */


public class Game {
	public static void main(String[] args){
		generirane labyrinth = new generirane();
		labyrinth.initializeScoreBoard();
		int reliabilityCounter = 0;
		while(true){		//A loop that never ends the program
			System.out.println("Program has played: "+ reliabilityCounter + " times");
			labyrinth.initializeMaze();		//Calling for the method that creates the maze
			labyrinth.endlessLoop();		//A method that will make this game go in a endless loop
			labyrinth.printMaze();			//Printing out the maze
			System.out.println("Congratulations! You escaped in "+labyrinth.playersMovesCount+" moves."); //Printing out the ending message, aka congratulation message when you've won the game			
			
			Scanner scanner = new Scanner(System.in);	//A scanner to recieve input
			System.out.println("Enter your name : ");	
			String name = scanner.next();				//Scans in an input that is used for the players name
			Player player = new Player(name,labyrinth.playersMovesCount); //Creating a player to add to the score board
			addTop(labyrinth,player);				//A static method that adds players to the high score board
			labyrinth.isExit=false;					//After you've added a player to the board, the game resets
			labyrinth.playersCurrentColumn = 3;		//The player will reset to column 3 and row 3
			labyrinth.playersCurrentRow = 3;		//And the moves count resets to zero
			labyrinth.playersMovesCount = 0;
			reliabilityCounter++;			
		}
	}
	static void addTop(generirane labyrinth, Player player){ 	//A method to use the method within board, to add players to the high score
		if(labyrinth.board.addPlayerToChart(player)==true){
			System.out.println("Your score is in top 5!");
			labyrinth.board.printBoard(labyrinth.board.list);	//Printing out the high score table
		}
	}
}
