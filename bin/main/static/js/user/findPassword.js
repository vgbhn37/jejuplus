/**
 *  비밀번호 찾기
 */

 $(document).ready(function() {
    
    var codeVerified = false; // 인증 여부를 저장하기 위한 변수

    $("#sendCodeButton").click(function() {
        var username = $("input[name='username']").val();
        var email = $("input[name='email']").val();
        console.log(username);
        console.log(typeof username);
        alert("이메일을 확인 중 입니다. 안내가 나올때까지 잠시만 기다려주세요");
        $.ajax({
            type: 'POST',
            url: '/user/check-id-email',
            data: { username: username, email: email },
            dataType: 'text',
            success: function(response, textStatus, xhr) {
                if (xhr.status === 200) {
                	$.post("/user/send-verification-code", { email: email }, function(response) {
                        alert("인증 코드가 이메일로 전송되었습니다.");
                    });
                } 
            },
            error: function(xhr, status, error) {
                alert("아이디와 이메일이 일치하지않습니다.");
            }
        });
    });
        
     
    $("#verifyCodeButton").click(function() {
        var email = $("input[name='email']").val();
        var code = $("input[name='code']").val();

        // 이메일과 코드를 서버로 전송하여 인증
        $.post("/user/verify-code", { email: email, code: code }, function(response) {
            if (response === "Verification successful") {
                alert("인증이 성공적으로 완료되었습니다.");
                codeVerified = true; // 인증 완료
            } else {
                alert("인증 코드를 다시 한번 확인해주세요");
                codeVerified = false; // 인증 실패
            }
        });
    });
    
       
    $("#reset-password-form").submit(function(event) {
        event.preventDefault();

        var email = $("#reset-password-email").val();
        var messageContainer = $("#reset-password-message");

        if (codeVerified) { // 인증이 성공했을 때에만 임시 비밀번호 발급
            $.ajax({
                type: 'POST',
                url: '/user/reset-password',
                data: { email: email },
                dataType: 'text',
                success: function(response) {
                    alert(response);
                    window.location.href = "/user/sign-in";
                },
                error: function(xhr, status, error) {
                    alert("오류: " + error);
                }
            });
        } else {
            alert("인증되지 않았습니다.");
        }
    });
});