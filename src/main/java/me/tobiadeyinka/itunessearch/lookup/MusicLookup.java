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

    private enum MusicList {
        TOP_SONGS("top-songs"),
        HOT_TRACKS("hot-tracks"),
        NEW_MUSIC("new-music"),
        RECENT_RELEASES("recent-releases");

        String urlKey;

        MusicList(String urlKey) {
            this.urlKey = urlKey;
        }
    }

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
     * get the top {@value me.tobiadeyinka.itunessearch.lookup.Lookup#DEFAULT_LIMIT} songs in the default iTunes store
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
        return querySongList(MusicList.TOP_SONGS, DEFAULT_COUNTRY, limit);
    }

    /**
     * get the top {@value me.tobiadeyinka.itunessearch.lookup.Lookup#DEFAULT_LIMIT} songs in the specified iTunes store
     *
     * @param countryCode country code of the itunes store to search
     * @return a JSONObject containing a list of the top songs
     */
    public static JSONObject topSongs(CountryCode countryCode) {
        return querySongList(MusicList.TOP_SONGS, countryCode, DEFAULT_LIMIT);
    }

    /**
     * get the top (limit) songs in the specified iTunes store
     *
     * @param countryCode country code of the itunes store to search
     * @param limit the maximum number of songs to return
     * @return a JSONObject containing a list of the top songs
     */
    public static JSONObject topSongs(CountryCode countryCode, int limit) {
        return querySongList(MusicList.TOP_SONGS, countryCode, limit);
    }

    /**
     * get the top {@value me.tobiadeyinka.itunessearch.lookup.Lookup#DEFAULT_LIMIT} hot tracks in the default iTunes store
     *
     * @return a JSONObject containing a list of the tracks
     */
    public static JSONObject hotTracks() {
        return hotTracks(DEFAULT_LIMIT);
    }

    /**
     * get the top (limit) hot tracks in the default iTunes store
     *
     * @param limit the maximum number of songs to return
     * @return a JSONObject containing a list of the tracks
     */
    public static JSONObject hotTracks(int limit) {
        return querySongList(MusicList.HOT_TRACKS, DEFAULT_COUNTRY, limit);
    }

    /**
     * get the top {@value me.tobiadeyinka.itunessearch.lookup.Lookup#DEFAULT_LIMIT} hot tracks in the specified iTunes store
     *
     * @param countryCode country code of the itunes store to search
     * @return a JSONObject containing a list of the tracks
     */
    public static JSONObject hotTracks(CountryCode countryCode) {
        return querySongList(MusicList.HOT_TRACKS, countryCode, DEFAULT_LIMIT);
    }

    /**
     * get the top (limit) hot tracks in the specified iTunes store
     *
     * @param countryCode country code of the itunes store to search
     * @param limit the maximum number of tracks to return
     * @return a JSONObject containing a list of the tracks
     */
    public static JSONObject hotTracks(CountryCode countryCode, int limit) {
        return querySongList(MusicList.HOT_TRACKS, countryCode, limit);
    }

    /**
     * get the top {@value me.tobiadeyinka.itunessearch.lookup.Lookup#DEFAULT_LIMIT} new songs in the default iTunes store
     *
     * @return a JSONObject containing a list of the songs
     */
    public static JSONObject newMusic() {
        return newMusic(DEFAULT_LIMIT);
    }

    /**
     * get the top (limit) new songs in the default iTunes store
     *
     * @param limit the maximum number of songs to return
     * @return a JSONObject containing a list of the songs
     */
    public static JSONObject newMusic(int limit) {
        return querySongList(MusicList.NEW_MUSIC, DEFAULT_COUNTRY, limit);
    }

    /**
     * get the top {@value me.tobiadeyinka.itunessearch.lookup.Lookup#DEFAULT_LIMIT} new songs in the specified iTunes store
     *
     * @param countryCode country code of the itunes store to search
     * @return a JSONObject containing a list of the songs
     */
    public static JSONObject newMusic(CountryCode countryCode) {
        return querySongList(MusicList.NEW_MUSIC, countryCode, DEFAULT_LIMIT);
    }

    /**
     * get the top (limit) new songs in the specified iTunes store
     *
     * @param countryCode country code of the itunes store to search
     * @param limit the maximum number of songs to return
     * @return a JSONObject containing a list of the songs
     */
    public static JSONObject newMusic(CountryCode countryCode, int limit) {
        return querySongList(MusicList.NEW_MUSIC, countryCode, limit);
    }

    /**
     * get the top {@value me.tobiadeyinka.itunessearch.lookup.Lookup#DEFAULT_LIMIT} recent releases in the default iTunes store
     *
     * @return a JSONObject containing a list of the songs
     */
    public static JSONObject recentReleases() {
        return recentReleases(DEFAULT_LIMIT);
    }

    /**
     * get the top (limit) recent releases in the default iTunes store
     *
     * @param limit the maximum number of songs to return
     * @return a JSONObject containing a list of the songs
     */
    public static JSONObject recentReleases(int limit) {
        return querySongList(MusicList.RECENT_RELEASES, DEFAULT_COUNTRY, limit);
    }

    /**
     * get the top {@value me.tobiadeyinka.itunessearch.lookup.Lookup#DEFAULT_LIMIT} recent releases in the specified iTunes store
     *
     * @param countryCode country code of the itunes store to search
     * @return a JSONObject containing a list of the songs
     */
    public static JSONObject recentReleases(CountryCode countryCode) {
        return querySongList(MusicList.RECENT_RELEASES, countryCode, DEFAULT_LIMIT);
    }

    /**
     * get the top (limit) recent releases in the specified iTunes store
     *
     * @param countryCode country code of the itunes store to search
     * @param limit the maximum number of songs to return
     * @return a JSONObject containing a list of the songs
     */
    public static JSONObject recentReleases(CountryCode countryCode, int limit) {
        return querySongList(MusicList.RECENT_RELEASES, countryCode, limit);
    }

    private static JSONObject querySongList(MusicList list, CountryCode countryCode, int limit) {
        String urlString = "https://rss.itunes.apple.com/api/v1/" + countryCode.getAlpha2() + "/itunes-music/" +
                list.urlKey + "/all/" + limit + "/explicit.json";
        return executeQuery(urlString);
    }

}
