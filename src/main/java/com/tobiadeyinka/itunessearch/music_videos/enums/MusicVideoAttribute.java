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

package com.tobiadeyinka.itunessearch.music_videos.enums;

/**
 * Enumeration of music video attributes that can be used to narrow down music searches.
 *
 * Created by Tobi Adeyinka on 2017. 10. 17..
 */
public enum MusicVideoAttribute {

    ALL(""),
    SONG("songTerm"),
    ALBUM("albumTerm"),
    ARTIST("artistTerm"),
    GENRE("genreIndex"),
    RATING("ratingIndex");

    private String parameterValue;

    MusicVideoAttribute(String parameterValue) {
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
