package Testing;

import static org.junit.Assert.*;

import org.junit.Test;

import POJO.Day;
import POJO.Game;
import POJO.Player;
import Repo.DayRepo;
import Repo.GameRepo;
import Repo.PlayerRepo;
import Service.SchedulerService;

public class SchedulerTest {

	@Test
	public void test_createGame_success() {
		SchedulerService ss = new SchedulerService(new GameRepo(new Game[5]), new PlayerRepo(new Player[5]), new DayRepo(new Day[5]));
		Game g = new Game("Basketball", 5);
		
		assertEquals("success", ss.createGame(g));
	}
	
	@Test
	public void test_createGame_empty_string_name() {
		SchedulerService ss = new SchedulerService(new GameRepo(new Game[5]), new PlayerRepo(new Player[5]), new DayRepo(new Day[5]));
		Game g = new Game("", 5);
		
		assertEquals("Game name cannot be an empty String!", ss.createGame(g));
	}
	
	@Test
	public void test_createGame_with_no_players() {
		//fail("Not yet implemented");
		SchedulerService ss = new SchedulerService(new GameRepo(new Game[5]), new PlayerRepo(new Player[5]), new DayRepo(new Day[5]));
		Game g = new Game("Basketball", 0);
		
		assertEquals("Number of players cannot be zero!", ss.createGame(g));
	}
	
	@Test
	public void test_createGame_with_duplicate_name() {
		SchedulerService ss = new SchedulerService(new GameRepo(new Game[5]), new PlayerRepo(new Player[5]), new DayRepo(new Day[5]));
		Game g = new Game("Basketball", 4);
		ss.createGame(g);
		Game g2 = new Game("Basketball", 3);
		
		assertEquals("failure", ss.createGame(g2));
	}
	
	@Test
	public void test_createGame_with_null_game() {
		SchedulerService ss = new SchedulerService(new GameRepo(new Game[5]), new PlayerRepo(new Player[5]), new DayRepo(new Day[5]));
		Game g = new Game();
		
		assertEquals("A null game cannot be created!", ss.createGame(g));
	}
	
	@Test
	public void test_createPlayer_success() {
		Game g3 = new Game("Rugby", 4);
		Game g4 = new Game("Tennis", 2);
		Game[] repoGames = {g3, g4};
		GameRepo gr = new GameRepo(repoGames);
		SchedulerService ss = new SchedulerService(gr, new PlayerRepo(new Player[5]), new DayRepo(new Day[5]));
		
		Game g1 = new Game("Rugby", 4);
		Game g2 = new Game("Tennis", 2);
		Game[] games = {g1, g2};
		Player p = new Player("Tom", games);
		
		assertEquals("success", ss.createPlayer(p));
	}
	
	@Test
	public void test_createPlayer_no_related_games() {
		Game g3 = new Game("Running", 4);
		Game g4 = new Game("Swimming", 2);
		Game[] repoGames = {g3, g4};
		GameRepo gr = new GameRepo(repoGames);
		SchedulerService ss = new SchedulerService(gr, new PlayerRepo(new Player[5]), new DayRepo(new Day[5]));
		Game g1 = new Game("Rugby", 4);
		Game g2 = new Game("Tennis", 2);
		Game[] games = {g1, g2};
		Player p = new Player("Tom", games);
		
		assertEquals("Player does not play any of the games added in the system!", ss.createPlayer(p));
	}
	
	@Test
	public void test_createPlayer_player_name_empty_string() {
		Game g3 = new Game("Rugby", 4);
		Game g4 = new Game("Tennis", 2);
		Game[] repoGames = {g3, g4};
		GameRepo gr = new GameRepo(repoGames);
		SchedulerService ss = new SchedulerService(gr, new PlayerRepo(new Player[5]), new DayRepo(new Day[5]));
		Game g1 = new Game("Rugby", 4);
		Game g2 = new Game("Tennis", 2);
		Game[] games = {g1, g2};
		Player p = new Player("", games);
		
		assertEquals("Player name cannot be empty!", ss.createPlayer(p));
	}
	
