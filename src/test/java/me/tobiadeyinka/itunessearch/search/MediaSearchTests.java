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
import me.tobiadeyinka.itunessearch.exceptions.MissingRequiredParameterException;

import org.testng.annotations.Test;

/**
 * Tests for non-specific media search methods.
 *
 * Created by Tobi Adeyinka on 2017. 10. 17..
 */
public class MediaSearchTests extends BaseSearchTest {

    private String searchTerm = "test";

    @Test(expectedExceptions = MissingRequiredParameterException.class)
    public void searchWithoutSearchTerm() {
        new MediaSearch().execute();
    }

    @Test
    public void searchWithDefaultParameters() {
        nullifySearchAndResponse();

        try {
            search = new MediaSearch().with(searchTerm);
            response = search.execute();
            verifyResponseHasResults();
        } finally { logUrlAndResponse(); }
    }

    @Test
    public void searchForPodcastWithMediaSearch() {
        nullifySearchAndResponse();

        try {
            searchTerm = "radiolab";
            search = new MediaSearch()
                    .with(searchTerm)
                    .inAttribute(MediaAttribute.TITLE)
                    .andReturn(MediaSearchReturnType.PODCAST);
            response = search.execute();
            verifyResponseHasResults();
        } finally { logUrlAndResponse(); }
    }

    @Test
    public void searchInSpecificStore() {
        nullifySearchAndResponse();

        try {
            search = new MediaSearch()
                    .with(searchTerm)
                    .inCountry(CountryCode.CA);
            response = search.execute();
            verifyResponseHasResults();
        } finally { logUrlAndResponse(); }
    }

    @Test
    public void searchWithLimit() {
        nullifySearchAndResponse();

        try {
            int limit = 5;
            search = new MediaSearch()
                    .with(searchTerm)
                    .withLimit(limit);
            response = search.execute();

            verifyResponseHasResults();
            verifyResponseMatchesLimit(limit);
        } finally { logUrlAndResponse(); }
    }

    @Test
    public void searchWithApiVersion1() {
        nullifySearchAndResponse();

        try {
            search = new MediaSearch()
                    .with(searchTerm)
                    .withApiVersion(ItunesApiVersion.ONE);
            response = search.execute();
            verifyResponseHasResults();
        } finally { logUrlAndResponse(); }
    }

    @Test
    public void searchWithoutExplicitContent() {
        nullifySearchAndResponse();

        try {
            search = new MediaSearch()
                    .with(searchTerm)
                    .allowExplicit(false);
            response = search.execute();
            verifyResponseHasResults();
        } finally { logUrlAndResponse(); }
    }

    @Test
    public void specifyReturnLanguage() {
        nullifySearchAndResponse();

        try {
            search = new MediaSearch()
                    .with(searchTerm)
                    .withReturnLanguage(ReturnLanguage.JAPANESE);
            response = search.execute();
            verifyResponseHasResults();
        } finally { logUrlAndResponse(); }
    }

    @Test
    public void comprehensiveMovieSearch() {
        nullifySearchAndResponse();

        try {
            int limit = 5;
            search = new MediaSearch()
                    .with(searchTerm)
                    .withLimit(limit)
                    .inCountry(CountryCode.CA)
                    .withReturnLanguage(ReturnLanguage.JAPANESE)
                    .withApiVersion(ItunesApiVersion.ONE);
            response = search.execute();

            verifyResponseHasResults();
            verifyResponseMatchesLimit(limit);
        } finally { logUrlAndResponse(); }
    }

}
