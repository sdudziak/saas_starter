FROM adoptopenjdk/openjdk11:x86_64-alpine-jre-11.0.3_7

VOLUME /tmp

ARG DEPENDENCY=build/dependency

ADD ${DEPENDENCY}/BOOT-INF/lib /app/lib
ADD ${DEPENDENCY}/META-INF /app/META-INF
ADD ${DEPENDENCY}/BOOT-INF/classes /app

EXPOSE 8080
EXPOSE 35729

CMD ["java","-cp","app:app/lib/*","io.saas.starter.ApplicationKt"]
