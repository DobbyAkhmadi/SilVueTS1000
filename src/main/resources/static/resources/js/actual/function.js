// navigation default
$(document).ready(function() {
    $('.navActual').addClass('active');
});
//delete All Data Actual
$('.deleteAllActual').click(function(){
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
        toastr.info('Deleting data all today schedule.')
        window.location = "/actual/delete-all-actualschedule/";
        }
    })
});


//delete Actual By Id
        $('.deleteActualRunningScheduleById').click(function(){
            var delete_id = $(this).attr('idActualPlan');
            // var link = $(this).find('a').attr('href');
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
                toastr.info('Deleting data Actual schedule.')
                window.location = "/actual/delete-actualSchedule-by-idActualPlan/" + delete_id;
                }
            })
        });

//delete Specific Actual Schedule
$('.combineIndexActual').click(function(){
    Swal.fire({
        title: 'Are you sure?',
        text: "You won't be able to revert this!",
        type: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Yes, Combine it!'
    }).then((result) => {
        if (result.value) {
        $("#img").fadeIn();
        toastr.info('Combine Index Actual.')
        window.location = "/actual/combine-index-actual/";
        }
    })
});

//delete Problem By Id
$('.deleteProblemLogById').click(function(){
    var delete_id = $(this).attr('idActualPlan');
    // var link = $(this).find('a').attr('href');
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
            toastr.info('Deleting data Problem Log.')
            window.location = "/actual/delete-problemLog-by-idActualPlan/" + delete_id;
        }
    })
});


//=========================================================
//Default Validation All Train
//=========================================================

function indexChange(selectObject) {
    $("#img").fadeIn();
    var value = selectObject.value;
   value = value.replace();
    value = value.replace();
   //console.log(value);
    window.location = "/actual/index/" + value;
}



/*$(document).on('click', '.edit-modals', function() {
    console.log($(this).data('indexActuals'));
    window.location = "/actual/noka/" + $(this).data('indexactualplan')+"_"+$(this).data('dataactualcode')+"_"+ $(this).data('train');
});*/

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
 n =  new Date();
 y = n.getFullYear();
 m = n.getMonth() + 1;
 d = n.getDate();
document.getElementById("date1").innerHTML = y + "/" + m + "/" + d;
document.getElementById("date2").innerHTML = y + "/" + m + "/" + d;

