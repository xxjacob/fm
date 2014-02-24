package com.ideax.fm360;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class GetSongInfoFromXiami {

    public static void main(String[] args) throws Exception {
      //*[@id="wrapper"]/div[2]/div[1]/div/div[2]/div[1]/div[1]/table/tbody[1]
        Document doc = Jsoup.connect("http://www.xiami.com/search?key=going+to+a+town+rufus+Wainwright").get();
        Elements newsHeadlines = doc.select(".track_list tbody");
        if (newsHeadlines.size()<= 0){
            System.out.println("no search result");
        }
        Element firstResult = newsHeadlines.get(0);
        Element artistA = firstResult.select(".song_artist a").get(0);
        String artistName = artistA.attr("title");
        String artistLink = artistA.attr("href");
        
        Element albumA = firstResult.select(".song_album a").get(0);
        String albumName = albumA.attr("title");
        String albumLink = albumA.attr("href");

        System.out.println(artistName);
        System.out.println(artistLink);
        System.out.println(albumName);
        System.out.println(albumLink);
        
        Document albumDoc = Jsoup.connect(albumLink).get();
        Elements els = albumDoc.select(".cdCover185");
        if (els.size()<= 0){
            System.out.println("no album info");
        }
        Element img = els.get(0);
        System.out.println(img.attr("href"));
    }

}
