package service;

import pojo.Day;
import pojo.Game;
import pojo.Player;
import repo.DayRepo;
import repo.GameRepo;
import repo.PlayerRepo;

public class SchedulerService implements ISchedulerService {
	private GameRepo gameRepo;
	private PlayerRepo playerRepo;
	private DayRepo dayRepo;
	public static final String failure = "failure";

	public SchedulerService() {
	}

	public SchedulerService(GameRepo gameRepo, PlayerRepo playerRepo, DayRepo dayRepo) {
		this.gameRepo = gameRepo;
		this.playerRepo = playerRepo;
		this.dayRepo = dayRepo;
	}

	public String createGame(Game g) {
		Game game = gameRepo.findOne(g.getName());
		if (g.getName() == null) {
			return "A null game cannot be created!";
		} else if (g.getName().equals("")) {
			return "Game name cannot be an empty String!";
		} else if (g.getNoOfPlayers() == 0) {
			return "Number of players cannot be zero!";
		}
		if (!(game.getName().equals("repeat"))) {
			return gameRepo.save(g);
		} else {
			return failure;
		}

	}

	public String createPlayer(Player p) {

		if (p.getName() == null) {
			return "A null player cannot be created";
		} else if (p.getName().equals("")) {
			return "Player name cannot be empty!";
		} else if (playerRepo.findOne(p.getName()).getName().equals("repeat")) {
			return "Player has already been added to the system!";
		}

		Game[] games = p.getGames();
		Game[] addedGames = gameRepo.getGames();
		int presenceOfGame = 0;

		for (int a = 0; a < games.length; a++) {
			for (int j = 0; j < addedGames.length; j++) {
				if (games[a].getName().equals(addedGames[j].getName())) {
					presenceOfGame = 1;
				}
			}
		}

		if (presenceOfGame == 0) {
			return "Player does not play any of the games added in the system!";
		}

		if (!(p.getName().equals("repeat"))) {
			return playerRepo.save(p);
		} else {
			return failure;
		}
	}

	public String createDay(Day d) {

		if (d.getName() == null) {
			return "A null day cannot be created";
		} else if (d.getName().equals("")) {
			return "Day name cannot be empty!";
		} else if (dayRepo.findOne(d.getName()).getName().equals("repeat")) {
			return "Day has already been added to the system!";
		}
		Game[] games = d.getGames();
		Game[] addedGames = gameRepo.getGames();
		int presenceOfGame = 0;

		for (int a = 0; a < games.length; a++) {
			for (int j = 0; j < addedGames.length; j++) {
				if (games[a].getName().equals(addedGames[j].getName())) {
					presenceOfGame = 1;
				}
			}
		}

		if (presenceOfGame == 0) {
			return "Games in added day do not exist in the system!";
		}

		if (!(d.getName().equals("repeat"))) {
			return dayRepo.save(d);
		} else {
			return failure;
		}
	}

	public StringBuffer gameWiseReport(String gameName) {

		int presenceOfGame = 0;
		StringBuffer sb = new StringBuffer();

		if (gameName.equals("")) {
			return sb.append("Game Name provided cannot be an empty String!");
		}

		for (int t = 0; t < dayRepo.getDays().length; t++) {
			Day[] testDays = dayRepo.getDays();
			Game[] testGames = testDays[t].getGames();

			for (int a = 0; a < testGames.length; a++) {

				if (testGames[a].getName().equals(gameName)) {
					presenceOfGame = 1;
				}
			}
		}

		if (presenceOfGame == 0) {
			return sb.append("Game does not exist in System!");
		}

		sb.append("Players playing ");
		sb.append(gameName);
		sb.append(":\n");
		for (int i = 0; i < playerRepo.getPlayers().length; i++) {
			Player[] players = playerRepo.getPlayers();
			for (int t = 0; t < players[i].getGames().length; t++) {
				Game[] games = players[i].getGames();

				if (games[t].getName().equals(gameName)) {
					sb.append(players[i].getName());
					sb.append("  ");
				}
			}
		}
		sb.append("\n");
		sb.append("Days on which ");
		sb.append(gameName);
		sb.append(" is played:\n");

		for (int i = 0; i < dayRepo.getDays().length; i++) {
			Day[] days = dayRepo.getDays();
			for (int t = 0; t < days[i].getGames().length; t++) {
				Game[] games = days[i].getGames();

				if (games[t].getName().equals(gameName)) {
					sb.append(days[i].getName());
					sb.append("  ");
				}
			}
		}

		sb.append("\n");

		return sb;
	}

