FROM openjdk:14-alpine
COPY target/gastosCompartidos-*.jar gastosCompartidos.jar
EXPOSE 8080
CMD ["java", "-Dcom.sun.management.jmxremote", "-Xmx128m", "-jar", "gastosCompartidos.jar"]