package br.com.superseniordevelopers.html5videotest.web.entity;

/**
 * This bean hold the sources of the Video. It can only readonly the field of
 * this class
 *
 * @author luan
 */
public class VideoHTML5 {

    private String ogg;
    private String mp4;

    public VideoHTML5(String ogg, String mp4) {
        this.ogg = ogg;
        this.mp4 = mp4;
    }

    public String getMp4() {
        return mp4;
    }

    public String getOgg() {
        return ogg;
    }
}
