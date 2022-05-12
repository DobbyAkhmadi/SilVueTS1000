// navigation default
$(document).ready(function() {
    $('.navToday').addClass('active');
});
//loading add schedule event
$(document).on('click', '.loading_add', function(){
    var data = $('#name_schedul').val();
    if (data != "") {
        $("#img").fadeIn();
        toastr.info('Select Type Schedule Event.')
    }
});
//loading modify schedule event
$(document).on('click', '.loading_modify', function(){
    var data = $('#name_schedul').val();
    if (data != "") {
        $("#img").fadeIn();
        toastr.info('Modify data schedule Event.')
    }
});
//loading delete all schedule event
$(document).on('click', '.loading_delete_all', function(){
    var data = $('#name_schedul').val();
    if (data != "") {
        $("#img").fadeIn();
        toastr.info('Delete all Schedule Event.')
    }
});