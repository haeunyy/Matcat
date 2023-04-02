window.onload = function () {
    var pwCheck = true;
    var email = true;
    if (document.getElementById("searchZipCode")) {
        const $search = document.getElementById("searchZipCode");
        $search.onclick = function () {
            new daum.Postcode({
                oncomplete: function (data) {
                    document.getElementById("zipCode").value = data.zonecode;
                    document.getElementById("address1").value = data.address;
                    document.getElementById("address2").focus();
                }
            }).open();
        }
    }

    if (document.getElementById("duplicationCheck")) {

        const $duplication = document.getElementById("duplicationCheck");
        const $email = document.getElementById("Email");
        $email.onkeyup = function () {
            email = false;
            $duplication.onclick = function () {
                let memberEmail = document.getElementById("Email").value.trim();
                fetch("/member/emailDupCheck", {
                    method: "POST",
                    headers: {'Content-Type': 'application/json;charset=UTF-8'},
                    body: JSON.stringify({memberEmail})
                })
                    .then(result => result.text())
                    .then(result => {
                        alert(result)
                        if (result === "사용 가능한 이메일 입니다.") {
                            email = true
                        }
                    })
                    .catch((error) => error.text().then((res) => alert(res)));
            }
        }
    }

    if (document.getElementById("memberPwd")) {
        document.getElementById("memberPwd").onkeyup = function () {
            const $value = document.getElementById("memberPwd2");
            pwCheck=false;
            $value.required = true;
            let msg = '';
            let strength = 0;
            let pass = this.value;
            if (pass.length < 8) {
                msg = "8자 이상이어야 합니다.";
            } else {
                strength += 1;
            }
            if (pass.match(/[^a-zA-Z\d]/)) {
                strength += 1;
            } else {
                msg = "특수문자와 숫자를 포함해야 합니다.";
            }
            if (pass.match(/^(?=.*[A-Za-z])(?=.*\d)(?=.*[!@#$%^&*])[A-Za-z\d!@#$%^&*]{8,}$/) && strength > 1) {
                msg = GoodPW();
                pwCheck = true;
            } else {
                msg = BadPW(pass);
            }
            document.getElementById("pwc").textContent = msg;
        }
    }
    let GoodPW = function () {
        let good = "너무멋진비밀번호예용";
        document.getElementById("goodPassword").classList.add("text-rainbow");
        document.getElementById("pwc").classList.add("text-rainbow");
        return good;
    }
    let BadPW = function (pass) {
        document.getElementById("goodPassword").classList.remove("text-rainbow");
        document.getElementById("pwc").classList.remove("text-rainbow");
        return pass + '는 너무 구려요';
    }

    if (document.getElementById('DoubleCheck')) {
        const $pwd2 = document.getElementById("DoubleCheck");
        $pwd2.onclick = function test() {
            var p1 = document.getElementById('memberPwd').value;
            var p2 = document.getElementById('memberPwd2').value;
            if (p1 != p2) {
                alert("비밀번호가 일치 하지 않습니다");
                return false;
            } else {
                alert("비밀번호가 일치합니다");
                document.getElementById("veryGoodPassword").classList.add("text-rainbow");
                return true;
            }
        }
    }

    if (document.getElementById("heyhey")) {
        const elementById = document.getElementById("heyhey");
        elementById.onclick = function () {
            if (!pwCheck) {
                alert("비밀번호 확인을 실행해주세요.");
            }
            if (!email) {
                alert("이메일 중복 체크를 실행해주세요.");
            }
            if (pwCheck && email) {
                const hahahaha = document.getElementById("updateForm");
                hahahaha.submit();
            }
        }
    }

    if (document.getElementById("submit2")) {
        const btn = document.getElementById("submit2");
        btn.onclick = function () {
            location.href = "/member/DeleteMember";
        }
    }
}