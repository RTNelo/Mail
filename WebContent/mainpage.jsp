<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.*"  %>

<%!
	User user = null;
	Database db = Database.getDefaultDatabase();
%>

<% user = db.getUserById((int)session.getAttribute("userid")); %>

<!doctype>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>邮件系统 - <%= user.getNickname() %></title>

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

  <nav class="navbar navbar-default">
    <div class="container-fluid">
      <!-- Brand and toggle get grouped for better mobile display -->
      <div class="navbar-header">
        <a class="navbar-brand" href="#">邮件系统</a>
      </div>

      <!-- Collect the nav links, forms, and other content for toggling -->
      <div class="collapse navbar-collapse">
        <div class="nav navbar-nav navbar-right">
          <form class="navbar-form navbar-left" role="search">
            <div class="form-group">
              <input class="search" type="text">
              <a class="btn btn-info search-button">
                <span class="glyphicon glyphicon-search" aria-hidden="true"></span>
              </a>
            </div>
            <a class="btn btn-info send">
              <span class="glyphicon glyphicon-envelope" aria-hidden="true"></span>
            </a>
            <a class="btn btn-info">
              <span class="glyphicon glyphicon-refresh" aria-hidden="true"></span>
            </a>            
            <a class="btn btn-default">
              <span class="glyphicon glyphicon-cog" aria-hidden="true" data-toggle="modal" data-target=".modal-config"></span>
            </a>
          </form>
        </div>
      </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
  </nav>

    <div class="modal modal-config fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                    <h4 class="modal-title">配置</h4>
                </div>
                <div class="bd row">
                 <div class="panel panel-default fullheight col-md-3">
                      <div class="panel-body">
                        <div class="list-group">
                          <a href="#" class="list-group-item active">总体设置</a>
                          <a href="#" class="list-group-item">服务器</a>
                          <a href="#" class="list-group-item">界面</a>
                          <a href="#" class="list-group-item">安全</a>
                          <a href="#" class="list-group-item">其他<span class="badge">1</span></a>
                        </div>
                      </div>
                    </div>

                    <div class="fullheight col-md-9">
                      <div id="title">
                        <h1 align="center">总体设置</h1>
                      </div>

                      <form class="form-horizontal full-width center-block">
                        <div class="form-group">
                          <label for="sender-address" class="col-sm-2 control-label">选项1</label>
                          <div class="col-sm-10">
                            <input class="form-control" id="sender-address" placeholder="选项内容" type="email">
                          </div>
                        </div>
                        <div class="form-group">
                          <label for="reciever-address" class="col-sm-2 control-label">选项2</label>
                          <div class="col-sm-10">
                            <input class="form-control" id="reciever-address" placeholder="选项内容" type="email">
                          </div>
                        </div>
                        <div class="form-group">
                          <label for="title" class="col-sm-2 control-label">选项3</label>
                          <div class="col-sm-10">
                            <input class="form-control" id="title" placeholder="选项内容" type="password">
                          </div>
                        </div>    
                        <div class="form-group full-width">
                          <div class="btn-group btn-group-lg float-right" role="group" aria-label="...">
                            <a class="btn btn-success">保存</a>
                            <a class="btn btn-warning" data-dismiss="modal">取消</a>
                          </div>        
                        </div>
                      </form>
                    </div>
                </div>

            </div>
        </div>
    </div>

    <div class="modal modal-add fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
        <div class="modal-dialog modal-sm">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                    <h4 class="modal-title">详细内容</h4>
                </div>
                <div class="sender">
                    <h1>星巴克星享俱乐部</h1>
                    <p class="text-primary">邮箱：</p>
                    <p>Example@qq.com</p>
                    <button class="btn-block">存通讯录</button>
                </div>
            </div>
        </div>
    </div>
    <div class="container full-height">
      <div class="container-fluid full-height">
        <div class="row full-height">
          <div class="col-md-4 account-list-container full-height">
              <h2>邮箱</h2><table class="account-list table table-bordered table-striped table-hover full-width">
                <thead>
                </thead>
                <tbody>
                <%
                	for (MailAddress mailAddress : db.getMailAddressByUser(user)) {
                		%>
                <tr class="account-list-item full-width">
                  <td>
                    <div class="header full-width">
                      <div style="" class="row info-header full-width">
                        <div class="col-md-8">
                          <p class="email"><%= mailAddress.getAccount() %></p>
                        </div>
                        <div class="float-right">
                          <div style="display: none;" class="btn-group button-group" role="group">
                            <button type="button" id="edit" class="btn btn-info">
                              <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                            </button>
                            <button type="button" id="delete" class="btn btn-danger">
                              <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                              <span class="text"></span>
                            </button>
                          </div>
                        </div>
                      </div>
                      <div style="display: none;" class="edit-header row">
                        <div class="btn-group col-md-12" role="group">
                          <button type="button" id="save" class="btn btn-lg btn-default col-md-6">
                            <span class="glyphicon glyphicon-save" aria-hidden="true"></span>
                          </button>
                          <button type="button" id="cancel" class="btn btn-lg btn-primary col-md-6">
                            <span class="glyphicon glyphicon-remove-sign" aria-hidden="true"></span>
                          </button>
                        </div>
                      </div>
                    </div>

                    <div style="display: none;" class="body">
                      <div class="info-body">
                        <form action="mail/configMailBox.jsp">
                          <div class="form-group">
                            <label for="email">邮箱地址</label>
                            <input class="form-control" id="email" placeholder="邮箱地址" type="email">
                          </div>
                          <div class="form-group">
                            <label for="password">密码</label>
                            <input class="form-control" id="password" placeholder="" type="password">
                          </div>
                        </form>
                      </div>
                      <div class="list-body">
                        <table class="table table table-bordered table-striped table-hover mailbox-list">
                          <tbody><tr class="mailbox-list-item">
                            <td>
                              <p><span class="glyphicon glyphicon-download" aria-hidden="true"></span> 收件箱</p>
                            </td>
                          </tr>
                          <tr class="mailbox-list-item">
                            <td>
                              <p><span class="glyphicon glyphicon-upload" aria-hidden="true"></span> 发件箱</p>
                            </td>
                          </tr>
                          <tr class="mailbox-list-item">
                            <td>
                              <p><span class="glyphicon glyphicon-inbox" aria-hidden="true"></span> 草稿箱</p>
                            </td>
                          </tr>
                          <tr class="mailbox-list-item">
                            <td>
                              <p><span class="glyphicon glyphicon-trash" aria-hidden="true"></span> 回收站</p>
                            </td>
                          </tr>
                        </tbody></table>
                      </div>
                    </div>
                  </td>
                </tr>
                		<%
                	}
                %>

                <tr class="list-item-adder">
                  <td>
                    <button type="button" class="btn btn-default btn-lg center-block" data-toggle="modal" data-target=".modal-login">
                      <span class="glyphicon glyphicon-plus" aria-hidden="true"></span> 添加
                    </button>
                  </td>
                </tr>
              </tbody></table>
          </div>
          <div class="col-md-8 full-height">
            <div style="display: none;" class="mail-list-container full-height full-width">
                <div class="mail-list-toolbar full-width">
                  <div class="row full-width">
                    <h4 class="col-md-2">邮件</h4>
                    <div class="col-md-2">
                        <button class="btn btn-primary change">切换为缓存邮箱</button>
                    </div>
                    <div class="float-right btn-group">
                        <button class="btn mail-btn btn-default">删除&nbsp;&nbsp;<span class="badge"></span></button>
                        <button class="btn mail-btn btn-default">转发&nbsp;&nbsp;<span class="badge"></span></button>
                        <button class="btn mail-btn btn-info">撤销&nbsp;&nbsp;</button>
                    </div>
                  </div>
                </div>
                <div class="panel-group scroll-auto" id="accordion" role="tablist" aria-multiselectable="true">
                    <div class="panel panel-default">
                        <div class="panel-heading" role="tab" id="headingOne">
                            <h4 class="panel-title">
                                <div class="panel-control">
                                    <a class="panel-head" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                                        <div class="row mail-row">
                                            <div class="col-md-2"><h4 class="text-info">星巴克星享俱乐部</h4></div>
                                            <div class="col-md-10">星享卡TM可在中国大陆任何一家参加星享俱乐部活动的星巴克门店使用。星享俱乐部会员好礼及其他活动受星享俱乐部网站...</div>
                                            <div class="col-md-12"><small class="date">2015/12/18</small></div>
                                        </div>
                                    </a>
                                    <div style="display: none;" class="row mail-row panel-btn">
                                        <hr>
                                        <div class="float-right btn-group">
                                            <button class="btn btn-danger mail-btn"> 删除</button>
                                            <button class="btn btn-default mail-btn"> 转发</button>
                                            <button class="btn btn-default mail-btn"> 加密</button>
                                            <button class="btn btn-default mail-btn"> 移到缓存</button>
                                        </div>
                                    </div>
                                </div>
                            </h4>
                        </div>
                        <div id="collapseOne" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">
                            <div class="panel-body">
                                <div class="row">
                                    <div class="col-md-3">
                                        发件人：<button type="button" class="btn btn-info" data-toggle="modal" data-target=".bs-example-modal-sm">星巴克星享俱乐部</button>
                                    </div>
                                    <div class="col-md-6">
                                        <p class="mail-them text-info">主题：新年心愿，祝您新年快乐</p>
                                    </div>
                                    <div class="col-md-3 mail-date text-primary">
                                        <div class="mail-date">
                                            具体时间：16:52:12&nbsp;&nbsp;
                                            附件：<a href="#"><img src="4.png" class="mail-ap"></a>
                                        </div>
                                    </div>
                                </div>

                                <hr>
                                <div class="mail-content">
