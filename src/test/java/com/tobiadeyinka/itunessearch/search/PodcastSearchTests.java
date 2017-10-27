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
import com.tobiadeyinka.itunessearch.entities.podcasts.PodcastAttribute;
import com.tobiadeyinka.itunessearch.entities.podcasts.PodcastSearchReturnType;

import com.tobiadeyinka.itunessearch.exceptions.*;

import org.testng.annotations.Test;

/**
 * Tests for all podcast search methods.
 *
 * Created by Tobi Adeyinka on 2017. 10. 15..
 */
public class PodcastSearchTests extends BaseSearchTest {

    private String searchTerm = "radio";

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

}
