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

package com.tobiadeyinka.itunessearch.lookup;

import com.tobiadeyinka.itunessearch.TestUtils;
import com.tobiadeyinka.itunessearch.exceptions.ItunesSearchException;

import org.json.JSONArray;
import org.json.JSONObject;

import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;

import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for all podcast look up methods
 *
 * Created by Tobi Adeyinka on 2017. 10. 18..
 */
public class PodcastLookupTests {

    private Logger logger = Logger.getLogger(PodcastLookupTests.class.getName());

    private JSONObject response = null;

    private static final String TEST_LOG_TAG = "test: ";
    private static final String RESPONSE_LOG_TAG = "response: ";

    @Test
    public void getTopPodcasts() throws ItunesSearchException {
        nullifyResponse();

        try {
            response = PodcastLookup.topPodcasts();
            verifyResponseHasResults();
        } finally {
            logResponse();
        }
    }

    @Test
    public void getTopPodcastsWithLimit() throws ItunesSearchException {
        nullifyResponse();

        try {
            int limit = 5;
            response = PodcastLookup.topPodcasts(limit);
            verifyResponseHasResults();
            verifyResponseMatchesLimit(limit);
        } finally {
            logResponse();
        }
    }

    @Test
    public void getComedyPodcasts() throws ItunesSearchException {
        nullifyResponse();

        try {
            response = PodcastLookup.comedyPodcasts();
            verifyResponseHasResults();
        } finally {
            logResponse();
        }
    }

    @Test
    public void getComedyPodcastsWithLimit() throws ItunesSearchException {
        nullifyResponse();

        try {
            int limit = 5;
            response = PodcastLookup.comedyPodcasts(limit);
            verifyResponseHasResults();
            verifyResponseMatchesLimit(limit);
        } finally {
            logResponse();
        }
    }

    @Test
    public void getNewsAndPoliticsPodcasts() throws ItunesSearchException {
        nullifyResponse();

        try {
            response = PodcastLookup.newsAndPoliticsPodcasts();
            verifyResponseHasResults();
        } finally {
            logResponse();
        }
    }

    @Test
    public void getNewsAndPoliticsPodcastsWithLimit() throws ItunesSearchException {
        nullifyResponse();

        try {
            int limit = 5;
            response = PodcastLookup.newsAndPoliticsPodcasts(limit);
            verifyResponseHasResults();
            verifyResponseMatchesLimit(limit);
        } finally {
            logResponse();
        }
    }

    @Test
    public void getSocietyAndCulturePodcasts() throws ItunesSearchException {
        nullifyResponse();

        try {
            response = PodcastLookup.societyAndCulturePodcasts();
            verifyResponseHasResults();
        } finally {
            logResponse();
        }
    }

    @Test
    public void getSocietyAndCulturePodcastsWithLimit() throws ItunesSearchException {
        nullifyResponse();

        try {
            int limit = 5;
            response = PodcastLookup.societyAndCulturePodcasts(limit);
            verifyResponseHasResults();
            verifyResponseMatchesLimit(limit);
        } finally {
            logResponse();
        }
    }

    private void verifyResponseHasResults() {
        assertThat(response.has("results"));
    }

    private void verifyResponseMatchesLimit(int limit) {
        /*
         * depending on the query, the results are sometimes buried under
         * a feed object. In that case, set the feed object as the response
         * object.
         */
        if (response.has("feed")) response = response.getJSONObject("feed");

        JSONArray matchingPodcastsArray = response.getJSONArray("results");
        assertThat(matchingPodcastsArray.length())
                .isGreaterThan(0)
                .isLessThan(limit + 1);
    }

    private void logResponse() {
        String callingMethodName = Thread.currentThread().getStackTrace()[2].getMethodName();

        if (response != null) {
            logger.info("\n" + TEST_LOG_TAG + callingMethodName + "\n"
                    + RESPONSE_LOG_TAG + response.toString() + "\n\n");
        }
    }

    /*
     * response object is nullified at the start of
     * each tests to prevent the values from a previous test from
     * being used when the current test fails.
     */
    private void nullifyResponse() {
        response = null;
    }

    /*
     * The itunes api limits call per minute, so sleep for a minute before the next
     * set of tests start, to avoid failures.
     */
    @AfterClass
    private void sleepForAMinute() {
        TestUtils.sleepForAMinute();
    }

}
