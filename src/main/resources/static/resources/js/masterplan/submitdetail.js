//submit_table
$(function() {
    $("#data_mp").DataTable({
        "paging": true,
        "lengthChange": false,
        "searching": true,
        "ordering": false,
        "info": true,
        "autoWidth": false,
    });
});




//muncul data di edit_modal
$(document).on('click', '.edit-modal', function() {
    window.location = "/submit/index/" + $(this).data('idtrain') + "_" + $(this).data('idtypemasterplane')+"_"+$(this).data('idtyperuterole');
});

//delete_data + loading
$('.deleteid').click(function() {
    var delete_id = $(this).data('idmasterplan');
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
            window.location = "/submit/deleteTrain/" + delete_id;
        }
    })
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
            toastr.info('Deleting data master plan.')
            window.location = "/submit/deleteall";
        }
    })
});

$('.deletealldept').click(function() {
   
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
            window.location = "/submit/deletealldept";
        }
    })
});


//loading_add
$(document).on('click', '.loading_submit', function() {
    var trn = $('#train').val();
    var stsn_f = $('#sel_station_from').val();
    var prn_f = $('#sel_peron_from').val();
    var line = $('#line').val();
    var tptrn = $('#typetrain').val();
    var dprt = $('#depart').val();
    var arvl = $('#arrival').val();
    
    if (trn != "" && stsn_f != null &&  prn_f != null && line != null && tptrn != "" && dprt != "" && arvl != "") {
        $("#img").fadeIn();
        //toastr.info('Adding data master plan.');
    }

    // if (trn != "" && stsn_f != null && stsn_t != null && prn_f != null && prn_t != null && line != null && tptrn != "" && dprt != "" && arvl != "") {
    //     $("#img").fadeIn();
    //     //toastr.info('Adding data master plan.');
    // }

});

//loading_edit
$(document).on('click', '.loading_submit_edit', function() {
    // alert("asd");
    var eid = $('#edit_id').val();
    var etrn = $('#edit_train').val();
    var estsn_f = $('#sel_station_from_edit').val();
    var estsn_t = $('#sel_station_to_edit').val();
    var eprn_f = $('#sel_peron_from_edit').val();
    var eprn_t = $('#sel_peron_to_edit').val();
    var eline = $('#edit_line').val();
    var etptrn = $('#edit_typetrain').val();
    var edprt = $('#edit_depart').val();
    var edwlng = $('#edit_dwelling').val();
    var earvl = $('#edit_arriving').val();
    if (eprn_f != null && eprn_t != null && edwlng != null) {
        $("#img").fadeIn();
        toastr.info('Editing data master plan.')
    }
});

//loading_import
$(document).on('click', '.loading_submit_import', function() {
    if (document.getElementById("csvUploadFile").files.length > 0) {
        $("#img").fadeIn();
    }
});

//ajax add peron data sesuai stasiun
//from
$(document).ready(function() {
    var CSRF_TOKEN = $('meta[name="csrf-token"').attr('content');


    $('.navSubmit').addClass('active');

    //$("#sidebarTM").tabs({ active: 1 });
    // STATION Change
    $('#sel_station_from').change(function() {
        // STATION id
        var id = $(this).children(":selected").attr("value");
        console.log("data " + id);

        // Empty the dropdown
        $('#sel_peron_from').find('option').not(':first').remove();
        $.get("/api/v1/ts1000/peron/"+id, function(data) {
            for (let i in data) {
                console.log(data[i]);
                var option = "<option value='" + data[i].idPeron + "'>" + data[i].noPeron + "</option>";
                $("#sel_peron_from").append(option);
            }
        });
    });
});

//to
$(document).ready(function() {
    var CSRF_TOKEN = $('meta[name="csrf-token"').attr('content');

    // STATION Change
    $('#sel_station_to').change(function() {
        // STATION id
        var id = $(this).val();

        // Empty the dropdown
        $('#sel_peron_to').find('option').not(':first').remove();

        // AJAX request 
        $.ajax({
            url: '/getPeronto',
            type: 'POST',
            dataType: 'json',
            data: {
                id: id,
                _token: CSRF_TOKEN
            },

            success: function(response) {

                var len = 0;
                if (response['data'] != null) {
                    len = response['data'].length;
                }

                if (len > 0) {
                    // Read data and create <option >
                    for (var i = 0; i < len; i++) {

                        var id = response.data[i]['IDPERON'];
                        var name = response.data[i]['NOPERON'];

                        var option = "<option value='" + name + "'>" + name + "</option>";

                        $("#sel_peron_to").append(option);
                    }
                }
            },
            error: function(xhr, ajaxOptions, thrownError) {
                alert(thrownError);
            }
        });
    });
});

//ajax edit peron data sesuai stasiun
//from
$(document).ready(function() {
    var CSRF_TOKEN = $('meta[name="csrf-token"').attr('content');

    // STATION Change
    $('#sel_station_from_edit').change(function() {
        // STATION id
        var id = $(this).val();

        // Empty the dropdown
        $('#sel_peron_from_edit').find('option').not(':first').remove();

        // AJAX request 
        $.ajax({
            url: '/getPeronfrom',
            type: 'POST',
            dataType: 'json',
            data: {
                id: id,
                _token: CSRF_TOKEN
            },

            success: function(response) {

                var len = 0;
                if (response['data'] != null) {
                    len = response['data'].length;
                }

                if (len > 0) {
                    // Read data and create <option >
                    for (var i = 0; i < len; i++) {

                        var id = response.data[i]['IDPERON'];
                        var name = response.data[i]['NOPERON'];

                        var option = "<option value='" + name + "'>" + name + "</option>";

                        $("#sel_peron_from_edit").append(option);
                    }
                }
            },
            error: function(xhr, ajaxOptions, thrownError) {
                alert(thrownError);
            }
        });
    });
});

$(document).on('click', '#export', function() {
    window.location = "/submit/export";
});

//to
$(document).ready(function() {
    var CSRF_TOKEN = $('meta[name="csrf-token"').attr('content');

    // STATION Change
    $('#sel_station_to_edit').change(function() {
        // STATION id
        var id = $(this).val();

        // Empty the dropdown
        $('#sel_peron_to_edit').find('option').not(':first').remove();

        // AJAX request 
        $.ajax({
            url: '/getPeronfrom',
            type: 'POST',
            dataType: 'json',
            data: {
                id: id,
                _token: CSRF_TOKEN
            },

            success: function(response) {

                var len = 0;
                if (response['data'] != null) {
                    len = response['data'].length;
                }

                if (len > 0) {
                    // Read data and create <option >
                    for (var i = 0; i < len; i++) {

                        var id = response.data[i]['IDPERON'];
                        var name = response.data[i]['NOPERON'];

                        var option = "<option value='" + name + "'>" + name + "</option>";

                        $("#sel_peron_to_edit").append(option);
                    }
                }
            },
            error: function(xhr, ajaxOptions, thrownError) {
                alert(thrownError);
            }
        });
    });
});
