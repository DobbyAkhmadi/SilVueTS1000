// navigation default
$(document).ready(function() {
    $('.navSettings').addClass('active');
});

$('.li-modal-schedule-type').on('click', function(e){
    e.preventDefault();
    $('#modalModifyScheduleType').modal('show').find('.modal-content').load($(this).attr('href'));
});

$('.li-modal-add-rute-list').on('click', function(e){
    e.preventDefault();
    $('#modalAddrutelist').modal('show').find('.modal-content').load($(this).attr('href'));
});

$('.li-modal-add-line-list').on('click', function(e){
    e.preventDefault();
    $('#modalAddLineList').modal('show').find('.modal-content').load($(this).attr('href'));
});

$('.li-modal-import-line').on('click', function(e){
    e.preventDefault();
    $('#Modalimportlineroute').modal('show').find('.modal-content').load($(this).attr('href'));
});

$('.li-modal-color-train').on('click', function(e){
    e.preventDefault();
    $('#modalModifyColorTrain').modal('show').find('.modal-content').load($(this).attr('href'));
});

$('.li-modal-add-type-schedule').on('click', function(e){
    e.preventDefault();
    $('#modalAddtypeSchedule').modal('show').find('.modal-content').load($(this).attr('href'));
});
