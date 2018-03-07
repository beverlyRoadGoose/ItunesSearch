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

package com.tobiadeyinka.itunessearch.entities.media;

/**
 * Enumeration of music attributes that can be used to narrow down searches.
 *
 * See attribute parameter key in <a href="https://affiliate.itunes.apple.com/resources/documentation/itunes-store-web-service-search-api/#searching">
 *     Searching the iTunes Store</a> for more details.
 *
 * Created by Tobi Adeyinka on 2017. 10. 18..
 */
public enum MediaAttribute {

    ALL(""),
    MIX("mixTerm"),
    SONG("songTerm"),
    SHOW("showTerm"),
    MOVIE("movieTerm"),
    ALBUM("albumTerm"),
    TITLE("titleTerm"),
    GENRE("genreIndex"),
    AUTHOR("authorTerm"),
    ARTIST("artistTerm"),
    ACTOR_TERM("actorTerm"),
    COMPOSER("composerTerm"),
    KEYWORDS("keywordsTerm"),
    DIRECTOR("directorTerm"),
    PRODUCER("producerTerm"),
    RATING_TERM("ratingTerm"),
    ALL_TRACK("allTrackTerm"),
    TV_SEASON("tvSeasonTerm"),
    ALL_ARTIST("allArtistTerm"),
    TV_EPISODE("tvEpisodeTerm"),
    SHORT_FILM("shortFilmTerm"),
    RATING_INDEX("ratingIndex"),
    LANGUAGE_TERM("languageTerm"),
    DESCRIPTION("descriptionTerm"),
    RELEASE_YEAR("releaseYearTerm"),
    FEATURE_FILM("featureFilmTerm"),
    MOVIE_ARTIST("movieArtistTerm");

    private String parameterValue;

    MediaAttribute(String parameterValue) {
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
