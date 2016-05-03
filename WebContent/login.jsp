<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>邮件系统</title>

    <!-- Bootstrap -->
    <!-- Latest compiled and minified CSS -->

    <link rel="stylesheet" href="css/bootstrap.min.css">

    <!-- Optional theme -->
    <link rel="stylesheet" href="css/bootstrap-theme.min.css">
    
    <link rel="stylesheet" type="text/css" href="css/BootSideMenu.css">
    <link rel="stylesheet" type="text/css" href="css/main.css">
    <link rel="stylesheet" href="css/login.css">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="js/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
    <script type="text/javascript" src="js/BootSideMenu.js"></script>
    <script type="text/javascript" src="js/maillist.js"></script>

  </head>
  <body role="document full-height">
    
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                    <h4 class="modal-title" id="gridSystemModalLabel">登录 | <a href="/Mail/register">注册</a></h4>
                </div>
                <div class="modal-body">
                    <div class="container-fluid">
                        <table class="table" rules="cols">
                            <tbody><tr>
                                <td>
                                    <div class="login">
                                        <form id="loginform" class="user-input" method="post">
                                            <div class="form-group">
                                                <input class="form-control" name="account" placeholder="请输入Email账号" type="email">
                                                <% if ((boolean)request.getAttribute("accountNotFound")) { %>
                                                <label class="text-danger">账号不存在</label>
                                                <% } %>
                                            </div>
                                            <div class="form-group">
                                                <input class="form-control" name="password" placeholder="请输入密码" type="password">
                                                <% if ((boolean)request.getAttribute("passwordInvalid")) {%>
                                                <label class="text-danger">密码错误</label>
                                                <% } %>
                                            </div>
                                        </form>
                                    </div>
                                </td>
                            </tr>
                        </tbody></table>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" onclick="$('#loginform').submit();" class="btn btn-primary">登录</button>
                </div>
  </body>
</html>