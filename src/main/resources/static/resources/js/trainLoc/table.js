//=========================================================
//Default Navigation
//=========================================================
$(document).ready(function() {
    $('.navTrain').addClass('active');
});
$(document).ready(function() {
    $('#trainLocationTable').DataTable( {
        "order": [[ 1, "asc" ]]
    } );
} );