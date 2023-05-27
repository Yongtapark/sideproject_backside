#!/usr/bin/env bash

ABSPATH=$(readlink -f $0)
ABSDIR=$(dirname $ABSPATH)
source ${ABSDIR}/profile.sh

REPOSITORY=/home/ec2-user/app/step3

PROJECT_NAME=sideproject_backside

echo "> Build 파일 복사"
echo "> cp $REPOSITORY/zip/*.jar $REPOSITORY/"

cp $REPOSITORY/zip/*.jar $REPOSITORY/

echo "> 새 애플리케이션 배포"
# ls -tr 명령어는 수정된 시간(오래된 순)으로 파일을 나열하고, tail -n 1 명령어는 그 중 가장 최신 파일을 선택한다.
# 이 파일명을 JAR_NAME 변수에 할당
JAR_NAME=$(ls -tr $REPOSITORY/*.jar | tail -n 1)

echo "> JAR NAME : $JAR_NAME"

echo "$JAR_NAME 에 실행권한 추가"

chmod +x $JAR_NAME

echo "> $JAR_NAME 실행"

IDLE_PROFILE=$(find_idle_profile)

echo ">$JAR_NAME를 profile=$IDLE_PROFILE 로 실행합니다. "
#nohup 명령어를 통해 백그라운드에서 JAR 파일을 실행한다. 이 때, java -jar 명령어와 함께 스프링 부트의 프로필과 함께 설정 파일 위치를 지정한다.
#실행 결과는 nohup.out 파일에 기록된다.
nohup java -jar \
-Dspring.config.location=classpath:/application.properties,classpath:/application-$IDLE_PROFILE.properties,/home/ec2-user/app/application-oauth.properties,/home/ec2-user/app/application-real-db.properties \
-Dspring.profiles.active=$IDLE_PROFILE \ $JAR_NAME > $REPOSITORY/nohup.out 2>&1 &
