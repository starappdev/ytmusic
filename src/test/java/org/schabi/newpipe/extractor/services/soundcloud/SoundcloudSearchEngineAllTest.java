package org.schabi.newpipe.extractor.services.soundcloud;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.schabi.newpipe.extractor.ServiceList.SoundCloud;

import java.util.EnumSet;

import org.junit.Before;
import org.junit.Test;
import org.schabi.newpipe.Downloader;
import org.schabi.newpipe.extractor.NewPipe;
import org.schabi.newpipe.extractor.search.SearchEngine;
import org.schabi.newpipe.extractor.search.SearchResult;

/**
 * Test for {@link SearchEngine}
 */
public class SoundcloudSearchEngineAllTest {
    private SearchResult result;

    @Before
    public void setUp() throws Exception {
        NewPipe.init(Downloader.getInstance());
        SearchEngine engine = SoundCloud.getService().getSearchEngine();

        // SoundCloud will suggest "lil uzi vert" instead of "lill uzi vert"
        // keep in mind that the suggestions can NOT change by country (the parameter "de")
        result = engine.search("lill uzi vert", 0, "de",
                EnumSet.of(SearchEngine.Filter.USER,
                        SearchEngine.Filter.STREAM)).getSearchResult();
    }

    @Test
    public void testResultList() {
        assertFalse(result.resultList.isEmpty());
    }

    @Test
    public void testResultErrors() {
        if (!result.errors.isEmpty()) for (Throwable error : result.errors) error.printStackTrace();
        assertTrue(result.errors == null || result.errors.isEmpty());
    }

    @Test
    public void testSuggestion() {
        //todo write a real test
        assertTrue(result.suggestion != null);
    }
}
