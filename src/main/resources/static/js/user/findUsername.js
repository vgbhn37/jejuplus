/**
 *  아이디 찾기
 */

 $(document).ready(function() {
	$("#find-user-Button").click(function() { 
        event.preventDefault();

        var email = $("#email").val();
		alert("이메일을 확인 중 입니다. 안내가 나올때까지 잠시만 기다려주세요");
        $.ajax({
            type: 'POST',
            url: '/user/find-username',
            data: email,
            contentType: 'application/json',
            dataType: 'text',
            success: function(response) {
                alert("입력하신 이메일에서 아이디를 확인해주세요");
                window.location.href = "/user/sign-in";
            },
            error: function(xhr, status, error) {
                alert('사용자를 찾을 수 없습니다.' );
            }
        });
    });
});