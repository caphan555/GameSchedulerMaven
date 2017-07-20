package repo;

import pojo.Game;

public class GameRepo implements IGameRepo {
	private Game[] games;

	public GameRepo() {
	}

	public GameRepo(Game[] games) {
		this.games = games;
	}

	public String save(Game g) {
		Game[] games = this.getGames();
		int gameSize = games.length;

		if (games[--gameSize] != null) {

			int newGameSize = games.length;
			++newGameSize;
			Game[] newGames = new Game[newGameSize];

			for (int t = 0; t < games.length; t++) {
				newGames[t] = games[t];
			}
			newGames[--newGameSize] = g;
			this.setGames(newGames);

			return "success";
		} else {
			for (int i = 0; i < games.length; i++) {
				if (games[i] != null) {
					continue;
				} else {
					games[i] = g;
					return "success";
				}
			}
		}
		return "success";
	
	}
	
	public Game findOne(String name) {
		
		for(int i=0; i<games.length; i++) {
			if(games[i] != null) {
				if(games[i].getName().equals(name)) {
					return new Game("repeat", 1);
				}
			}
		}

		return new Game("new", 2);
	}

	public Game[] findAll() {
		return games;
	}

	public Game[] getGames() {
		return games;
	}

	public void setGames(Game[] games) {
		this.games = games;
	}

}
