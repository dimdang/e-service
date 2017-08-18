var arrayFile = [];
var images;
var spinner = $("<div id='progress'> <div class='content'><img src='./resources/img/spinner.gif' /></div> </div>");
$(document).ready(function () {

    if (window.File && window.FileList && window.FileReader) {

        var preview = $("#preview");

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
                        "</div>").appendTo(preview);
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

});

