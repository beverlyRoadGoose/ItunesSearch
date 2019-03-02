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

import me.tobiadeyinka.itunessearch.TestUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.logging.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Base class for all lookup tests
 *
 * Created by Tobi Adeyinka on 2017. 11. 08..
 */
public class BaseLookupTest {

    private Logger logger = Logger.getLogger(BaseLookupTest.class.getName());

    protected int limit = 5;
    protected JSONObject response = null;

    private static final String TEST_LOG_TAG = "test: ";
    private static final String RESPONSE_LOG_TAG = "response: ";

    protected void verifyResponseHasResults() {
        assertThat(response.has("results") || (response.has("feed") && response.getJSONObject("feed").has("results")))
            .isTrue();
    }

    protected void verifyResponseMatchesLimit(int limit) {
        /*
         * depending on the query, the results are sometimes buried under
         * a feed object. In that case, set the feed object as the response
         * object.
         */
        if (response.has("feed")) {
            response = response.getJSONObject("feed");
        }

        JSONArray matchingPodcastsArray = response.getJSONArray("results");
        assertThat(matchingPodcastsArray.length())
                .isGreaterThan(0)
                .isLessThan(limit + 1);
    }

    protected void logResponse() {
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
    @BeforeMethod
    protected void nullifyResponse() {
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
