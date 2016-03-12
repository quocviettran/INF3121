package telerik;
public class Player {		//This class is only for the highscore board, where the programs enter the amount of moves made by the player after you've won.
	String name;			//Variable for the player's name
	int movesCount;			//Variable for amount of moves made when playing that round of a game.
	public Player(String name, int movesCount){	//Constructor for the class player, taking in parameters as string and int
		this.name = name;
		this.movesCount = movesCount;
	}
}
