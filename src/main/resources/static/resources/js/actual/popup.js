// nav actual
$(document).ready(function() {
    $('.navActual').addClass('active');
});

//modal modify data
$('.li-modal-export-problem').on('click', function(e){
   e.preventDefault();
   $('#modalExportDataProblem').modal('show').find('.modal-content').load($(this).attr('href'));
});

//modal modify data
$('.li-modal-export-actual').on('click', function(e){
  // e.preventDefault();
   $('#ModalExportActualPlan').modal('show').find('.modal-content').load($(this).attr('href'));
});
//modal modify data
$('.li-modal-select-actual').on('click', function(e){
  // e.preventDefault();
   $('#ModalSelectActual').modal('show').find('.modal-content').load($(this).attr('href'));
});
//modal modify detail data
$('.li-modal-actual-schedule').on('click', function(e){
   e.preventDefault();
   $('#modalModifyActualSchedule').modal('show').find('.modal-content').load($(this).attr('href'));
});
//modal modify detail data
$('.li-modal-problem-log').on('click', function(e){
   e.preventDefault();
   $('#modalModifyProblemLog').modal('show').find('.modal-content').load($(this).attr('href'));
});
//modal modify detail data
$('.li-modal-all-train').on('click', function(e){
   e.preventDefault();
   $('#modalModifyAllTrain').modal('show').find('.modal-content').load($(this).attr('href'));
});
//modal modify detail data
$('.li-modal-spesific-date').on('click', function(e){
   e.preventDefault();
   $('#modalDeleteSpesificDate').modal('show').find('.modal-content').load($(this).attr('href'));
});
//modal import data
$('.li-modal-import-actual').on('click', function(e){
   e.preventDefault();
   $('#ModalimportActualSchedule').modal('show').find('.modal-content').load($(this).attr('href'));
});