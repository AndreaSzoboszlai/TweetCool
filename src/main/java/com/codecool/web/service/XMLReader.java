package com.codecool.web.service;

import com.codecool.web.model.Tweet;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class XMLReader{
    private ArrayList<Tweet> tweets;
    Document doc = null;

    public void loadXmlDocument(String xmlPath) {

        try {

            File fXmlFile = new File(xmlPath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void readXML(String xmlPath) {

        tweets = new ArrayList<Tweet>();
        loadXmlDocument(xmlPath);
        Element rootNode = doc.getDocumentElement();
        List<Element> tweetNodes = Utils.getElements(rootNode);
        addTweet(tweetNodes);
    }

    public  void addTweet(List<Element> tweetNodes) {
        for (Element tweetNode : tweetNodes ) {
            List<Element> fieldNodes = Utils.getElements(tweetNode);
            int id = Integer.valueOf(Utils.getString(fieldNodes, "id"));
            String posterName = Utils.getString(fieldNodes, "posterName");
            String content = Utils.getString(fieldNodes, "content");
            String dateString = (Utils.getString(fieldNodes, "timestamp"));
            SimpleDateFormat df = new SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy", Locale.ENGLISH);
            Date date = null;
            try {
                date = (Date) df.parse(dateString);
                Tweet tweet = new Tweet(id, posterName, content, date);
                tweets.add(tweet);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }


    public ArrayList<Tweet> getReadTweets() {
        if (tweets.isEmpty()) {
            return null;
        }
        return tweets;
    }

}
