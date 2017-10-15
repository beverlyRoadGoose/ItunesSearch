package com.tobiadeyinka.itunessearch.search;

import com.tobiadeyinka.itunessearch.exceptions.MissingRequiredParameterException;

import org.testng.annotations.Test;

/**
 * Tests for all podcast search methods.
 *
 * Created by Tobi Adeyinka on 2017. 10. 15..
 */
public class PodcastsSearchTests {

    @Test(expectedExceptions = MissingRequiredParameterException.class)
    public void searchForPodcastWithoutSearchTerm() throws MissingRequiredParameterException {
        new PodcastsSearch().execute();
    }

}
