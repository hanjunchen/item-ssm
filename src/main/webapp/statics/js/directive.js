angular.module('myApp').directive('dropzone', dropzone);
function dropzone() {
    return {
        restrict: 'C',
        link: function (scope, element, attrs) {
            var config = {
                url: scope.url,
                maxFilesize: 5,
                paramName: "file",
                maxThumbnailFilesize: 10,
                parallelUploads: 15,
                autoProcessQueue: false,
                uploadMultiple: false,
                addRemoveLinks: true,
                maxFiles: scope.maxFilesize == undefined ? 15 : scope.maxFilesize,
                dictRemoveFile: "移除文件",
                acceptedFiles: scope.fileType == undefined ? ".jpg,.jpeg,.png,.doc.docx,.xls,.xlsx,.pdf" : scope.fileType,
                dictDefaultMessage: "拖动或者点击选择文件",
                dictInvalidFileType: '不支持当前文件类型',
                dictMaxFilesExceeded: '最多只能上传' + scope.maxFilesize + '张'
            };

            //  element[0]是指令所在标签的DOM对象（这里是一个form表单），当一个页面上有多个dropzone时，那会产生多个dropzone对象
            var dropzone = new Dropzone(element[0], config);

            dropzone.on('success', function (file, response) {
                scope.key = response.key;
                scope.$apply();
            });

            scope.processDropzone = function () {
                dropzone.processQueue();
            };

            scope.resetDropzone = function () {
                dropzone.removeAllFiles();
            }

            scope.dropzone = dropzone;
        }
    };
}