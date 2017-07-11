var app = angular.module('ngApp', []);

app.controller('ngCtrl', ['$scope', '$http', function ($scope, $http) {

    $scope.baseUrl = "http://localhost:8080/api/web";
    $scope.products = [];
    $scope.customers = [];
    $scope.imageUrl=$scope.baseUrl+"/image/view/";


  	$scope.fetchProduct = function(){
         spinner.appendTo("body");
  		$http({
  			 method: 'POST',
	       url: $scope.baseUrl+'/product/fetch',
  		}).then(function(response){
  			console.log(response.data["DATA"]);
  			$scope.products=response.data["DATA"];
            spinner.remove();
  		},function(response){
  			console.log(response);
            spinner.remove();
            alert("There are some error plase contact to developer");
        });
    }

    $scope.fetchCustomer = function () {
        spinner.appendTo("body");
        $http({
            method: 'POST',
            url: $scope.baseUrl + '/customer/fetch',
        }).then(function (response) {
            console.log(response.data["DATA"]);
            $scope.customers = response.data["DATA"];
            spinner.remove();
        }, function (response) {
            console.log(response);
            spinner.remove();
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
        spinner.appendTo("body");
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
                spinner.remove();
            },
            function (response) {// failed
                console.log(response);
                spinner.remove();
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

    }

    $scope.viewImage = function (imgs) {
        console.log(imgs);
        for(var i=0;i<imgs.length;i++){
            var div="<div><a id='"+imgs[i].IMG_ID+"' href='"+$scope.imageUrl+imgs[i].IMG_ID+"'></a></div>";
            $('.gallery').append(div);
        }
        if(imgs.length>0){
             $.getScript('./resources/js/zoom.min.js', function() {
                $(".gallery a")[0].click();
                 images=imgs;
            });
        }
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

}]);