[![Build Status](https://travis-ci.org/EtherealT/ItunesSearch.svg?branch=master)](https://travis-ci.org/EtherealT/ItunesSearch)
[![Coverage Status](https://coveralls.io/repos/github/EtherealT/ItunesSearch/badge.svg?branch=master)](https://coveralls.io/github/EtherealT/ItunesSearch?branch=master)
[![GitHub version](https://badge.fury.io/gh/etherealt%2Fitunessearch.svg)](https://github.com/EtherealT/ItunesSearch/releases)
[![Jitpack](https://jitpack.io/v/EtherealT/ItunesSearch.svg)](https://jitpack.io/#EtherealT/ItunesSearch)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/6139acc8bc4c44dd91d73fa2456ec52d)](https://www.codacy.com/app/EtherealT/ItunesSearch?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=EtherealT/ItunesSearch&amp;utm_campaign=Badge_Grade)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg?maxAge=1)](https://opensource.org/licenses/Apache-2.0)

## About
This is a Java wrapper for the [iTunes search api](https://affiliate.itunes.apple.com/resources/documentation/itunes-store-web-service-search-api/). As it's still in active development, only Podcast and Music searches are ready for use. In time, all iTunes media types would be covered.

## Usage
**Adding the library to your project**

1. Add the JitPack repository to your build file
      ```Gradle
      allprojects {
        repositories {
            ...
            maven { url 'https://jitpack.io' }
        }
      }
      ```
      
2. Add the dependency
      ```Gradle
        dependencies {
            compile 'com.github.EtherealT:ItunesSearch:v0.0.3'
        }
      ```
      
**Making calls to the API**

Using Podcast's search as an example, to search for "Radiolab" and leaving all other parameters set to default, it's as simple as:
```java
new PodcastsSearch()
    .with("radiolab")
    .execute();
```

## License

```
   Copyright 2017 Oluwatobi Adeyinka

   
   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
```