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

import com.tobiadeyinka.itunessearch.entities.ReturnLanguage;
import com.tobiadeyinka.itunessearch.entities.ItunesApiVersion;

import com.tobiadeyinka.itunessearch.entities.short_films.ShortFilmAttribute;
import com.tobiadeyinka.itunessearch.entities.short_films.ShortFilmSearchReturnType;

import com.tobiadeyinka.itunessearch.exceptions.ItunesSearchException;
import com.tobiadeyinka.itunessearch.exceptions.MissingRequiredParameterException;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.logging.Logger;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for all short film search methods.
 *
 * Created by Tobi Adeyinka on 2017. 10. 19..
 */
public class ShortFilmSearchTests {

    private String searchTerm = "the";
    private Logger logger = Logger.getLogger(ShortFilmSearchTests.class.getName());

    private ShortFilmSearch search = null;
    private JSONObject response = null;

    private static final String TEST_LOG_TAG = "test: ";
    private static final String URL_LOG_TAG = "search url: ";
    private static final String RESPONSE_LOG_TAG = "search response: ";

    @Test(expectedExceptions = MissingRequiredParameterException.class)
    public void searchForShortFilmWithoutSearchTerm() throws ItunesSearchException {
        new ShortFilmSearch().execute();
    }

    @Test
    public void searchForShortFilmWithDefaultParameters() throws ItunesSearchException {
        nullifySearchAndResponse();

        try {
            search = new ShortFilmSearch().with(searchTerm);
            response = search.execute();
            verifyResponseHasResults();
        } finally {
            logUrlAndResponse();
        }
    }

    @Test
    public void searchForShortFilmUsingArtistAttribute() throws ItunesSearchException {
        nullifySearchAndResponse();

        try {
            search = new ShortFilmSearch()
                    .with(searchTerm)
                    .inAttribute(ShortFilmAttribute.ARTIST);
            response = search.execute();
            verifyResponseHasResults();
        } finally {
            logUrlAndResponse();
        }
    }

    @Test
    public void searchForShortFilmInSpecificStore() throws ItunesSearchException {
        nullifySearchAndResponse();

        try {
            search = new ShortFilmSearch()
                    .with(searchTerm)
                    .inCountry(CountryCode.NG);
            response = search.execute();
            verifyResponseHasResults();
        } finally {
            logUrlAndResponse();
        }
    }

    @Test
    public void searchForShortFilmWithLimit() throws ItunesSearchException {
        nullifySearchAndResponse();

        try {
            int limit = 5;
            search = new ShortFilmSearch()
                    .with(searchTerm)
                    .withLimit(limit);
            response = search.execute();

            verifyResponseHasResults();
            verifyResponseMatchesLimit(limit);
        } finally {
            logUrlAndResponse();
        }
    }

    @Test
    public void searchForShortFilmWithApiVersion1() throws ItunesSearchException {
        nullifySearchAndResponse();

        try {
            search = new ShortFilmSearch()
                    .with(searchTerm)
                    .withApiVersion(ItunesApiVersion.ONE);
            response = search.execute();
            verifyResponseHasResults();
        } finally {
            logUrlAndResponse();
        }
    }

    @Test
    public void searchForShortFilmWithJapaneseResponse() throws ItunesSearchException {
        nullifySearchAndResponse();

        try {
            search = new ShortFilmSearch()
                    .with(searchTerm)
                    .withReturnLanguage(ReturnLanguage.JAPANESE);
            response = search.execute();
            verifyResponseHasResults();
        } finally {
            logUrlAndResponse();
        }
    }

    @Test
    public void searchForShortFilmWithArtistReturnType() throws ItunesSearchException {
        nullifySearchAndResponse();

        try {
            search = new ShortFilmSearch()
                    .with(searchTerm)
                    .inAttribute(ShortFilmAttribute.ARTIST)
                    .andReturn(ShortFilmSearchReturnType.ARTIST);
            response = search.execute();
            verifyResponseHasResults();
        } finally {
            logUrlAndResponse();
        }
    }

    @Test
    public void searchForShortFilmWithoutExplicitContent() throws ItunesSearchException {
        nullifySearchAndResponse();

        try {
            search = new ShortFilmSearch()
                    .with(searchTerm)
                    .allowExplicit(false);
            response = search.execute();
            verifyResponseHasResults();
        } finally {
            logUrlAndResponse();
        }
    }

    @Test
    public void comprehensiveShortFilmSearch() throws ItunesSearchException {
        nullifySearchAndResponse();

        try {
            int limit = 5;
            search = new ShortFilmSearch()
                    .with(searchTerm)
                    .withLimit(limit)
                    .inCountry(CountryCode.US)
                    .inAttribute(ShortFilmAttribute.ARTIST)
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
                .isGreaterThanOrEqualTo(0)
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
