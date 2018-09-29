function doDelete() {
    if ($(':checkbox:checked').length > 0) {
        var ids = $.map($(":checkbox:checked"), function (el) {
            return $(el).val();
        });
        $.ajax({
            type: "DELETE",
            url: document.location.href + getCheckBoxes(),
            success: function () {
                ids.forEach(function (element) {
                    document.getElementById(element).remove();
                });
            },
        });
    }
}

function doPut() {
    if ($(':checkbox:checked').length > 0) {
        var ids = $.map($(":checkbox:checked"), function (el) {
            return $(el).val();
        });
        $.ajax({
            type: "PUT",
            url: document.location.href + getCheckBoxes(),
            success: function () {
                ids.forEach(function (element) {
                    document.getElementById(element).remove();
                });
            },

        });
    }
}

function doClear() {
    var ids = $.map($(":checkbox"), function (el) {
        return $(el).val();
    });
    $.ajax({
        type: "DELETE",
        url: document.location.href + getAllCheckBoxes(),

        success: function () {
            ids.forEach(function (element) {
                document.getElementById(element).remove();
            });
        },

    });
}


function getAllCheckBoxes() {
    var ids = "id=";
    ids = ids + $.map($(":checkbox"), function (el) {
        return $(el).val();
    });
    ids = ids.replace(/,/gi, "&id=");
    return "?" + ids;
}

function getCheckBoxes() {
    var ids = "id=";
    ids = ids + $.map($(":checkbox:checked"), function (el) {
        return $(el).val();
    });
    ids = ids.replace(/,/gi, "&id=");
    return "?" + ids;
}


function doDeleteFile(id) {
    if (id == undefined) {
        id = "";
    }
    $.ajax({
        type: "DELETE",
        url: document.location.href.replace(/list.+/gi, "list/") + "/file?id=" + id,
        success: function () {
            document.location.href = document.location.href;
        },

    });
}