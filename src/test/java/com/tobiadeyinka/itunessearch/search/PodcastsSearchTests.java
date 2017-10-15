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

import com.tobiadeyinka.itunessearch.exceptions.*;

import com.tobiadeyinka.itunessearch.podcasts.enums.PodcastAttribute;
import org.json.JSONArray;
import org.json.JSONObject;

import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for all podcast search methods.
 *
 * Created by Tobi Adeyinka on 2017. 10. 15..
 */
public class PodcastsSearchTests {

    @Test(expectedExceptions = MissingRequiredParameterException.class)
    public void searchForPodcastWithoutSearchTerm()
            throws MissingRequiredParameterException, InvalidParameterException, SearchURLConstructionFailure,
            NetworkCommunicationException {

        new PodcastsSearch().execute();
    }

    @Test
    public void searchForPodcastWithDefaultParameters() throws NetworkCommunicationException, InvalidParameterException,
            MissingRequiredParameterException, SearchURLConstructionFailure {

        String searchTerm = "radiolab";
        JSONObject response = new PodcastsSearch()
                .with(searchTerm)
                .execute();

        JSONArray matchingPodcastsArray = response.getJSONArray("results");
        assertThat(matchingPodcastsArray.length()).isGreaterThan(0);
    }

    @Test
    public void searchForPodcastUsingTitleAttribute() throws NetworkCommunicationException, InvalidParameterException,
            MissingRequiredParameterException, SearchURLConstructionFailure {

        String searchTerm = "radiolab";
        JSONObject response = new PodcastsSearch()
                .with(searchTerm)
                .inAttribute(PodcastAttribute.TITLE)
                .execute();

        JSONArray matchingPodcastsArray = response.getJSONArray("results");
        assertThat(matchingPodcastsArray.length()).isGreaterThan(0);
    }

}
