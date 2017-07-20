package Repo;

import POJO.Game;

public class GameRepo implements IGameRepo {
	private Game[] games;

	public GameRepo() {
	}

	public GameRepo(Game[] games) {
		this.games = games;
	}

	public String save(Game g) {
		//Game[] games = gameRepo.getGames();
		Game[] games = this.getGames();
		int gameSize = games.length;

		//System.out.println("Game size: "+gameSize);
		if (games[--gameSize] != null) {

			int newGameSize = games.length;
			++newGameSize;
			Game[] newGames = new Game[newGameSize];

			for (int t = 0; t < games.length; t++) {
				//
				//System.out.println(g.getName());
				//System.out.println("Sorting new array " + t);
				newGames[t] = games[t];
			}
			//
			newGames[--newGameSize] = g;
			this.setGames(newGames);
			/*for (Game gameTry : this.getGames()) {
				System.out.println("Testing game presence");
				System.out.println(gameTry.getName());
			}*/

			return "success";
		} else {
			//System.out.println("Entered first if");
			for (int i = 0; i < games.length; i++) {
				if (games[i] != null) {
					//System.out.println("Hit games[i] not null " + i);
					continue;
				} else {
					//System.out.println("Hit games[i] null " + i);
					
					//
					games[i] = g;
					//System.out.println("game passed in: "+g.getName());
					//System.out.println("game "+i+": "+games[i].getName());
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
					//System.out.println("came here");
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
