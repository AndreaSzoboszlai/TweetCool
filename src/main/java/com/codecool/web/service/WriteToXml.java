package com.codecool.web.service;

import com.codecool.web.model.Tweet;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class WriteToXml {

    public void writeTweets(List<Tweet> tweets) {
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

                // set attribute to staff element
                //Attr attr = doc.createAttribute("id");
                //attr.setValue("1");
                //staff.setAttributeNode(attr);
                //No need for attribute id for my XML file

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
                StreamResult result = result = new StreamResult(new File("/home/andrea/Dokumentumok/apache-tomcat-9.0.16/webapps/Tweets.xml"));

                // Output to console for testing
                // StreamResult result = new StreamResult(System.out);

                transformer.transform(source, result);

            } catch (TransformerException tfe) {
                tfe.printStackTrace();
            }
        }
    }
}


