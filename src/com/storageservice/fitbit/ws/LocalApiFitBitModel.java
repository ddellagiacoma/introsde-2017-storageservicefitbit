package com.storageservice.fitbit.ws;



import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.WebResult;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;

import com.storageservice.fitbit.model.Person;
import com.storageservice.fitbit.model.WeightGoal;
import com.storageservice.fitbit.model.Goal;





//service definition
@WebService
@SOAPBinding(style = Style.DOCUMENT, use=Use.LITERAL) 
public interface LocalApiFitBitModel {
	 
@WebMethod(operationName="getWeightHeight")
@WebResult(name="Person") 
public Person getWeightHeight(@WebParam(name="access_token")String access_token,@WebParam(name="user_id")String user_id,@WebParam(name="refresh_token")String refresh_token);
	  
@WebMethod(operationName="getDailyGoal")
@WebResult(name="dailygoal") 
public Goal getDailyGoal(@WebParam(name="access_token")String access_token,@WebParam(name="user_id")String user_id,@WebParam(name="refresh_token")String refresh_token);

@WebMethod(operationName="getPeriodWeightDifference")
@WebResult(name="weightDifference")  
public double PeriodWeightDifference(@WebParam(name="startDate")String startDate,@WebParam(name="endDate")String endDate,@WebParam(name="access_token")String access_token,@WebParam(name="user_id")String user_id,@WebParam(name="refresh_token")String refresh_token);

@WebMethod(operationName="getWeightGoal")
@WebResult(name="weightGoal")  
public WeightGoal getWeightGoal(@WebParam(name="access_token")String access_token,@WebParam(name="user_id")String user_id,@WebParam(name="refresh_token")String refresh_token);

  
}