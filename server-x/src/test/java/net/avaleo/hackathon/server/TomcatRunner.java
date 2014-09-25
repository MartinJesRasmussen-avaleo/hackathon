package net.avaleo.hackathon.server;

import org.apache.catalina.startup.Tomcat;

import java.io.File;

/**
 * Created by ras on 25-09-14.
 */
public class TomcatRunner {



    public static void main(String[] args) throws Exception {
        final String webappDirLocation = "src/main/webapp/";
        final Tomcat tomcat = new Tomcat();

        tomcat.setPort(8080);

        tomcat.addWebapp("/server-x", new File(webappDirLocation).getAbsolutePath());
        System.out.println("configuring app with basedir: " + new File("./" + webappDirLocation).getAbsolutePath());

        tomcat.start();
        tomcat.getServer().await();

    }
}
