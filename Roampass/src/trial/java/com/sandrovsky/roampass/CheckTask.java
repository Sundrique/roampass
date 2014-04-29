package com.sandrovsky.roampass;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.telephony.TelephonyManager;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author asandrovsky@gmail.com
 */
public class CheckTask extends AsyncTask<Void, Void, Void> {
    private static String API_KEY = "PBdA3KKHI6fdNhUGzBziNDMw9o0Dmux5";
    private static String API_SECRET = "omZPM5zKB4Pkhl8uXg8AmcSm6XxZHYC8";
    private static String API_URL = "http://trial-checker.herokuapp.com/";

    private static String ELAPSED_TIME = "elapsedTime";

    private Context context;

    public CheckTask(Context context) {
        this.context = context;
    }

    @Override
    protected Void doInBackground(Void... params) {
        SharedPreferences preferences = context.getSharedPreferences(Trial.PREFS_TRIAL, Context.MODE_PRIVATE);
        long installTime = preferences.getLong(Trial.INSTALL_TIME, 0);

        if (installTime == 0) {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            if (activeNetwork != null && activeNetwork.isConnected()) {
                preferences = context.getSharedPreferences(Trial.PREFS_TRIAL, Context.MODE_PRIVATE);

                TelephonyManager telephoneMgr = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
                String id = telephoneMgr.getDeviceId();

                List<NameValuePair> parameters = new ArrayList<NameValuePair>(3);
                parameters.add(new BasicNameValuePair("id", id));
                parameters.add(new BasicNameValuePair("api_key", API_KEY));
                parameters.add(new BasicNameValuePair("sign", Hash.Sha1(id + API_KEY + API_SECRET)));

                HttpClient httpclient = new DefaultHttpClient();
                HttpGet httpGet = new HttpGet(API_URL + "?" + URLEncodedUtils.format(parameters, "utf-8"));

                try {
                    HttpResponse response = httpclient.execute(httpGet);
                    JSONObject result = new JSONObject(EntityUtils.toString(response.getEntity()));
                    long elapsedTime = result.getLong(ELAPSED_TIME);

                    preferences.edit().putLong(Trial.INSTALL_TIME, System.currentTimeMillis() - elapsedTime).commit();
                } catch (Exception e) {
                }
            }
        }

        return null;
    }
}
