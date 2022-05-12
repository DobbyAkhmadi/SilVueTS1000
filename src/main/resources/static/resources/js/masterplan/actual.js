$(function() {
    $("#example1").DataTable({
        "paging": false,
        "lengthChange": false,
        "searching": true,
        "ordering": false,
        "info": true,
        "autoWidth": false,
    });
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
            toastr.info('Deleting data master plan.');
            window.location = "/actual/deleteall";
        }
    })
});

function indexChange(selectObject) {
    $("#img").fadeIn();
    var value = selectObject.value;
    value = value.replace("/", "-");
    value = value.replace("/", "-");
    console.log(value);
    window.location = "/actual/indexbynumber/" + value;
}

$(document).on('click', '#export', function() {
    window.location = "/actual/export";
});

$(document).on('click', '.edit-modals', function() {
    console.log($(this).data('indexActualPlan'));
    window.location = "/actual/noka/" + $(this).data('indexactualplan')+"_"+$(this).data('dataactualcode')+"_"+ $(this).data('train');
});

$(document).on('click', '#combine', function() {
    value = $(this).data('actualcode')
    value = value.replace("/", "-");
    value = value.replace("/", "-");
    Swal.fire({
        title: 'Are you sure?',
        text: "You won't be able to revert this!",
        type: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Yes, combine it!'
    }).then((result) => {
        if (result.value) {
            window.location = "/actual/combine/" + value;
        }
    })
});


$('.deletetrain').click(function() {
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
            $.ajax({
                url: '/actual/deletetrain',
                type: 'get',
                async: false,
                dataType: 'JSON',
                data: {
                    trainNumber: delete_id,
                    actualCode: deleteActualCode
                },

                success: function(response) {

                    window.location = "/actual/suksesdelete";
                },
                error: function(xhr, ajaxOptions, thrownError) {
                    alert(thrownError);
                    window.location = "/actual/faileddelete";
                }
            }); //end ajax

        }
    })
});
$(document).on('click', '#deletetrain', function() {
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
$(document).on('click', '#resetunknown', function() {
    Swal.fire({
        title: 'Are you sure?',
        text: "You won't be able to revert this!",
        type: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Yes, reset it!'
    }).then((result) => {
        if (result.value) {
            $("#img").fadeIn();
            toastr.info('Reset Unknown Number.');
            window.location = "/actual/resetunknown";
        }
    })
});

$(document).on('click', '#deleteallactual', function() {
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
            toastr.info('Reset All Data Actual.');
            window.location = "/actual/deleteall";
        }
    })
});


$(document).on('click', '#confirmImportActual', function() {
    Swal.fire({
        title: 'Are you sure?',
        text: "This will erase the data on the imported date!",
        type: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Yes, import it!'
    }).then((result) => {
        if (result.value) {
            $('#Modalimport').modal('show');
        }
    })
    

});

$(document).on('click', '#deletespecificdate', function() {
    Swal.fire({
        title: 'Are you sure?',
        text: "You won't be able to revert this!",
        type: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Yes'
    }).then((result) => {
        if (result.value) {
            $('#ModalDeleteSpecifikActual').modal('show');
        }
    })
    

});

$(document).on('click', '.edit-modal', function() {
    $('#edit_id').val($(this).data('id'));
    $('#edit_train').val($(this).data('train'));
    $('#edit_timedataactual').val($(this).data('dataactualcode'));
    //$('#edit_status').val($(this).data('status'));
    //toastr.info($(this).data('arrival'));
    $('#edit_arrivings').val($(this).data('arrival'));
    $('#edit_departs').val($(this).data('depart'));
    $('#edit_comments').val($(this).data('comments'));
    $('#edit_idlogmessage').val($(this).data('idlogmessage'));
    $('#Modaledit').modal('show');
});

$(document).ready(function() {
    $('.navActual').addClass('active');
});

//delete_data + loading
// $('.deleteid').click(function(){
//     var delete_id = $(this).attr('delete_data');
//     Swal.fire({
//         title: 'Are you sure?',
//         text: "You won't be able to revert this!",
//         type: 'warning',
//         showCancelButton: true,
//         confirmButtonColor: '#3085d6',
//         cancelButtonColor: '#d33',
//         confirmButtonText: 'Yes, delete it!'
//     }).then((result) => {
//         if (result.value) {
//         $("#img").fadeIn();
//         toastr.info('Deleting data master plan.')
//         window.location = "/actual/delete/" + delete_id;
//         }
//     })
// });

//delete_data + loading
// $('.resetunknown').click(function(){
//     Swal.fire({
//         title: 'Are you sure?',
//         text: "You won't be able to revert this!",
//         type: 'warning',
//         showCancelButton: true,
//         confirmButtonColor: '#3085d6',
//         cancelButtonColor: '#d33',
//         confirmButtonText: 'Yes, reset it!'
//     }).then((result) => {
//         if (result.value) {
//         $("#img").fadeIn();
//         toastr.info('Reset Unknown Number.');
//         window.location = "/actual/resetunknown";
//         }
//     })
// });