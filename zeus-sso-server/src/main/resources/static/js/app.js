function getUrlParam(name) {
    let reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    let r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]);
    return null;
}

function goPage(isLoginPage) {
    let title = isLoginPage ? '登陆' : '注册';
    let params = {
        action: isLoginPage ? 'sign-in' : 'sign-up'
    }
    history.replaceState(params, title, '?' + $.param(params));
}

$(function () {
    const sign_in_btn = document.querySelector("#sign-in-btn");
    const sign_up_btn = document.querySelector("#sign-up-btn");
    const container = document.querySelector(".container");

    sign_up_btn.addEventListener("click", () => {
        goPage(false);
        hashChangeFire();
    });

    sign_in_btn.addEventListener("click", () => {
        goPage(true);
        hashChangeFire();
    });

    let hashChangeFire = function () {
        let action = getUrlParam('action') ?? 'sign-in';
        let isLogin = action === 'sign-in';
        if (!isLogin) {
            container.classList.add("sign-up-mode");
        } else {
            container.classList.remove("sign-up-mode");
        }
    }
    hashChangeFire();

    let joinError = $('#join-error');
    let $phone = $('#sign-up-form input[name="phone"]');
    $('#join-btn').bind('click', () => {
        joinError.css('display', 'none');
        let href = $('#sign-up-form').attr('href');
        let username = $('#sign-up-form input[name="username"]').val();
        let phone = $phone.val();
        let sms = $('#sign-up-form input[name="sms"]').val();
        let password = $('#sign-up-form input[name="password"]').val();

        let callback = ({success, message}) => {
            joinError.css('display', 'block');
            if (!success) {
                joinError.text(message || '注册失败');
            } else {
                window.location.href = '/login';
            }
        };
        $.ajax({
            type: "POST", url: href, dataType: 'json',
            data: {username, phone, sms, password},
            success: callback,
            error: ({responseJSON}) => callback(responseJSON)
        });
    });

    const $getVerifyCode = $('#get-verify-code');
    $getVerifyCode.on('click', () => {
        joinError.css('display', 'none');
        if ($getVerifyCode.val()) {
            // 正在获取, 请稍后
            return;
        }

        let phone = $phone.val();

        // 倒计时
        let handler = () => {
            let value = $getVerifyCode.val();
            if (value <= 0) {
                $getVerifyCode.text($getVerifyCode.attr('deftext') || '获取验证码');
                $getVerifyCode.val(null);
                return;
            } else {
                let targetValue = value - 1;
                $getVerifyCode.val(targetValue);
                $getVerifyCode.text(`${targetValue} 秒`)
            }
            setTimeout(handler, 1000);
        };

        // 发送短信
        let callback = ({success, message}) => {
            joinError.css('display', 'block');
            if (!success) {
                joinError.text(message || '注册失败');
            } else {
                $getVerifyCode.val(59);
                $getVerifyCode.text(`59 秒`)
                handler();
            }
        };
        $.ajax({
            type: "POST", url: '/get-code', dataType: 'json',
            data: {phone: phone},
            success: callback,
            error: ({responseJSON}) => callback(responseJSON)
        });
    });


    // 点击获取微信二维码
    let $wxQrcode = $('#wx-qrcode');
    const $getWxQrCode = $('#get-wx-qrcode');
    let reWxLoginStatus = () => {
        let idflag = $wxQrcode.data('idflag');
        let callback = ({data}) => {
            let status = data?.status;
            let redirectUrl = data?.redirectUrl;
            if ('success' === status) {
                window.location.href = redirectUrl;
            } else if ('fail' === status) {
                // 重新点击获取二维码
                $wxQrcode.attr('src', null);
                $wxQrcode.data('idflag', null);
                return;
            }
            setTimeout(reWxLoginStatus, 500);
        };
        let redirectUrl = getUrlParam('redirectUrl') || null;
        $.ajax({
            type: "GET", url: `/wx/login-status`, dataType: 'json',
            data: {idFlag: idflag, redirectUrl},
            success: callback,
            error: ({responseJSON}) => callback(responseJSON)
        });
    };
    $getWxQrCode.on('click', () => {
        if ($wxQrcode.data('idflag')) {
            return;
        }

        let callback = ({data}) => {
            let qrCodeUrl = data?.qrCodeUrl;
            let idFlag = data?.idFlag;

            $wxQrcode.attr('src', qrCodeUrl);
            $wxQrcode.data('idflag', idFlag);
            if (idFlag && qrCodeUrl) {
                reWxLoginStatus();
            }
        };
        $.ajax({
            type: "GET", url: '/wx/wxb4df1f0c3dc96256/qrcode', dataType: 'json',
            data: {},
            success: callback,
            error: ({responseJSON}) => callback(responseJSON)
        });
    });

});
