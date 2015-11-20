<%@page pageEncoding="UTF-8"%>
<tiles:insert template="/WEB-INF/view/common/layout.jsp" flush="true">
<tiles:put name="title"  value="Tiles"/>
<tiles:put name="content" type="string">
${f:h(name)}
</tiles:put>
</tiles:insert>