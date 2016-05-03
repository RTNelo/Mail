<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.*"  %>

<%!
	User user = null;
	Database db = Database.getDefaultDatabase();
%>

<% user = (User)session.getAttribute("user"); %>

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
            <a class="btn btn-info send">
              <span class="glyphicon glyphicon-envelope" aria-hidden="true"></span>
            </a>
            <a class="btn btn-info">
              <span class="glyphicon glyphicon-refresh" aria-hidden="true"></span>
            </a>            
          </form>
        </div>
      </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
  </nav>

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
                <tbody id='account-list-item-container'>
                <%
                	for (MailAddress mailAddress : db.getMailAddressByUser(user)) {
                		%>
                <tr class="account-list-item full-width" mailid="<%=mailAddress.getId() %>" mailaddress="<%=mailAddress.getAccount() %>">
                  <td>
                    <div class="header full-width">
                      <div style="" class="row info-header full-width">
                        <div class="col-md-8">
                        <% 
                        String account = mailAddress.getAccount();
                        int endPoint = account.indexOf("@");
                        if (endPoint == -1) {
                        	endPoint = account.length();
                        }
                        String nickname = account.substring(0, endPoint);
                        %>
                          <p class="nickname"><%= nickname %></p>
                          <p class="email"><%= account %></p>
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
                          <tbody><tr class="mailbox-list-item" mailtype="RECV" mailid="<%=mailAddress.getId() %>">
                            <td>
                              <p><span class="glyphicon glyphicon-download" aria-hidden="true"></span> 收件箱</p>
                            </td>
                          </tr>
                          <tr class="mailbox-list-item" mailtype="SENT" mailid="<%=mailAddress.getId() %>">
                            <td>
                              <p><span class="glyphicon glyphicon-upload" aria-hidden="true"></span> 发件箱</p>
                            </td>
                          </tr>
                          <tr class="mailbox-list-item" mailtype="DRAFT" mailid="<%=mailAddress.getId() %>">
                            <td>
                              <p><span class="glyphicon glyphicon-inbox" aria-hidden="true"></span> 草稿箱</p>
                            </td>
                          </tr>
                          <tr class="mailbox-list-item" mailtype="TRASH" mailid="<%=mailAddress.getId() %>">
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
                <tfoot>
                <tr class="list-item-adder">
                  <td>
                    <button type="button" class="btn btn-default btn-lg center-block" data-toggle="modal" data-target=".modal-login">
                      <span class="glyphicon glyphicon-plus" aria-hidden="true"></span> 添加
                    </button>
                  </td>
                </tr>
                </tfoot>
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
            <form id="sendmailform" class="form-horizontal">
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
                  <input class="form-control" name="to" id="reciever-address" placeholder="收件箱" type="email">
                </div>
              </div>
              <div class="form-group">
                <label for="title" class="col-sm-2 control-label">标题</label>
                <div class="col-sm-10">
                  <input class="form-control" name="title" id="title" placeholder="标题" type="text">
                </div>
              </div>
              <div class="form-group">
                <label for="edit-area" class="col-sm-2 control-label">正文</label>
                <div class="col-sm-10">      
                  <textarea id="edit-area" name="content" class="form-control textarea" rows="15"></textarea>
                </div>

              </div>              
            </form>
                <div class="form-group">
                  <div class="col-sm-offset-12 col-sm-1">
                     <button id="sendmailbtn" class="btn btn-default">发送</button>
                  </div>
               </div>
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
              <tbody id="contact-list-item-container">
              <% for (Contact contact : db.getContactByUser(user)) { %>
                  <tr class="contact-list-item" itemid=<%= contact.getId() %>>
                    <td>
                      <div class="header full-width">
                        <div style="" class="row info-header full-width">
                          <div class="col-md-6">
                            <p class="nickname"><%=contact.getNickname() %></p>
                            <p class="email"><%=contact.getMailAddress() %></p>
                          </div>
                          <div class="float-right">
                            <div style="display: none;" class="btn-group button-group" role="group">
                              <button type="button" class="btn btn-info send" id="refresh">
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
                          </form>
                        </div>
                      </div>
                    </td>
                  </tr>
              <% } %>
              </tbody>
              <tfoot> 
                  <tr class="list-item-adder">
                    <td>
                      <button type="button" class="btn btn-default btn-lg center-block" data-toggle="modal" data-target=".modal-addaddress">
                        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span> 添加
                      </button>
                    </td>
                  </tr>
              </tfoot>
            </table>
      </div>
     </div>
</div></div>

    <div class="modal modal-login fade" role="dialog" aria-labelledby="gridSystemModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                    <h4 class="modal-title" id="gridSystemModalLabel"><a class="btn">添加邮箱</a></h4>
                </div>
                <div class="modal-body">
                    <div class="container-fluid">
                        <table class="table" rules="cols">
                            <tbody><tr>
                                <td>
                                    <div class="login">
                                        <form id="addaddressform" class="user-input" action="/Mail/addaddress" method="post">
                                            <div class="form-group">
                                                <input class="form-control" name="account" placeholder="请输入Email账号" type="email">
                                                <label class="text-danger" id="mailaddressaddtip"></label>
                                            </div>
                                            <div class="form-group">
                                                <input class="form-control" name="password" placeholder="请输入密码" type="password">
                                            </div>
                                        </form>
                                    </div>
                                </td>
                            </tr>
                        </tbody></table>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary" id='mailaddressaddbtn'>添加</button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->

    <div class="modal modal-addaddress fade" role="dialog" aria-labelledby="gridSystemModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                    <h4 class="modal-title" id="gridSystemModalLabel"><a class="btn">添加联系人</a></h4>
                </div>
                <div class="modal-body">
                    <div class="container-fluid">
                        <table class="table" rules="cols">
                            <tbody><tr>
                                <td>
                                    <div class="login">
                                        <form id="addcontactform" class="user-input" action="/Mail/addaddress" method="post">
                                            <div class="form-group">
                                                <input class="form-control" name="nickname" placeholder="昵称" type="text">
                                                <label id="addcontacttip" class="text-danger"></label>
                                            </div>
                                            <div class="form-group">
                                                <input class="form-control" name="mailaddress" placeholder="邮箱" type="email">
                                            </div>
                                        </form>
                                    </div>
                                </td>
                            </tr>
                        </tbody></table>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="button" id="addcontactbtn" class="btn btn-primary">添加</button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->

	<script type="text/javascript" src="js/jquery.form.js"></script>
    <script type="text/javascript" src="js/mail.js"></script>
  

</div>

</body>
</html>