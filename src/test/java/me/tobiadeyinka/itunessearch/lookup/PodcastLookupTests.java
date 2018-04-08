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

import me.tobiadeyinka.itunessearch.exceptions.NoMatchFoundException;

import org.testng.annotations.Test;

/**
 * Tests for all podcast look up methods
 *
 * Created by Tobi Adeyinka on 2017. 10. 18..
 */
public class PodcastLookupTests extends BaseLookupTest {

    @Test
    public void getPodcastByCollectionId() throws NoMatchFoundException {
        nullifyResponse();

        try {
            int limit = 1;
            long podcastID = 1272970334;
            response = PodcastLookup.getPodcastByCollectionId(podcastID);

            verifyResponseHasResults();
            verifyResponseMatchesLimit(limit);
        } finally { logResponse(); }
    }

    @Test(expectedExceptions = NoMatchFoundException.class)
    public void getPodcastByNonExistingCollectionId() throws NoMatchFoundException {
        nullifyResponse();

        try {
            long podcastID = 1;
            response = PodcastLookup.getPodcastByCollectionId(podcastID);
        } finally { logResponse(); }
    }

    @Test
    public void getTopPodcasts() {
        nullifyResponse();

        try {
            response = PodcastLookup.topPodcasts();
            verifyResponseHasResults();
        } finally { logResponse(); }
    }

    @Test
    public void getTopPodcastsWithLimit() {
        nullifyResponse();

        try {
            int limit = 5;
            response = PodcastLookup.topPodcasts(limit);

            verifyResponseHasResults();
            verifyResponseMatchesLimit(limit);
        } finally { logResponse(); }
    }

    @Test
    public void getComedyPodcasts() {
        nullifyResponse();

        try {
            response = PodcastLookup.comedyPodcasts();
            verifyResponseHasResults();
        } finally { logResponse(); }
    }

    @Test
    public void getComedyPodcastsWithLimit() {
        nullifyResponse();

        try {
            int limit = 5;
            response = PodcastLookup.comedyPodcasts(limit);

            verifyResponseHasResults();
            verifyResponseMatchesLimit(limit);
        } finally { logResponse(); }
    }

    @Test
    public void getNewsAndPoliticsPodcasts() {
        nullifyResponse();

        try {
            response = PodcastLookup.newsAndPoliticsPodcasts();
            verifyResponseHasResults();
        } finally { logResponse(); }
    }

    @Test
    public void getNewsAndPoliticsPodcastsWithLimit() {
        nullifyResponse();

        try {
            int limit = 5;
            response = PodcastLookup.newsAndPoliticsPodcasts(limit);

            verifyResponseHasResults();
            verifyResponseMatchesLimit(limit);
        } finally { logResponse(); }
    }

    @Test
    public void getSocietyAndCulturePodcasts() {
        nullifyResponse();

        try {
            response = PodcastLookup.societyAndCulturePodcasts();
            verifyResponseHasResults();
        } finally { logResponse(); }
    }

    @Test
    public void getSocietyAndCulturePodcastsWithLimit() {
        nullifyResponse();

        try {
            int limit = 5;
            response = PodcastLookup.societyAndCulturePodcasts(limit);

            verifyResponseHasResults();
            verifyResponseMatchesLimit(limit);
        } finally { logResponse(); }
    }

}
