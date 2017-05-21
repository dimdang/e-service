<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Bootstrap HTML5 Admin Template : Master</title>
    <!-- Bootstrap Styles-->
    <link href="./resources/css/bootstrap.css" rel="stylesheet"/>
    <!-- FontAwesome Styles-->
    <link href="./resources/css/font-awesome.css" rel="stylesheet"/>
    <!-- Custom Styles-->
    <link href="./resources/css/custom-styles.css" rel="stylesheet"/>
    <!-- Google Fonts-->
    <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'/>
    <!-- <link rel="stylesheet" href="assets/css/bootstrap.min.css"> -->
    <link href="./resources/css/bootstrap-imageupload.css" rel="stylesheet">

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <link rel="stylesheet" type="text/css" href="./resources/css/main.css">
</head>
<body ng-app="ngApp" ng-controller="ngCtrl">
<div id="wrapper">
    <nav class="navbar navbar-default top-navbar" role="navigation">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".sidebar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="index.html"><strong>bluebox</strong></a>
        </div>

        <ul class="nav navbar-top-links navbar-right">
            <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" href="#" aria-expanded="false">
                    <i class="fa fa-envelope fa-fw"></i> <i class="fa fa-caret-down"></i>
                </a>
                <ul class="dropdown-menu dropdown-messages">
                    <li>
                        <a href="#">
                            <div>
                                <strong>John Doe</strong>
                                <span class="pull-right text-muted">
                                        <em>Today</em>
                                    </span>
                            </div>
                            <div>Lorem Ipsum has been the industry's standard dummy text ever since the 1500s...</div>
                        </a>
                    </li>
                    <li class="divider"></li>
                    <li>
                        <a href="#">
                            <div>
                                <strong>John Smith</strong>
                                <span class="pull-right text-muted">
                                        <em>Yesterday</em>
                                    </span>
                            </div>
                            <div>Lorem Ipsum has been the industry's standard dummy text ever since an kwilnw...</div>
                        </a>
                    </li>
                    <li class="divider"></li>
                    <li>
                        <a href="#">
                            <div>
                                <strong>John Smith</strong>
                                <span class="pull-right text-muted">
                                        <em>Yesterday</em>
                                    </span>
                            </div>
                            <div>Lorem Ipsum has been the industry's standard dummy text ever since the...</div>
                        </a>
                    </li>
                    <li class="divider"></li>
                    <li>
                        <a class="text-center" href="#">
                            <strong>Read All Messages</strong>
                            <i class="fa fa-angle-right"></i>
                        </a>
                    </li>
                </ul>
                <!-- /.dropdown-messages -->
            </li>
            <!-- /.dropdown -->
            <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" href="#" aria-expanded="false">
                    <i class="fa fa-tasks fa-fw"></i> <i class="fa fa-caret-down"></i>
                </a>
                <ul class="dropdown-menu dropdown-tasks">
                    <li>
                        <a href="#">
                            <div>
                                <p>
                                    <strong>Task 1</strong>
                                    <span class="pull-right text-muted">60% Complete</span>
                                </p>
                                <div class="progress progress-striped active">
                                    <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="60"
                                         aria-valuemin="0" aria-valuemax="100" style="width: 60%">
                                        <span class="sr-only">60% Complete (success)</span>
                                    </div>
                                </div>
                            </div>
                        </a>
                    </li>
                    <li class="divider"></li>
                    <li>
                        <a href="#">
                            <div>
                                <p>
                                    <strong>Task 2</strong>
                                    <span class="pull-right text-muted">28% Complete</span>
                                </p>
                                <div class="progress progress-striped active">
                                    <div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="28"
                                         aria-valuemin="0" aria-valuemax="100" style="width: 28%">
                                        <span class="sr-only">28% Complete</span>
                                    </div>
                                </div>
                            </div>
                        </a>
                    </li>
                    <li class="divider"></li>
                    <li>
                        <a href="#">
                            <div>
                                <p>
                                    <strong>Task 3</strong>
                                    <span class="pull-right text-muted">60% Complete</span>
                                </p>
                                <div class="progress progress-striped active">
                                    <div class="progress-bar progress-bar-warning" role="progressbar" aria-valuenow="60"
                                         aria-valuemin="0" aria-valuemax="100" style="width: 60%">
                                        <span class="sr-only">60% Complete (warning)</span>
                                    </div>
                                </div>
                            </div>
                        </a>
                    </li>
                    <li class="divider"></li>
                    <li>
                        <a href="#">
                            <div>
                                <p>
                                    <strong>Task 4</strong>
                                    <span class="pull-right text-muted">85% Complete</span>
                                </p>
                                <div class="progress progress-striped active">
                                    <div class="progress-bar progress-bar-danger" role="progressbar" aria-valuenow="85"
                                         aria-valuemin="0" aria-valuemax="100" style="width: 85%">
                                        <span class="sr-only">85% Complete (danger)</span>
                                    </div>
                                </div>
                            </div>
                        </a>
                    </li>
                    <li class="divider"></li>
                    <li>
                        <a class="text-center" href="#">
                            <strong>See All Tasks</strong>
                            <i class="fa fa-angle-right"></i>
                        </a>
                    </li>
                </ul>
                <!-- /.dropdown-tasks -->
            </li>
            <!-- /.dropdown -->
            <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" href="#" aria-expanded="false">
                    <i class="fa fa-bell fa-fw"></i> <i class="fa fa-caret-down"></i>
                </a>
                <ul class="dropdown-menu dropdown-alerts">
                    <li>
                        <a href="#">
                            <div>
                                <i class="fa fa-comment fa-fw"></i> New Comment
                                <span class="pull-right text-muted small">4 min</span>
                            </div>
                        </a>
                    </li>
                    <li class="divider"></li>
                    <li>
                        <a href="#">
                            <div>
                                <i class="fa fa-twitter fa-fw"></i> 3 New Followers
                                <span class="pull-right text-muted small">12 min</span>
                            </div>
                        </a>
                    </li>
                    <li class="divider"></li>
                    <li>
                        <a href="#">
                            <div>
                                <i class="fa fa-envelope fa-fw"></i> Message Sent
                                <span class="pull-right text-muted small">4 min</span>
                            </div>
                        </a>
                    </li>
                    <li class="divider"></li>
                    <li>
                        <a href="#">
                            <div>
                                <i class="fa fa-tasks fa-fw"></i> New Task
                                <span class="pull-right text-muted small">4 min</span>
                            </div>
                        </a>
                    </li>
                    <li class="divider"></li>
                    <li>
                        <a href="#">
                            <div>
                                <i class="fa fa-upload fa-fw"></i> Server Rebooted
                                <span class="pull-right text-muted small">4 min</span>
                            </div>
                        </a>
                    </li>
                    <li class="divider"></li>
                    <li>
                        <a class="text-center" href="#">
                            <strong>See All Alerts</strong>
                            <i class="fa fa-angle-right"></i>
                        </a>
                    </li>
                </ul>
                <!-- /.dropdown-alerts -->
            </li>
            <!-- /.dropdown -->
            <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" href="#" aria-expanded="false">
                    <i class="fa fa-user fa-fw"></i> <i class="fa fa-caret-down"></i>
                </a>
                <ul class="dropdown-menu dropdown-user">
                    <li><a href="#"><i class="fa fa-user fa-fw"></i> User Profile</a>
                    </li>
                    <li><a href="#"><i class="fa fa-gear fa-fw"></i> Settings</a>
                    </li>
                    <li class="divider"></li>
                    <li><a href="#"><i class="fa fa-sign-out fa-fw"></i> Logout</a>
                    </li>
                </ul>
                <!-- /.dropdown-user -->
            </li>
            <!-- /.dropdown -->
        </ul>
    </nav>
    <!--/. NAV TOP  -->
    <nav class="navbar-default navbar-side" role="navigation">
        <div class="sidebar-collapse">
            <ul class="nav" id="main-menu">

                <li>
                    <a href="index"><i class="fa fa-desktop"></i> Dashboard</a>
                </li>
                <li>
                    <a href="product"><i class="fa fa-newspaper-o"></i> Product</a>
                </li>
                <li>
                    <a class="active-menu" href="customer"><i class="fa fa-user"></i> Customer</a>
                </li>

            </ul>

        </div>

    </nav>
    <!-- /. NAV SIDE  -->
    <div id="page-wrapper">
        <div class="header">
            <ol class="breadcrumb">
                <li><a href="#">Home</a></li>
                <li><a href="#">Empty</a></li>
                <li class="active">Data</li>
            </ol>

        </div>
        <div id="page-inner">
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">Customer
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-md-12"><br/>
                                    <div class="panel panel-default">
                                        <div class="panel-heading">
                                            Table Customer
                                        </div>
                                        <div class="panel-body">
                                            <div class="table-responsive">
                                                <table class="table table-striped table-bordered table-hover">
                                                    <thead>
                                                    <tr>
                                                        <th>Applicant</th>
                                                        <th>Code</th>
                                                        <th>Size</th>
                                                        <th>Price</th>
                                                        <th>Color</th>
                                                        <th>Action</th>
                                                    </tr>
                                                    </thead>
                                                    <tbody>
                                                    <tr ng-repeat="applicant in applicants">
                                                        <td width="50px">{{applicant.ID}}</td>
                                                        <td>{{applicant.PRODUCT.CODE}}</td>
                                                        <td>{{applicant.PRODUCT.SIZE}}</td>
                                                        <td>{{applicant.PRODUCT.PRICE}}</td>
                                                        <td>{{applicant.PRODUCT.COLOR.DESC}}</td>
                                                        <td width="70px">
                                                            <button class="btn btn-info btn-xs" data-title="Image"
                                                                    data-toggle="modal" data-target="#image"
                                                                    ng-click="viewImage(applicant.PRODUCT.IMAGES)"><span
                                                                    class="glyphicon glyphicon-picture"></span></button>
                                                            <button class="btn btn-primary btn-xs" data-title="Customer"
                                                                    data-toggle="modal" data-target="#myModal"
                                                                    ng-click="viewCustomer(applicant)"><span
                                                                    class="glyphicon glyphicon-user"></span></button>
                                                        </td>
                                                    </tr>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>
        </div>
        <!-- /. PAGE WRAPPER  -->
    </div>
    <!-- /. WRAPPER  -->

    <!-- Modal -->
    <div class="modal fade" id="myModal" role="dialog">
        <div class="modal-dialog">

            <!-- Modal content-->
            <div class="modal-content">
                <div class="panel panel-default">
                    <div class="panel-heading">Customer Detail
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                    </div>
                    <div class="panel-body">
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label>Man</label>
                                </div>
                                <div class="form-group">
                                    <label id="mName"></label>
                                </div>
                                <div class="form-group">
                                    <label id="mFatName"></label>
                                </div>
                                <div class="form-group">
                                    <label id="mMomName"></label>
                                </div>
                                <div class="form-group">
                                    <label id="mDob"></label>
                                </div>
                                <div class="form-group">
                                    <label id="mProvince"></label>
                                </div>
                                <div class="form-group">
                                    <label id="mDistrict"></label>
                                </div>
                                <div class="form-group">
                                    <label id="mCommune"></label>
                                </div>
                                <div class="form-group">
                                    <label id="mVillage"></label>
                                </div>
                                <div class="form-group">
                                    <label id="mTel"></label>
                                </div>
                                <div class="form-group">
                                    <label id="mEmail"></label>
                                </div>
                                <div class="form-group">
                                    <label id="mFB"></label>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label>Woman</label>
                                </div>
                                <div class="form-group">
                                    <label id="fName"></label>
                                </div>
                                <div class="form-group">
                                    <label id="fFatName"></label>
                                </div>
                                <div class="form-group">
                                    <label id="fMomName"></label>
                                </div>
                                <div class="form-group">
                                    <label id="fDob"></label>
                                </div>
                                <div class="form-group">
                                    <label id="fProvince"></label>
                                </div>
                                <div class="form-group">
                                    <label id="fDistrict"></label>
                                </div>
                                <div class="form-group">
                                    <label id="fCommune"></label>
                                </div>
                                <div class="form-group">
                                    <label id="fVillage"></label>
                                </div>
                                <div class="form-group">
                                    <label id="fTel"></label>
                                </div>
                                <div class="form-group">
                                    <label id="fEmail"></label>
                                </div>
                                <div class="form-group">
                                    <label id="fFB"></label>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- JS Scripts-->
        <!-- jQuery Js -->
        <script src="./resources/js/jquery-1.10.2.js"></script>
        <!-- Bootstrap Js -->
        <script src="./resources/js/bootstrap.min.js"></script>
        <!-- Metis Menu Js -->
        <script src="./resources/js/jquery.metisMenu.js"></script>
        <!-- Custom Js -->
        <script src="./resources/js/custom-scripts.js"></script>
        <!-- lib jquery.min.js -->
        <script src="./resources/js/jquery-3.2.1.min.js"></script>
        <!-- lib angular js -->
        <script src="./resources/js/angular.min.js"></script>
        <!-- angular app -->
        <script src="./resources/js/angular/app.js"></script>
        <!-- main js -->
        <script src="./resources/js/main.js"></script>

</body>
</html>
