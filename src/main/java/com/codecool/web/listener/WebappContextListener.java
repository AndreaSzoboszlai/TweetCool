package com.codecool.web.listener;

import com.codecool.web.model.Tweet;
import com.codecool.web.service.*;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;


@WebListener
public final class WebappContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        String homeDir = System.getenv("CATALINA_HOME");
        String directory = homeDir + "/webapps";

        if (Utils.doesFileExit(directory + "/")) {
            for (Tweet element : Utils.readXML(directory + "/Tweets.xml")) {
                TweetList.getInstance().addToTweets(element);
            }
        }
        System.out.println("This method is invoked once when the webapp gets deployed.");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        Utils.writeTweets(TweetList.getInstance().getTweets());
        System.out.println("This method is invoked once when the webapp gets undeployed.");
    }
}
