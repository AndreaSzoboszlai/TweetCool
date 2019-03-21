package com.codecool.web.listener;

import com.codecool.web.model.Tweet;
import com.codecool.web.service.*;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;


@WebListener
public final class WebappContextListener implements ServletContextListener {
    private XMLReader reader = new XMLReader();

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        String homeDir = System.getenv("CATALINA_HOME");
        String directory = homeDir + "/webapps";

        if (Utils.doesFileExit(directory + "/")) {
            reader.readXML(directory + "/Tweets.xml");
            for (Tweet element : reader.getReadTweets()) {
                TweetListSingleton.getInstance().addToTweets(element);
            }
        }
        System.out.println("This method is invoked once when the webapp gets deployed.");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        Utils.writeTweets(TweetListSingleton.getInstance().getTweets());
        System.out.println("This method is invoked once when the webapp gets undeployed.");
    }
}
