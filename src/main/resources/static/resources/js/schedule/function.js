//=========================================================
//Default Navigation
//=========================================================
$(document).ready(function() {
    $('.navSchedule').addClass('active');
});
//=========================================================
//Default Validation Next Day
//=========================================================
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
//=========================================================
// Get Peron From Station ID
//=========================================================
$('#JSAddStation').change(function() {
    // get Station ID
    var idStation = $(this).children(":selected").attr("value");
    // Empty the dropdown
    $('#FromAddPeron').empty();
    // get first data dropdown
    $('#FromAddPeron').find('option').not(':first');
    $.get("/api/v1/ts1000/peron/"+idStation, function(dataArray) {
        var peron = "Peron No "
        for (let i in dataArray) {
            console.log(dataArray[i]);
            $("#FromAddPeron").append('<option data-tickIcon="fa-check" data-icon="fa fa-road" value="'+dataArray[i].idPeron+'" selected="">'+peron+dataArray[i].noPeron+'</option>');
        }
        $("#FromAddPeron").selectpicker("refresh");
    });
});
//=========================================================
//Get Station From Type Sch Train Rute Role
//=========================================================
var trainVal=0;
var typeMasterPlanVal=0;
var ruteRoleVal=0;
$(document).ready(function() {
    $('#JSAddTrain').change(function() {
        trainVal = $(this).children(":selected").attr("value");
    });
    $('#JSAddRuteRole').change(function() {
        ruteRoleVal = $(this).children(":selected").attr("value");
    });
    $('#JSAddTypeMasterPlan').change(function() {
        typeMasterPlanVal = $(this).children(":selected").attr("value");
        if (trainVal!=0 && typeMasterPlanVal!=0 && ruteRoleVal!=0)
        {
            // empty the Station
            $('#JSAddStation').empty();
            // get first data dropdown
            $('#JSAddStation').find('option').not(':first');
            // get API value
            $.get( "/api/v1/ts1000/station", function(dataArray) {
                for (let i in dataArray) {
                    $("#JSAddStation").append('<option data-tickIcon="fa-check" data-icon="fa fa-hotel" value="'+dataArray[i].idStation+'" selected="">'+dataArray[i].nameStation+'</option>');
                }
                $("#JSAddStation").selectpicker("refresh");
            });
        }
    });
});

