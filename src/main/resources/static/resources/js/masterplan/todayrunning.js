$(document).on('click', '#export', function() {
    window.location = "/todayrunning/export";
});

$(document).ready(function() {
    $('.navToday').addClass('active');
});
$(document).on('click', '.edit-modal', function() {
    $('#edit_id').val($(this).data('id'));
    $('#edit_train').val($(this).data('train'));
    $('#edit_arriving').val($(this).data('arrival'));
    $('#edit_depart').val($(this).data('depart'));
    $('#edit_dwelling').val($(this).data('dt'));
    $('#Modaledit').modal('show');
});
$('.applyTypeSchedule').click(function() {
    $("#img").fadeIn();
});

//modal add data
$('.li-modal-add').on('click', function(e){
   e.preventDefault();
   $('#modalAddMasterSchedule').modal('show').find('.modal-content').load($(this).attr('href'));
});

$('.deleteid').click(function() {
    var delete_id = $(this).attr('delete_data');
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
            toastr.info('Deleting data master plan.')
            window.location = "/todayrunning/delete/" + delete_id;
        }
    })
});

$('.deleteall').click(function() {
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
            toastr.info('Deleting data master plan.')
            window.location = "/todayrunning/delete";
        }
    })
});

$(document).on('click', '.applyEditSchedule', function(e) {
    e.preventDefault(); //prevent submit
    statfalse = 'false';
    stattrue = 'true';
    swal.fire({
        title: "Are you sure?",
        text: "auto update schedule will be dismissed!",
        type: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        confirmButtonText: "Yes!",
        cancelButtonText: "Cancel",
        cancelButtonColor: '#d33',
        closeOnConfirm: true,
        footer: '<button class="btn btn-secondary"  href="#!" id="some-action">Modify and Keep Autoupdate Schedule</button>'
    }).then((result) => {
        if (result.value) {
            $("#img").fadeIn();
            $.ajax({
                url: '/config/autoenable',
                type: 'GET',
                dataType: 'json',
                data: {
                    value: statfalse
                },
                success: function(response) {

                },
                error: function(xhr, ajaxOptions, thrownError) {}
            });

            $('#todayeditform').submit();
        }
    });
    $(document).on('click', '#some-action', function(e) {
        e.preventDefault();
        $("#img").fadeIn();
        $.ajax({
            url: '/config/autoenable',
            type: 'GET',
            dataType: 'json',
            data: {
                value: stattrue
            },
            success: function(response) {

            },
            error: function(xhr, ajaxOptions, thrownError) {}
        });
        $('#todayeditform').submit();
    });
});