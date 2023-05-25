FROM openjdk:8

RUN mkdir -p /opt/exam/

COPY exam-admin/target/exam-admin-0.0.1-SNAPSHOT.jar /opt/exam

COPY exam-admin/run.sh /opt/exam/

COPY exam-admin/src/main/resources/static /exam

EXPOSE 8888

WORKDIR /opt/exam

ENTRYPOINT ["sh", "/opt/exam/run.sh"]
