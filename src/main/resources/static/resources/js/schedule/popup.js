//=========================================================
//Default Navigation
//=========================================================
$(document).ready(function() {
    $('.navSchedule').addClass('active');
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
//modal import data
$('.li-modal-import').on('click', function(e){
    e.preventDefault();
    $('#modalImportData').modal('show').find('.modal-content').load($(this).attr('href'));
});
//modal combine data
$('.li-modal-combine').on('click', function(e){
    e.preventDefault();
    $('#modalCombineSchedule').modal('show').find('.modal-content').load($(this).attr('href'));
});

//delete data masterPlan by id Train
$('.deleteMasterPlanByIdTrain').click(function(){
    var delete_id = $(this).attr('masterPlanByIdTrain');
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
        toastr.info('Deleting data type schedule.')
            // show loading with MS
            $("#img").fadeIn(2000);
            // hide loadings
            $("#img").fadeOut("slow", function () {
                window.location = "/schedule/masterschedule/delete-masterplan-by-idtrain/" + delete_id;
            });
        }
    })
});
//delete all data masterPlan by id Train
$('.deleteMasterPlanById').click(function(){
    var delete_id = $(this).attr('idMasterPlan');
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
            toastr.info('Deleting data type schedule.')
            // show loading with MS
            $("#img").fadeIn(2000);
            // hide loadings
            $("#img").fadeOut("slow", function () {
                window.location = "/schedule/masterschedule/delete-all-masterplan-by-idtrain/" + delete_id;
            });
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
            toastr.info('Deleting data type schedule.')
            // show loading with MS
            $("#img").fadeIn(2000);
            // hide loadings
            $("#img").fadeOut("slow", function () {
                window.location="/schedule/masterschedule/delete-all-masterplan/";
            });
        }
    })
});
//check all conflict
$('.checkConflict').click(function() {
    let val = document.getElementById('idTypeMasterPlan').value;
    if (val != "")
    {
        Swal.fire({
            title: 'Are you sure?',
            text: "You won't be able to revert this!",
            type: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Yes, Check it!'
        }).then((result) => {
            if (result.value) {
                toastr.info('check conflict data schedule : '+ $("#idTypeMasterPlan option:selected").text())
                // execute to ADA GNAT
                $.post({
                    url: "/api/v1/ts1000/checkConflict",
                    data: {
                        typeSchedule: $("#idTypeMasterPlan option:selected").text(),
                    },
                    success: function (data) {
                        console.log(data);
                    }
                });
                // show loading with MS
                $("#img").fadeIn(5000);
                // hide loadings
                $("#img").fadeOut("slow", function () {
                    // Animation complete.
                    // Fetch All Conflict to Array Tables
                    $.get("/api/v1/ts1000/getAllConflict", function (dataArray) {
                        // init var datatable
                        let dataTable = $('#data_master_conflict').DataTable();
                        // refresh / remove
                        dataTable.row().remove().draw()
                        if(Object.entries(dataArray).length==0){
                            dataTable.row().remove().draw()
                        }else{
                            for (let i in dataArray) {
                                dataTable.row.add([
                                    dataArray[i].masterPlanA.train.noka,
                                    dataArray[i].masterPlanB.train.noka,
                                    "Peron No " + dataArray[i].masterPlanA.peronFrom.noPeron,
                                    dataArray[i].masterPlanA.peronFrom.station.nameStation,
                                    dataArray[i].masterPlanA.arrival,
                                    dataArray[i].masterPlanB.arrival,
                                    dataArray[i].masterPlanA.depart,
                                    dataArray[i].masterPlanB.depart,
                                    dataArray[i].masterPlanA.ruteRole.nameRoute,
                                    dataArray[i].masterPlanB.ruteRole.nameRoute,
                                    dataArray[i].masterPlanA.typeMasterPlan.nameTypeMasterPlan,
                                    dataArray[i].masterPlanB.typeMasterPlan.nameTypeMasterPlan
                                ]).draw(false);
                                console.log(dataArray[i]);
                            }
                        }
                    });
                });
            }
        })
    }
    else
    {
        toastr.warning('Please Select Type Schedule First !')
    }
});
