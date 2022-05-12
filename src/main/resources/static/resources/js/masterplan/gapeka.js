//var station = ['First', 'Second', 'Third', 'Fourth', 'Fifth', 'Sixth'];

var listTrain = [];
var dataListTrain = [];
var countDataPlan = 0;
var dataListTrainPrint = [];
var listTrainPrint = [];

function addDataPlan(item){

    if(listTrain.includes(item['notrain'])){
        var idx = listTrain.indexOf(item['notrain']);
        var dataX = item['x'];
        var date = new Date(dataX);
        var minutes = date.getMinutes();
        if(station.length - 2 == item['y']){
            var dataAllTrains = '{x:' + dataX + ',y:' + item['y'] + ',dataLabels: [{ enabled: true,style:{fontSize: 5}, formatter: function(){return ' + minutes + '} },{ enabled: true,style:{fontSize:10}, align: \'left\',verticalAlign: \'top\',  formatter: function(){return \'' + item['notrain'] + '\'}}]},';
        }else{
            var dataAllTrains = '{x:' + dataX + ',y:' + item['y'] + ',dataLabels: { enabled: true,style:{fontSize: 5}, formatter: function(){return ' + minutes + '}}},';
        }
        dataListTrain[idx] = dataListTrain[idx].concat(dataAllTrains);

    }else{
        listTrain.push(item['notrain']);
        var idx = listTrain.indexOf(item['notrain']);
        var dataX = item['x'];
        var date = new Date(dataX);
        var minutes = date.getMinutes();
        var dataAllTrains = '{ id:\'' + item['notrain'] + '\',';
        dataAllTrains += 'name:\'' + item['notrain'] + '\',';
        dataAllTrains += 'color: \'' + item['color'] + '\',';
        dataAllTrains += 'tooltip: {formatter: function() {return \'' + item['notrain'] + '\'}},';
        dataAllTrains += ' data:[';
        dataAllTrains += '{x:' + dataX + ',y:' + item['y'] + ',dataLabels: [{ enabled: true,style:{fontSize: 5}, formatter: function(){return ' + minutes + '} },{ enabled: true,style:{fontSize:10}, align: \'left\',verticalAlign: \'top\',  formatter: function(){return \'' + item['notrain'] + '\'}}]},';

        dataListTrain[idx] = dataAllTrains;

    }

}
function addDataPlanPrint(item) {
    if(listTrainPrint.includes(item['notrain'])){
        var idx = listTrainPrint.indexOf(item['notrain']);
        var dataX = item['x'];
        var date = new Date(dataX);
        var minutes = date.getMinutes();
        var dataAllTrains = '{x:' + dataX + ',y:' + item['y'] + ',dataLabels: [{ enabled: true,style:{fontSize: 5}, formatter: function(){return ' + minutes + '} },{ enabled: true,style:{fontSize:10}, align: \'left\',verticalAlign: \'top\',  formatter: function(){return \'' + item['notrain'] + '\'}}]},';
       
        // if(station.length - 2 == item['y']){
        //     var dataAllTrains = '{x:' + dataX + ',y:' + item['y'] + ',dataLabels: [{ enabled: true,style:{fontSize: 5}, formatter: function(){return ' + minutes + '} },{ enabled: true,style:{fontSize:10}, align: \'left\',verticalAlign: \'top\',  formatter: function(){return \'' + item['notrain'] + '\'}}]},';
        // }else{
        //     var dataAllTrains = '{x:' + dataX + ',y:' + item['y'] + ',dataLabels: { enabled: true,style:{fontSize: 5}, formatter: function(){return ' + minutes + '}}},';
        // }
        dataListTrain[idx] = dataListTrain[idx].concat(dataAllTrains);

    }else{
        listTrainPrint.push(item['notrain']);
        var idx = listTrainPrint.indexOf(item['notrain']);
        var dataX = item['x'];
        var date = new Date(dataX);
        var minutes = date.getMinutes();
        var dataAllTrains = '{ id:\'' + item['notrain'] + '\',';
        dataAllTrains += 'name:\'' + item['notrain'] + '\',';
        dataAllTrains += 'color: \'' + item['color'] + '\',';
        dataAllTrains += 'tooltip: {formatter: function() {return \'' + item['notrain'] + '\'}},';
        dataAllTrains += ' data:[';
        dataAllTrains += '{x:' + dataX + ',y:' + item['y'] + ',dataLabels: [{ enabled: true,style:{fontSize: 5}, formatter: function(){return ' + minutes + '} },{ enabled: true,style:{fontSize:10}, align: \'left\',verticalAlign: \'top\',  formatter: function(){return \'' + item['notrain'] + '\'}}]},';

        dataListTrain[idx] = dataAllTrains;

    }

}

