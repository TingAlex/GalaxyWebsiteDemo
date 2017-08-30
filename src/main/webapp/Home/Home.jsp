<%--
  Created by IntelliJ IDEA.
  User: Ting
  Date: 2017/8/20
  Time: 20:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
    <jsp:include page="_header.jsp"/>
</head>
<body>
<div class="container">
    <jsp:include page="_head_for_show.jsp"/>
    <%--<form class="form-horizontal" role="form" onsubmit="return check_signin_form()">--%>
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
                               placeholder="UserName or Email">
                    </div>
                    <div class="form-group">
                        <input type="password" id="Password1" class="form-control input-lg" placeholder="Password">
                    </div>
                </div>
                <div class="modal-footer">
                    <div class="form-group">
                        <button class="btn btn-primary btn-lg btn-block" onclick="check_signin_form()">
                            Sign In
                        </button>
                        <span id="tip1"></span>
                        <span><a href="#" class="pull-left">Find password</a></span>
                        <span><a href="#" class="pull-right">Sign Up</a></span>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <%--</form>--%>
    <%--<form class="form-horizontal" role="form" onsubmit="return check_signup_form()">--%>
    <div class="modal fade" id="SignUpModel">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">x</button>
                    <h4 class="modal-title">Sign Up</h4>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <input type="text" id="Email" class="form-control"
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
                    <%--<label class="checkbox-inline">--%>
                    <%--<input type="checkbox" value="体育" id="inlineCheckbox1">体育--%>
                    <%--</label>--%>
                    <%--<label class="checkbox-inline">--%>
                    <%--<input type="checkbox" value="音乐" id="inlineCheckbox2">音乐--%>
                    <%--</label>--%>
                    <div class="form-group">
                        <select class="form-control" id="Gender">
                            <option value="" selected="true" disabled="true">Gender</option>
                            <option>Boy</option>
                            <option>Girl</option>
                        </select>
                    </div>
                    <div class="form-group">
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
                        <input type="text" id="TEL" class="form-control"
                               placeholder="TEL">
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
                    <div id="choose-a-school"></div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Save</button>
                </div>
            </div>
        </div>
    </div>
    <%--</form>--%>
    <div class="leftSide">
        <div class="leftSideUp">
            <div>星体类别</div>
            <div>星体参数</div>
            <div>运行参数</div>
            <div>星体位置</div>


        </div>
        <div class="leftSideMiddle">

        </div>
        <div class="leftSideDown">
            <div>历史渊源</div>
            巴啦啦能量！
        </div>

    </div>
    <div class="main">
        3d模型
    </div>
    <div class="rightSide">
        <div class="rightSideUp">
            <div><a href="#">最近值得观测星体</a></div>
            <div><a href="#">时间</a></div>
            <div><a href="#">观测带</a></div>
            <div><a href="#">认领活动</a></div>
            <!--<div>全年观测星图</div>-->
            <div><a href="forum.html">论坛</a></div>
            <div><a href="#">交易</a></div>
            <div><a href="#">天文百科</a></div>
            <%--<button onclick="testInsert()">Click</button>--%>
        </div>
    </div>
