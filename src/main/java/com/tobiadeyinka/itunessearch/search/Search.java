/*
 *  Copyright 2017 Oluwatobi Adeyinka
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

/**
 * Parent class for all searches
 *
 * Created by Tobi Adeyinka on 2017. 10. 16..
 */
abstract class Search<T> {

    /**
     * The term to search for.
     */
    protected String searchTerm;

    /**
     * The maximum number of item's to return. Default is 50.
     */
    protected int limit = 50;

    /**
     * Sets the term to search for. Required.
     *
     * @param searchTerm the term to search for
     * @return the current instance of {@link T}
     */
    public T with(String searchTerm) {
        this.searchTerm = searchTerm;
        return (T)this;
    }

    /**
     * Sets the maximum number of item's to return. Default is 50.
     *
     * @param limit the maximum number of item's to return.
     * @return the current instance of {@link T}
     */
    public T withLimit(int limit) {
        this.limit = limit;
        return (T)this;
    }

}
