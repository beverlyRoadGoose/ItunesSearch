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

package com.tobiadeyinka.itunessearch.search;

import com.neovisionaries.i18n.CountryCode;

import com.tobiadeyinka.itunessearch.entities.*;
import com.tobiadeyinka.itunessearch.exceptions.*;

import org.testng.annotations.Test;

/**
 * Tests for all music video search methods.
 *
 * Created by Tobi Adeyinka on 2017. 10. 17..
 */
public class MusicVideoSearchTests extends BaseSearchTest {

    private String searchTerm = "the national";

    @Test(expectedExceptions = MissingRequiredParameterException.class)
    public void searchForMusicVideoWithoutSearchTerm() throws ItunesSearchException {
        new MusicVideoSearch().execute();
    }

    @Test
    public void searchForMusicVideoWithDefaultParameters() throws ItunesSearchException {
        nullifySearchAndResponse();

        try {
            search = new MusicVideoSearch().with(searchTerm);
            response = search.execute();
            verifyResponseHasResults();
        } finally {
            logUrlAndResponse();
        }
    }

    @Test
    public void searchForMusicVideoUsingArtistAttribute() throws ItunesSearchException {
        nullifySearchAndResponse();

        try {
            search = new MusicVideoSearch()
                    .with(searchTerm)
                    .inAttribute(MusicVideoAttribute.ARTIST);
            response = search.execute();
            verifyResponseHasResults();
        } finally {
            logUrlAndResponse();
        }
    }

    @Test
    public void searchForMusicVideoInSpecificStore() throws ItunesSearchException {
        nullifySearchAndResponse();

        try {
            search = new MusicVideoSearch()
                    .with(searchTerm)
                    .inCountry(CountryCode.NG);
            response = search.execute();
            verifyResponseHasResults();
        } finally {
            logUrlAndResponse();
        }
    }

    @Test
    public void searchForMusicVideoWithLimit() throws ItunesSearchException {
        nullifySearchAndResponse();

        try {
            int limit = 5;
            search = new MusicVideoSearch()
                    .with(searchTerm)
                    .withLimit(limit);
            response = search.execute();

            verifyResponseHasResults();
            verifyResponseMatchesLimit(limit);
        } finally {
            logUrlAndResponse();
        }
    }

    @Test
    public void searchForMusicVideoWithApiVersion1() throws ItunesSearchException {
        nullifySearchAndResponse();

        try {
            search = new MusicVideoSearch()
                    .with(searchTerm)
                    .withApiVersion(ItunesApiVersion.ONE);
            response = search.execute();
            verifyResponseHasResults();
        } finally {
            logUrlAndResponse();
        }
    }

    @Test
    public void searchForMusicVideoWithJapaneseResponse() throws ItunesSearchException {
        nullifySearchAndResponse();

        try {
            search = new MusicVideoSearch()
                    .with(searchTerm)
                    .withReturnLanguage(ReturnLanguage.JAPANESE);
            response = search.execute();
            verifyResponseHasResults();
        } finally {
            logUrlAndResponse();
        }
    }

    @Test
    public void searchForMusicVideoWithArtistReturnType() throws ItunesSearchException {
        nullifySearchAndResponse();

        try {
            search = new MusicVideoSearch()
                    .with(searchTerm)
                    .inAttribute(MusicVideoAttribute.ARTIST)
                    .andReturn(MusicVideoSearchReturnType.MUSIC_ARTIST);
            response = search.execute();
            verifyResponseHasResults();
        } finally {
            logUrlAndResponse();
        }
    }

    @Test
    public void searchForMusicVideoWithoutExplicitContent() throws ItunesSearchException {
        nullifySearchAndResponse();

        try {
            search = new MusicVideoSearch()
                    .with(searchTerm)
                    .allowExplicit(false);
            response = search.execute();
            verifyResponseHasResults();
        } finally {
            logUrlAndResponse();
        }
    }

    @Test
    public void comprehensiveMusicVideoSearch() throws ItunesSearchException {
        nullifySearchAndResponse();

        try {
            int limit = 5;
            search = new MusicVideoSearch()
                    .with(searchTerm)
                    .withLimit(limit)
                    .inCountry(CountryCode.US)
                    .inAttribute(MusicVideoAttribute.ARTIST)
                    .withReturnLanguage(ReturnLanguage.JAPANESE)
                    .withApiVersion(ItunesApiVersion.ONE);
            response = search.execute();

            verifyResponseHasResults();
            verifyResponseMatchesLimit(limit);
        } finally {
            logUrlAndResponse();
        }
    }
    
}
