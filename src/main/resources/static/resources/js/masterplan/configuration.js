//submit_table
$(function() {
    $("#data_train").DataTable({
        "paging": true,
        "lengthChange": false,
        "searching": true,
        "ordering": false,
        "info": true,
        "autoWidth": false,
    });
});

$(document).ready(function() {
    $('.navSettings').addClass('active');
    var CSRF_TOKEN = $('meta[name="csrf-token"').attr('content');

    // STATION Change
    $('#intervalTime').change(function() {
        // STATION id
        var id = $(this).val();
        $.ajax({
            url: '/gapeka/updatePrint',
            type: 'GET',
            dataType: 'json',
            data: {
                printhours: id
            },
            success: function(response) {

            },
            error: function(xhr, ajaxOptions, thrownError) {}
        });
    });

    $("#planbase").change(function () {
        var id = $(this).val();
        if (id == 'rute') {
            document.getElementById('typerute').disabled = false;
            document.getElementById('typeline').disabled = true;
            var val = document.getElementById('typerute').value;
            $.ajax({
                url: '/config/updateRute',
                type: 'GET',
                dataType: 'json',
                data: {
                    value: val
                },
                success: function(response) {

                },
                error: function(xhr, ajaxOptions, thrownError) {}
            });

        } else if (id == 'line') {
            document.getElementById('typeline').disabled = false;
            document.getElementById('typerute').disabled = true;
            var val = document.getElementById('typeline').value;
            $.ajax({
                url: '/config/updateLine',
                type: 'GET',
                dataType: 'json',
                data: {
                    value: val
                },
                success: function(response) {

                },
                error: function(xhr, ajaxOptions, thrownError) {}
            });
        }
        var id = $(this).val();
        $.ajax({
            url: '/config/updatePlan',
            type: 'GET',
            dataType: 'json',
            data: {
                value: id
            },
            success: function(response) {

            },
            error: function(xhr, ajaxOptions, thrownError) {}
        });
    });

    $("#typerute").change(function () {
        var id = $(this).val();
        $.ajax({
            url: '/config/updateRute',
            type: 'GET',
            dataType: 'json',
            data: {
                value: id
            },
            success: function(response) {

            },
            error: function(xhr, ajaxOptions, thrownError) {}
        });
    });

    $("#typeline").change(function () {
        var id = $(this).val();
        $.ajax({
            url: '/config/updateLine',
            type: 'GET',
            dataType: 'json',
            data: {
                value: id
            },
            success: function(response) {

            },
            error: function(xhr, ajaxOptions, thrownError) {}
        });
    });

    $("#rangeA").change(function () {
        var id = $(this).val();
        $.ajax({
            url: '/config/updaterangea',
            type: 'GET',
            dataType: 'json',
            data: {
                value: id
            },
            success: function(response) {

            },
            error: function(xhr, ajaxOptions, thrownError) {}
        });
    });

    $("#rangeB").change(function () {
        var id = $(this).val();
        $.ajax({
            url: '/config/updaterangeb',
            type: 'GET',
            dataType: 'json',
            data: {
                value: status
            },
            success: function(response) {

            },
            error: function(xhr, ajaxOptions, thrownError) {}
        });
    });

    // $('#colorchange').change(function() {
    //     // STATION id
    //     var id = $(this).val();
    //     console.log(id);
    //     value = $(this).data('id');
    //     console.log(value);
    // });
});

function colorChange(selectObject) {
    var value = selectObject.value;
    var id = selectObject.id;
    $.ajax({
        url: '/gapeka/updateColor',
        type: 'GET',
        dataType: 'json',
        data: {
            idcolorprint: id,
            colorprint: value
        },

        success: function(response) {

        },
        error: function(xhr, ajaxOptions, thrownError) {}
    });
}

// $('.applySetting').click(function() {
//     $("#img").fadeIn();
// });

// $(document).ready(function(){

// function loop() {
//     if (time === time) {
//         //$("#autoUpdate").trigger("click");
//         console.log(updateTime);
//     }
//     now = new Date();                  // allow for time passing
//     var delay = 60000 - (now % 60000); // exact ms to next minute interval
//     setTimeout(loop, delay);
// }
// });

$("#autoenable").on("change", function () {
    var status = $(this).prop('checked');
    if (this.checked) {
        document.getElementById('autoactual').disabled = false;
        document.getElementById('autoactualbtn').disabled = false;
        $('#autoactualtime').show( );
    }
    else{
        document.getElementById('autoactual').disabled = true;
        document.getElementById('autoactualbtn').disabled = true;
        $('#autoactualtime').hide( );
    };
    $.ajax({
                    url: '/config/autoenable',
                    type: 'GET',
                    dataType: 'json',
                    data: {
                        value: status
                    },
                    success: function(response) {

                    },
                    error: function(xhr, ajaxOptions, thrownError) {}
                });
});

$(document).ready(function(){
    var btnState = document.getElementById("act").value;
    if (btnState=='true'){
        $('#autoenable').bootstrapToggle('on')
    }else {
    $('#autoenable').bootstrapToggle('off')
    }
    //var updateTime = document.getElementById("autoactualshow").value;
});







