$('.li-modal-atr').on('click', function (e) {
    e.preventDefault();
    $('#modalModifyAtr').modal('show').find('.modal-content').load($(this).attr('href'));
});
