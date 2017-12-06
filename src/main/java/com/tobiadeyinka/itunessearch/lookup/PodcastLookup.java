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

import org.json.JSONObject;
import com.neovisionaries.i18n.CountryCode;

/**
 * This class manages looking up podcasts with different attributes
 *
 * Created by Tobi Adeyinka on 2017. 10. 18..
 */
public abstract class PodcastLookup extends Lookup {

    private static final int DEFAULT_LIMIT = 100;
    private static final CountryCode DEFAULT_COUNTRY = CountryCode.US;

    /**
     * get the top 100 podcasts in the default iTunes store
     *
     * @return a JSONObject containing a list of the top podcasts
     */
    public static JSONObject topPodcasts() {
        return topPodcasts(DEFAULT_LIMIT);
    }

    /**
     * get the top 100 podcasts in the default iTunes store
     *
     * @return a JSONObject containing a list of the top podcasts
     */
    public static JSONObject topPodcasts(CountryCode countryCode) {
        return queryTopPodcasts(countryCode, DEFAULT_LIMIT);
    }

    /**
     * get the top (limit) podcasts in the default iTunes store
     *
     * @param limit the maximum number of podcasts to return
     * @return a JSONObject containing a list of the top podcasts
     */
    public static JSONObject topPodcasts(int limit) {
        return queryTopPodcasts(DEFAULT_COUNTRY, limit);
    }

    /**
     * get the top (limit) podcasts in the default iTunes store
     *
     * @param limit the maximum number of podcasts to return
     * @return a JSONObject containing a list of the top podcasts
     */
    public static JSONObject topPodcasts(CountryCode countryCode, int limit) {
        return queryTopPodcasts(countryCode, limit);
    }

    /**
     * get a list of 100 comedy podcasts in the iTunes store
     *
     * @return a JSONObject containing a list of the top podcasts
     */
    public static JSONObject comedyPodcasts() {
        int genreId = 1303;
        return getPodcastGenre(genreId, DEFAULT_LIMIT);
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
        return getPodcastGenre(genreId, DEFAULT_LIMIT);
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
        return getPodcastGenre(genreId, DEFAULT_LIMIT);
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

    private static JSONObject queryTopPodcasts(CountryCode countryCode, int limit) {
        String urlString = "https://rss.itunes.apple.com/api/v1/" + countryCode.getAlpha2() + "/podcasts/top-podcasts/all/" + limit + "/explicit.json";
        return executeQuery(urlString);
    }

}
