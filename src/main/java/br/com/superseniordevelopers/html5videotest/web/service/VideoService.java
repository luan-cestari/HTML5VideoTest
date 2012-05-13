package br.com.superseniordevelopers.html5videotest.web.service;

import br.com.superseniordevelopers.html5videotest.web.dto.VideoDTO;
import br.com.superseniordevelopers.html5videotest.web.dto.VideoDTOMock;

/**
 * This class will a stateless service of VideoDTO
 *
 * @author luan
 */
public class VideoService implements Service {

    /**
     * Process the message and returns VideoDTO for that message
     *
     * @param message
     * @return
     */
    public VideoDTO process(String message) {
        // Mock
        return new VideoDTOMock();
    }
}
