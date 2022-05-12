$(document).ready(function () {
    // init get peron default
    getPeron();
    // init delay ars
    $("#delayArs").attr("disabled", true)
    $("#platformOnly, #delayOnly").change(function () {
        if ($("#platformOnly").is(":checked")) {
            $("#idPeron").attr("disabled", false);
            $("#delayArs").attr("disabled", true);
        } else if ($("#delayOnly").is(":checked")) {
            $("#idPeron").attr("disabled", true);
            $("#delayArs").attr("disabled", false);
        }
    });
});

function getPeron() {
    let idStation = document.getElementById("idStations").value;
    // empty the Station
    $('#idPeron').empty();
    // get first data dropdown
    $('#idPeron').find('option').not(':first');
    // get API value
    $.get("/api/v1/ts1000/peron/" + idStation, function (dataArray) {
        for (let i in dataArray) {
            console.log(dataArray[i])
            $("#idPeron").append('<option ' +
                'data-tickIcon="fa-check" ' +
                'data-icon="fa fa-route" ' +
                'value="' + dataArray[i].idPeron + '">' + 'Peron - ' + dataArray[i].noPeron + '</option>');
        }
        $("#idPeron").selectpicker("refresh");
    });
}
