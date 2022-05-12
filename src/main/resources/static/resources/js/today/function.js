// navigation default
$(document).ready(function() {
    $('.navToday').addClass('active');
});
//delete all data today schedule
$('.deleteAllToday').click(function(){
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
        window.location = "/today/delete-all-todayDetail/";
        }
    })
});


    //delete all data todayDetail by id Train
    $('.deleteTodayDetails').click(function(){
        var delete_id = $(this).attr('idTodayRunningSchedule');
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
            toastr.info('Deleting data todayDetail schedule.')
            window.location = "/today/delete-all-todayDetail-by-idtrain/" + delete_id;
            }
        })
    });

     //delete all data todayDetail by id Train
        $('.deleteTodayRunningScheduleById').click(function(){
            var delete_id = $(this).attr('idTodayDetail');
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
                toastr.info('Deleting data todayDetail schedule.')
                window.location = "/today/delete-todayDetail-by-idTodayRunningSchedule/" + delete_id;
                }
            })
        });

$(document).on('click', '#insertwithoutcheck', function(e) {
    e.preventDefault();
    $("#img").fadeIn();
    window.location = "/today/today-to-actual";
});

$('.inserttoactual').click(function() {
    Swal.fire({
        title: 'Insert To Actual',
        text: "Continue to insert",
        type: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Insert, With Check Conflict',
        footer:
            '<a href="#!" id="insertwithoutcheck"> Insert without check conflict</a>'
    }).
    then((result) => {
        if (result.value) {
            $("#img").fadeIn();
            window.location = "/today/today-to-actual-with-conflict";
        }
    })
});

$('.checkConflicts').click(function() {
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
                toastr.info('check conflict data today schedule : '+ $("#idTypeMasterPlan option:selected").text())
                // execute to ADA GNAT
                $.post({
                    url: "/api/v2/ts1000/checkConflict",
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
                    $.get("/api/v2/ts1000/getAllConflict", function (dataArray) {
                        // init var datatable
                        let dataTable = $('#data_today_conflict').DataTable();
                        // refresh / remove
                        dataTable.row().remove().draw()
                        if(Object.entries(dataArray).length==0){
                            dataTable.row().remove().draw()
                        }else{
                            for (let i in dataArray) {
                                dataTable.row.add([
                                    dataArray[i].todayA.train.noka,
                                    dataArray[i].todayB.train.noka,
                                    "Peron No " + dataArray[i].todayA.peronFrom.noPeron,
                                    dataArray[i].todayA.peronFrom.station.nameStation,
                                    dataArray[i].todayA.arrival,
                                    dataArray[i].todayB.arrival,
                                    dataArray[i].todayA.depart,
                                    dataArray[i].todayB.depart,
                                    dataArray[i].todayA.ruteRole.nameRoute,
                                    dataArray[i].todayB.ruteRole.nameRoute,
                                    dataArray[i].todayA.typeMasterPlan.nameTypeMasterPlan,
                                    dataArray[i].todayB.typeMasterPlan.nameTypeMasterPlan
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

