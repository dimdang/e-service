$(document).ready(function() {
 
 if(window.File && window.FileList && window.FileReader) {

    var preview = $("#preview");

     $("#files").on("change",function(e) {
          var files = e.target.files ,
          filesLength = files.length ;
          for (var i = 0; i < filesLength ; i++) {
            var f = files[i]
            var fileReader = new FileReader();
            fileReader.onload = (function(e) {
                var file = e.target;
                 $("<div class=\"img-wrap\"><span class=\"close\">&times;</span>" +
                  "<img class=\"img-thumbnail\" src=\"" + e.target.result + 
                  "\" style=\"height:100px\" title=\"" + file.name + "\"/>" +
                  "</div>").appendTo(preview);
                /* $("<img />",{
                    class : "img-thumbnail",
                    height : 100,
                    src : e.target.result,
                    title : file.name
                  }).appendTo(preview);*/
              });
          fileReader.readAsDataURL(f);
        }
    });

  } else { alert("Your browser doesn't support to File API") }

});