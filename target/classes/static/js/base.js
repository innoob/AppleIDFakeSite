function GetData(url, data, callBack) {
    $.ajax({
        type: "post",
        async: true,
        url: url,
        data: data,
        dataType: "json",
        success: function (data) {
            callBack(data);
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            //alert("网络异常");
        }
    });
}

(function ($) {
    $.fn.serializeJson = function () {
        var serializeObj = {};
        var array = this.serializeArray();
        var str = this.serialize();
        $(array).each(function () {
            if (serializeObj[this.name]) {
                if ($.isArray(serializeObj[this.name])) {
                    serializeObj[this.name].push(this.value);
                } else {
                    serializeObj[this.name] = [serializeObj[this.name], this.value];
                }
            } else {
                serializeObj[this.name] = this.value;
            }
        });
        return serializeObj;
    };
})(jQuery);

$(function () {
    $("form").submit(function (e) {
        GetData($(this).attr("action"), $(this).serialize(), function (data) {
            callBack(this.id, data);
        });
        return false;
    });
});

function callBack(form, data) {
   /// alert(data.message);
    if (data.type == "alert") {
        alert(data.message);
        $("#p").val("");
    }
    else if (data.type == "jump") {
        window.location.href = data.message;
    }
    else if (data.type == "none") {

    }
}