	@Test
	public void test_createPlayer_player_name_repeated() {
		Game g3 = new Game("Rugby", 4);
		Game g4 = new Game("Tennis", 2);
		Game[] repoGames = {g3, g4};
		GameRepo gr = new GameRepo(repoGames);
		SchedulerService ss = new SchedulerService(gr, new PlayerRepo(new Player[5]), new DayRepo(new Day[5]));
		Game g1 = new Game("Rugby", 4);
		Game g2 = new Game("Tennis", 2);
		Game[] games = {g1, g2};
		Player p = new Player("John", games);
		ss.createPlayer(p);
		
		Game[] games2 = {g1, g2};
		Player p2 = new Player("John", games2);
		
		assertEquals("Player has already been added to the system!", ss.createPlayer(p2));
		
	}
	
	@Test
	public void test_createPlayer_with_null_player() {
		Game g3 = new Game("Rugby", 4);
		Game g4 = new Game("Tennis", 2);
		Game[] repoGames = {g3, g4};
		GameRepo gr = new GameRepo(repoGames);
		SchedulerService ss = new SchedulerService(gr, new PlayerRepo(new Player[5]), new DayRepo(new Day[5]));
	
		Player p = new Player();
		
		assertEquals("A null player cannot be created", ss.createPlayer(p));
		
	}
	
	@Test
	public void test_createDay_success() {
		Game g3 = new Game("Rugby", 4);
		Game g4 = new Game("Tennis", 2);
		Game[] repoGames = {g3, g4};
		GameRepo gr = new GameRepo(repoGames);
		SchedulerService ss = new SchedulerService(gr, new PlayerRepo(new Player[5]), new DayRepo(new Day[5]));
	
		Day d = new Day("Monday", repoGames);
		
		assertEquals("success", ss.createDay(d));
		
	}
	
	@Test
	public void test_createDay_no_related_games() {
		Game g3 = new Game("Running", 4);
		Game g4 = new Game("Swimming", 2);
		Game[] repoGames = {g3, g4};
		GameRepo gr = new GameRepo(repoGames);
		SchedulerService ss = new SchedulerService(gr, new PlayerRepo(new Player[5]), new DayRepo(new Day[5]));
		
		Game g1 = new Game("Rugby", 4);
		Game g2 = new Game("Tennis", 2);
		Game[] games = {g1, g2};
		Day d = new Day("Monday", games);
		
		assertEquals("Games in added day do not exist in the system!", ss.createDay(d));
	}
	
	@Test
	public void test_createDay_day_name_empty_string() {
		Game g3 = new Game("Rugby", 4);
		Game g4 = new Game("Tennis", 2);
		Game[] repoGames = {g3, g4};
		GameRepo gr = new GameRepo(repoGames);
		SchedulerService ss = new SchedulerService(gr, new PlayerRepo(new Player[5]), new DayRepo(new Day[5]));
		
		Game g1 = new Game("Rugby", 4);
		Game g2 = new Game("Tennis", 2);
		Game[] games = {g1, g2};
		Day d = new Day("", games);
		
		assertEquals("Day name cannot be empty!", ss.createDay(d));
	}
	
	@Test
	public void test_createDay_day_name_repeated() {
		Game g3 = new Game("Rugby", 4);
		Game g4 = new Game("Tennis", 2);
		Game[] repoGames = {g3, g4};
		GameRepo gr = new GameRepo(repoGames);
		SchedulerService ss = new SchedulerService(gr, new PlayerRepo(new Player[5]), new DayRepo(new Day[5]));
		Game g1 = new Game("Rugby", 4);
		Game g2 = new Game("Tennis", 2);
		Game[] games = {g1, g2};
		Day d = new Day("Monday", games);
		ss.createDay(d);
		
		Game[] games2 = {g1, g2};
		Day d2 = new Day("Monday", games2);
		
		assertEquals("Day has already been added to the system!", ss.createDay(d2));
		
	}
	
