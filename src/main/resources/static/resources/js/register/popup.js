$('.li-modal-add-user').on('click', function (e) {
    console.log('kesini');
    e.preventDefault();
    $('#modalAddUser').modal('show').find('.modal-content').load($(this).attr('href'));
});




