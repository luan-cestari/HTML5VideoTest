package br.com.superseniordevelopers.html5videotest.web.service;

/**
 * Simple factory. I didn't want to make more complex and use EJB or CDI to DI
 *
 * @author luan
 */
public class Factory {
    //TODO -> Better use ENUM here
    public static final String VIDEO_SERVICE = "VideoService";

    /**
     * Get the specific service requested
     * @param <T>
     * @param service
     * @return 
     */
    public static <T extends Service> T get(String service) {
        if (VIDEO_SERVICE.equals(service)) {
            return (T) new VideoService();
        }
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
