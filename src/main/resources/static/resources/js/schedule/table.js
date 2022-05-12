//=========================================================
//Default Navigation
//=========================================================
$(document).ready(function() {
    $('.navSchedule').addClass('active');
});
//Schedule Type Table
// $(function () {
//     $("#data_master_plan").DataTable({
//         "paging": true,
//       //  "lengthChange": false,
//         "searching": true,
//         "ordering": false,
//         "info": true,
//         "autoWidth": false,
//     });
// });

$(document).ready(function() {
    $('#data_master_plan').DataTable( {
        "order": [[ 3, "desc" ]]
    } );
} );


//Data Master Conflict
// $(function () {
//     $("#data_master_conflict").DataTable({
//        // "paging": true,
//     //    "lengthChange": false,
//         "searching": true,
//         "ordering": false,
//       //  "info": true,
//         "autoWidth": false,
//     });
// });

$(document).ready(function() {
    $('#data_master_conflict').DataTable( {
        "order": [[ 3, "desc" ]]
    } );
} );


//Data Master Detail
// $(function () {
//     $("#data_master_detail").DataTable({
//         "paging": true,
// //        "lengthChange": false,
//         "searching": true,
//         "ordering": false,
//         "info": true,
//         "autoWidth": false,
//     });
// });

$(document).ready(function() {
    $('#data_master_detail').DataTable( {
        "order": [[ 3, "desc" ]]
    } );
} );

//Train Color Table
// $(function () {
//     $("#data_color").DataTable({
//         "paging": true,
// //        "lengthChange": false,
//         "searching": true,
//         "ordering": false,
//         "info": true,
//         "autoWidth": false,
//     });
// });

$(document).ready(function() {
    $('#data_color').DataTable( {
        "order": [[ 3, "desc" ]]
    } );
} );