$(document).ready(function () {
    $('.navGapeka').addClass('active');

    $('#actualcode').val(new Date().toLocaleDateString('en-CA'));

    planBaseInit();

    $("#planschedule").change(function () {
        var id = $(this).val();
        if (id === 'actual') {
            var urlAllParam = '/api/v1/ts1000/actual/all-param';
            document.getElementById('typeschedule').disabled = false;
            document.getElementById('actualcode').disabled = false;
            document.getElementById('typeline').disabled = false;
            document.getElementById('planbase').disabled = true;
            $.ajax({
                url: urlAllParam,
                type: 'get',
                dataType: 'json',
                success: function(response) {
                    $('#typeschedule').empty();
                    // get first data dropdown
                    $('#typeschedule').find('option').not(':first');
                    for(let i in response.typePlans) {
                        $("#typeschedule").append('<option value="' + response.typePlans[i] + '">' + response.typePlans[i] + '</option>');
                    }

                    $('#typeline').empty();
                    // get first data dropdown
                    $('#typeline').find('option').not(':first');
                    for(let i in response.ruteRoles) {
                        $("#typeline").append('<option value="' + response.ruteRoles[i] + '">' + response.ruteRoles[i] + '</option>');
                    }

                    $('#viewbase').empty();
                    $('#viewbase').find('option').not(':first');
                    $('#viewbase').append('<option value="actual">Actual</option>');
                    $('#viewbase').append('<option value="actual_schedule">Actual & Schedule</option>');
                }
            });
        } else {
            document.getElementById('typeschedule').disabled = false;
            document.getElementById('actualcode').disabled = true;
            document.getElementById('typeline').disabled = false;
            document.getElementById('planbase').disabled = false;

            var paramScheduleUrl = '/api/v1/ts1000/schedule/param';
            $.ajax({
                url: paramScheduleUrl,
                type: 'get',
                dataType: 'json',
                success: function(response) {
                    if ($('#planbase').val() == 'rute') {
                        $('#typeline').empty();
                        $('#typeline').find('option').not(':first');
                        for (let i in response.ruteRoleList) {
                            $("#typeline").append('<option value="' + response.ruteRoleList[i].idRuteRole + '">' + response.ruteRoleList[i].nameRoute + '</option>');
                        }
                    } else if ($('#planbase').val() == 'line') {
                        $('#typeline').empty();
                        $('#typeline').find('option').not(':first');
                        for (let i in response.lineList) {
                            $('#typeline').append('<option value="' + response.lineList[i].idLine + '">' + response.lineList[i].nameLine + '</option>');
                        }
                    }

                    $('#typeschedule').empty();
                    $('#typeschedule').find('option').not(':first');
                    for(let i in response.typeMasterPlanList) {
                        $('#typeschedule').append('<option value="' + response.typeMasterPlanList[i].idTypeMasterPlan + '">' + response.typeMasterPlanList[i].nameTypeMasterPlan + '</option>');
                    }

                    $('#viewbase').empty();
                    $('#viewbase').find('option').not(':first');
                    $('#viewbase').append('<option value="schedule">Schedule</option>');
                    $('#viewbase').append('<option value="actual_schedule">Actual & Schedule</option>');
                }
            });
        }
    });

    $('#planbase').change(function () {
        planBaseInit();
    });
});

