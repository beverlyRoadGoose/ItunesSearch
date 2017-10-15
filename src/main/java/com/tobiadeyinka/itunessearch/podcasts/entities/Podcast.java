package com.tobiadeyinka.itunessearch.podcasts.entities;

import com.tobiadeyinka.itunessearch.common.entities.Genre;

import java.net.URL;
import java.util.List;

/**
 * Created by Tobi Adeyinka on 2017. 10. 15..
 *
 * This class defines a podcast entity.
 */
public class Podcast {

    /*
     * podcast id (collectionId)
     */
    private final long id;

    /*
     * podcast name (collectionName)
     */
    private final String name;

    /*
     * podcast artist id (artistId)
     */
    private final String artistId;

    /*
     * podcast artist name (artistName)
     */
    private final String artistName;

    /*
     * podcast feed url (feedUrl)
     */
    private final URL feedUrl;

    /*
     * no. of podcast episodes (trackCount)
     */
    private final int trackCount;

    /*
     * list of the podcast's genre's (genreID's & genres)
     */
    private List<Genre> genres;

    /**
     * @param id The podcast's id (collectionId)
     * @param name The name of the podcast (collectionName)
     * @param artistId The podcast creator's id (artistId)
     * @param artistName The podcast creator's name (artistName)
     * @param feedUrl The podcast's url (feedUrl)
     * @param trackCount no. of podcast episodes (trackCount)
     * @param genres list of the podcast's genre's (genreID's & genres)
     */
    public Podcast(long id, String name, String artistId, String artistName, URL feedUrl, int trackCount, List<Genre> genres) {
        this.id = id;
        this.name = name;
        this.artistId = artistId;
        this.artistName = artistName;
        this.feedUrl = feedUrl;
        this.trackCount = trackCount;
        this.genres = genres;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getArtistId() {
        return artistId;
    }

    public String getArtistName() {
        return artistName;
    }

    public URL getFeedUrl() {
        return feedUrl;
    }

    public int getTrackCount() {
        return trackCount;
    }

    public List<Genre> getGenres() {
        return genres;
    }

}
