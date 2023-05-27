#!/usr/bin/env bash
#env 명령어를 통해 bash를 찾아 사용하게 지시하고 있습니다.

#readlink -f : 심볼릭 링크를 포함한 입력된 경로($0, 즉 스크립트 자체의 경로)의 절대경로를 찾는다.
# 그 값을 ABSPATH 변수에 할당한다.
ABSPATH=$(readlink -f $0)
#dirname : 경로 중에서 디렉토리 경로만을 반환한다. ABSPATH의 디렉토리 경로를 ABSDIR 경로에 할당한다.
ABSDIR=$(dirname $ABSPATH)
#source 명령어는 다른 쉘 스크립트를 현재 쉘 에서 실행한다.
source  ${ABSDIR}/profile.sh
source  ${ABSDIR}/switch.sh

IDLE_PORT=$(find_idle_port) #real1이면 8082, 아니면 8083

echo "> Health Check Start!"
echo "> IDLE_PORT: $IDLE_PORT"
echo "> curl -s http://localhost:$IDLE_PORT/profile "
sleep 10

for RETRY_COUNT in {1..10}
do
  #curl 명령어를 통해 해당 주소에서 프로필 정보를 가져온다.
  RESPONSE=$(curl -s http://localhost:${IDLE_PORT}/profile)
  #프로필 정보에서 'real'이라는 단어가 포함된 라인의 수를 센 결과를 UP_COUNT에 할당
echo "> response= $RESPONSE"
  UP_COUNT=$(echo ${RESPONSE} | grep 'real' | wc -l)

  if [ ${UP_COUNT} -ge 1 ] #만약 UP_COUNT 값이 1보다 크거나 같다면
  then # $up_count >= 1 ("real" 문자열이 있는지 검증)
    echo "> Health check 성공"
    switch_proxy
    break
  else
    echo "> HEALTH check의 응답을 알 수 없거나 혹은 실행 상태가 아닙니다."
    echo "> HEALTH check : ${RESPONSE}"
  fi

  if [ ${RETRY_COUNT} -eq 10 ]
  then
    echo "> Health check 실패. "
    echo "> 엔진엑스에 연결하지 않고 배포를 종료합니다."
    exit 1
  fi

  echo "> Health check 연결 실패. 재시도..."
  sleep 10
done
