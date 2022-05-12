// navigation default
$(document).ready(function() {
    $('.navSettings').addClass('active');
});
//Train Color
$(function () {
    $("#dataTrainColor").DataTable({
        "paging": true,
     //   "lengthChange": false,
        "searching": true,
        "ordering": false,
        "info": true,
        "autoWidth": false,
    });
});
//Schedule Type
$(function () {
    $("#dataSchedule_type").DataTable({
        "paging": true,
      //  "lengthChange": false,
        "searching": true,
        "ordering": false,
        "info": true,
        "autoWidth": false,
    });
});

//Route Relation
$(function () {
    $("#dataRouteRelation").DataTable({
        "paging": true,
   //     "lengthChange": false,
        "searching": true,
        "ordering": false,
        "info": true,
        "autoWidth": false,
    });
});

//Line Relation
$(function () {
    $("#dataLineRelation").DataTable({
        "paging": true,
     //   "lengthChange": false,
        "searching": true,
        "ordering": false,
        "info": true,
        "autoWidth": false,
    });
});

//Conflict Master Schedule
$(function () {
    $("#stationListAdd").DataTable({
        "paging": true,
   //     "lengthChange": false,
        "searching": true,
        "ordering": false,
        "info": true,
        "autoWidth": false,
    });
});


