FROM java:8

MAINTAINER rmeloca@gmail.com

WORKDIR /app

ADD ./target/ /app

ENV JAVA_ENV=PRODUCTION

EXPOSE 8080

CMD ["java", "-server", "-cp", "classes:dependency/*", "com.kumuluz.ee.EeApplication"]