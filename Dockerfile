FROM openjdk:11
ADD build/libs/portfolio-web.war portfolio-web.war
EXPOSE 80
ENTRYPOINT ["java", "-jar", "portfolio-web.war"]
