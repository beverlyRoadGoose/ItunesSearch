package com.tobiadeyinka.itunessearch.search;

import com.tobiadeyinka.itunessearch.podcasts.enums.PodcastAttribute;
import com.tobiadeyinka.itunessearch.podcasts.enums.PodcastSearchReturnType;
import org.json.JSONObject;

/**
 * Podcasts search api endpoint.
 *
 * See <a href="https://affiliate.itunes.apple.com/resources/documentation/itunes-store-web-service-search-api/#searching">
 *     Searching the iTunes Store</a> for more details about the parameters.
 *
 * Created by Tobi Adeyinka on 2017. 10. 15..
 */
public class PodcastsSearch extends MediaSearch {

    /**
     * The podcast attribute the search term is compared with. Default is all attributes.
     */
    private PodcastAttribute attribute;

    /**
     * The type of results returned (Podcasts or PodcastArtists)
     */
    private PodcastSearchReturnType returnType;

    /**
     * Sets the podcast attribute the search term is compared with. Default is all attributes.
     *
     * @param attribute the podcast attribute the {@link #searchTerm} is compared with.
     * @return the current instance of {@link PodcastsSearch}
     */
    public PodcastsSearch inAttribute(PodcastAttribute attribute) {
        this.attribute = attribute;
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

    /**
     * execute the search
     *
     * @return A {@link org.json.JSONObject} object containing the results.
     */
    @Override
    public JSONObject execute() {
        PodcastsSearchManager searchManager;

        return null;
    }
}