function planBaseInit() {
    var planScheduleVal = $('#planschedule').val();
    if(planScheduleVal === 'master' || planScheduleVal === 'today') {
        var paramScheduleUrl = '/api/v1/ts1000/schedule/param';
        $.ajax({
            url: paramScheduleUrl,
            type: 'get',
            dataType: 'json',
            success: function (response) {
                var typeLineVal = $('#planbase').val();
                $('#typeline').empty();
                $('#typeline').find('option').not(':first');
                if (typeLineVal === 'rute') {
                    for (let i in response.ruteRoleList) {
                        $("#typeline").append('<option value="' + response.ruteRoleList[i].idRuteRole + '">' + response.ruteRoleList[i].nameRoute + '</option>');
                    }
                } else if (typeLineVal === 'line') {
                    for (let i in response.lineList) {
                        $('#typeline').append('<option value="' + response.lineList[i].idLine + '">' + response.lineList[i].nameLine + '</option>');
                    }
                }
            }
        });
    }
}

function settingBeforePrint() {
    var seriesA = "";
    for (let j = 0; j < Object.values(nametrain).length; j++) {
        if (typesschedulename != "actual") {
            $.ajax({
                url: '/setData',
                type: 'get',
                async: false,
                dataType: 'JSON',
                data: {
                    val_name: nametrain[j],
                    rute_name: idroutename,
                    scheduleType: idtype,
                    nameScheduleType: nameType,
                    type_schedulename: typesschedulename
                },

                success: function (response) {
                    data_gapeka = response;
                    seriesA += '{ name:\'' + nametrain[j] + '\',';
                    seriesA += 'color: \'' + colortrain[j] + '\',';
                    seriesA += 'tooltip: {formatter: function() {return \'' + nametrain[j] + '\'}},';
                    //data: [{x:1595468400000,y:1,dataLabels: { enabled: true,style:{fontSize: 5}, formatter: function(){return 10} } } ]
                    seriesA += ' data:[';
                    for (let i = 0; i < Object.keys(data_gapeka).length; i++) {
                        _obj_key = Object.keys(data_gapeka);
                        _obj_value = Object.values(data_gapeka);
                        var date = new Date(_obj_key[i] * 1000);
                        var minutes = date.getMinutes();
                        //if(i === 0 || i === Object.keys(data_gapeka).length - 1){
                        seriesA += '{x:' + _obj_key[i] * 1000 + ',y:' + _obj_value[i] + ',dataLabels: [{ enabled: true,style:{fontSize: 5}, formatter: function(){return ' + minutes + '} },{ enabled: true,style:{fontSize:10}, align: \'left\',verticalAlign: \'top\',  formatter: function(){return \'' + nametrain[j] + '\'}}]},';
                        //}
                        //else{
                        //    seriesA += '{x:'+ _obj_key[i]*1000 + ',y:'+_obj_value[i]+',dataLabels: { enabled: true,style:{fontSize: 5}, formatter: function(){return '+minutes+'} } },';
                        //}

                    }
                    seriesA = seriesA.substr(0, seriesA.length - 1);

                    if (j == Object.values(nametrain).length - 1) {
                        seriesA += ']}';
                    } else {
                        seriesA += ']},';
                    }

                },
                error: function (xhr, ajaxOptions, thrownError) {
                    alert(thrownError);
                }
            }); //end ajax 

        } else {
            var countReal = 0;
            hoursGraph = 24;
            $.ajax({
                url: '/setData',
                type: 'get',
                async: false,
                dataType: 'JSON',
                data: {
                    val_name: nametrain[j],
                    rute_name: idroutename,
                    actualCode: dateActualCode,
                    scheduleType: idtype,
                    nameScheduleType: nameType,
                    type_schedulename: typesschedulename,
                    type_actual: 'real'
                },

                success: function (response) {
                    data_gapeka = response;
                    if (Object.keys(data_gapeka).length > 0) {


                        seriesA += '{ name:\'' + nametrain[j] + 'A\',';
                        seriesA += 'color: \'' + colortrain[j] + '\',';
                        seriesA += 'tooltip: {formatter: function() {return \'' + nametrain[j] + 'A\'}},';
                        //data: [{x:1595468400000,y:1,dataLabels: { enabled: true,style:{fontSize: 5}, formatter: function(){return 10} } } ]
                        seriesA += ' data:[';
                        for (let i = 0; i < Object.keys(data_gapeka).length; i++) {
                            _obj_key = Object.keys(data_gapeka);
                            _obj_value = Object.values(data_gapeka);
                            var date = new Date(_obj_key[i] * 1000);
                            var minutes = date.getMinutes();
                            //if(i === 0 || i === Object.keys(data_gapeka).length - 1){
                            seriesA += '{x:' + _obj_key[i] * 1000 + ',y:' + _obj_value[i] + ',dataLabels: [{ enabled: true,style:{fontSize: 5}, formatter: function(){return ' + minutes + '} },{ enabled: true,style:{fontSize:10}, align: \'left\',verticalAlign: \'top\',  formatter: function(){return \'' + nametrain[j] + 'A\'}}]},';
                            //}
                            //else{
                            //    seriesA += '{x:'+ _obj_key[i]*1000 + ',y:'+_obj_value[i]+',dataLabels: { enabled: true,style:{fontSize: 5}, formatter: function(){return '+minutes+'} } },';
                            //}

                        }
                        seriesA = seriesA.substr(0, seriesA.length - 1);

                        if (j == Object.values(nametrain).length - 1) {
                            seriesA += ']},';
                        } else {
                            seriesA += ']},';
                        }
                    }

                },
                error: function (xhr, ajaxOptions, thrownError) {
                    alert(thrownError);
                }
            }); //end ajax 

            $.ajax({
                url: '/setData',
                type: 'get',
                async: false,
                dataType: 'JSON',
                data: {
                    val_name: nametrain[j],
                    rute_name: idroutename,
                    actualCode: dateActualCode,
                    scheduleType: idtype,
                    nameScheduleType: nameType,
                    type_schedulename: typesschedulename,
                    type_actual: 'schedule'
                },

                success: function (response) {
                    data_gapeka = response;
                    if (Object.keys(data_gapeka).length > 0) {
                        //console.log(data_gapeka);
                        seriesA += '{ name:\'' + nametrain[j] + '\',';
                        seriesA += 'color: \'' + colortrain[j] + '\',';
                        seriesA += 'opacity: 0.3,';
                        seriesA += 'marker: {symbol: \'triangle\'},';
                        seriesA += 'tooltip: {formatter: function() {return \'' + nametrain[j] + '\'}},';
                        //data: [{x:1595468400000,y:1,dataLabels: { enabled: true,style:{fontSize: 5}, formatter: function(){return 10} } } ]
                        seriesA += ' data:[';
                        for (let i = 0; i < Object.keys(data_gapeka).length; i++) {
                            _obj_key = Object.keys(data_gapeka);
                            _obj_value = Object.values(data_gapeka);
                            var date = new Date(_obj_key[i] * 1000);
                            var minutes = date.getMinutes();
                            //if(i === 0 || i === Object.keys(data_gapeka).length - 1){
                            seriesA += '{x:' + _obj_key[i] * 1000 + ',y:' + _obj_value[i] + ',dataLabels: [{ enabled: true,style:{fontSize: 5}, formatter: function(){return ' + minutes + '} },{ enabled: true,style:{fontSize:10}, align: \'left\',verticalAlign: \'top\',  formatter: function(){return \'' + nametrain[j] + '\'}}]},';
                            //}
                            //else{
                            //    seriesA += '{x:'+ _obj_key[i]*1000 + ',y:'+_obj_value[i]+',dataLabels: { enabled: true,style:{fontSize: 5}, formatter: function(){return '+minutes+'} } },';
                            //}

                        }
                        //seriesA = seriesA.substr(0, seriesA.length - 1);

                        if (j == Object.values(nametrain).length - 1) {
                            seriesA += ']}';
                        } else {
                            seriesA += ']},';
                        }
                    }
                },
                error: function (xhr, ajaxOptions, thrownError) {
                    alert(thrownError);
                }
            }); //end ajax 

        }
    }

    dataPrint = seriesA;
}

