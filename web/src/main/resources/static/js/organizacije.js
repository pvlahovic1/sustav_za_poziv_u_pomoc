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