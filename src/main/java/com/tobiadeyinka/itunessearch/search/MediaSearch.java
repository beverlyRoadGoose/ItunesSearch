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

import org.json.JSONObject;

import java.net.URL;
import java.net.MalformedURLException;

/**
 * API endpoint for non-specific media types.
 *
 * See <a href="https://affiliate.itunes.apple.com/resources/documentation/itunes-store-web-service-search-api/#searching">
 *     Searching the iTunes Store</a> for more details about the parameters.
 *
 * Created by Tobi Adeyinka on 2017. 10. 15..
 */
public class MediaSearch {

    /**
     * The term to search for.
     */
    private String searchTerm;

    /**
     * The media type to search for. In this case all types.
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
     *  <a href="https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">ISO 3166-1 alpha-2</a> country code for the
     *  iTunes store to search. Default is US.
     */
    private CountryCode countryCode = CountryCode.US;

    /**
     * The language to return the result in (only english/japanese). Default is english.
     */
    private ReturnLanguage returnLanguage = ReturnLanguage.ENGLISH;

    /**
     * URL used to search the iTunes store, generated using all the variables of the instance
     */
    private URL searchUrl;

    /**
     * Sets the term to search for. Required.
     *
     * @param searchTerm the term to search for
     * @return the current instance of {@link MediaSearch}
     */
    public MediaSearch with(String searchTerm) {
        this.searchTerm = searchTerm;
        return this;
    }

    /**
     * Sets the maximum number of item's to return. Default is 50.
     *
     * @param limit the maximum number of item's to return.
     * @return the current instance of {@link MediaSearch}
     */
    public MediaSearch withLimit(int limit) {
        this.limit = limit;
        return this;
    }

    /**
     * Sets the iTunes store to search.
     *
     * @param countryCode <a href="https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">ISO 3166-1 alpha-2</a> country code
     *                    for the iTunes store to search. Default is US.
     * @return the current instance of {@link MediaSearch}
     */
    public MediaSearch inCountry(CountryCode countryCode) {
        this.countryCode = countryCode;
        return this;
    }

    /**
     * Set the version of the iTunes api to use (1/2). Default is 2.
     *
     * @param apiVersion the version of the iTunes api to use.
     * @return the current instance of {@link MediaSearch}
     */
    public MediaSearch withApiVersion(ItunesApiVersion apiVersion) {
        this.apiVersion = apiVersion.getVersionNumber();
        return this;
    }

    /**
     * Sets the language results are return in. Default is english.
     *
     * @param returnLanguage The language result should be return in.
     * @return the current instance of {@link MediaSearch}
     */
    public MediaSearch withReturnLanguage(ReturnLanguage returnLanguage) {
        this.returnLanguage = returnLanguage;
        return this;
    }

    /**
     * allow/remove explicit content in search results. Explicit content is allowed by default.
     *
     * @param allowExplicit allow explicit content or not.
     * @return the current instance of {@link MediaSearch}
     */
    public MediaSearch allowExplicit(boolean allowExplicit) {
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
    public JSONObject execute() throws ItunesSearchException {

        runPreExecutionChecks();
        String urlString = constructUrlString();
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
    private void runPreExecutionChecks() throws ItunesSearchException {
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

    private URL createUrlObject(String urlString) throws ItunesSearchException {
        try {
            return new URL(urlString);
        } catch (MalformedURLException e) {
            throw new SearchURLConstructionFailure("Error during search url construction: " + e.getMessage());
        }
    }

    private String constructUrlString() {
        String urlString = "https://itunes.apple.com/search?";
        urlString += "term=" + searchTerm;
        urlString += "&country=" + countryCode.getAlpha2();
        urlString += "&media=" + media.getParameterValue();
        urlString += "&limit=" + limit;
        urlString += "&lang=" + returnLanguage.getCodeName();
        urlString += "&version=" + apiVersion;
        urlString += "&explicit=" + (allowExplicit ? "Yes" : "No");

        return urlString;
    }

    public String getSearchTerm() {
        return searchTerm;
    }

    public ItunesMedia getMedia() {
        return media;
    }

    public int getApiVersion() {
        return apiVersion;
    }

    public int getLimit() {
        return limit;
    }

    public boolean isAllowExplicit() {
        return allowExplicit;
    }

    public CountryCode getCountryCode() {
        return countryCode;
    }

    public ReturnLanguage getReturnLanguage() {
        return returnLanguage;
    }

    public URL getSearchUrl() {
        return searchUrl;
    }
    
}
