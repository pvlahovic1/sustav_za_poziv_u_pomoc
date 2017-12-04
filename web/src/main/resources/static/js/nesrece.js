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
            refreshNesrece();
        },
        error : function(request,error)
        {
            console.log("Request: "+JSON.stringify(request));
        }
    });
}

function urediPodatke(id) {
    $.ajax({
        url : "/nesrece/edit/" + id,
        type : 'GET',
        success : function(data) {
            $("#nesreca-modal-content").html(data);
        },
        error : function(request,error)
        {
            console.log("Request: "+JSON.stringify(request));
        }
    });
}

function pohraniUredenuNesrecu(id) {
    $.ajax({
        url : "/nesrece/update",
        type : 'POST',
        data : JSON.stringify(getFormData()),
        contentType: "application/json; charset=UTF-8",
        success : function(data) {
            $('#poziviModal').modal('hide');
        },
        error : function(request,error)
        {
            console.log("Request: "+JSON.stringify(request));
        }
    });
}

function getFormData() {
    var data = {};
    var odabraniTipoviNesrece = [];
    $("input:checked").each(function() {
        odabraniTipoviNesrece.push(parseInt($(this).val()));
    });
    data['opisiNesrece'] = odabraniTipoviNesrece;
    data['idNesrece'] = $("#idPoziva").val();

    return data;
}

function refreshNesrece() {
    $.ajax({
        url : "/nesrece/table",
        type : 'GET',
        success : function(data) {
            $("#tableWrapper").html(data);
            $("#nesreceTable").dataTable();
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