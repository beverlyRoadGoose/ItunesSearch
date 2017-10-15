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
import com.tobiadeyinka.itunessearch.exceptions.NetworkCommunicationException;
import com.tobiadeyinka.itunessearch.exceptions.SearchURLConstructionFailure;

import org.json.JSONObject;

import java.net.URL;
import java.net.MalformedURLException;

/**
 * All searches related to podcast's are done here.
 *
 * Created by Tobi Adeyinka on 2017. 10. 15..
 */
class PodcastsSearchManager {

    public JSONObject executePodcastSearch(PodcastsSearch podcastsSearch)
            throws SearchURLConstructionFailure, NetworkCommunicationException {
        String urlString = constructUrlString(podcastsSearch);
        URL url = createUrlObject(urlString);
        return NetworkUtils.executeSearch(url);
    }

    private URL createUrlObject(String urlString) throws SearchURLConstructionFailure {
        try {
            return new URL(urlString);
        } catch (MalformedURLException e) {
            throw new SearchURLConstructionFailure("Error during search url construction: " + e.getMessage());
        }
    }

    private String constructUrlString(PodcastsSearch podcastsSearch) {
        String urlString = "https://itunes.apple.com/search?";
        urlString += "term=" + podcastsSearch.getSearchTerm();
        urlString += "&country=" + podcastsSearch.getCountryCode().getAlpha2();
        urlString += "&media=" + podcastsSearch.getMedia().getParameterValue();
        urlString += "&entity=" + podcastsSearch.getReturnType().getParameterValue();
        urlString += "&attribute=" + podcastsSearch.getAttribute().getParameterValue();
        urlString += "&limit=" + podcastsSearch.getLimit();
        urlString += "&lang=" + podcastsSearch.getReturnLanguage().getCodeName();
        urlString += "&version=" + podcastsSearch.getApiVersion();
        urlString += "&explicit=" + (podcastsSearch.explicitAllowed() ? "Yes" : "No");

        return urlString;
    }

}
