package com.tobiadeyinka.itunessearch.networking;

import java.io.IOException;
import java.io.InputStream;

import java.net.URL;
import java.net.HttpURLConnection;

import java.util.Scanner;

/**
 * URL queries management.
 *
 * Created by Tobi Adeyinka on 2017. 10. 15..
 */
public abstract class NetworkUtils {

    public static String query(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();

        try {
            InputStream inputStream = urlConnection.getInputStream();
            Scanner scanner = new Scanner(inputStream);
            scanner.useDelimiter("\\A");
            return scanner.hasNext() ? scanner.next() : null;
        } finally {
            urlConnection.disconnect();
        }
    }

}
