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

package me.tobiadeyinka.itunessearch.lookup;

import com.neovisionaries.i18n.CountryCode;
import me.tobiadeyinka.itunessearch.exceptions.NoMatchFoundException;

import org.testng.annotations.Test;

/**
 * Tests for all music lookup methods
 *
 * Created by Tobi Adeyinka on 2018. 05. 06..
 */
public class MovieLookupTests extends BaseLookupTest {

    @Test
    public void getMovieById() throws NoMatchFoundException {
        try {
            long trackId = 1348407955;
            response = MovieLookup.getMovieById(trackId);

            verifyResponseHasResults();
            verifyResponseMatchesLimit(1);
        } finally { logResponse(); }
    }

    @Test(expectedExceptions = NoMatchFoundException.class)
    public void getMovieByNonExistingTrackId() throws NoMatchFoundException {
        try {
            long movieId = 1;
            response = MovieLookup.getMovieById(movieId);
        } finally { logResponse(); }
    }

    @Test
    public void getTopMovies() {
        try {
            response = MovieLookup.topMovies();
            verifyResponseHasResults();
        } finally { logResponse(); }
    }

    @Test
    public void getTopMoviesWithLimit() {
        try {
            response = MovieLookup.topMovies(limit);
            verifyResponseHasResults();
            verifyResponseMatchesLimit(limit);
        } finally { logResponse(); }
    }

    @Test
    public void getTopMoviesInSpecificCountry() {
        try {
            response = MovieLookup.topMovies(CountryCode.CA);
            verifyResponseHasResults();
        } finally { logResponse(); }
    }

    @Test
    public void getTopMoviesWithLimitInSpecificCountry() {
        try {
            response = MovieLookup.topMovies(CountryCode.CA, limit);
            verifyResponseHasResults();
            verifyResponseMatchesLimit(limit);
        } finally { logResponse(); }
    }

}
