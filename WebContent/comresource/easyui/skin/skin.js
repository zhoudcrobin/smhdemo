﻿$(function() {
	$('.styleswitch').click(function(){
		switchStylestyle(this.getAttribute("rel"));
		return false;
	});
	var c = readCookie('smhdemostyle');
	if (c) switchStylestyle(c);
});

function switchStylestyle(styleName){
	$('link[rel=stylesheet][title]').each(function(i){
		this.disabled = true;
		if (this.getAttribute('title') == styleName) this.disabled = false;
	});
	
	$("iframe").contents().find('link[rel=stylesheet][title]').each(function(i){
		this.disabled = true;
		if (this.getAttribute('title') == styleName) this.disabled = false;
	});
	
	$("iframe").contents().find('iframe').contents().find('link[rel=stylesheet][title]').each(function(i){
		this.disabled = true;
		if (this.getAttribute('title') == styleName) this.disabled = false;
	});
	
	$("iframe").contents().find('iframe').contents().find('iframe').contents().find('link[rel=stylesheet][title]').each(function(i){
		this.disabled = true;
		if (this.getAttribute('title') == styleName) this.disabled = false;
	});
	$("iframe").contents().find("iframe").contents().find('iframe').contents().find('iframe').contents().find('link[rel=stylesheet][title]').each(function(i){
		this.disabled = true;
		if (this.getAttribute('title') == styleName) this.disabled = false;
	});	
	createCookie('smhdemostyle', styleName, 365);
}
//cookie
function createCookie(name,value,days){
	if (days){
		var date = new Date();
		date.setTime(date.getTime()+(days*24*60*60*1000));
		var expires = "; expires="+date.toGMTString();
	}
	else var expires = "";
	document.cookie = name+"="+value+expires+"; path=/";
}
function readCookie(name){
	var nameEQ = name + "=";
	var ca = document.cookie.split(';');
	for(var i=0;i < ca.length;i++){
		var c = ca[i];
		while (c.charAt(0)==' ') c = c.substring(1,c.length);
		if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length,c.length);
	}
	return null;
}
function eraseCookie(name){
	createCookie(name,"",-1);
}
// /cookie functions