package telerik;
import java.util.LinkedList;

public class HighScoreBoard {		
	LinkedList<Player> list;				//A linked list to view the top 5 players
	public final int boardSize = 5;	//Size of the list
	public HighScoreBoard(){		//Constructor
		list = new LinkedList<Player>();
	}

	public boolean addPlayerToChart(Player player){	//A method to add a player to the board if h*s inside the top moves
		if(list.size()==0){			//An statement to check if the list is empty
			list.addFirst(player);	//IF so he/she gets to be first
			return true;
		}
		Player pl = (Player) list.get(list.size()-1);
		if((list.size()>0)&&(list.size()<boardSize)){				
			if(player.movesCount>pl.movesCount){		//A statement that adds the player if he/she has more moves than the ones in top list
				list.addLast(player);					//and adds them to a lower place than the ones they are comparing to
				return true;
			}
			int index = 0;
			while(index<list.size()){
				pl = (Player) list.get(index);
				if(player.movesCount<=pl.movesCount){
					list.add(index,player);
					return true;
				}
				index++;
			}
		}
		if((list.size()==boardSize)) {
			if((player.movesCount<pl.movesCount)){		//If the board is full, the statement will make sure to remove a player that has more moves, than the one that is coming in
				list.remove(list.size() - 1);
				int index = 0;
				while (index < list.size()) {
					pl = (Player) list.get(index);
					if (player.movesCount <= pl.movesCount) {
						list.add(index, player);
						return true;
					}
					index++;
				}
			}
		}
		return false;
	}
	void printBoard(LinkedList<Player> list){	//Method to print out the score board
		System.out.println("Score :");
		for(int i=0;i<list.size();i++){
			Player p = (Player) list.get(i);
			System.out.print((i+1)+". Name - "+p.name+" " + "Escaped in "+p.movesCount+" moves\n");
		}
	}
}
