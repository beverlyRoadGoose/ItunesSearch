package com.tobiadeyinka.itunessearch.common.enums;

/**
 * enumeration of the iTunes api versions
 * Created by Tobi Adeyinka on 2017. 10. 15..
 */
public enum ItunesApiVersion {
    ONE(1),
    TWO(2);

    int apiVersion;

    ItunesApiVersion(int apiVersion) {
        this.apiVersion = apiVersion;
    }

    /**
     *
     * @return the api version value
     */
    public int getApiVersion() {
        return apiVersion;
    }
}
