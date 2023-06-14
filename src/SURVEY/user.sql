INSERT INTO user(USER, USER_ID)
VALUES ('홍길동', 'USER_01'), ('박영희', 'USER_02'), ('김철수', 'USER_03'), ('이영미', 'USER_04');

INSERT INTO question(QUESTION, QUESTION_ID)
VALUES ('1. 교수는 수업 전 강의 목표를 명확히 제시하였습니까?', 'QUESTION_01'),
('2. 강의의 내용은 체계적이고 성의있게 구성되었는가?', 'QUESTION_02'),
('3. 교수는 강의 내용에 대해 전문적 지식이 있었는가?', 'QUESTION_03'),
('4. 강의 진행 속도는 적절하였는가?', 'QUESTION_04')

INSERT INTO answer (Answer, Answer_ID)
Values ('(1)전혀 아니다.', 'ANSWER_01'), ('(2)아니다.', 'ANSWER_02'), ('(3)그렇다.', 'ANSWER_03'), ('(4)매우그렇다.', 'ANSWER_04');