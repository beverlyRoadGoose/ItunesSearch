package com.tobiadeyinka.itunessearch.common.entities;

/**
 * Enumeration of all genres &amp; their id's.
 *
 * The id's mirror the official iTunes api documentation.
 * See <a href="https://affiliate.itunes.apple.com/resources/documentation/genre-mapping/">Genre IDs Appendix</a>
 *
 * Created by Tobi Adeyinka on 2017. 10. 15..
 */
public enum Genre {

    Podcasts(26);

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
        switch (this) {
            case Podcasts: return "Podcasts";
            default: return null;
        }
    }

}
