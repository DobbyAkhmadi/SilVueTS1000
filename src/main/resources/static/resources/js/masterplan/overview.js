$(function() {
    $.ajaxSetup({
        headers: {
            'X-CSRF-TOKEN': $('meta[name="csrf-token"]').attr('content')
        }
    });
    
});
$(document).ready(function() {
    $('.nav0v').addClass('active');
    var CSRF_TOKEN = $('meta[name="csrf-token"').attr('content');
});
