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
        },
        error : function(request,error)
        {
            console.log("Request: "+JSON.stringify(request));
        }
    });
}