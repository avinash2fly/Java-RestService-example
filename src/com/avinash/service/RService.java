package com.avinash.service;

import java.util.ArrayList;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import com.avinash.dao.RDao;
import com.avinash.util.Utils;

public class RService {

	private static RDao dao = new RDao();

	public int getCabCount(String driver, String date, boolean cache) throws Exception {
		if (Utils.getData(driver) != -1 && !cache) {
			return Utils.getData(driver);
		}
		Utils.addData(driver, dao.getCabTripCountByDate(driver, date));
		return Utils.getData(driver);
	}

	public JSONArray getCabsCount(JSONArray drivers, String date, boolean cache) throws Exception {
		JSONArray out = new JSONArray();
		ArrayList<String> newDrivers = new ArrayList<>();
		for (int i = 0; i < drivers.length(); i++) {
			String driver = String.valueOf(drivers.get(i));
			if (Utils.getData(driver) != -1 && cache) {
				JSONObject obbj = new JSONObject();
				obbj.put("driver", driver);
				obbj.put("count", Utils.getData(driver));
				out.put(obbj);
			} else {
				newDrivers.add(driver);
			}
		}
		if (newDrivers.size() > 0)
			dao.getCabsTripCountByDate(newDrivers, date, out);
		return out;
	}

}
