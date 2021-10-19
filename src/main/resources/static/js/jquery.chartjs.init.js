!function (i) {
    "use strict";
    var e = function () {
    };
    e.prototype.respChart = function (e, r, a, o) {
        var t = e.get(0).getContext("2d"), n = i(e).parent();

        function s() {
            e.attr("width", i(n).width());
            switch (r) {
                case"Line":
                    new Chart(t, {type: "line", data: a, options: o});
                    break;
                case"Doughnut":
                    new Chart(t, {type: "doughnut", data: a, options: o});
                    break;
                case"Pie":
                    new Chart(t, {type: "pie", data: a, options: o});
                    break;
                case"Bar":
                    new Chart(t, {type: "bar", data: a, options: o});
                    break;
                case"Radar":
                    new Chart(t, {type: "radar", data: a, options: o});
                    break;
                case"PolarArea":
                    new Chart(t, {data: a, type: "polarArea", options: o})
            }
        }

        i(window).resize(s), s()
    }, e.prototype.init = function () {
        let dom = $('#doanhSo')
        let arr = dom[0].dataset.ds
        let arrData = []
        for (let j = 0; j < arr.length; j++) {
            if (Number(arr[j]) || arr[j] === '0') {
                arrData.push(Number(arr[j]))
            }
        }
        Array.prototype.max = function () {
            return Math.max.apply(null, this);
        };
        Array.prototype.min = function () {
            return Math.min.apply(null, this);
        };
        let min = Math.min.apply(null, arrData) - 10;
        let max = Math.max.apply(null, arrData) + 10;
        this.respChart(i("#lineChart"), "Line", {
            labels: ["Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5", "Tháng 6", "Tháng 7", "Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12"],
            datasets: [{
                label: "Doanh số bán hàng trong năm",
                fill: !1,
                backgroundColor: "#00bcd4",
                borderColor: "#00bcd4",
                data: arrData
            }]
        }, {
            responsive: !0,
            tooltips: {mode: "index", intersect: !1},
            hover: {mode: "nearest", intersect: !0},
            scales: {
                xAxes: [{display: !0, gridLines: {color: "rgba(0,0,0,0.1)"}}],
                yAxes: [{
                    gridLines: {color: "rgba(255,255,255,0.05)", fontColor: "#fff"},
                    ticks: {max: max, min: min, stepSize: 10}
                }]
            }
        });
        let doanhThu = $('#doanhThu')
        let arrDt = doanhThu[0].dataset.ds.replace("[", "").replace("]", "").split(",")
        console.log(arrDt)
        let arrDoanhThu = []
        for (let j = 0; j < arrDt.length; j++) {
            arrDoanhThu.push(Number(arrDt[j]))
        }
        let minDoanhThu = Math.min.apply(null, arrDoanhThu) - 1000;
        let maxDoanhThu = Math.max.apply(null, arrDoanhThu) + 1000;
        console.log(arrDoanhThu)
        this.respChart(i("#canvasDoanhThu"), "Line", {
            labels: ["Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5", "Tháng 6", "Tháng 7", "Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12"],
            datasets: [{
                label: "Doanh thu bán hàng trong năm",
                fill: !1,
                backgroundColor: "yellow",
                borderColor: "red",
                data: arrDoanhThu
            }]
        }, {
            responsive: !0,
            tooltips: {mode: "index", intersect: !1},
            hover: {mode: "nearest", intersect: !0},
            scales: {
                xAxes: [{display: !0, gridLines: {color: "rgba(0,0,0,0.1)"}}],
                yAxes: [{
                    gridLines: {color: "rgba(255,255,255,0.05)", fontColor: "orange"},
                    ticks: {max: minDoanhThu, min: maxDoanhThu, stepSize: 50000}
                }]
            }
        });
        this.respChart(i("#doughnut"), "Doughnut", {
            labels: ["Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5", "Tháng 6", "Tháng 7", "Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12"],
            datasets: [{
                data: arrDoanhThu,
                backgroundColor: ["#f7931a", "#1ecab8", "#e3eaef", "#1c75bc", "#000000", "#FF0000", "#800080",
                    "#000080", "#008080", "#800080", "#808000", "#00FFFF"],
                hoverBackgroundColor: ["#f7931a", "#1ecab8", "#e3eaef", "#1c75bc", "#000000", "#FF0000", "#800080",
                    "#000080", "#008080", "#800080", "#808000", "#00FFFF"],
                hoverBorderColor: "#fff"
            }]
        });
        this.respChart(i("#pie"), "Pie", {
            labels: ["Desktops", "Tablets", "Mobiles", "Mobiles"],
            datasets: [{
                data: [80, 50, 100, 121],
                backgroundColor: ["#194a8b", "#00264a", "#e3eaef", "#44a2d2"],
                hoverBackgroundColor: ["#194a8b", "#00264a", "#e3eaef", "#44a2d2"],
                hoverBorderColor: "#fff"
            }]
        });
        this.respChart(i("#bar"), "Bar", {
            labels: ["January", "February", "March", "April", "May", "June", "July"],
            datasets: [{
                label: "Sales Analytics",
                backgroundColor: "rgba(68, 162, 210, 0.4)",
                borderColor: "#44a2d2",
                borderWidth: 2,
                barPercentage: .3,
                categoryPercentage: .5,
                hoverBackgroundColor: "rgba(68, 162, 210, 0.7)",
                hoverBorderColor: "#44a2d2",
                data: [65, 59, 80, 81, 56, 55, 40, 20]
            }]
        }, {responsive: !0, scales: {xAxes: [{barPercentage: .8, categoryPercentage: .4, display: !0}]}});
        this.respChart(i("#radar"), "Radar", {
            labels: ["Eating", "Drinking", "Sleeping", "Designing", "Coding", "Cycling", "Running"],
            datasets: [{
                label: "Desktops",
                backgroundColor: "rgba(152,212,206,0.3)",
                borderColor: "#98d4ce",
                pointBackgroundColor: "#038660",
                pointBorderColor: "#fff",
                pointHoverBackgroundColor: "#fff",
                pointHoverBorderColor: "rgba(179,181,198,1)",
                data: [65, 59, 90, 81, 56, 55, 40]
            }, {
                label: "Tablets",
                backgroundColor: "rgba(21,128,196,0.2)",
                borderColor: "#1580c4",
                pointBackgroundColor: "#095d88",
                pointBorderColor: "#fff",
                pointHoverBackgroundColor: "#fff",
                pointHoverBorderColor: "rgba(255,99,132,1)",
                data: [28, 48, 40, 19, 96, 27, 100]
            }]
        });
        this.respChart(i("#polarArea"), "PolarArea", {
            datasets: [{
                data: [11, 16, 7, 18],
                backgroundColor: ["#1580c4", "#162546", "#ebeff2", "#ea3c75"],
                label: "My dataset",
                hoverBorderColor: "#fff"
            }], labels: ["Series 1", "Series 2", "Series 3", "Series 4"]
        })
    }, i.ChartJs = new e, i.ChartJs.Constructor = e
}(window.jQuery), function (e) {
    "use strict";
    window.jQuery.ChartJs.init()
}();