package telerik;
public class Player {		//This class is only for the highscore board, where the programs enter the amount of moves made by the player after you've won.
	String name;			
	int movesCount;			
	public Player(String name, int movesCount){	
		this.name = name;
		this.movesCount = movesCount;
	}
}