${{MailContent}}
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="foot-nav">
                    <nav>
                        <ul class="pagination">
                            <li>
                                <a href="#" aria-label="Previous">
                                    <span aria-hidden="true">«</span>
                                </a>
                            </li>
                            <li><a href="#">1</a></li>
                            <li><a href="#">2</a></li>
                            <li><a href="#">...</a></li>
                            <li><a href="#">14</a></li>
                            <li><a href="#">15</a></li>
                            <li>
                                <a href="#" aria-label="Next">
                                    <span aria-hidden="true">»</span>
                                </a>
                            </li>
                        </ul>
                    </nav>
                </div>
            </div>
            <div style="display: none;" class="editor-container full-height" border-width="50px">
            <form class="form-horizontal">
              <h2>发送邮件</h2>
              <div class="form-group">
                <label for="sender-address" class="col-sm-2 control-label">发件箱</label>
                <div class="col-sm-10">
                  <input class="form-control" id="sender-address" placeholder="发件箱" type="email">
                </div>
              </div>
              <div class="form-group">
                <label for="reciever-address" class="col-sm-2 control-label">收件箱</label>
                <div class="col-sm-10">
                  <input class="form-control" id="reciever-address" placeholder="收件箱" type="email">
                </div>
              </div>
              <div class="form-group">
                <label for="title" class="col-sm-2 control-label">标题</label>
                <div class="col-sm-10">
                  <input class="form-control" id="title" placeholder="标题" type="text">
                </div>
              </div>
              <div class="form-group">
                <label for="edit-area" class="col-sm-2 control-label">正文</label>
                <div class="col-sm-10">      
                    <div class="btn-group full-width" id="toolbar">
                      <button type="button" class="btn btn-default btn-sm with-hh">
                          <span class="glyphicon glyphicon-text-size" aria-hidden="true">字体大小</span>
                      </button>

                      <button type="button" class="btn btn-default btn-sm with-hh">
                          <span class="glyphicon glyphicon-text-color" aria-hidden="true">字体颜色</span>
                      </button>

                      <button type="button" class="btn btn-default btn-sm with-hh">
                          <span class="glyphicon glyphicon-align-left" aria-hidden="true">左对齐</span>
                      </button>

                      <button type="button" class="btn btn-default btn-sm with-hh">
                          <span class="glyphicon glyphicon-align-center" aria-hidden="true">居中</span>
                      </button>

                      <button type="button" class="btn btn-default btn-sm with-hh">
                          <span class="glyphicon glyphicon-align-right" aria-hidden="true">右对齐</span>
                      </button>

                      <button type="button" class="btn btn-default btn-sm with-hh">
                          <span class="glyphicon glyphicon-align-justify" aria-hidden="true">平铺</span>
                      </button>

                      <button type="button" class="btn btn-default btn-sm with-hh">
                          <span class="glyphicon glyphicon-paperclip" aria-hidden="true">添加附件</span>
                      </button>

                      <button class="btn btn-default btn-sm with-hh scrolltop">
                          <span class="glyphicon glyphicon-triangle-top" aria-hidden="true">回到顶部</span>
                      </button>
                  </div>
                  <textarea id="edit-area" class="form-control textarea" rows="15"></textarea>
                </div>

              </div>              
            </form>
            <form class="form" role="form">
                <div class="form-group">
                  <div class="col-sm-offset-9 col-sm-1">
                     <button type="submit" class="btn btn-default">发送</button>
                  </div>
               </div>
               <div class="form-group">
                  <div class="col-sm-2">
                     <button type="submit" class="btn btn-default">存入草稿箱</button>
                  </div>
               </div>
            </form>
            </div>
        </div>
      </div>
    </div>

    <div data-status="closed" style="right: -300px;" class="container sidebar sidebar-right" id="contact-list-wrapper"><div class="row">
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg1-12" data-side="right">

      <div id="contact-list-container" class="contact-list-container full-height">
            <h2>通讯录</h2><table class="contact-list table table-bordered table-striped table-hover">
              <thead>
                
              </thead>
              <tbody>
                  <tr class="contact-list-item">
                    <td>
                      <div class="header full-width">
                        <div style="" class="row info-header full-width">
                          <div class="col-md-6">
                            <p class="nickname">杨程</p>
                            <p class="email">baka@626.com</p>
                          </div>
                          <div class="float-right">
                            <div style="display: none;" class="btn-group button-group" role="group">
                              <button type="button" class="btn btn-info send">
                                <span class="glyphicon glyphicon-envelope" aria-hidden="true"></span>
                              </button>
                              <button type="button" id="edit" class="btn btn-info">
                                <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                              </button>
                              <button type="button" id="delete" class="btn btn-danger">
                                <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                                <span class="text"></span>
                              </button>
                            </div>
                          </div>
                        </div>
                        <div style="display: none;" class="edit-header row">
                          <div class="btn-group col-md-12" role="group">
                            <button type="button" id="save" class="btn btn-lg btn-default col-md-6">
                              <span class="glyphicon glyphicon-save" aria-hidden="true"></span>
                            </button>
                            <button type="button" id="cancel" class="btn btn-lg btn-primary col-md-6">
                              <span class="glyphicon glyphicon-remove-sign" aria-hidden="true"></span>
                            </button>
                          </div>
                        </div>
                      </div>

                      <div style="display: none;" class="body">
                        <div class="info-body">
                          <form>
                            <div class="form-group">
                              <label for="nickname">昵称</label>
                              <input class="form-control" id="nickname" placeholder="昵称" type="text">
                            </div>
                            <div class="form-group">
                              <label for="email">邮箱</label>
                              <input class="form-control" id="email" placeholder="邮箱地址" type="email">
                            </div>
                            <div class="form-group">
                              <label for="password">密码</label>
                              <input class="form-control" id="password" placeholder="" type="password">
                            </div>
                          </form>
                        </div>
                        <div class="list-body">
                          <hr class="custom">
                          <form>
                            <div class="form-group">
                              <label for="cellphone">手机</label>
                              <input class="form-control" id="cellphone" placeholder="手机号码" readonly="" type="text">
                            </div>
                            <div class="form-group">
                              <label for="location">所在地</label>
                              <input class="form-control" id="location" placeholder="所在地" readonly="" type="text">
                            </div>
                          </form>
                        </div>
                        </div>
                      
                    </td>
                  </tr>
              </tbody>
              <tfoot> 
                  <tr class="list-item-adder">
                    <td>
                      <button type="button" class="btn btn-default btn-lg center-block">
                        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span> 添加
                      </button>
                    </td>
                  </tr>
              </tfoot>
            </table>
      </div>
     </div>
