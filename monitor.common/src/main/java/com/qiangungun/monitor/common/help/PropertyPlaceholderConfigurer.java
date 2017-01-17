package com.qiangungun.monitor.common.help;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by chengkaijie on 2017/1/14.
 */
public class PropertyPlaceholderConfigurer
        extends org.springframework.beans.factory.config.PropertyPlaceholderConfigurer implements InitializingBean {

    public static final String USER_HOME = System.getProperty("user.home");
    public static final String WEB_RES_CLASS = "org.springframework.web.context.support.ServletContextResource";

    private static final String DUBBO_CONFIG = "dubbo.properties";

    @Override
    public void setLocation(Resource location) {
        setLocations(new Resource[]{location});
    }

    @Override
    public void setLocations(Resource[] locations) {
        List<Resource> list = new ArrayList<Resource>(locations.length);

        for (Resource location : locations) {
            if (location instanceof FileSystemResource) {
                list.add(fromFile(location));
            } else if (location instanceof UrlResource) {
                list.add(fromUrl(location));
            } else if (isWebResource(location)) {
                final Resource newLoc = fromUrl(location);
                list.add((newLoc == location ? fromFile(location) : newLoc));
            } else {
                list.add(location);
            }
        }

        super.setLocations(list.toArray(new Resource[list.size()]));
    }

    private static Resource fromFile(Resource location) {
        try {
            final File file = location.getFile();
            String path = file.getPath();

            if (needFilter(path)) {
                path = filter(path);

                copyToSystem(path);
                return new FileSystemResource(path);
            } else {
                return location;
            }
        } catch (IOException e) {
            return location;
        }
    }

    private static Resource fromUrl(Resource location) {
        try {
            final URL url = location.getURL();
            String path = url.getPath();

            if (needFilter(path) && url.getProtocol().equals("file")) {
                path = filter(path);

                copyToSystem(path);
                return new FileSystemResource(path);
            } else {
                return location;
            }
        } catch (IOException e) {
            return location;
        }
    }

    private static boolean needFilter(String path) {
        if (path == null || path.isEmpty()) {
            return false;
        }

        if (path.startsWith("~") || path.startsWith("/~")) {
            return true;
        }

        return false;
    }

    private static String filter(String file) {
        if (file.startsWith("~")) {
            file = USER_HOME + file.substring(1);
        } else if (file.startsWith("/~")) {
            file = USER_HOME + file.substring(2);
        }

        return file;
    }

    private static boolean isWebResource(Resource location) {
        if (location == null) {
            return false;
        } else if (WEB_RES_CLASS.equals(location.getClass().getName())) {
            return true;
        }

        return false;
    }

    private static void copyToSystem(String path) {
        if (path == null || path.toLowerCase().equals(DUBBO_CONFIG)) {
            return;
        }

        InputStream in = null;
        try {
            Properties p = new Properties();

            in = new BufferedInputStream(new FileInputStream(new File(path)));
            p.load(in);

            System.getProperties().putAll(p);
        } catch (Exception e) {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e1) {
                ;
            }
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Properties properties = mergeProperties();
        String env = properties.getProperty("env");

        if (StringUtils.isBlank(env)) {
            //throw new IllegalArgumentException("env is blank");
        }
    }
}
