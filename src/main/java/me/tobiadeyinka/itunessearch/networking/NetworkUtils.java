/*
 *  Copyright 2018 Oluwatobi Adeyinka
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package me.tobiadeyinka.itunessearch.networking;

import me.tobiadeyinka.itunessearch.exceptions.NetworkCommunicationException;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

import java.net.*;
import java.util.Objects;
import java.util.Scanner;

/**
 * URL queries management.
 *
 * Created by Tobi Adeyinka on 2017. 10. 15..
 */
public abstract class NetworkUtils {

    public static JSONObject executeQuery(URL url) {
        try {
            /*
             * encode url before query
             */
            URI uri = new URI(
                url.getProtocol(),
                url.getUserInfo(),
                url.getHost(),
                url.getPort(),
                url.getPath(),
                url.getQuery(),
                url.getRef()
            );

            return new JSONObject(Objects.requireNonNull(query(new URL(uri.toASCIIString()))));
        } catch (IOException | URISyntaxException e) {
            throw new NetworkCommunicationException(String.format("Error while executing query: %s", e.getMessage()));
        }
    }

    private static String query(URL url) throws IOException {
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
