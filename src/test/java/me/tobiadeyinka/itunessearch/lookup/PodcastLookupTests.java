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
 * Tests for all podcast look up methods
 *
 * Created by Tobi Adeyinka on 2017. 10. 18..
 */
public class PodcastLookupTests extends BaseLookupTest {

    @Test
    public void getPodcastById() throws NoMatchFoundException {
        try {
            long podcastID = 1272970334;
            response = PodcastLookup.getPodcastById(podcastID);

            verifyResponseHasResults();
            verifyResponseMatchesLimit(1);
        } finally { logResponse(); }
    }

    @Test(expectedExceptions = NoMatchFoundException.class)
    public void getPodcastByNonExistingCollectionId() throws NoMatchFoundException {
        try {
            long podcastID = 1;
            response = PodcastLookup.getPodcastById(podcastID);
        } finally { logResponse(); }
    }

    @Test
    public void getTopPodcasts() {
        try {
            response = PodcastLookup.topPodcasts();
            verifyResponseHasResults();
        } finally { logResponse(); }
    }

    @Test
    public void getTopPodcastsWithLimit() {
        try {
            response = PodcastLookup.topPodcasts(limit);
            verifyResponseHasResults();
            verifyResponseMatchesLimit(limit);
        } finally { logResponse(); }
    }

    @Test
    public void getTopPodcastsInSpecificCountry() {
        try {
            response = PodcastLookup.topPodcasts(CountryCode.NG);
            verifyResponseHasResults();
        } finally { logResponse(); }
    }

    @Test
    public void getTopPodcastsWithLimitInSpecificCountry() {
        try {
            response = PodcastLookup.topPodcasts(CountryCode.NG, limit);
            verifyResponseHasResults();
            verifyResponseMatchesLimit(limit);
        } finally { logResponse(); }
    }

    @Test
    public void getComedyPodcasts() {
        try {
            response = PodcastLookup.comedyPodcasts();
            verifyResponseHasResults();
        } finally { logResponse(); }
    }

    @Test
    public void getComedyPodcastsWithLimit() {
        try {
            response = PodcastLookup.comedyPodcasts(limit);
            verifyResponseHasResults();
            verifyResponseMatchesLimit(limit);
        } finally { logResponse(); }
    }

    @Test
    public void getNewsAndPoliticsPodcasts() {
        try {
            response = PodcastLookup.newsAndPoliticsPodcasts();
            verifyResponseHasResults();
        } finally { logResponse(); }
    }

    @Test
    public void getNewsAndPoliticsPodcastsWithLimit() {
        try {
            response = PodcastLookup.newsAndPoliticsPodcasts(limit);
            verifyResponseHasResults();
            verifyResponseMatchesLimit(limit);
        } finally { logResponse(); }
    }

    @Test
    public void getSocietyAndCulturePodcasts() {
        try {
            response = PodcastLookup.societyAndCulturePodcasts();
            verifyResponseHasResults();
        } finally { logResponse(); }
    }

    @Test
    public void getSocietyAndCulturePodcastsWithLimit() {
        try {
            response = PodcastLookup.societyAndCulturePodcasts(limit);
            verifyResponseHasResults();
            verifyResponseMatchesLimit(limit);
        } finally { logResponse(); }
    }

}
