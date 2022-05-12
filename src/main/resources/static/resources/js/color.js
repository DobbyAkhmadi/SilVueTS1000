$(document).on('click', '.colored', function(){
    $(this).children('a').addClass("active");
    $("#load").fadeIn();
});