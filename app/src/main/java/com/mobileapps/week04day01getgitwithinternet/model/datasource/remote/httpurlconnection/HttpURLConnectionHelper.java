package com.mobileapps.week04day01getgitwithinternet.model.datasource.remote.httpurlconnection;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpURLConnectionHelper
{
    public static final String RANDOM_USER_URL = "https://api.github.com/search/repositories?q=user:";
    private static URL urlObject;
    private static HttpURLConnection httpURLConnection;
    public static final int EOF = -1;

    public static String getGitResponse(String name) throws Exception
    {

        String resultString = "";

        //Open the connection
        urlObject = new URL(RANDOM_USER_URL+name);
        httpURLConnection = (HttpURLConnection)urlObject.openConnection();

        //Buffer the stream
        InputStream inputStream = httpURLConnection.getInputStream();
        int getIntValueOfCurrentRead = inputStream.read();
        while(getIntValueOfCurrentRead != EOF) {
            char convertedIntToChar = (char)getIntValueOfCurrentRead;
            resultString = resultString + convertedIntToChar;
            getIntValueOfCurrentRead = inputStream.read();
        }

        httpURLConnection.disconnect();
        return resultString;


    }
}
