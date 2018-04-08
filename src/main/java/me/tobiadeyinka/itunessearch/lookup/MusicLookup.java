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
 * Created by Tobi Adeyinka on 2018. 04. 07..
 */
public abstract class MusicLookup extends Lookup {

    /**
     * get a song by it's trackId
     *
     * @param trackId The trackId of the song
     * @return a JSONObject of the song
     * @throws NoMatchFoundException if no song is found with the passed trackId
     */
    public static JSONObject getSongByTrackId(long trackId) throws NoMatchFoundException {
        return getById(trackId);
    }

    /**
     * get the top {@value #DEFAULT_LIMIT} songs in the default iTunes store
     *
     * @return a JSONObject containing a list of the top songs
     */
    public static JSONObject topSongs() {
        return topSongs(DEFAULT_LIMIT);
    }

    /**
     * get the top (limit) songs in the default iTunes store
     *
     * @param limit the maximum number of songs to return
     * @return a JSONObject containing a list of the top songs
     */
    public static JSONObject topSongs(int limit) {
        return queryTopSongs(DEFAULT_COUNTRY, limit);
    }

    /**
     * get the top {@value #DEFAULT_LIMIT} songs in the specified iTunes store
     *
     * @param countryCode country code of the itunes store to search
     * @return a JSONObject containing a list of the top songs
     */
    public static JSONObject topSongs(CountryCode countryCode) {
        return queryTopSongs(countryCode, DEFAULT_LIMIT);
    }

    /**
     * get the top (limit) songs in the specified iTunes store
     *
     * @param countryCode country code of the itunes store to search
     * @param limit the maximum number of songs to return
     * @return a JSONObject containing a list of the top songs
     */
    public static JSONObject topSongs(CountryCode countryCode, int limit) {
        return queryTopSongs(countryCode, limit);
    }

    private static JSONObject queryTopSongs(CountryCode countryCode, int limit) {
        String urlString = "https://rss.itunes.apple.com/api/v1/" + countryCode.getAlpha2() + "/itunes-music/top-songs/all/" + limit + "/explicit.json";
        return executeQuery(urlString);
    }

}
