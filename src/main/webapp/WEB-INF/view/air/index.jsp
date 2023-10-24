<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/header.jsp" %>
<!-- air css -->
<link rel="stylesheet" href="../../css/air/air.css" />

        <!-- main -->
        <main>
            <section class="flight">
                <div class="flight-container">
                    <h4>항공권</h4>
                    <div class="flight-type">
                        <label for="round-trip"><input type="radio" name="flight-type" id="round-trip" />왕복</label>
                        <label for="oneway"><input type="radio" name="flight-type" id="oneway" />편도</label>
                    </div>
                    <div class="flight-search">
                        <div class="dep-name">
                            <label for="dep-name">출발지</label>
                            <div class="dep-input">
                                <input type="text" id="dep-name" name="dep-name" placeholder="출발지" />
                                <i class="fas fa-map-marker-alt"></i>
                                <i class="fas fa-times-circle"></i>
                            </div>
                        </div>
                        <div class="arr-name">
                            <label for="arr-name">도착지</label>
                            <div class="arr-input">
                                <input type="text" id="arr-name" name="arr-name" placeholder="도착지" />
                                <i class="fas fa-map-marker-alt"></i>
                                <i class="fas fa-times-circle"></i>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- 날짜 선택 -->
                <!--    
                    <div class="container px-1 px-sm-5 mx-auto">
                        <form autocomplete="off">
                            <div class="flex-row d-flex justify-content-center">
                                <div class="col-xl-5 col-lg-6 col-11 px-1">
                                    <div class="input-group input-daterange">
                                        <label class="">날짜 선택 : </label>
                                        <input type="text" name="daterange" class="calender-input" readonly />
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                 -->
            </section>

            <!-- 공지사항 start -->
            <section class="air_notice">
                <h2>항공권 공지사항</h2>
                <div class="more_view">
                    <a href="#">더 보기</a>
                </div>
                <div class="notice_list">
                    <table>
                        <tr>
                            <td class="notice-title"><a href="#">[공지] 해외결제 피싱문자 주의!!</a></td>
                            <td class="notice-date">2023-10-24</td>
                        </tr>
                        <tr>
                            <td class="notice-title"><a href="#">[공지] 해외결제 피싱문자 주의!!</a></td>
                            <td class="notice-date">2023-10-24</td>
                        </tr>
                        <tr>
                            <td class="notice-title"><a href="#">[공지] 해외결제 피싱문자 주의!!</a></td>
                            <td class="notice-date">2023-10-24</td>
                        </tr>
                    </table>
                </div>
            </section>
            <!-- 공지사항 end -->
        </main>


        <!-- js -->
        <script src="../../js/main.js"></script>
<%@ include file="/WEB-INF/view/footer.jsp" %>