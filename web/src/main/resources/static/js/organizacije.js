$(document).ready(function(){
    $('#organizacije_table').dataTable({
        "language": {
            "url": "https://cdn.datatables.net/plug-ins/1.10.16/i18n/Croatian.json"
        }
    });
});

function showDetails(id) {
    $('#organizacijeModal').modal('show');

    $.ajax({
        url : "/organizacije/" + id,
        type : 'GET',
        success : function(data) {
            $("#bolnica-modal-content").html(data);
        },
        error : function(request,error)
        {
            console.log("Request: "+JSON.stringify(request));
        }
    });
}

function editDetails(id) {
    $.ajax({
        url : "/organizacije/edit/" + id,
        type : 'GET',
        success : function(data) {
            $("#bolnica-modal-content").html(data);
        },
        error : function(request,error)
        {
            console.log("Request: "+JSON.stringify(request));
        }
    });
}

function saveOrganizacija(id) {
    $.ajax({
        url : "/organizacije/update",
        type : 'POST',
        data : JSON.stringify(getFormData()),
        contentType: "application/json; charset=UTF-8",

        success : function(data) {
            $("#bolnica-modal-content").html(data);
        },
        error : function(request,error)
        {
            console.log("Request: "+JSON.stringify(request));
        }
    });
}

function getFormData() {
    var data = {};
    $("#organizacijaSaveForm").serializeArray().map(function(x){data[x.name] = x.value;});

    var odabraniTipoviOrganizacije = [];
    $("input:checked").each(function() {
        odabraniTipoviOrganizacije.push(parseInt($(this).val()));
    });
    data['odabraniTipoviOrganizacije'] = odabraniTipoviOrganizacije;
    data['tipoviOrganizacije'] = [];
    data['sviTipoviOrganizacija'] = [];

    return data;
}