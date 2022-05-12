// navigation default
//$(document).ready(function() {
//    $('.navActual').addClass('active');
//});
$('.deleteAllEventLogger').click(function(){
//console.log('hahaha');
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
            toastr.info('Deleting all data Event Logger.')
            // show loading with MS
            $("#img").fadeIn(2000);
            // hide loadings
            $("#img").fadeOut("slow", function () {
                window.location = "/alarm/delete-all-event";
            });
        }
    })
});

