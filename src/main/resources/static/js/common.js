$.namespace("jsche.common")
//Basic handler of ajax.
jsche.common = function(){
    var page = 1;
    return {
        init: function() {
        },
        url: "",
        getUrl: function(uri){
            var result = uri;
            if(page != undefined){
                result += "page=" + page;
            }
            return result;
        }
        handleCallback: null,
        handleAjaxResult: function(data){
            jsche.common.handleCallback(data);
        },
        sendRequest: function(){
            $.ajax({
                type:'POST',
                url: jsche.common.getUrl(jsche.common.url),
                success: function(data) {
                    jsche.common.handleAjaxResult(data);
                },
                dataType: "json"
            })
        }
    }
}

$(document).ready(function(){
    jsche.common.init();
})