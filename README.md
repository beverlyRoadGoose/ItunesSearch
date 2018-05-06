[![Build Status](https://travis-ci.org/EtherealT/ItunesSearch.svg?branch=master&maxAge=1)](https://travis-ci.org/EtherealT/ItunesSearch)
[![Coverage Status](https://coveralls.io/repos/github/EtherealT/ItunesSearch/badge.svg?branch=master&maxAge=1)](https://coveralls.io/github/EtherealT/ItunesSearch?branch=master)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/6139acc8bc4c44dd91d73fa2456ec52d)](https://www.codacy.com/app/EtherealT/ItunesSearch?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=EtherealT/ItunesSearch&amp;utm_campaign=Badge_Grade)
[![GitHub version](https://badge.fury.io/gh/etherealt%2Fitunessearch.svg)](https://github.com/EtherealT/ItunesSearch/releases)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg?maxAge=1)](https://opensource.org/licenses/Apache-2.0)

## About
This is a Java wrapper for the [iTunes Search API](https://affiliate.itunes.apple.com/resources/documentation/itunes-store-web-service-search-api/).

## Usage
**Adding the library to your project**

**Gradle**
```Gradle
compile group: 'me.tobiadeyinka', name: 'iTunesSearch', version: '1.5.0'
```

**Maven**
```xml
<dependency>
    <groupId>me.tobiadeyinka</groupId>
    <artifactId>iTunesSearch</artifactId>
    <version>1.5.0</version>
</dependency>
```
      
**Making calls to the API**

Using Podcast's search as an example, to search for "Radiolab" and leaving all other parameters set to default, it's as simple as:
```java
new PodcastSearch()
    .with("radiolab")
    .execute();
```

To compare your search term with only a particular attribute, for example the genre of podcasts, you can specify that this way:
```java
new PodcastSearch()
    .with("radiolab")
    .inAttribute(PodcastAttribute.GENRE);
    .execute();
```

To run your search in only one itunes store, just pass the [ISO 3166-1 alpha-2 code](https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2) 
for that country, this example uses Nigeria:
```java
new PodcastSearch()
    .with("radiolab")
    .inCountry(CountryCode.NG)
    .execute();
```

Searching other media types follow the same method. To search every media type in one call, use [MediaSearch](https://etherealt.github.io/ItunesSearch/docs/):
```java
new MediaSearch()
    .with("something")
    .execute();
```
There are much more configurations available all of which are detailed in the [wiki](https://github.com/EtherealT/ItunesSearch/wiki).

To lookup up lists or items from the store, use the lookup API's. For example, to get the top songs in the store:
```java
MusicLookup.topSongs();
```

To get the top _n_ songs:
```java
MusicLookup.topSongs(n);
```

The javadoc is also available [here](https://etherealt.github.io/ItunesSearch/docs/).
All searches and lookups return a [JSON object](https://stleary.github.io/JSON-java/).

## Disclaimer
iTunes is a trademark of Apple Inc., registered in the U.S. and other countries.

This library has not been authorized, sponsored, or otherwise approved by Apple Inc.

## License

```
   Copyright 2018 Oluwatobi Adeyinka

   
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
