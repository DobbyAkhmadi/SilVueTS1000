$(function() {
    $("#data_users").DataTable({
        "paging": true,
        "lengthChange": false,
        "searching": true,
        "ordering": false,
        "info": true,
        "autoWidth": false,
    });
});
$(document).on('click', '.user-edit-modal', function() {
    $("#edit_id_user").val($(this).data('id'));
    $("#edit_name_user").val($(this).data('name'));
    $('#usermodalsedit').modal('show');
});

$('.deleteuserdetail').click(function() {
    var id = $(this).data('id');
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
            window.location = "/auth/delete/" + id;
        }
    })
});
$(document).ready(function() {
    $('.navAdmin').addClass('active');
});

$(document).ready(function() {
    $("#show_hide_password a").on('click', function(event) {
        event.preventDefault();
        if($('#show_hide_password input').attr("type") == "text"){
            $('#show_hide_password input').attr('type', 'password');
            $('#show_hide_password i').addClass( "fa-eye-slash" );
            $('#show_hide_password i').removeClass( "fa-eye" );
        }else if($('#show_hide_password input').attr("type") == "password"){
            $('#show_hide_password input').attr('type', 'text');
            $('#show_hide_password i').removeClass( "fa-eye-slash" );
            $('#show_hide_password i').addClass( "fa-eye" );
        }
    });
});

$('#modaladduser').on('hide.bs.modal', function () {
    $("input").removeClass("is-invalid");
    });

$('#usermodalsedit').on('hide.bs.modal', function () {
    $("input").removeClass("is-invalid");
    });