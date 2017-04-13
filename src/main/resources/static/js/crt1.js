var myChart = echarts.init(document.getElementById("my_chart"));
var colors = ['#5793f3', '#d14a61', '#675bba'];
var option = {
    title: {
        text: "Weekly task trend"
    },
    color: colors,
    legend: {
        data:['Tasks']
    },
    tooltip: {
        trigger: 'none',
        axisPointer: {
            type: 'cross'
        }
    },
    grid: {
        top: 70,
        bottom: 50
    },
    xAxis: {
        //load from map key
        type: 'category',
        axisLine: {
            onZero: false,
            lineStyle: {
                color: colors[1]
            }
        },
        axisPointer: {
            label: {
                formatter: function (params) {
                    return params.seriesData[0].data;
                }
            }
        },
        //inject this data
        data: [[${trends.key}]]
    },
    yAxis: [
        {
            type: 'value'
        }
    ],
    series: [{
        name: '',
        type: 'line',
        smooth: true,
        //inject this data
        data: [[${trends.data}]]
    }]
}
myChart.setOption(option);