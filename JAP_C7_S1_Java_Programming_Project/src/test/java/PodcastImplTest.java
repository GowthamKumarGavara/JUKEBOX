import App.PlaylistImpl;
import App.PodcastImpl;
import App.SongImpl;
import Dao.PodcastDao;
import model.Podcast;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PodcastImplTest {
    PodcastImpl podcastImpl = new PodcastImpl();
    PodcastDao pd = new PodcastDao();
   // PodcastImpl podcastImpl;
   List<Podcast> podlist = pd.readAllpodcast();

    public PodcastImplTest() throws SQLException, ClassNotFoundException {
    }

    @Test
    public void givenPodcastNameGetPodcastDetails() {
        assertEquals(101, podcastImpl.displaypodcastbypodname(podlist,"Being Strong").get(0).getPodid());
        assertEquals("lufi", podcastImpl.displaypodcastbypodname(podlist,"Newshour").get(0).getCelebrity());
        assertEquals(Date.valueOf("2000-01-01"), podcastImpl.displaypodcastbypodname(podlist,"Being Strong"));
    }

    @Test
    public void givenPodcastCelebrityGetPodcastDetails() {
        assertEquals(102, podcastImpl.displaypodcastbycelebrityName(podlist,"lufi").get(0).getPodid());
        assertEquals("Side Effects", podcastImpl.displaypodcastbycelebrityName(podlist,"zorozoro").get(0).getPodname());
        assertEquals(Date.valueOf("2005-05-05"), podcastImpl.displaypodcastbycelebrityName(podlist,"zorozoro").get(0).getDate());
    }
    }

