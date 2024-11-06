$(document).ready(function() {
    // 폼 제출 이벤트 처리
    $('#accommodationForm').on('submit', function(event) {
        event.preventDefault(); // 폼 기본 제출 막기

        // 폼 데이터 가져오기
        const postData = {
            name: document.getElementById('name').value,
            phone: document.getElementById('phone').value,
            accommodationType: document.querySelector('input.accommodationType:checked').value,
            // imagePath: document.getElementById('photo').files[0],
            address1: document.getElementById('sample5_region').value,
            address2: document.getElementById('sample5_roadName').value,
            latitude: document.getElementById('sample5_lat').value,
            longitude: document.getElementById('sample5_lon').value,
            description: document.getElementById('editor').innerHTML
        };

        // AJAX 요청 보내기 (JSON 형식)
        $.ajax({
            type: 'POST',
            url: '/api/accommodation/create',
            data: JSON.stringify(postData),
            contentType: 'application/json',
            success: function() {
                alert('게시글이 성공적으로 등록되었습니다!');
                window.location.href = '/index';
            },
            error: function(error) {
                alert('게시글 등록에 실패했습니다.');
                console.error("Error:", error);
            }
        });
    });
});