package br.com.superseniordevelopers.html5videotest.web.ajax;

import br.com.superseniordevelopers.html5videotest.web.dto.VideoDTO;
import br.com.superseniordevelopers.html5videotest.web.entity.VideoHTML5;
import br.com.superseniordevelopers.html5videotest.web.service.Factory;
import br.com.superseniordevelopers.html5videotest.web.service.VideoService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This servlet is used to translate the incoming message and also return URLs
 * to movies that represent the translation
 *
 * @author luan
 */
@WebServlet(name = "VideoAjaxServlet", urlPatterns = {"/VideoAjaxServlet"})
public class VideoAjaxServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP to provide the video links (URLs) or
     * error message, in case of an exception (both inside of HTML DIV elements)
     * to translate an requested message.
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        final PrintWriter out = response.getWriter();
        // Get the field to be translated from the request
        final String text = request.getParameter("text_textarea_id");

        try {
            // Get the service (used this just to loose couple, but would be better not use an static field VIDEO_SERVICE)
            VideoService vs = Factory.get(Factory.VIDEO_SERVICE);
            // Process the request, this way we can already look into the return. I put a "try catch" in case a RuntimeException occured
            VideoDTO vdto = vs.process(text);

            // In case that the process doesn't have error
            if (vdto.hasError() == false) {

                // Get the Video URLs
                List<VideoHTML5> urls = vdto.getVideoURLs();
                String traducao = text + "Hello World!";

                // Generate the output to the client (that is a lot of div with different proposes, like the one that holds the error, the one that hold the translation and the others that hold the video URLs
                out.write("<div id='server_error_id' value='' />");
                out.write("<div id='server_translation_id' value='" + traducao + "' />");
                int i = 0;
                for (Iterator<VideoHTML5> it = urls.iterator(); it.hasNext();) {
                    VideoHTML5 video = it.next();
                    out.write("<div id='server_video_" + i + "' valueOGG='" + video.getOgg() + "' valueMP4='" + video.getMp4() + "' />");
                    i++;
                }
            } else {
                // In case an expected error ocurred
                out.write("<div id='server_error_id' value='" + vdto.getErrorMessage() + "' />");
            }
        } catch (Exception e) {
            // In case a non-expected error ocurred
            out.write("<div id='server_error_id' value='Unexpected erro!' />");
        } finally {
            // In all the cases, it will close the PrintWriter
            out.close();
        }
    }

    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return this.getClass().getSimpleName() + ": A service of URLs to Videos";
    }
}
