<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>HTML5 Video Tag</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
        <script src="<%=request.getContextPath()%>/js/index.js"></script>
    </head>
    <body>
        <form id="form_id" >
            Text: <textarea id="text_textarea_id" name="text_textarea_id"></textarea>
            Translation: <textarea id="text_translation_textarea_id" readonly="readonly"></textarea>
            <input id="submit_button_id" type="submit" value="Go!"/>
            <input id="replay_button_id" type="submit" value="Replay!"/>
            <input id="error_div_id" type="text" />
            <div id="video_div_id"></div>
            <div id="result_div_id"></div>
        </form>
    </body>
</html>