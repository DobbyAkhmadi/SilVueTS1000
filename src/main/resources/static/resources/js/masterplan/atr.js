$(function() {
    $("#page_idx").DataTable({
        "paging": true,
        "lengthChange": false,
        "searching": true,
        "ordering": false,
        "info": true,
        "autoWidth": false,
    });
});

$("#updateArsBtn").click(function () {
    Swal.fire({
        title: 'Are you sure',
        text: "to update it now?",
        type: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Yes, update it!'
    }).then((result) => {
        if (result.value) {
         console.log('ok');
         window.location = "/ars/insert-schedule-command";
//
//                  function toIsoString(date) {
//                      var tzo = -date.getTimezoneOffset(),
//                          dif = tzo >= 0 ? '+' : '-',
//                          pad = function(num) {
//                              var norm = Math.floor(Math.abs(num));
//                              return (norm < 10 ? '0' : '') + norm;
//                          };
//
//                      return date.getFullYear() +
//                          '-' + pad(date.getMonth() + 1) +
//                          '-' + pad(date.getDate()) +
//                          'T' + pad(date.getHours()) +
//                          ':' + pad(date.getMinutes()) +
//                          ':' + pad(date.getSeconds());
//                    }
//                  var updateTime = new Date();
//                  var updateTimeIso = toIsoString(updateTime);
//                  console.log(updateTimeIso);
//        $.post({
//        url: "/api/v1/ts1000/auto/updateArsBtn",
//        data: {
//               lastUpdateArs:updateTime
//        },
//        success: function (data){
//                    console.log(data);
//                 }
//        });
        }
    })
});

$("#toggleWrapperArs").click(function () {
    var arsStatus = document.getElementById('enableArsTemp').value;
    if (arsStatus == ''){
        arsStatus = 'off';
        };
    var on = 'on';
    var off = 'off';
    console.log(arsStatus);
    if(arsStatus == off){
        Swal.fire({
            title: 'Are you sure',
            text: "to enable ARS now?",
            type: 'warning',
            allowOutsideClick: false,
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Yes, enable it!'
        }).then((result) => {
              if (result.value) {
                    $.post({
                    url: "/api/v1/ts1000/auto/updateArsEnable",
                    data: {
                        arsStatusEnable:on
                    },
                    success: function (data){
                        console.log('ok');
                        window.location = "/ars/send-ars-enable";
                    }
                } );
               } else if (result.dismiss === Swal.DismissReason.cancel){
                   $('#enableArsBtn').bootstrapToggle("off");
               }
        })
    }else{
        Swal.fire({
                title: 'Are you sure',
                text: "to disable ARS now?",
                type: 'warning',
                showCancelButton: true,
                allowOutsideClick: false,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Yes, disable it!'
            }).then((result) => {
                if (result.value) {
                     $.post({
                           url: "/api/v1/ts1000/auto/updateArsEnable",
                           data: {
                                 arsStatusEnable:off
                           },
                           success: function (data){
                                 window.location = "/ars/send-ars-disable";
                           }
                     } );

                  } else if (result.dismiss === Swal.DismissReason.cancel){
                  $('#enableArsBtn').bootstrapToggle("on");
                  }
            })
    }

});

$(document).ready(function() {
    $('.navAtr').addClass('active');
    var CSRF_TOKEN = $('meta[name="csrf-token"').attr('content');

    var refreshIntervalId = setInterval(checkArsConflict,3000);

    $("#delayArs").attr("disabled", true)
    $("#platformOnly, #delayOnly").change(function () {
        if ($("#platformOnly").is(":checked")) {
            $("#peron").attr("disabled", false);
            $("#delayArs").attr("disabled", true);
        } else if ($("#delayOnly").is(":checked")) {
            $("#peron").attr("disabled", true);
            $("#delayArs").attr("disabled", false);
        }
    });


    function checkArsConflict(){
          $.post({
                 url: "/ars/checkArsConflict",
                 success: function (response){
                    console.log(response);
                    var len = 0;
                    if (response != null) {
                        len = response.length;
                    }

                    if (len > 0) {
                        // Read data and create <option >
                        for (var i = 0; i < len; i++) {

                            var idConflict = response[i]['idArsConflict'];
                            var idArs = response[i]['arsSchedule']['idArsSchedule'];
                            var peronArs = response[i]['arsSchedule']['peronFromArs']['noPeron'];
                            var departArs = response[i]['arsSchedule']['departArs'];
                            var trackArs = response[i]['routeStick']['track'];

                            $('#idConflict').val(idConflict);
                            $('#idars').val(idArs);
                            $('#peron').val(peronArs);
                            $('#depart').val(departArs);
                            $('#idRouteStick').val(trackArs);
                            $('#modalModifyDataArs').modal();
                            clearInterval(refreshIntervalId);
                        }
                    }
                        }
                 });
    };


});

setInterval(function(){
     $("#arstable").load(location.href + " #arstable>*", "");
 }, 8000);
