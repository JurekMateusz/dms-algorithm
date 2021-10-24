function showChart(myJson) {
  let data = '[';
  for (let i = 0; i < myJson.length; i++) {
    if (i !== 0) {
      data += ","
    }
    // console.log(myJson[i]);
    // console.log(myJson[i].taskName);
    // console.log(taskWithColor.get(myJson[i].taskName));
    data += JSON.stringify({
          x: myJson[i].taskName,
          y: [
            myJson[i].start,
            myJson[i].end
          ],
          fillColor: taskWithColor.get(myJson[i].taskName)
        }
    )
  }
  data += ']'
  console.log(JSON.parse(data));

  let options = {
    series: [
      {
        type: 'line',
        data: JSON.parse(data)//{x:'Z1',y:[0,5],fillColor: '#FF0000'}
      }
    ],
    chart: {
      height: '80%',
      width: '80%',
      type: 'rangeBar'
    },
    plotOptions: {
      bar: {
        horizontal: true
      }
    },
    dataLabels: {
      enabled: true,
      formatter: function (val) {
        return "from " + val[0] + " to " + val[1]
      }
    },
    xaxis: {
      type: 'numeric'
    }
  };

  let chart = new ApexCharts(document.querySelector(".tasks-diagram"), options);
  chart.render();
}