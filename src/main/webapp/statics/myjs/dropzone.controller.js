angular.module('myApp', []).controller('dropzoneCtrl', dropzoneCtrl);

function dropzoneCtrl($scope, $http, $filter) {
    var vm = $scope;
    vm.url = 'http://upload.qiniu.com/';
    vm.imagePrefix = 'http://ofdy8083a.bkt.clouddn.com/';
    vm.dropzone = {};
    vm.fileType = ".pdf";
    vm.maxFilesize = 1;

    vm.uploadFile = function () {
        var name = vm.dropzone.files[0].name;
        var ext = name.substring(name.lastIndexOf('.'), name.length);
        var timeStamp = $filter('date')(new Date(), 'MM-dd-HH-mm-ss');
        vm.fileName = 'gene1' + timeStamp + ext;
        var url = 'http://localhost:81/uptoken?key=' + vm.fileName;
        $http.get(url).then(function (res) {
            //vm.token = res.data;
            //vm.$apply();    //  异步请求相当于定时任务，需要手动digest才能更新到view和其他相连的作用域，这里直接用jq
            $('#token').val(res.data);
            vm.processDropzone();
        });
    };

    vm.$watch('key', function (newValue, oldValue) {
        if (newValue != undefined && newValue != oldValue) {
            console.log(newValue, oldValue, '上传成功');
        }
    });

    vm.reset = function () {
        vm.resetDropzone();
    };

}

