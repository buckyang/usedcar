var global = {};

$(document).ready(function () {
    var carDetails = $('#carDetails');
    var carDetailsList = $('#carDetails ul');

    var allBrands = {};

    carDetails.hide();

    var compiled = _.template('<% _.each(values, function(val, key){ %> <li id=<%= key%>><%= val %></li> <%}); %>');

    var renderOptions = function (target, data) {

        var html = compiled({values: data});
        carDetailsList.append(html);

        carDetails.find('li').click(function (event) {
            var selectedListValue = $(event.target).text();
            var selectedListId = $(event.target).attr('id');

            target.val(selectedListValue);
            target.attr('value', selectedListId);
        });

        var top = target.position().top;
        carDetails.css('top', top);
        carDetails.show();
    };

    var hoverIn = function (event) {
        var target = $(event.target);

        target.width(255);

        var currentTagId = target.attr('id');

        if (currentTagId == 'carBrand') {
            renderOptions(target, allBrands);
        }

        if (currentTagId == 'carType') {
            $.ajax({
                url: '/product/getSeries',
                type: 'GET',
                dataType: 'json',
                data: {"brandId": $('#carBrand').attr('value')}
            }).done(function (response) {
                renderOptions(target, response);
            });
        }

        if (currentTagId == 'carPrice') {
            renderOptions(target, {
                1: '1000~10000',
                2: '10000~50000',
                3: '50000~100000',
                4: '100000~200000',
                5: '200000~400000',
                6: '400000~80000',
                7: '800000以上'
            })
        }

        if (currentTagId == 'carAge') {
            renderOptions(target, {
                1 :'三年以下',
                2 : '三至五年',
                3 : '五至十年',
                4: '十年以上'
            })
        }

    };

    var insideArea = function (x, y) {
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
    };

    var hoverOut = function (event) {
        if (insideArea(event.pageX, event.pageY)) {
            carDetails.mouseleave(function () {
                restore(event);
            });
        } else {
            restore(event);
        }
    };

    $.ajax({
        url: '/product/getBrands',
        type: 'GET',
        dataType: 'json'
    }).done(function (data) {
        allBrands = data;
    });

    _.each(['carBrand', 'carType', 'carPrice', 'carAge'], function (val) {
        $('#' + val).hover(hoverIn, hoverOut);
    });
});
