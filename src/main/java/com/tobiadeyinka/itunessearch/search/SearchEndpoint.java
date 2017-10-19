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
import com.tobiadeyinka.itunessearch.entities.ReturnLanguage;
import com.tobiadeyinka.itunessearch.entities.ItunesApiVersion;

import org.json.JSONObject;

/**
 * Declares required methods for search endpoints with specific media types
 *
 * Created by Tobi Adeyinka on 2017. 10. 16..
 */
interface SearchEndpoint<T, U, V> {

    /**
     * Sets the term to search for. Required.
     *
     * @param searchTerm the term to search for
     * @return the current instance of {@link T}
     */
    T with(String searchTerm);

    /**
     * Sets the maximum number of item's to return. Default is 50.
     *
     * @param limit the maximum number of item's to return.
     * @return the current instance of {@link T}
     */
    T withLimit(int limit);

    /**
     * Sets the attribute the search term is compared with. Default is all attributes.
     *
     * @param attribute the attribute the search term is compared with.
     * @return the current instance of {@link T}
     */
    T inAttribute(U attribute);

    /**
     * Sets the iTunes store to search.
     *
     * @param countryCode <a href="https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">ISO 3166-1 alpha-2</a> country code
     *                    for the iTunes store to search. Default is US.
     * @return the current instance of {@link T}
     */
    T inCountry(CountryCode countryCode);

    /**
     * Set the version of the iTunes api to use (1/2). Default is 2.
     *
     * @param apiVersion the version of the iTunes api to use.
     * @return the current instance of {@link T}
     */
    T withApiVersion(ItunesApiVersion apiVersion);

    /**
     * Sets the language results are return in. Default is english.
     *
     * @param returnLanguage The language result should be return in.
     * @return the current instance of {@link T}
     */
    T withReturnLanguage(ReturnLanguage returnLanguage);

    /**
     * allow/remove explicit content in search results. Explicit content is allowed by default.
     *
     * @param allowExplicit allow explicit content or not.
     * @return the current instance of {@link T}
     */
    T allowExplicit(boolean allowExplicit);

    /**
     * Sets the return type of the results
     *
     * @param returnType the type of results you want returned
     * @return the current instance of {@link T}
     */
    T andReturn(V returnType);

    /**
     * execute the search
     *
     * @return A {@link org.json.JSONObject} object containing the results.
     * @throws MissingRequiredParameterException if the search term is not set.
     * @throws InvalidParameterException if any of the set parameters are invalid.
     * @throws SearchURLConstructionFailure if there is an error during url construction.
     * @throws NetworkCommunicationException if any issues occur while communicating with the iTunes api.
     */
    JSONObject execute() throws ItunesSearchException;

}
