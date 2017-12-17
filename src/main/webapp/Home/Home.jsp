<%--
  Created by IntelliJ IDEA.
  User: Ting
  Date: 2017/8/20
  Time: 20:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Home</title>
    <jsp:include page="_header.jsp"/>
    <style>
        body {
            background: #00FF00 url('../image/wallhaven.jpg') no-repeat;
            background-size: 100%;
        }
    </style>
</head>
<body>
<div class="modal fade" id="SignUpModel">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header"><!--模态框头-->
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">x</button>
                <h4 class="modal-title">Sign Up</h4>
            </div>
            <div class="modal-body"><!--模态框主体-->
                <div class="form-group">
                    <input type="email" id="Email" class="form-control" data-bv-notempty-message="XXX不能为空"
                           placeholder="Email">
                </div>
                <div class="form-group">
                    <input type="text" id="NickName" class="form-control"
                           placeholder="NickName">
                </div>
                <div class="form-group">
                    <input type="password" id="Password" class="form-control" placeholder="Password">
                </div>
                <div class="form-group">
                    <input type="text" id="RealName" class="form-control"
                           placeholder="RealName">
                </div>
                <div class="form-group">
                    <select class="form-control" id="Gender">
                        <option value="" selected="true" disabled="true">Gender</option>
                        <option>Boy</option>
                        <option>Girl</option>
                    </select>
                </div>
                <div class="form-group"><!--学校选择-->
                    <input type="text" id="school-name" data-toggle="modal" data-target="#SchoolModel"
                           class="form-control" placeholder="Choose your school">
                </div>
                <div class="form-group">
                    <select class="form-control" id="schoolyears">
                        <option value="" selected="true" disabled="true">School Grade</option>
                        <option>freshman</option>
                        <option>sophomore</option>
                        <option>junior</option>
                        <option>senior</option>
                    </select>
                </div>
                <div class="form-group">
                    <input type="text" id="tel" class="form-control"
                           placeholder="tel">
                </div>
                <div class="form-group">
                    <input type="radio" class="pull-right" name="signupRemem" value="Y">rememberMe
                </div>
            </div>
            <div class="modal-footer">
                <div class="form-group">
                    <button class="btn btn-primary btn-lg btn-block" onclick="check_signup_form()">
                        Sign Up
                    </button>
                    <span id="tip"></span>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="SchoolModel">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">x</button>
                <h4 class="modal-title">School</h4>
            </div>
            <div class="modal-body">
                <div id="choose-a-province"></div>
                <div class="divider"></div>
                <div id="choose-a-school" style="height:300px; overflow:scroll;overflow-x:hidden;"></div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Save</button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="SignInModel">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">x</button>
                <h4 class="modal-title">Sign In</h4>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <input type="text" id="EmailOrNickName1" class="form-control input-lg"
                           value="${cookie.ATTRIBUTE_FOR_STORE_USER_NAME_IN_COOKIE.value}"
                           placeholder="UserName or Email">
                </div>
                <div class="form-group">
                    <input type="password" id="Password1" class="form-control input-lg" placeholder="Password">
                </div>
                <div class="form-group">
                    <input type="radio" class="pull-right" name="signinRemem" value="Y">rememberMe
                </div>
            </div>
            <div class="modal-footer">
                <div class="form-group">
                    <button class="btn btn-primary btn-lg btn-block" onclick="check_signin_form()">
                        Sign In
                    </button>
                    <span id="tip1"></span>
                    <span><a href="UserPage/find_password.jsp" class="pull-left">Find password</a></span>
                    <span><a href="" class="pull-right">Haven't signed up yet</a></span>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="container">
    <jsp:include page="_head_for_show.jsp"/>
