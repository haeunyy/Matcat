window.onload = function () {

    if (document.getElementById("doubleCheck")) {

        const $button = document.getElementById("doubleCheck");

        $button.onclick = async function () {
            let memberId = document.getElementById("memberId").value.trim();
            let memberEmail = document.getElementById("memberEmail").value.trim();
            let memberName = document.getElementById("memberName").value.trim();
            let memberPhone = document.getElementById("memberPhone").value.trim();
            await fetch("/member/mailDupCheck", {
                method: "POST",
                headers: {'Content-Type': 'application/json;charset=UTF-8'},
                body: JSON.stringify({memberEmail, memberId, memberPhone, memberName})
            })
                .then(result => result.text())
                .then(result => {
                    if (result === "입력하신 정보가 일치합니다. 임시 비밀번호를 전송하고, 로그인 화면으로 돌아갑니다.") {
                        if (confirm(result)) {
                            const elementById = document.getElementById("findPWD");
                            elementById.submit();
                        }
                    } else {
                        alert(result)
                    }
                })
                .catch((error) => error.text().then((res) => alert(res)));
        }
    }
}
