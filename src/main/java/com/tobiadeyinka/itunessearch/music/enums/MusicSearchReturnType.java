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

package com.tobiadeyinka.itunessearch.music.enums;

/**
 * Enumeration of the possible entity return types from music search queries.
 *
 * See entity parameter key in <a href="https://affiliate.itunes.apple.com/resources/documentation/itunes-store-web-service-search-api/#searching">
 *     Searching the iTunes Store</a> for more details.
 *
 * Created by Tobi Adeyinka on 2017. 10. 16..
 */
public enum MusicSearchReturnType {

    DEFAULT(""),
    MIX("mix"),
    SONG("song"),
    ALBUM("album"),
    VIDEO("musicVideo"),
    ARTIST("musicArtist"),
    COMPOSER("composerTerm"),
    GENRE_INDEX("genreIndex"),
    TRACK("musicTrack");

    private String parameterValue;

    MusicSearchReturnType(String parameterValue){
        this.parameterValue = parameterValue;
    }

    /**
     *
     * @return the value to be passed in the api request
     */
    public String getParameterValue() {
        return parameterValue;
    }
}
