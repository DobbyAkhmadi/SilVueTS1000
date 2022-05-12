$(function () {
    $("#data_conflict").DataTable({
        "paging": true,
        "lengthChange": false,
        "searching": true,
        "ordering": false,
        "info": true,
        "autoWidth": false,
    });
    // $("#data_mp").DataTable({
    //     "paging": true,
    //     "lengthChange": false,
    //     "searching": true,
    //     "ordering": false,
    //     "info": true,
    //     "autoWidth": false,
    // });
});
$(document).on('click', '.edit-modal-conflictmaster', function() {

    

    $("#edit_id_conflict").val($(this).data('idconflictmaster'));
    $("#edit_train_a").val($(this).data('traina'));
    $("#edit_train_b").val($(this).data('trainb'));
    $("#edit_arriving_a").val($(this).data('arrivea'));
    $('#edit_arriving_b').val($(this).data('arriveb'));
    $('#edit_depart_a').val($(this).data('departa'));
    $('#edit_depart_b').val($(this).data('departb'));
    $('#Modaleditconflictmaster').modal('show');
});


$('.check_master_data').click(function(){
    $('.check_master_data').attr("disabled", true);
    
    var counter = 0;

    var typeScheduleSelction = document.getElementById('typeschedulecheckmaster').value;
    //toastr.warning(typeScheduleSelction);

    $.ajaxSetup({
        headers: {
        'X-CSRF-TOKEN': $('meta[name="csrf-token"]').attr('content')
        }
    });
        
    function socketmaster(){
        $.ajax({
        url: '/conflict/socketmaster',
        type: 'post',
        data: {
            typeSchedule: typeScheduleSelction
        },
        success: function(response){
            // console.log(response)
            //toastr.warning(response);
            toastr.warning('Waiting response from conflict service.');
            if(response == "\"sukses\""){
                countdown_ajaxcall();
            }else{
                Swal.fire(
                    'Request time out',
                    response,
                    'error'
                    )
            $('.check_master_data').attr("disabled", false);
            $("#img").fadeOut();
            }
        },
        });
    }
    
    $.ajax({
        url: '/conflict/clear',
        type: 'post',
        success: function(response){
            socketmaster();
            $("#img").fadeIn();

        },
    });

    function countdown_ajaxcall(){
        var countdown = setInterval(function(){
        
        $.ajax({
            url: '/conflict/respon_data',
            type: 'get',
            dataType: 'json',

            success: function(response){
            if(response == "true"){
                clearInterval(countdown);
                toastr.success('Success Check Master Schedule.')
                $("#img").fadeOut();
                    Swal.fire(
                        'Success !',
                        'Check Conflict Master Schedule Done',
                        'success'
                    ).then((result) => {
                        $("#img").fadeIn();
                        window.location = "/submitDetail";
                    })
                $('.check_master_data').attr("disabled", false);
               
            }
            },

            error: function(xhr, ajaxOptions, thrownError){
            clearInterval(countdown);
            Swal.fire(
            'Error!',
            ''+thrownError+'',
            'error'
            )
            }
        });
        counter++
            if(counter === 60){
            Swal.fire(
                'Request time out',
                'conflict service didnt response request',
                'error'
                )
            clearInterval(countdown);
            $("#img").fadeOut();
            $('.check_master_data').attr("disabled", false);
            }
        }, 
        3000);
    }
    //window.location = "/conflict";
});

$('.check_today_data').click(function(){
    $('.check_today_data').attr("disabled", true);

    //var typeScheduleSelction = document.getElementById('typeschedulecheckmaster').value;
    //toastr.warning(typeScheduleSelction);

    var counter = 0;

    $.ajaxSetup({
        headers: {
        'X-CSRF-TOKEN': $('meta[name="csrf-token"]').attr('content')
        }
    });
        
    function sockettoday(){
        $.ajax({
        url: '/conflict/sockettoday',
        type: 'post',
        
        success: function(response){
            // console.log(response)
            //toastr.warning(response);
            toastr.warning('Waiting response from conflict service.');
            if(response == "\"sukses\""){
                countdown_ajaxcall_today();
            }else{
                Swal.fire(
                    'Request time out',
                    response,
                    'error'
                    );
            $('.check_today_data').attr("disabled", false);
            $("#img").fadeOut();
            }
        },
        });
    }
    
    $.ajax({
        url: '/conflict/clear',
        type: 'post',
        success: function(response){
            sockettoday();
            $("#img").fadeIn();

        },
    });

    function countdown_ajaxcall_today(){
        var countdown = setInterval(function(){
        
        $.ajax({
            url: '/conflict/respon_data',
            type: 'get',
            dataType: 'json',

            success: function(response){
            if(response == "true"){
                clearInterval(countdown);
                toastr.success('Success Check Today Schedule.')
                $("#img").fadeOut();
                    Swal.fire(
                        'Success !',
                        'Check Conflict Done',
                        'success'
                    ).then((result) => {
                        $("#img").fadeIn();
                        window.location = "/todaysDetail";
                    })
                $('.check_today_data').attr("disabled", false);
                window.location = "/todaysDetail";
            }
            },

            error: function(xhr, ajaxOptions, thrownError){
            clearInterval(countdown);
            Swal.fire(
            'Error!',
            ''+thrownError+'',
            'error'
            )
            }
        });
        counter++
            if(counter === 60){
            Swal.fire(
                'Request time out',
                'conflict service didnt response request',
                'error'
                )
            clearInterval(countdown);
            $("#img").fadeOut();
            $('.check_today_data').attr("disabled", false);
            }
        }, 
        3000);
    }
    //window.location = "/conflict";
});