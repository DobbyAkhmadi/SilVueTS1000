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
