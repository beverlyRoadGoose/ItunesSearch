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
 * Created by Tobi Adeyinka on 2018. 04. 07..
 */
public class MusicLookupTests extends BaseLookupTest {

    private int limit = 5;

    @Test
    public void getSongByTrackId() throws NoMatchFoundException {
        nullifyResponse();

        try {
            long trackId = 879273573;
            response = MusicLookup.getSongByTrackId(trackId);

            verifyResponseHasResults();
            verifyResponseMatchesLimit(1);
        } finally { logResponse(); }
    }

    @Test(expectedExceptions = NoMatchFoundException.class)
    public void getSongByNonExistingTrackId() throws NoMatchFoundException {
        nullifyResponse();

        try {
            long songID = 1;
            response = MusicLookup.getById(songID);
        } finally { logResponse(); }
    }

    @Test
    public void getTopSongs() {
        nullifyResponse();

        try {
            response = MusicLookup.topSongs();
            verifyResponseHasResults();
        } finally { logResponse(); }
    }

    @Test
    public void getTopSongsWithLimit() {
        nullifyResponse();

        try {
            response = MusicLookup.topSongs(limit);
            verifyResponseHasResults();
            verifyResponseMatchesLimit(5);
        } finally { logResponse(); }
    }

    @Test
    public void getTopSongsInSpecificCountry() {
        nullifyResponse();

        try {
            response = MusicLookup.topSongs(CountryCode.CA);
            verifyResponseHasResults();
        } finally { logResponse(); }
    }

    @Test
    public void getTopSongsWithLimitInSpecificCountry() {
        nullifyResponse();

        try {
            response = MusicLookup.topSongs(CountryCode.CA, limit);
            verifyResponseHasResults();
            verifyResponseMatchesLimit(limit);
        } finally { logResponse(); }
    }

    @Test
    public void getHotTracks() {
        nullifyResponse();

        try {
            response = MusicLookup.hotTracks();
            verifyResponseHasResults();
        } finally { logResponse(); }
    }

    @Test
    public void getHotTracksWithLimit() {
        nullifyResponse();

        try {
            response = MusicLookup.hotTracks(limit);
            verifyResponseHasResults();
            verifyResponseMatchesLimit(limit);
        } finally { logResponse(); }
    }

    @Test
    public void getHotTracksInSpecificCountry() {
        nullifyResponse();

        try {
            response = MusicLookup.hotTracks(CountryCode.CA);
            verifyResponseHasResults();
        } finally { logResponse(); }
    }

    @Test
    public void getHotTracksWithLimitInSpecificCountry() {
        nullifyResponse();

        try {
            response = MusicLookup.hotTracks(CountryCode.CA, limit);
            verifyResponseHasResults();
            verifyResponseMatchesLimit(limit);
        } finally { logResponse(); }
    }

    @Test
    public void getNewMusic() {
        nullifyResponse();

        try {
            response = MusicLookup.newMusic();
            verifyResponseHasResults();
        } finally { logResponse(); }
    }

    @Test
    public void getNewMusicWithLimit() {
        nullifyResponse();

        try {
            response = MusicLookup.newMusic(limit);
            verifyResponseHasResults();
            verifyResponseMatchesLimit(limit);
        } finally { logResponse(); }
    }

    @Test
    public void getNewMusicInSpecificCountry() {
        nullifyResponse();

        try {
            response = MusicLookup.newMusic(CountryCode.CA);
            verifyResponseHasResults();
        } finally { logResponse(); }
    }

    @Test
    public void getNewMusicWithLimitInSpecificCountry() {
        nullifyResponse();

        try {
            response = MusicLookup.newMusic(CountryCode.CA, limit);
            verifyResponseHasResults();
            verifyResponseMatchesLimit(limit);
        } finally { logResponse(); }
    }

    @Test
    public void getRecentReleases() {
        nullifyResponse();

        try {
            response = MusicLookup.recentReleases();
            verifyResponseHasResults();
        } finally { logResponse(); }
    }

    @Test
    public void getRecentReleasesWithLimit() {
        nullifyResponse();

        try {
            response = MusicLookup.recentReleases(limit);
            verifyResponseHasResults();
            verifyResponseMatchesLimit(limit);
        } finally { logResponse(); }
    }

    @Test
    public void getRecentReleasesInSpecificCountry() {
        nullifyResponse();

        try {
            response = MusicLookup.recentReleases(CountryCode.CA);
            verifyResponseHasResults();
        } finally { logResponse(); }
    }

    @Test
    public void getRecentReleasesWithLimitInSpecificCountry() {
        nullifyResponse();

        try {
            response = MusicLookup.recentReleases(CountryCode.CA, limit);
            verifyResponseHasResults();
            verifyResponseMatchesLimit(limit);
        } finally { logResponse(); }
    }

}
