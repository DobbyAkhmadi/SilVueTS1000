$(function() {
    $("#data_alarm").DataTable({
        "paging": false,
        "lengthChange": false,
        "searching": true,
        "ordering": false,
        "info": true,
        "autoWidth": false,
    });
});

$(document).ready(function() {
    $('.navAlarm').addClass('active');
});
//delete_data + loading
$('.deleteall').click(function() {
    //var delete_id = $(this).attr('delete_data');
    Swal.fire({
        title: 'Are you sure?',
        text: "You won't be able to revert this!",
        type: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Yes, delete it!'
    }).then((result) => {
        if (result.value) {
            $("#img").fadeIn();
            toastr.info('Deleting Alarm.')
            window.location = "/eventlogger/deleteall";
        }
    })
});