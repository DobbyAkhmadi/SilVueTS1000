//route_table 
$(function () {
    $("#data_route").DataTable({
        "paging": true,
        "lengthChange": false,
        "searching": true,
        "ordering": false,
        "info": true,
        "autoWidth": false,
    });
});

var station_route_data = [];
var location_route_data = [];
//menambah list station dalam rute

$(document).ready(function(){
    $('.add_station_data_rute').click(function(){
        $("#station_name_rute").hide();
        var get_route_station = $("#station_input_rute").val();
        var get_route_location = $("#station_loc_rute").val();
        var get_route_location_m;
        var get_route_unit = $("#loc_unit_rute").val();
        
        if(get_route_unit == "kilometer"){
            get_route_location_m = get_route_location * 1000;
            
        }else{
            get_route_location_m = get_route_location * 1;
        }
        var stationfilter = station_route_data.includes(get_route_station);
        var locationfilter = location_route_data.includes(get_route_location_m);

        var varRouteData = "<div class='row'><div class='col-sm-6'><input type='text' class='form-control station_data' name='station[]' value='"+get_route_station+"'readonly style='margin-bottom: 10px;'></div>  <div class='col-sm-3'><input type='text' class='form-control unit_data' name='unit[]' value='"+get_route_unit+"'readonly style='margin-bottom: 10px;'></div>  <div class='col-sm-3'><input type='text' class='form-control location_data' name='location[]' value='"+get_route_location+"'readonly style='margin-bottom: 10px;'></div></div>";

        if (stationfilter == true || locationfilter == true){
            Swal.fire(
                'Error !',
                'Station or location data already exists',
                'error'
            )
        }else if (get_route_station == "" || get_route_location == ""){
            Swal.fire(
                'Error !',
                'Data Null',
                'error'
            )
        }
        else{
            station_data.push(get_route_station);
            location_data.push(get_route_location_m);
            //console.log(location_data);
            $("#station_list_rute").append(varRouteData);
        }
    })


    //harus nya disini ada masukin data
    //ntar kucek

});

//mendapatkan data dan tampilkan edit modal
$(document).on('click', '.edit-modal-route', function() {
    $('#edit_id_route').val($(this).data('idrute'));
    $('#edit_name_route').val($(this).data('namerute'));
    id = $('#edit_id_route').val();
    $('#Modaleditroute').modal('show');
});
 

//delete data + loading
$('.deleteid_route').click(function(){
    var delete_id = $(this).attr('delete_data_route_id');
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
        toastr.info('Deleting data route relation.')
        window.location = "/ruterelation/delete/" + delete_id;
        }
    })
});

//loading_add_route
$(document).on('click', '.loading_route_add', function(){
    var data = $('#name_route').val();
    if (data != "") {
        $("#img").fadeIn();
        toastr.info('Adding data route relation.')
    }
});

//loading_edit_route
$(document).on('click', '.loading_route_edit', function(){
    var data = $('#name_route').val();
    if (data != "") {
        $("#img").fadeIn();
        toastr.info('Modifying data route relation.')
    }
});

//edit route data
$(document).on('click', '.modify-rutestation-modal', function(){

    $('#idruterelationedit').val($(this).data('idruterelation'));
    $('#namerouteedit').val($(this).data('rutename'));
    $('#rutestationedit').val($(this).data('station'));
    $('#stationlocedit').val($(this).data('location'));
    $('#locunitedit').val($(this).data('unit'));
    $('#Modaleditrute').modal('show');
});
$(document).on('click', '.edit-modal-rute', function() {
    window.location = "/ruterelation/index/" + $(this).data('idrute');
});

//delete data detail
$('.delete-rutestation-detail').click(function(){
    var delete_id = $(this).data('idruterelation');
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
        toastr.info('Deleting data route relation.')
        window.location = "/ruterelation/deleterutestation/" + delete_id;
        }
    })
});

