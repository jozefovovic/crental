<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<s:layout-definition>
<!DOCTYPE html>
<html lang="${pageContext.request.locale}">
  <head>
    <title><f:message key="${titlekey}"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta charset="utf-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css" media="screen">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/theme.min.css" media="screen">
    <s:layout-component name="header"/>
  </head>
  <body>
    <div class="navbar navbar-default navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <a href="../" class="navbar-brand">Crental</a>
          <button class="navbar-toggle" type="button" data-toggle="collapse" data-target="#navbar-main">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
        </div>
        <div class="navbar-collapse collapse" id="navbar-main">
          <ul class="nav navbar-nav">
            <li>
              <s:link href="/index.jsp"><f:message key="navigation.index"/></s:link>
            </li>
            <li>
              <s:link beanclass="cz.muni.fi.pompe.crental.web.EmployeeActionBean"><f:message key="navigation.employees"/></s:link>
            </li>
            <li>
              <s:link beanclass="cz.muni.fi.pompe.crental.web.CarActionBean"><f:message key="navigation.car"/></s:link>
            </li>
          </ul>
        </div>
      </div>
    </div>

    <div class="container">

      <div class="page-header" id="banner">
        <div class="row">
            <div class="col-lg-12">
                <h1><f:message key="${titlekey}"/></h1>
            </div>
        </div>
      </div>



      <div class="bs-docs-section">

        <div class="row">
            <s:messages/>
            <s:layout-component name="body"/>
        </div>

      </div>


      <footer>
        <div class="row">
          <div class="col-lg-12">

            <ul class="list-unstyled">
              <li class="pull-right"><a href="#top">Back to top</a></li>
              <li><a href="https://github.com/pompep/crental">GitHub</a></li>
              <li><a href="../help/#api">API</a></li>
            </ul>

            <p>Made by <a href="http://thomaspark.me">Thomas Park</a>. Contact him at <a href="mailto:hello@thomaspark.me">hello@thomaspark.me</a>.</p>
            <p>Code licensed under the <a href="http://www.apache.org/licenses/LICENSE-2.0">Apache License v2.0</a>.</p>
            <p>Based on <a href="http://getbootstrap.com">Bootstrap</a>. Icons from <a href="http://fortawesome.github.io/Font-Awesome/">Font Awesome</a>. Web fonts from <a href="http://www.google.com/webfonts">Google</a>. Favicon by <a href="https://twitter.com/geraldhiller">Gerald Hiller</a>.</p>

          </div>
        </div>

      </footer>


    </div>
  </body>
</html>
</s:layout-definition>