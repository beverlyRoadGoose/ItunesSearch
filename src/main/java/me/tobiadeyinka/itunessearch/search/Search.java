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
import me.tobiadeyinka.itunessearch.exceptions.*;

import org.json.JSONObject;

import java.net.URL;
import java.net.MalformedURLException;

/**
 * Parent class for all searches, containing common code implementations
 *
 * Created by Tobi Adeyinka on 2017. 10. 16..
 */
abstract class Search<T> {

    /**
     * The term to search for.
     */
    protected String searchTerm;

    /**
     * The media type to search for.
     */
    protected final ItunesMedia media;

    /**
     * The maximum number of item's to return. Default is 50.
     */
    protected int limit = 50;

    /**
     * The version of the iTunes api to use (1/2). Default is 2.
     */
    protected int apiVersion = 2;

    /**
     * allow/exclude explicit content in search results. Explicit content is allowed by default.
     */
    protected boolean allowExplicit = true;

    /**
     *  <a href="https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">ISO 3166-1 alpha-2</a> country code for the
     *  iTunes store to search. Default is US.
     */
    protected CountryCode countryCode = CountryCode.US;

    /**
     * The language to return the result in (only english/japanese). Default is english.
     */
    protected ReturnLanguage returnLanguage = ReturnLanguage.ENGLISH;

    /**
     * URL used to search the iTunes store, generated using all the variables of the instance
     */
    protected URL searchUrl;

    protected Search(ItunesMedia media) {
        this.media = media;
    }

    /**
     * Sets the term to search for. Required.
     *
     * @param searchTerm the term to search for
     * @return the current search instance
     */
    public T with(String searchTerm) {
        this.searchTerm = searchTerm;
        return (T)this;
    }

    /**
     * Sets the maximum number of item's to return. Default is 50.
     *
     * @param limit the maximum number of item's to return.
     * @return the current search instance
     */
    public T withLimit(int limit) {
        this.limit = limit;
        return (T)this;
    }

    /**
     * Set the version of the iTunes api to use (1/2). Default is 2.
     *
     * @param apiVersion the version of the iTunes api to use.
     * @return the current search instance
     */
    public T withApiVersion(ItunesApiVersion apiVersion) {
        this.apiVersion = apiVersion.getVersionNumber();
        return (T)this;
    }

    /**
     * allow/remove explicit content in search results. Explicit content is allowed by default.
     *
     * @param allowExplicit allow explicit content or not.
     * @return the current search instance
     */
    public T allowExplicit(boolean allowExplicit) {
        this.allowExplicit = allowExplicit;
        return (T)this;
    }

    /**
     * Sets the language results are return in. Default is english.
     *
     * @param returnLanguage The language result should be return in.
     * @return the current search instance
     */
    public T withReturnLanguage(ReturnLanguage returnLanguage) {
        this.returnLanguage = returnLanguage;
        return (T)this;
    }

    /**
     * Sets the iTunes store to search.
     *
     * @param countryCode <a href="https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">ISO 3166-1 alpha-2</a> country code
     *                    for the iTunes store to search. Default is US.
     * @return the current instance of {@link MediaSearch}
     */
    public T inCountry(CountryCode countryCode) {
        this.countryCode = countryCode;
        return (T)this;
    }

    /**
     * check the validity of all required data before executing the search
     *
     * @throws MissingRequiredParameterException if the search term is not set.
     * @throws InvalidParameterException if any of the set parameters are invalid.
     */
    protected void runPreExecutionChecks() {
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

    /**
     * execute the search
     *
     * @return A {@link org.json.JSONObject} object containing the results.
     * @throws MissingRequiredParameterException if the search term is not set.
     * @throws InvalidParameterException if any of the set parameters are invalid.
     * @throws SearchURLConstructionFailure if there is an error during url construction.
     * @throws NetworkCommunicationException if any issues occur while communicating with the iTunes api.
     */
    public JSONObject execute() {
        runPreExecutionChecks();
        String urlString = constructUrlString();
        URL url = createUrlObject(urlString);
        searchUrl = url;
        return new SearchManager().executeSearch(url);
    }

    private URL createUrlObject(String urlString) {
        try {
            return new URL(urlString);
        } catch (MalformedURLException e) {
            throw new SearchURLConstructionFailure("Error during search url construction: " + e.getMessage());
        }
    }

    protected abstract String constructUrlString();

    /**
     *
     * @return the term being searched for
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
     * @return the iTunes store being searched
     */
    public CountryCode getCountryCode() {
        return countryCode;
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
     * @return the url used to search the iTunes store.
     */
    public URL getSearchUrl() {
        return searchUrl;
    }

}
