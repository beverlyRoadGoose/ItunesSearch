package com.tobiadeyinka.itunessearch.podcasts.enums;

/**
 * Enumeration of the possible entity return types from  podcast's search.
 *
 * See entity parameter key in <a href="https://affiliate.itunes.apple.com/resources/documentation/itunes-store-web-service-search-api/#searching">
 *     Searching the iTunes Store</a> for more details.
 *
 * Created by Tobi Adeyinka on 2017. 10. 15..
 */
public enum PodcastSearchReturnType {

    PODCAST("podcast"),
    PODCAST_AUTHOR("podcastAuthor");

    private String parameterKey;

    PodcastSearchReturnType(String parameterKey){
        this.parameterKey = parameterKey;
    }

    public String getParameterKey() {
        return parameterKey;
    }

}
