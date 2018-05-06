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
 * This class manages looking up movies.
 *
 * Created by Tobi Adeyinka on 2018. 05. 05..
 */
public class MovieLookup extends Lookup {

    private enum MovieList {
        TOP_MOVIES("top-movies");

        String urlKey;

        MovieList(String urlKey) {
            this.urlKey = urlKey;
        }
    }

    /**
     * get a movie by it's id
     *
     * @param id The id of the movie
     * @return a JSONObject of the movie
     * @throws NoMatchFoundException if no movie is found with the passed id
     */
    public static JSONObject getMoviebyId(long id) throws NoMatchFoundException {
        return getById(id);
    }

    /**
     * get the top {@value me.tobiadeyinka.itunessearch.lookup.Lookup#DEFAULT_LIMIT} movies in the default iTunes store
     *
     * @return a JSONObject containing a list of the top movies
     */
    public static JSONObject topMovies() {
        return topMovies(DEFAULT_LIMIT);
    }

    /**
     * get the top (limit) movies in the default iTunes store
     *
     * @param limit the maximum number of movies to return
     * @return a JSONObject containing a list of the top movies
     */
    public static JSONObject topMovies(int limit) {
        return queryMovieList(MovieList.TOP_MOVIES, DEFAULT_COUNTRY, limit);
    }

    /**
     * get the top {@value me.tobiadeyinka.itunessearch.lookup.Lookup#DEFAULT_LIMIT} movies in the specified iTunes store
     *
     * @param countryCode country code of the itunes store to search
     * @return a JSONObject containing a list of the top movies
     */
    public static JSONObject topMovies(CountryCode countryCode) {
        return queryMovieList(MovieList.TOP_MOVIES, countryCode, DEFAULT_LIMIT);
    }

    /**
     * get the top (limit) movies in the specified iTunes store
     *
     * @param countryCode country code of the itunes store to search
     * @param limit the maximum number of movies to return
     * @return a JSONObject containing a list of the top songs
     */
    public static JSONObject topMovies(CountryCode countryCode, int limit) {
        return queryMovieList(MovieList.TOP_MOVIES, countryCode, limit);
    }

    private static JSONObject queryMovieList(MovieList list, CountryCode countryCode, int limit) {
        String urlString = "https://rss.itunes.apple.com/api/v1/" + countryCode.getAlpha2() + "/movies/" +
                list.urlKey + "/all/" + limit + "/explicit.json";
        return executeQuery(urlString);
    }

}
