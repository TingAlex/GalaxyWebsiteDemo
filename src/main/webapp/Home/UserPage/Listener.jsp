<%--
  Created by IntelliJ IDEA.
  User: Ting
  Date: 2017/9/21
  Time: 22:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>UserInfo</title>
    <jsp:include page="../_header.jsp"/>
</head>
<body>
<div class="container">
    <jsp:include page="../_userpage_head_for_show.jsp"/>
    <div>
        <div class="row">
            <div class="col-md-5">
                <br>
                <br>
                <img src=${requestScope.headpic} class="img-responsive img-rounded center-block" width="240px" height="240px" id="headpic">
                <script>
                    <%--$("#headpic").attr('src', '/userInfo/IOReadImage/' + ${sessionScope.user.headUID});--%>
                </script>
                <br>
                <div style="text-align: center">Experience:${sessionScope.user.experience} Points</div>
                <br>
                <!--<div class="btn-toolbar " role="toolbar">-->
                <div class="btn-group btn-group-justified" role="group" aria-label="...">
                    <div class="btn-group">
                        <button class="btn btn-primary btn-lg" onclick="change_headpic()">Change</button>
                    </div>
                    <div class="btn-group">
                        <button class="btn btn-primary btn-lg " onclick="delete_headpic()">Delete</button>
                    </div>
                </div>
            </div>
            <div class="col-md-7">
                <div class="modal-body">
                    <div class="form-group">
                        <input type="text" id="Email" class="form-control"
                               value=${sessionScope.user.email}>
                    </div>
                    <div class="form-group">
                        <input type="text" id="NickName" class="form-control"
                               value=${sessionScope.user.nickName}>
                    </div>
                    <div class="form-group">
                        <input type="text" id="RealName" class="form-control"
                               value=${sessionScope.user.name}>
                    </div>
                    <div class="form-group">
                        <select class="form-control" id="Gender">
                            <option value=${sessionScope.user.gender} selected="true" disabled="true">Gender</option>
                            <option>Boy</option>
                            <option>Girl</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <input type="text" id="school-name" data-toggle="modal" data-target="#SchoolMode"
                               class="form-control" value=${sessionScope.user.school}>

                    </div>
                    <div class="form-group">
                        <select class="form-control" id="schoolyears">
                            <option value=${sessionScope.user.schoolYears} selected="true" disabled="true">School
                                Grade
                            </option>
                            <option>freshman</option>
                            <option>sophomore</option>
                            <option>junior</option>
                            <option>senior</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <input type="text" id="tel" class="form-control"
                               value=${sessionScope.user.tel}>
                    </div>
                    <div class="form-group">
                        <input type="text" id="Sign" class="form-control"
                               placeholder="Enter your sign here" value=${sessionScope.user.sign}>
                    </div>
                </div>
                <div class="modal-footer">
                    <div class="form-group">
                        <button class="btn btn-primary btn-lg btn-block" onclick="check_change_info_form()">
                            Change
                        </button>
                        <span id="tip"></span>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <footer class="modal-footer navbar-inverse navbar-fixed-bottom">
        <div class="container" id="foot">
            <div class="row">
                <div class="col-sm-2">
                    <h6>Copyright &copy;MIC of SSDUT</h6>
                    <button class="navbar-toggle col-sm-2" type="button" data-toggle="collapse"
                            data-target="#foot_hide">
                        <span class="sr-only">Toggle Navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                </div>
                <div class="collapse navbar-collapse navbar-responsive-collapse" id="foot_hide">
                    <div class="col-sm-4">
                        <h6>About</h6>
                        <p>New way to explore the Space</p>
                    </div>
                    <div class="col-sm-2">
                        <h6>Navigation</h6>
                        <ul class="unstyled">
                            <li><a href="">Main Page</a></li>
                            <li><a href="">Contact us</a></li>
                        </ul>
                    </div>
                    <div class="col-sm-2">
                        <h6>Follow us</h6>
                        <ul class="unstyled">
                            <li><a href="">Sina Weibo</a></li>
                            <li><a href="">Wechat</a></li>
                        </ul>
                    </div>
                    <div class="col-sm-2">
                        <h6>Powered by MIC</h6>
                    </div>
                </div>
            </div>
        </div>
    </footer>
    <jsp:include page="../_footer.jsp"/>
</div>
<script>
    $(document).ready(function () {
        $("#listener").addClass("active");
    });
</script>
<script>
    //自动访问userPageServlet,加载出user信息显示在网页对应的位置上
    //    $(document).ready(function(){
    //        $.ajax(
    //                {
    //                    url: "/test/userpage",
    //                    dataType: "json",
    //                    success: function (data) {
    //                        var s = "";
    //                        $.each(data, function (k, v) {
    //                            s += k + ":" + v;
    //                        });
    //                        alert(s);
    //                        $("#tip").html("<span style='color:blueviolet'>" + data + "成功！</span>");
    //                    },
    //                    error: function () {
    //                        alert('请求出错');
    //                    }
    //                }
    //        )
    //    });
</script>
<script>
    //尝试删除原来登陆注册的button元素，修改为用户名和退出的a标签
    //    function undologin() {
    //        $("#username").addClass("hide");
    //        $("#quit").addClass("hide");
    //        $("#signin").show();
    //        $("#signup").show();
    //    }
</script>
<script>
    function check_change_info_form() {
        var email = $.trim($('#Email').val());
        var nickname = $.trim($('#NickName').val());
        var realname = $.trim($('#RealName').val());
        var gender = $.trim($('#Gender').val());
        var school = $.trim($('#school-name').val());
        var schoolyears = $.trim($('#schoolyears').val());
        var tel = $.trim($('#tel').val());
        var sign=$.trim($('#Sign').val());
//        alert(email + nickname + password + realname + gender + school + schoolyears + tel);
        $.ajax(
                {
                    url: "/test/changeUserInfoServlet",
                    data: {
                        "email": email, "nickname": nickname, "password": password,
                        "realname": realname, "gender": gender, "school": school, "schoolyears": schoolyears,
                        "tel": tel,"sign":sign
                    },
                    type: "get",
                    dataType: "json",
                    beforeSend: function () {
//                        $("#tip").html("<span style='color:blue'>正在处理...</span>");
                        return true;
                    },
                    success: function (data) {
//                        var s = "";
//                        $.each(data, function (k, v) {
//                            s += k + ":" + v;
//                        });
//                        alert(s);
                        alert("change success!");
//                        $("#tip").html("<span style='color:blueviolet'>" + data + "成功！</span>");
                    },
                    error: function () {
                        alert('change fault');
                    }
//                    complete: function () {
//                        $('#acting_tips').hide();
//                    }
                }
        )
    }
</script>
<script>
    $("#SchoolMode").on('shown.bs.modal', function (e) {
        //初始化省份列表
        initProvince();
        //默认情况下, 给第一个省份添加active样式
        $('#choose-a-province li:first').addClass('active');
        //初始化大学列表
        initSchool(1);
    });
    function initProvince() {
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
</body>
</html>
