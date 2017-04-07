var myChart = echarts.init(document.getElementById('main'));

var option = {
	title: {
		text: 'Statistics:Task priority'
	},
    tooltip: {},
    legend: {
        data:['Priority']
    },
    xAxis: {
    },
    yAxis: {
    	data: ["P1","P2","P3","P4"]
    },
    series: [{
        name: '',
        type: 'bar',
        data: [1, 15, 6, 4]
    }]
};

// 使用刚指定的配置项和数据显示图表。
myChart.setOption(option);