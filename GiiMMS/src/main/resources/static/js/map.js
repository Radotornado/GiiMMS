function initMap() {
    var text = document.getElementById("map");
    var coord  = text.textContent.split(", ");
    const pos = { lat: parseFloat(coord[0]), lng: parseFloat(coord[1]) };
    const map = new google.maps.Map(document.getElementById("map"), {
        zoom: 17,
        center: pos,
    });
    const marker = new google.maps.Marker({
        position: pos,
        map: map,
    });
}

var startDateTime = new Date(2014,0,1,23,59,59,0); // YYYY (M-1) D H m s ms (start time and date from DB)
var startStamp = startDateTime.getTime();

var newDate = new Date();
var newStamp = newDate.getTime();

var timer; // for storing the interval (to stop or pause later if needed)

function updateClock() {
    newDate = new Date();
    newStamp = newDate.getTime();
    var diff = Math.round((newStamp-startStamp)/1000);

    var d = Math.floor(diff/(24*60*60));
    diff = diff-(d*24*60*60);
    var h = Math.floor(diff/(60*60));
    diff = diff-(h*60*60);
    var m = Math.floor(diff/(60));
    diff = diff-(m*60);
    var s = diff;

    document.getElementById("timeElapsed").innerHTML = h+" hour(s), "+m+" minute(s), "+s+" second(s)";
}

timer = setInterval(updateClock, 1000);
