var app = angular.module('ngApp', []);

app.controller('ngCtrl', ['$scope', '$http', function ($scope, $http) {

    $scope.products = [];
    $scope.promotions = [];
    $scope.customers = [];
    $scope.types = {"WED": "សំបុត្រការ", "CER": "សំបុត្របុណ្យ", "DES": "សំបុត្រច្នៃ"};

    $scope.fetchProduct = function () {
        spinner.appendTo("body");
        $http({
            method: 'POST',
            url: baseUrl + '/product/fetch',
        }).then(function (response) {
            console.log(response.data["DATA"]);
            $scope.products = response.data["DATA"];
            spinner.remove();
        }, function (response) {
            console.log(response);
            spinner.remove();
            swal('Oops...', 'Something went wrong please contact to developer!', 'error');
        });
    }

    $scope.fetchPromotion = function () {
        spinner.appendTo("body");
        $http({
            method: 'POST',
            url: baseUrl + '/promotion/fetch',
        }).then(function (response) {
            console.log(response.data["DATA"]);
            $scope.promotions = response.data["DATA"];
            spinner.remove();
        }, function (response) {
            console.log(response);
            spinner.remove();
            swal('Oops...', 'Something went wrong please contact to developer!', 'error');
        });
    }

    $scope.fetchCustomer = function () {
        spinner.appendTo("body");
        $http({
            method: 'POST',
            url: baseUrl + '/customer/fetch',
        }).then(function (response) {
            console.log(response.data["DATA"]);
            $scope.customers = response.data["DATA"];
            spinner.remove();
        }, function (response) {
            console.log(response);
            spinner.remove();
            swal('Oops...', 'Something went wrong please contact to developer!', 'error');
        });
    }

    $scope.submitProduct = function () {
        var formData = new FormData();
        var model = {
            "ID": $scope.txtId,
            "CODE": $scope.txtCode,
            "SIZE": $scope.txtSize,
            "PRICE": $scope.txtPrice,
            "COLOR": $scope.txtColor,
            "TYPE": $scope.selectType,
            "CONTACT": {
                "EMAIL": $scope.txtEmail,
                "FACEBOOK": $scope.txtFacebook,
                "PHONE1": $scope.txtPhone1,
                "PHONE2": $scope.txtPhone2
            },
        };
        //part value 'json'-> json data
        formData.append('json', JSON.stringify(model));
        for (var i = 0; i < arrayFile.length; i++) {
            formData.append("files", arrayFile[i], arrayFile[i].name);
        }
        console.log("JSON DATA ===>>> " + formData.get("json"));
        console.log("FILES ===>>> " + formData.get("files"));

        if (isValid()) {
            spinner.appendTo("body");
            $http({
                method: 'POST',
                url: baseUrl + '/product/submit',
                data: formData,
                transformRequest: angular.identity,
                headers: {'Content-Type': undefined}
            }).then(function (response) {// success
                    console.log(response);
                    $scope.fetchProduct();
                    $scope.reset();
                    spinner.remove();
                    alertify.log("Submit product successful.", "success", 4000);
                },
                function (response) {// failed
                    console.log(response);
                    spinner.remove();
                    swal('Oops...', 'Something went wrong please contact to developer!', 'error');
                });
        } else {
            swal({
                title: 'Mandatory Fields!',
                text: 'Please fill in mandatory fields',
                type: 'warning'
            })
            console.log("====>>>> Can not submit there are invalid field or required");
        }
    }

    $scope.submitPromotion = function () {
        var formData = new FormData();
        var model = {
            "ID": $scope.txtId,
            "CODE": $scope.txtCode,
            "DESC": $scope.txtDesc
        };
        formData.append('json', JSON.stringify(model));
        for (var i = 0; i < arrayFile.length; i++) {
            formData.append("files", arrayFile[i], arrayFile[i].name);
        }
        console.log("JSON DATA ===>>> " + formData.get("json"));
        console.log("FILES ===>>> " + formData.get("files"));
        if (arrayFile.length > 0 || $scope.txtId != null) {
            spinner.appendTo("body");
            $http({
                method: 'POST',
                url: baseUrl + '/promotion/submit',
                data: formData,
                transformRequest: angular.identity,
                headers: {'Content-Type': undefined}
            }).then(function (response) {// success
                    console.log(response);
                    $scope.fetchPromotion();
                    $scope.reset();
                    spinner.remove();
                    alertify.log("Submit promotion successful.", "success", 4000);
                },
                function (response) {// failed
                    console.log(response);
                    spinner.remove();
                    swal('Oops...', 'Something went wrong please contact to developer!', 'error');
                });
        } else {
            swal({
                title: 'Warning!',
                text: 'Please choose image to upload',
                type: 'warning'
            });
            console.log("====>>>> Can not submit there are invalid field or required");
        }
    }

    $scope.editProduct = function (product) {
        $scope.txtId = product.ID;
        $scope.txtCode = product.CODE;
        $scope.txtSize = product.SIZE;
        $scope.txtPrice = product.PRICE;
        $scope.txtColor = product.COLOR;
        $scope.selectType = product.TYPE;
        $scope.txtPhone1 = product.CONTACT.PHONE1;
        $scope.txtPhone2 = product.CONTACT.PHONE2;
        $scope.txtEmail = product.CONTACT.EMAIL;
        $scope.txtFacebook = product.CONTACT.FACEBOOK;
        $("html, body").animate({scrollTop: 0}, 600);

    }

    $scope.editPromotion = function (promotion) {
        $scope.txtId = promotion.ID;
        $scope.txtCode = promotion.CODE;
        $scope.txtDesc = promotion.DESC;
        $("html, body").animate({scrollTop: 0}, 600);
    }

    $scope.deleteEntity = function (id, index, type) {
        var msg = "";
        var func = function () {
            if (id != null && index > -1 && type != null) {
                spinner.appendTo("body");
                $http({
                    method: 'GET',
                    url: baseUrl + '/entity/delete?id=' + id + '&type=' + type,
                }).then(function (response) {// success
                        console.log(response);
                        if (type == "PRO") {
                            $scope.products.splice(index, 1);
                            msg = "Your product has been deleted."
                        }
                        else if (type == "POM") {
                            $scope.promotions.splice(index, 1);
                            msg = "Your promotion has been deleted."
                        }
                        else {
                            $scope.customers.splice(index, 1);
                            msg = "Your customer has been deleted."
                        }
                        spinner.remove();
                        swal('Deleted!', msg, 'success');
                    },
                    function (response) {// failed
                        console.log(response);
                        spinner.remove();
                        swal('Oops...', 'Something went wrong please contact to developer!', 'error');
                    });
            }
        }
        $.fn.confirmDelete(func);
    }

    $scope.viewImage = function (imgs, id, type) {
        console.log("array images id:" + imgs);
        console.log("entity id:" + id);
        console.log("entity type:" + type);
        var key = id + "-" + type;
        $('.gallery').attr('id', key);
        for (var i = 0; i < imgs.length; i++) {
            var div = "<div><a id='" + imgs[i] + "' href='" + imageUrl + "/view/" + imgs[i] + "'></a></div>";
            $('.gallery').append(div);
        }
        if (imgs.length > 0) {
            $.getScript('./resources/js/zoom.min.js', function () {
                $(".gallery a")[0].click();
                images = imgs;
            });
        } else {
            swal('Oops...', 'No image available on the server!', 'info').catch(swal.noop);
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
        $("#fb").attr({"href": customer.FACEBOOK, "target": "_blank"});
        $("#map").attr({"href": customer.MAP, "target": "_blank"});
        $("#other").text("Other: " + customer.OTHER);
    }

    $scope.reset = function () {
        $scope.txtId = "";
        $scope.txtCode = "";
        $scope.txtSize = "";
        $scope.txtPrice = "";
        $scope.txtColor = "";
        $scope.txtPhone1 = "";
        $scope.txtPhone2 = "";
        $scope.selectType = "";
        $scope.txtEmail = "";
        $scope.txtFacebook = "";
        $scope.txtDesc = "";
        $("#files").val("");
        $("#preview").empty();
        while (arrayFile.length > 0) {
            arrayFile.pop();
        }
    }

    function isValid() {
        return !$scope.txtCode == "" && !$scope.txtSize == ""
            && !$scope.txtColor == "" && !$scope.txtPrice == ""
            && !$scope.selectType == "" && !$scope.txtPhone1 == "";
    }
}]);