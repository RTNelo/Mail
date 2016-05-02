/**
 * Created by An Young on 2016/1/12.
 */

$(document).ready(function(){
    $(".panel-btn").hide();
    $(".panel-control").mouseenter(function(){
        $(this).find(".panel-btn").show();
    });
    $(".panel-control").mouseleave(function(){
        $(this).find(".panel-btn").hide();
    });

    $(".span-btn").click(function(){
        var num = 0;
        if(haschoosed==false){
            $(this).addClass("btn-danger");
            $(this).find(".badge").text("0");
            $(this).addClass("choosed-btn");
            $("a").bind("click",stopa());
            haschoosed = true;
        }else{
            $(this).removeClass("btn-danger");
            $(this).find(".badge").text("");
            $("a").unbind("click",stopa());
            $(".panel-heading").css("background-color","#f5f5f5");
            haschoosed=false;
        }
    });
});
var haschoosed = false;

function stopa(){
    return false; }

