package com.tobiadeyinka.itunessearch.podcasts.entities;

import com.tobiadeyinka.itunessearch.common.enums.Genre;

import java.net.URL;
import java.util.List;

/**
 * This class defines a podcast entity.
 *
 * Created by Tobi Adeyinka on 2017. 10. 15..
 */
public class Podcast {

    /**
     * podcast id (collectionId)
     */
    private final long id;

    /**
     * podcast name (collectionName)
     */
    private final String name;

    /**
     * podcast artist id (artistId)
     */
    private final String artistId;

    /**
     * podcast artist name (artistName)
     */
    private final String artistName;

    /**
     * podcast feed url (feedUrl)
     */
    private final URL feedUrl;

    /**
     * no. of podcast episodes (trackCount)
     */
    private final int trackCount;

    /**
     * list of the podcast's genre's (genreID's &amp; genres)
     */
    private List<Genre> genres;

    /**
     * link to the podcast's iTunes page (collectionUrl)
     */
    private URL itunesUrl;

    /**
     * link to the podcast creator's iTunes page (artistUrl)
     */
    private URL artistItunesUrl;

    /**
     * link to small poster image for podcast (100x100) (artworkUrl100)
     */
    private final URL smallPosterUrl;

    /**
     * link to big poster image for podcast (600x600) (artworkUrl600)
     */
    private final URL bigPosterUrl;

    /**
     * @param id The podcast's id (collectionId)
     * @param name The name of the podcast (collectionName)
     * @param artistId The podcast creator's id (artistId)
     * @param artistName The podcast creator's name (artistName)
     * @param feedUrl The podcast's feed url (feedUrl)
     * @param trackCount no. of podcast episodes (trackCount)
     * @param genres list of the podcast's genre's (genreID's &amp; genres)
     * @param itunesUrl link to the podcast's iTunes page (collectionUrl)
     * @param artistItunesUrl link to the podcast creator's iTunes page (artistUrl)
     * @param smallPosterUrl link to small poster image for podcast (100x100) (artworkUrl100)
     * @param bigPosterUrl link to big poster image for podcast (600x600) (artworkUrl600)
     */
    public Podcast(long id,
                   String name,
                   String artistId,
                   String artistName,
                   URL feedUrl,
                   int trackCount,
                   List<Genre> genres,
                   URL itunesUrl,
                   URL artistItunesUrl,
                   URL smallPosterUrl,
                   URL bigPosterUrl) {

        this.id = id;
        this.name = name;
        this.artistId = artistId;
        this.artistName = artistName;
        this.feedUrl = feedUrl;
        this.trackCount = trackCount;
        this.genres = genres;
        this.itunesUrl = itunesUrl;
        this.artistItunesUrl = artistItunesUrl;
        this.smallPosterUrl = smallPosterUrl;
        this.bigPosterUrl = bigPosterUrl;
    }

    /**
     *
     * @return The podcast's id.
     */
    public long getId() {
        return id;
    }

    /**
     *
     * @return The name of the podcast.
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return The iTunes id of the podcast's creator.
     */
    public String getArtistId() {
        return artistId;
    }

    /**
     *
     * @return The podcast's creator's name.
     */
    public String getArtistName() {
        return artistName;
    }

    /**
     *
     * @return The feed url for the podcast.
     */
    public URL getFeedUrl() {
        return feedUrl;
    }

    /**
     *
     * @return The number of episodes in the podcast.
     */
    public int getTrackCount() {
        return trackCount;
    }

    /**
     *
     * @return A list of the podcast's genre's
     */
    public List<Genre> getGenres() {
        return genres;
    }

    /**
     *
     * @return link to the podcast's itunes page.
     */
    public URL getItunesUrl() {
        return itunesUrl;
    }

    /**
     *
     * @return link to the podcast creator's page.
     */
    public URL getArtistItunesUrl() {
        return artistItunesUrl;
    }

    /**
     *
     * @return link to small poster image for podcast
     */
    public URL getSmallPosterUrl() {
        return smallPosterUrl;
    }

    /**
     *
     * @return link to big poster image for podcast
     */
    public URL getBigPosterUrl() {
        return bigPosterUrl;
    }

}
