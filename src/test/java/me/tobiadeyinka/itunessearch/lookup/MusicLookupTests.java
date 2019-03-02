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

    @Test
    public void getSongById() throws NoMatchFoundException {
        try {
            long trackId = 879273573;
            response = MusicLookup.getSongById(trackId);

            verifyResponseHasResults();
            verifyResponseMatchesLimit(1);
        } finally { logResponse(); }
    }

    @Test(expectedExceptions = NoMatchFoundException.class)
    public void getSongByNonExistingTrackId() throws NoMatchFoundException {
        try {
            long songId = 1;
            response = MusicLookup.getSongById(songId);
        } finally { logResponse(); }
    }

    @Test
    public void getTopSongs() {
        try {
            response = MusicLookup.topSongs();
            verifyResponseHasResults();
        } finally { logResponse(); }
    }

    @Test
    public void getTopSongsWithLimit() {
        try {
            response = MusicLookup.topSongs(limit);
            verifyResponseHasResults();
            verifyResponseMatchesLimit(limit);
        } finally { logResponse(); }
    }

    @Test
    public void getTopSongsInSpecificCountry() {
        try {
            response = MusicLookup.topSongs(CountryCode.CA);
            verifyResponseHasResults();
        } finally { logResponse(); }
    }

    @Test
    public void getTopSongsWithLimitInSpecificCountry() {
        try {
            response = MusicLookup.topSongs(CountryCode.CA, limit);
            verifyResponseHasResults();
            verifyResponseMatchesLimit(limit);
        } finally { logResponse(); }
    }

    @Test
    public void getHotTracks() {
        try {
            response = MusicLookup.hotTracks();
            verifyResponseHasResults();
        } finally { logResponse(); }
    }

    @Test
    public void getHotTracksWithLimit() {
        try {
            response = MusicLookup.hotTracks(limit);
            verifyResponseHasResults();
            verifyResponseMatchesLimit(limit);
        } finally { logResponse(); }
    }

    @Test
    public void getHotTracksInSpecificCountry() {
        try {
            response = MusicLookup.hotTracks(CountryCode.CA);
            verifyResponseHasResults();
        } finally { logResponse(); }
    }

    @Test
    public void getHotTracksWithLimitInSpecificCountry() {
        try {
            response = MusicLookup.hotTracks(CountryCode.CA, limit);
            verifyResponseHasResults();
            verifyResponseMatchesLimit(limit);
        } finally { logResponse(); }
    }

    @Test
    public void getNewMusic() {
        try {
            response = MusicLookup.newMusic();
            verifyResponseHasResults();
        } finally { logResponse(); }
    }

    @Test
    public void getNewMusicWithLimit() {
        try {
            response = MusicLookup.newMusic(limit);
            verifyResponseHasResults();
            verifyResponseMatchesLimit(limit);
        } finally { logResponse(); }
    }

    @Test
    public void getNewMusicInSpecificCountry() {
        try {
            response = MusicLookup.newMusic(CountryCode.CA);
            verifyResponseHasResults();
        } finally { logResponse(); }
    }

    @Test
    public void getNewMusicWithLimitInSpecificCountry() {
        try {
            response = MusicLookup.newMusic(CountryCode.CA, limit);
            verifyResponseHasResults();
            verifyResponseMatchesLimit(limit);
        } finally { logResponse(); }
    }

    @Test
    public void getRecentReleases() {
        try {
            response = MusicLookup.recentReleases();
            verifyResponseHasResults();
        } finally { logResponse(); }
    }

    @Test
    public void getRecentReleasesWithLimit() {
        try {
            response = MusicLookup.recentReleases(limit);
            verifyResponseHasResults();
            verifyResponseMatchesLimit(limit);
        } finally { logResponse(); }
    }

    @Test
    public void getRecentReleasesInSpecificCountry() {
        try {
            response = MusicLookup.recentReleases(CountryCode.CA);
            verifyResponseHasResults();
        } finally { logResponse(); }
    }

    @Test
    public void getRecentReleasesWithLimitInSpecificCountry() {
        try {
            response = MusicLookup.recentReleases(CountryCode.CA, limit);
            verifyResponseHasResults();
            verifyResponseMatchesLimit(limit);
        } finally { logResponse(); }
    }

    @Test
    public void getAlbumById() throws NoMatchFoundException {
        try {
            long albumId = 879273573;
            response = MusicLookup.getAlbumById(albumId);

            verifyResponseHasResults();
            verifyResponseMatchesLimit(1);
        } finally { logResponse(); }
    }

    @Test(expectedExceptions = NoMatchFoundException.class)
    public void getAlbumByNonExistingTrackId() throws NoMatchFoundException {
        try {
            long albumID = 1;
            response = MusicLookup.getAlbumById(albumID);
        } finally { logResponse(); }
    }

    @Test
    public void geTopAlbums() {
        try {
            response = MusicLookup.topAlbums();
            verifyResponseHasResults();
        } finally { logResponse(); }
    }

    @Test
    public void getTopAlbumsWithLimit() {
        try {
            response = MusicLookup.topAlbums(limit);
            verifyResponseHasResults();
            verifyResponseMatchesLimit(limit);
        } finally { logResponse(); }
    }

    @Test
    public void getTopAlbumsInSpecificCountry() {
        try {
            response = MusicLookup.topAlbums(CountryCode.CA);
            verifyResponseHasResults();
        } finally { logResponse(); }
    }

    @Test
    public void getTopAlbumsWithLimitInSpecificCountry() {
        try {
            response = MusicLookup.topAlbums(CountryCode.CA, limit);
            verifyResponseHasResults();
            verifyResponseMatchesLimit(limit);
        } finally { logResponse(); }
    }

}
