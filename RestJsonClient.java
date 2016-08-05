package me.kaelaela.sample;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;

public class RestJsonClient {

	public static int responsecode;
	public static Boolean nullJson = false;
	
	public static JSONObject connect(String url) {
		// TODO Auto-generated method stub
		System.out.println("url in restjsonclien is" + url);
		HttpClient httpclient = new DefaultHttpClient();
		HttpGet httpget = new HttpGet(url);
		HttpResponse response;
		JSONObject json = new JSONObject();
		System.out.println("httpget is" + httpget);
		

		try {
			nullJson = false;
			response = httpclient.execute(httpget);
			System.out.println("response in restjoson cleis" + response);
			
			responsecode =  response.getStatusLine().getStatusCode() ;
			  Log.v("response code", response.getStatusLine()
                      .getStatusCode() + ""); 
			HttpEntity entity = response.getEntity();

			if (entity != null) {
				// A Simple JSON Response Read
				InputStream instream = entity.getContent();
				String result = convertStreamToString(instream);
				json = new JSONObject(result);
				instream.close();
			}
		} catch (ClientProtocolException e) {
			System.out.println("client");
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			System.out.println("illegal");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			nullJson = true;
			System.out.println("ioexcepiton");
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			System.out.println("jsonex");
			e.printStackTrace();
		}

		return json;
	}
	
	/**
	 * 
	 * @param is
	 * @return String
	 */
	public static  String convertStreamToString(InputStream is) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();

		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}

	
	public static boolean isNetworkAvailable1(Activity activity) {
		   {
			   
			   System.out.println("hi eerror");
		        boolean haveConnectedWifi = false;
		        boolean haveConnectedMobile = false;

		        ConnectivityManager cm = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
		        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
		        for (NetworkInfo ni : netInfo)
		        {
		            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
		            {
		                if (ni.isConnected())
		                {
		                    haveConnectedWifi = true;
		                    Log.v("WIFI CONNECTION ", "AVAILABLE");
		                } else
		                {
		                    Log.v("WIFI CONNECTION ", "NOT AVAILABLE");
		                }
		            }
		            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
		            {
		                if (ni.isConnected())
		                {
		                    haveConnectedMobile = true;
		                    Log.v("MOBILE INTERNET CONNECTION ", "AVAILABLE");
		                } else
		                {
		                    Log.v("MOBILE INTERNET CONNECTION ", "NOT AVAILABLE");
		                }
		            }
		        }
		        return haveConnectedWifi || haveConnectedMobile;
		    }
	    
	    }
	
	

	public static String getJSONString(String url) {
		String jsonString = null;
		HttpURLConnection linkConnection = null;
		try {
			URL linkurl = new URL(url);
			linkConnection = (HttpURLConnection) linkurl.openConnection();
			int responseCode = linkConnection.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) {
				InputStream linkinStream = linkConnection.getInputStream();
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				int j = 0;
				while ((j = linkinStream.read()) != -1) {
					baos.write(j);
				}
				byte[] data = baos.toByteArray();
				jsonString = new String(data);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (linkConnection != null) {
				linkConnection.disconnect();
			}
		}
		return jsonString;
	}

	public static boolean isEmailValid(String email) {
	    boolean isValid = false;

	    String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
	    CharSequence inputStr = email;

	    Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
	    Matcher matcher = pattern.matcher(inputStr);
	    if (matcher.matches()) {
	        isValid = true;
	    }
	    return isValid;
	}
	//CODE FOR HIDE SOFT KEYBOARD
		public static void hideSoftKeyboard(Activity activity)
		{
		    InputMethodManager inputMethodManager = (InputMethodManager)  activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
		    inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
		}

}


