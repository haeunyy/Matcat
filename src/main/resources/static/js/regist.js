window.onload = function () {
    var id2 = false;
    var idcheck = false;
    var pwd = false;
    var pwcheck = false;
    var zip = false;
    var zip2 = false;
    if (document.getElementById("AllCheck")) {
        const $check = document.getElementById("AllCheck");
        $check.addEventListener("change", function (e) {
            e.preventDefault();
            const list = document.querySelectorAll("input[class=check]");
            for (var i = 0; i < list.length; i++) {
                list[i].checked = this.checked;
            }
        })
    }

    if (document.getElementById("next")) {
        const $next = document.getElementById("next");
        const $check = document.getElementById("check1");
        const $check2 = document.getElementById("check2");
        $next.onclick = function () {
            if (!($check.checked && $check2.checked)) {
                alert("필수 옵션을 체크해주세요.");
            } else {
                location.href = '/member/regist2';
            }
        }
    }

    if (document.getElementById("searchZipCode")) {
        const $search = document.getElementById("searchZipCode");
        $search.onclick = function () {
            zip = true;
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

        $duplication.onclick = function () {
            idcheck = false;
            let memberId = document.getElementById("memberId").value.trim();
            fetch("/member/idDupCheck", {
                method: "POST",
                headers: {'Content-Type': 'application/json;charset=UTF-8'},
                body: JSON.stringify({memberId})
            })
                .then(result => result.text())
                .then(result => {
                    alert(result)
                    if (result === "사용 가능한 아이디입니다.") {
                        idcheck = true
                    }
                })
                .catch((error) => error.text().then((res) => alert(res)));
        }
    }

    if (document.getElementById("memberId")) {
        const $id = document.getElementById("memberId");
        const idReg = /^[a-z]+[a-z0-9]{5,14}$/;
        $id.oninput = function () {
            let id = document.getElementById("memberId").value.trim();
            if (idReg.test(id)) {
                id2 = true;
            }
        }
    }

    if (document.getElementById("memberPwd")) {
        document.getElementById("memberPwd").oninput = function () {
            let msg = '';
            pwcheck = false;
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
                pwd = true;
            } else {
                msg = BadPW(pass);
                pwd = false;
            }
            document.getElementById("pwc").textContent = msg;
        };
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
            let p1 = document.getElementById('memberPwd').value;
            let p2 = document.getElementById('memberPwd2').value;
            if (p1 != p2) {
                alert("비밀번호가 일치 하지 않습니다");
                pwcheck = false;
                return false;
            } else {
                alert("비밀번호가 일치합니다");
                document.getElementById("veryGoodPassword").classList.add("text-rainbow");
                pwcheck = true;
                return true;
            }
        }
    }

    if (document.getElementById("address2")) {
        const $address = document.getElementById("address2");
        $address.oninput = function () {
            let add2 = document.getElementById("address2").value
            if (add2.length > 1) {
                zip2 = true;
            }
        }
    }

    if (document.getElementById("joinbtn")) {
        const elementById = document.getElementById("joinbtn");
        elementById.onclick = function () {
            if (!(idcheck)) {
                alert("아이디 중복 체크를 해주세요.");
            }
            if (!(pwcheck)) {
                alert("비밀번호 확인 체크를 해주세요.");
            }
            if (!(zip)) {
                alert("주소를 입력해주세요.");
            }
            if (!(id2)) {
                alert("아이디의 양식이 올바르지 않습니다.");
            }
            if (!(zip2)) {
                alert("상세 주소를 입력해주세요.")
            }
            if (idcheck && pwcheck && zip2 && id2 && zip) {
                const hoho = document.getElementById("joinform");
                hoho.submit();
            }
        };
    }

    if (document.getElementById("joinbtn2")) {
        const message = '[[${message}]]';
        if (message) {
            id2 = true;
            const elementById = document.getElementById("joinbtn2");
            elementById.onclick = function () {
                if (!(pwcheck)) {
                    alert("비밀번호 확인 체크를 해주세요.");
                }
                if (!(zip)) {
                    alert("주소를 입력해주세요.");
                }
                if (!(id2)) {
                    alert("아이디의 양식이 올바르지 않습니다.");
                }
                if (!(zip2)) {
                    alert("상세 주소를 입력해주세요.")
                }
                if (pwcheck && zip2 && id2 && zip) {
                    const hoho = document.getElementById("joinform");
                    hoho.submit();
                }
            };
        }
    }


}


