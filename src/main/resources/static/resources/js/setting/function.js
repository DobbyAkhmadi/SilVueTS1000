// navigation default
$(document).ready(function() {
    $('.navSettings').addClass('active');
});

$(document).ready(function(){
    if (document.getElementById("autoEnable").checked){
        $('#autoEnable').bootstrapToggle('on')
    }
    else if(!document.getElementById("autoEnable").checked)
    {
        $('#autoEnable').bootstrapToggle('off')
    }
});

$("#autoactualbtn").click(function () {
    var autoUpdate = document.getElementById('autoEnable').value;
    var autoActual = document.getElementById('autoactual').value;
    $.post({
        url: "/api/v1/ts1000/auto/autoUpdate",
        data: {
            autoUpdateEnable:autoUpdate,
            autoUpdateActual:autoActual,
        },
        success: function (data){
            console.log(data);
        }
    } );
//    document.getElementById('autoactual').value=null;
    location.reload();
});


$("#autoEnable").change(function () {
    var strVal;
    if (this.checked) {
        document.getElementById('autoactual').disabled = false;
        document.getElementById('autoactualbtn').disabled = false;
        $('#autoactualtime').show( );
        strVal= document.getElementById("autoEnableTemp").value="on"
    }
    else
    {
        document.getElementById('autoactual').disabled = true;
        document.getElementById('autoactualbtn').disabled = true;
        $('#autoactualtime').hide( );
        strVal= document.getElementById("autoEnableTemp").value="off"
    };
    $.post({
        url: "/api/v1/ts1000/auto/updateEnable",
        data: {
            autoUpdateEnable:strVal
        },
        success: function (data){
            console.log(data);
        }
    } );
});

// default value
document.getElementById('typeRute').disabled = false;
document.getElementById('typeLine').disabled = true;
$("#planBase").change(function () {
        var planBase = this.value;
        var varHiddenValue = document.getElementById("planBaseTemp").value=planBase;
        if (planBase == 'rute') {
            document.getElementById('typeRute').disabled = false;
            document.getElementById('typeLine').disabled = true;
        } else if (planBase == 'line') {
            document.getElementById('typeLine').disabled = false;
            document.getElementById('typeRute').disabled = true;
        }

        $.post({
            url: "/api/v1/ts1000/auto/updateBase",
            data: {
                tdgBase:varHiddenValue
            },
            success: function (data){
                console.log(data);
            }
        } );
});

$("#typeLine").change(function () {
    var line = this.value;
    var varHiddenValue = document.getElementById("typeLineTemp").value=line;
    $.post({
         url: "/api/v1/ts1000/auto/updateLine",
         data: {
             tdgLine:varHiddenValue
         },
         success: function (data){
             console.log(data);
         }
    } );
});

$("#typeRute").change(function () {
    var rute = this.value;
    var varHiddenValue = document.getElementById("typeRuteTemp").value=rute;
    $.post({
        url: "/api/v1/ts1000/auto/updateRute",
        data: {
            tdgRute:varHiddenValue
        },
        success: function (data){
            console.log(data);
        }
    } );
});

$("#rangeA").change(function () {
    var rangeA = this.value;
    var varHiddenValue = document.getElementById("rangeATemp").value=rangeA;
    $.post({
        url: "/api/v1/ts1000/auto/updateRangeA",
        data: {
            tdgRangeALive:varHiddenValue
        },
        success: function (data){
            console.log(data);
        }
    } );
});

$("#rangeB").change(function () {
    var rangeB = this.value;
    var varHiddenValue = document.getElementById("rangeBTemp").value=rangeB;
    $.post({
        url: "/api/v1/ts1000/auto/updateRangeB",
        data: {
            tdgRangeBLive:varHiddenValue
        },
        success: function (data){
            console.log(data);
        }
    } );
});

$("#intervalTime").change(function () {
    var intervalTime = this.value;
    var varHiddenValue = document.getElementById("intervalTimeTemp").value=intervalTime;
    $.post({
        url: "/api/v1/ts1000/auto/updateIntervalPrintHours",
         data: {
             printHours:varHiddenValue
         },
        success: function (data){
             console.log(data);
         }
    } );
});


//delete type schedule
$('.deleteTypeSchedule').click(function(){
    var delete_id = $(this).attr('deleteTypeScheduleId');
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
        window.location = "/setting/settings/delete-schedule-type/" + delete_id;
        }
    })
});

//delete all data route by id
$('.deleteRouteList').click(function(){
    var delete_id = $(this).attr('deleteRouteDetailById');
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
            toastr.info('Deleting data route.')
            window.location = "/setting/settings/delete-route-relation/" + delete_id;
        }
    })
});

//delete all data route by id
$('.deleteLineList').click(function(){
    var delete_id = $(this).attr('deleteLineDetailById');
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
            toastr.info('Deleting data Line.')
            window.location = "/setting/settings/delete-line-relation/" + delete_id;
        }
    })
});



