$(function() {
    $("#page_idx").DataTable({
        "paging": false,
        "lengthChange": false,
        "searching": true,
        "ordering": false,
        "info": true,
        "autoWidth": false,
    });
});
$(document).ready(function() {
    $('.navTrain').addClass('active');
});

setInterval(function(){
     $("#traintable").load(location.href + " #traintable>*", "");
 }, 5000);
