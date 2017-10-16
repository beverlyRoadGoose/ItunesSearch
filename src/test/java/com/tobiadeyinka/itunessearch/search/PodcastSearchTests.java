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
import com.tobiadeyinka.itunessearch.common.enums.ReturnLanguage;
import com.tobiadeyinka.itunessearch.common.enums.ItunesApiVersion;
import com.tobiadeyinka.itunessearch.podcasts.enums.PodcastAttribute;
import com.tobiadeyinka.itunessearch.podcasts.enums.PodcastSearchReturnType;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.logging.Logger;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for all podcast search methods.
 *
 * Created by Tobi Adeyinka on 2017. 10. 15..
 */
public class PodcastSearchTests {

    private String searchTerm = "radio";
    private Logger logger = Logger.getLogger(PodcastSearchTests.class.getName());

    private PodcastsSearch search = null;
    private JSONObject response = null;

    static final String TEST_LOG_TAG = "test: ";
    static final String URL_LOG_TAG = "search url: ";
    static final String RESPONSE_LOG_TAG = "search response: ";

    @Test(expectedExceptions = MissingRequiredParameterException.class)
    public void searchForPodcastWithoutSearchTerm() throws ItunesSearchException {
        new PodcastsSearch().execute();
    }

    @Test
    public void searchForPodcastWithDefaultParameters() throws ItunesSearchException {
        nullifySearchAndResponse();

        try {
            search = new PodcastsSearch().with(searchTerm);
            response = search.execute();

            JSONArray matchingPodcastsArray = response.getJSONArray("results");
            assertThat(matchingPodcastsArray.length())
                    .isGreaterThan(0);
        } finally {
            String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
            logUrlAndResponse(methodName);
        }
    }

    @Test
    public void searchForPodcastUsingTitleAttribute() throws ItunesSearchException {
        nullifySearchAndResponse();

        try {
            search = new PodcastsSearch()
                    .with(searchTerm)
                    .inAttribute(PodcastAttribute.TITLE);
            response = search.execute();

            JSONArray matchingPodcastsArray = response.getJSONArray("results");
            assertThat(matchingPodcastsArray.length())
                    .isGreaterThan(0);
        } finally {
            String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
            logUrlAndResponse(methodName);
        }
    }

    @Test
    public void searchForPodcastInSpecificStore() throws ItunesSearchException {
        nullifySearchAndResponse();

        try {
            search = new PodcastsSearch()
                    .with(searchTerm)
                    .inCountry(CountryCode.NG);
            response = search.execute();

            JSONArray matchingPodcastsArray = response.getJSONArray("results");
            assertThat(matchingPodcastsArray.length())
                    .isGreaterThan(0);
        } finally {
            String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
            logUrlAndResponse(methodName);
        }
    }

    @Test
    public void searchForPodcastWithLimit() throws ItunesSearchException {
        nullifySearchAndResponse();

        try {
            search = new PodcastsSearch()
                    .with(searchTerm)
                    .withLimit(5);

            response = search.execute();

            JSONArray matchingPodcastsArray = response.getJSONArray("results");
            assertThat(matchingPodcastsArray.length())
                    .isGreaterThan(0)
                    .isLessThan(6);
        } finally {
            String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
            logUrlAndResponse(methodName);
        }
    }

    @Test
    public void searchForPodcastWithApiVersion1() throws ItunesSearchException {
        nullifySearchAndResponse();

        try {
            search = new PodcastsSearch()
                    .with(searchTerm)
                    .withApiVersion(ItunesApiVersion.ONE);
            response = search.execute();

            JSONArray matchingPodcastsArray = response.getJSONArray("results");
            assertThat(matchingPodcastsArray.length())
                    .isGreaterThan(0);
        } finally {
            String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
            logUrlAndResponse(methodName);
        }
    }

    @Test
    public void searchForPodcastWithJapaneseResponse() throws ItunesSearchException {
        nullifySearchAndResponse();

        try {
            search = new PodcastsSearch()
                    .with(searchTerm)
                    .withReturnLanguage(ReturnLanguage.JAPANESE);
            response = search.execute();

            JSONArray matchingPodcastsArray = response.getJSONArray("results");
            assertThat(matchingPodcastsArray.length())
                    .isGreaterThan(0);
        } finally {
            String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
            logUrlAndResponse(methodName);
        }
    }

    @Test
    public void searchForPodcastWithAuthorReturnType() throws ItunesSearchException {
        nullifySearchAndResponse();

        try {
            search = new PodcastsSearch()
                    .with(searchTerm)
                    .inAttribute(PodcastAttribute.TITLE)
                    .andReturn(PodcastSearchReturnType.PODCAST_AUTHOR);
            response = search.execute();

            JSONArray matchingPodcastsArray = response.getJSONArray("results");
            assertThat(matchingPodcastsArray.length())
                    .isGreaterThan(0);
        } finally {
            String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
            logUrlAndResponse(methodName);
        }
    }

    @Test
    public void searchForPodcastWithoutExplicitContent() throws ItunesSearchException {
        nullifySearchAndResponse();

        try {
            search = new PodcastsSearch()
                    .with(searchTerm)
                    .allowExplicit(false);
            response = search.execute();

            JSONArray matchingPodcastsArray = response.getJSONArray("results");
            assertThat(matchingPodcastsArray.length())
                    .isGreaterThan(0);
        } finally {
            String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
            logUrlAndResponse(methodName);
        }
    }

    @Test
    public void comprehensivePodcastSearch() throws ItunesSearchException {
        nullifySearchAndResponse();

        try {
            search = new PodcastsSearch()
                    .with(searchTerm)
                    .withLimit(5)
                    .inCountry(CountryCode.NG)
                    .inAttribute(PodcastAttribute.TITLE)
                    .withReturnLanguage(ReturnLanguage.JAPANESE)
                    .withApiVersion(ItunesApiVersion.ONE);
            response = search.execute();

            JSONArray matchingPodcastsArray = response.getJSONArray("results");
            assertThat(matchingPodcastsArray.length())
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
