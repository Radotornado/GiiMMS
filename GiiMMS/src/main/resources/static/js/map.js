let map, infoWindow;

function initMap() {
    // get last known location from the employee object
    var text = document.getElementById("map");
    var coord  = text.textContent.split(", ");
    const pos = { lat: parseFloat(coord[0]), lng: parseFloat(coord[1]) };
    // set it as default (if unable to get current one)
    map = new google.maps.Map(document.getElementById("map"), {
        center: pos,
        zoom: 17,
    });
    infoWindow = new google.maps.InfoWindow();
    // Try HTML5 geolocation.
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(
            (position) => {
                const pos = {
                    lat: position.coords.latitude,
                    lng: position.coords.longitude,
                };
                infoWindow.setPosition(pos);
                infoWindow.setContent("Here you are.");
                infoWindow.open(map);
                map.setCenter(pos);
            },
            () => {
                handleLocationError(true, infoWindow, map.getCenter());
            }
        );
    } else {
        // Browser doesn't support Geolocation
        handleLocationError(false, infoWindow, map.getCenter());
    }

    updateLocationObject(pos);
}

function handleLocationError(browserHasGeolocation, infoWindow, pos) {
    infoWindow.setPosition(pos);
    infoWindow.setContent(
        browserHasGeolocation
            ? "Error: The Geolocation service failed."
            : "Error: Your browser doesn't support geolocation."
    );
    infoWindow.open(map);
}