// $(document).ready(function () {
//     $('.navSubmit').addClass('active');
//     $('#namerouteedit').val(nameRoute);
//     station_data = [];
//     location_data = [];
//     listStation.forEach(
//         function tampil(element, index, array) {
//             station_data.push(element);
//             location_data.push(listLocation[index]);
//             var varData = "<div class='row'><div class='col-sm-6'><input type='text' class='form-control station_data' name='station[]' value='"+element+"'readonly style='margin-bottom: 10px;'></div>  <div class='col-sm-3'><input type='text' class='form-control unit_data' name='unit[]' value='"+listUnit[index]+"'readonly style='margin-bottom: 10px;'></div>  <div class='col-sm-3'><input type='text' class='form-control location_data' name='location[]' value='"+listLocation[index]+"'readonly style='margin-bottom: 10px;'></div></div>";
//             $("#station_list_rute").append(varData);
           
//         }
//     );
//     //console.log(nameRoute);
//     //console.log(location_data);

// });


$(document).on('click', '.add_rutestation', function() {
    
    $('#routestation_route_name').val(nameRoute);
    $('#routestation_loc_unit').val(listUnit[0]);

    $('#Modaladdroutestation').modal('show');
});

//Menambahkan list data stasiun dan lokasinya pada saat modify route
var routestation_data = [];
var routestation_location_data = [];

// $(document).ready(function(){
//     $('.navSubmit').addClass('active');
//     $("#routestation_name").hide();
//     listStation.forEach(
//         function routestationData(value, index)
//         {
//             routestation_data.push(value);
//             routestation_location_data.push(listLocation[index]);
//             var prevData = "<div class='row'><div class='col-sm-6'><input type='text' class='form-control routestation_data' name='routestation[]' value='"+value+"'readonly style='margin-bottom: 10px;'></div>  <div class='col-sm-3'><input type='text' class='form-control routestation_unit_data' name='routestation_unit[]' value='"+listUnit[index]+"'readonly style='margin-bottom: 10px;'></div>  <div class='col-sm-3'><input type='text' class='form-control routestation_location_data' name='routestation_location[]' value='"+listLocation[index]+"'readonly style='margin-bottom: 10px;'></div></div>";
//             $("#routestation_list").append(prevData);
//         }
//     );
//
//     $('.add_routestation_data').click(function(){
//         var get_routestation = $("#routestation_input").val();
//         var get_routestation_loc = $("#routestation_loc").val();
//         var get_routestation_loc_unit = $("#routestation_loc_unit").val();
//
//         var stationfilter = routestation_data.includes(get_routestation);
//         var locationfilter = routestation_location_data.includes(get_routestation_loc*1);
//
//         var varData = "<div class='row'><div class='col-sm-6'><input type='text' class='form-control routestation_data' name='routestation[]' value='"+get_routestation+"'readonly style='margin-bottom: 10px;'></div>  <div class='col-sm-3'><input type='text' class='form-control routestation_unit_data' name='routestation_unit[]' value='"+get_routestation_loc_unit+"'readonly style='margin-bottom: 10px;'></div>  <div class='col-sm-3'><input type='text' class='form-control routestation_location_data' name='routestation_location[]' value='"+get_routestation_loc+"'readonly style='margin-bottom: 10px;'></div></div>";
//
//         if (stationfilter == true || locationfilter == true){
//             Swal.fire(
//                 'Error !',
//                 'Station or location data already exists',
//                 'error'
//             )
//         }
//         else{
//             routestation_data.push(get_routestation);
//             routestation_location_data.push(get_routestation_loc*1);
//
//             $("#routestation_list").append(varData);
//         }
//     })
// });

$('#Modaladdrutelist').on('hide.bs.modal', function () {
    $("input").removeClass("is-invalid");
    });

$('#Modaleditrute').on('hide.bs.modal', function () {
    $("input").removeClass("is-invalid");
    });
