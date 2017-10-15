package com.tobiadeyinka.itunessearch.podcasts.enums;

/**
 * Enumeration of podcast attributes that can be use to narrow down searches.
 *
 * See attribute parameter key in <a href="https://affiliate.itunes.apple.com/resources/documentation/itunes-store-web-service-search-api/#searching">
 *     Searching the iTunes Store</a> for more details.
 *
 * Created by Tobi Adeyinka on 2017. 10. 15..
 */
public enum PodcastAttribute {

    TITLE("titleTerm"),
    LANGUAGE("languageTerm"),
    AUTHOR("authorTerm"),
    GENRE("genreIndex"),
    ARTIST("artistTerm"),
    RATING("ratingIndex"),
    KEYWORDS("keywordsTerm"),
    DESCRIPTION("descriptionTerm");

    private String parameterValue;

    PodcastAttribute(String parameterValue){
        this.parameterValue = parameterValue;
    }

    /**
     *
     * @return the value to be passed in the api request
     */
    public String getParameterValue() {
        return parameterValue;
    }

}
