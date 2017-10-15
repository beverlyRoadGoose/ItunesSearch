package com.tobiadeyinka.itunessearch.search;

import com.neovisionaries.i18n.CountryCode;

import com.tobiadeyinka.itunessearch.common.enums.ReturnLanguage;
import com.tobiadeyinka.itunessearch.exceptions.MissingRequiredParameterException;

import org.json.JSONObject;

/**
 * Base class for media search classes
 *
 * Created by Tobi Adeyinka on 2017. 10. 15..
 */
class MediaSearch {

    /**
     * The term to search for.
     */
    protected String searchTerm;

    /**
     *  <a href="https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">ISO 3166-1 alpha-2</a> country code for the
     *  iTunes store to search. Default is US.
     */
    protected CountryCode countryCode = CountryCode.US;

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
     * The language to return the result in (only english/japanese). Default is english.
     */
    protected ReturnLanguage returnLanguage;

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
     * Set the version of the iTunes api to use (1/2). Default is 2.
     *
     * @param apiVersion the version of the iTunes api to use.
     * @return the current instance of {@link MediaSearch}
     */
    public MediaSearch withApiVersion(int apiVersion) {
        this.apiVersion = apiVersion;
        return this;
    }

    /**
     * enable/disable content in search results. Explicit content is allowed by default.
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
     */
    public JSONObject execute() throws MissingRequiredParameterException {
        SearchManager searchManager;

        return null;
    }

    /**
     * check the validity of all required data before executing the search
     */
    protected void runPreExecutionChecks() throws MissingRequiredParameterException {

    }

}
