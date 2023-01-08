<%@ tag trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="monopoly" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ attribute name="screenTittle" required="true" %> 
<%@ attribute name="customScript" required="false" fragment="true"%>

<!doctype html>
<html>
<monopoly:htmlHeader/>

<body>
<monopoly:bodyHeader2 screenTittle="${screenTittle}"/>

<div class="scrollableBodyDiv">
	<jsp:doBody/>
</div>

<monopoly:footer/>
<jsp:invoke fragment="customScript" />

</body>

</html>
