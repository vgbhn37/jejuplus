<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/view/header.jsp"%>
<link rel="stylesheet" href="/css/contents/list.css" />
<body>
	<div id="contentsLabel">관광지</div>
	<hr>
	<br><br>
	<div id="container">
		<c:forEach var="contents" items="${shoppingList}">
			<div id="img-box"><img src="${contents.thumbnailPath}" onerror="this.src='/images/NoImage.jpg'" id="img"></div>
			<div id="content-box">
				<div id="title">${contents.title}</div> 
				<div id="location">${contents.region1} > ${contents.region2}</div>
				<div id="tag">${contents.tag}</div>
				<div id="icon">
					<div><img src="/images/좋아요.png" class="icon">${contents.recommendedCnt}</div>
					<div><img src="/images/리뷰.png" class="icon">${contents.reviewCnt}</div>
				</div>
				<div><input type="hidden" value="${contents.contentsLabel}"></div>
				<input type="button" onclick="location.href='/contents/shoppingDetail/${contents.contentsId}'" value="자세히보기" id="btn">
			</div>
		</c:forEach>
	</div>
</body>
<script>

</script>
<%@ include file="/WEB-INF/view/footer.jsp"%>