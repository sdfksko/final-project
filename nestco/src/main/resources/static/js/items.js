function declaration() {
    const uploaderEl = document.querySelector('#uploader-name');
    let nickname = uploaderEl.innerText
    console.log(nickname);

    axios.post('/items/check/nickname', {nickname})
    .then(function (response) {
        alert('신고가 접수되었습니다.');
    })
    .catch(function (error) {
        alert('신고가 실패하였습니다.');
    });
}

const saveLike = (itemId) => {
        axios.post(`/items/like/${itemId}`)
            .then(response => {
                if (response.data.success) {
                    alert("찜하기 성공!");
                } else {
                    alert(response.data.message);
                }
            })
            .catch(error => {
                if (error.response.status === 401) {
                    alert("로그인이 필요합니다.");
                    window.location.href = "/loginForm";
                } else {
                    alert("찜하기 처리 중 오류가 발생했습니다.");
                }
            });

};