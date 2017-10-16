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

    static final String URL_LOG_TAG = "search url: ";
    static final String RESPONSE_LOG_TAG = "search response: ";


    @Test(expectedExceptions = MissingRequiredParameterException.class)
    public void searchForMusicWithoutSearchTerm() throws ItunesSearchException {
        new MusicSearch().execute();
    }

    @Test
    public void searchForMusicWithDefaultParameters() throws ItunesSearchException {
        try {
            search = new MusicSearch().with(searchTerm);
            response = search.execute();

            JSONArray matchingMusicArray = response.getJSONArray("results");
            assertThat(matchingMusicArray.length())
                    .isGreaterThan(0);
        } finally {
            logUrlAndResponse();
        }
    }

    @Test
    public void searchForMusicUsingArtistAttribute() throws ItunesSearchException {
        try {
            search = new MusicSearch()
                    .with(searchTerm)
                    .inAttribute(MusicAttribute.ARTIST);
            response = search.execute();

            JSONArray matchingMusicArray = response.getJSONArray("results");
            assertThat(matchingMusicArray.length())
                    .isGreaterThan(0);
        } finally {
            logUrlAndResponse();
        }
    }

    @Test
    public void searchForMusicInSpecificStore() throws ItunesSearchException {
        try {
            search = new MusicSearch()
                    .with(searchTerm)
                    .inCountry(CountryCode.NG);
            response = search.execute();

            JSONArray matchingMusicArray = response.getJSONArray("results");
            assertThat(matchingMusicArray.length())
                    .isGreaterThan(0);
        } finally {
            logUrlAndResponse();
        }
    }

    @Test
    public void searchForMusicWithLimit() throws ItunesSearchException {
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
            logUrlAndResponse();
        }
    }

    @Test
    public void searchForMusicWithApiVersion1() throws ItunesSearchException {
        try {
            search = new MusicSearch()
                    .with(searchTerm)
                    .withApiVersion(ItunesApiVersion.ONE);
            response = search.execute();

            JSONArray matchingMusicArray = response.getJSONArray("results");
            assertThat(matchingMusicArray.length())
                    .isGreaterThan(0);
        } finally {
            logUrlAndResponse();
        }
    }

    @Test
    public void searchForMusicWithJapaneseResponse() throws ItunesSearchException {
        try {
            search = new MusicSearch()
                    .with(searchTerm)
                    .withReturnLanguage(ReturnLanguage.JAPANESE);
            response = search.execute();

            JSONArray matchingMusicArray = response.getJSONArray("results");
            assertThat(matchingMusicArray.length())
                    .isGreaterThan(0);
        } finally {
            logUrlAndResponse();
        }
    }

    @Test
    public void searchForMusicWithArtistReturnType() throws ItunesSearchException {
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
            logUrlAndResponse();
        }
    }

    @Test
    public void searchForMusicWithoutExplicitContent() throws ItunesSearchException {
        try {
            search = new MusicSearch()
                    .with(searchTerm)
                    .allowExplicit(false);
            response = search.execute();

            JSONArray matchingMusicArray = response.getJSONArray("results");
            assertThat(matchingMusicArray.length())
                    .isGreaterThan(0);
        } finally {
            logUrlAndResponse();
        }
    }

    @Test
    public void comprehensiveMusicSearch() throws ItunesSearchException {
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
            logUrlAndResponse();
        }
    }

    private void logUrlAndResponse() {
        logger.info(URL_LOG_TAG + search.getSearchUrl());
        logger.info(RESPONSE_LOG_TAG + response.toString());
    }

}
