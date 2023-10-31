<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/header.jsp" %>
<!-- air css -->
<link rel="stylesheet" href="../../css/air/air.css" />
<!-- js -->
<script src="../../js/air/air.js"></script>
       <!-- main -->
        <main>
        
            <!-- 항공 스케줄 선택 start -->
            
            <section class="flight">
                <div class="flight-container">
	                <form action="/air/index" method="post">
	                    <h4>항공권</h4>
	                    <div class="flight-type">
	                        <label for="round-trip"><input type="radio" name="flight-type" id="round-trip" />왕복</label>
	                        <label for="oneway"><input type="radio" name="flight-type" id="oneway" />편도</label>
	                    </div>
	                    <div class="flight-search">
	                        <!-- dep -->
	                        <div class="dep-name">
	                            <label for="dep-name">출발지</label>
	                            <div class="dep-search">
	                            <select>
	                        		<option>${depAirportId}</option>
	                        	</select>
<!-- 	                                <i class="fas fa-map-marker-alt"></i> -->
<!-- 	                                <i class="fas fa-times-circle"></i> -->
<!-- 	                                <input -->
<!-- 	                                    type="text" -->
<!-- 	                                    class="dep-input" -->
<!-- 	                                    id="dep-name" -->
<!-- 	                                    name="depAirportId" -->
<!-- 	                                    placeholder="출발지" -->
<!-- 	                                /> -->
	                            </div>
	                        </div>
	                        <!-- dep end -->
	
	                        <!-- arr start -->
	                        <div class="arr-name">
	                            <label for="arr-name">도착지</label>
	                            <div class="arr-search">
	                                <i class="fas fa-map-marker-alt"></i>
	                                <i class="fas fa-times-circle"></i>
	                                <input
	                                    type="text"
	                                    class="arr-input"
	                                    id="arr-name"
	                                    name="arrAirportId"
	                                    placeholder="도착지"
	                                />
	                            </div>
	                        </div>
	                        <!-- arr end -->
	
	                        <!-- customer start -->
							<div class="customer-container">
							    <label for="">탑승객</label>
							    <div class="customer-option">
							        <span>성인</span>
							        <div class="passenger-selector">
							            <a class="cus-minus-adult">-</a>
							            <span class="passengerCountAdult" name="passengerCount">1</span>
							            <a class="cus-plus-adult">+</a>
							        </div>
							    </div>
							    <div class="customer-option">
							        <span>어린이</span>
							        <div class="passenger-selector">
							            <a class="cus-minus-child">-</a>
							            <span class="passengerCountChild" name="passengerCount">1</span>
							            <a class="cus-plus-child">+</a>
							        </div>
							    </div>
							</div>



	                        <!-- customer end -->
	                    </div>
	                    
	                    <!-- 날짜 선택 -->
	                    <div class="calnender-container px-1 px-sm-5 mx-auto">
	                        <form autocomplete="off">
	                            <div class="flex-row d-flex justify-content-center">
	                                <div class="col-xl-5 col-lg-6 col-11 px-1">
	                                    <div class="input-group input-daterange">
	                                        <label class="calender-label">날짜 선택 : </label>
	                                        <input type="text" name="daterange" class="calender-input" readonly />
	                                    </div>
	                                </div>
	                            </div>
	                        </form>
	                    </div>
	                    <!-- 예약하기 버튼 -->
	                    <div class="flight-find">
	                        <button type="submit" class="flight-find-btn">예약하기</button>
	                    </div>
	                    <!-- 예약하기 버튼 -->
                    </form>
                </div>
            </section>
           
            <!-- 항공 스케줄 선택 end -->

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
<%@ include file="/WEB-INF/view/footer.jsp" %>