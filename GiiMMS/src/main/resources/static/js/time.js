var timer;

var dateAsText = document.getElementById("timeElapsed").innerHTML;
//alert(dateAsText);
// TODO

function updateClock() {

    var startDateTime = new Date(2014, 0, 1, 23, 59, 59, 0);
    var startStamp = startDateTime.getTime();

    var newDate = new Date();
    var newStamp = newDate.getTime();


    newDate = new Date();
    newStamp = newDate.getTime();
    var diff = Math.round((newStamp - startStamp) / 1000);

    var d = Math.floor(diff / (24 * 60 * 60));
    diff = diff - (d * 24 * 60 * 60);
    var h = Math.floor(diff / (60 * 60));
    diff = diff - (h * 60 * 60);
    var m = Math.floor(diff / (60));
    diff = diff - (m * 60);
    var s = diff;

    document.getElementById("timeElapsed").innerHTML = h + " hour(s), " + m + " minute(s), " + s + " second(s)";
}

timer = setInterval(updateClock, 1000);
