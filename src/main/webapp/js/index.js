
$(document).ready(function(){
    var carDetails = $('#carDetails');
    var carDetailsList = $('#carDetails ul');

    carDetails.hide();

    var compiled = _.template('<% _.each(values, function(value){ %> <li><%= value %></li> <%}); %>');

    var hoverIn = function(event){
        var target = $(event.target);

        target.width(250);

        var data = [];
        var currentTagId = target.attr('id');
        if (currentTagId == 'carBrand'){
            for (var i = 0; i < 50; i++){
                data.push('brand' + i);
            }
        }
        if (currentTagId == 'carType'){
            for (var i = 0; i < 50; i++){
                data.push('type' + i);
            }
        }
        if (currentTagId == 'carPrice'){
            data.push('1000~10000', '10000~50000', '50000~100000', '100000~200000', '200000~400000', '400000~80000', '800000以上');
        }
        if (currentTagId == 'carAge'){
            data.push('三年以下', '三至五年', '五至十年', '十年以上');
        }


        var html = compiled({values: data});
        carDetailsList.append(html);
        carDetails.find('li').click(function(event){

            var message = $(event.target).text();
            console.log('data', message);
            target.val(message);
            target.attr('value', message);
        });

        var top = target.position().top;
        carDetails.css('top', top);

        carDetails.show();
    };

    var insideArea = function(x, y){
        var position = carDetails.offset();
        var x0 = position.left;
        var x1 = position.left + carDetails.width();
        var y0 = position.top;
        var y1 = position.top + carDetails.height();

        return x > x0 && x < x1 && y > y0 && y < y1;

    };

    var restore = function (event) {
        var target = $(event.target);
        target.width(234);
        carDetailsList.empty();
        carDetails.hide();
    }

    var hoverOut = function(event){
        if (insideArea(event.pageX, event.pageY)){
            carDetails.mouseleave(function(){
                restore(event);
            });
        }else{
            restore(event);
        }
    };
//
//    $.ajax({
//        url: '/product/getBrands',
//        type: 'GET',
//        data: {brandId: 'hello'}
//    }).done(function(data){
//        console.log('data', data);
//    });

    _.each(['carBrand', 'carType', 'carPrice', 'carAge'], function(val){
        $('#' + val).hover(hoverIn, hoverOut);
    });

});
