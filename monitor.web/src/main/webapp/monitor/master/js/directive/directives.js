App
    .directive("lineChart", ["$timeout", function ($timeout) {
        function draw(scope, ele, title, cate, yaxis, ser) {
            ele.highcharts({
                chart: {
                    type: 'line',
                },
                title: {
                    text: title,
                    x: -20 //center
                },
                xAxis: {
                    categories:cate
                },
                yAxis: {
                    title: {
                        text: yaxis
                    },
                    plotLines: [{
                        value: 0,
                        width: 1,
                        color: '#808080'
                    }]
                },

                legend: {
                    layout: 'vertical',
                    align: 'right',
                    verticalAlign: 'middle',
                    borderWidth: 0
                },
                series:ser
            });
        }

        return {
            restrict: "AE",
            // scope:false,
            link: function (scope, ele, attr) {

                attr.$observe('series', function (value) {
                    if ((attr.series.replace(/(^s*)|(s*$)/g, "").length == 0)) {
                        return;
                    }
                    var ser = JSON.parse(attr.series);
                  //如果true的话全部显示，否则只显示最后一列
                    if(!(attr.allCol==="true")){
                        ser = ser.splice(ser.length-1);
                    }

                    var category = JSON.parse(attr.categories)
                    if (category.length > 0) {
                        draw(scope, ele, attr.title, category, attr.yaxis, ser);
                    }
                });

            }
        }
    }])
    //柱状图
    .directive("columnChart", ["$timeout", function ($timeout) {
        function draw(scope, ele, title, cate, yaxis, ser) {

            ele.highcharts({
                chart: {
                    type: 'column',
                },
                title: {
                    text: title
                },
                xAxis: {
                    categories: cate
                },
                yAxis: {
                    min: 0,
                    title: {
                        text: yaxis
                    }
                },
                tooltip: {
                    headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
                    pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                    '<td style="padding:0"><b>{point.y:.1f}</b></td></tr>',
                    footerFormat: '</table>',
                    shared: true,
                    useHTML: true
                },
                plotOptions: {
                    column: {
                        pointPadding: 0.2,
                        borderWidth: 0
                    }
                },
                series: ser
            });
        }

        return {
            restrict: "AE",
            // scope:false,
            link: function (scope, ele, attr) {
                attr.$observe('series', function (value) {
                    if ((attr.series.replace(/(^s*)|(s*$)/g, "").length == 0)) {
                        return;
                    }
                    var ser = JSON.parse(attr.series);
                    var category = JSON.parse(attr.categories)
                    if (category.length > 0) {
                        draw(scope, ele, attr.title, category, attr.yaxis, ser);
                    }
                });
            }
        }
    }])
    //中国地图
    .directive("chinamap", ["$timeout", function ($timeout) {
        function draw(scope, ele, data) {

            var mapKey = "countries/china";
            var mapGeoJSON = Highcharts.maps[mapKey];

            Highcharts.theme = {
                colors: ["#2b908f", "#90ee7e", "#f45b5b", "#7798BF", "#aaeeee", "#ff0066", "#eeaaee",
                    "#55BF3B", "#DF5353", "#7798BF", "#aaeeee"],
                chart: {
                    backgroundColor: {
                        linearGradient: {x1: 0, y1: 0, x2: 1, y2: 1},
                        stops: [
                            [0, '#2a2a2b'],
                            [1, '#3e3e40']
                        ]
                    },
                    style: {
                        fontFamily: "'Unica One', sans-serif"
                    },
                    plotBorderColor: '#606063'
                },
                title: {
                    style: {
                        color: '#E0E0E3',
                        textTransform: 'uppercase',
                        fontSize: '20px'
                    }
                },
                subtitle: {
                    style: {
                        color: '#E0E0E3',
                        textTransform: 'uppercase'
                    }
                },
                xAxis: {
                    gridLineColor: '#707073',
                    labels: {
                        style: {
                            color: '#E0E0E3'
                        }
                    },
                    lineColor: '#707073',
                    minorGridLineColor: '#505053',
                    tickColor: '#707073',
                    title: {
                        style: {
                            color: '#A0A0A3'

                        }
                    }
                },
                yAxis: {
                    gridLineColor: '#707073',
                    labels: {
                        style: {
                            color: '#E0E0E3'
                        }
                    },
                    lineColor: '#707073',
                    minorGridLineColor: '#505053',
                    tickColor: '#707073',
                    tickWidth: 1,
                    title: {
                        style: {
                            color: '#A0A0A3'
                        }
                    }
                },
                tooltip: {
                    backgroundColor: 'rgba(0, 0, 0, 0.85)',
                    style: {
                        color: '#F0F0F0'
                    }
                },
                plotOptions: {
                    series: {
                        dataLabels: {
                            color: '#B0B0B3'
                        },
                        marker: {
                            lineColor: '#333'
                        }
                    },
                    boxplot: {
                        fillColor: '#505053'
                    },
                    candlestick: {
                        lineColor: 'white'
                    },
                    errorbar: {
                        color: 'white'
                    }
                },
                legend: {
                    itemStyle: {
                        color: '#E0E0E3'
                    },
                    itemHoverStyle: {
                        color: '#FFF'
                    },
                    itemHiddenStyle: {
                        color: '#606063'
                    }
                },
                credits: {
                    style: {
                        color: '#666'
                    }
                },
                labels: {
                    style: {
                        color: '#707073'
                    }
                },

                drilldown: {
                    activeAxisLabelStyle: {
                        color: '#F0F0F3'
                    },
                    activeDataLabelStyle: {
                        color: '#F0F0F3'
                    }
                },

                navigation: {
                    buttonOptions: {
                        symbolStroke: '#DDDDDD',
                        theme: {
                            fill: '#505053'
                        }
                    }
                },

                // scroll charts
                rangeSelector: {
                    buttonTheme: {
                        fill: '#505053',
                        stroke: '#000000',
                        style: {
                            color: '#CCC'
                        },
                        states: {
                            hover: {
                                fill: '#707073',
                                stroke: '#000000',
                                style: {
                                    color: 'white'
                                }
                            },
                            select: {
                                fill: '#000003',
                                stroke: '#000000',
                                style: {
                                    color: 'white'
                                }
                            }
                        }
                    },
                    inputBoxBorderColor: '#505053',
                    inputStyle: {
                        backgroundColor: '#333',
                        color: 'silver'
                    },
                    labelStyle: {
                        color: 'silver'
                    }
                },

                navigator: {
                    handles: {
                        backgroundColor: '#666',
                        borderColor: '#AAA'
                    },
                    outlineColor: '#CCC',
                    maskFill: 'rgba(255,255,255,0.1)',
                    series: {
                        color: '#7798BF',
                        lineColor: '#A6C7ED'
                    },
                    xAxis: {
                        gridLineColor: '#505053'
                    }
                },

                scrollbar: {
                    barBackgroundColor: '#808083',
                    barBorderColor: '#808083',
                    buttonArrowColor: '#CCC',
                    buttonBackgroundColor: '#606063',
                    buttonBorderColor: '#606063',
                    rifleColor: '#FFF',
                    trackBackgroundColor: '#404043',
                    trackBorderColor: '#404043'
                },

                // special colors for some of the
                legendBackgroundColor: 'rgba(0, 0, 0, 0.5)',
                background2: '#505053',
                dataLabelsColor: '#B0B0B3',
                textColor: '#C0C0C0',
                contrastTextColor: '#F0F0F3',
                maskColor: 'rgba(255,255,255,0.3)'
            };


            var options = {
                title: {
                    text: '访问地区分布'
                },
                mapNavigation: {
                    enabled: true
                },
                colorAxis: {
                    min: 0,
                    stops: [
                        [0, '#EFEFFF'],
                        [0.5, Highcharts.getOptions().colors[0]],
                        [1, Highcharts.Color(Highcharts.getOptions().colors[0]).brighten(-0.5).get()]
                    ]
                },
                legend: {
                    layout: 'vertical',
                    align: 'left',
                    verticalAlign: 'bottom'
                },
                series: [{
                    data: data,
                    mapData: mapGeoJSON,
                    joinBy: 'name',
                    name: '访问量',
                    states: {
                        hover: {
                            color: Highcharts.getOptions().colors[2]
                        }
                    },
                    dataLabels: {
                        enabled: true,
                        formatter: function () {
                            return mapKey === 'custom/world' || mapKey === 'countries/us/us-all' ?
                                (this.point.properties && this.point.properties['hc-a2']) :
                                this.point.name;
                        }
                    },
                    point: {
                        events: {
                            // On click, look for a detailed map
                            click: function () {
                                var key = this.key;
                                $('#mapDropdown option').each(function () {
                                    if (this.value === 'countries/' + key.substr(0, 2) + '/' + key + '-all.js') {
                                        $('#mapDropdown').val(this.value).change();
                                    }
                                });
                            }
                        }
                    }
                }, {
                    type: 'mapline',
                    name: "Separators",
                    data: Highcharts.geojson(mapGeoJSON, 'mapline'),
                    nullColor: 'gray',
                    showInLegend: false,
                    enableMouseTracking: false
                }]
            };

            options = $.extend(true, {}, Highcharts.theme, options);


            ele.highcharts('Map', options);

        }

        return {
            restrict: "AE",
            // scope:false,
            link: function (scope, ele, attr) {

                attr.$observe('areadata', function (value) {
                    if ((attr.areadata.replace(/(^s*)|(s*$)/g, "").length == 0)) {
                        return;
                    }
                    var data = JSON.parse(attr.areadata);
                    if (data.length > 0) {
                        draw(scope, ele, data);
                    }
                });


            }
        }
    }])

    .directive("dateFormat", ["$timeout", function ($timeout) {
        return {
            restrict: "AE",
            scope: false,
            link: function (scope, ele, attr) {
                //   var calculate = ele.datetimepicker({sideBySide: true, format: "YYYY-MM-DD HH:mm"});
                var calculate = ele.datetimepicker({sideBySide: true, format: "YYYY-MM-DD"});
            }
        }
    }])

    .directive("pieChart", ["$timeout", function ($timeout) {

        function draw(scope, ele, serialdata) {
            // Build the chart
            ele.highcharts({
                chart: {
                    plotBackgroundColor: null,
                    plotBorderWidth: null,
                    plotShadow: false
                },
                title: {
                    text: '用户使用的机型访问统计'
                },
                tooltip: {
                    pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
                },
                plotOptions: {
                    pie: {
                        allowPointSelect: true,
                        cursor: 'pointer',
                        dataLabels: {
                            enabled: false
                        },
                        showInLegend: true
                    }
                },
                series: [{
                    type: 'pie',
                    name: 'Browser share',
                    data: serialdata
                }]
            });

        }

        return {
            restrict: "AE",
            // scope:false,
            link: function (scope, ele, attr) {

                attr.$observe('phonedata', function (value) {
                    if ((attr.phonedata.replace(/(^s*)|(s*$)/g, "").length == 0)) {
                        return;
                    }
                    var data = JSON.parse(attr.phonedata);
                    if (data.length > 0) {
                        draw(scope, ele, data);
                    }
                });
            }
        }
    }])
    .directive('dismiss', ['$rootScope', '$timeout', function ($rootScope, $timeout) {
        return {
            restrict: 'A',
            link: function (scope, $element, attrs) {
                $element.on('click', function () {
                    $timeout(function () {
                        var $context = $element.closest('.' + attrs.dismiss);
                        var $contextScope = $context.scope();
                        $contextScope.$apply(function () {
                            $contextScope.isOpen = false;
                        });
                    });
                });
            }
        };
    }])
    .directive('qrcodeRepeatFinish', function () {
        return {
            restrict: 'A',
            link: function (scope, element, attr) {
                var url = "http://duodianyingxiao.com/seastar/open5/editpreview.html?documentId=" + attr["documentid"];
                //if (scope.$last == true) {
                element.find(".code").qrcode({
                    width: 120,
                    height: 120,
                    text: url
                });
                //  }
            }
        };
    })
    .directive('templatePreview', ['$rootScope', '$timeout', 'activityService', function ($rootScope, $timeout, activityService) {
        return {
            restrict: 'A',
            link: function (scope, elem, attrs) {
                var page = window.Player.initPage(elem);

                activityService.queryDocument(attrs["documentid"]).success(function (resp) {
                    var elehtml = page.build(resp.pages[0], elem.width(), elem.height());
                    elem.html(elehtml);
                });

            }
        };
    }])
    .directive('baiduShare', function () {
        return {
            restrict: 'E',
            link: function (scope, element, attr) {
                console.log(attr);
                window._bd_share_main = null;
                window._bd_share_config = {
                    common: {
                        bdText: '自定义分享内容',
                        bdDesc: '自定义分享摘要',
                        bdUrl: 'http://duodianyingxiao.com/seastar/open5/share.html?spreadFlag=1&activityId=' + attr.activityid + '&whoshares=' + attr.activityid
                        //  bdPic: '自定义分享图片'
                    },
                    share: [{
                        "bdSize": 32
                    }]
                };
                with (document)0[(getElementsByTagName('head')[0] || body).appendChild(createElement('script')).src = 'http://bdimg.share.baidu.com/static/api/js/share.js?cdnversion=' + ~(-new Date() / 36e5)];
            }
        };
    })
    .directive('spreadqrcode', function () {
        return {
            restrict: 'E',
            link: function (scope, element, attr) {
                var url = "http://duodianyingxiao.com/seastar/open5/share.html?spreadFlag=1&activityId=" + attr["activityid"] + "&whoshares=" + attr["mobileno"];

                element.qrcode({
                    width: 100,
                    height: 100,
                    text: url
                });
            }
        };
    })
;

