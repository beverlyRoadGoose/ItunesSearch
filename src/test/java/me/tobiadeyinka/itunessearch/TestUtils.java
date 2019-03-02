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

package me.tobiadeyinka.itunessearch;

import java.util.logging.Logger;

/**
 * Common test methods.
 *
 * Created by Tobi Adeyinka on 2017. 10. 27..
 */
public abstract class TestUtils {

    public static void sleepForAMinute() {
        Logger logger = Logger.getLogger(TestUtils.class.getName());
        logger.info("sleeping for 1 minute to avoid access denial from itunes api");

        try { Thread.sleep(60000); }
        catch (InterruptedException e) { e.printStackTrace(); }
    }

}
