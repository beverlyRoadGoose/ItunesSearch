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
 * Tests for all audio book search methods.
 *
 * Created by Tobi Adeyinka on 2017. 10. 18..
 */
public class AudioBookSearchTests extends BaseSearchTest {

    /*
     * used a common word on purpose
     */
    private String searchTerm = "the";

    @Test(expectedExceptions = MissingRequiredParameterException.class)
    public void searchForAudioBookWithoutSearchTerm() {
        new AudioBookSearch().execute();
    }

    @Test
    public void searchForAudioBookWithDefaultParameters() {
        try {
            search = new AudioBookSearch().with(searchTerm);
            response = search.execute();
            verifyResponseHasResults();
        } finally { logUrlAndResponse(); }
    }

    @Test
    public void searchForAudioBookUsingAuthorAttribute() {
        try {
            search = new AudioBookSearch()
                    .with(searchTerm)
                    .inAttribute(AudioBookAttribute.AUTHOR);
            response = search.execute();
            verifyResponseHasResults();
        } finally { logUrlAndResponse(); }
    }

    @Test
    public void searchForAudioBookInSpecificStore() {
        try {
            search = new AudioBookSearch()
                    .with(searchTerm)
                    .inCountry(CountryCode.NG);
            response = search.execute();
            verifyResponseHasResults();
        } finally { logUrlAndResponse(); }
    }

    @Test
    public void searchForAudioBookWithLimit() {
        try {
            int limit = 5;
            search = new AudioBookSearch()
                    .with(searchTerm)
                    .withLimit(limit);
            response = search.execute();

            verifyResponseHasResults();
            verifyResponseMatchesLimit(limit);
        } finally { logUrlAndResponse(); }
    }

    @Test
    public void searchForAudioBookWithApiVersion1() {
        try {
            search = new AudioBookSearch()
                    .with(searchTerm)
                    .withApiVersion(ItunesApiVersion.ONE);
            response = search.execute();
            verifyResponseHasResults();
        } finally { logUrlAndResponse(); }
    }

    @Test
    public void searchForAudioBookWithJapaneseResponse() {
        try {
            search = new AudioBookSearch()
                    .with(searchTerm)
                    .withReturnLanguage(ReturnLanguage.JAPANESE);
            response = search.execute();
            verifyResponseHasResults();
        } finally { logUrlAndResponse(); }
    }

    @Test
    public void searchForAudioBookWithAuthorReturnType() {
        try {
            search = new AudioBookSearch()
                    .with(searchTerm)
                    .inAttribute(AudioBookAttribute.AUTHOR)
                    .andReturn(AudioBookSearchReturnType.AUTHOR);
            response = search.execute();
            verifyResponseHasResults();
        } finally { logUrlAndResponse(); }
    }

    @Test
    public void searchForAudioBookWithoutExplicitContent() {
        try {
            search = new AudioBookSearch()
                    .with(searchTerm)
                    .allowExplicit(false);
            response = search.execute();
            verifyResponseHasResults();
        } finally { logUrlAndResponse(); }
    }

    @Test
    public void comprehensiveAudioBookSearch() {
        try {
            int limit = 5;
            search = new AudioBookSearch()
                    .with(searchTerm)
                    .withLimit(limit)
                    .inCountry(CountryCode.US)
                    .inAttribute(AudioBookAttribute.AUTHOR)
                    .withReturnLanguage(ReturnLanguage.JAPANESE)
                    .withApiVersion(ItunesApiVersion.ONE);
            response = search.execute();

            verifyResponseHasResults();
            verifyResponseMatchesLimit(limit);
        } finally { logUrlAndResponse(); }
    }
    
}
