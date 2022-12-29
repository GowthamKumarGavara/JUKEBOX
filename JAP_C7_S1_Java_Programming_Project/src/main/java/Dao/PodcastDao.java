package Dao;

import model.Podcast;
import Connection.ConnectDataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PodcastDao {
    public List<Podcast> readAllpodcast() throws SQLException, ClassNotFoundException {

        List<Podcast> podcastList=new ArrayList<>();
        Connection con = ConnectDataBase.connectToSql();
        PreparedStatement ps=con.prepareStatement("select * from podcasts");
        ResultSet rs=  ps.executeQuery();
        while (rs.next()){
            int podcastId=rs.getInt(1);
            String podcastName=rs.getString(2);
            String celebrityName=rs.getString(3);
            Date publishDate=rs.getDate(4);
            String podcastUrl=rs.getString(5);
            Podcast podObj=new Podcast(podcastId,podcastName,celebrityName,publishDate,podcastUrl);
            podcastList.add(podObj);
        }
        rs.close();
        ps.close();
        return podcastList;
    }

}
