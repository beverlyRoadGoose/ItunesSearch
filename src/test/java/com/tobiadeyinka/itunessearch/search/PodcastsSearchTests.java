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

import org.testng.annotations.Test;

import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for all podcast search methods.
 *
 * Created by Tobi Adeyinka on 2017. 10. 15..
 */
public class PodcastsSearchTests {

    private String searchTerm = "radiolab";
    private Logger logger = Logger.getLogger(PodcastsSearchTests.class.getName());

    @Test(expectedExceptions = MissingRequiredParameterException.class)
    public void searchForPodcastWithoutSearchTerm()
            throws MissingRequiredParameterException, InvalidParameterException, SearchURLConstructionFailure,
            NetworkCommunicationException {

        new PodcastsSearch().execute();
    }

    @Test
    public void searchForPodcastWithDefaultParameters() throws NetworkCommunicationException, InvalidParameterException,
            MissingRequiredParameterException, SearchURLConstructionFailure {

        PodcastsSearch search = new PodcastsSearch().with(searchTerm);
        logger.info("search url: " + search.getSearchUrl());

        JSONObject response = search.execute();
        logger.info("search response: " + response.toString());

        JSONArray matchingPodcastsArray = response.getJSONArray("results");
        assertThat(matchingPodcastsArray.length())
                .isGreaterThan(0);
    }

    @Test
    public void searchForPodcastUsingTitleAttribute() throws NetworkCommunicationException, InvalidParameterException,
            MissingRequiredParameterException, SearchURLConstructionFailure {

        PodcastsSearch search = new PodcastsSearch()
                .with(searchTerm)
                .inAttribute(PodcastAttribute.TITLE);
        logger.info("search url: " + search.getSearchUrl());

        JSONObject response = search.execute();
        logger.info("search response: " + response.toString());

        JSONArray matchingPodcastsArray = response.getJSONArray("results");
        assertThat(matchingPodcastsArray.length())
                .isGreaterThan(0);
    }

    @Test
    public void searchForPodcastInSpecificStore() throws NetworkCommunicationException, InvalidParameterException,
            MissingRequiredParameterException, SearchURLConstructionFailure {

        PodcastsSearch search = new PodcastsSearch()
                .with(searchTerm)
                .inCountry(CountryCode.NG);
        logger.info("search url: " + search.getSearchUrl());

        JSONObject response = search.execute();
        logger.info("search response: " + response.toString());

        JSONArray matchingPodcastsArray = response.getJSONArray("results");
        assertThat(matchingPodcastsArray.length())
                .isGreaterThan(0);
    }

    @Test
    public void searchForPodcastWithLimit() throws NetworkCommunicationException, InvalidParameterException,
            MissingRequiredParameterException, SearchURLConstructionFailure {

        PodcastsSearch search = new PodcastsSearch()
                .with(searchTerm)
                .withLimit(5);
        logger.info("search url: " + search.getSearchUrl());

        JSONObject response = search.execute();
        logger.info("search response: " + response.toString());

        JSONArray matchingPodcastsArray = response.getJSONArray("results");
        assertThat(matchingPodcastsArray.length())
                .isGreaterThan(0)
                .isLessThan(6);
    }

    @Test
    public void searchForPodcastWithApiVersion1() throws NetworkCommunicationException, InvalidParameterException,
            MissingRequiredParameterException, SearchURLConstructionFailure {

        PodcastsSearch search = new PodcastsSearch()
                .with(searchTerm)
                .withApiVersion(ItunesApiVersion.ONE);
        logger.info("search url: " + search.getSearchUrl());

        JSONObject response = search.execute();
        logger.info("search response: " + response.toString());

        JSONArray matchingPodcastsArray = response.getJSONArray("results");
        assertThat(matchingPodcastsArray.length())
                .isGreaterThan(0);
    }

    @Test
    public void searchForPodcastWithJapaneseResponse() throws NetworkCommunicationException, InvalidParameterException,
            MissingRequiredParameterException, SearchURLConstructionFailure {

        PodcastsSearch search = new PodcastsSearch()
                .with(searchTerm)
                .withReturnLanguage(ReturnLanguage.JAPANESE);
        logger.info("search url: " + search.getSearchUrl());

        JSONObject response = search.execute();
        logger.info("search response: " + response.toString());

        JSONArray matchingPodcastsArray = response.getJSONArray("results");
        assertThat(matchingPodcastsArray.length())
                .isGreaterThan(0);
    }

    @Test
    public void searchForPodcastWithAuthorReturnType() throws NetworkCommunicationException, InvalidParameterException,
            MissingRequiredParameterException, SearchURLConstructionFailure {

        PodcastsSearch search = new PodcastsSearch()
                .with(searchTerm)
                .inAttribute(PodcastAttribute.TITLE)
                .andReturn(PodcastSearchReturnType.PODCAST_AUTHOR);
        logger.info("search url: " + search.getSearchUrl());

        JSONObject response = search.execute();
        logger.info("search response: " + response.toString());

        JSONArray matchingPodcastsArray = response.getJSONArray("results");
        assertThat(matchingPodcastsArray.length())
                .isGreaterThan(0);
    }

    @Test
    public void searchForPodcastWithoutExplicitContent() throws NetworkCommunicationException, InvalidParameterException,
            MissingRequiredParameterException, SearchURLConstructionFailure {

        PodcastsSearch search = new PodcastsSearch()
                .with(searchTerm)
                .allowExplicit(false);
        logger.info("search url: " + search.getSearchUrl());

        JSONObject response = search.execute();
        logger.info("search response: " + response.toString());

        JSONArray matchingPodcastsArray = response.getJSONArray("results");
        assertThat(matchingPodcastsArray.length())
                .isGreaterThan(0);
    }

    @Test
    public void comprehensivePodcastSearch() throws NetworkCommunicationException, InvalidParameterException,
            MissingRequiredParameterException, SearchURLConstructionFailure {

        PodcastsSearch search = new PodcastsSearch()
                .with(searchTerm)
                .withLimit(5)
                .inCountry(CountryCode.NG)
                .inAttribute(PodcastAttribute.TITLE)
                .withReturnLanguage(ReturnLanguage.JAPANESE)
                .withApiVersion(ItunesApiVersion.ONE);
        logger.info("search url: " + search.getSearchUrl());

        JSONObject response = search.execute();
        logger.info("search response: " + response.toString());

        JSONArray matchingPodcastsArray = response.getJSONArray("results");
        assertThat(matchingPodcastsArray.length())
                .isGreaterThan(0)
                .isLessThan(6);
    }

}
