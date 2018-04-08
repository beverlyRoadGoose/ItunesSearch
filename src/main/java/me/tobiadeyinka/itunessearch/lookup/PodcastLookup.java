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

import me.tobiadeyinka.itunessearch.exceptions.NoMatchFoundException;

import org.json.JSONObject;
import com.neovisionaries.i18n.CountryCode;

/**
 * This class manages looking up podcasts with different attributes
 *
 * Created by Tobi Adeyinka on 2017. 10. 18..
 */
public abstract class PodcastLookup extends Lookup {

    private enum PodcastGenre {
        COMEDY(1303),
        NEWS_AND_POLITICS(1311),
        SOCIETY_AND_CULTURE(1324);

        int id;

        PodcastGenre(int id) {
            this.id = id;
        }
    }

    /**
     * get a podcast by it's collectionId
     *
     * @param collectionId The collectionId of the podcast
     * @return a JSONObject of the podcast
     * @throws NoMatchFoundException if no podcast is found with the passed collectionId
     */
    public static JSONObject getPodcastByCollectionId(long collectionId) throws NoMatchFoundException {
        return getById(collectionId);
    }

    /**
     * get the top {@value me.tobiadeyinka.itunessearch.lookup.Lookup#DEFAULT_LIMIT} podcasts in the default iTunes store
     *
     * @return a JSONObject containing a list of the top podcasts
     */
    public static JSONObject topPodcasts() {
        return topPodcasts(DEFAULT_LIMIT);
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
     * get the top {@value me.tobiadeyinka.itunessearch.lookup.Lookup#DEFAULT_LIMIT} podcasts in the specified iTunes store
     *
     * @param countryCode country code of the itunes store to search
     * @return a JSONObject containing a list of the top podcasts
     */
    public static JSONObject topPodcasts(CountryCode countryCode) {
        return queryTopPodcasts(countryCode, DEFAULT_LIMIT);
    }

    /**
     * get the top (limit) podcasts in the specified iTunes store
     *
     * @param limit the maximum number of podcasts to return
     * @param countryCode country code of the itunes store to search
     * @return a JSONObject containing a list of the top podcasts
     */
    public static JSONObject topPodcasts(CountryCode countryCode, int limit) {
        return queryTopPodcasts(countryCode, limit);
    }

    /**
     * get a list of {@value me.tobiadeyinka.itunessearch.lookup.Lookup#DEFAULT_LIMIT} comedy podcasts in the iTunes store
     *
     * @return a JSONObject containing a list of the top podcasts
     */
    public static JSONObject comedyPodcasts() {
        return getPodcastGenre(PodcastGenre.COMEDY, DEFAULT_LIMIT);
    }

    /**
     * get a list of (limit) comedy podcasts in the iTunes store
     *
     * @param limit the maximum number of podcasts to return
     * @return a JSONObject containing a list of the top podcasts
     */
    public static JSONObject comedyPodcasts(int limit) {
        return getPodcastGenre(PodcastGenre.COMEDY, limit);
    }

    /**
     * get a list of {@value me.tobiadeyinka.itunessearch.lookup.Lookup#DEFAULT_LIMIT} news &amp; politics podcasts in the iTunes store
     *
     * @return a JSONObject containing a list of the top podcasts
     */
    public static JSONObject newsAndPoliticsPodcasts() {
        return getPodcastGenre(PodcastGenre.NEWS_AND_POLITICS, DEFAULT_LIMIT);
    }

    /**
     * get a list of (limit) news &amp; politics podcasts in the iTunes store
     *
     * @param limit the maximum number of podcasts to return
     * @return a JSONObject containing a list of the top podcasts
     */
    public static JSONObject newsAndPoliticsPodcasts(int limit) {
        return getPodcastGenre(PodcastGenre.NEWS_AND_POLITICS, limit);
    }

    /**
     * get a list of {@value me.tobiadeyinka.itunessearch.lookup.Lookup#DEFAULT_LIMIT} society &amp; culture podcasts in the iTunes store
     *
     * @return a JSONObject containing a list of the top podcasts
     */
    public static JSONObject societyAndCulturePodcasts() {
        return getPodcastGenre(PodcastGenre.NEWS_AND_POLITICS, DEFAULT_LIMIT);
    }

    /**
     * get a list of (limit) society &amp; culture podcasts in the iTunes store
     *
     * @param limit the maximum number of podcasts to return
     * @return a JSONObject containing a list of the top podcasts
     */
    public static JSONObject societyAndCulturePodcasts(int limit) {
        return getPodcastGenre(PodcastGenre.SOCIETY_AND_CULTURE, limit);
    }

    /**
     * Get podcasts by their genre id
     *
     * @param genre the podcasts genre
     * @param limit maximum number of returned elements
     * @return a JSONObject containing a list of the matching podcasts
     */
    private static JSONObject getPodcastGenre(PodcastGenre genre, int limit) {
        String urlString = "https://itunes.apple.com/search?term=podcast&limit=" + limit + "&genreId=" + genre.id;
        return executeQuery(urlString);
    }

    private static JSONObject queryTopPodcasts(CountryCode countryCode, int limit) {
        String urlString = "https://rss.itunes.apple.com/api/v1/" + countryCode.getAlpha2() + "/podcasts/top-podcasts/all/" + limit + "/explicit.json";
        return executeQuery(urlString);
    }

}
