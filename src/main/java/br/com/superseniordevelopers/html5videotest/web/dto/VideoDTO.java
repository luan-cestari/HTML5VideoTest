package br.com.superseniordevelopers.html5videotest.web.dto;

import br.com.superseniordevelopers.html5videotest.web.entity.VideoHTML5;
import java.util.List;

/**
 * This interface will represent a set of method of to interact of it holding
 * data. This way the service could be stateless and (will) have cache
 *
 * @author luan
 */
public interface VideoDTO {

    /**
     * Return true if there was any error during the process the request or the
     * filling of this VideoDTO
     *
     * @return
     */
    boolean hasError();

    /**
     * Return the message of the error to be displayed to the client
     *
     * @return
     */
    String getErrorMessage();
    
    /*
     * Get the list of URLs encapisulated in the VideoHTML5(due it can offer
     * more than one type of video, like OGG and MP4)
     */
    List<VideoHTML5> getVideoURLs();
}
