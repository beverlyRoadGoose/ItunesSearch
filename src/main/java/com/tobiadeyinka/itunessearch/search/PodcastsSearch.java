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
import com.tobiadeyinka.itunessearch.common.enums.*;
import com.tobiadeyinka.itunessearch.podcasts.enums.PodcastAttribute;
import com.tobiadeyinka.itunessearch.podcasts.enums.PodcastSearchReturnType;

import org.json.JSONObject;

import java.net.URL;
import java.net.MalformedURLException;

/**
 * Podcasts search API endpoint.
 *
 * See <a href="https://affiliate.itunes.apple.com/resources/documentation/itunes-store-web-service-search-api/#searching">
 *     Searching the iTunes Store</a> for more details about the parameters.
 *
 * Created by Tobi Adeyinka on 2017. 10. 15..
 */
public class PodcastsSearch implements SearchEndpoint<PodcastsSearch> {

    /**
     * The term to search for.
     */
    private String searchTerm;

    /**
     * The media type to search for. Default is all.
     */
    private ItunesMedia media = ItunesMedia.ALL;

    /**
     * The version of the iTunes api to use (1/2). Default is 2.
     */
    private int apiVersion = 2;

    /**
     * The maximum number of item's to return. Default is 50.
     */
    private int limit = 50;

    /**
     * allow/exclude explicit content in search results. Explicit content is allowed by default.
     */
    private boolean allowExplicit = true;

    /**
     * The podcast attribute the search term is compared with. Default is all attributes.
     */
    private PodcastAttribute attribute;

    /**
     *  <a href="https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">ISO 3166-1 alpha-2</a> country code for the
     *  iTunes store to search. Default is US.
     */
    private CountryCode countryCode = CountryCode.US;

    /**
     * The language to return the result in (only english/japanese). Default is english.
     */
    private ReturnLanguage returnLanguage = ReturnLanguage.ENGLISH;

    /**
     * The type of results returned (Podcasts or PodcastArtists)
     */
    private PodcastSearchReturnType returnType = PodcastSearchReturnType.PODCAST;

    /**
     * URL used to search the iTunes store, generated using all the variables of the instance
     */
    private URL searchUrl;

    /**
     * creates a media search instance and set's the media type to podcast
     */
    public PodcastsSearch() {
        this.media = ItunesMedia.PODCAST;
        this.attribute = PodcastAttribute.ALL;
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
     * Sets the term to search for. Required.
     *
     * @param searchTerm the term to search for
     * @return the current instance of {@link PodcastsSearch}
     */
    @Override
    public PodcastsSearch with(String searchTerm) {
        this.searchTerm = searchTerm;
        return this;
    }

    /**
     * Sets the iTunes store to search.
     *
     * @param countryCode <a href="https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">ISO 3166-1 alpha-2</a> country code
     *                    for the iTunes store to search. Default is US.
     * @return the current instance of {@link PodcastsSearch}
     */
    @Override
    public PodcastsSearch inCountry(CountryCode countryCode) {
        this.countryCode = countryCode;
        return this;
    }

    /**
     * Sets the maximum number of item's to return. Default is 50.
     *
     * @param limit the maximum number of item's to return.
     * @return the current instance of {@link PodcastsSearch}
     */
    @Override
    public PodcastsSearch withLimit(int limit) {
        this.limit = limit;
        return this;
    }

    /**
     * Sets the language results are return in. Default is english.
     *
     * @param returnLanguage The language result should be return in.
     * @return the current instance of {@link PodcastsSearch}
     */
    @Override
    public PodcastsSearch withReturnLanguage(ReturnLanguage returnLanguage) {
        this.returnLanguage = returnLanguage;
        return this;
    }

    /**
     * Set the version of the iTunes api to use (1/2). Default is 2.
     *
     * @param apiVersion the version of the iTunes api to use.
     * @return the current instance of {@link PodcastsSearch}
     */
    @Override
    public PodcastsSearch withApiVersion(ItunesApiVersion apiVersion) {
        this.apiVersion = apiVersion.getVersionNumber();
        return this;
    }

    /**
     * enable/disable content in search results. Explicit content is allowed by default.
     *
     * @param allowExplicit allow explicit content or not.
     * @return the current instance of {@link PodcastsSearch}
     */
    @Override
    public PodcastsSearch allowExplicit(boolean allowExplicit) {
        this.allowExplicit = allowExplicit;
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
    public JSONObject execute()
            throws MissingRequiredParameterException, InvalidParameterException, SearchURLConstructionFailure,
            NetworkCommunicationException {

        runPreExecutionChecks();
        String urlString = constructUrlString(this);
        URL url = createUrlObject(urlString);
        searchUrl = url;
        return new SearchManager().executeSearch(url);
    }

    /**
     * check the validity of all required data before executing the search
     *
     * @throws MissingRequiredParameterException if the search term is not set.
     * @throws InvalidParameterException if any of the set parameters are invalid.
     */
    private void runPreExecutionChecks() throws MissingRequiredParameterException, InvalidParameterException {
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

    /**
     *
     * @return the term to be searched for
     */
    public String getSearchTerm() {
        return searchTerm;
    }

    /**
     *
     * @return the set media type being searched for
     */
    public ItunesMedia getMedia() {
        return media;
    }

    /**
     *
     * @return the iTunes store being searched
     */
    public CountryCode getCountryCode() {
        return countryCode;
    }

    /**
     *
     * @return the maximum number of results
     */
    public int getLimit() {
        return limit;
    }

    /**
     *
     * @return the api version to use
     */
    public int getApiVersion() {
        return apiVersion;
    }

    /**
     *
     * @return the explicit data setting
     */
    public boolean explicitAllowed() {
        return allowExplicit;
    }

    /**
     *
     * @return the language the result should be returned in
     */
    public ReturnLanguage getReturnLanguage() {
        return returnLanguage;
    }

    /**
     *
     * @return the attribute to search in
     */
    public PodcastAttribute getAttribute() {
        return attribute;
    }

    /**
     *
     * @return the type of data to return
     */
    public PodcastSearchReturnType getReturnType() {
        return returnType;
    }

    /**
     *
     * @return the url used to search the iTunes store.
     */
    public URL getSearchUrl() {
        return searchUrl;
    }

}
