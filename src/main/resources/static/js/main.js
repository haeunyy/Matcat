$(function(){
$("#minus").click(function(e){
e.preventDefault();
var stat = $("#result").text();
var num = parseInt(stat,10);
num--;
if(num<=0){
alert('더이상 줄일수 없습니다.');
num =1;
}
$("#result").text(num);
});
$("#add").click(function(e){
e.preventDefault();
var stat = $("#result").text();
var num = parseInt(stat,10);
num++;

if(num>5){
alert('더이상 늘릴수 없습니다.');
num=5;
}
$("#result").text(num);
});
});
// let result = {
// 	"productCode" : $productCode
// }
// console.log(result);
// function orderForm(code){
// 	console.log(code);
//     $.ajax({
//         url: '/order/form',
//         type: 'POST',
//         data: JSON.stringify(result,
//             ['productCode', 'merchant_uid', 'biz_email', 'pay_date', 'amount', 'card_type', 'refund']
//         ),
//         contentType: 'application/json; charset=UTF-8',
//         dataType: 'json',
//         error: onError,
//         success: onSuccess
//     }) //ajax
// }



