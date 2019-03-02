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

package me.tobiadeyinka.itunessearch.lookup;

import com.neovisionaries.i18n.CountryCode;

import me.tobiadeyinka.itunessearch.networking.NetworkUtils;
import me.tobiadeyinka.itunessearch.exceptions.ItunesSearchException;
import me.tobiadeyinka.itunessearch.exceptions.NoMatchFoundException;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;
import java.net.MalformedURLException;

/**
 * Parent class for all lookups, containing common code implementations
 *
 * Created by Tobi Adeyinka on 2017. 11. 08..
 */
abstract class Lookup {

    protected static final String BASE_LOOKUP_URL = "https://itunes.apple.com/lookup?";
    protected static final int DEFAULT_LIMIT = 100;
    protected static final CountryCode DEFAULT_COUNTRY = CountryCode.US;

    /**
     * retrieve a media item by its id
     *
     * @param id the itunes id for the item
     * @return a JSONObject representation of the item
     * @throws NoMatchFoundException if no matching item is found
     */
    protected static JSONObject getById(long id) throws NoMatchFoundException {
        String urlString = String.format("%sid=%s", BASE_LOOKUP_URL, id);

        JSONObject response = executeQuery(urlString);
        JSONArray responseJSONArray = response.getJSONArray("results");

        if (responseJSONArray.length() == 0) {
            throw new NoMatchFoundException("No item matches the given id");
        }

        return response;
    }

    protected static JSONObject executeQuery(String urlString) {
        JSONObject response = null;

        try {
            URL url = new URL(urlString);
            response = NetworkUtils.executeQuery(url);
        } catch (MalformedURLException | ItunesSearchException e) {
            e.printStackTrace();
        }

        return response;
    }

}
