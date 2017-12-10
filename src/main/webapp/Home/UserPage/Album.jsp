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
    <style>
        .inputfile {
            width: 0.1px;
            height: 0.1px;
            opacity: 0;
            overflow: hidden;
            position: absolute;
            z-index: -1;
        }

        .inputfile + label {
            max-width: 80%;
            font-size: 1.25rem;
            /* 20px */
            font-weight: 700;
            text-overflow: ellipsis;
            white-space: nowrap;
            cursor: pointer;
            display: inline-block;
            overflow: hidden;
            padding: 0.625rem 1.25rem;
            /* 10px 20px */
        }

        /* style 6 */

        .inputfile-6 + label {
            color: #d3394c;
        }

        .inputfile-6 + label {
            border: 1px solid #d3394c;
            background-color: #f1e5e6;
            padding: 0;
        }

        .inputfile-6:focus + label,
        .inputfile-6.has-focus + label,
        .inputfile-6 + label:hover {
            border-color: #722040;
        }

        .inputfile-6 + label span,
        .inputfile-6 + label strong {
            padding: 0.625rem 1.25rem;
            /* 10px 20px */
        }

        .inputfile-6 + label span {
            width: 200px;
            min-height: 2em;
            display: inline-block;
            text-overflow: ellipsis;
            white-space: nowrap;
            overflow: hidden;
            vertical-align: top;
        }

        .inputfile-6 + label strong {
            height: 100%;
            color: #f1e5e6;
            background-color: #d3394c;
            display: inline-block;
        }

        .inputfile-6:focus + label strong,
        .inputfile-6.has-focus + label strong,
        .inputfile-6 + label:hover strong {
            background-color: #722040;
        }

        @media screen and (max-width: 50em) {
            .inputfile-6 + label strong {
                display: block;
            }
        }

        img {
            width: 100%;
            height: 240px;
        }

        /*special here*/
        .pics {
            position: relative;
        }

        .tools {
            position: absolute;
            text-align: center;
            margin-top: -30%;
            width: 100%;
            opacity: 0;
        }

        .story {
            background-color: #898989;
            margin-top: -50%;
            color: #ffffff;
            width: 100%;
        }

        .dele {
            margin-top: -30%;
            margin-left: 80%;
        }
    </style>
    <link rel="stylesheet" type="text/css" href="../../bootstrap-3.3.7-dist/css/component.css"/>
    <link rel="stylesheet" type="text/css" href="../../bootstrap-3.3.7-dist/js/clipboard.min.js"/>
