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

import org.json.JSONObject;

import me.tobiadeyinka.itunessearch.exceptions.NoMatchFoundException;

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

    public static JSONObject getMoviebyId(long id) throws NoMatchFoundException {
        return getById(id);
    }

    public static JSONObject topMovies() {
        return null;
    }
    
}