function setDataEveryHours(startHours, stopHours) {
    var seriesA = "";
    for (let j = 0; j < Object.values(nametrain).length; j++) {
        if (typesschedulename != "actual") {
            $.ajax({
                url: '/setDataPerHours',
                type: 'get',
                async: false,
                dataType: 'JSON',
                data: {
                    val_name: nametrain[j],
                    rute_name: idroutename,
                    type_schedulename: typesschedulename,
                    startPrint: startHours,
                    stopPrint: stopHours
                },

                success: function (response) {
                    data_gapeka = response;
                    seriesA += '{ name:' + nametrain[j] + ',';
                    seriesA += 'tooltip: {formatter: function() {return ' + nametrain[j] + '}},';
                    //data: [{x:1595468400000,y:1,dataLabels: { enabled: true,style:{fontSize: 5}, formatter: function(){return 10} } } ]
                    seriesA += ' data:[';
                    for (let i = 0; i < Object.keys(data_gapeka).length; i++) {
                        _obj_key = Object.keys(data_gapeka);
                        _obj_value = Object.values(data_gapeka);
                        var date = new Date(_obj_key[i] * 1000);
                        var minutes = date.getMinutes();
                        if (i === 0 || i === Object.keys(data_gapeka).length - 1) {
                            seriesA += '{x:' + _obj_key[i] * 1000 + ',y:' + _obj_value[i] + ',dataLabels: [{ enabled: true,style:{fontSize: 5}, formatter: function(){return ' + minutes + '} },{ enabled: true,style:{fontSize:10}, align: \'left\',verticalAlign: \'top\',  formatter: function(){return ' + nametrain[j] + '}}]},';
                        } else {
                            seriesA += '{x:' + _obj_key[i] * 1000 + ',y:' + _obj_value[i] + ',dataLabels: { enabled: true,style:{fontSize: 5}, formatter: function(){return ' + minutes + '} } },';
                        }

                    }
                    if (Object.keys(data_gapeka).length > 0)
                        seriesA = seriesA.substr(0, seriesA.length - 1);

                    if (j == Object.values(nametrain).length - 1) {
                        seriesA += ']}';
                    } else {
                        seriesA += ']},';
                    }

                },
                error: function (xhr, ajaxOptions, thrownError) {
                    alert(thrownError);
                }
            }); //end ajax 

        } else {
            var countReal = 0;
            $.ajax({
                url: '/setData',
                type: 'get',
                dataType: 'JSON',
                data: {
                    val_name: nametrain[j],
                    rute_name: idroutename,
                    type_schedulename: typesschedulename,
                    type_actual: 'real'
                },

                success: function (response) {
                    data_gapeka = response;
                    seriesA += '{ "name": "' + nametrain[j] + '",';
                    seriesA += ' "data": [';
                    for (let i = 0; i < Object.keys(data_gapeka).length; i++) {
                        _obj_key = Object.keys(data_gapeka);
                        _obj_value = Object.values(data_gapeka);
                        seriesA += '[' + _obj_key[i] * 1000 + ',' + _obj_value[i] + '],';
                    }
                    seriesA += '],';
                    seriesA += ' "formatter": function() return "' + nametrain[j] + '" },';

                },
                error: function (xhr, ajaxOptions, thrownError) {
                    alert(thrownError);
                }
            }); //end ajax 

            $.ajax({
                url: '/setData',
                type: 'get',
                dataType: 'JSON',
                data: {
                    val_name: nametrain[j],
                    rute_name: idroutename,
                    type_schedulename: typesschedulename,
                    type_actual: 'schedule'
                },

                success: function (response) {
                    data_gapeka = response;
                    seriesA += '{ "name": ' + nametrain[j] + ',';
                    seriesA += ' "data": [';
                    for (let i = 0; i < Object.keys(data_gapeka).length; i++) {
                        _obj_key = Object.keys(data_gapeka);
                        _obj_value = Object.values(data_gapeka);
                        seriesA += '[' + _obj_key[i] * 1000 + ',' + _obj_value[i] + '],';
                    }
                    seriesA += '],';
                    seriesA += ' "formatter": function() return "' + nametrain[j] + '" },';
                },
                error: function (xhr, ajaxOptions, thrownError) {
                    alert(thrownError);
                }
            }); //end ajax 
        }
    }

    //seriesA = seriesA;
    dataPrint = seriesA;
    //console.log("Print Hours : "+startHours);
    //console.log(data);
}
