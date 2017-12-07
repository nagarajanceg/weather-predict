jQuery(document).ready(function(e) {
    console.log("in init");
    var resultData;
    $.ajax({
        url: "http://localhost:8080/consume",
        type: "GET",
        dataType: "json",
        success: function(result) {
            load_chart(result, "F", "TAVG", 10, "Binghamton"); 
            resultData = result;
        }
    });

    $( "#apply" ).click(function() {
        console.log(city);
        console.log(temp);
        console.log(dayCnt);
        console.log(deg);
        if(deg == "C"){
            $('#t-deg').html('&#8451');
        }else{
            $('#t-deg').html('&#8457');
        }
        load_chart(resultData, deg, temp, dayCnt, city);
    });

    var city = "Binghamton";
    $('#city').change(function (e) {
        city = e.target.value.trim().trim();
    });

    var temp = "TAVG";
    $('#temp').change(function (e) {
        temp = e.target.value.trim().trim();
    });

    var dayCnt = 10;
    $('#dayCnt').change(function (e) {
        dayCnt = e.target.value.trim().trim();
    });

    var deg = "F";
    $('#deg-f').click(function(){
        deg = "F";
    });

    $('#deg-c').click(function(){
        deg = "C";
    });

    $('#t-dig').html('30');
    $('#t-deg').html('&#8457');

});