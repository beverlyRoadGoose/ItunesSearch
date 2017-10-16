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

import com.tobiadeyinka.itunessearch.common.enums.ItunesMedia;
import com.tobiadeyinka.itunessearch.common.enums.ReturnLanguage;
import com.tobiadeyinka.itunessearch.common.enums.ItunesApiVersion;

/**
 * Music search API endpoint.
 *
 * See <a href="https://affiliate.itunes.apple.com/resources/documentation/itunes-store-web-service-search-api/#searching">
 *     Searching the iTunes Store</a> for more details about the parameters.
 *
 * Created by Tobi Adeyinka on 2017. 10. 16..
 */
public class MusicSearch implements SearchEndpoint<MusicSearch> {

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

    @Override
    public MusicSearch withLimit(int limit) {
        return null;
    }

    @Override
    public MusicSearch with(String searchTerm) {
        return null;
    }

    @Override
    public MusicSearch inCountry(CountryCode countryCode) {
        return null;
    }

    @Override
    public MusicSearch allowExplicit(boolean allowExplicit) {
        return null;
    }

    @Override
    public MusicSearch withApiVersion(ItunesApiVersion apiVersion) {
        return null;
    }

    @Override
    public MusicSearch withReturnLanguage(ReturnLanguage returnLanguage) {
        return null;
    }

}
