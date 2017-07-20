package Repo;

import POJO.Game;
import POJO.Player;

public class PlayerRepo implements IPlayerRepo {
	private Player[] players;
	
	public PlayerRepo() {
	}
	
	public PlayerRepo(Player[] players) {
		this.players = players;
	}

	public String save(Player p) {
		Player[] players = this.getPlayers();
		int playerSize = players.length;

		if (players[--playerSize] != null) {

			int newPlayerSize = players.length;
			++newPlayerSize;
			Player[] newPlayers = new Player[newPlayerSize];

			for (int t = 0; t < players.length; t++) {
				//
				//System.out.println(p.getName());
				//System.out.println("Sorting new array " + t);
				newPlayers[t] = players[t];
			}
			//
			newPlayers[--newPlayerSize] = p;
			this.setPlayers(newPlayers);
			/*for (Player playerTry : playerRepo.getPlayers()) {
				System.out.println("Testing game presence");
				System.out.println(playerTry.getName());
			}*/

			return "success";
		} else {
			//System.out.println("Entered first if");
			for (int i = 0; i < players.length; i++) {
				if (players[i] != null) {
					//System.out.println("Hit games[i] not null " + i);
					continue;
				} else {
					//System.out.println("Hit games[i] null " + i);
					//
					players[i] = p;
					return "success";
				}
			}
		}
		return "success";
	}
	
	public Player findOne(String name) {
		for(int i=0; i<players.length; i++) {
			if(players[i] != null) {
				if(players[i].getName().equals(name)) {
					//System.out.println("came here");
					Game[] games = new Game[5];
					return new Player("repeat", games);
				}
			}
		}
		
		Game[] games2 = new Game[5];
		return new Player("new", games2);
	}
	public Player[] findAll() {
		return players;
	}

	public Player[] getPlayers() {
		return players;
	}

	public void setPlayers(Player[] players) {
		this.players = players;
	}
	
}
