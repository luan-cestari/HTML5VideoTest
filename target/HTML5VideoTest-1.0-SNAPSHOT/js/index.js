// TODO 
// - Use HTML5 Session Storage to store the data from Ajax
// - Change data to JSON
// - Cache the data
// - Put a nice CSS and jQuery effects

// * After the document is fully loaded, the init function is executed
$(document).ready(init);

// * Some global variables
// ** The Servlet that I'm using (remember to put the context path if necessary)
var url = "VideoAjaxServlet";
// ** I will use this to create an teplate of how my video tags should be (I fill it using replace)
var video_tag_template =    '<video width="320" height="240" id="current_video@ID" onended="javascript:nextVideo(@ID_PROX)" onloadeddata="javascript:loadNextVideoAfterThisLoad(@ID_PROX)" muted="muted">'+
                                '<source src="@SOURCE_OGG" type="video/ogg" />'+  // firefox linux
                                '<source src="@SOURCE_MP4" type="video/mp4" />'+ //chrome linux 
                            '</video>';
// ** A simple variable to control the number of Videos that I have for the current search
var numVideos = 0;


// * The functions
// ** This one is executed when the document is loaded. Basically, it will put some triggers and format the page
function init(){
    // Remove the component from view
    $('#error_div_id').hide();

    // Trigger the button to make an ajax request to the server
    $('#submit_button_id').click(function(){
        // jQuery function to make an ajax of POST type
        $.post(
            url, // The servlet
            $('#form_id').serialize(), // jQuery function to get all inputs from the form to send to the server (like an usually submit)
            function(responseText){
                // When the ajax complete, this method is executed
                
                // Cache for better performace
                var $error_div_id = $('#error_div_id');
                var $result_div_id = $('#result_div_id');
                var $video_div_id = $('#video_div_id');
                
                // Cleanning the loaded videos, the old result and hiding error so, if it was displayed in the last request, it be hide.
                $video_div_id.empty();
                $result_div_id.empty();
                $error_div_id.hide();

                // Put the reponse of the server into a div to hold and process it
                $result_div_id.html(responseText);

                // If the server send an error message, it will be displayed
                var $server_error_id = $('#server_error_id'); //child of $result_div_id
                var erro = $server_error_id.attr('value');
                $server_error_id.remove();
                if(erro != ''){
                    $error_div_id.show().val(erro);
                    return;
                }
                
                // Get the translation of the text send to the server 
                var $server_translation_id = $('#server_translation_id');
                var traducao = $server_translation_id.attr('value');
                $server_translation_id.remove()
                $('#text_translation_textarea_id').val(traducao);

                // Storage the number of videos
                var divs = $result_div_id.children('div');
                numVideos = divs.length;
                
                // Create from the template the video tags (using the URLs from the server)
                var $div_first = divs.eq(0);
                var valueOGG = $div_first.attr('valueOGG');
                var valueMP4 = $div_first.attr('valueMP4');
                var content =  video_tag_template.replace('@SOURCE_OGG',valueOGG).replace('@SOURCE_MP4',valueMP4).replace('@ID','1').replace('@ID_PROX','2').replace('@ID_PROX','2');
                $video_div_id.html(content)
                $('#current_video1').get(0).play();
            },
            'html'
        );
        return false;
    });

    // Trigger a function that will hide the last video and play the first on again 
    $('#replay_button_id').click(function(){
        $('#current_video'+numVideos).hide();
        $('#current_video1').show().get(0).play();
        return false;
    });

}

// ** A function that work in chain: after videoN ends, the videoN+1 play. With this, the videos playlist are playing just like a big video (without any merge).
function nextVideo(id){
    if(id <= numVideos){
        $('#current_video'+(id-1)).hide();
        $('#current_video'+id).show().get(0).play();
    }
}

// ** This other function that work in chain too: after videoN ends to load, the videoN+1 start to load. This way, the video will otimize the loading.
function loadNextVideoAfterThisLoad(id){
    if(id <= numVideos){
        var $div_nth_child = $('#result_div_id > div:nth-child('+id+')'); // CSS3 nth-child 
        var valueOGG = $div_nth_child.attr('valueOGG');
        var valueMP4 = $div_nth_child.attr('valueMP4');
        var content =  video_tag_template.replace('@SOURCE_OGG',valueOGG).replace('@SOURCE_MP4',valueMP4).replace('@ID',id).replace('@ID_PROX',id+1).replace('@ID_PROX',id+1);
        $('#video_div_id').append(content)
        $('#current_video'+id).hide();
    }
}