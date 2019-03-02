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

package me.tobiadeyinka.itunessearch.entities;

/**
 * Enumeration of all genres &amp; their id's.
 *
 * The id's mirror the official iTunes api documentation.
 * See <a href="https://affiliate.itunes.apple.com/resources/documentation/genre-mapping/">Genre IDs Appendix</a>
 *
 * Created by Tobi Adeyinka on 2017. 10. 15..
 */
public enum Genre {

    PODCASTS(26);

    private long id;

    Genre(long id){
        this.id = id;
    }

    /*
     * returns the genre's id
     */
    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        if (this == Genre.PODCASTS) {
            return "Podcasts";
        }

        return null;
    }

}
