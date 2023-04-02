window.onload = function () {
    var pwcheck = false;
    if (document.getElementById("password")) {
        const pass = document.getElementById("password");
        pass.oninput = async function () {
            let pass2 = this.value;
            let msg = '' ;
            if (pass2.length > 3) {
                const memberId = document.getElementById("memberId").value;
                const memberPwd = document.getElementById("password").value;
                const stringPromise = await fetch("/member/pwDupCheck", {
                    method: "POST",
                    headers: {'Content-Type': 'application/json;charset=UTF-8'},
                    body: JSON.stringify({memberPwd , memberId})
                })
                msg = await stringPromise.text();
                if(msg === "비밀번호가 일치합니다 !"){
                    pwcheck = true;
                } else {
                    pwcheck = false;
                }
            } else {
                msg = "비밀번호가 일치하지 않습니다."
                pwcheck = false;
            }
            document.getElementById("pwc").textContent = msg;
        }
    }

    if(document.getElementById("before")){
        const formtag = document.getElementById("before");
        const btn = document.getElementById("loginSubmit");
        btn.onclick = () => {
            if (pwcheck) {
                formtag.submit();
            } else {
                alert("비밀번호가 일치하지 않는다니까요;;");
            }
        }
    }

}
