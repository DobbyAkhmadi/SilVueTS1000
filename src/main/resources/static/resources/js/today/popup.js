// navigation default
$(document).ready(function() {
    $('.navToday').addClass('active');
});
//modal modify data
$('.li-modal-today-schedule').on('click', function(e){
   e.preventDefault();
   $('#modalModifyTodaySchedule').modal('show').find('.modal-content').load($(this).attr('href'));
});
//modal modify detail data
$('.li-modal-schedule-today-detail').on('click', function(e){
   e.preventDefault();
   $('#modalModifyTodayDetailSchedule').modal('show').find('.modal-content').load($(this).attr('href'));
});
//modal select type data
$('.li-modal-select').on('click', function(e){
   e.preventDefault();
   $('#modalTypeSchedule').modal('show').find('.modal-content').load($(this).attr('href'));
});