</div>
<jsp:include page="_footer.jsp"/>
<script>
    //尝试删除原来登陆注册的button元素，修改为用户名和退出的a标签,在登陆或者注册成功后触发
    function transTologin(username) {
        $("#signin").hide();
        $("#signup").hide();
        $("#username").text(username);
        $("#username").removeClass("hide");
//        $("a").style.textDecoration='none';
//        $("#username").style.color("white");
        $("#quit").removeClass("hide");
    }
    //把登陆注册的按钮再改回来，退出按钮触发
    function undologin() {
        $("#username").addClass("hide");
        $("#quit").addClass("hide");
        $("#signin").show();
        $("#signup").show();
    }
    function check_signin_form() {
        var user_id = $.trim($('#EmailOrNickName1').val());
        var pass = $.trim($('#Password1').val());

        if (!user_id) {
            alert('用户ID不能为空！');
            return false;
        }
        if (!pass) {
            alert('Password不能为空！');
            return false;
        }
//            var form_data = $('#form_data').serialize();

        // 异步提交数据到action/add_action.php页面
        $.ajax(
                {
                    url: "/test/login",
                    data: {"user_id": user_id, "pass": pass},
                    type: "get",
                    dataType: "json",
                    beforeSend: function () {
                        $("#tip1").html("<span style='color:blue'>Processing</span>");
                        return true;
                    },
                    success: function (data) {
                        var s = data['Name'];
                        transTologin(s);
//                        var s = "";
//                        $.each(data, function (k, v) {
//                            s += k + ":" + v;
//                        });
//                        alert(s);
//                            $("#name").text(data['Name']);
//                            $("#Email").text(data['Email']);
//                        $("#signin").text(data['Name']);
//                        $("#signin").removeAttr("data-toggle");
//                        $("#signin").attr("href","http://baidu.com");
//                        $("#signin").removeAttr("data-toggle");
//                        $("#signin").removeAttr("data-target");
//                        $("#signin").onclick(function(){window.location.href = "http://localhost:8080"}());
//                        $("#signup").text("Quit");
//                            $.each(data,function(i,n){
//                            $("#name").text(i+":"+n);
//                            $("#Email").text(i+":"+n);
//                            });
//                            $("#name").text(i+":"+n);
//                            $("#Email").text(i+":"+n);
//                                var msg = "添加";
//                                if(act == "edit") msg = "编辑";
                        $("#tip1").html("<span style='color:blueviolet'> Sign In Successful！</span>");
                        // document.location.href='system_notice.php'
//                                alert(msg + "OK！");
//                        location.reload();
//                            }
//                            else {
//                                $("#tip").html("<span style='color:red'>失败，请重试</span>");
//                                alert('操作失败');
//                            }
                    },
                    error: function () {
                        alert('请求出错');
                    },
                    complete: function () {
                        $('#acting_tips').hide();
                    }
                });
        return false;
    }
    function check_signup_form() {
        var email = $.trim($('#Email').val());
        var nickname = $.trim($('#NickName').val());
        var password = $.trim($('#Password').val());
        var realname = $.trim($('#RealName').val());
//        var gender = $.trim($("input[name='Gender']:checked").val());
        var gender = $.trim($('#Gender').val());
        var school = $.trim($('#school-name').val());
        var schoolyears = $.trim($('#schoolyears').val());
        var tel = $.trim($('#TEL').val());
//        alert(email + nickname + password + realname + gender + school + schoolyears + tel);
        $.ajax(
                {
                    url: "/test/signup",
                    data: {
                        "email": email, "nickname": nickname, "password": password,
                        "realname": realname, "gender": gender, "school": school, "schoolyears": schoolyears,
                        "tel": tel
                    },
                    type: "get",
                    dataType: "json",
                    beforeSend: function () {
                        $("#tip").html("<span style='color:blue'>Processing</span>");
                        return true;
                    },
                    success: function (data) {
                        var s = data['Name'];
                        transTologin(s);
//                        var s="";
//                        $.each(data,function (k, v) {
//                            s+=k+":"+v;
//                        });
//                        alert(s);
                        $("#tip").html("<span style='color:blueviolet'>Sign Up Successful！</span>");
                    },
                    error: function () {
                        alert('请求出错');
                    },
                    complete: function () {
                        $('#acting_tips').hide();
                    }
                }
        )
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
            string += '<li><a class="province-item" province-id="' + schoolList[i].id + '">' + schoolList[i].name + '</a></li>';
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
            string += '<li><a class="school-item" school-id="' + schools[i].id + '">' + schools[i].name + '</a></li>';
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
<script src="../bootstrap-3.3.7-dist/js/school.js" type="text/javascript"></script>
</body>
</html>
