$(function () {
    $("#data_today").DataTable({
        "paging": true,
        "lengthChange": false,
        "searching": true,
        "ordering": false,
        "info": true,
        "autoWidth": false,
    });
});

$(document).on('click', '#export', function() {
    window.location = "/todayrunning/export";
});

$(document).ready(function() {
    $('.navToday').addClass('active');
    $returnInterval = setInterval(function() {
        var d = new Date();
        var date = d.getDate();
        var month = d.getMonth() + 1; // Since getMonth() returns month from 0-11 not 1-12
        var year = d.getFullYear();
        if(month < 10){
            month = "0"+month;
        }
        if(date < 10){
            date = "0"+date;
        }
        var dateStr = year  + "-" + month + "-" + date;

        if(actualcode == "" || actualcode != dateStr){
                    toastr.error('Todays not Save on Actual');
                }


    }, 6000);
});

$(document).on('click', '.applyTypeSchedule', function(e) {
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
        footer: '<button class="btn btn-secondary"  href="#!" id="some-action">Insert and Keep Autoupdate Schedule</button>'
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

            $('#myform').submit();
        }
    });

    $(document).on('click', '#some-action', function(e) {
        e.preventDefault();
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

        $('#myform').submit();
    });
});

$(document).on('click', '.inserttoactual', function(e) {
    e.preventDefault(); //prevent submit
    statbtn = 'false';
    swal.fire({
        title: "Are you sure?",
        text: "schedule hasn't been checked",
        type: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        confirmButtonText: "Yes!",
        cancelButtonText: "Cancel",
        cancelButtonColor: '#d33',
        closeOnConfirm: true,
        // footer: '<button class="btn btn-secondary"  href="#!" id="checkSch">Insert and Check Schedule</button>',
    }).then((result) => {
        if (result.value) {
            $("#img").fadeIn();
            window.location = "/todayrunning/insertToActualNoCheck";
        }
    });
    // $(document).on('click', '#checkSch', function(e) {
    //     window.location = "/todayrunning/insertToActual";
    // });
});

$('.loading_today_import').click(function() {
    console.log(document.getElementById("csvUploadFile").files.length);
    if (document.getElementById("csvUploadFile").files.length > 0) {
        $("#img").fadeIn();
    }


});

$(document).on('click', '.edit-modal', function() {
    $(this).data('id');
    window.location = "/todayrunning/index/" + $(this).data('id');;

});


$('.deleteid').click(function() {
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
            window.location = "/todayrunning/deleteTrain/" + $(this).data('id');;
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
// $('.inserttoactual').click(function() {
//     $("#img").fadeIn();
// });