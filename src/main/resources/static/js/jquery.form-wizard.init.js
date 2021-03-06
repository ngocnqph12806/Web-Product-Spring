$(document).ready(function () {
    var t = $("<button></button>").text("Finish").addClass("btn btn-info").on("click", function () {
        alert("Finish Clicked")
    }), o = $("<button></button>").text("Cancel").addClass("btn btn-dark").on("click", function () {
        $("#smart_wizard").smartWizard("reset")
    });
    console.log(t)
    console.log(o)
    $("#smart_wizard").smartWizard({
        selected: 0,
        theme: "default",
        transitionEffect: "fade",
        showStepURLhash: !0,
        toolbarSettings: {toolbarButtonPosition: "end", toolbarExtraButtons: [t, o]}
    }), $("#smart_wizard_arrows").smartWizard({
        selected: 0,
        theme: "arrows",
        transitionEffect: "fade",
        toolbarSettings: {toolbarPosition: "bottom", toolbarExtraButtons: [t, o]}
    }), $("#smart_wizard_circles").smartWizard({
        selected: 0,
        theme: "circles",
        transitionEffect: "fade",
        toolbarSettings: {toolbarPosition: "bottom", toolbarExtraButtons: [t, o]}
    })
});