var arrayFile = [];//array image upload
var arrayImage = [];//array image for view and upload
var images;//array id image from server
var advertisements = [];
var baseUrl = "https://e-service-application.herokuapp.com/api/web";//base url
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
                    var div = $("<div class='img-wrap'></div>");
                    var img = $("<img class='img-thumbnail' src='" + e.target.result + "' style='height:100px' title='" + e.target.fileName + "' />");
                    var span = $("<span class='close' name='" + e.target.fileName + "'>&times;</span>");
                    span.appendTo(div);
                    img.appendTo(div);
                    div.appendTo($('#preview'));
                    span.click(function () {
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
            func();
        }).catch(swal.noop);
    }

    $.fn.addMoreImage = function (key, resolve, reject, callback) {
        var url = imageUrl + "/add?key=" + key;
        var form = new FormData();
        for (var i = 0; i < arrayImage.length; i++)
            form.append("files", arrayImage[i], arrayImage[i].name);
        if (key == "AD")
            url = baseUrl + "/advertisement/submit"
        $.ajax({
            type: 'POST',
            url: url,
            data: form,
            processData: false,
            contentType: false,
            success: function (response) {
                var imgs = [];
                if (response.MESSAGE == "Submit advertisement successful") {
                    var data = response.DATA;
                    for (var i = 0; i < data.length; i++) {
                        imgs.push(data[i].IMAGE.ID);
                        advertisements.push(data[i]);
                        $("<div><a id='" + imgs[i] + "' href='" + imageUrl + "/view/" + imgs[i] + "'></a></div>").appendTo($('.gallery'));
                        var span = $("<span class='close' id='" + data[i].ID + "' name='" + imgs[i] + "'>&times;</span>");
                        var img = $("<img name='" + imgs[i] + "' class='img-thumbnail' src='" + imageUrl + "/view/" + imgs[i] + "' title='" + data[i].IMAGE.NAME + "' style='height:100px;cursor: pointer;'/>");
                        var wrap = $("<div class='img-wrap'></div>");
                        span.appendTo(wrap);
                        img.appendTo(wrap);
                        wrap.appendTo($('#grid'));
                        img.click(function () {
                            $("#" + $(this).attr("name")).click();
                            return false;
                        });
                        $.fn.spanOnClose(span);
                    }
                } else {
                    imgs = response.DATA;
                    for (var i = 0; i < imgs.length; i++) {
                        images.push(imgs[i]);
                        $("<div><a id='" + imgs[i] + "' href='" + imageUrl + "/view/" + imgs[i] + "'></a></div>").appendTo($('.gallery'));
                    }
                }
                callback(imgs);
                while (arrayImage.length > 0) {
                    arrayImage.pop();
                }
                alertify.log("Upload image to server successful.", "success", 2000);
                resolve();
                console.log(advertisements);
                console.log(response);
            },
            error: function (response) {
                //swal('error!', 'error');
                console.log(response);
                reject("error");
            }
        });
    }
});

