package com.cp.json;

import java.io.IOException;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.cp.readURI.URItoJson;


public class JsonTest {




	public static void main( String[] args )
			throws IOException, JSONException {
		JSONObject json = URItoJson.readJsonFromUrl("https://koinex.in/api/ticker");

		JSONObject coinPrice = (JSONObject) json.get("prices");
		Iterator currentPriceItr = coinPrice.keys();
		while(currentPriceItr.hasNext())
		{
			String token = currentPriceItr.next().toString();
			System.out.printf("Current price of %s is %s\n",token,coinPrice.get(token));
		}
		JSONObject coinStats = ((JSONObject) json.get("stats"));
		JSONArray coinStatsArr = coinStats.names();

		for(int i=0; i<coinStatsArr.length(); i++)
		{
			JSONObject tokenJson = coinStats.getJSONObject(coinStatsArr.getString(i));
			Iterator tokenStatsItr = tokenJson.keys();
			System.out.printf("******* %s stats ********\n",coinStatsArr.getString(i));
			while(tokenStatsItr.hasNext())
			{
				String key = (String) tokenStatsItr.next();
				System.out.println(key + " - "+tokenJson.getString(key));
			}
		}


	}

}
