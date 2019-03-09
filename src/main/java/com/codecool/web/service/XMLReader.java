package com.codecool.web.service;

import com.codecool.web.model.Tweet;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class XMLReader extends XMLParser {
    private ArrayList<Tweet> tweets;

    public XMLReader(String xmlPath) {
        tweets = new ArrayList<Tweet>();
        loadXmlDocument(xmlPath);
        Element rootNode = doc.getDocumentElement();
        List<Element> tweetNodes = getElements(rootNode);
        addTweet(tweetNodes);
    }

    public  void addTweet(List<Element> tweetNodes) {
        for (Element tweetNode : tweetNodes ) {
            List<Element> fieldNodes = getElements(tweetNode);
            int id = Integer.valueOf(getString(fieldNodes, "id"));
            String posterName = getString(fieldNodes, "posterName");
            String content = getString(fieldNodes, "content");
            String dateString = (getString(fieldNodes, "timestamp"));
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

    public String getString(List<Element> elements, String name) {
        for (Element element : elements) {
            if (element.getTagName().equals(name)) {
                return element.getTextContent();
            }
        }
        throw new IllegalStateException();
    }

    public List<Element> getElements(Element parentNode) {
        ArrayList<Element> elements = new ArrayList<Element>();
        for (int i = 0; i < parentNode.getChildNodes().getLength(); i++) {
            Node childnode = parentNode.getChildNodes().item(i);
            if (childnode instanceof Element) {
                elements.add((Element) childnode);
            }

        }
        return elements;
    }

    public ArrayList<Tweet> getTweets() {
        if (tweets.isEmpty()) {
            return null;
        }
        return tweets;
    }

}

/*         */
