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
 * Tests for all music search methods.
 *
 * Created by Tobi Adeyinka on 2017. 10. 16..
 */
public class MusicSearchTests extends BaseSearchTest {

    private String searchTerm = "the national";

    @Test(expectedExceptions = MissingRequiredParameterException.class)
    public void searchForMusicWithoutSearchTerm() {
        new MusicSearch().execute();
    }

    @Test
    public void searchForMusicWithDefaultParameters() {
        try {
            search = new MusicSearch().with(searchTerm);
            response = search.execute();
            verifyResponseHasResults();
        } finally { logUrlAndResponse(); }
    }

    @Test
    public void searchForMusicUsingArtistAttribute() {
        try {
            search = new MusicSearch()
                    .with(searchTerm)
                    .inAttribute(MusicAttribute.ARTIST);
            response = search.execute();
            verifyResponseHasResults();
        } finally { logUrlAndResponse(); }
    }

    @Test
    public void searchForMusicInSpecificStore() {
        try {
            search = new MusicSearch()
                    .with(searchTerm)
                    .inCountry(CountryCode.NG);
            response = search.execute();
            verifyResponseHasResults();
        } finally { logUrlAndResponse(); }
    }

    @Test
    public void searchForMusicWithLimit() {
        try {
            int limit = 5;
            search = new MusicSearch()
                    .with(searchTerm)
                    .withLimit(limit);
            response = search.execute();

            verifyResponseHasResults();
            verifyResponseMatchesLimit(limit);
        } finally { logUrlAndResponse(); }
    }

    @Test
    public void searchForMusicWithApiVersion1() {
        try {
            search = new MusicSearch()
                    .with(searchTerm)
                    .withApiVersion(ItunesApiVersion.ONE);
            response = search.execute();
            verifyResponseHasResults();
        } finally { logUrlAndResponse(); }
    }

    @Test
    public void searchForMusicWithJapaneseResponse() {
        try {
            search = new MusicSearch()
                    .with(searchTerm)
                    .withReturnLanguage(ReturnLanguage.JAPANESE);
            response = search.execute();
            verifyResponseHasResults();
        } finally { logUrlAndResponse(); }
    }

    @Test
    public void searchForMusicWithArtistReturnType() {
        try {
            search = new MusicSearch()
                    .with(searchTerm)
                    .inAttribute(MusicAttribute.ARTIST)
                    .andReturn(MusicSearchReturnType.ARTIST);
            response = search.execute();
            verifyResponseHasResults();
        } finally { logUrlAndResponse(); }
    }

    @Test
    public void searchForMusicWithoutExplicitContent() {
        try {
            search = new MusicSearch()
                    .with(searchTerm)
                    .allowExplicit(false);
            response = search.execute();
            verifyResponseHasResults();
        } finally { logUrlAndResponse(); }
    }

    @Test
    public void comprehensiveMusicSearch() {
        try {
            int limit = 5;
            search = new MusicSearch()
                    .with(searchTerm)
                    .withLimit(limit)
                    .inCountry(CountryCode.NG)
                    .inAttribute(MusicAttribute.ARTIST)
                    .withReturnLanguage(ReturnLanguage.JAPANESE)
                    .withApiVersion(ItunesApiVersion.ONE);
            response = search.execute();

            verifyResponseHasResults();
            verifyResponseMatchesLimit(limit);
        } finally { logUrlAndResponse(); }
    }

}
