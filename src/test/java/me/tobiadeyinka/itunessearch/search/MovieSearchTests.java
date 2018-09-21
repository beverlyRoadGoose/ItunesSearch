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
import me.tobiadeyinka.itunessearch.exceptions.MissingRequiredParameterException;

import org.testng.annotations.Test;

/**
 * Tests for all movie search methods.
 *
 * Created by Tobi Adeyinka on 2017. 10. 16..
 */
public class MovieSearchTests extends BaseSearchTest {

    private String searchTerm = "the imitation game";

    @Test(expectedExceptions = MissingRequiredParameterException.class)
    public void searchForMovieWithoutSearchTerm() {
        new MovieSearch().execute();
    }

    @Test
    public void searchForMovieWithDefaultParameters() {
        nullifySearchAndResponse();
        try {
            search = new MovieSearch().with(searchTerm);
            response = search.execute();
            verifyResponseHasResults();
        } finally { logUrlAndResponse(); }
    }

    @Test
    public void searchForMovieUsingDirectorAttribute() {
        nullifySearchAndResponse();
        try {
            String actor = "Steven";
            search = new MovieSearch()
                    .with(actor)
                    .inAttribute(MovieAttribute.DIRECTOR);
            response = search.execute();
            verifyResponseHasResults();
        } finally { logUrlAndResponse(); }
    }

    @Test
    public void searchForMovieInSpecificStore() {
        nullifySearchAndResponse();
        try {
            search = new MovieSearch()
                    .with(searchTerm)
                    .inCountry(CountryCode.CA);
            response = search.execute();
            verifyResponseHasResults();
        } finally { logUrlAndResponse(); }
    }

    @Test
    public void searchForMovieWithLimit() {
        nullifySearchAndResponse();
        try {
            /*
             * use a common word to match a lot of movies
             * for the sake of the limit test
             */
            String commonWord = "the";
            int limit = 5;

            search = new MovieSearch()
                    .with(commonWord)
                    .withLimit(limit);
            response = search.execute();

            verifyResponseHasResults();
            verifyResponseMatchesLimit(limit);
        } finally { logUrlAndResponse(); }
    }

    @Test
    public void searchForMovieWithApiVersion1() {
        nullifySearchAndResponse();
        try {
            search = new MovieSearch()
                    .with(searchTerm)
                    .withApiVersion(ItunesApiVersion.ONE);
            response = search.execute();
            verifyResponseHasResults();
        } finally { logUrlAndResponse(); }
    }

    @Test
    public void searchForMovieWithJapaneseResponse() {
        nullifySearchAndResponse();
        try {
            search = new MovieSearch()
                    .with(searchTerm)
                    .withReturnLanguage(ReturnLanguage.JAPANESE);
            response = search.execute();
            verifyResponseHasResults();
        } finally { logUrlAndResponse(); }
    }

    @Test
    public void searchForMovieWithArtistReturnType() {
        nullifySearchAndResponse();
        try {
            search = new MovieSearch()
                    .with(searchTerm)
                    .andReturn(MovieSearchReturnType.MOVIE_ARTIST);
            response = search.execute();
            verifyResponseHasResults();
        } finally { logUrlAndResponse(); }
    }

    @Test
    public void searchForMovieWithoutExplicitContent() {
        nullifySearchAndResponse();
        try {
            search = new MovieSearch()
                    .with(searchTerm)
                    .allowExplicit(false);
            response = search.execute();
            verifyResponseHasResults();
        } finally { logUrlAndResponse(); }
    }

    @Test
    public void comprehensiveMovieSearch() {
        nullifySearchAndResponse();
        try {
            int limit = 5;

            search = new MovieSearch()
                    .with(searchTerm)
                    .withLimit(limit)
                    .inCountry(CountryCode.CA)
                    .inAttribute(MovieAttribute.ARTIST)
                    .withReturnLanguage(ReturnLanguage.JAPANESE)
                    .withApiVersion(ItunesApiVersion.ONE);
            response = search.execute();

            verifyResponseHasResults();
            verifyResponseMatchesLimit(limit);
        } finally { logUrlAndResponse(); }
    }

}
