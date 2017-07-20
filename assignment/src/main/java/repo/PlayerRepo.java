package repo;

import pojo.Game;
import pojo.Player;

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
				newPlayers[t] = players[t];
			}
			newPlayers[--newPlayerSize] = p;
			this.setPlayers(newPlayers);
	
			return "success";
		} else {
			for (int i = 0; i < players.length; i++) {
				if (players[i] != null) {
					continue;
				} else {
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