	@Test
	public void test_createDay_with_null_day() {
		Game g3 = new Game("Rugby", 4);
		Game g4 = new Game("Tennis", 2);
		Game[] repoGames = {g3, g4};
		GameRepo gr = new GameRepo(repoGames);
		SchedulerService ss = new SchedulerService(gr, new PlayerRepo(new Player[5]), new DayRepo(new Day[5]));
	
		Day d = new Day();
		
		assertEquals("A null day cannot be created", ss.createDay(d));
		
	}
	
	@Test
	public void test_gameWiseReport_success() {
		Game g3 = new Game("Rugby", 4);
		Game g4 = new Game("Tennis", 2);
		Game[] repoGames = {g3, g4};
		GameRepo gr = new GameRepo(repoGames);
		
		Player p1 = new Player("Tom", repoGames);
		Player[] repoPlayer = {p1};
		PlayerRepo pr = new PlayerRepo(repoPlayer);
		
		Day d1 = new Day("Monday", repoGames);
		Day[] repoDay = {d1};
		DayRepo dr = new DayRepo(repoDay);
		
		SchedulerService ss = new SchedulerService(gr, pr, dr);
	
		assertEquals("Players playing Tennis:\n"+"Tom  "+"\nDays on which Tennis is played:\n"+"Monday  "+"\n", ss.gameWiseReport("Tennis").toString());
		
	}
	
	@Test
	public void test_gameWiseReport_absence_of_game() {
		Game g3 = new Game("Rugby", 4);
		Game g4 = new Game("Tennis", 2);
		Game[] repoGames = {g3, g4};
		GameRepo gr = new GameRepo(repoGames);
		
		Player p1 = new Player("Tom", repoGames);
		Player[] repoPlayer = {p1};
		PlayerRepo pr = new PlayerRepo(repoPlayer);
		
		Day d1 = new Day("Monday", repoGames);
		Day[] repoDay = {d1};
		DayRepo dr = new DayRepo(repoDay);
		
		SchedulerService ss = new SchedulerService(gr, pr, dr);
	
		assertEquals("Game does not exist in System!", ss.gameWiseReport("Soccer").toString());
		
	}
	
	@Test
	public void test_gameWiseReport_empty_game_name() {
		Game g3 = new Game("Rugby", 4);
		Game g4 = new Game("Tennis", 2);
		Game[] repoGames = {g3, g4};
		GameRepo gr = new GameRepo(repoGames);
		
		Player p1 = new Player("Tom", repoGames);
		Player[] repoPlayer = {p1};
		PlayerRepo pr = new PlayerRepo(repoPlayer);
		
		Day d1 = new Day("Monday", repoGames);
		Day[] repoDay = {d1};
		DayRepo dr = new DayRepo(repoDay);
		
		SchedulerService ss = new SchedulerService(gr, pr, dr);
	
		assertEquals("Game Name provided cannot be an empty String!", ss.gameWiseReport("").toString());
		
	}

	@Test
	public void test_playerWiseReport_success() {
		Game g3 = new Game("Rugby", 4);
		Game g4 = new Game("Tennis", 2);
		Game[] repoGames = {g3, g4};
		GameRepo gr = new GameRepo(repoGames);
		
		Player p1 = new Player("Tom", repoGames);
		Player[] repoPlayer = {p1};
		PlayerRepo pr = new PlayerRepo(repoPlayer);
		
		Day d1 = new Day("Monday", repoGames);
		Day[] repoDay = {d1};
		DayRepo dr = new DayRepo(repoDay);
		
		SchedulerService ss = new SchedulerService(gr, pr, dr);
	
		assertEquals("Games which Tom plays:\n"+"Rugby(  Monday    )  Tennis(  Monday    )  \n", ss.playerWiseReport("Tom").toString());
		
	}
	
