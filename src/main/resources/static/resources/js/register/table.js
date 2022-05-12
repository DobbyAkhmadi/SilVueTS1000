//=========================================================
//Default Navigation
//=========================================================
$(document).ready(function() {
    $('.navAdmin').addClass('active');
});
$(document).ready(function() {
    $('#dataUser').DataTable( {
        "order": [[ 1, "asc" ]]
    } );
} );