<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common" %>
<%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/desktop/nav/breadcrumb" %>
<%@ taglib prefix="guidedselling" tagdir="/WEB-INF/tags/addons/b2ctelcostorefront/desktop/guidedselling" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>

<template:page pageTitle="${pageTitle}">
 	<jsp:body>
		<c:if test="${not empty message}">
			<spring:theme code="${message}"/>
		</c:if>
		<div id="globalMessages">
			<common:globalMessages/>
		</div>
		<div class="span-24">
			<div class="span-24 productDetailUpdateable_viewplans">
				<guidedselling:viewAllServicePlans bundleTabs="${product.bundleTabs}" selectProduct="true" showButtons="false" horizontalLayout="false"/>
			</div>
		</div>
	</jsp:body>
</template:page>