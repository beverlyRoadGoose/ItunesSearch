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

import org.json.JSONObject;
import com.neovisionaries.i18n.CountryCode;

public class BookLookup extends Lookup {

    private enum BookList {
        TOP_FREE("top-free"),
        TOP_PAID("top-paid");

        String urlKey;

        BookList(String urlKey) {
            this.urlKey = urlKey;
        }
    }

    /**
     * get a book by it's id
     *
     * @param id The id of the book
     * @return a JSONObject of the book
     * @throws NoMatchFoundException if no book is found with the passed id
     */
    public static JSONObject getBookById(long id) throws NoMatchFoundException {
        return getById(id);
    }

    /**
     * get the top {@value me.tobiadeyinka.itunessearch.lookup.Lookup#DEFAULT_LIMIT} free books in the default iTunes store
     *
     * @return a JSONObject containing a list of the top free books
     */
    public static JSONObject topFree() {
        return topFree(DEFAULT_LIMIT);
    }

    /**
     * get the top (limit) free books in the default iTunes store
     *
     * @param limit the maximum number of books to return
     * @return a JSONObject containing a list of the top free books
     */
    public static JSONObject topFree(int limit) {
        return queryBookList(BookList.TOP_FREE, DEFAULT_COUNTRY, limit);
    }

    /**
     * get the top {@value me.tobiadeyinka.itunessearch.lookup.Lookup#DEFAULT_LIMIT} free books in the specified iTunes store
     *
     * @param countryCode country code of the itunes store to search
     * @return a JSONObject containing a list of the top free books
     */
    public static JSONObject topFree(CountryCode countryCode) {
        return queryBookList(BookList.TOP_FREE, countryCode, DEFAULT_LIMIT);
    }

    /**
     * get the top (limit) free books in the specified iTunes store
     *
     * @param countryCode country code of the itunes store to search
     * @param limit the maximum number of books to return
     * @return a JSONObject containing a list of the top free books
     */
    public static JSONObject topFree(CountryCode countryCode, int limit) {
        return queryBookList(BookList.TOP_FREE, countryCode, limit);
    }

    /**
     * get the top {@value me.tobiadeyinka.itunessearch.lookup.Lookup#DEFAULT_LIMIT} paid books in the default iTunes store
     *
     * @return a JSONObject containing a list of the top paid books
     */
    public static JSONObject topPaid() {
        return topPaid(DEFAULT_LIMIT);
    }

    /**
     * get the top (limit) paid books in the default iTunes store
     *
     * @param limit the maximum number of books to return
     * @return a JSONObject containing a list of the top paid books
     */
    public static JSONObject topPaid(int limit) {
        return queryBookList(BookList.TOP_PAID, DEFAULT_COUNTRY, limit);
    }

    /**
     * get the top {@value me.tobiadeyinka.itunessearch.lookup.Lookup#DEFAULT_LIMIT} paid books in the specified iTunes store
     *
     * @param countryCode country code of the itunes store to search
     * @return a JSONObject containing a list of the top paid books
     */
    public static JSONObject topPaid(CountryCode countryCode) {
        return queryBookList(BookList.TOP_PAID, countryCode, DEFAULT_LIMIT);
    }

    /**
     * get the top (limit) paid books in the specified iTunes store
     *
     * @param countryCode country code of the itunes store to search
     * @param limit the maximum number of books to return
     * @return a JSONObject containing a list of the top paid books
     */
    public static JSONObject topPaid(CountryCode countryCode, int limit) {
        return queryBookList(BookList.TOP_PAID, countryCode, limit);
    }

    private static JSONObject queryBookList(BookLookup.BookList list, CountryCode countryCode, int limit) {
        return executeQuery(
            String.format(
                "https://rss.itunes.apple.com/api/v1/%s/books/%s/all/%s/explicit.json",
                countryCode.getAlpha2(), list.urlKey, limit
            )
        );
    }

}
