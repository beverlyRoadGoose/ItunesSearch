package com.tobiadeyinka.itunessearch.podcasts;

import com.neovisionaries.i18n.CountryCode;

import com.tobiadeyinka.itunessearch.podcasts.enums.PodcastSearchReturnType;

/**
 * Podcasts search api endpoint.
 *
 * See <a href="https://affiliate.itunes.apple.com/resources/documentation/itunes-store-web-service-search-api/#searching">
 *     Searching the iTunes Store</a> for more details about the parameters.
 *
 * Created by Tobi Adeyinka on 2017. 10. 15..
 */
public class PodcastsSearch {

    /**
     *
     * The term to search for.
     */
    private String searchTerm;

    /**
     *  <a href="https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">ISO 3166-1 alpha-2</a> country code for the
     *  iTunes store to search. Default is US.
     */
    private CountryCode countryCode = CountryCode.US;

    /**
     * The type of results returned (Podcasts or PodcastArtists)
     */
    private PodcastSearchReturnType returnType;

    /**
     * {@link com.tobiadeyinka.itunessearch.podcasts.PodcastsSearchManager} to execute the search.
     */
    private PodcastsSearchManager searchManager;

    PodcastsSearch() {
        searchManager = new PodcastsSearchManager();
    }

    /**
     * Sets the term to search for in podcasts. Required.
     *
     * @param searchTerm the term to search for
     * @return the current instance of {@link PodcastsSearch}
     */
    public PodcastsSearch with(String searchTerm) {
        this.searchTerm = searchTerm;
        return this;
    }

    /**
     * Sets the iTunes store to search.
     *
     * @param countryCode <a href="https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">ISO 3166-1 alpha-2</a> country code
     *                    for the iTunes store to search. Default is US.
     * @return the current instance of {@link PodcastsSearch}
     */
    public PodcastsSearch inCountry(CountryCode countryCode) {
        this.countryCode = countryCode;
        return this;
    }

    /**
     * Sets the return type of the results (Podcasts or PodcastArtists)
     *
     * @param returnType the type of results you want returned
     * @return the current instance of {@link PodcastsSearch}
     */
    public PodcastsSearch andReturn(PodcastSearchReturnType returnType) {
        this.returnType = returnType;
        return this;
    }

}
