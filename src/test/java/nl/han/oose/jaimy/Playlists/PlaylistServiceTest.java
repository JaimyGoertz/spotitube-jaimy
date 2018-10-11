package nl.han.oose.jaimy.Playlists;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class PlaylistServiceTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();
    private PlaylistService sut;

    @Before
    public void setUp() throws Exception {
        sut = new PlaylistServiceImpl();
    }

    @Test
    public void testGetTracksInPlaylist() {

    }
}