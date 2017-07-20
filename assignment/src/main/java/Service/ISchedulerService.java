package Service;

import POJO.Day;
import POJO.Game;
import POJO.Player;

public interface ISchedulerService {
	public String createGame(Game g);
	public String createPlayer(Player p);
	public String createDay(Day d);
	public StringBuffer gameWiseReport(String gameName);
	public StringBuffer playerWiseReport(String playerName);
	public StringBuffer dayWiseReport(String dayName);
}
