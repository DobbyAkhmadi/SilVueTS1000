//=========================================================
//Default Navigation
//=========================================================
$(document).ready(function() {
    $('.navSchedule').addClass('active');
});
//loading add schedule event
$(document).on('click', '.loading_add', function(){
    toastr.info('Adding data schedule Event !')
});
//loading modify schedule event
$(document).on('click', '.loading_modify', function(){
    toastr.info('Modify data schedule Event !')
});
//loading modify all schedule event
$(document).on('click', '.loading_modify_all', function(){
    toastr.info('Modify data all schedule Event !')
});
//loading Export schedule event
$(document).on('click', '.loadingExport', function(){
    toastr.info('Export schedule Event !')
});
//loading Import schedule event
$(document).on('click', '.loadingImport', function(){
    toastr.info('Import schedule Event !')
});
//loading Combine schedule event
$(document).on('click', '.loadingCombine', function(){
    toastr.info('Combine schedule Event !')
});
//loading delete all schedule event
$(document).on('click', '.loading_delete_all', function(){
    toastr.warning('Delete all Schedule Event !')
});
//loading delete schedule by id event
$(document).on('click', '.loading_delete', function(){
    toastr.warning('Delete a Schedule Event !')
});