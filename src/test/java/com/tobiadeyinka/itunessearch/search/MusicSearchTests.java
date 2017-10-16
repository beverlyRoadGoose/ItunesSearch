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

import com.tobiadeyinka.itunessearch.exceptions.*;
import com.tobiadeyinka.itunessearch.music.enums.MusicAttribute;
import com.tobiadeyinka.itunessearch.common.enums.ReturnLanguage;
import com.tobiadeyinka.itunessearch.common.enums.ItunesApiVersion;
import com.tobiadeyinka.itunessearch.music.enums.MusicSearchReturnType;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.logging.Logger;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for all music search methods.
 *
 * Created by Tobi Adeyinka on 2017. 10. 16..
 */
public class MusicSearchTests {

    private String searchTerm = "the national";
    private Logger logger = Logger.getLogger(MusicSearchTests.class.getName());

    private MusicSearch search = null;
    private JSONObject response = null;

    static final String TEST_LOG_TAG = "test: ";
    static final String URL_LOG_TAG = "search url: ";
    static final String RESPONSE_LOG_TAG = "search response: ";

    @Test(expectedExceptions = MissingRequiredParameterException.class)
    public void searchForMusicWithoutSearchTerm() throws ItunesSearchException {
        new MusicSearch().execute();
    }

    @Test
    public void searchForMusicWithDefaultParameters() throws ItunesSearchException {
        nullifySearchAndResponse();

        try {
            search = new MusicSearch().with(searchTerm);
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
    public void searchForMusicUsingArtistAttribute() throws ItunesSearchException {
        nullifySearchAndResponse();

        try {
            search = new MusicSearch()
                    .with(searchTerm)
                    .inAttribute(MusicAttribute.ARTIST);
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
    public void searchForMusicInSpecificStore() throws ItunesSearchException {
        nullifySearchAndResponse();

        try {
            search = new MusicSearch()
                    .with(searchTerm)
                    .inCountry(CountryCode.NG);
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
    public void searchForMusicWithLimit() throws ItunesSearchException {
        nullifySearchAndResponse();

        try {
            search = new MusicSearch()
                    .with(searchTerm)
                    .withLimit(5);
            response = search.execute();

            JSONArray matchingMusicArray = response.getJSONArray("results");
            assertThat(matchingMusicArray.length())
                    .isGreaterThan(0)
                    .isLessThan(6);
        } finally {
            String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
            logUrlAndResponse(methodName);
        }
    }

    @Test
    public void searchForMusicWithApiVersion1() throws ItunesSearchException {
        nullifySearchAndResponse();

        try {
            search = new MusicSearch()
                    .with(searchTerm)
                    .withApiVersion(ItunesApiVersion.ONE);
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
    public void searchForMusicWithJapaneseResponse() throws ItunesSearchException {
        nullifySearchAndResponse();

        try {
            search = new MusicSearch()
                    .with(searchTerm)
                    .withReturnLanguage(ReturnLanguage.JAPANESE);
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
    public void searchForMusicWithArtistReturnType() throws ItunesSearchException {
        nullifySearchAndResponse();

        try {
            search = new MusicSearch()
                    .with(searchTerm)
                    .inAttribute(MusicAttribute.ARTIST)
                    .andReturn(MusicSearchReturnType.ARTIST);
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
    public void searchForMusicWithoutExplicitContent() throws ItunesSearchException {
        nullifySearchAndResponse();

        try {
            search = new MusicSearch()
                    .with(searchTerm)
                    .allowExplicit(false);
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
    public void comprehensiveMusicSearch() throws ItunesSearchException {
        nullifySearchAndResponse();

        try {
            search = new MusicSearch()
                    .with(searchTerm)
                    .withLimit(5)
                    .inCountry(CountryCode.NG)
                    .inAttribute(MusicAttribute.ARTIST)
                    .withReturnLanguage(ReturnLanguage.JAPANESE)
                    .withApiVersion(ItunesApiVersion.ONE);
            response = search.execute();

            JSONArray matchingMusicArray = response.getJSONArray("results");
            assertThat(matchingMusicArray.length())
                    .isGreaterThan(0)
                    .isLessThan(6);
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
