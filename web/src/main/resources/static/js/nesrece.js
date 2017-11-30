$(document).ready(function(){
    $('#nesreceTable').dataTable({
        "language": {
            "url": "https://cdn.datatables.net/plug-ins/1.10.16/i18n/Croatian.json"
        }
    });
});

function showDetails(id) {
    $('#poziviModal').modal('show');

    $.ajax({
        url : "/nesrece/" + id,
        type : 'GET',
        success : function(data) {
            $("#nesreca-modal-content").html(data);
            myMap($("#xKoordinata").val(), $("#yKoordinata").val());
        },
        error : function(request,error)
        {
            console.log("Request: "+JSON.stringify(request));
        }
    });
}

function oznaciKaoRijeseno(id) {
    $.ajax({
        url : "/nesrece/rijeseno/" + id,
        type : 'GET',
        success : function(data) {
            $('#poziviModal').modal('hide');
        },
        error : function(request,error)
        {
            console.log("Request: "+JSON.stringify(request));
        }
    });
}

function myMap(x, y) {
    var myLatLng = {lat: parseFloat(x), lng: parseFloat(y)};

    var map = new google.maps.Map(document.getElementById('map'), {
        zoom: 15,
        center: myLatLng
    });

    var contentString = "<div>"+'Pozivni broj: '+ $("#brojMobitela").text() +"</div>";

    var infowindow = new google.maps.InfoWindow({
        content: contentString
    });

    var marker = new google.maps.Marker({
        position: myLatLng,
        map: map,
        title: 'Poziv'
    });

    marker.addListener('click', function() {
        infowindow.open(map, marker);
    });

}