</div>
<jsp:include page="_footer.jsp"/>
<script>
    function check_signin_form() {
        var user_id = $.trim($('#EmailOrNickName1').val());
        var pass = $.trim($('#Password1').val());
//        var remember = $.trim($('#rememberMe1').val());
        var remember="noremem";
        if($("input[name='signinRemem']:checked").val()!=null){
            remember=$("input[name='signinRemem']:checked").val();
        }
        if (!user_id) {
            alert('ID can not be empty');
            return false;
        }
        if (!pass) {
            alert('Password can not be empty');
            return false;
        }
//        alert(remember);
        $.ajax(
                {
                    url: "/test/login",
                    data: {"user_id": user_id, "pass": pass, "rememberMe1": remember},
                    type: "get",
                    dataType: "json",
                    beforeSend: function () {
                        $("#tip1").html("<span style='color:blue'>Processing</span>");
                        return true;
                    },
                    success: function (data) {
                        var nickname = data['NickName'];
                        var password = data['Password'];
//                        alert(nickname+" "+password);
                        if (nickname == null || nickname == "") {
                            $("#tip1").html("<span style='color:blueviolet'> User not exits or password wrong！</span>");
                        } else {
//                            if($(remember)=="Y"){
//                                Cookies.set('NickName', nickname,{expires:1});
//                                Cookies.set('Password', password,{expires:1});
//                            }else{
//                                Cookies.set('NickName', nickname);
//                                Cookies.set('Password', password);
//                            }
                            transTologin(nickname);
                            $("#tip1").html("<span style='color:blueviolet'> Sign In Successful！</span>");
//                            history.pushState({title:'push',url:'/test/login'});
                        }
                    },
                    error: function () {
                        alert('Request Wrong!');

                    }
                });
        return false;
    }
    function check_signup_form() {
        var flag = true;
        var email = $.trim($('#Email').val());
        var nickname = $.trim($('#NickName').val());
        var password = $.trim($('#Password').val());
        var realname = $.trim($('#RealName').val());
//        var gender = $.trim($("input[name='Gender']:checked").val());
        var gender = $.trim($('#Gender').val());
        var school = $.trim($('#school-name').val());
        var schoolyears = $.trim($('#schoolyears').val());
        var tel = $.trim($('#tel').val());
        var remember="noremem";
        if($("input[name='signupRemem']:checked").val()!=null){
            remember=$("input[name='signinRemem']:checked").val();
        }
//        alert(email + nickname + password + realname + gender + school + schoolyears + tel);
        if (tel.length == 0) {
            alert("手机号码没有输入！");
            flag = false;
            $('#phone').focus();
        } else {
            var telret = /(\d{3}-|\d{4}-)?(\d{8}|\d{7}|\d{11})?/
            if (telret.test(tel) == false) {
                alert("手机或电话号码不正确！");
                flag = false;
                $('#phone').focus();
            }
        }
        if (email.length == 0) {
            alert("电子邮箱不能为空！");
            flag = false;
            $("#Email").focus();
        } else {
            var emailret = /^[\w-]+(\.[\w-]+)*@[\w-]+(\.[\w-]+)+$/;
            if (emailret.test(email) == false) {
                alert("电子邮箱格式不正确！");
                flag = false;
                $('#Email').focus();
            }
        }
        if (flag == true) {
            $.ajax(
                    {
                        url: "/test/signup",
//
                        data: {
                            "email": email, "nickname": nickname, "password": password,
                            "realname": realname, "gender": gender, "school": school, "schoolyears": schoolyears,
                            "tel": tel, "rememberMe": remember
                        },
                        type: "get",
                        dataType: "json",
                        beforeSend: function () {
                            $("#tip").html("<span style='color:blue'>Processing</span>");
                            return true;
                        },
                        success: function (data) {
                            var nickname = data['NickName'];
                            var password = data['Password'];
                            if (nickname == null || nickname == "") {
                                $("#tip").html("<span style='color:blueviolet'> User can't be create！</span>");
                            } else {
//                                if($(remember)=="Y"){
//                                    Cookies.set('NickName', nickname,{expires:1});
//                                    Cookies.set('Password', password,{expires:1});
//                                }else{
//                                    Cookies.set('NickName', nickname);
//                                    Cookies.set('Password', password);
//                                }
                                transTologin(nickname);
                                $("#tip").html("<span style='color:blueviolet'> Sign Up Successful！</span>");
                            }
                        },
                        error: function () {
                            alert('您的邮箱已经注册过该网站！');
//                        setTimeout($("#SignUpModel").modal("hide"),3000);
                        },
                        complete: function () {
                            $('#acting_tips').hide();
                        }
                    }
            )
        }
    }
