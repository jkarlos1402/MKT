function handleResponse(status) {
//    console.log("ya esta 2");
    if (status === 1) {
        PF('statusDialog').show();
//        PF('pbAjax').start();
    } else if (status === 2) {
//        PF('pbAjax').cancel();
        PF('statusDialog').hide();
    }
}

function setFilter(filter) {
    $("input[name='formLoad:tableInfo:columCategory:filter']").val(filter).keyup();
}

$(document).ready(function () {
    if ($("#menuGeneralDinamico").length > 0) {
        initMenu();        
    }
    $(".ui-outputlabel-rfi").hide();
});

function initMenu() {    
    $('#menuGeneralDinamico').dropdown_menu({
        sub_indicators: true,
        vertical: true
    });
}

function hideAsteriscos() {
    $(".ui-outputlabel-rfi").hide();
}

function showLoading(status) {
    console.log("ya esta 3 " + status);
    if (status === "1") {
        console.log("entro a mostrar");
        PF('statusDialog').show();
    } else if (status === "0") {
        console.log("entro a ocultar");
        PF('statusDialog').hide();
    }
}

