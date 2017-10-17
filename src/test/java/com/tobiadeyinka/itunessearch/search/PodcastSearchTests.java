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

    private PodcastSearch search = null;
    private JSONObject response = null;

    private static final String TEST_LOG_TAG = "test: ";
    private static final String URL_LOG_TAG = "search url: ";
    private static final String RESPONSE_LOG_TAG = "search response: ";

    @Test(expectedExceptions = MissingRequiredParameterException.class)
    public void searchForPodcastWithoutSearchTerm() throws ItunesSearchException {
        new PodcastSearch().execute();
    }

    @Test
    public void searchForPodcastWithDefaultParameters() throws ItunesSearchException {
        nullifySearchAndResponse();

        try {
            search = new PodcastSearch().with(searchTerm);
            response = search.execute();
            verifyResponseHasResults();
        } finally {
            logUrlAndResponse();
        }
    }

    @Test
    public void searchForPodcastUsingTitleAttribute() throws ItunesSearchException {
        nullifySearchAndResponse();

        try {
            search = new PodcastSearch()
                    .with(searchTerm)
                    .inAttribute(PodcastAttribute.TITLE);
            response = search.execute();
            verifyResponseHasResults();
        } finally {
            logUrlAndResponse();
        }
    }

    @Test
    public void searchForPodcastInSpecificStore() throws ItunesSearchException {
        nullifySearchAndResponse();

        try {
            search = new PodcastSearch()
                    .with(searchTerm)
                    .inCountry(CountryCode.NG);
            response = search.execute();
            verifyResponseHasResults();
        } finally {
            logUrlAndResponse();
        }
    }

    @Test
    public void searchForPodcastWithLimit() throws ItunesSearchException {
        nullifySearchAndResponse();

        try {
            int limit = 5;
            search = new PodcastSearch()
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
    public void searchForPodcastWithApiVersion1() throws ItunesSearchException {
        nullifySearchAndResponse();

        try {
            search = new PodcastSearch()
                    .with(searchTerm)
                    .withApiVersion(ItunesApiVersion.ONE);
            response = search.execute();
            verifyResponseHasResults();
        } finally {
            logUrlAndResponse();
        }
    }

    @Test
    public void searchForPodcastWithJapaneseResponse() throws ItunesSearchException {
        nullifySearchAndResponse();

        try {
            search = new PodcastSearch()
                    .with(searchTerm)
                    .withReturnLanguage(ReturnLanguage.JAPANESE);
            response = search.execute();
            verifyResponseHasResults();
        } finally {
            logUrlAndResponse();
        }
    }

    @Test
    public void searchForPodcastWithAuthorReturnType() throws ItunesSearchException {
        nullifySearchAndResponse();

        try {
            search = new PodcastSearch()
                    .with(searchTerm)
                    .inAttribute(PodcastAttribute.TITLE)
                    .andReturn(PodcastSearchReturnType.PODCAST_AUTHOR);
            response = search.execute();
            verifyResponseHasResults();
        } finally {
            logUrlAndResponse();
        }
    }

    @Test
    public void searchForPodcastWithoutExplicitContent() throws ItunesSearchException {
        nullifySearchAndResponse();

        try {
            search = new PodcastSearch()
                    .with(searchTerm)
                    .allowExplicit(false);
            response = search.execute();
            verifyResponseHasResults();
        } finally {
            logUrlAndResponse();
        }
    }

    @Test
    public void comprehensivePodcastSearch() throws ItunesSearchException {
        nullifySearchAndResponse();

        try {
            int limit = 5;
            search = new PodcastSearch()
                    .with(searchTerm)
                    .withLimit(limit)
                    .inCountry(CountryCode.NG)
                    .inAttribute(PodcastAttribute.TITLE)
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
