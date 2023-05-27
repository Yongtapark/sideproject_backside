#!/usr/bin/env bash

#쉬고 있는 profile 찾기 : real1이 사용중이면 real2가 쉬고 있고, 반대면 real1이 쉬고 있음

function find_idle_profile() {
  #현재 엔진엑스가 바라보고 있는 스프링부트가 정상적으로 수행중인지 확인
  #응답값을 HttpStatus로 받는다.
  #정상이면 200,오류가 발생한다면 400~503 사이로 발생하니, 400 이상은 모두 예외로 보고 real2를 현재 profile로 사용한다.
    RESPONSE_CODE=$(curl -s -o /dev/null -w "%{http_code}"
                                http://localhost/profile)

    if [ ${RESPONSE_CODE} -ge 400 ] #400보다 크면(즉, 40x/50x 에러 모두 포함)
    then
      CURRENT_PROFILE=real2 #응답 코드가 400 이상이라면(즉, 서버에 문제가 있다면) CURRENT_PROFILE을 real12로 설정
    else
      CURRENT_PROFILE=$(curl -s http://localhost/profile) #그렇지않다면, 서버의 profile 정보를 가져와 CURRENT_PROFILE에 설정
    fi

    if [ ${CURRENT_PROFILE} == real1 ] # 만약 CURRENT_PROFILE이 real1이라면
    then
      #엔진엑스와 연결되지 않은 profile
      #스프링부트 프로젝트를 이 profile이 연결하기 위해 반환
      IDLE_PROFILE=real2
    else
      IDLE_PROFILE=real1
    fi
    #bash라는 스크립트는 값을 반환하는 기능이 없다.
    #그래서 제일 마지막 줄에 echo로 결과를 출력 후, 클라이언트에서 그 값을 잡아서 사용합니다.
    #중간에 echo를 사용해서는 안된다.
    echo "${IDLE_PROFILE}"
}

#쉬고있는 profile의 port 찾기
function find_idle_port() {
    IDLE_PROFILE=$(find_idle_profile) #find_idle_profile 함수의 결과를 IDLE_PROFILE 변수에 할당

    if [ ${IDLE_PROFILE} == real1 ]
    then
      echo "8081"
    else
      echo "8082"
    fi
}