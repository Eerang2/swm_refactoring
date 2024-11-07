$(document).ready(
    function() {
        // 폼 제출 이벤트 처리
        $('#accommodationForm').on('submit', function(event) {
            event.preventDefault(); // 폼 기본 제출 막기

            // 폼 데이터 가져오기
            const postData = {
                name: $('#name').val(),
                phone: $('#phone').val(),
                accommodationType: $('input.accommodationType:checked').val(),
                // imagePath: $('#photo')[0].files[0],  // 파일 선택 시에는 [0]으로 접근
                address1: $('#sample5_region').val(),
                address2: $('#sample5_roadName').val(),
                latitude: $('#sample5_lat').val(),
                longitude: $('#sample5_lon').val(),
                description: $('#editor').html()
            };

            // AJAX 요청 보내기 (JSON 형식)
            $.ajax({
                type: 'POST',
                url: '/api/accommodation/create',
                data: JSON.stringify(postData),
                contentType: 'application/json',
                success: function(res) {
                    alert('게시글이 성공적으로 등록되었습니다!');
                    const newId = res.id;
                    window.location.href = '/accommodation/'+newId;
                },
                error: function(error) {
                    alert('게시글 등록에 실패했습니다.');
                    console.error("Error:", error);
                }
            });
        });
});