$(function() {
    $("#data_users").DataTable({
        "paging": false,
        "lengthChange": false,
        "searching": true,
        "ordering": false,
        "info": true,
        "autoWidth": false,
    });
});
$(document).ready(function() {
    $('.navAdmin').addClass('active');
});

setInterval(function(){
     $("#page_idx").load(location.href + " #page_idx>*", "");
 }, 5000);
