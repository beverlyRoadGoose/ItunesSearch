package com.tobiadeyinka.itunessearch.movies.enums;

/**
 * Created by Tobi Adeyinka on 2017. 10. 16..
 */
public enum MovieSearchReturnType {

    MOVIE("movie"),
    MOVIE_ARTIST("movieArtist");

    private String parameterValue;

    MovieSearchReturnType(String parameterValue){
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
