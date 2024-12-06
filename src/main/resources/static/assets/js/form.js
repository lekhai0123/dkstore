(function() {
    $(document).ready(function() {
        // Toggle form khi nhấn vào .alt-form
        $('.alt-form').click(function() {
            $('.form-content').animate({
                height: "toggle",
                opacity: 'toggle'
            }, 0);
        });
		$('.show-forgot-password').click(function () {
		    $('#loginForm, #registerForm').hide(); // Ẩn form đăng nhập
		    $('#forgotPasswordForm').show(); // Hiển thị form quên mật khẩu
		});

		$('.show-login').click(function () {
		    $('#forgotPasswordForm, #registerForm').hide(); // Ẩn các form khác
		    $('#loginForm').show(); // Hiển thị form đăng nhập
		});

		$('.show-register').click(function () {
		    $('#loginForm, #forgotPasswordForm').hide(); // Ẩn các form khác
		    $('#registerForm').show(); // Hiển thị form đăng ký
		});

        // Hiệu ứng active và error cho các input khi mất focus (blur)
        let formInputs = document.getElementsByClassName('form-input');
        for (let i = 0; i < formInputs.length; i++) {
            formInputs[i].addEventListener('blur', function() {
                // Kiểm tra nếu có phần tử nextElementSibling (label)
                if (this.nextElementSibling) {
                    if (this.value.length >= 1) {
                        this.nextElementSibling.classList.add('active');
                        this.nextElementSibling.classList.remove('error');
                    } else if (this.value.length === 0) {
                        this.nextElementSibling.classList.add('error');
                        this.nextElementSibling.classList.remove('active');
                    } else {
                        this.nextElementSibling.classList.remove('active');
                    }
                }
            });
        }

        // Biến để quản lý bước hiện tại
        let currentStep = 1;

        // Hàm hiển thị bước hiện tại và ẩn các bước khác
        function showStep(step) {
            $('.form-step').hide(); // Ẩn tất cả các bước
            $(`#step${step}`).show(); // Hiển thị bước mới
            currentStep = step; // Cập nhật bước hiện tại
        }

        // Hàm kiểm tra email hợp lệ
        function isValidEmail(email) {
            const emailRegex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/;
            return emailRegex.test(email);
        }

        // Hàm kiểm tra mật khẩu hợp lệ (ít nhất 8 ký tự và chứa ít nhất 1 ký tự in hoa)
        function isValidPassword(password) {
            const passwordRegex = /^(?=.*[A-Z]).{8,}$/; // Ít nhất 8 ký tự và 1 ký tự in hoa
            return passwordRegex.test(password);
        }

        // Hàm chuyển đến bước tiếp theo
        function nextStep() {
            var currentStepElement = $(`#step${currentStep}`);
            var isValid = true;

            // Nếu đang ở bước đầu tiên, kiểm tra userName và email
			if (currentStep === 1) {
			    var userName = $("input[name='userName']").val(); // Lấy giá trị userName
			    var email = $("input[name='email']").val(); // Lấy giá trị email
			    var isValid = true;

			    // Kiểm tra tính hợp lệ của email
			    if (!isValidEmail(email)) {
			        toastr.error("Email không hợp lệ!");
			        isValid = false;
			    }

			    // Gửi AJAX kiểm tra username có tồn tại không
			    $.ajax({
			        url: '/check-username', // Đường dẫn kiểm tra userName
			        method: 'POST',
			        data: { userName: userName },
			        success: function(response) {
			            if (response === 'username_exists') {
			                toastr.error("Username đã tồn tại!");
			                showStep(1); // Quay lại bước đầu tiên để sửa userName
			            } else {
			                // Tiếp tục kiểm tra email nếu username hợp lệ
			                $.ajax({
			                    url: '/check-email', // Đường dẫn kiểm tra email
			                    method: 'POST',
			                    data: { email: email },
			                    success: function(response) {
			                        if (response === 'email_exists') {
			                            toastr.error("Email đã tồn tại!");
			                            showStep(1); // Quay lại bước đầu tiên để sửa email
			                        } else {
			                            // Nếu cả username và email hợp lệ, kiểm tra các input ở bước hiện tại
			                            if (isValid) {
			                                currentStepElement.find('.form-input').each(function() {
			                                    if ($(this).val() === '') {
			                                        isValid = false;
			                                        $(this).next('label').addClass('error').removeClass('active');
			                                    } else {
			                                        $(this).next('label').removeClass('error').addClass('active');
			                                    }
			                                });

			                                if (isValid) {
			                                    if (currentStep < 4) { // Kiểm tra để đảm bảo không vượt quá số bước
			                                        showStep(currentStep + 1);
			                                    }
			                                } else {
			                                    toastr.error("Vui lòng điền đủ thông tin!");
			                                }
			                            }
			                        }
			                    },
			                    error: function() {
			                        toastr.error("Có lỗi xảy ra khi kiểm tra email! Vui lòng thử lại.");
			                    }
			                });
			            }
			        },
			        error: function() {
			            toastr.error("Có lỗi xảy ra khi kiểm tra username! Vui lòng thử lại.");
			        }
			    });
			}
			else if (currentStep === 2) {  // Bước kiểm tra mật khẩu
			    var password = $("input[name='passWord']").val(); // Lấy giá trị password
			    var rePassword = $("input[name='re-passWord']").val(); // Lấy giá trị re-enter password

			    var isValidPasswordFields = true;  // Biến để theo dõi tình trạng của các trường mật khẩu

			    // Kiểm tra xem cả hai ô mật khẩu đều có giá trị chưa
			    if (password === '' || rePassword === '') {
					toastr.error("Vui lòng điền đủ thông tin!");
			        isValidPasswordFields = false;  // Đánh dấu là không hợp lệ
			    } else {
			        // Kiểm tra nếu mật khẩu và xác nhận mật khẩu khớp
			        if (password !== rePassword) {
			            toastr.error("Mật khẩu và xác nhận mật khẩu không khớp!");
			            isValidPasswordFields = false;
			        }

			        // Kiểm tra mật khẩu có hợp lệ không (ít nhất 8 ký tự và có ít nhất 1 ký tự in hoa)
			        if (!isValidPassword(password)) {
			            toastr.error("Mật khẩu phải có ít nhất 8 ký tự và chứa ít nhất 1 ký tự in hoa!");
			            isValidPasswordFields = false;
			        }
			    }

			    // Kiểm tra các input ở bước hiện tại (một lần nữa)
			    currentStepElement.find('.form-input').each(function() {
			        if ($(this).val() === '') {
			            isValidPasswordFields = false;
			            $(this).next('label').addClass('error').removeClass('active');
			        } else {
			            $(this).next('label').removeClass('error').addClass('active'); // Thêm lớp active vào label
			        }
			    });

			    if (isValidPasswordFields) {
			        if (currentStep < 4) {  // Kiểm tra để đảm bảo không vượt quá số bước
			            showStep(currentStep + 1);
						console.log(currentStep);
			        }
			    } else {
			         // Hiển thị thông báo lỗi 1 lần
			    }
			}
			else if (currentStep === 3) { // Bước 3 (validate thông tin cá nhân)
			        var fullName = $("input[name='fullName']").val();
			        var birthday = $("input[name='birthday']").val();
			        var gender = $("select[name='gender']").val();
			        
			        if (fullName === '' || birthday === '' || gender === '') {
			            toastr.error("Vui lòng điền đủ thông tin!");
			            isValid = false;
			        }

			        // Kiểm tra các input ở bước này
			        currentStepElement.find('.form-input').each(function() {
			            if ($(this).val() === '') {
			                isValid = false;
			                $(this).next('label').addClass('error').removeClass('active');
			            } else {
			                $(this).next('label').removeClass('error').addClass('active');
			            }
			        });

			        if (isValid) {
			            showStep(currentStep + 1);
			        } else {
			        }
			    }
				else if (currentStep === 4) {
				    // Lấy các giá trị input ở bước 4 (Thông tin liên hệ)
				    var phone = $("input[name='telephone']").val();
				    var address = $("input[name='address']").val();
				    var isValidContactInfo = true;

				    // Kiểm tra nếu các ô 'phone' hoặc 'address' trống
				    if (phone === '' || address === '') {
				        toastr.error("Vui lòng điền đủ thông tin!");
				        isValidContactInfo = false;  // Đánh dấu là không hợp lệ
				    }

				    // Kiểm tra tất cả các input ở bước 4
				    currentStepElement.find('.form-input').each(function() {
				        if ($(this).val() === '') {
				            isValidContactInfo = false;
				            $(this).next('label').addClass('error').removeClass('active');
				        } else {
				            $(this).next('label').removeClass('error').addClass('active');
				        }
				    });

				    // Nếu tất cả các input hợp lệ, submit form
				    if (isValidContactInfo) {
				        $(".cod-form1").submit();  // Submit form
				    } else {
				        console.log("Có lỗi, không submit form.");  // Debug log
				    }
				}

        }

        // Hàm quay lại bước trước đó
        function prevStep() {
            if (currentStep > 1) {
                showStep(currentStep - 1);
            }
        }

        // Gắn sự kiện vào các nút Tiếp tục và Quay lại
        $('.next').click(function() {
            nextStep();
        });

        $('.prev').click(function() {
            prevStep();
        });

        // Hiển thị bước đầu tiên khi load trang
        showStep(1);
    });
})();
