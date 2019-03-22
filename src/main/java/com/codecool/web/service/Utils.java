package com.codecool.web.service;

import com.codecool.web.model.Tweet;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Utils {

    public static boolean doesFileExit(String filePath) {
        File folder = new File(filePath);
        File[] listOfFiles = folder.listFiles((dir, name) -> name.endsWith(".xml"));

        for (File file : listOfFiles) {
            if (file.getName().equals("Tweets.xml")) {
                return true;
            }
        }
        return false;
    }

    public static void writeTweets(List<Tweet> tweets) {
        DocumentBuilder docBuilder = null;
        Document doc = null;
        Element rootElement = null;
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            docBuilder = docFactory.newDocumentBuilder();

            // root elements
            doc = docBuilder.newDocument();
            rootElement = doc.createElement("Tweets");

            doc.appendChild(rootElement);
        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        }
        for (int i = 0; i < tweets.size(); i++) {
            try {
                // Teacher elements
                Element staff = doc.createElement("Tweet");

                rootElement.appendChild(staff);


                //firstname elements
                Element firstName = doc.createElement("id");
                firstName.appendChild(doc.createTextNode(String.valueOf(tweets.get(i).getId())));
                staff.appendChild(firstName);

                // lastName elements
                Element lastName = doc.createElement("posterName");
                lastName.appendChild(doc.createTextNode(tweets.get(i).getPosterName()));
                staff.appendChild(lastName);

                // yearOfBirth elements
                Element yearOfBirth = doc.createElement("content");
                yearOfBirth.appendChild(doc.createTextNode(tweets.get(i).getContent()));
                staff.appendChild(yearOfBirth);

                // gender elements
                Element gender = doc.createElement("timestamp");
                gender.appendChild(doc.createTextNode(String.valueOf(tweets.get(i).getTimestamp())));
                staff.appendChild(gender);


                // write the content into xml file
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                DOMSource source = new DOMSource(doc);
                String homeDir = System.getenv("CATALINA_HOME");
                String directory = homeDir + "/webapps";
                StreamResult result = result = new StreamResult(new File(directory + "/Tweets.xml"));

                transformer.transform(source, result);

            } catch (TransformerException tfe) {
                tfe.printStackTrace();
            }
        }
    }

    public static String getString(List<Element> elements, String name) {
        for (Element element : elements) {
            if (element.getTagName().equals(name)) {
                return element.getTextContent();
            }
        }
        throw new IllegalStateException();
    }

    public static List<Element> getElements(Element parentNode) {
        ArrayList<Element> elements = new ArrayList<Element>();
        for (int i = 0; i < parentNode.getChildNodes().getLength(); i++) {
            Node childnode = parentNode.getChildNodes().item(i);
            if (childnode instanceof Element) {
                elements.add((Element) childnode);
            }

        }
        return elements;
    }

    public static Document loadXmlDocument(String xmlPath) {
        Document doc = null;
        try {

            File fXmlFile = new File(xmlPath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();
            return doc;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return doc;
    }

    public static List<Tweet> readXML(String xmlPath) {
        List<Tweet> tweets = new ArrayList<>();
        Document doc = loadXmlDocument(xmlPath);
        Element rootNode = doc.getDocumentElement();
        List<Element> tweetNodes = Utils.getElements(rootNode);
        addTweet(tweetNodes, tweets);
        return tweets;
    }

    private static void addTweet(List<Element> tweetNodes, List<Tweet> targetTweets) {
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
                targetTweets.add(tweet);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

}
