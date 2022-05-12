//=========================================================
//Default Navigation
//=========================================================
$(document).ready(function() {
    $('.navSettings').addClass('active');
});
//=========================================================
//Default Init Loading
//=========================================================
$(document).ready(function(){
    $("#img").hide()
    $("#load").hide()
});
//=========================================================
//Add Route Station Validation
//=========================================================
var arrayRouteTr = [];
var arrayRouteDir = [];
var arrayRouteLoc = [];
function btnFunctionAddRoute() {
    let table = document.getElementById("stationListAddRoute");
    let stationText = $("#stationRoute option:selected").text();
    let stationId = document.getElementById("stationRoute").value;
    let directionId = document.getElementById("directionIdRoute").value;
    let location = document.getElementById("locationRoute").value;
    if (stationText != "" & directionId != "" & location !="")
    {
        let row = table.insertRow(1);
        let cell1 = row.insertCell(0);
        let cell2 = row.insertCell(1);
        let cell3 = row.insertCell(2);
        cell1.innerHTML = stationText;
        cell2.innerHTML = directionId;
        cell3.innerHTML = location;
        arrayRouteTr.push(stationId);
        arrayRouteDir.push(directionId);
        arrayRouteLoc.push(location);
    }
    else
    {
        toastr.warning("Data Station Or Location Can't Be Null");
    }
}
//=========================================================
//Send Route Station API
//=========================================================
function sendArrayRoute(){
    let chkAutoRoute = document.getElementById("chkRoute").checked;
    console.log("chkAuto: " + chkAutoRoute);
    if (chkAutoRoute==true)
    {
        $.post({
            url: "/api/v1/ts1000/auto/insertRouteAuto",
            data: {
                ArrayRouteTrainId:arrayRouteTr,
                ArrayRouteDirection:arrayRouteDir,
                ArrayRouteLocation:arrayRouteLoc,
            },
            success: function (data){
                console.log(data);
            }
        } );
    }
    else
    {
        // check existing Route
        let nameRoute = document.getElementById("idNewRoute").value;
        let routeId = document.getElementById("ruteRole").value;
        if (routeId!="")
        {
            // create by existing id
            $.post({
                url: "/api/v1/ts1000/auto/insertRouteById",
                data: {
                    routeId:routeId,
                    ArrayRouteTrainId:arrayRouteTr,
                    ArrayRouteDirection:arrayRouteDir,
                    ArrayRouteLocation:arrayRouteLoc,
                },
                success: function (data){
                    console.log(data);
                }
            } );
        }
        else
        {
            // create new Route
            $.post({
                url: "/api/v1/ts1000/auto/insertRouteManual",
                data: {
                    routeName:nameRoute,
                    ArrayRouteTrainId:arrayRouteTr,
                    ArrayRouteDirection:arrayRouteDir,
                    ArrayRouteLocation:arrayRouteLoc,
                },
                success: function (data){
                    console.log(data);
                }
            } );
        }
    }
    location.reload()
}
//=========================================================
//Send Line Station API
//=========================================================
function sendArrayLine(){
    let chkAutoLine = document.getElementById("chkLine").checked;
    if (chkAutoLine==true)
    {
        $.post({
            url: "/api/v1/ts1000/auto/insertLineAuto",
            data: {
                ArrayLineTrainId:arrayLineTr,
                ArrayLineDirection:arrayLineDir,
                ArrayLineLocation:arrayLineLoc,
            },
            success: function (data){
                console.log(data);
            }
        } );
    }
    else
    {
        // check existing line
        let nameLine = document.getElementById("idNewLine").value;
        let lineId = document.getElementById("idLine").value;
        if (lineId!="") {
            // create by existing id
            $.post({
                url: "/api/v1/ts1000/auto/insertLineById",
                data: {
                    lineId:lineId,
                    ArrayLineTrainId:arrayLineTr,
                    ArrayLineDirection:arrayLineDir,
                    ArrayLineLocation:arrayLineLoc,
                },
                success: function (data){
                    console.log(data);
                }
            } );
        }
        else
        {
            // create new Line
            $.post({
                url: "/api/v1/ts1000/auto/insertLineManual",
                data: {
                    lineName:nameLine,
                    ArrayLineTrainId:arrayLineTr,
                    ArrayLineDirection:arrayLineDir,
                    ArrayLineLocation:arrayLineLoc,
                },
                success: function (data){
                    console.log(data);
                }
            } );

        }
    }
    location.reload()
}
//=========================================================
//Add Line Station Validation
//=========================================================
var arrayLineTr = [];
var arrayLineDir = [];
var arrayLineLoc = [];
function btnFunctionAddLine() {
    let table = document.getElementById("stationListAddLine");
    let stationText = $("#stationLine option:selected").text();
    let stationId = document.getElementById("stationLine").value;
    let directionId = document.getElementById("directionIdLine").value;
    let location = document.getElementById("locationLine").value;
    if (stationId != "" & directionId != "" & location !="")
    {
        var row = table.insertRow(1);
        var cell1 = row.insertCell(0);
        var cell2 = row.insertCell(1);
        var cell3 = row.insertCell(2);
        cell1.innerHTML = stationText;
        cell2.innerHTML = directionId;
        cell3.innerHTML = location;
        arrayLineTr.push(stationId);
        arrayLineDir.push(directionId);
        arrayLineLoc.push(location);
    }
    else
    {
        toastr.warning("Data Station Cant Be Null");
    }
}
//=========================================================
//Modify Train Color Validation + Loading
//=========================================================
$(document).on('click', '.btnSubmitTrainColor', function(){
    // show loading with 500ms
    $("#img").fadeIn(2000);
    // hide loading
    $("#img").fadeOut();
});

//=========================================================
//Add Route Relation + Loading
//=========================================================
$(document).on('click', '.btnAddRuteRelation', function(){
    // show loading with 500ms
    $("#img").fadeIn(2000);
    // hide loading
    $("#img").fadeOut();
});
//=========================================================
//Show Hide Add Route
//=========================================================
function ShowHideDivRoute(chkRoute) {
    var dvRoute = document.getElementById("dvRoute");
    dvRoute.style.display = chkRoute.checked ? "none" : "block";
}
//=========================================================
//Show Hide Add Line
//=========================================================
function ShowHideDivLine(chkLine) {
    var dvLine = document.getElementById("dvLine");
    dvLine.style.display = chkLine.checked ? "none" : "block";
}
