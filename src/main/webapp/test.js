function setCookie(cname, cvalue, exdays) {
  var d = new Date();
  //alert(cname + " cookiename and " + cvalue);
  d.setTime(d.getTime() + (exdays*24*60*60*1000));
  var expires = "expires="+ d.toUTCString();
  document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
}

function getCookie(cname) {
       // alert("ok1");
  var name = cname + "=";
  var decodedCookie = decodeURIComponent(document.cookie);
  var ca = decodedCookie.split(';');
  for(var i = 0; i <ca.length; i++) {
    var c = ca[i];
    while (c.charAt(0) == ' ') {
      c = c.substring(1);
    }
    if (c.indexOf(name) == 0) {
      return c.substring(name.length, c.length);
    }
  }
  return "";
}

function checkCookie() {
    //alert("check");
  var user = getCookie("name");

  if (user != "") {
    document.form1.name.value = user;
    return user;
    //alert("Welcome again " + user);
  } else {
    document.form1.name.value = "";
  }

}

