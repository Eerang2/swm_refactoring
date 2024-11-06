document.addEventListener('DOMContentLoaded', function () {
    let currentYear = new Date().getFullYear(); // 현재 연도를 가져옵니다.
    let currentMonth = new Date().getMonth() + 1; // 현재 월을 가져옵니다. 0부터 시작하므로 1을 더합니다.

    loadMonthlyData(currentYear, currentMonth); // 초기 페이지 로드 시 데이터 로드

    // 이전 달 버튼 클릭 시
    document.getElementById('prev-month').addEventListener('click', () => {
        if (currentMonth === 1) {
            currentMonth = 12;
            currentYear--;
        } else {
            currentMonth--;
        }
        loadMonthlyData(currentYear, currentMonth); // 이전 달 데이터 로드
    });

    // 다음 달 버튼 클릭 시
    document.getElementById('next-month').addEventListener('click', () => {
        if (currentMonth === 12) {
            currentMonth = 1;
            currentYear++;
        } else {
            currentMonth++;
        }
        loadMonthlyData(currentYear, currentMonth); // 다음 달 데이터 로드
    });

    // 월별 데이터를 로드하는 함수
    function loadMonthlyData(year, month) {
        const accommodationNo = 1; // 예시로 관리자 번호를 지정합니다.

        fetch(`/reservation-monthly-data?accommodationNo=${accommodationNo}`)
            .then(response => {
                if (!response.ok) {
                    throw new Error(`HTTP error! status: ${response.status}`); // HTTP 에러 처리
                }
                return response.json();
            })
            .then(data => {
                generateCalendar(data.accommodationRoomData, data.monthlyData, year, month); // 달력 생성
            })
            .catch(error => console.error('Error loading data:', error)); // 데이터 로드 에러 처리
    }

    // 달력을 생성하는 함수
    function generateCalendar(accommodationRoomData, roomData, year, month) {
        const firstDay = new Date(year, month - 1, 1); // 해당 월의 첫 번째 날
        const lastDay = new Date(year, month, 0); // 해당 월의 마지막 날
        const calendarBody = document.getElementById('calendar-body');
        calendarBody.innerHTML = ''; // 이전 달력 내용을 초기화

        let date = 1;
        const today = new Date();

        while (date <= lastDay.getDate()) {
            const row = document.createElement('tr'); // 새로운 행 생성
            for (let i = 0; i < 7; i++) {
                const cell = document.createElement('td'); // 새로운 셀 생성
                if (date > lastDay.getDate()) {
                    // 남은 셀은 빈칸으로 채웁니다.
                    row.appendChild(cell);
                } else if (i >= firstDay.getDay() || date > 1) {
                    cell.innerHTML = `<div>${date}</div>`;

                    // 객실 이름별로 그룹화
                    const roomsByName = {};
                    accommodationRoomData.forEach(room => {
                        if (!roomsByName[room.roomName]) {
                            roomsByName[room.roomName] = [];
                        }
                        roomsByName[room.roomName].push(room.roomNo); // 객실 이름별로 객실 번호를 그룹화
                    });

                    // 각 객실 이름에 대해 버튼을 표시
                    for (let roomName in roomsByName) {  // 각 방 이름에 대해 반복문을 실행
                        const roomInfo = document.createElement('div');
                        roomInfo.classList.add('room-info');  // 방 정보가 들어갈 div 생성 및 클래스 추가

                        const roomNameDiv = document.createElement('div');
                        roomNameDiv.classList.add('room-name');  // 방 이름을 표시할 div 생성 및 클래스 추가
                        roomNameDiv.textContent = `객실: ${roomName}`;  // 방 이름 설정
                        roomInfo.appendChild(roomNameDiv);  // 방 이름 div를 방 정보 div에 추가

                        roomsByName[roomName].forEach(roomNo => {  // 각 방 번호에 대해 반복문 실행
                            // 방 번호에 대한 버튼을 생성
                            const roomBtn = document.createElement('button');
                            roomBtn.classList.add('room-btn');  // 버튼에 기본 클래스 추가

                            // 해당 날짜에 방이 예약된 상태인지 확인
                            const reservationForDate = roomData.find(reservation =>
                                new Date(reservation.reserveCheckIn) <= new Date(year, month - 1, date) &&
                                new Date(reservation.reserveCheckOut) >= new Date(year, month - 1, date) &&
                                reservation.roomNo === roomNo
                            );

                            // 예약 상태에 따라 버튼 스타일을 적용
                            if (reservationForDate) {  // 예약이 있는 경우
                                roomBtn.textContent = roomNo;  // 버튼 텍스트에 방 번호 설정
                                roomBtn.classList.add('unavailable');  // 예약된 방에 'unavailable' 클래스 추가

                                // 예약 상태가 CONFIRMED일 경우 status-complete 클래스 적용
                                if (reservationForDate.reservationStatus === 'CONFIRMED') {
                                    roomBtn.classList.add('status-complete');  // 예약 확정 상태일 때 붉은색 스타일 적용
                                } else {
                                    // 예약 상태가 NULL 또는 CANCELLED일 경우 status-cancel 클래스 적용
                                    roomBtn.classList.add('status-cancel');  // 예약 취소 또는 예약 없음일 때 녹색 스타일 적용
                                }
                            } else {  // 예약이 없는 경우
                                roomBtn.textContent = roomNo;  // 버튼 텍스트에 방 번호 설정
                                roomBtn.classList.add('available');  // 예약되지 않은 방에 'available' 클래스 추가
                                roomBtn.classList.add('status-cancel');  // 예약 없음 상태일 때 녹색 스타일 적용
                            }

                            // 방 정보 영역에 버튼을 추가
                            roomInfo.appendChild(roomBtn);
                        });

                        cell.appendChild(roomInfo);  // 셀에 방 정보를 포함한 div를 추가
                    }


                    // 오늘 날짜 강조 표시
                    if (year === today.getFullYear() && month === today.getMonth() + 1 && date === today.getDate()) {
                        cell.classList.add('today'); // 'today' 클래스 추가하여 스타일 적용
                    }

                    date++;
                }
                row.appendChild(cell); // 행에 셀 추가
            }
            calendarBody.appendChild(row); // 달력에 행 추가
        }

        document.getElementById('current-month').textContent = `${year}년 ${month}월`; // 현재 월 표시
    }
});
