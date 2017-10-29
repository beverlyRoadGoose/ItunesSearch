/*
 *  Copyright 2017 Oluwatobi Adeyinka
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

package com.tobiadeyinka.itunessearch.lookup;

import com.tobiadeyinka.itunessearch.networking.NetworkUtils;
import com.tobiadeyinka.itunessearch.exceptions.ItunesSearchException;

import org.json.JSONObject;

import java.net.URL;
import java.net.MalformedURLException;

/**
 * This class manages looking up podcasts with different attributes
 *
 * Created by Tobi Adeyinka on 2017. 10. 18..
 */
public abstract class PodcastLookup {

    /**
     * get the top 100 podcasts in the iTunes store
     *
     * @return a JSONObject containing a list of the top podcasts
     */
    public static JSONObject topPodcasts() {
        return topPodcasts(100);
    }

    /**
     * get the top (limit) podcasts in the iTunes store
     *
     * @param limit the maximum number of podcasts to return
     * @return a JSONObject containing a list of the top podcasts
     */
    public static JSONObject topPodcasts(int limit) {
        String urlString = "https://rss.itunes.apple.com/api/v1/us/podcasts/top-podcasts/all/" + limit + "/explicit.json";
        return executeQuery(urlString);
    }

    /**
     * get a list of 100 comedy podcasts in the iTunes store
     *
     * @return a JSONObject containing a list of the top podcasts
     */
    public static JSONObject comedyPodcasts() {
        int genreId = 1303;
        return getPodcastGenre(genreId, 100);
    }

    /**
     * get a list of (limit) comedy podcasts in the iTunes store
     *
     * @param limit the maximum number of podcasts to return
     * @return a JSONObject containing a list of the top podcasts
     */
    public static JSONObject comedyPodcasts(int limit) {
        int genreId = 1303;
        return getPodcastGenre(genreId, limit);
    }

    /**
     * get a list of 100 news &amp; politics podcasts in the iTunes store
     *
     * @return a JSONObject containing a list of the top podcasts
     */
    public static JSONObject newsAndPoliticsPodcasts() {
        int genreId = 1311;
        return getPodcastGenre(genreId, 100);
    }

    /**
     * get a list of (limit) news &amp; politics podcasts in the iTunes store
     *
     * @param limit the maximum number of podcasts to return
     * @return a JSONObject containing a list of the top podcasts
     */
    public static JSONObject newsAndPoliticsPodcasts(int limit) {
        int genreId = 1311;
        return getPodcastGenre(genreId, limit);
    }

    /**
     * get a list of 100 society &amp; culture podcasts in the iTunes store
     *
     * @return a JSONObject containing a list of the top podcasts
     */
    public static JSONObject societyAndCulturePodcasts() {
        int genreId = 1324;
        return getPodcastGenre(genreId, 100);
    }

    /**
     * get a list of (limit) society &amp; culture podcasts in the iTunes store
     *
     * @param limit the maximum number of podcasts to return
     * @return a JSONObject containing a list of the top podcasts
     */
    public static JSONObject societyAndCulturePodcasts(int limit) {
        int genreId = 1324;
        return getPodcastGenre(genreId, limit);
    }

    /**
     * Get podcasts by their genre id
     *
     * @param genreId the podcasts genre id
     * @param limit maximum number of returned elements
     * @return a JSONObject containing a list of the matching podcasts
     */
    private static JSONObject getPodcastGenre(int genreId, int limit) {
        String urlString = "https://itunes.apple.com/search?term=podcast&limit=" + limit + "&genreId=" + genreId;
        return executeQuery(urlString);
    }

    private static JSONObject executeQuery(String urlString) {
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
