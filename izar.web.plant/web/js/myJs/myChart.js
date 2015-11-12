/*
 *图表类信息
 */

require.config({//引入一次
    paths: {
        echarts: 'dist'
    }
});
function setOption1() {
    myChart = null;
    //按需加载
    require(['echarts',
        'echarts/chart/gauge' // 使用柱状图就加载bar模块，按需加载
    ],
            function (ec) {
                myChart = ec.init(document.getElementById('chart'));
                var option = {
                    tooltip: {
                        formatter: "{a} <br/>{b} : {c}%"
                    },
                    toolbox: {
                        show: true,
                        feature: {
                            mark: {
                                show: true
                            },
                            restore: {
                                show: true
                            },
                            saveAsImage: {
                                show: true
                            }
                        }
                    },
                    series: [{
                            name: '个性化仪表盘',
                            type: 'gauge',
                            center: ['50%', '50%'],
                            // 默认全局居中
                            radius: [0, '100%'],
                            min: 0,
                            // 最小值
                            max: 110,
                            // 最大值
                            precision: 0,
                            // 小数精度，默认为0，无小数点
                            splitNumber: 11,
                            // 分割段数，默认为5
                            axisLine: {// 坐标轴线
                                show: true,
                                // 默认显示，属性show控制显示与否
                                lineStyle: {// 属性lineStyle控制线条样式
                                    color: [[0.32, '#52CA3F'], [0.38, '#C5E131'], [0.45, '#FCED15'], [0.49, '#F79169'], [0.5, '#DC563F'], [0.55, '#CF232C'], [0.6, '#DD2427'], [0.8, '#5E1D48'], [1, '#291840']],
                                    width: 20
                                }
                            },
                            axisTick: {// 坐标轴小标记
                                show: true,
                                // 属性show控制显示与否，默认不显示
                                splitNumber: 0,
                                // 每份split细分多少段
                                length: 8,
                                // 属性length控制线长
                                lineStyle: {// 属性lineStyle控制线条样式
                                    color: '#eee',
                                    width: 1,
                                    type: 'solid'
                                }
                            },
                            axisLabel: {// 坐标轴文本标签，详见axis.axisLabel
                                show: true,
                                formatter: function (v) {
                                    switch (v + '') {
                                        case '0':
                                            return 'MIN';
                                        case '70':
                                            return 'FAST';
                                        case '100':
                                            return 'MAX';
                                        default:
                                            return '';
                                    }
                                },
                                textStyle: {// 其余属性默认使用全局文本样式，详见TEXTSTYLE
                                    color: '#333'
                                }
                            },
                            splitLine: {// 分隔线
                                show: true,
                                // 默认显示，属性show控制显示与否
                                length: 30,
                                // 属性length控制线长
                                lineStyle: {// 属性lineStyle（详见lineStyle）控制线条样式
                                    color: '#eee',
                                    width: 2,
                                    type: 'solid'
                                }
                            },
                            pointer: {
                                length: '80%',
                                width: 8,
                                color: 'auto'
                            },
                            title: {
                                show: true,
                                // offsetCenter: ['-65%', -10],       // x, y，单位px
                                textStyle: {// 其余属性默认使用全局文本样式，详见TEXTSTYLE
                                    color: '#333',
                                    fontSize: 15
                                }
                            },
                            detail: {
                                show: true,
                                backgroundColor: 'rgba(0,0,0,0)',
                                borderWidth: 0,
                                borderColor: '#ccc',
                                width: 100,
                                height: 40,
                                // offsetCenter: ['-60%', 10],       // x, y，单位px
                                formatter: '{value}%',
                                textStyle: {// 其余属性默认使用全局文本样式，详见TEXTSTYLE
                                    color: 'auto',
                                    fontSize: 30
                                }
                            },
                            data: [{
                                    value: 50,
                                    name: 'DOWNLOADS'
                                }]
                        }]
                };
                //随机数(四舍五入到两位数的0-100)
                /*clearInterval(timeTicket);*/
                // timeTicket = setInterval(function (){
                option.series[0].data[0].value = (Math.random() * 100).toFixed(2) - 0;
                myChart.setOption(option, true);
                //},2000);

                // myChart.setOption(option);
            });

}
function setOption2() {
    myChart = null;
    //公共数据转换方法 第一个参数要保证是需要转换的数据
    function setConversionData() {
        var len = arguments.length;
        var arr = new Array(arguments.length - 1); //保存所有的属性对应值
        for (var i = 0; i < arguments.length - 1; i++) {
            arr[i] = []; //创建一个固定长度的二维数组
        }
        var data = arguments[0]; //需要转换的数据
        for (var i = 0; i < arr.length; i++) {
            for (var j = 0; j < data.length; j++) {
                arr[i].push(data[j][arguments[i + 1]]);
            }
        }
        return arr;
    }
    var data = [{
            "areaSum": "10",
            "cuCount": "1807",
            "street": "045-B2二包机"
        },
        {
            "areaSum": "-15",
            "cuCount": "-1807",
            "street": "038-A2二包机"
        },
        {
            "areaSum": "25",
            "cuCount": "768",
            "street": "007生物炉"
        },
        {
            "areaSum": "-18",
            "cuCount": "-768",
            "street": "007生物炉"
        },
        {
            "areaSum": "30",
            "cuCount": "725",
            "street": "005总控"
        },
        {
            "areaSum": "35",
            "cuCount": "-725",
            "street": "054D1一包机"
        },
        {
            "areaSum": "40",
            "cuCount": "1807",
            "street": "045-B2二包机"
        },
        {
            "areaSum": "45",
            "cuCount": "-1807",
            "street": "038-A2二包机"
        },
        {
            "areaSum": "50",
            "cuCount": "768",
            "street": "007生物炉"
        },
        {
            "areaSum": "55",
            "cuCount": "-768",
            "street": "007生物炉"
        },
        {
            "areaSum": "70",
            "cuCount": "725",
            "street": "005总控"
        },
        {
            "areaSum": "75",
            "cuCount": "-725",
            "street": "054D1一包机"
        }];
    var xData = setConversionData(data, "street", "cuCount", "areaSum");
    require(['echarts', 'echarts/chart/bar', 'echarts/chart/line'],
            function (ec) {
                myChart = ec.init(document.getElementById('chart'));
                var option = {
                    tooltip: {
                        trigger: 'axis'
                    },
                    toolbox: {
                        show: true,
                        feature: {
                            mark: {
                                show: true
                            },
                            dataView: {
                                show: true,
                                readOnly: false
                            },
                            magicType: {
                                show: true,
                                type: ['bar']
                            },
                            restore: {
                                show: true
                            },
                            saveAsImage: {
                                show: true
                            }
                        }
                    },
                    calculable: true,
                    color: ['#81ACD9', '#BE4B48'],
                    xAxis: [{
                            type: 'category',
                            // axisLine: {onZero: true},
                            data: xData[0]
                        }

                    ],
                    yAxis: [{
                            type: 'value',
                            axisLabel: {
                                formatter: '{value} '
                            }
                        },
                        {
                            type: 'value',
                            axisLabel: {
                                formatter: '{value}%'
                            }
                        }],
                    series: [{
                            name: '户数',
                            type: 'bar',
                            data: xData[1]
                        },
                        {
                            name: '供热面积',
                            type: 'line',
                            yAxisIndex: 1,
                            //指明为第二个y轴
                            data: xData[2]
                        }]
                };

                myChart.setOption(option);
            });
}
function setOption3() {
    myChart = null;
    require(['echarts', 'echarts/chart/bar', ],
            function (ec) {
                var myChart = ec.init(document.getElementById('chart'));
                myChart.setOption({
                    title: {
                        text: '4月份包装车间用电情况'
                                // subtext: 'From ExcelHome',
                                // sublink: 'http://e.weibo.com/1341556070/Aj1J2x5a5'
                    },
                    tooltip: {
                        trigger: 'axis',
                        axisPointer: {// 坐标轴指示器，坐标轴触发有效
                            type: 'shadow' // 默认为直线，可选为：'line' | 'shadow'
                        },
                        formatter: function (params) {
                            var tar;
                            if (params[1].value != '-') {
                                tar = params[1];
                            } else {
                                tar = params[0];
                            }
                            return tar.name + '<br/>' + tar.seriesName + ' : ' + tar.value;
                        }
                    },
                    legend: {
                        data: ['支出', '收入']
                    },
                    toolbox: {
                        show: true,
                        feature: {
                            mark: {
                                show: true
                            },
                            dataView: {
                                show: true,
                                readOnly: false
                            },
                            restore: {
                                show: true
                            },
                            saveAsImage: {
                                show: true
                            }
                        }
                    },
                    xAxis: [{
                            type: 'category',
                            splitLine: {
                                show: false
                            },
                            data: function () {
                                var list = [];
                                for (var i = 1; i <= 11; i++) {
                                    list.push('04月' + i + '日');
                                }
                                return list;
                            }()
                        }],
                    yAxis: [{
                            type: 'value'
                        }],
                    series: [{
                            name: '辅助',
                            type: 'bar',
                            stack: '总量',
                            itemStyle: {
                                normal: {
                                    barBorderColor: 'rgba(0,0,0,0)',
                                    color: 'rgba(0,0,0,0)'
                                },
                                emphasis: {
                                    barBorderColor: 'rgba(0,0,0,0)',
                                    color: 'rgba(0,0,0,0)'
                                }
                            },
                            data: [0, 900, 1245, 1530, 1376, 1376, 1511, 1689, 1856, 1495, 1292]
                        },
                        {
                            name: '收入',
                            type: 'bar',
                            stack: '总量',
                            itemStyle: {
                                normal: {
                                    label: {
                                        show: true,
                                        position: 'top'
                                    }
                                }
                            },
                            data: [900, 345, 393, '-', '-', 135, 178, 286, '-', '-', '-']
                        },
                        {
                            name: '支出',
                            type: 'bar',
                            stack: '总量',
                            itemStyle: {
                                normal: {
                                    label: {
                                        show: true,
                                        position: 'bottom'
                                    }
                                }
                            },
                            data: ['-', '-', '-', 108, 154, '-', '-', '-', 119, 361, 203]
                        }]
                }

                );

            }

    );

}
function setOption4() {
    myChart = null;
    require(['echarts', 'echarts/chart/bar', 'echarts/chart/line'],
            function (ec) {
                var myChart = ec.init(document.getElementById('chart'));
                myChart.setOption({
                    legend: {
                        data: ['Y15和Y14电能同比情况']
                    },
                    toolbox: {
                        show: true,
                        feature: {
                            mark: {
                                show: true
                            },
                            dataView: {
                                show: true,
                                readOnly: false
                            },
                            magicType: {
                                show: true,
                                type: ['line', 'bar']
                            },
                            restore: {
                                show: true
                            },
                            saveAsImage: {
                                show: true
                            }
                        }
                    },
                    calculable: true,
                    tooltip: {
                        trigger: 'axis',
                        formatter: function (v) {//console.log(v.toSource()+"========"+typeof(v)+"     "+v[0].value+"  "+v[0].name);
                            return  "同比 : <br/>" + v[0].name + " :" + (v[0].value * 100).toFixed(2) + "%";
                        }
                    },
                    xAxis: [
                        {
                            type: 'category',
                            data: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12]
                        }
                    ],
                    yAxis: [
                        {
                            type: 'value',
                            axisLine: {
                                onZero: false
                            },
                            axisLabel: {
                                formatter: '{value} '
                            },
                            scale: true,
                            power: 0.1,
                            precision: 0.1, // set the Y range
                            splitNumber: 10
                                    //                   , data : [0.10, 0.20, 0.30, 0.40, 0.50,0.60, 0.70, 0.80,0.90]
                        }
                    ],
                    series: [
                        {
                            name: 'Y15和Y14电能同比情况',
                            type: 'line',
                            smooth: false, //折或者曲线
                            itemStyle: {
                                normal: {
                                    label: {
                                        show: true
                                        ,
                                        formatter: function (v) {
                                            return (v.data * 100).toFixed(2) + "%";
                                        }
                                    },
                                    lineStyle: {
                                        shadowColor: 'rgba(0,0,0,0.4)'
                                    }
                                }
                            },
                            data: [0.5583, 0.0520, 0.5598, 0.6080, .6918, .5889, .7622, .7027, .3566, .5476, .5420, .5352]
                        }
                    ]
                });
            });
}
function setOption5() {
    myChart = null;
    require(['echarts', 'echarts/chart/bar', ],
            function (ec) {
                var myChart = ec.init(document.getElementById('chart'));
                myChart.setOption({
                    tooltip: {
                        trigger: 'axis'
                    },
                    legend: {
                        data: ['Target', 'Actual', 'Cumlative ActualSaving']
                    },
                    toolbox: {
                        show: true,
                        feature: {
                            mark: {
                                show: true
                            },
                            dataView: {
                                show: true
                            },
                            magicType: {
                                show: true,
                                type: ['line', 'bar']
                            },
                            restore: {
                                show: true
                            },
                            saveAsImage: {
                                show: true
                            }
                        }
                    },
                    xAxis: [
                        {
                            type: 'category',
                            position: 'top',
                            data: ['Jan-14', 'Feb-14', 'Mar-14', 'Apr-14', 'May-14', 'Jun-14', 'Jul-14', 'Aug-14', 'Sep-14', 'Oct-14', 'Nov-14', 'Dec-14']
                        }
                    ],
                    yAxis: [
                        {
                            type: 'value',
                            position: 'left',
                            //min: 0,
                            //max: 300,
                            //splitNumber: 5,
                            boundaryGap: [0, 0.1],
                            axisLine: {// 轴线
                                show: false,
                                lineStyle: {
                                    color: 'red',
                                    type: 'dashed',
                                    width: 2
                                }
                            },
                            axisTick: {// 轴标记
                                show: true,
                                length: 10,
                                lineStyle: {
                                    color: 'green',
                                    type: 'solid',
                                    width: 2
                                }
                            },
                            axisLabel: {
                                show: true,
                                interval: 'auto', // {number}
                                // rotate: -45,
                                margin: 18,
                                formatter: function (v) {
                                    if (v < 0) {
                                        return "(" + -v + ")";
                                    }
                                    return -v;

                                }, // Template formatter!
                                textStyle: {
                                    color: '#DB0B0B',
                                    fontFamily: 'verdana',
                                    fontSize: 10,
                                    fontStyle: 'normal',
                                    fontWeight: 'bold'
                                }
                            },
                            splitLine: {
                                show: true,
                                lineStyle: {
                                    color: '#483d8b',
                                    type: 'dotted',
                                    width: 2
                                }
                            },
                            splitArea: {
                                show: false,
                                areaStyle: {
                                    //   color:['rgba(205,92,92,0.3)','rgba(255,215,0,0.3)']
                                }
                            }
                        },
                    ],
                    color: ['#81ACD9', '#BE4B48', '#ABC37D'],
                    series: [
                        {
                            name: 'Target',
                            type: 'bar',
                            data: (function () {
                                var oriData = [2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2, 32.6, 20.0, 6.4, 3.3];
                                var len = oriData.length;
                                while (len--) {
                                    oriData[len] *= -1;
                                }
                                return oriData;
                            })()
                        },
                        {
                            name: 'Actual',
                            type: 'bar',
                            data: (function () {
                                var oriData = [2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6, 182.2, 48.7, 18.8, 6.0, 2.3];
                                var len = oriData.length;
                                while (len--) {
                                    oriData[len] *= -1;
                                }
                                return oriData;
                            })()
                        },
                        {
                            name: 'Cumlative ActualSaving',
                            type: 'line',
                            yAxisIndex: 0,
                            data: (function () {
                                var oriData = [2.0, 2.2, 3.3, 4.5, 6.3, 10.2, 20.3, 23.4, 23.0, 16.5, 12.0, 6.2];
                                var len = oriData.length;
                                while (len--) {
                                    oriData[len] *= -1;
                                }
                                return oriData;
                            })()


                        }
                    ]
                });
            });

}
//超耗检测
function setTyperConsumptionTest() {
    myChart = null;
//    $("#chart").html('<div class="row"><div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main" id="gauge"></div></div>');
    $("#chart").html('<table border="0" cellpadding="0" cellspacing="0" width="100%"><tr><td style="width:25%;"><div style=" height:150px;" id="chart1"></div>	</td>	<td style="width:25%;"><div style=" height:150px;" id="chart2"></div></td>	<td style="width:25%;"><div  style=" height:150px;" id="chart3"></div></td><td><div style=" height:150px;" id="chart4"></div></td></tr><tr><td style="width:25%;"><div style=" height:150px;" id="chart5"></div>	</td>	<td style="width:25%;"><div style=" height:150px;" id="chart6"></div></td>	<td style="width:25%;"><div  style=" height:150px;" id="chart7"></div></td><td><div style=" height:150px;" id="chart8"></div></td></tr></table>');
    var option = {
        tooltip: {
            formatter: "{a} <br/>{b} : {c}%"
        },
        toolbox: {
            show: false
        },
        series: [{
                name: '个性化仪表盘',
                type: 'gauge',
                center: ['50%', '50%'],
                // 默认全局居中
                radius: [0, '100%'],
                min: 0,
                // 最小值
                max: 110,
                // 最大值
                precision: 0,
                // 小数精度，默认为0，无小数点
                splitNumber: 11,
                // 分割段数，默认为5
                axisLine: {// 坐标轴线
                    show: true
                },
                  axisLabel : { // 坐标轴文本标签，详见axis.axisLabel  
                      show:false,
                                textStyle : { // 其余属性默认使用全局文本样式，详见TEXTSTYLE  
                                    color : 'auto'  
                                }  
                            },
                axisTick: {// 坐标轴小标记
                    show: true,
                    // 属性show控制显示与否，默认不显示
                    splitNumber: 0,
                    // 每份split细分多少段
                    length: 8,
                    // 属性length控制线长
                    lineStyle: {// 属性lineStyle控制线条样式
                        color: '#eee',
                        width: 1,
                        type: 'solid'
                    }
                },
                splitLine: {// 分隔线
                    show: true,
                    // 默认显示，属性show控制显示与否
                    length: 30,
                    // 属性length控制线长
                    lineStyle: {// 属性lineStyle（详见lineStyle）控制线条样式
                        color: '#eee',
                        width: 2,
                        type: 'solid'
                    }
                },
                pointer: {
                    length: '80%',
                    width: 10,
                    color: 'auto'
                },
                title: {
                    show: true,
                    // offsetCenter: ['-65%', -10],       // x, y，单位px
                    textStyle: {// 其余属性默认使用全局文本样式，详见TEXTSTYLE
                        color: '#333',
                        fontSize: 15
                    }
                },
                detail: {
                    show: true,
                    backgroundColor: 'rgba(0,0,0,0)',
                    borderWidth: 0,
                    borderColor: '#ccc',
                    width: 100,
                    height: 40,
                    // offsetCenter: ['-60%', 10],       // x, y，单位px
                    formatter: '{value}%',
                    textStyle: {// 其余属性默认使用全局文本样式，详见TEXTSTYLE
                        color: 'auto',
                        fontSize: 30
                    }
                },
                data: [{
                        value: (Math.random() * 100).toFixed(2) 
                    }]
            }]
    };
    require(['echarts',
        'echarts/chart/gauge' // 使用柱状图就加载bar模块，按需加载
    ],
            function (ec) {
                for (var i = 1; i <= 8; i++) {
                    ec.init(document.getElementById('chart'+i )).setOption(option, true);
                }


            });
}