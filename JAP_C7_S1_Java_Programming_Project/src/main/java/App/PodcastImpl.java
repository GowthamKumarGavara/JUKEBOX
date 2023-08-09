package App;

import UserDefinedExceptions.PodcastNotFoundException;
import UserDefinedExceptions.SongNotFoundException;
import model.Podcast;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PodcastImpl{

    public PodcastImpl() {}

    public void displaypod(List<Podcast> list) {

        System.out.println("------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%2s %16s %28s %30s %25s", "PODCAST ID", "PODNAME", "CELEBRITYNAME ", "DATE", "URL");
        System.out.println();
        System.out.println("------------------------------------------------------------------------------------------------------------------");
        try {
            for (Podcast p : list) {
                System.out.printf("%2s %32s %25s %25s %25s", p.getPodid(), p.getPodname(), p.getCelebrity(), p.getDate(), p.getPodpath());
                System.out.println();
                if (list.isEmpty()) {
                    throw new PodcastNotFoundException("Podcast not found");
                }
            }
        }
        catch (PodcastNotFoundException e) {
            e.printStackTrace();
            }
            }


    public void displayPodCastList(List<Podcast> list) {
   try {
        displaypod(list);
        if (list.isEmpty()) {
         throw new PodcastNotFoundException("Podcast not found");
    }
    }
  catch (PodcastNotFoundException e) {
      e.printStackTrace();
   }
   }

    public List<Podcast> displaypodcastbypodname(List<Podcast> list, String podname) {
        List<Podcast> displaybypodname = new ArrayList<>();
     try {
         displaybypodname = list.stream().filter(p1 -> p1.getPodname().equalsIgnoreCase(podname)).collect(Collectors.toList());
         displaypod(displaybypodname);
         if (list.isEmpty()) {
             throw new PodcastNotFoundException("Podcast not found");
         }
     }
         catch (PodcastNotFoundException e) {
             e.printStackTrace();
         }
        return displaybypodname;
    }



    public List<Podcast> displaypodcastbycelebrityName(List<Podcast> list, String celebrity) {
        List<Podcast> displaybycelebrity = new ArrayList<>();
    try {
        displaybycelebrity = list.stream().filter(p1 -> p1.getCelebrity().equalsIgnoreCase(celebrity)).collect(Collectors.toList());
        displaypod(displaybycelebrity);
        if (list.isEmpty()) {
            throw new PodcastNotFoundException("Podcast not found");
        }
    }
    catch (PodcastNotFoundException e) {
        e.printStackTrace();
    }
     return displaybycelebrity;
    }

    public void displaypodcastbydate(List<Podcast> list, Date date) {
        List<Podcast> displaybydate = new ArrayList<>();
        try {
            displaybydate = list.stream().filter(i -> i.getDate().equals(date)).collect(Collectors.toList());
            displaypod(displaybydate);
            if (list.isEmpty()) {
                throw new PodcastNotFoundException("Podcast not found");
            }
        }
        catch (PodcastNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void displaypodcastbypodId(List<Podcast> list, int podid) {
        List<Podcast> displaybypodid = new ArrayList<>();
    try {
        displaybypodid = list.stream().filter(p1 -> p1.getPodid() == podid).collect(Collectors.toList());
        displaypod(displaybypodid);
        if (list.isEmpty()) {
            throw new PodcastNotFoundException("Podcast not found");
        }
    }
    catch (PodcastNotFoundException e) {
        e.printStackTrace();
    }

    }
}
