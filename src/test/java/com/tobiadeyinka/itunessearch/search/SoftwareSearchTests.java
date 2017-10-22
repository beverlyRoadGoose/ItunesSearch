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
import com.tobiadeyinka.itunessearch.entities.software.SoftwareAttribute;
import com.tobiadeyinka.itunessearch.entities.software.SoftwareSearchReturnType;

import com.tobiadeyinka.itunessearch.exceptions.*;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.logging.Logger;

import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for all software search methods.
 * 
 * Created by Tobi Adeyinka on 2017. 10. 19..
 */
public class SoftwareSearchTests {

    private String searchTerm = "test";
    private Logger logger = Logger.getLogger(SoftwareSearchTests.class.getName());

    private SoftwareSearch search = null;
    private JSONObject response = null;

    private static final String TEST_LOG_TAG = "test: ";
    private static final String URL_LOG_TAG = "search url: ";
    private static final String RESPONSE_LOG_TAG = "search response: ";

    @Test(expectedExceptions = MissingRequiredParameterException.class)
    public void searchForSoftwareWithoutSearchTerm() throws ItunesSearchException {
        new SoftwareSearch().execute();
    }

    @Test
    public void searchForSoftwareWithDefaultParameters() throws ItunesSearchException {
        nullifySearchAndResponse();

        try {
            search = new SoftwareSearch().with(searchTerm);
            response = search.execute();
            verifyResponseHasResults();
        } finally {
            logUrlAndResponse();
        }
    }

    @Test
    public void searchForSoftwareUsingDeveloperAttribute() throws ItunesSearchException {
        nullifySearchAndResponse();

        try {
            search = new SoftwareSearch()
                    .with(searchTerm)
                    .inAttribute(SoftwareAttribute.SOFTWARE_DEVELOPER);
            response = search.execute();
            verifyResponseHasResults();
        } finally {
            logUrlAndResponse();
        }
    }

    @Test
    public void searchForSoftwareInSpecificStore() throws ItunesSearchException {
        nullifySearchAndResponse();

        try {
            search = new SoftwareSearch()
                    .with(searchTerm)
                    .inCountry(CountryCode.NG);
            response = search.execute();
            verifyResponseHasResults();
        } finally {
            logUrlAndResponse();
        }
    }

    @Test
    public void searchForSoftwareWithLimit() throws ItunesSearchException {
        nullifySearchAndResponse();

        try {
            int limit = 5;
            search = new SoftwareSearch()
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
    public void searchForSoftwareWithApiVersion1() throws ItunesSearchException {
        nullifySearchAndResponse();

        try {
            search = new SoftwareSearch()
                    .with(searchTerm)
                    .withApiVersion(ItunesApiVersion.ONE);
            response = search.execute();
            verifyResponseHasResults();
        } finally {
            logUrlAndResponse();
        }
    }

    @Test
    public void searchForSoftwareWithJapaneseResponse() throws ItunesSearchException {
        nullifySearchAndResponse();

        try {
            search = new SoftwareSearch()
                    .with(searchTerm)
                    .withReturnLanguage(ReturnLanguage.JAPANESE);
            response = search.execute();
            verifyResponseHasResults();
        } finally {
            logUrlAndResponse();
        }
    }

    @Test
    public void searchForSoftwareWithIpadSoftwareReturnType() throws ItunesSearchException {
        nullifySearchAndResponse();

        try {
            search = new SoftwareSearch()
                    .with(searchTerm)
                    .inAttribute(SoftwareAttribute.SOFTWARE_DEVELOPER)
                    .andReturn(SoftwareSearchReturnType.IPAD_SOFTWARE);
            response = search.execute();
            verifyResponseHasResults();
        } finally {
            logUrlAndResponse();
        }
    }

    @Test
    public void searchForSoftwareWithoutExplicitContent() throws ItunesSearchException {
        nullifySearchAndResponse();

        try {
            search = new SoftwareSearch()
                    .with(searchTerm)
                    .allowExplicit(false);
            response = search.execute();
            verifyResponseHasResults();
        } finally {
            logUrlAndResponse();
        }
    }

    @Test
    public void comprehensiveSoftwareSearch() throws ItunesSearchException {
        nullifySearchAndResponse();

        try {
            int limit = 5;
            search = new SoftwareSearch()
                    .with(searchTerm)
                    .withLimit(limit)
                    .inCountry(CountryCode.NG)
                    .inAttribute(SoftwareAttribute.SOFTWARE_DEVELOPER)
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
        JSONArray matchingSoftwaresArray = response.getJSONArray("results");
        assertThat(matchingSoftwaresArray.length())
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

    /*
     * The itunes api limits call per minute, so sleep for a minute before the next
     * set of tests start, to avoid failures.
     */
    @AfterClass
    private void sleepForAMinute() {
        logger.info("sleeping for 1 minute");

        try {
            Thread.sleep(60000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
}
