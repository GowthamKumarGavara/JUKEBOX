package App;

import model.Audio;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class PlayMusic {
    Long currentFrame;
    Clip clip;   // Clip interface

    String status;// current status of clip
    AudioInputStream audioInputStream;  //creating object for Audio input stream

    int index=0;
    List<Audio> audioList;

    public PlayMusic() throws UnsupportedAudioFileException, IOException, LineUnavailableException
    {
        // create clip reference
        clip = AudioSystem.getClip();

    }

    public void playerOperation(List<Audio> audioDataList) {
        try
        {
            audioList=audioDataList;

            audioInputStream = AudioSystem.getAudioInputStream(new File(audioList.get(index).getAudioUrl()).getAbsoluteFile());

            clip = AudioSystem.getClip();

            clip.open(audioInputStream);

            System.out.println("The Audio is now playing: "+audioList.get(index).getAudioName());

            clip.loop(Clip.LOOP_CONTINUOUSLY);

            play();
            Scanner sc = new Scanner(System.in);
            while (true)
            {
                System.out.println("1. Pause");
                System.out.println("2. Resume");
                System.out.println("3. Restart");
                System.out.println("4. Next");
                System.out.println("5. Previous");
                System.out.println("6. Exit");
                System.out.println("enter your choice");
                int c = sc.nextInt();
                gotoChoice(c);
                if (c == 6) {
                    clip.close();
                    break;
                }
            }
        }
        catch (Exception ex)
        {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }


    private void gotoChoice(int c) {
        switch (c)
        {
            case 1:
                pause();
                break;
            case 2:
                resumeAudio();
                break;
            case 3:
                restart();
                break;
            case 4:
                next();
                break;
            case 5:
                previous();
                break;
            case 6:break;
        }
    }


    public void play() {
        clip.start();

        status = "play";
    }


    public void pause() {
        if (status.equals("paused"))
        {
            System.out.println("audio is already paused");
        }
        this.currentFrame = this.clip.getMicrosecondPosition();
        clip.stop();
        status = "paused";
    }


    public void resumeAudio() {
        if (status.equals("play"))
        {
            System.out.println("Audio is already "+ "being played");
        }
        clip.setMicrosecondPosition(currentFrame);
        this.play();
    }


    public void restart() {
        clip.stop();
        currentFrame = 0L;
        clip.setMicrosecondPosition(currentFrame);
        this.play();
    }


    public void stop() {
        currentFrame = 0L;
        clip.stop();
        clip.close();
    }


    public void previous(){
        try {
            clip.stop();
            --index;
            audioInputStream = AudioSystem.getAudioInputStream(new File(audioList.get(index).getAudioUrl()).getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
            System.out.println("The Audio is now playing: "+audioList.get(index).getAudioName());
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            status="play";
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }


    public void next(){
        try {
            clip.stop();
            ++index;
            audioInputStream = AudioSystem.getAudioInputStream(new File(audioList.get(index).getAudioUrl()).getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
            System.out.println("The Audio is now playing: "+audioList.get(index).getAudioName());
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            status="play";
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

}
