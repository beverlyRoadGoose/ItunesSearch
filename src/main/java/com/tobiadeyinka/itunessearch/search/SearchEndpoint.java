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

import org.json.JSONObject;

/**
 * Declares required methods for all search endpoints
 *
 * Created by Tobi Adeyinka on 2017. 10. 16..
 */
interface SearchEndpoint<T, U, V> {

    T with(String searchTerm);
    T withLimit(int limit);
    T inAttribute(U attribute);
    T inCountry(CountryCode countryCode);
    T withApiVersion(ItunesApiVersion apiVersion);
    T withReturnLanguage(ReturnLanguage returnLanguage);
    T allowExplicit(boolean allowExplicit);
    T andReturn(V returnType);
    JSONObject execute() throws ItunesSearchException;

}
