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