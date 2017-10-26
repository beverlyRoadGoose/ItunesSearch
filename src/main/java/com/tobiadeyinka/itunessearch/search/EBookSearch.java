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

import com.tobiadeyinka.itunessearch.entities.ItunesMedia;
import com.tobiadeyinka.itunessearch.entities.ebooks.EBookAttribute;
import com.tobiadeyinka.itunessearch.entities.ebooks.EBookSearchReturnType;

/**
 * EBook search API endpoint.
 *
 * See <a href="https://affiliate.itunes.apple.com/resources/documentation/itunes-store-web-service-search-api/#searching">
 *     Searching the iTunes Store</a> for more details about the parameters.
 *
 * Created by Tobi Adeyinka on 2017. 10. 26..
 */
public class EBookSearch extends Search<EBookSearch> {

    /**
     * The media type to search for. In this case eBooks.
     */
    private final ItunesMedia media = ItunesMedia.EBOOK;

    /**
     * The eBook attribute the search term is compared with. Default is all attributes.
     */
    private EBookAttribute attribute = EBookAttribute.ALL;

    /**
     * The type of results returned
     */
    private EBookSearchReturnType returnType = EBookSearchReturnType.DEFAULT;

    /**
     * Sets the eBook attribute the search term is compared with. Default is all attributes.
     *
     * @param attribute the eBook attribute the {@link #searchTerm} is compared with.
     * @return the current instance of {@link EBookSearch}
     */
    public EBookSearch inAttribute(EBookAttribute attribute) {
        this.attribute = attribute;
        return this;
    }

    /**
     * Sets the return type of the results
     *
     * @param returnType the type of results you want returned
     * @return the current instance of {@link EBookSearch}
     */
    public EBookSearch andReturn(EBookSearchReturnType returnType) {
        this.returnType = returnType;
        return this;
    }

    @Override
    String constructUrlString() {
        String urlString = "https://itunes.apple.com/search?";
        urlString += "term=" + searchTerm;
        urlString += "&country=" + countryCode.getAlpha2();
        urlString += "&media=" + media.getParameterValue();
        urlString += "&entity=" + returnType.getParameterValue();
        urlString += "&attributeType=" + attribute.getParameterValue();
        urlString += "&limit=" + limit;
        urlString += "&lang=" + returnLanguage.getCodeName();
        urlString += "&version=" + apiVersion;
        urlString += "&explicit=" + (allowExplicit ? "Yes" : "No");

        return urlString;
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
     * @return the attribute to search in
     */
    public EBookAttribute getAttribute() {
        return attribute;
    }

    /**
     *
     * @return the type of data to return
     */
    public EBookSearchReturnType getReturnType() {
        return returnType;
    }

}
