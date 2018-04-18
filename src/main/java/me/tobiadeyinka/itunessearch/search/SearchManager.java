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

import me.tobiadeyinka.itunessearch.networking.NetworkUtils;

import java.net.URL;

import org.json.JSONObject;

/**
 * All searches are triggered from here.
 *
 * Created by Tobi Adeyinka on 2017. 10. 15..
 */
class SearchManager {

    public JSONObject executeSearch(URL url) {
        return NetworkUtils.executeQuery(url);
    }

}
