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

package me.tobiadeyinka.itunessearch.search;

import me.tobiadeyinka.itunessearch.TestUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import org.testng.annotations.AfterClass;

import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Base class for all search tests.
 *
 * Created by Tobi Adeyinka on 2017. 10. 27..
 */
public class BaseSearchTest {

    protected Logger logger = Logger.getLogger(BaseSearchTest.class.getName());

    protected static final String TEST_LOG_TAG = "test: ";
    protected static final String URL_LOG_TAG = "search url: ";
    protected static final String RESPONSE_LOG_TAG = "search response: ";

    protected Search search = null;
    protected JSONObject response = null;

    protected void verifyResponseHasResults() {
        assertThat(response.has("results"));
    }

    protected void verifyResponseMatchesLimit(int limit) {
        JSONArray matchingPodcastsArray = response.getJSONArray("results");
        assertThat(matchingPodcastsArray.length())
                .isGreaterThanOrEqualTo(0)
                .isLessThan(limit + 1);
    }

    protected void logUrlAndResponse() {
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
    protected void nullifySearchAndResponse() {
        search = null;
        response = null;
    }

    /*
     * The itunes api limits call per minute, so sleep for a minute before the next
     * set of tests start, to avoid failures.
     */
    @AfterClass
    protected void sleepForAMinute() {
        TestUtils.sleepForAMinute();
    }

}
