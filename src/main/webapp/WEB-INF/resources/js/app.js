var app = angular.module('ngApp', []);

app.controller('ngCtrl', ['$scope', '$http', function ($scope, $http) {

    $scope.products = [];
    $scope.promotions = [];
    $scope.customers = [];
    $scope.types = {"WED": "សំបុត្រការ", "CER": "សំបុត្របុណ្យ", "DES": "សំបុត្រច្នៃ"};

    $scope.sort = function (keyname) {
        $scope.sortKey = keyname;   //set the sortKey to the param passed
        $scope.reverse = !$scope.reverse; //if true make it false and vice versa
    }

    $scope.fetchProduct = function () {
        var page = 2;
        var size = 10;
        spinner.appendTo("body");
        $http({
            method: 'POST',
            url: baseUrl + '/products/fetch?page=' + page + '&size=' + size,
        }).then(function (response) {
            console.log(response.data.content);
            $scope.products = response.data.content;
            spinner.remove();
        }, function (response) {
            console.log(response);
            spinner.remove();
            swal('Oops...', 'Something went wrong please contact to developer!', 'error').catch(swal.noop);
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
            swal('Oops...', 'Something went wrong please contact to developer!', 'error').catch(swal.noop);
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
            swal('Oops...', 'Something went wrong please contact to developer!', 'error').catch(swal.noop);
        });
    }

    $scope.fetchAdvertisement = function () {
        spinner.appendTo("body");
        $http({
            method: 'POST',
            url: baseUrl + '/advertisement/fetch',
        }).then(function (response) {
            console.log(response.data["DATA"]);
            advertisements = response.data["DATA"];
            for (var i = 0; i < advertisements.length; i++) {
                var ad = advertisements[i];
                var span = $("<span class='close' id='" + ad.ID + "' name='" + ad.IMAGE.ID + "'>&times;</span>");
                var img = $("<img name='" + ad.IMAGE.ID + "' class='img-thumbnail' src='" + imageUrl + "/view/" + ad.IMAGE.ID + "' title='" + ad.IMAGE.NAME + "' style='height:100px;cursor: pointer;'/>");
                var wrap = $("<div class='img-wrap'></div>");
                span.appendTo(wrap);
                img.appendTo(wrap);
                wrap.appendTo($("#grid"));
                var div = $("<div><a id='" + ad.IMAGE.ID + "' href='" + imageUrl + "/view/" + ad.IMAGE.ID + "'></a></div>");
                div.appendTo($('.gallery'));
                $.fn.imageOnClick(div);
                img.click(function () {
                    $("#" + $(this).attr("name")).click();
                    return false;
                });
                $.fn.spanOnClose(span);
            }
            $('.container').attr('id', "AD");
            $('.gallery').attr('id', "AD");
            spinner.remove();
        }, function (response) {
            console.log(response);
            spinner.remove();
            swal('Oops...', 'Something went wrong please contact to developer!', 'error').catch(swal.noop);
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
                    alertify.log("Submit product successful.", "success", 2000);
                },
                function (response) {// failed
                    console.log(response);
                    spinner.remove();
                    swal('Oops...', 'Something went wrong please contact to developer!', 'error').catch(swal.noop);
                });
        } else {
            swal({
                title: 'Mandatory Fields!',
                text: 'Please fill in mandatory fields',
                type: 'warning'
            }).catch(swal.noop);
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
                    alertify.log("Submit promotion successful.", "success", 2000);
                },
                function (response) {// failed
                    console.log(response);
                    spinner.remove();
                    swal('Oops...', 'Something went wrong please contact to developer!', 'error').catch(swal.noop);
                });
        } else {
            swal({
                title: 'Warning!',
                text: 'Please choose image to upload',
                type: 'warning'
            }).catch(swal.noop);
            ;
            console.log("====>>>> Can not submit there are invalid field or required");
        }
    }

    $scope.submitAdvertisement = function () {
        var formData = new FormData();
        for (var i = 0; i < arrayFile.length; i++) {
            formData.append("files", arrayFile[i], arrayFile[i].name);
        }
        console.log("FILES ===>>> " + formData.get("files"));
        if (arrayFile.length > 0) {
            spinner.appendTo("body");
            $http({
                method: 'POST',
                url: baseUrl + '/advertisement/submit',
                data: formData,
                transformRequest: angular.identity,
                headers: {'Content-Type': undefined}
            }).then(function (response) {// success
                    console.log(response);
                    var ads = response.data["DATA"];
                    for (var i = 0; i < ads.length; i++) {
                        var ad = ads[i];
                        advertisements.push(ad);
                        var span = $("<span class='close' id='" + ad.ID + "' name='" + ad.IMAGE.ID + "'>&times;</span>");
                        var img = $("<img name='" + ad.IMAGE.ID + "' class='img-thumbnail' src='" + imageUrl + "/view/" + ad.IMAGE.ID + "' title='" + ad.IMAGE.NAME + "' style='height:100px;cursor: pointer;'/>");
                        var wrap = $("<div class='img-wrap'></div>");
                        span.appendTo(wrap);
                        img.appendTo(wrap);
                        wrap.appendTo($('#grid'));
                        //$("#grid").prepend(div);
                        var div = $("<div><a id='" + ad.IMAGE.ID + "' href='" + imageUrl + "/view/" + ad.IMAGE.ID + "'></a></div>");
                        div.appendTo($('.gallery'));
                        $.fn.imageOnClick(div);
                        img.click(function () {
                            $("#" + $(this).attr("name")).click();
                            return false;
                        });
                        $.fn.spanOnClose(span);
                    }
                    $scope.reset();
                    spinner.remove();
                    alertify.log("Submit advertisement successful.", "success", 2000);
                },
                function (response) {// failed
                    console.log(response);
                    spinner.remove();
                    swal('Oops...', 'Something went wrong please contact to developer!', 'error').catch(swal.noop);
                });
        } else {
            swal({
                title: 'Warning!',
                text: 'Please choose image to upload',
                type: 'warning'
            }).catch(swal.noop);
            console.log("====>>>> Can not submit there are invalid field or required");
        }
    }

    $.fn.spanOnClose = function (span) {
        span.click(function () {
            for (var j = 0; j < advertisements.length; j++) {
                if ($(this).attr("id") == advertisements[j].ID) {
                    $scope.deleteEntity($(this).attr("id"), j, $(this));
                    break;
                }
            }
        });
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

    $scope.deleteEntity = function (id, index, val) {
        var msg = "";
        var type;
        if (typeof val === 'string' || val instanceof String) {
            type = val;
        } else {
            type = 'AD';
        }
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
                        else if (type == "CUS") {
                            $scope.customers.splice(index, 1);
                            msg = "Your customer has been deleted."
                        } else {
                            advertisements.splice(index, 1);
                            $("#" + val.attr("name")).parent("div").remove();
                            val.parent(".img-wrap").remove();
                            msg = "Your advertisement has been deleted."
                            $.get(baseUrl + "/notification");
                        }
                        alertify.log(msg, "success", 2000);
                        spinner.remove();
                    },
                    function (response) {// failed
                        console.log(response);
                        spinner.remove();
                        swal('Oops...', 'Something went wrong please contact to developer!', 'error').catch(swal.noop);
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
        $('.container').attr('id', type);
        $('.gallery').attr('id', key);
        for (var i = 0; i < imgs.length; i++) {
            $("<div><a id='" + imgs[i] + "' href='" + imageUrl + "/view/" + imgs[i] + "'></a></div>").appendTo($('.gallery'));
        }
        if (imgs.length > 0) {
            $.getScript('./resources/js/zoom.min.js', function () {
                $(".gallery a")[0].click();
                images = imgs;
            });
        } else {
            swal('Oops...', 'No image available on the server!', 'info').catch(swal.noop).catch(swal.noop);
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
        $("#email").text("Email: " + customer.EMAIL);
        $("#fb").text("Facebook: " + customer.FACEBOOK);
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