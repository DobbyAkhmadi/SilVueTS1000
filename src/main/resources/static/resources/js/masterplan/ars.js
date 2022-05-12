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
                    url: "/api/v1/ts1000/ars/enable",
                    success: function (data){
                        console.log('ok');
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
                           url: "/api/v1/ts1000/ars/disable",
                           success: function (data){
                               console.log("DISABLE");
                           }
                     } );

                  } else if (result.dismiss === Swal.DismissReason.cancel){
                  $('#enableArsBtn').bootstrapToggle("on");
                  }
            })
    }

});

$(document).ready(function() {
    $('.navArs').addClass('active');
    //var CSRF_TOKEN = $('meta[name="csrf-token"').attr('content');

    // var refreshIntervalId = setInterval(checkArsConflict,3000);

//    $("#delayArs").attr("disabled", true)
//    $("#platformOnly, #delayOnly").change(function () {
//        if ($("#platformOnly").is(":checked")) {
//            $("#noPeron").attr("disabled", false);
//            $("#delayArs").attr("disabled", true);
//        } else if ($("#delayOnly").is(":checked")) {
//            $("#noPeron").attr("disabled", true);
//            $("#delayArs").attr("disabled", false);
//        }
//    });


//    function checkArsConflict(){
//          $.post({
//                 url: "/ars/checkArsConflict",
//                 success: function (response){
//                    console.log(response);
//                    var len = 0;
//                    if (response != null) {
//                        len = response.length;
//                    }
//
//                    if (len > 0) {
//                        // Read data and create <option >
//                        for (var i = 0; i < len; i++) {
//
//                            var idArsConflict = response[i]['idArsConflict'];
//                            var idArsSchedule = response[i]['arsSchedule']['idArsSchedule'];
//                            var noka = response[i]['arsSchedule']['train']['noka'];
//                            var noPeron = response[i]['arsSchedule']['peronFromArs']['noPeron'];
//                            var nameStation = response[i]['arsSchedule']['peronFromArs']['station']['nameStation'];
//                            var departArs = response[i]['arsSchedule']['departArs'];
//                            var track = response[i]['routeStick']['track'];
//
//                            $('#idArsConflict').val(idArsConflict);
//                            $('#idArsSchedule').val(idArsSchedule);
//                            $('#noka').val(noka);
//                            $('#noPeron').val(noPeron);
//                            $('#nameStation').val(nameStation);
//                            $('#departArs').val(departArs);
//                            $('#track').val(track);
//                            $('#modalModifyDataArs').modal();
//                            clearInterval(refreshIntervalId);
//                        }
//                    }
//                        }
//                 });
//    };
});

// setInterval(function(){
//      // $("#arstable").load(location.href + " #arstable>*", "");
//      $.get({
//          url: "/api/v1/ts1000/ars/getalldata",
//          success: function(data) {
//              $("#page_idx").empty();
//              $.each(data, function(k, v) {
//                  var row = "<td>" + v.train.noka + ""</td>"
//                  + "<td th:text="${sch.peronFromArs?.noPeron}">" + + "</td>
//                  <td th:text="${sch.departArs}"></td>
//                  <td th:text="${sch.peronFromArs?.Station?.nameStation}"></td>
//                  <td th:text="${sch.ruteRole?.nameRoute}"></td>
//                  <td th:if="${sch.scheduleStatusArs} == '1'" th:text="|Done|"></td>
//                  <td th:if="${sch.scheduleStatusArs} == '0'" th:text="|Running|"></td>
//              });
//         }
//      });
//  }, 8000);
