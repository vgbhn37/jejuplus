/**
 *  회원가입
 */
	var usernameChecked = false;
	var passwordChecked = false;
	var emailChecked = false;
	var codeVerified = false;
	var firstUsername;
	var firstPassword;

	// 이메일 중복확인 겸 코드전송
	$("#sendCodeButton").click(function() {
	    var email = $("input[name='email']").val();

	    // Check for email duplication
	    $.get("/user/check-email", { email: email })
	        .done(function(response) {
	            if (response === "duplicate") {
	                alert("이미 누군가 사용 중인 이메일입니다.");
	                emailChecked = false;
	            } else {
	                // Send a request to get the verification code
	                alert("이메일을 확인 중 입니다. 안내가 나올때까지 잠시만 기다려주세요");
	                $.post("/user/send-verification-code", { email: email })
	                    .done(function(response) {
	                        alert("인증 코드가 이메일로 전송되었습니다.");
	                        emailChecked = true;
	                    })
	                    .fail(function(error) {
	                        alert("인증 코드 전송에 실패했습니다.");
	                        emailChecked = false;
	                    });
	            }
	            checkFormSubmission();
	        })
	        .fail(function(error) {
	            alert("이미 사용중인 이메일 입니다.");
	            emailChecked = false;
	        });
	});

	// 인증하기 버튼
	$("#verifyCodeButton").click(function() {
	    var email = $("input[name='email']").val();
	    var code = $("input[name='code']").val();

	    // Verify the code with the server
	    $.post("/user/verify-code", { email: email, code: code }, function(response) {
	        if (response === "Verification successful") {
	            alert("인증이 성공적으로 완료되었습니다.");
	            codeVerified = true;
	        } else {
	            alert("인증 실패");
	            codeVerified = false;
	        }
	        checkFormSubmission();
	    });
	});

	// 아이디 중복 확인
	$("#checkUsernameButton").click(function(event) {

	    var username = $("#usernameInput").val();

	    var maxUsernameLength = 15;
	    var minUsernameLength = 6;

	    if (username.length > maxUsernameLength || username.length < minUsernameLength) {
	        alert("아이디는 6자에서 15자 사이여야 합니다.");
	        usernameChecked = false;
	    } else {
	        $.get("/user/check-username", { username: username }, function(response) {
	            if (response === "Username available") {
	                alert("사용 가능한 아이디입니다.");
	                usernameChecked = true;
	                firstUsername = username; // Update the hidden input field
	                $("#firstUsernameInput").val(username);

	            } else {
	                alert("아이디가 이미 존재하거나 한글로 작성했습니다.");
	                usernameChecked = false;
	            }
	            checkFormSubmission();
	        });
	    }
	});

	// 비밀번호 체크
	$("#checkUserPasswordButton").click(function(event) {
	    var password = $("#passwordInput").val();

		
	    var maxPasswordLength = 20;
	    var minPasswordLength = 8;

	    if (password.length > maxPasswordLength || password.length < minPasswordLength) {
	        alert("비밀번호는 8자에서 20자 사이여야 합니다.");
	        passwordChecked = false;
	    } else {
	        $.get("/user/check-password", { password: password }, function(response) {
	            if (response === "Password valid") {
	                alert("사용 가능한 비밀번호입니다.");
	                passwordChecked = true;
	                firstPassword = password; // Update the hidden input field
	                $("#firstPasswordInput").val(password);

	            } else {
	                alert("비밀번호가 요구 사항을 충족하지 않습니다.");
	                passwordChecked = false;
	            }
	            checkFormSubmission();
	        });
	    }
	});

	function checkFormSubmission() {
	    // Enable the "가입하기" button only if all checks are successful
	    if (usernameChecked && passwordChecked && emailChecked && codeVerified) {
	        $("#register-final-button").prop("disabled", false);
	    } else {
	        $("#register-final-button").prop("disabled", true);
	    }
	}



       
        // 가입하기 버튼 클릭 시
        $("#registrationForm").submit(function(event) {
            event.preventDefault(); // 폼 제출 방지
            var formData = $(this).serialize();
            var username = $("#usernameInput").val();
            var password = $("#passwordInput").val();
            var email = $("#emailInput").val();
    	    var code = $("#codeInput").val();
    	    var fullname = $("#fullnameInput").val();
    	    var phoneNumber = $("#phoneNumberInput").val();
          
            
            
            
            if( !username ){
            	alert("아이디가 비어있습니다.");
            }
            if( !password ){
            	alert("패스워드가 비어있습니다.");
            }
            if( !email ){
            	alert("이메일이 비어있습니다.");
            }
            if( !code ){
            	alert("인증코드가 비어있습니다.");
            }
            if( !fullname ){
            	alert("이름이 비어있습니다.");
            }
            if( !phoneNumber ){
            	alert("전화번호가 비어있습니다.");
            } else {
            	
			console.log(username);
			console.log(firstUsername);
			console.log(password);
			console.log(firstPassword);
			
            if(firstUsername == username && firstPassword == password){
             $.post("/user/register", formData, function(response) {
                if (response === "Registration successful") {
                    alert("가입이 성공적으로 완료되었습니다.");
                    window.location.href = "/user/sign-in"; // 로그인 페이지로 이동
                } else {
                    alert("가입 실패" ); // 실패 메시지를 표시
                }
            })
            .fail(function(jqXHR, textStatus, errorThrown) {
                if (jqXHR.status === 400) {
                    alert("아이디 또는 이메일을 확인해주세요."); // 400 (Bad Request) 오류 메시지
                } else {
                    alert("가입에 실패했습니다. 다시 시도해주세요."); // 다른 오류의 경우 메시지
                }
            });
        } else {
        	alert("입력하신 정보가 변경되었습니다.");
        }
        }
        });