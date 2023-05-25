FROM node:12

ARG REGISTRY=https://registry.npm.taobao.org

WORKDIR /opt/exam-frontend

COPY . /opt/exam-frontend

EXPOSE 8080

RUN ls && npm i --registry ${REGISTRY}

CMD ["npm", "run", "prod"]