</script>
<script>
    $("#SchoolModel").on('shown.bs.modal', function () {
        //初始化省份列表
        initProvince();
        //默认情况下, 给第一个省份添加active样式
        $("#choose-a-province li:first").addClass('active');
        //初始化大学列表
        initSchool(1);

    });

    function initProvince() {
        //原先的省份列表清空
//            $('#choose-a-province').html('');
        var string = '';
        for (i = 0; i < schoolList.length; i++) {
            string += '<li><a class="btn province-item" province-id="' + schoolList[i].id + '">' + schoolList[i].name + '</a></li>';
        }
        $('#choose-a-province').html('<ul class="nav nav-pills small">' + string + '</ul>');
        //添加省份列表项的click事件
        $('.province-item').bind('click', function () {
                    var item = $(this);
                    var province = item.attr('province-id');
                    var parent = item.parent();
                    var choosenItem = parent.parent().find('.active');
                    if (choosenItem)
                        $(choosenItem).removeClass('active');
                    item.parent().addClass('active');
                    //更新大学列表
                    initSchool(province);
                }
        );
    }

    function initSchool(provinceID) {
        //原先的学校列表清空
        var string = '';
        var schools = schoolList[provinceID - 1].school;
        for (i = 0; i < schools.length; i++) {
            string += '<li><a class="btn school-item" school-id="' + schools[i].id + '">' + schools[i].name + '</a></li>';
        }
        $('#choose-a-school').html('<ul class="nav nav-pills small">' + string + '</ul>');
        //添加大学列表项的click事件
        $('.school-item').bind('click', function () {
                    var item = $(this);
                    var school = item.attr('school-id');
                    var parent = item.parent();
                    var choosenItem = parent.parent().find('.active');
                    if (choosenItem)
                        $(choosenItem).removeClass('active');
                    item.parent().addClass('active');
                    //更新选择大学文本框中的值
                    $('#school-name').val(item.text());
                }
        );
    }
</script>
<script>
    <%--$(document).ready(function () {--%>
        <%--&lt;%&ndash;if ("${cookie.isLogin.value}" == "Y") {&ndash;%&gt;--%>
        <%--&lt;%&ndash;$("#signin").hide();&ndash;%&gt;--%>
        <%--&lt;%&ndash;$("#signup").hide();&ndash;%&gt;--%>
        <%--&lt;%&ndash;$("#username").text("${cookie.ATTRIBUTE_FOR_STORE_USER_NAME_IN_COOKIE.value}");&ndash;%&gt;--%>
        <%--&lt;%&ndash;$("#username").removeClass("hide");&ndash;%&gt;--%>
        <%--&lt;%&ndash;$("#quit").removeClass("fade");&ndash;%&gt;--%>
        <%--&lt;%&ndash;$('#account').removeClass("hide");//哪个是accout这个？？？&ndash;%&gt;--%>
        <%--&lt;%&ndash;}&ndash;%&gt;--%>
        <%--var user_id = Cookies.get('NickName');--%>
        <%--var pass = Cookies.get('Password');--%>
        <%--var remember = $.trim($('#rememberMe1').val());--%>
        <%--if (user_id!=null && pass!=null) {--%>
            <%--$.ajax(--%>
                    <%--{--%>
                        <%--url: "/test/login",--%>
<%--//--%>
                        <%--data: {"user_id": user_id, "pass": pass,"rememberMe1": remember},--%>
                        <%--type: "get",--%>
                        <%--dataType: "json",--%>
                        <%--success: function (data) {--%>
                            <%--var nickname = data['NickName'];--%>
                            <%--transTologin(nickname);--%>
                        <%--}--%>
                    <%--});--%>
        <%--}--%>
    <%--})--%>
</script>
<script src="../bootstrap-3.3.7-dist/js/school.js" type="text/javascript"></script>
</body>
</html>
