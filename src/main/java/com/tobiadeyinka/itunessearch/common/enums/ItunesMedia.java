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

package com.tobiadeyinka.itunessearch.common.enums;

/**
 * enumeration of all iTunes media types
 *
 * Created by Tobi Adeyinka on 2017. 10. 15..
 */
public enum ItunesMedia {

    ALL("all"),
    MOVIE("movie"),
    MUSIC("music"),
    EBOOK("ebook"),
    TV_SHOW("tvShow"),
    PODCAST("podcast"),
    SOFTWARE("software"),
    SHORT_FILM("shortFilm"),
    AUDIO_BOOK("audiobook"),
    MUSIC_VIDEO("musicVideo");

    private String parameterValue;

    ItunesMedia(String parameterValue) {
        this.parameterValue = parameterValue;
    }

    /**
     *
     * @return the parameter value of the media type
     */
    public String getParameterValue() {
        return parameterValue;
    }

}
