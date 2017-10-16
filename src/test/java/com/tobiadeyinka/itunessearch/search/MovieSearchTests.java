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

package com.tobiadeyinka.itunessearch.search;

import com.tobiadeyinka.itunessearch.movies.enums.MovieAttribute;
import com.tobiadeyinka.itunessearch.exceptions.ItunesSearchException;
import com.tobiadeyinka.itunessearch.exceptions.MissingRequiredParameterException;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.logging.Logger;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for all movie search methods.
 *
 * Created by Tobi Adeyinka on 2017. 10. 16..
 */
public class MovieSearchTests {

    private String searchTerm = "the imitation game";
    private Logger logger = Logger.getLogger(MovieSearchTests.class.getName());

    private MovieSearch search = null;
    private JSONObject response = null;

    static final String TEST_LOG_TAG = "test: ";
    private static final String URL_LOG_TAG = "search url: ";
    private static final String RESPONSE_LOG_TAG = "search response: ";

    @Test(expectedExceptions = MissingRequiredParameterException.class)
    public void searchForMovieWithoutSearchTerm() throws ItunesSearchException {
        new MovieSearch().execute();
    }

    @Test
    public void searchForMovieWithDefaultParameters() throws ItunesSearchException {
        nullifySearchAndResponse();

        try {
            search = new MovieSearch().with(searchTerm);
            response = search.execute();

            JSONArray matchingMusicArray = response.getJSONArray("results");
            assertThat(matchingMusicArray.length())
                    .isGreaterThan(0);

        } finally {
            String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
            logUrlAndResponse(methodName);
        }
    }

    @Test
    public void searchForMusicUsingDirectorAttribute() throws ItunesSearchException {
        nullifySearchAndResponse();

        try {
            String actor = "Steven";

            search = new MovieSearch()
                    .with(actor)
                    .inAttribute(MovieAttribute.DIRECTOR);
            response = search.execute();

            JSONArray matchingMusicArray = response.getJSONArray("results");
            assertThat(matchingMusicArray.length())
                    .isGreaterThan(0);
        } finally {
            String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
            logUrlAndResponse(methodName);
        }
    }

    private void logUrlAndResponse(String callingMethod) {
        if (search != null && response != null) {
            logger.info("\n" + TEST_LOG_TAG + callingMethod + "\n"
                    + URL_LOG_TAG + search.getSearchUrl() + "\n"
                    + RESPONSE_LOG_TAG + response.toString() + "\n\n");
        }
    }

    /*
     * search and response objects are nullified at the start of
     * each tests to prevent the values from a previous test from
     * being logged when the current test fails.
     */
    private void nullifySearchAndResponse() {
        search = null;
        response = null;
    }

}
