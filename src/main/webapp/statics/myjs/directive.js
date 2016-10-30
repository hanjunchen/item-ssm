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
                dictMaxFilesExceeded: '不支持多文件上传'
            };
            var eventHandlers = {
                'success': function (file, response) {
                    scope.key = response.key;
                    scope.$apply();
                }
            };

            dropzone = new Dropzone(element[0], config);

            angular.forEach(eventHandlers, function (handler, event) {
                dropzone.on(event, handler);
            });

            scope.processDropzone = function () {
                scope.dropzone.processQueue();
            };

            scope.resetDropzone = function () {
                scope.dropzone.removeAllFiles();
            }
            scope.dropzone = dropzone;
        }
    };
}