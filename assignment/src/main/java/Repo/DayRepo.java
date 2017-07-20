package Repo;

import POJO.Day;
import POJO.Game;

public class DayRepo implements IDayRepo {
	private Day[] days;
	
	public DayRepo() {
	}
	
	public DayRepo(Day[] days) {
		this.days = days;
	}

	public String save(Day d) {
		Day[] days = this.getDays();
		int daySize = days.length;

		if (days[--daySize] != null) {

			int newDaySize = days.length;
			++newDaySize;
			Day[] newDays = new Day[newDaySize];

			for (int t = 0; t < days.length; t++) {
				
				newDays[t] = days[t];
			}
			newDays[--newDaySize] = d;
			this.setDays(newDays);

			return "success";
		} else {
			for (int i = 0; i < days.length; i++) {
				if (days[i] != null) {
					continue;
				} else {
				
					days[i] = d;
					return "success";
				}
			}
		}
		return "success";
	}
	
	public Day findOne(String name) {
		for(int i=0; i<days.length; i++) {
			if(days[i] != null) {
				if(days[i].getName().equals(name)) {
					Game[] games = new Game[5];
					return new Day("repeat", games);
				}
			}
		}
		
		Game[] games2 = new Game[5];
		return new Day("new", games2);
	}
	public Day[] findAll() {
		return new Day[5];
	}

	public Day[] getDays() {
		return days;
	}

	public void setDays(Day[] days) {
		this.days = days;
	}
	
}
