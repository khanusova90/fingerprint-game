package cz.hanusova.fingerprintgame.model;

import java.util.List;

/**
 * Timetable action from STAG IS
 * 
 * @author khanusova
 *
 */
public class TimetableAction {

	private List<TimetableActionInfo> timetableActionInfos;

	public List<TimetableActionInfo> getTimetableActionInfos() {
		return timetableActionInfos;
	}

	public void setTimetableActionInfos(List<TimetableActionInfo> timetableActionInfos) {
		this.timetableActionInfos = timetableActionInfos;
	}
}
