
    const onError = xhr => console.log(xhr);
    const onSuccess = data => {
        console.log(data);
        if (confirm("주문이 완료되었습니다. 주문 내역으로 이동을 원하시면 확인을 눌러주세요.")) {
            //location.href="/order/myOrdList";
        } else {
            location.href="/main";
        }

    }

    /*      개인정보 동의 보기창     */
    function modal(id) {
        var zIndex = 9999;
        var modal = document.getElementById(id);

        // 모달 div 뒤에 레이어
        var bg = document.createElement('div');
        bg.setStyle({
            position: 'fixed',
            zIndex: zIndex,
            left: '0px',
            top: '0px',
            width: '100%',
            height: '100%',
            overflow: 'auto',
            // 레이어 색깔 변경가능
            backgroundColor: 'rgba(0,0,0,0.4)'
        });
        document.body.append(bg);

        // 닫기 버튼 처리, 시꺼먼 레이어와 모달 div 지우기
        modal.querySelector('.modal_close_btn').addEventListener('click', function () {
            bg.remove();
            modal.style.display = 'none';
        });


        modal.setStyle({
            position: 'fixed',
            display: 'block',
            boxShadow: '0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19)',

            // 시꺼먼 레이어 보다 한칸 위에 보이기
            zIndex: zIndex + 1,

            // div center 정렬
            top: '50%',
            left: '50%',
            transform: 'translate(-50%, -50%)',
            msTransform: 'translate(-50%, -50%)',
            webkitTransform: 'translate(-50%, -50%)'
        });
    }

// Element 에 style 한번에 오브젝트로 설정하는 함수 추가
    Element.prototype.setStyle = function (styles) {
        for (var k in styles) this.style[k] = styles[k];
        return this;
    };

    function modalcomeon() {
        // 모달창 띄우기
        modal('my_modal');
    };

    var today = new Date();
    var hours = today.getHours();
    var minutes = today.getMinutes();
    var seconds = today.getSeconds();
    var milliseconds = today.getMilliseconds();
    var makeMerchantUid = hours + minutes + seconds + milliseconds;

    function pay() {
        // 결제수단 확인하여 기능연결
        const $paymethod = document.getElementsByName('paymethods');
        const $policy = document.getElementsByName('agree_privacy_policy');

        var IMP = window.IMP; // 생략가능
        IMP.init("imp66505071");

        for (let i = 0; i < $policy.length; i++) {
            if (!$policy[i].checked) {
                alert('개인정보 동의를 확인해주세요.');
                return;
            }
        }

        let selectedOption;
        for (const paymethod of $paymethod) {
            if (paymethod.checked) {
                selectedOption = paymethod.value;
                break;
            }
        }

        switch (selectedOption) {
            case 'paymethod1':
                kakaoPay();
                break;
            case 'paymethod2':
                card();
                break;
            case false:
                alert('결제수단을 선택해주세요.');
                break;
            default:
                alert('결제수단을 선택해주세요.');
                break;
        }
    }


    function kakaoPay() {
        var elementById = document.getElementById("productCode").value;
        console.log(elementById)
        // 카카오페이 결제실행
        console.log("카카오페이 실행");
        IMP.request_pay({

            /* 테스트 발급 받은 종류 기입 */
            pg: 'kakaopay',
            pay_method: 'card',
            merchant_uid: 'merchant_' + makeMerchantUid, // 주문번호
            name: prdName[0].innerHTML, // 결제창에서 보여질 이름
            amount: totalPay, // 실제 결제되는 가격
            buyer_email: $email,
            buyer_name: $name,
            buyer_tel: $ophone,
            buyer_addr: $address,
            buyer_postcode: $address1

        }, function (rsp) {
            console.log(rsp); // 결제 검증

            if (rsp.success) {
                var msg;

                let result = {
                    "imp_uid": rsp.imp_uid,
                    "merchant_uid": rsp.merchant_uid,
                    "biz_email": rsp.buyer_email,
                    "pay_date": rsp.paid_at,
                    "amount": rsp.paid_amount,
                    "card_type": rsp.pg_provider,
                    "status": 'payed',
                    "proCode": elementById
                }

                $.ajax({
                    url: '/verifyIamport/' + rsp.imp_uid, //+rsp.imp_uid
                    type: 'POST',
                    data: JSON.stringify(result,
                        ['imp_uid', 'merchant_uid', 'biz_email', 'pay_date', 'amount', 'card_type', 'status', 'proCode']
                    ),
                    contentType: 'application/json; charset=UTF-8',
                    dataType: 'json',
                    error: onError,
                    success: onSuccess
                }) //ajax
            } else {
                msg = '결제에 실패하였습니다.\n';
                msg += '에러내용 : ' + rsp.error_msg;
                // 실패시 이둉할 페이지
                location.href = 'http://localhost:8001/order/form';
                alert(msg);
            }
            $.ajax({
                url: '/order/completion', //+rsp.imp_uid
                type: 'GET',
                data: JSON.stringify(result,
                    ['imp_uid', 'merchant_uid', 'biz_email', 'pay_date', 'amount', 'card_type', 'status']
                ),
                contentType: 'application/json; charset=UTF-8',
                dataType: 'json',
                error: onError,
                success: onSuccess
            }) //ajax
        });
    }


    function card() {
        // kicc 결제 진행
        console.log("kicc 실행");
        IMP.request_pay({

            // 테스트 발급 받은 종류 기입
            pg: 'html5_inicis',
            pay_method: 'card',
            merchant_uid: 'merchant_' + makeMerchantUid, // 주문번호
            name: prdName[0].innerHTML + ' 외 ' + (prdName.length - 1) + '개의 상품', // 결제창에서 보여질 이름
            amount: 1000, // 실제 결제되는 가격
            buyer_email: $email,
            buyer_name: $name,
            buyer_tel: $ophone,
            buyer_addr: $address,
            buyer_postcode: $address1

        }, function (rsp) {
            console.log(rsp); // 결제 검증

            if (rsp.success) {
                var msg;

                let result = {
                    "imp_uid": rsp.imp_uid,
                    "merchant_uid": rsp.merchant_uid,
                    "biz_email": rsp.buyer_email,
                    "pay_date": rsp.paid_at,
                    "amount": rsp.paid_amount,
                    "card_type": rsp.pg_provider,
                    "status": 'payed'
                }

                $.ajax({
                    url: '/verifyIamport/' + rsp.imp_uid, //+rsp.imp_uid
                    type: 'POST',
                    data: JSON.stringify(result,
                        ['imp_uid', 'merchant_uid', 'biz_email', 'pay_date', 'amount', 'card_type', 'status']
                    ),
                    contentType: 'application/json; charset=UTF-8',
                    dataType: 'json',
                    error: onError,
                    success: onSuccess
                }) //ajax
            } else {
                msg = '결제에 실패하였습니다.\n';
                msg += '에러내용 : ' + rsp.error_msg;
                // 실패시 이둉할 페이지
                location.href = 'http://localhost:8001/order/form';
                alert(msg);
            }
        });


}