</div><div class="toggler">
	<span style="display: none;" class="glyphicon glyphicon-chevron-right">&nbsp;</span> <span style="display: block;" class="glyphicon glyphicon-chevron-left">&nbsp;</span>
</div></div>

    <div class="modal modal-login fade" role="dialog" aria-labelledby="gridSystemModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                    <h4 class="modal-title" id="gridSystemModalLabel"><a class="btn" data-toggle="modal" data-target=".sign">注册</a> | <a class="btn">登录</a></h4>
                </div>
                <div class="modal-body">
                    <div class="container-fluid">
                        <table class="table" rules="cols">
                            <tbody><tr>
                                <td>
                                    <div class="login">
                                        <form class="user-input">
                                            <div class="form-group">
                                                <input class="form-control" id="exampleInputEmail1" placeholder="请输入Email账号" type="email">
                                                <label class="text-danger">账号不存在</label>
                                            </div>
                                            <div class="form-group">
                                                <input class="form-control" id="exampleInputPassword1" placeholder="请输入密码" type="password">
                                                <label class="text-danger">密码错误</label>
                                            </div>
                                            <label>
                                                <input type="checkbox"> 记住密码
                                            </label>
                                            <label>
                                                <input type="checkbox"> 自动登录
                                            </label>
                                        </form>
                                    </div>
                                </td>

                                <td>
                                    <ul class="box vertical-list">
                                        <li>
                                            <div class="btn login-box">
                                                <ul class="box each-user">
                                                    <li><img src="resource/1.jpg" class="login-img"></li>
                                                    <li>
                                                        <ul class="box vertical-list">
                                                            <li><h4 class="text-muted">马景阳</h4></li>
                                                            <li class="saved-user-email"><small class="text-info">rtnelo@qq.com</small></li>
                                                        </ul>
                                                    </li>
                                                    <li>
                                                        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                                            <span aria-hidden="true">×</span>
                                                        </button>
                                                    </li>
                                                </ul>
                                            </div>
                                        </li>
                                        <li>
                                            <div class="btn more">
                                                <img src="resource/2.png" class="img-thumbnail">
                                            </div>
                                        </li>
                                    </ul>
                                </td>
                            </tr>
                        </tbody></table>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary">登录</button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->

    <div class="modal sign">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                    <h4 class="modal-title">注册</h4>
                </div>
                <div class="modal-body">


                             <form class="form-horizontal">
              <div class="form-group">
                <label for="sender-address" class="col-sm-2 control-label">用户名</label>
                <div class="col-sm-10">
                  <input class="form-control" id="sender-address" placeholder="用户名" type="email">
                  <p class="text-success">格式正确✔</p>
                </div>
              </div>
              <div class="form-group">
                <label for="reciever-address" class="col-sm-2 control-label">邮箱地址</label>
                <div class="col-sm-10">
                  <input class="form-control" id="reciever-address" placeholder="邮箱地址" type="email">
                  <p class="text-success">格式正确✔</p>
                </div>
              </div>
              <div class="form-group">
                <label for="title" class="col-sm-2 control-label">密码</label>
                <div class="col-sm-10">
                  <input class="form-control" id="title" placeholder="密码" type="password">
                </div>
              </div>  
              <div class="form-group">
                <label for="title" class="col-sm-2 control-label">密码确认</label>
                <div class="col-sm-10">
                  <input class="form-control" id="title" placeholder="密码确认" type="password">
                  <p class="text-danger">密码不匹配</p>
                </div>
              </div>                      
            </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-primary">提交</button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->

    <script type="text/javascript" src="js/mail.js"></script>
  

</div>

</body>
</html>