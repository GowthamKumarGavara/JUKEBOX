package model;

import java.sql.Date;

public class Podcast {
    int podid;
    String podname;
    String celebrity;
    Date date;
    String podpath;

    public Podcast() {}

    public Podcast(int podid, String podname, String celebrity, Date date, String podpath) {
        this.podid = podid;
        this.podname = podname;
        this.celebrity = celebrity;
        this.date = date;
        this.podpath = podpath;
    }

    public int getPodid() {
        return podid;
    }

    public void setPodid(int podid) {
        this.podid = podid;
    }

    public String getPodname() {
        return podname;
    }

    public void setPodname(String podname) {
        this.podname = podname;
    }

    public String getCelebrity() {
        return celebrity;
    }

    public void setCelebrity(String celebrity) {
        this.celebrity = celebrity;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPodpath() {
        return podpath;
    }

    public void setPodpath(String podpath) {
        this.podpath = podpath;
    }

    @Override
    public String toString() {
        return "podcast{" +
                "podid=" + podid +
                ", podname='" + podname + '\'' +
                ", celebrity='" + celebrity + '\'' +
                ", date='" + date + '\'' +
                ", podpath='" + podpath + '\'' +
                '}';
    }
}
