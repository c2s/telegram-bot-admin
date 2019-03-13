$(function () {
    var $messageTableForm = $(".message-table-form");
    var settings = {
        url: ctx + "telegram/message",
        pageSize: 10,
        queryParams: function (params) {
            return {
                pageSize: params.limit,
                pageNum: params.offset / params.limit + 1,
                title: $messageTableForm.find("input[name='title']").val().trim(),
            };
        },
        columns: [{
            checkbox: true
        },
            {
                field: 'id',
                title: 'ID'
            }, {
                field: 'title',
                title: '标题'
            }, {
                field: 'status',
                title: '状态'
            }, {
                field: 'created',
                title: '创建时间'
            }, {
                field: 'updated',
                title: '更新时间'
            }
        ]
    };

    $MB.initTable('telegramMessageTable', settings);
});

function search() {
    $MB.refreshTable('telegramMessageTable');
}

function refresh() {
    $(".message-table-form")[0].reset();
    search();
}

function deleteLogs() {
    var selected = $("#telegramMessageTable").bootstrapTable('getSelections');
    var selected_length = selected.length;
    if (!selected_length) {
        $MB.n_warning('请勾选需要删除的日志！');
        return;
    }
    var ids = "";
    for (var i = 0; i < selected_length; i++) {
        ids += selected[i].id;
        if (i !== (selected_length - 1)) ids += ",";
    }

    $MB.confirm({
        text: "确定删除选中的日志？",
        confirmButtonText: "确定删除"
    }, function () {
        $.post(ctx + 'log/delete', {"ids": ids}, function (r) {
            if (r.code === 0) {
                $MB.n_success(r.msg);
                refresh();
            } else {
                $MB.n_danger(r.msg);
            }
        });
    });
}

function exportLogExcel() {
    $.post(ctx + "log/excel", $(".log-table-form").serialize(), function (r) {
        if (r.code === 0) {
            window.location.href = "common/download?fileName=" + r.msg + "&delete=" + true;
        } else {
            $MB.n_warning(r.msg);
        }
    });
}

function exportLogCsv() {
    $.post(ctx + "log/csv", $(".log-table-form").serialize(), function (r) {
        if (r.code === 0) {
            window.location.href = "common/download?fileName=" + r.msg + "&delete=" + true;
        } else {
            $MB.n_warning(r.msg);
        }
    });
}