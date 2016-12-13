(function($) {
	var ROOTPATH = function(){  
	    var fullPath=window.document.location.href;
	    var pathName=window.document.location.pathname;
	    var pos=fullPath.indexOf(pathName);
	    var hostPath=fullPath.substring(0,pos);
	    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1)+'/';
	    return(hostPath+projectName);
	};
	
	cmdMap = {
            LOGIN: 'user/login'
	};
	
	var _ajax = $.ajax;
	$.ajax = function(opt) {
            var fn = {
                error : function(XMLHttpRequest, textStatus, errorThrown) {
                },
                success : function(data, textStatus) {
                }
            }
            if (opt.error) {
                fn.error = opt.error;
            }
            if (opt.success) {
                fn.success = opt.success;
            }

            var _opt = $.extend(opt, {
                error : function(XMLHttpRequest, textStatus, errorThrown) {
                    fn.error(XMLHttpRequest, textStatus, errorThrown);
                },
                success : function(data, textStatus) {
                    if(data!==null && data!==undefined && data!=="") {
                        if(data.cmd) {
                            //execute command here
                            switch (data.cmd) {
                            case 'CMD_LOGIN':
                                alert('jump~');
                                location.href = ROOTPATH() + cmdMap.LOGIN + "?callback="+data.callback + "&r=" + Math.random();
                                return;
                            default:
                                break;
                            }
                        } else {
                            fn.success(data, textStatus);
                        }
                    } else {
                        window.alert("no data response.");
                    }
                }
            });
            _ajax(_opt);
	};
})(jQuery);