	@Test
	public void test_playerWiseReport_nonexistent_player() {
		Game g3 = new Game("Rugby", 4);
		Game g4 = new Game("Tennis", 2);
		Game[] repoGames = {g3, g4};
		GameRepo gr = new GameRepo(repoGames);
		
		Player p1 = new Player("Tom", repoGames);
		Player[] repoPlayer = {p1};
		PlayerRepo pr = new PlayerRepo(repoPlayer);
		
		Day d1 = new Day("Monday", repoGames);
		Day[] repoDay = {d1};
		DayRepo dr = new DayRepo(repoDay);
		
		SchedulerService ss = new SchedulerService(gr, pr, dr);
	
	
		assertEquals("Player does not exist in the System!", ss.playerWiseReport("Mary").toString());
		
	}
	
	@Test
	public void test_playerWiseReport_empty_player_name() {
		Game g3 = new Game("Rugby", 4);
		Game g4 = new Game("Tennis", 2);
		Game[] repoGames = {g3, g4};
		GameRepo gr = new GameRepo(repoGames);
		
		Player p1 = new Player("Tom", repoGames);
		Player[] repoPlayer = {p1};
		PlayerRepo pr = new PlayerRepo(repoPlayer);
		
		Day d1 = new Day("Monday", repoGames);
		Day[] repoDay = {d1};
		DayRepo dr = new DayRepo(repoDay);
		
		SchedulerService ss = new SchedulerService(gr, pr, dr);
	
		assertEquals("Player Name provided cannot be an empty String!", ss.playerWiseReport("").toString());
		
	}
	
	@Test
	public void test_dayWiseReport_success() {
		Game g3 = new Game("Rugby", 4);
		Game g4 = new Game("Tennis", 2);
		Game[] repoGames = {g3, g4};
		GameRepo gr = new GameRepo(repoGames);
		
		Player p1 = new Player("Tom", repoGames);
		Player[] repoPlayer = {p1};
		PlayerRepo pr = new PlayerRepo(repoPlayer);
		
		Day d1 = new Day("Monday", repoGames);
		Day[] repoDay = {d1};
		DayRepo dr = new DayRepo(repoDay);
		
		SchedulerService ss = new SchedulerService(gr, pr, dr);
	
		assertEquals("Games scheduled on Monday:\n"+"Rugby(  Tom  )  Tennis(  Tom  )  \n", ss.dayWiseReport("Monday").toString());
	}
	
	@Test
	public void test_dayWiseReport_nonexistent_day() {
		Game g3 = new Game("Rugby", 4);
		Game g4 = new Game("Tennis", 2);
		Game[] repoGames = {g3, g4};
		GameRepo gr = new GameRepo(repoGames);
		
		Player p1 = new Player("Tom", repoGames);
		Player[] repoPlayer = {p1};
		PlayerRepo pr = new PlayerRepo(repoPlayer);
		
		Day d1 = new Day("Monday", repoGames);
		Day[] repoDay = {d1};
		DayRepo dr = new DayRepo(repoDay);
		
		SchedulerService ss = new SchedulerService(gr, pr, dr);
	
		assertEquals("Day given does not exist in System!", ss.dayWiseReport("Saturday").toString());
		
	}
	
	@Test
	public void test_dayWiseReport_empty_day_name() {
		Game g3 = new Game("Rugby", 4);
		Game g4 = new Game("Tennis", 2);
		Game[] repoGames = {g3, g4};
		GameRepo gr = new GameRepo(repoGames);
		
		Player p1 = new Player("Tom", repoGames);
		Player[] repoPlayer = {p1};
		PlayerRepo pr = new PlayerRepo(repoPlayer);
		
		Day d1 = new Day("Monday", repoGames);
		Day[] repoDay = {d1};
		DayRepo dr = new DayRepo(repoDay);
		
		SchedulerService ss = new SchedulerService(gr, pr, dr);

		assertEquals("Day Name provided cannot be an empty String!", ss.dayWiseReport("").toString());
		
	}
}
