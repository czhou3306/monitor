$(document).ready(function() {
    (function(){
        $(".icon_dropdown_switch .arrow_up").click(function(){
            $(this).hide();
            $(this).sibling().show();
            $(".yzGz").show();
        });

        $(".icon_dropdown_switch .arrow_down").click(function(){
            $(this).hide();
            $(this).sibling().show();
            $(".yzGz").hide();
        });
    })();
});