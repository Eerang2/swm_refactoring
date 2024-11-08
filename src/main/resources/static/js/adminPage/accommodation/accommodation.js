$(document).ready(
    function() {
        // 폼 제출 이벤트 처리
        $('#accommodationForm').on('submit', function(event) {
            event.preventDefault(); // 폼 기본 제출 막기

            const facilities = [];
            document.querySelectorAll('.tag input[type="checkbox"]:checked').forEach(function(checkbox) {
                facilities.push(checkbox.getAttribute('data-tag')); // 체크된 태그의 이름을 배열에 추가
            });

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
                    const newId = res.id;
                    $.ajax({
                        type: 'POST',
                        url: '/api/facility/add',
                        data: JSON.stringify({
                            accommodationId: newId,
                            facilities: facilities
                        }),
                        contentType: 'application/json',
                        success: function() {
                            alert('숙소가 성공적으로 등록되었습니다!');
                            window.location.href = '/accommodation/' + newId;
                        },
                        error: function(error) {
                            alert('부대시설 등록에 실패했습니다.');
                            console.error("Error:", error);
                        }
                    });
                },
                error: function(error) {
                    alert('게시글 등록에 실패했습니다.');
                    console.error("Error:", error);
                }
            });
        });
});



/**
 *  숙소 형태 태그 단일선택
 */
document.addEventListener('DOMContentLoaded', function() {
    var checkboxes = document.querySelectorAll('.accommodationType');

    checkboxes.forEach(function(checkbox) {
        checkbox.addEventListener('change', function() {
            if (this.checked) {
                checkboxes.forEach(function(box) {
                    if (box !== checkbox) {
                        box.checked = false;
                    }
                });
            }
        });
    });
});
document.querySelectorAll('.tag input[type="radio"]').forEach(function (radio) {
    radio.addEventListener('change', function () {
        var typeInput = document.getElementById('type');
        typeInput.value = this.getAttribute('data-tag');
    });
});


/**
 * 부대시설 태그 추가
 */
document.querySelectorAll('.tag input[type="checkbox"]').forEach(function (checkbox) {
    checkbox.addEventListener('change', function () {

        var subFacilityInput = document.getElementById('sub-facility');
        var tagValue = this.getAttribute('data-tag');

        if (this.checked) {
            if (subFacilityInput.value) {
                subFacilityInput.value += ', ' + tagValue;
            } else {
                subFacilityInput.value = tagValue;
            }
        } else {
            var values = subFacilityInput.value.split(', ');
            values = values.filter(function (value) {
                return value !== tagValue;
            });
            subFacilityInput.value = values.join(', ');
        }
    });
});