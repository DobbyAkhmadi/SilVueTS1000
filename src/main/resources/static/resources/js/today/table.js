//Schedule Type Table
// $(function () {
//     $("#data_today").DataTable({
//         "paging": true,
//         "lengthChange": false,
//         "searching": true,
//         "ordering": false,
//         "info": true,
//         "autoWidth": false,
//     });
// });
$(document).ready(function() {
    $('#data_today').DataTable( {
        "order": [[ 3, "desc" ]]
    } );
} );
//Train Color Table
// $(function () {
//     $("#data_today_detail").DataTable({
//         "paging": true,
//         "lengthChange": false,
//         "searching": true,
//         "ordering": false,
//         "info": true,
//         "autoWidth": false,
//     });
// });
$(document).ready(function() {
    $('#data_today_detail').DataTable( {
        "order": [[ 3, "desc" ]]
    } );
} );

//Data Today Conflict
// $(function () {
//     $("#data_today_conflict").DataTable({
//         // "paging": true,
//         //    "lengthChange": false,
//         "searching": true,
//         "ordering": false,
//         //  "info": true,
//         "autoWidth": false,
//     });
// });
$(document).ready(function() {
    $('#data_today_conflict').DataTable( {
        "order": [[ 3, "desc" ]]
    } );
} );

// navigation default
$(document).ready(function() {
    $('.navToday').addClass('active');
});