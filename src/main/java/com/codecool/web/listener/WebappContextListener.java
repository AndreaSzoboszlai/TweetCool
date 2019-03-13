package com.codecool.web.listener;

import com.codecool.web.model.Tweet;
import com.codecool.web.service.GetFileNames;
import com.codecool.web.service.TweetListSingleton;
import com.codecool.web.service.WriteToXml;
import com.codecool.web.service.XMLReader;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public final class WebappContextListener implements ServletContextListener {
    private XMLReader reader;
    private WriteToXml writer = new WriteToXml();

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        String homeDir = System.getenv("CATALINA_HOME");
        String directory = homeDir + "/webapps";

        if (GetFileNames.getFiles((directory + "/")) == true) {
            reader = new XMLReader(directory + "/Tweets.xml");

            for (Tweet element : reader.getTweets()) {
                TweetListSingleton.getInstance().addToTweets(element);
            }
        }
        System.out.println("This method is invoked once when the webapp gets deployed.");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        writer.writeTweets(TweetListSingleton.getInstance().getTweets());
        System.out.println("This method is invoked once when the webapp gets undeployed.");
    }
}
