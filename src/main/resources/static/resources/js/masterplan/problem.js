$(function() {
    $("#data_problem").DataTable({
        "paging": true,
        "lengthChange": false,
        "searching": true,
        "ordering": false,
        "info": true,
        "autoWidth": false,
    });
});

$(document).on('click', '.edit-problem', function(){
    $('#edit_id_Log').val($(this).data('idlog'));
    $('#edit_train_Log').val($(this).data('trainlog'));
    $('#edit_timedataactual_Log').val($(this).data('dataactualcodes'));
    $('#edit_arrivings_Log').val($(this).data('arrival'));
    $('#edit_departs_Log').val($(this).data('depart'));
    $('#edit_comments_Log').val($(this).data('commentslog'));
    $('#problog').val($(this).data('problem'));
    $('#edit_department_Log').val($(this).data('department'));
    $('#ModalEditProblem').modal('show');
});



// $(document).ready(function(){
//     $(document).on('change','.dept',function() {
//         var id = $(this).val();
//         console.log(id);

//             $.ajax({
//                 type:"GET",
//                 url:'/problemList/' +id,
//                 dataType: "json",
//                 success:function(data)
//                 {
//                     console.log(data);

//                     });
//                 }
//             });
//     });
// });

$('.deleteProblem').click(function() {
    console.log("tampan");
    value = $(this).data('idlog');
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
                window.location = "/actual/deletecomment/" + value;
        }
    })
});

$(document).on('click', '#deleteProblem', function() {
    toastr.info('Deleting data master plan.');
    var delete_id = $(this).data('train');
    var deleteActualCode = $(this).data('dataactualcode');
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
            /*$.ajax({
                url: '/actual/deletetrain',
                type: 'get',
                async: false,
                dataType: 'JSON',
                data:{
                  trainNumber: delete_id,
                  actualCode: deleteActualCode
                },

                success: function(response){

                    window.location = "/actualDetail";
                },
                error: function(xhr, ajaxOptions, thrownError){
                    alert(thrownError);
                    window.location = "/actualDetail";
                }
            });//end ajax*/

        }
    })
});

$(document).ready(function() {
    var CSRF_TOKEN = $('meta[name="csrf-token"').attr('content');

    // STATION Change
    $('#edit_department_Log').change(function() {
        // STATION id
        var id = $(this).val();
        console.log(id);

        //AJAX request
        $.ajax({
            url: '/getProbList/' + id,
            type: 'GET',
            dataType: 'json',

            success: function(response) {
                var len = 0;
                if (response['data'] != null) {
                    len = response['data'].length;
                }

                if (len > 0) {
                    $('#problist').empty();
                    // Read data and create <option >
                    for (var i = 0; i < len; i++) {

                         var id = response.data[i]['IDPROBLEM'];
                        var name = response.data[i]['PROBLEM_NAME'];
                        var option = "<option value='" + name + "'>" + name + "</option>";
                        $('#problist').append(option);
                        $('.selectpicker').selectpicker('refresh');

                    }
                }
            },
            error: function(xhr, ajaxOptions, thrownError) {
                alert(thrownError);
            }
        });
    });
});