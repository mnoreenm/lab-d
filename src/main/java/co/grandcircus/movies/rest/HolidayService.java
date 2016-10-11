package co.grandcircus.movies.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import co.grandcircus.movies.model.Holiday;
import co.grandcircus.movies.model.Weather;

/**
 * Responsibility: Get Holiday data from holidayapi.com
 * //Your API Key:  b51deb3f-0dc0-4bf4-a8e6-922469466766
 */
@Service
public class HolidayService {

	private final static String key = "b51deb3f-0dc0-4bf4-a8e6-922469466766";
	private final static String country = "US";
	private final static Integer year = 2015;
	private final static Integer month = 10;

	public ArrayList<Holiday> getCurrentHoliday() {
		return getHolidaysAt(key,country, year, month);
	}

	public ArrayList<Holiday> getHolidaysAt(String key,String country, Integer year, Integer month) { // url copy and paste.method 
																				// in controller
																				 
		String url = "https://holidayapi.com/v1/holidays?key=" + key + "&country=" + country + "&year=" + year
				+ "&month=" + month + "&FcstType=json";
                    
		try (BufferedReader reader = HttpHelper.doGet(url)) { // try with  resources will auto close the reader
		
			if (reader == null) {
				throw new RuntimeException("Not found: " + url);
			}
         
			// parse the HTTP response body to JSON
			JsonElement root = new JsonParser().parse(reader);// parse always
			
			
			
																// turn into
																// element
			// pick the "currentobservation" key from the root JSON object.
			JsonArray holidays = root.getAsJsonObject().get("holidays").getAsJsonArray();

			ArrayList<Holiday> holidayList = new ArrayList<Holiday>();// created Holiday ArrayList 
			Holiday holiday = new Holiday();
			for(int i =0;i < holidays.size();i++){
				holiday.setName(holidays.get(i).getAsJsonObject().get("name").getAsString());// setting
				holiday.setDate(holidays.get(i).getAsJsonObject().get("date").getAsString());// datatype
				holiday.setObserved(holidays.get(i).getAsJsonObject().get("observed").getAsString());																// values
				holidayList.add(holiday);																// ,same
			}												
			return holidayList; // holiday type return with all info
		} catch (IOException ex) {
			throw new RuntimeException("Error reading from URL: " + url, ex);
		}
	

}
}





/**

@Service
public class HolidayService {
	private final static String key = "16988214-ad9e-47c0-954e-831473fb5417";
	private final static String country = "us";
	private final static Integer year = 2015;
	private final static Integer month = 10;

	public ArrayList<Holiday> getCurrentHolidays() {
		return getCurrentHolidayAt(key, country, year, month);
	}

	public ArrayList<Holiday> getCurrentHolidayAt(String key, String country, Integer year, Integer month) {
		String url = "https://holidayapi.com/v1/holidays?key=" + key + "&country=" + country + "&year=" + year
				+ "&month=" + month + "&FcstType=json";
		// Use HTTP GET with the above URL
		try (BufferedReader reader = HttpHelper.doGet(url)) { // try with
																// resources
																// will auto
																// close the
																// reader
			if (reader == null) {
				throw new RuntimeException("Not found: " + url);
			}

			// parse the HTTP response body to JSON
			// System.out.println(reader.readLine());
			JsonElement root = new JsonParser().parse(reader);

			JsonArray holidays = root.getAsJsonObject().get("holidays").getAsJsonArray();
			// pick the "Holidays" key from the root JSON object.

			ArrayList<Holiday> holidayList = new ArrayList<Holiday>();

			for (int i = 0; i < holidays.size(); i++) {

				Holiday holiday = new Holiday();
				holiday.setName(holidays.get(i).getAsJsonObject().get("name").getAsString());
				holiday.setDate(holidays.get(i).getAsJsonObject().get("date").getAsString());
				holiday.setObserved(holidays.get(i).getAsJsonObject().get("observed").getAsString());
				holidayList.add(holiday);

			}

			return holidayList;

		} catch (IOException ex) {
			throw new RuntimeException("Error reading from URL: " + url, ex);
		}
	}

}
*/



