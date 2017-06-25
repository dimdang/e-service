var app = angular.module('ngApp', []);

app.controller('ngCtrl', ['$scope', '$http', function ($scope, $http) {

    $scope.baseUrl = "http://localhost:8080/api/web";
    $scope.products = [];
    $scope.customers = [];
    $scope.images = [];

    $scope.fetchProduct = function () {
        $http({
            method: 'POST',
            url: $scope.baseUrl + '/product/fetch',
        }).then(function (response) {
            console.log("List products:" + response.data["DATA"]);
            $scope.products = response.data["DATA"];
        }, function (response) {
            console.log(response);
            alert("There are some error plase contact to developer");
        });
    }

    $scope.fetchCustomer = function () {
        $http({
            method: 'POST',
            url: $scope.baseUrl + '/customer/fetch',
        }).then(function (response) {
            console.log("List customers:" + response.data["DATA"]);
            $scope.customers = response.data["DATA"];
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
            "COLOR": $scope.txtColor,
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
        $scope.txtColor = product.COLOR;
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

    $scope.viewCustomer = function (customer) {

        $("#mName").text("Name: " + customer.GROOM_NAME);
        $("#mFatName").text("Father Name: " + customer.GROOM_DAD_NAME);
        $("#mMomName").text("Mother Name: " + customer.GROOM_MOM_NAME);
        $("#fName").text("Name: " + customer.BRIDE_NAME);
        $("#fFatName").text("Father Name: " + customer.BRIDE_DAD_NAME);
        $("#fMomName").text("Mother Name: " + customer.BRIDE_MOM_NAME);
        $("#home").text("Home : " + customer.HOME);
        $("#date").text("Date: " + customer.DATE);
        $("#address").text("Address: " + customer.ADDRESS);
        $("#tel").text("Tel: " + customer.PHONE);
        $("#other").text("Other : " + customer.OTHER);
    }

    $scope.reset = function () {
        $scope.txtId = "";
        $scope.txtCode = "";
        $scope.txtSize = "";
        $scope.txtPrice = "";
        $scope.txtColor = "";
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
    $scope.fetchCustomer();
}]);