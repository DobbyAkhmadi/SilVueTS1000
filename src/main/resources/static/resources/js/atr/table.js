// navigation default
$(document).ready(function() {
    $('.navAtr').addClass('active');
});
$(document).ready(function() {
    $('#page_idx').DataTable( {
        "order": [[ 3, "desc" ]]
    } );
} );
