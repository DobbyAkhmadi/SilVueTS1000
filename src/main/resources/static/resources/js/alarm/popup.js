//modal modify data
$('.li-modal-export-alarm').on('click', function(e){
   e.preventDefault();
   $('#ModalExportAlarm').modal('show').find('.modal-content').load($(this).attr('href'));
});

$(document).ready(function() {
    $('.navAlarm').addClass('active');
});