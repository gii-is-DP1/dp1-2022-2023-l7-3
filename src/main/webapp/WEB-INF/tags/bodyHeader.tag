<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="monopoly" tagdir="/WEB-INF/tags" %>

<%@ attribute name="screenTittle" required="true" rtexprvalue="true"
              description="Name of the active screen" %>

<monopoly:menu screenTittle="${screenTittle}"/>
