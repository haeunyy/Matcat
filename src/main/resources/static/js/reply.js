
		if(document.getElementById("registBtn")){
			
			const $replyBody = document.getElementById("replyBody");
			const $regtisBtn = document.getElementById("registBtn");
			
			$regtisBtn.onclick = function(){
				
				if(!$replyBody.value.trim()) {
					alert("댓글을 입력해주세요");
					return;
				}
				
				const postCode = '[[${board.postCode}]]';
				const replyContent = $replyBody.value;
				
				fetch("/board/registReply",{
					method : 'POST',
					headers : {
						'Content-Type' : 'application/json;charset=UTF-8'
					},
					body : JSON.stringify({
						postCode,
						replyContent
					})
				})
				.then(data => {
					console.log(data);
					$replyBody.value = '';    // 댓글 입력창 비우는 용도~~
					loadReplyList();                 // 댓글 다시 업로드 하기~~
					
				})
				.catch(error => console.log(error));
			};
		}
		
		/* 2. 최신 댓글 조회 */
		function loadReplyList() {
			
			const postCode = '[[${board.postCode}]]';
			
			
			fetch("/board/loadReplyList?postCode=" + postCode)
			.then(result => result.json())
			.then(data => {
				console.log(data);
				makeReplyList(data);
				
			})
			.catch(error => console.log(error));
			
			console.log(postCode);
		}
		
		/* 댓글 데이터를 테이블화하는 함수 */
		function makeReplyList(replyList) {
			
			const $table = document.querySelector("#replyUpdate");
			$table.innerHTML = '';
			
			replyList.forEach(reply => {
				
				
 				
				const $div = document.createElement('div');
				const $replyNoDiv = document.createElement('div');
				const $replyContentDiv = document.createElement('div');
				const $memberNameDiv = document.createElement('div');
				const $reportDateDiv = document.createElement('div');
				const $buttonDiv = document.createElement('div');
				
				$replyNoDiv.textContent = reply.replyNo;
				$replyContentDiv.textContent = reply.replyContent;
				$memberNameDiv.textContent = reply.writer.memberName;
				$reportDateDiv.textContent = reply.reportDate;
				
				if('${ reply.writer.memberNo == #authentication.principal.memberNo }'==reply.writer.memberNo) {
					$buttonDiv.innerHTML = `<button onclick='removeReply(${reply.replyNo})'>댓글삭제</button>`;
				}
				
				$div.append($replyNoDiv, $replyContentDiv, $memberNameDiv, $reportDateDiv, $buttonDiv);
				$table.append($div);
				
			});
		}
		
		function removeReply(replyNo) {
			
			fetch("/board/removeReply", {
				method : 'POST',
				headers : {
					'Content-Type' : 'application/json; charset=UTF-8'
				},
				body : JSON.stringify({ replyNo : replyNo})
			})
			.then(result => {
				loadReplyList();
				
			})
			.catch(error => console.log(error));
		}
