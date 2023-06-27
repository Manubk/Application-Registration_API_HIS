FROM khipu/openjdk17-alpine
MAINTAINER <maltesh>
COPY ./target/*.jar AR_Api.jar
ENTRYPOINT [ "java","-jar","AR_Api.jar" ]