var arrayFile = [];//array image upload
var arrayImage = [];//array image for view and upload
var images;//array id image from server
var advertisement;
var baseUrl = "http://localhost:8080/api/web";//base url
var imageUrl = baseUrl + "/image";//base image url
var spinner = $("<div id='progress'> <div class='content'><img src='./resources/img/spinner.gif' /></div> </div>");
$(document).ready(function () {

    if (window.File && window.FileList && window.FileReader) {

        $("#files").on("change", function (e) {
            var files = e.target.files;
            var filesLength = files.length;
            for (var i = 0; i < filesLength; i++) {
                var file = files[i];
                arrayFile.push(file);
                console.log(arrayFile);
                console.log($("#files").prop("files"));
                var fileReader = new FileReader();
                fileReader.fileName = file.name;
                fileReader.onload = (function (e) {
                    $("<div class='img-wrap'><span class='close' name='" + e.target.fileName + "'>&times;</span>" +
                        "<img class='img-thumbnail' src=\"" + e.target.result +
                        "\" style='height:100px' title=\"" + e.target.fileName + "\"/>" +
                        "</div>").appendTo($("#preview"));
                    $(".close").click(function () {
                        for (var index = 0; index < arrayFile.length; index++) {
                            var name = $(this).attr("name");
                            if (arrayFile[index].name === name) {
                                arrayFile.splice(index, 1);
                                console.log(arrayFile);
                                console.log($("#files").prop("files"));
                                console.log("file ===>>> " + name + " has been removed!");
                                break;
                            }
                        }
                        $(this).parent(".img-wrap").remove();
                    });
                });
                fileReader.readAsDataURL(file);
            }
        });

    } else {
        alert("Your browser doesn't support to File API")
    }

    $.fn.confirmDelete = function (func) {
        swal({
            title: 'Are you sure?',
            text: "You won't be able to get it back!",
            type: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Yes, delete it!'
        }).then(function () {
            console.log("====>>>> after confirm delete entity");
            func();
        }).catch(swal.noop);
    }

    $.fn.addMoreImage = function (key, resolve, reject, callback) {
        var form = new FormData();
        for (var i = 0; i < arrayImage.length; i++)
            form.append("files", arrayImage[i], arrayImage[i].name);
        $.ajax({
            type: 'POST',
            url: imageUrl + "/add?key=" + key,
            data: form,
            processData: false,
            contentType: false,
            success: function (response) {
                var imgs = response.DATA;
                for (var i = 0; i < imgs.length; i++) {
                    images.push(imgs[i]);
                    $("<div><a id='" + imgs[i] + "' href='" + imageUrl + "/view/" + imgs[i] + "'></a></div>").appendTo($('.gallery'));
                }
                callback(imgs);
                while (arrayImage.length > 0) {
                    arrayImage.pop();
                }
                alertify.log("Upload image to server successful.", "success", 2000);
                resolve();
                console.log(response);
                console.log(images);
            },
            error: function (response) {
                //swal('error!', 'error');
                console.log(response);
                reject("error");
            }
        });
    }
});

