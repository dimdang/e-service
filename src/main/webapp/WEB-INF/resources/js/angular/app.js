var app = angular.module('ngApp', []);

app.controller('ngCtrl', ['$scope', '$http', function ($scope, $http) {

    $scope.baseUrl = "http://localhost:8080/api/web";
    $scope.products = [];
    $scope.applicants = [];
    $scope.images = [];
    $scope.colors = [];


    $scope.fetchColor = function () {
        $http({
            method: 'GET',
            url: 'http://localhost:8080/api/data/color/fetch',
        }).then(function (response) {
            console.log(response.data["DATA"]);
            $scope.colors = response.data["DATA"];
        }, function (response) {
            console.log(response);
            alert("There are some error plase contact to developer");
        });
    }

    $scope.fetchProduct = function () {
        $http({
            method: 'POST',
            url: $scope.baseUrl + '/product/fetch',
        }).then(function (response) {
            console.log(response.data["DATA"]);
            $scope.products = response.data["DATA"];
        }, function (response) {
            console.log(response);
            alert("There are some error plase contact to developer");
        });
    }

    $scope.fetchApplicant = function () {
        $http({
            method: 'POST',
            url: $scope.baseUrl + '/applicant/fetch',
        }).then(function (response) {
            console.log(response.data["DATA"]);
            $scope.applicants = response.data["DATA"];
            //console.log($scope.applicants[0].PERSONS[0]);
        }, function (response) {
            console.log(response);
            alert("There are some error plase contact to developer");
        });
    }

    $scope.submit = function () {
        //object form data
        var formData = new FormData();
        //object model
        var model = {
            "ID": $scope.txtId,
            "CODE": $scope.txtCode,
            "SIZE": $scope.txtSize,
            "PRICE": $scope.txtPrice,
            "COLOR": {"ID": $scope.selectColor.ID},
            "CONTACT": {
                "EMAIL": $scope.txtEmail,
                "FACEBOOK": $scope.txtFacebook,
                "PHONE1": $scope.txtPhone1,
                "PHONE2": $scope.txtPhone2,
                "PHONE3": $scope.txtPhone3
            },
        };
        //part value 'json'-> json data
        formData.append('json', JSON.stringify(model));
        for (var i = 0; i < arrayFile.length; i++) {
            formData.append("files", arrayFile[i], arrayFile[i].name);
        }
        console.log("JSON DATA ===>>> " + formData.get("json") + "\nFILES ===>>> " + formData.get("files"));
        //send http request
        $http({
            method: 'POST',
            url: $scope.baseUrl + '/product/submit',
            data: formData,
            transformRequest: angular.identity,
            headers: {'Content-Type': undefined}
        }).then(function (response) {// success
                console.log(response);
                $scope.fetchProduct();
                $scope.reset();
            },
            function (response) {// failed
                console.log(response);
                alert("There are some error plase contact to developer");
            });
    }

    $scope.editProduct = function (product) {
        $scope.txtId = product.ID;
        $scope.txtCode = product.CODE;
        $scope.txtSize = product.SIZE;
        $scope.txtPrice = product.PRICE;
        for (var i = 0; i < $scope.colors.length; i++) {
            if ($scope.colors[i].ID == product.COLOR.ID) {
                $scope.selectColor = $scope.colors[i];
                break;
            }
        }
        $scope.txtPhone1 = product.CONTACT.PHONE1;
        $scope.txtPhone2 = product.CONTACT.PHONE2;
        $scope.txtPhone3 = product.CONTACT.PHONE3;
        $scope.txtEmail = product.CONTACT.EMAIL;
        $scope.txtFacebook = product.CONTACT.FACEBOOK;

        /*var preview = $("#preview");
         preview.empty();
         for(var i=0;i<product.IMAGES.length;i++){
         var img=$("<img />",{
         class : "img-thumbnail",
         height : 100,
         src : $scope.baseUrl+"/product/view/"+product.IMAGES[i].PRO_IMG_ID,
         title : product.IMAGES[i].NAME
         });
         img.appendTo(preview);
         $scope.images[i]=img;
         }*/

    }

    $scope.viewImage = function (images) {
        alert(arrayFile.length);
        for (var i = 0; i < arrayFile.length; i++)
            console.log(arrayFile[i].name);
    }

    $scope.viewCustomer = function (applicant) {
        for (var i = 0; i < applicant.PERSONS.length; i++) {
            var person = applicant.PERSONS[i];
            if (person.GENDER === "man") {
                $("#mName").text("Name: " + person.NAME);
                $("#mFatName").text("Father Name: " + person.FATHER_NAME);
                $("#mMomName").text("Mother Name: " + person.MOTHER_NAME);
            }
            if (person.GENDER === "woman") {
                $("#fName").text("Name: " + person.NAME);
                $("#fFatName").text("Father Name: " + person.FATHER_NAME);
                $("#fMomName").text("Mother Name: " + person.MOTHER_NAME);
            }
        }
        $("#date").text("Date: " + applicant.DATE);
        $("#province").text("Province: " + applicant.ADDRESS.PROVINCE);
        $("#district").text("District: " + applicant.ADDRESS.DISTRICT);
        $("#commune").text("Commune: " + applicant.ADDRESS.COMMUNE);
        $("#village").text("Village: " + applicant.ADDRESS.VILLAGE);
        $("#tel").text("Tel: " + applicant.CONTACT.PHONE1 + ", " + applicant.CONTACT.PHONE2 + ", " + applicant.CONTACT.PHONE3);
        $("#email").text("Email : " + applicant.CONTACT.EMAIL);
        $("#fb").text("Facebook : " + applicant.CONTACT.FACEBOOK);
        $("#other").text("Other : " + applicant.OTHER);
    }

    $scope.reset = function () {
        $scope.txtId = "";
        $scope.txtCode = "";
        $scope.txtSize = "";
        $scope.txtPrice = "";
        $scope.selectColor = "";
        $scope.txtPhone1 = "";
        $scope.txtPhone2 = "";
        $scope.txtPhone3 = "";
        $scope.txtEmail = "";
        $scope.txtFacebook = "";
        $("#files").val("");
        $("#preview").empty();
        while (arrayFile.length > 0) {
            arrayFile.pop();
        }
    }

    $scope.fetchProduct();
    $scope.fetchApplicant();
    $scope.fetchColor();
}]);