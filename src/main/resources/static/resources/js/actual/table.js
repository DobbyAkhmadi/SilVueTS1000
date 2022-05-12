// Actual Schedule
// $(function () {
//     $("#actual_list").DataTable({
//         "paging": true,
//         "lengthChange": false,
//         "searching": true,
//         "ordering": false,
//         "info": true,
//         "autoWidth": false,
//     });
// });
$(document).ready(function() {
    $('#actual_list').DataTable( {
        "order": [[ 3, "desc" ]]
    } );
} );
// Problem Actual
// $(function () {
//     $("#actual_problem").DataTable({
//         "paging": true,
//         "lengthChange": false,
//         "searching": true,
//         "ordering": false,
//         "info": true,
//         "autoWidth": false,
//     });
// });
$(document).ready(function() {
    $('#actual_problem').DataTable( {
        "order": [[ 3, "desc" ]]
    } );
} );
// nav actual
$(document).ready(function() {
    $('.navActual').addClass('active');
});
