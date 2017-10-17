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

import com.neovisionaries.i18n.CountryCode;

import com.tobiadeyinka.itunessearch.common.enums.ReturnLanguage;
import com.tobiadeyinka.itunessearch.movies.enums.MovieAttribute;
import com.tobiadeyinka.itunessearch.common.enums.ItunesApiVersion;
import com.tobiadeyinka.itunessearch.exceptions.ItunesSearchException;
import com.tobiadeyinka.itunessearch.exceptions.MissingRequiredParameterException;

import com.tobiadeyinka.itunessearch.movies.enums.MovieSearchReturnType;
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
            verifyResponseHasResults();
        } finally {
            logUrlAndResponse();
        }
    }

    @Test
    public void searchForMovieUsingDirectorAttribute() throws ItunesSearchException {
        nullifySearchAndResponse();

        try {
            String actor = "Steven";
            search = new MovieSearch()
                    .with(actor)
                    .inAttribute(MovieAttribute.DIRECTOR);
            response = search.execute();
            verifyResponseHasResults();
        } finally {
            logUrlAndResponse();
        }
    }

    @Test
    public void searchForMovieInSpecificStore() throws ItunesSearchException {
        nullifySearchAndResponse();

        try {
            search = new MovieSearch()
                    .with(searchTerm)
                    .inCountry(CountryCode.CA);
            response = search.execute();
            verifyResponseHasResults();
        } finally {
            logUrlAndResponse();
        }
    }

    @Test
    public void searchForMovieWithLimit() throws ItunesSearchException {
        nullifySearchAndResponse();

        try {
            /*
             * use a common word to match a lot of movies
             * for the sake of the limit test
             */
            String commonWord = "the";
            int limit = 5;

            search = new MovieSearch()
                    .with(commonWord)
                    .withLimit(limit);
            response = search.execute();

            verifyResponseHasResults();
            verifyResponseMatchesLimit(limit);
        } finally {
            logUrlAndResponse();
        }
    }

    @Test
    public void searchForMovieWithApiVersion1() throws ItunesSearchException {
        nullifySearchAndResponse();

        try {
            search = new MovieSearch()
                    .with(searchTerm)
                    .withApiVersion(ItunesApiVersion.ONE);
            response = search.execute();
            verifyResponseHasResults();
        } finally {
            logUrlAndResponse();
        }
    }

    @Test
    public void searchForMovieWithJapaneseResponse() throws ItunesSearchException {
        nullifySearchAndResponse();

        try {
            search = new MovieSearch()
                    .with(searchTerm)
                    .withReturnLanguage(ReturnLanguage.JAPANESE);
            response = search.execute();
            verifyResponseHasResults();
        } finally {
            logUrlAndResponse();
        }
    }

    @Test
    public void searchForMovieWithArtistReturnType() throws ItunesSearchException {
        nullifySearchAndResponse();

        try {
            search = new MovieSearch()
                    .with(searchTerm)
                    .andReturn(MovieSearchReturnType.MOVIE_ARTIST);
            response = search.execute();
            verifyResponseHasResults();
        } finally {
            logUrlAndResponse();
        }
    }

    @Test
    public void searchForMovieWithoutExplicitContent() throws ItunesSearchException {
        nullifySearchAndResponse();

        try {
            search = new MovieSearch()
                    .with(searchTerm)
                    .allowExplicit(false);
            response = search.execute();
            verifyResponseHasResults();
        } finally {
            logUrlAndResponse();
        }
    }

    @Test
    public void comprehensiveMovieSearch() throws ItunesSearchException {
        nullifySearchAndResponse();

        try {
            int limit = 5;

            search = new MovieSearch()
                    .with(searchTerm)
                    .withLimit(limit)
                    .inCountry(CountryCode.CA)
                    .inAttribute(MovieAttribute.ARTIST)
                    .withReturnLanguage(ReturnLanguage.JAPANESE)
                    .withApiVersion(ItunesApiVersion.ONE);
            response = search.execute();

            verifyResponseHasResults();
            verifyResponseMatchesLimit(limit);
        } finally {
            logUrlAndResponse();
        }
    }

    private void verifyResponseHasResults() {
        assertThat(response.has("results"));
    }

    private void verifyResponseMatchesLimit(int limit) {
        JSONArray matchingPodcastsArray = response.getJSONArray("results");
        assertThat(matchingPodcastsArray.length())
                .isGreaterThan(0)
                .isLessThan(limit + 1);
    }

    private void logUrlAndResponse() {
        String callingMethodName = Thread.currentThread().getStackTrace()[2].getMethodName();

        if (search != null && response != null) {
            logger.info("\n" + TEST_LOG_TAG + callingMethodName + "\n"
                    + URL_LOG_TAG + search.getSearchUrl() + "\n"
                    + RESPONSE_LOG_TAG + response.toString() + "\n\n");
        }
    }

    /*
     * search and response objects are nullified at the start of
     * each tests to prevent the values from a previous test from
     * being used when the current test fails.
     */
    private void nullifySearchAndResponse() {
        search = null;
        response = null;
    }

}