	public StringBuffer playerWiseReport(String playerName) {

		StringBuffer sb = new StringBuffer();
		
		if(playerName.equals("")) {
			return sb.append("Player Name provided cannot be an empty String!");
		}
		
		int presenceOfPlayer = 0;
		Player[] playersTest = playerRepo.getPlayers();
		for(int e=0; e<playersTest.length; e++) {
			
			if(playersTest[e].getName().equals(playerName)) {
				presenceOfPlayer = 1;
				break;
			}
		}
		
		if(presenceOfPlayer == 0) {
			return sb.append("Player does not exist in the System!");
		}
		

		Player[] players = playerRepo.getPlayers();
		sb.append("Games which ");
		sb.append(playerName);
		sb.append(" plays:\n");
		for (int i = 0; i < players.length; i++) {

			if (playerName.equals(players[i].getName())) {
				Game[] games = players[i].getGames();
				for (int t = 0; t < games.length; t++) {
					sb.append(games[t].getName());
					sb.append("(  ");

					Day[] days = dayRepo.getDays();
					for (int a = 0; a < days.length; a++) {
						Game[] dayGames = days[a].getGames();

						for (int r = 0; r < dayGames.length; r++) {
							if (dayGames[r].getName().equals(games[t].getName())) {
								sb.append(days[a].getName());
								sb.append("  ");
							}
						}
					}
					sb.append("  )  ");
				}
				
			}
		}

		sb.append("\n");

		return sb;
	}

	public StringBuffer dayWiseReport(String dayName) {
		
		StringBuffer sb = new StringBuffer();
		int presenceOfDays = 0;
		Day[] days = dayRepo.getDays();
		
		if(dayName.equals("")) {
			return sb.append("Day Name provided cannot be an empty String!");
		}
		
		for(int f=0; f<days.length; f++) {
			if(days[f].getName().equals(dayName)) {
				presenceOfDays = 1;
				break;
			}
		}
		
		if(presenceOfDays == 0) {
			return sb.append("Day given does not exist in System!");
		}
		
		sb.append("Games scheduled on ");
		sb.append(dayName);
		sb.append(":\n");
		
		for(int i=0; i<days.length; i++) {
			if(days[i].getName().equals(dayName)) {
				
				Game[] games = days[i].getGames();
				
				for(int t=0; t<games.length; ++t) {
					sb.append(games[t].getName());
					sb.append("(  ");
					
					Player[] players = playerRepo.getPlayers();
					for(int a=0; a<players.length; a++) {
						Game[] eachPlayerGames = players[a].getGames();
						
					
						for(int r=0; r<eachPlayerGames.length; r++) {
							if(eachPlayerGames[r].getName().equals(games[t].getName())) {
								sb.append(players[a].getName());
								sb.append("  ");
							}
						}
						
					}
					sb.append(")  ");
				}
			}
		}
		
		sb.append("\n");
		
		return sb;
	}

	public GameRepo getGameRepo() {
		return gameRepo;
	}

	public void setGameRepo(GameRepo gameRepo) {
		this.gameRepo = gameRepo;
	}

	public PlayerRepo getPlayerRepo() {
		return playerRepo;
	}

	public void setPlayerRepo(PlayerRepo playerRepo) {
		this.playerRepo = playerRepo;
	}

	public DayRepo getDayRepo() {
		return dayRepo;
	}

	public void setDayRepo(DayRepo dayRepo) {
		this.dayRepo = dayRepo;
	}

}
