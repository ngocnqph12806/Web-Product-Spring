!function (n) {
    "use strict";
    var t = function () {
    };
    t.prototype.init = function () {
        n(".colorpicker-default").colorpicker({format: "hex"}), n(".colorpicker-rgba").colorpicker(), jQuery("#datepicker").datepicker(), jQuery("#datepicker-autoclose").datepicker({
            autoclose: !0,
            todayHighlight: !0
        }), jQuery("#datepicker-inline").datepicker(), jQuery("#datepicker-multiple-date").datepicker({
            format: "mm/dd/yyyy",
            clearBtn: !0,
            multidate: !0,
            multidateSeparator: ","
        }), jQuery("#date-range").datepicker({toggleActive: !0}), n("input#defaultconfig").maxlength({
            warningClass: "badge badge-info",
            limitReachedClass: "badge badge-warning"
        }), n("input#thresholdconfig").maxlength({
            threshold: 20,
            warningClass: "badge badge-info",
            limitReachedClass: "badge badge-warning"
        }), n("input#moreoptions").maxlength({
            alwaysShow: !0,
            warningClass: "badge badge-success",
            limitReachedClass: "badge badge-danger"
        }), n("input#alloptions").maxlength({
            alwaysShow: !0,
            warningClass: "badge badge-success",
            limitReachedClass: "badge badge-danger",
            separator: " out of ",
            preText: "You typed ",
            postText: " chars available.",
            validate: !0
        }), n("textarea#textarea").maxlength({
            alwaysShow: !0,
            warningClass: "badge badge-info",
            limitReachedClass: "badge badge-warning"
        }), n("input#placement").maxlength({
            alwaysShow: !0,
            placement: "top-left",
            warningClass: "badge badge-info",
            limitReachedClass: "badge badge-warning"
        }), n(".vertical-spin").TouchSpin({
            verticalbuttons: !0,
            verticalupclass: "ion-plus-round",
            verticaldownclass: "ion-minus-round",
            buttondown_class: "btn btn-primary",
            buttonup_class: "btn btn-primary"
        }), n("input[name='demo1']").TouchSpin({
            min: 0,
            max: 100,
            step: .1,
            decimals: 2,
            boostat: 5,
            maxboostedstep: 10,
            postfix: "%",
            buttondown_class: "btn btn-primary",
            buttonup_class: "btn btn-primary"
        }), n("input[name='demo2']").TouchSpin({
            min: -1e9,
            max: 1e9,
            stepinterval: 50,
            maxboostedstep: 1e7,
            prefix: "$",
            buttondown_class: "btn btn-primary",
            buttonup_class: "btn btn-primary"
        }), n("input[name='demo3']").TouchSpin({
            buttondown_class: "btn btn-primary",
            buttonup_class: "btn btn-primary"
        }), n("input[name='demo3_21']").TouchSpin({
            initval: 40,
            buttondown_class: "btn btn-primary",
            buttonup_class: "btn btn-primary"
        }), n("input[name='demo3_22']").TouchSpin({
            initval: 40,
            buttondown_class: "btn btn-primary",
            buttonup_class: "btn btn-primary"
        }), n("input[name='demo5']").TouchSpin({
            prefix: "pre",
            postfix: "post",
            buttondown_class: "btn btn-primary",
            buttonup_class: "btn btn-primary"
        }), n("input[name='demo0']").TouchSpin({
            buttondown_class: "btn btn-primary",
            buttonup_class: "btn btn-primary"
        }), n("#mdate").bootstrapMaterialDatePicker({
            weekStart: 0,
            time: !1
        }), n("#timepicker").bootstrapMaterialDatePicker({
            format: "HH:mm",
            time: !0,
            date: !1
        }), n("#date-format").bootstrapMaterialDatePicker({format: "dddd DD MMMM YYYY - HH:mm"}), n("#min-date").bootstrapMaterialDatePicker({
            format: "DD/MM/YYYY HH:mm",
            minDate: new Date
        }), n("#single-input").clockpicker({
            placement: "bottom",
            align: "left",
            autoclose: !0,
            default: "now"
        }), n(".clockpicker").clockpicker({donetext: "Done"}).find("input").change(function () {
            console.log(this.value)
        }), n("#check-minutes").click(function (t) {
            t.stopPropagation(), input.clockpicker("show").clockpicker("toggleView", "minutes")
        }), n(".colorpicker").asColorPicker(), n(".gradient-colorpicker").asColorPicker({mode: "gradient"}), n(".complex-colorpicker").asColorPicker({mode: "complex"}), n(".select2").select2({width: "100%"}), n('input[name="dates"]').daterangepicker({alwaysShowCalendars: !0}), n(".open_picker").show(), n('input[name="daterange"]').daterangepicker({opens: "left"}, function (t, e, a) {
            console.log("A new date selection was made: " + t.format("YYYY-MM-DD") + " to " + e.format("YYYY-MM-DD"))
        }), n('input[name="datetimes"]').daterangepicker({
            timePicker: !0,
            startDate: moment().startOf("hour"),
            endDate: moment().startOf("hour").add(32, "hour"),
            locale: {format: "M/DD hh:mm A"}
        }), n('input[data-type="date"]').daterangepicker({
            singleDatePicker: !0,
            showDropdowns: !0,
            minYear: 1901,
            maxYear: parseInt(moment().format("YYYY"), 10)
        }, function (t, e, a) {
            // var n = moment().diff(t, "years") + '';
            // if (Number(n) < 18) {
            //     swal("Lỗi", "Bạn chưa đủ 18 tuổi", "warning")
            // }
            // alert("You are " + n + " years old!")
        }), n('input[data-type="date-event"]').daterangepicker({
            singleDatePicker: !0,
            showDropdowns: !0,
            minYear: 2010,
            maxYear: parseInt(moment().format("YYYY"), 0)
        }, function (t, e, a) {
            // var n = moment().diff(t, "years") + '';
            // if (Number(n) < 18) {
            //     swal("Lỗi", "Bạn chưa đủ 18 tuổi", "warning")
            // }
            // alert("You are " + n + " years old!")
        });
        var t = moment(), e = moment().subtract(-29, "days");

        function a(t, e) {
            n("#date-time-voucher span").html(t.format("MMMM D, YYYY") + " - " + e.format("MMMM D, YYYY"))
        }

        n("#date-time-voucher").daterangepicker({
            startDate: t,
            endDate: e,
            ranges: {
                "Hôm nay": [moment(), moment()],
                "Ngày mai": [moment().subtract(-1, "days"), moment().subtract(-1, "days")],
                "7 ngày tới": [moment(), moment().subtract(-6, "days")],
                "30 ngày tới": [ moment(),moment().subtract(-29, "days")],
                "Tháng này": [moment().startOf("month"), moment().endOf("month")],
                "Tháng sau": [moment().subtract(-1, "month").startOf("month"), moment().subtract(-1, "month").endOf("month")]
            }
        }, a), a(t, e)
    }, n.AdvancedForm = new t, n.AdvancedForm.Constructor = t
}(window.jQuery), function (t) {
    "use strict";
    window.jQuery.AdvancedForm.init()
}();