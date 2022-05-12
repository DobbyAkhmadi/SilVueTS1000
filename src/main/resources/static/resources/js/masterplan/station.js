//stasiun_table
$(function () {
    $("#data_station").DataTable({
        "paging": true,
        "lengthChange": false,
        "searching": true,
        "ordering": false,
        "info": true,
        "autoWidth": false,
    });
});

//mendapatkan data dan tampilkan edit modal
$(document).on('click', '.edit-modal-station', function() {
    $('#id_station_edit').val($(this).data('idperon'));
    $('#name_station_edit').val($(this).data('namestation'));
    $('#peron_station_edit').val($(this).data('noperon'));
    id = $('#id_station_edit').val();
    $('#Modaleditstation').modal('show');
});

//delete stasiun data
$('.deleteidstation').click(function(){
    var delete_id = $(this).attr('delete_data_station_id');
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
        window.location = "/station/delete/" + delete_id;
        }
    })
});