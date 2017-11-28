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
        data : JSON.stringify(getFormData("organizacijaSaveForm")),
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

function getFormData(formName) {
    var data = {};
    $("#" + formName).serializeArray().map(function(x){data[x.name] = x.value;});

    var odabraniTipoviOrganizacije = [];
    $("input:checked").each(function() {
        odabraniTipoviOrganizacije.push(parseInt($(this).val()));
    });
    data['odabraniTipoviOrganizacije'] = odabraniTipoviOrganizacije;
    data['tipoviOrganizacije'] = [];
    data['sviTipoviOrganizacija'] = [];

    return data;
}

function deleteOrganizacija(id) {
    var result = confirm("Sigurno želite obrisati organizaciju?");
    if (result) {
        $.ajax({
            url : "/organizacije/delete/" + id,
            type : 'GET',
            success : function(data) {
                $('#organizacijeModal').modal('hide');
                refreshPage();
            },
            error : function(request,error)
            {
                console.log("Request: "+JSON.stringify(request));
            }
        });
    }
}

function showDodajNovuBolnicu(id) {
    $('#organizacijeModal').modal('show');

    $.ajax({
        url : "/organizacije/new",
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

function saveNovuBolnicu() {
    $.ajax({
        url : "/organizacije/new/save",
        type : 'POST',
        data : JSON.stringify(getFormData("organizacijaNewForm")),
        contentType: "application/json; charset=UTF-8",

        success : function(data) {
            $('#organizacijeModal').modal('hide');
            refreshPage();
        },
        error : function(request,error)
        {
            console.log("Request: "+JSON.stringify(request));
        }
    });
}

//TODO: Zamjeniti refreshPage sa refreshDataTables
function refreshPage() {
    location.reload();
}