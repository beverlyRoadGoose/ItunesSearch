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

import com.neovisionaries.i18n.CountryCode;

import me.tobiadeyinka.itunessearch.entities.*;
import me.tobiadeyinka.itunessearch.exceptions.ItunesSearchException;
import me.tobiadeyinka.itunessearch.exceptions.MissingRequiredParameterException;

import org.testng.annotations.Test;

/**
 * Tests for all software search methods.
 * 
 * Created by Tobi Adeyinka on 2017. 10. 19..
 */
public class SoftwareSearchTests extends BaseSearchTest {

    private String searchTerm = "test";

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
    
}
