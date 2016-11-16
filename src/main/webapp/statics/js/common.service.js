app.factory('CommonService', function ($http) {
    var service = {};
    service.getUpToken = function (url, key) {
        return $http({
            method: 'GET',
            url: url,
            params: {key: key}
        }).then(handleOrSuccess, handleError('获取token失败'));
    }
    return service;
});

function handleSuccess(res) {
    if (res.data.success) {
        return res.data.list;
    }
    return new Array();
}
function handleOrSuccess(res) {
    return res.data;
}
function handleError(error) {
    return function () {
        return {
            success: false,
            message: error
        }
    }
}
