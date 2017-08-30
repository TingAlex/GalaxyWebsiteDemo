<%--
  Created by IntelliJ IDEA.
  User: Ting
  Date: 2017/8/30
  Time: 11:03
  To change this template use File | Settings | File Templates.
--%>
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">Galaxy</a>
            <button class="navbar-toggle " type="button" data-toggle="collapse"
                    data-target="#head_hide">
                <span class="sr-only">Toggle Navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
        </div>
        <div class="collapse navbar-collapse navbar-responsive-collapse" id="head_hide">
            <form class="navbar-form navbar-left" role="search">
                <div class="form-group"><input type="text" class="form-control" placeholder="Serarch"></div>
                <button type="submit" class="btn btn-default">Google</button>
            </form>
            <ul class="nav navbar-nav navbar-left">
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">Dropdown
                        <span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="#">Forum</a></li>
                        <li><a href="#">Market</a></li>
                        <li><a href="#">Wiki</a></li>
                    </ul>
                </li>
            </ul>
            <div class="pull-right">
                <button id="signin" data-toggle="modal" data-target="#SignInModel" class="btn navbar-btn btn-primary">
                    Sign in
                </button>
                <a class="hide" href="/test/userpage" id="username"></a>
                <button id="signup" data-toggle="modal" data-target="#SignUpModel" class="btn navbar-btn btn-primary">
                    Sign up
                </button>
                <button id="quit" class="btn navbar-btn btn-primary fade" onclick="undologin()">
                    Quit
                </button>
            </div>
        </div>
    </div>
</nav>
