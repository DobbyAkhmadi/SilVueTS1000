
$(function () {
    $("#data_dept").DataTable({
        "paging": true,
        "lengthChange": false,
        "searching": true,
        "ordering": false,
        "info": true,
        "autoWidth": false,
    });
});

//mendapatkan data dan tampilkan di modal schedule
$(document).on('click', '.edit-modal-dept', function() {
    $('#edit_id_dept').val($(this).data('idschedul'));
    $('#edit_name_dept').val($(this).data('nameschedul'));
    id = $('#edit_id_dept').val();
    $('#modalmodifydept').modal('show');
});

//loading add schedule
$(document).on('click', '.loading_schedule_add', function(){
    var data = $('#name_dept').val();
    if (data != "") {
        $("#img").fadeIn();
        toastr.info('Adding data Department.')
    }
});

//loading edit dept
$(document).on('click', '.loading_schedule_edit', function(){
    var data = $('#name_schedul').val();
    if (data != "") {
        $("#img").fadeIn();
        toastr.info('Editing data type schedule.')
    }
});

//delete data + loading
$('.deleteid_dept').click(function(){
    console.log('tampan');
    var delete_id = $(this).attr('delete_data_dept_id');
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
        toastr.info('Deleting data type schedule.')
        window.location = "/dept/delete/" + delete_id;
        }
    })
});