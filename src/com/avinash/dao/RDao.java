package com.avinash.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import com.avinash.util.Utils;
import com.mysql.jdbc.PreparedStatement;

public class RDao {

	public int getCabTripCountByDate(String CabID, String date) throws Exception {
		String sql = "select count(*) as count from ny_cab_data.cab_trip_data\n"
				+ "where cast(pickup_datetime as date) = ? \n" + "and medallion=?";
		ResultSet resultSet = null;
		try (Connection con = Utils.getConnection();PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(sql)) {
			preparedStatement.setDate(1, Utils.getDateByString(date));
			preparedStatement.setString(2, CabID);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				return resultSet.getInt("count");
			}
			return -1;
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
		
		
	}
	
	public void getCabsTripCountByDate(ArrayList<String> cabs, String date,JSONArray out) throws Exception {
		String sql = "select medallion,count(*) from ny_cab_data.cab_trip_data\n" + 
				"where cast(pickup_datetime as date) = ? \n" + 
				"and medallion in (##cabs##)\n" + 
				"group by medallion;";
		StringBuilder builder =new StringBuilder();
		for(int i=0;i<cabs.size();i++) {
			String cab = cabs.get(i);
			if(i==cabs.size()-1) {
				builder.append("'"+cab+"'");
			}else {
				builder.append("'"+cab+"',");					
			}
		}
		sql = sql.replace("##cabs##", builder.toString());
		ResultSet resultSet = null;
		try (Connection connection = Utils.getConnection();PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(sql)) {
			preparedStatement.setDate(1, Utils.getDateByString(date));
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				JSONObject obbj = new JSONObject();
				Utils.addData(resultSet.getString(1), resultSet.getInt(2));
				obbj.put("driver", resultSet.getString(1));
				obbj.put("count", resultSet.getInt(2));
				out.put(obbj);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		} 
		
	}

//	public static void main(String[] args) throws Exception {
//		RDao dao = new RDao();
//		ArrayList<String> cabs = new ArrayList<>();
//		cabs.add("00377E15077848677B32CE184CE7E871");
//		cabs.add("5455D5FF2BD94D10B304A15D4B7F2735");
//		System.out.println(dao.getCabsTripCountByDate(cabs, "01-12-2013"));
//	}
}
