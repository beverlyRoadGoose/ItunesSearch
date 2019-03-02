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
 * Tests for all book lookup methods
 *
 * Created by Tobi Adeyinka on 2018. 04. 17..
 */
public class BookLookupTests extends BaseLookupTest {

    @Test
    public void getBookById() throws NoMatchFoundException {
        try {
            long bookId = 1267086009;
            response = BookLookup.getBookById(bookId);

            verifyResponseHasResults();
            verifyResponseMatchesLimit(1);
        } finally { logResponse(); }
    }

    @Test(expectedExceptions = NoMatchFoundException.class)
    public void getBookByNonExistingTrackId() throws NoMatchFoundException {
        try {
            long bookId = 1;
            response = BookLookup.getBookById(bookId);
        } finally { logResponse(); }
    }

    @Test
    public void getTopFreeBooks() {
        try {
            response = BookLookup.topFree(); 
            verifyResponseHasResults();
        } finally { logResponse(); }
    }

    @Test
    public void getTopFreeBooksWithLimit() {
        try {
            response = BookLookup.topFree(limit);
            verifyResponseHasResults();
            verifyResponseMatchesLimit(limit);
        } finally { logResponse(); }
    }

    @Test
    public void getTopFreeBooksInSpecificCountry() {
        try {
            response = BookLookup.topFree(CountryCode.CA);
            verifyResponseHasResults();
        } finally { logResponse(); }
    }

    @Test
    public void getTopFreeBooksWithLimitInSpecificCountry() {
        try {
            response = BookLookup.topFree(CountryCode.CA, limit);
            verifyResponseHasResults();
            verifyResponseMatchesLimit(limit);
        } finally { logResponse(); }
    }

    @Test
    public void getTopPaidBooks() {
        try {
            response = BookLookup.topPaid();
            verifyResponseHasResults();
        } finally { logResponse(); }
    }

    @Test
    public void getTopPaidBooksWithLimit() {
        try {
            response = BookLookup.topPaid(limit);
            verifyResponseHasResults();
            verifyResponseMatchesLimit(limit);
        } finally { logResponse(); }
    }

    @Test
    public void getTopPaidBooksInSpecificCountry() {
        try {
            response = BookLookup.topPaid(CountryCode.CA);
            verifyResponseHasResults();
        } finally { logResponse(); }
    }

    @Test
    public void getTopPaidBooksWithLimitInSpecificCountry() {
        try {
            response = BookLookup.topPaid(CountryCode.CA, limit);
            verifyResponseHasResults();
            verifyResponseMatchesLimit(limit);
        } finally { logResponse(); }
    }

}
