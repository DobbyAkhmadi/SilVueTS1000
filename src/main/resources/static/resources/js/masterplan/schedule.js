//Schedule Type Table
$(function () {
    $("#data_master_plan").DataTable({
        "paging": true,
        "lengthChange": false,
        "searching": true,
        "ordering": false,
        "info": true,
        "autoWidth": false,
    });
});

//Data Master Conflict
$(function () {
    $("#data_master_conflict").DataTable({
        "paging": true,
        "lengthChange": false,
        "searching": true,
        "ordering": false,
        "info": true,
        "autoWidth": false,
    });
});

//Train Color Table
$(function () {
    $("#data_color").DataTable({
        "paging": true,
        "lengthChange": false,
        "searching": true,
        "ordering": false,
        "info": true,
        "autoWidth": false,
    });
});
//loading add schedule event
$(document).on('click', '.loading_add', function(){
    var data = $('#name_schedul').val();
    if (data != "") {
       // $("#img").fadeIn();
        toastr.info('Adding data schedule Event.')
    }
});
//loading modify schedule event
$(document).on('click', '.loading_modify', function(){
    var data = $('#name_schedul').val();
    if (data != "") {
        $("#img").fadeIn();
        toastr.info('Modify data schedule Event.')
    }
});
//loading delete all schedule event
$(document).on('click', '.loading_delete_all', function(){
    var data = $('#name_schedul').val();
    if (data != "") {
        $("#img").fadeIn();
        toastr.info('Delete all Schedule Event.')
    }
});
//loading delete schedule by id event
$(document).on('click', '.loading_delete', function(){
    var data = $('#name_schedul').val();
    if (data != "") {
        $("#img").fadeIn();
        toastr.info('Delete a Schedule Event.')
    }
});
//delete data schedule type
$('.deleteid_schedule').click(function(){
    var delete_id = $(this).attr('delete_data_schedule_id');
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
        toastr.info('Deleting data type schedule.')
        window.location = "/settings/delete-schedule-type/" + delete_id;
        }
    })
});
//delete data masterPlan by id Train
$('.deleteMasterPlanByIdTrain').click(function(){
    var delete_id = $(this).attr('masterPlanByIdTrain');
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
        toastr.info('Deleting data type schedule.')
        window.location = "/masterschedule/delete-masterplan-by-idtrain/" + delete_id;
        }
    })
});

//delete all data masterPlan by id Train
$('.deleteMasterPlanById').click(function(){
    var delete_id = $(this).attr('idMasterPlan');
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
        toastr.info('Deleting data type schedule.')
        window.location = "/masterschedule/delete-all-masterplan-by-idtrain/" + delete_id;
        }
    })
});

//delete all data
$('.deleteAllMasterPlan').click(function(){
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
        toastr.info('Deleting data type schedule.')
        window.location = "/masterschedule/delete-all-masterplan/";
        }
    })
});

//modal add data
$('.li-modal-add').on('click', function(e){
   e.preventDefault();
   $('#modalAddMasterSchedule').modal('show').find('.modal-content').load($(this).attr('href'));
});
//modal modify data
$('.li-modal-schedule').on('click', function(e){
   e.preventDefault();
   $('#modalModifySchedule').modal('show').find('.modal-content').load($(this).attr('href'));
});
//modal modify detail data
$('.li-modal-schedule-detail').on('click', function(e){
   e.preventDefault();
   $('#modalModifyDetailSchedule').modal('show').find('.modal-content').load($(this).attr('href'));
});
//modal export data
$('.li-modal-export').on('click', function(e){
    e.preventDefault();
    $('#modalExportData').modal('show').find('.modal-content').load($(this).attr('href'));
});
//modal combine data
$('.li-modal-combine').on('click', function(e){
    e.preventDefault();
    $('#modalCombineSchedule').modal('show').find('.modal-content').load($(this).attr('href'));
});

function ValidateCheckBoxDetailTrain()
{
  var flagMasterCheckbox = document.getElementById("idFlagMasterPlan");
  document.getElementById('idFlagMasterPlan').value = 0;
  if (flagMasterCheckbox.checked) {
       document.getElementById('idFlagMasterPlan').value = 1;
  } else {
       document.getElementById('idFlagMasterPlan').value = 0;
  }
}

// AJAX EVENT Add Peron Each Station Choose Event Add
$(document).ready(function() {
     var CSRF_TOKEN = $('meta[name="csrf-token"').attr('content');
     $('.navSubmit').addClass('active');
     //$("#sidebarTM").tabs({ active: 1 });
     // STATION Change
     $('#JSAdd').change(function() {
        // STATION id
        var id = $(this).children(":selected").attr("value");
        console.log("data " + id);
        // Empty the dropdown
        $('#FromAdd').find('option').not(':first').remove();
        $.get("/api/v1/ts1000/peron/"+id, function(data) {
          for (let i in data) {
              console.log(data[i]);
              var option = "<option value='" + data[i].idPeron + "'>" + data[i].noPeron + "</option>";
              $("#FromAdd").append(option);
          }
        });
     });
});

