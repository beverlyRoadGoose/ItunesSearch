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
import com.tobiadeyinka.itunessearch.common.enums.ItunesMedia;
import com.tobiadeyinka.itunessearch.podcasts.enums.PodcastAttribute;
import com.tobiadeyinka.itunessearch.podcasts.enums.PodcastSearchReturnType;

import org.json.JSONObject;

/**
 * Podcasts search api endpoint.
 *
 * See <a href="https://affiliate.itunes.apple.com/resources/documentation/itunes-store-web-service-search-api/#searching">
 *     Searching the iTunes Store</a> for more details about the parameters.
 *
 * Created by Tobi Adeyinka on 2017. 10. 15..
 */
public class PodcastsSearch extends MediaSearch {

    /**
     * The podcast attribute the search term is compared with. Default is all attributes.
     */
    protected PodcastAttribute attribute;

    /**
     * The type of results returned (Podcasts or PodcastArtists)
     */
    protected PodcastSearchReturnType returnType = PodcastSearchReturnType.PODCAST;

    /**
     * creates a media search instance and set's the media type to podcast
     */
    public PodcastsSearch() {
        this.media = ItunesMedia.PODCAST;
    }

    /**
     * Sets the podcast attribute the search term is compared with. Default is all attributes.
     *
     * @param attribute the podcast attribute the {@link #searchTerm} is compared with.
     * @return the current instance of {@link PodcastsSearch}
     */
    public PodcastsSearch inAttribute(PodcastAttribute attribute) {
        this.attribute = attribute;
        return this;
    }

    /**
     * Sets the return type of the results (Podcasts or PodcastArtists)
     *
     * @param returnType the type of results you want returned
     * @return the current instance of {@link PodcastsSearch}
     */
    public PodcastsSearch andReturn(PodcastSearchReturnType returnType) {
        this.returnType = returnType;
        return this;
    }

    /**
     * execute the search
     *
     * @return A {@link org.json.JSONObject} object containing the results.
     * @throws MissingRequiredParameterException if the search term is not set.
     * @throws InvalidParameterException if any of the set parameters are invalid.
     * @throws SearchURLConstructionFailure if there is an error during url construction.
     * @throws NetworkCommunicationException if any issues occur while communicating with the iTunes api.
     */
    @Override
    public JSONObject execute()
            throws MissingRequiredParameterException, InvalidParameterException, SearchURLConstructionFailure,
            NetworkCommunicationException {

        runPreExecutionChecks();
        return new PodcastsSearchManager().executePodcastSearch(this);
    }

    /**
     * check the validity of all required data before executing the search
     *
     * @throws MissingRequiredParameterException if the search term is not set.
     * @throws InvalidParameterException if any of the set parameters are invalid.
     */
    @Override
    protected void runPreExecutionChecks() throws MissingRequiredParameterException, InvalidParameterException {
        /*
         * search term must be set.
         */
        if (searchTerm == null || searchTerm.isEmpty())
            throw new MissingRequiredParameterException("Search execution failed: missing search term parameter");

        /*
         * check the api version validity.
         */
        if (apiVersion < 1 || apiVersion > 2)
            throw new InvalidParameterException("Search execution failed: invalid api version code");
    }

}
