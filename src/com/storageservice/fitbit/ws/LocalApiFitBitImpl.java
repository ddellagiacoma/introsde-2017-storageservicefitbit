package com.storageservice.fitbit.ws;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;

import java.util.Date;


import javax.jws.WebService;

import org.json.JSONArray;
import org.json.JSONObject;

import com.storageservice.fitbit.model.Person;
import com.storageservice.fitbit.model.WeightGoal;
import com.storageservice.fitbit.model.Goal;





//Service Implementation

@WebService(endpointInterface = "com.storageservice.fitbit.ws.LocalApiFitBitModel", serviceName = "storageServiceFitBit")
public class LocalApiFitBitImpl implements LocalApiFitBitModel {
	@Override
	public Person getWeightHeight(String access_token,String user_id,String refresh_token){
		String url = "https://adapterservice.herokuapp.com/FitbitProfile?access_token="+access_token+"&user_id="+user_id+"&refresh_token="+refresh_token;
		try {
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();

			con.setRequestMethod("GET");
			con.setRequestProperty("Accept", "application/json");
			int responseCode = con.getResponseCode();

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			JSONObject jobj= new JSONObject(response.toString());
			Person p=new Person();
		p.setWeight(jobj.getDouble("weight"));
		p.setHeight(jobj.getDouble("height"));
		
			return p;		
		} catch (Exception e) {
			System.out.println("error in getting weight and height  request on FitBitAdapter " + e);
			return null;
		}
	
}
@Override	
public Goal getDailyGoal(String access_token,String user_id,String refresh_token) {
		//get daily activity summary
		Date date=new Date();
	String sdate = new SimpleDateFormat("yyyy-MM-dd").format(date);
			String url = "https://adapterservice.herokuapp.com/FitbitDailyActivitySummary?access_token="+access_token+"&user_id="+user_id+"&refresh_token="+refresh_token+"&date="+sdate;
			try {
				URL obj = new URL(url);
				HttpURLConnection con = (HttpURLConnection) obj.openConnection();

				con.setRequestMethod("GET");
				con.setRequestProperty("Accept", "application/json");
				int responseCode = con.getResponseCode();

				BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();

				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();
				System.out.println("response "+response);
				Goal goal= new Goal();
				JSONObject jobj = new JSONObject(response.toString());
				goal.setCaloriesOut(jobj.getJSONObject("goals").getInt("caloriesOut"));
				try{goal.setDistance((jobj.getJSONObject("goals").getDouble("distance")));}catch(Exception e){goal.setDistance(0);}
				goal.setSteps(jobj.getJSONObject("goals").getInt("steps"));
				JSONArray activities=jobj.getJSONArray("activities");
				
				int StepsDone=0;
				int DistanceDone=0;
				int CaloriesOutDone=jobj.getJSONObject("summary").getInt("caloriesOut");
				for (int i = 0; i < activities.length(); i++) {
					JSONObject activity = activities.getJSONObject(i);
					StepsDone+=activity.getInt("steps");
					try{
					DistanceDone+=activity.getDouble("distance");}catch(Exception e){}
					}
				goal.setMissingSteps(goal.getSteps()-StepsDone);
				goal.setMissingDistance(goal.getDistance()-DistanceDone);
				goal.setMissingCalories(goal.getCaloriesOut()-CaloriesOutDone);
				return goal;		
			} catch (Exception e) {
				System.out.println("error in getting daily goal request on FitBitAdapter " + e);
				return null;
			}
	
	}
@Override	
public WeightGoal getWeightGoal( String access_token,String user_id,String refresh_token) {

		//get body goal
		String url = "https://adapterservice.herokuapp.com/FitbitBodyGoal?access_token="+access_token+"&user_id="+user_id+"&refresh_token="+refresh_token;
		try {
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();

			con.setRequestMethod("GET");
			con.setRequestProperty("Accept", "application/json");
			int responseCode = con.getResponseCode();

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			WeightGoal wg= new WeightGoal();
			JSONObject jobj = new JSONObject(response.toString());
			wg.setStartDate(jobj.getString("startDate"));
			wg.setGoalWeight(jobj.getDouble("weight"));
			wg.setStartWeight(jobj.getDouble("startWeight"));
			return wg;		
		} catch (Exception e) {
			System.out.println("error in getting weight goal request on FitBitAdapter " + e);
			return null;
		}

}
@Override	
public double PeriodWeightDifference(String startDate, String endDate, String access_token,String user_id,String refresh_token){
		String url = "https://adapterservice.herokuapp.com/FitbitWeightLogs?access_token="+access_token+"&user_id="+user_id+"&refresh_token="+refresh_token+"&base_date="+startDate+"&end_date="+endDate;
		try {
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();

			con.setRequestMethod("GET");
			con.setRequestProperty("Accept", "application/json");
			int responseCode = con.getResponseCode();

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			
			JSONArray jarr = new JSONArray(response.toString());
			
				if(response!=null){
					System.out.println(response);
				double startWeight = jarr.getJSONObject(0).getDouble("weight");
				double endWeight =jarr.getJSONObject(1).getDouble("weight");
				
				return (startWeight-endWeight);
				}
				else{
					return 0;
				}
				
		} catch (Exception e) {
			System.out.println("error in getting weight logs request on FitBitAdapter " + e);
			return 0;
		}
	
	}

	}

