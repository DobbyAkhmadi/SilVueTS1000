//Data Line Relation
$(function () {
    $("#data_line").DataTable({
        "paging": true,
        "lengthChange": false,
        "searching": true,
        "ordering": false,
        "info": true,
        "autoWidth": false,
    });
});

var station_data = [];
var location_data = [];

$(document).ready(function(){
    $('.add_station_data').click(function(){
        $("#station_name").hide();
        var get_station = $("#station_input").val();
        var get_location = $("#station_loc").val();
        var get_unit = $("#loc_unit").val();

        var get_location_m;
        if (get_unit == "kilometer")
        {
            get_location_m = get_location * 1000;
        }
        else
        {
            get_location_m = get_location * 1;
        }

        var stationfilter = station_data.includes(get_station);
        console.log(stationfilter);
        var locationfilter = location_data.includes(get_location_m);
        console.log(locationfilter);
        var varData = "<div class='row'><div class='col-sm-6'><input type='text' class='form-control station_data' name='station[]' value='"+get_station+"'readonly style='margin-bottom: 10px;'></div>  <div class='col-sm-3'><input type='text' class='form-control unit_data' name='unit[]' value='"+get_unit+"'readonly style='margin-bottom: 10px;'></div>  <div class='col-sm-3'><input type='text' class='form-control location_data' name='location[]' value='"+get_location+"'readonly style='margin-bottom: 10px;'></div></div>";

        if (stationfilter == true || locationfilter == true){
            Swal.fire(
                'Error !',
                'Station or location data already exists',
                'error'
            )
        }
        else if (get_station == "" || get_location == "")
        {
            Swal.fire(
                'Error !',
                'Data is null',
                'error'
            )
        }
        else
        {
            station_data.push(get_station);
            location_data.push(get_location_m);

            $("#station_list").append(varData);
        }
    })
});



//mendapatkan data dan menampilkan halaman modify linerelation
$(document).on('click', '.edit-modal-line', function() {
    window.location = "/linerelation/index/" + $(this).data('idline');
});

//Mendapatakan data line name saat menambahkan stasiun baru pada line
$(document).on('click', '.add_linestation', function() {
    $("#linestation_line_name").val(nameLine);
    // $("#linestation_loc_unit").val(listLineUnit[0]);

    $('#Modaladdlinestation').modal('show');
});

//Menambahkan list data stasiun dan lokasinya pada saat modify line
var linestation_data = [];
var linestation_location_data = [];

// $(document).ready(function(){
//     $('.navSubmit').addClass('active');
//     $("#linestation_name").hide();
//
//     listLineStation.forEach(
//         function linestationData(value, index)
//         {
//             linestation_data.push(value);
//             linestation_location_data.push(listLineLocation[index]);
//             var prevData = "<div class='row'><div class='col-sm-6'><input type='text' class='form-control' name='linestation[]' value='"+value+"'readonly style='margin-bottom: 10px;'></div>  <div class='col-sm-3'><input type='text' class='form-control' name='linestation_unit[]' value='"+listLineUnit[index]+"'readonly style='margin-bottom: 10px;'></div>  <div class='col-sm-3'><input type='text' class='form-control' name='linestation_location[]' value='"+listLineLocation[index]+"' readonly style='margin-bottom: 10px;'></div></div>";
//             $("#linestation_list").append(prevData);
//         }
//     );
//
//     $('.add_linestation_data').click(function(){
//         var get_linestation = $("#linestation_input").val();
//         var get_linestation_loc = $("#linestation_loc").val();
//         var get_linestation_loc_unit = $("#linestation_loc_unit").val();
//         var get_linestation_loc_m;
//
//         if (get_linestation_loc_unit == "kilometer")
//         {
//             get_linestation_loc_m = get_linestation_loc * 1000;
//         }
//         else{
//             get_linestation_loc_m = get_linestation_loc * 1;
//         }
//
//         var stationfilter = linestation_data.includes(get_linestation);
//         var locationfilter = linestation_location_data.includes(get_linestation_loc_m);
//
//         var varData = "<div class='row'><div class='col-sm-6'><input type='text' class='form-control' name='linestation[]' value='"+get_linestation+"'readonly style='margin-bottom: 10px;'></div>  <div class='col-sm-3'><input type='text' class='form-control' name='linestation_unit[]' value='"+get_linestation_loc_unit+"'readonly style='margin-bottom: 10px;'></div>  <div class='col-sm-3'><input type='text' class='form-control' name='linestation_location[]' value='"+get_linestation_loc+"'readonly style='margin-bottom: 10px;'></div></div>";
//         //var prevData= "<div class='row'><div class='col-sm-6'><input type='text' class='form-control linestation_data' name='linestation[]' value='"+get_linestation+"'readonly style='margin-bottom: 10px;'></div>  <div class='col-sm-3'><input type='text' class='form-control linestation_unit_data' name='linestation_unit[]' value='"+get_linestation_loc_unit+"'readonly style='margin-bottom: 10px;'></div>  <div class='col-sm-3'><input type='text' class='form-control linestation_location_data' name='linestation_location[]' value='"+get_linestation_loc+"'readonly style='margin-bottom: 10px;'></div></div>";
//
//         if (stationfilter == true || locationfilter == true){
//             Swal.fire(
//                 'Error !',
//                 'Station or location data already exists',
//                 'error'
//             )
//         }
//         else{
//             linestation_data.push(get_linestation);
//             linestation_location_data.push(get_linestation_loc_m);
//             //console.log(varData);
//             $("#linestation_list").append(varData);
//         }
//     })
// });

//Mendapatkan data dan menampilkan linestation modify modal
$(document).on('click', '.modify-linestation-modal', function() {
    $("#edit_linerelation_id").val($(this).data('idlinerelation'));
    $("#edit_line_name").val($(this).data('linename'));
    $("#edit_line_station").val($(this).data('station'));
    $("#edit_loc_unit").val($(this).data('unit'));
    $("#edit_station_loc").val($(this).data('location'));

    $('#Modaleditline').modal('show');
});

//delete line data
$('.deleteid_line').click(function(){
    var delete_id = $(this).attr('data-idline');
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
        toastr.info('Deleting line relation data.')
        window.location = "/linerelation/delete/" + delete_id;
        }
    })
});

//delete line station data
$('.delete-linestation').click(function(){
    var delete_id = $(this).attr('data_idlinerelation');
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
        toastr.info('Deleting line station data.')
        window.location = "/linerelation/deletelinestation/" + delete_id;
        }
    })
});

//loading_add_line
// $(document).on('click', '.loading_line_add', function(){
//     var data = $('#line_name').val();
//     if (data != "") {
//         $("#img").fadeIn();
//         toastr.info('Adding data line relation.')
//     }
// });

//loading_edit_line
$(document).on('click', '.loading_line_edit', function(){
    var data = $('#line_name').val();
    if (data != "") {
        $("#img").fadeIn();
        toastr.info('Modifying data line relation.')
    }
});

//loading_import
$(document).on('click', '.loading_lineroute_import', function() {
    $("#img").fadeIn();
    toastr.info('Importing line and route data...')
});

//Export line route data
$(document).on('click', '#export_line_route', function() {
    window.location = "/linerelation/exportLineRoute";
});

$('#Modaladdrutelist').on('hide.bs.modal', function () {
    $("input").removeClass("is-invalid");
    });

$('#Modaleditline').on('hide.bs.modal', function () {
    $("input").removeClass("is-invalid");
    });
