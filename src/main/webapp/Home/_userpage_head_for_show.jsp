<%--
  Created by IntelliJ IDEA.
  User: Ting
  Date: 2017/8/30
  Time: 11:02
  To change this template use File | Settings | File Templates.
--%>
<%@page isELIgnored="false" %>
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="/test/returnHome">Galaxy</a>
            <button class="navbar-toggle " type="button" data-toggle="collapse"
                    data-target="#head_hide">
                <span class="sr-only">Toggle Navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
        </div>
        <div class="collapse navbar-collapse navbar-responsive-collapse" id="head_hide">
            <ul class="nav navbar-nav navbar-left">
                <li id="self_intro"><a href="/test/userpage">Self-Intro</a></li>
                <li id="album"><a href="/test/userAlbum">Album</a></li>
                <li id="watch"><a href="#">Watch</a></li>
                <li id="listener"><a href="#">Listener</a></li>
                <li id="notice"><a href="#">Notice</a></li>
            </ul>
            <div class="pull-right">
                <a href="/test/userpage">${sessionScope.user.name}</a>&nbsp;
                <a href="/test/quit" id="quit" class="btn navbar-btn btn-primary">Quit</a>
            </div>
        </div>
    </div>
</nav>