</head>
<body>
<div class="container">
    <jsp:include page="../_userpage_head_for_show.jsp"/>
    <div>
        <script>(function (e, t, n) {
            var r = e.querySelectorAll("html")[0];
            r.className = r.className.replace(/(^|\s)no-js(\s|$)/, "$1js$2")
        })(document, window, 0);
        </script>
        <script>
            function download(url, method,filename){
                jQuery('<form action="'+url+'" method="'+(method||'post')+'">' +  // action请求路径及推送方法
                        '<input type="text" name="pic" value="'+filename+'"/>' + // 文件名称
                        '</form>')
                        .appendTo('body').submit().remove();
            }
            function getDownload(ob) {
                var picSrc = $(ob).parent().parent().children("img").attr("src");
                var result=picSrc.replace("/test/returnImg?addr=","");
                download('/test/downloadPic', 'post', result);
            }
            function getModify() {
                var picSrc = $("#picSrc").html();
                var title = $("#modifyTitle").attr("placeholder");
                var detial = $("#modifyStory").html();
                $.ajax(
                        {
                            url: "/test/modifyDetail",
                            data: {
                                "srcs": picSrc,
                                "documents": title,
                                "detials": detial
                            },
                            type: "post",
                            dataType: "json",
                            success: function () {
                            }
                        }
                )
            }
            function getDelete(ob) {
                var picSrc = $(ob).parent().parent().children("img").attr("src");
                $.ajax(
                        {
                            url: "/test/deletePic",
                            data: {
                                "pic": picSrc
                            },
                            type: "post",
                            dataType: "json",
                            success: function () {

                            }
                        }
                )
            }
            function appendText(v) {
                var pageNum = v;
                for (var i = 0; i <= pageNum; i++) {
                    var txt3 = document.createElement("button");  // 以 DOM 创建新元素
                    $(txt3).text(i);
                    $(txt3).attr("class", "btn");
                    $(txt3).attr("value", i);
                    $("#pages").append(txt3);
                }
                $(".btn").each(
                        function () {
                            $(this).bind("click", function () {
                                $.ajax(
                                        {
                                            url: "/test/getAlbumPage",
                                            data: {
                                                "pageNum": $(this).val()
                                            },
                                            type: "post",
                                            dataType: "json",
                                            success: function (data) {
                                                var index = 0;
                                                //如果a被点击了，移除其他按钮的点击效果，给a加上效果
                                                var item = $(this);
                                                var parent = item.parent();
                                                var choosenItem = parent.find(".btn-primary");
                                                if (choosenItem) {
                                                    $(choosenItem).removeClass("btn-primary");
                                                }
                                                $("button:eq(" + $(this.target).val() + ")").addClass("btn-primary");
                                                //
                                                $.each(data, function (k, v) {
                                                    if (index == 0) {
                                                    } else {
                                                        createInner(v);
//                                                        for (var a = 0; a < v.length; a++) {
//                                                            $("img:eq(" + a + ")").attr("src", "/test/returnImg?addr=" + v[a].srcs );
//                                                        }
                                                    }
                                                    index = index + 1;
                                                });
                                            }
                                        }
                                )
                            })
                        }
                )
            }
            $(document).ready(
                    function everytime() {
                        var clipboard = new Clipboard('.btn');
                        $.ajax(
                                {
                                    url: "/test/getAlbumPage",
                                    data: {
                                        "pageNum": 0
                                    },
                                    type: "post",
                                    dataType: "json",
                                    success: function (data) {
//                                        var s = "";
                                        var index = 0;
                                        $("button:eq(0)").addClass("btn-primary");
                                        $.each(data, function (k, v) {
//                                            s += k + ":" + v;
                                            //???
                                            if (index == 0) {
                                                appendText(parseInt(v));
                                            } else {
                                                createInner(v);
                                                $('#ModifyModel').on('show.bs.modal', function (event) {
                                                    var picSrc = $(event.relatedTarget).parent().parent(); //get the id from tr
                                                    var src = $(picSrc.children('img')).attr("src");
                                                    var title = $(picSrc.children('p')).html();
                                                    var detail = $($(event.relatedTarget).parent().children('.story').children('p')).html();
                                                    $("#picSrc").html(src);
                                                    $("#modifyTitle").attr("placeholder", title);
                                                    $("#modifyStory").html(detail);
                                                });
//                                                for (var a = 0; a < v.length; a++) {
//                                                    $("img:eq(" + a + ")").attr("src", "/test/returnImg?addr=" + v[a].srcs);
//                                                }
                                            }
                                            index = index + 1;
                                        });
                                    }
                                }
                        )
                    }
            );
            function createInner(data) {
                $("#imgs").empty();
                var colImglist = new Array();
                for (var i = 0; i < data.length; i++) {
                    var colImg = document.createElement("div");
                    var pics = document.createElement("div");
                    var img = document.createElement("img");
                    var docu = document.createElement("p");
                    $(colImg).addClass("col-md-3");
                    $(pics).addClass("pics");
                    $(img).attr("src", "/test/returnImg?addr="+data[i].srcs);
                    $(img).attr("width", "100%");
                    $(docu).attr("width", "100%");
                    $(docu).attr("style", "text-align:center;");
                    $(docu).text(data[i].documents);
                    //create tools
                    var tool = document.createElement("div");
                    $(tool).addClass("tools");
                    //create button1
                    var bt1 = document.createElement("button");
                    $(bt1).addClass("btn btn-default");
                    $(bt1).attr("aria-label", "Left Align");
                    $(bt1).click(function () {
                        getDownload(this);
                    });
                    var down = document.createElement("span");
                    $(down).addClass("glyphicon glyphicon-arrow-down");
                    $(down).attr("aria-hidden", "true");
                    $(bt1).append($(down));
                    //create button2
                    var bt2 = document.createElement("button");
                    $(bt2).addClass("btn btn-default");
                    $(bt2).attr("aria-hidden", "true");
                    $(bt2).attr("data-clipboard-text","/test/returnImg?addr="+data[i].srcs);
                    $(bt2).attr("aria-label", "Left Align");
                    var link = document.createElement("span");
                    $(link).addClass("glyphicon glyphicon-link");
                    $(link).attr("aria-hidden", "true");
                    $(bt2).append($(link));
                    //create button3
                    var bt3 = document.createElement("button");
                    $(bt3).addClass("btn btn-default");
                    $(bt3).attr("data-toggle", "modal");
                    $(bt3).attr("data-target", "#ModifyModel");
                    $(bt3).attr("aria-label", "Left Align");

                    var edit = document.createElement("span");
                    $(edit).addClass("glyphicon glyphicon-edit");
                    $(edit).attr("aria-hidden", "true");
                    $(bt3).append($(edit));
                    //create story
                    var story = document.createElement("div");
                    $(story).addClass("story");
                    var content = document.createElement("p");
                    $(content).text(data[i].details);
                    $(story).append($(content));
                    //create button4
                    var bt4Father = document.createElement("div");
                    $(bt4Father).addClass("dele");
                    var bt4 = document.createElement("button");
                    $(bt4).addClass("btn btn-default");
                    $(bt4).attr("aria-label", "Left Align");
                    $(bt4).click(function () {
                        getDelete(this);
                    });
                    var dele = document.createElement("span");
                    $(dele).addClass("glyphicon glyphicon-remove");
                    $(dele).attr("aria-hidden", "true");
                    $(bt4).append($(dele));
                    $(bt4Father).append(bt4);
                    //add to main frame
                    $(pics).append($(img));
                    $(pics).append($(docu));
                    $(tool).append($(bt1));
                    $(tool).append($(bt2));
                    $(tool).append($(bt3));
                    $(tool).append($(story));
                    $(tool).append($(bt4Father));
                    $(pics).append($(tool));
                    $(colImg).append($(pics));
                    colImglist.push($(colImg));

                }
                //原本的条件是超过4个图片才放2个row，但现在我决定固定设置2个row，但是高度我还没有搞清楚，因为第二个row没有内容没有高度
                //i <= (data.length - 1) / 4
                for (var i = 0; i < 2; i++) {
                    var row = document.createElement("div");
                    $(row).attr("class", "row");
                    $(row).attr("id", i);
                    for (var j = 0; j < 4; j++) {
                        if (colImglist[i * 4 + j] != null) {
                            $(row).append(colImglist[i * 4 + j]);
                        }
                    }
                    $("#imgs").append($(row));
                }
                $(".pics").hover(function () {
                            $(this).children(".tools").stop(true, true).delay(100).animate(
                                    {
//                                        'top':0,
                                        opacity: 0.6
                                    }, 300
                            );
                        }, function () {
                            $(this).children(".tools").stop(true, true).animate(
                                    {
//                                        'top':-300,
                                        opacity: 0
                                    }, 200
                            );
                        }
                );
            }
        </script>
        <br>
        <div class="row">
            <div class="col-md-6">
                <form class="navbar-form navbar-left" role="search">
                    <div class="form-group"><input type="text" class="form-control" placeholder=""></div>
                    <button type="submit" class="btn btn-default">Search</button>
                </form>
            </div>
            <div class="col-md-6">
                <div style="height: 29px">
                    <input type="file" name="uploadFile" id="upfile" class="inputfile inputfile-6"
                           data-multiple-caption="{count} files selected" multiple/>
                    <label for="upfile"><span></span> <strong>
                        Choose a file&hellip;</strong></label>
                    <input class="btn btn-primary" id="upJQuery" style="margin-top: -34px;" type="submit"
                           value="Submit">
                </div>
                <script>
                    $('#upJQuery').on('click', function () {
                        var fd = new FormData();
                        fd.append("upload", 1);
                        fd.append("upfile", $("#upfile").get(0).files[0]);

                        $.ajax({
                            url: "/test/uploadPic",
                            type: "POST",
                            processData: false,
                            contentType: false,
                            dataType: "json",
                            data: fd,
                            success: function (data) {
                                var s = "";
                                $.each(data, function (k, v) {
                                    s += k + ":" + v;
                                });
                                alert(s);
                            }
                        });
                    });
                </script>
            </div>
        </div>
        <br>
        <div id="imgs">
        </div>
        <br>
        <div class="text-center">
            <div class="btn-toolbar">
                <div class="btn-group" id="pages">
                </div>
            </div>
        </div>
    </div>
    <jsp:include page="../_footer.jsp"/>
</div>
<div class="modal fade" id="ModifyModel">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">x</button>
                <h4 class="modal-title">Modify</h4>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <input type="text" id="modifyTitle" class="form-control input-lg">
                </div>
                <div class="form-group">
                    <textarea id="modifyStory" class="form-control"></textarea>
                </div>
                <div class="form-group hide">
                    <p id="picSrc" class="form-control"></p>
                </div>
            </div>
            <div class="modal-footer">
                <div class="form-group">
                    <button class="btn btn-primary btn-lg btn-block" onclick="getModify()">
                        Save
                    </button>
                    <span id="tip1"></span>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    $(document).ready(function () {
        $("#album").addClass("active");
    });
</script>

</body>
</html>
