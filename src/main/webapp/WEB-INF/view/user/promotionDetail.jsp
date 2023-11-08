<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/view/header.jsp"%>
<!-- header.jsp  -->
<link rel="stylesheet" href="/css/user/user.css" />
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<body>
<div class="promotionDetail-page">
<h3>소개합니다</h3>
    <label class="promotionDetail-label">소개할 곳</label>
    <input type="text" name="promotiontitle" class="promotionDetail-form-control" readonly value="${promotion.title}">

    <label class="promotionDetail-label">소개글</label>
    <input type="text" name="promotionIntroduce" class="promotionDetail-form-control" readonly value="${promotion.introduce}">

	<label class="promotionDetail-label">상세내용</label>
    <div class="promotionDetail-image-container">
        <c:forEach var="image" items="${images}">
            <div class="promotionDetail-image-wrapper">
                <img src="${image.imgPath}">
            </div>
        </c:forEach>
    </div>

    <div style="margin-top: 20px;"></div>

    <label class="promotionDetail-label"></label>
    <input type="text" name="promotionContent" class="promotionDetail-form-control" readonly value="${promotion.content}">
</div>

</body>
<%@ include file="/WEB-INF/view/footer.jsp"%>