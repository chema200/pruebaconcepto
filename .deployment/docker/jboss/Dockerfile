# Etapa de construcción del WAR
FROM maven:3.8.4-jdk-11 AS build
WORKDIR /app

# Copiar el archivo pom.xml y las fuentes para resolver las dependencias y construir el WAR
COPY ./pruebaconcepto/pom.xml /app/
COPY ./pruebaconcepto/src /app/src

# Compilar el WAR
RUN mvn clean package -DskipTests

# Dependencies environment
FROM debian:bullseye AS base

ENV APP_NAME=ibavi.ear
ENV INSTALL_DIR=/home/app
ENV LOGS_PATH /var/log/app

RUN apt-get update &&        \
    apt-get install -y       \
    nano                     \
    lynx                    \
    unzip                   \
    default-jre           \
    curl                  && \
    apt-get clean

# Configure JBoss EAP
FROM base AS server
ENV JBOSS_VERSION 7.2
ENV JBOSS_HOME /opt/jboss
ENV JBOSS_CONFIG /opt/jboss/standalone/configuration/

# User management
ARG WILDFLY_ADMIN_PWD=admin
ENV ADMIN_PWD=$WILDFLY_ADMIN_PWD
ENV LAUNCH_JBOSS_IN_BACKGROUND true

USER root
RUN mkdir -p /opt/jboss \
    && useradd -d  /opt/jboss jboss \
    && mkdir -p $LOGS_PATH \
    && chown jboss:jboss $LOGS_PATH \
    && chown jboss:jboss /opt/jboss

ENV TZ=Europe/Madrid
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

USER jboss
WORKDIR /opt/jboss

# Download and configure JBoss EAP
COPY --chown=jboss ./.deployment/docker/jboss/jboss-eap-7.2.zip /tmp/

RUN unzip /tmp/jboss-eap-$JBOSS_VERSION.zip -d /tmp/ \
    && mv /tmp/jboss-eap-$JBOSS_VERSION/* $JBOSS_HOME/ \
    && chmod +x $JBOSS_HOME/bin/*.sh \
    && chown -R jboss:jboss $JBOSS_HOME \
    && $JBOSS_HOME/bin/add-user.sh admin $ADMIN_PWD --silent

FROM server as development

# Copiar el WAR generado desde la etapa de construcción (Maven)
COPY --from=build /app/target/Hotel-1.0-SNAPSHOT.war $JBOSS_HOME/standalone/deployments/

# Copiar el driver de PostgreSQL
COPY --chown=jboss ./.deployment/docker/drivers/postgresql-42.7.4-2024.jar $JBOSS_HOME/modules/org/postgresql/main/

# Crear el módulo para el driver de PostgreSQL
RUN echo '<?xml version="1.0" encoding="UTF-8"?>' > $JBOSS_HOME/modules/org/postgresql/main/module.xml && \
    echo '<module xmlns="urn:jboss:module:1.1" name="org.postgresql">' >> $JBOSS_HOME/modules/org/postgresql/main/module.xml && \
    echo '    <resources>' >> $JBOSS_HOME/modules/org/postgresql/main/module.xml && \
    echo '        <resource-root path="postgresql-42.7.4-2024.jar"/>' >> $JBOSS_HOME/modules/org/postgresql/main/module.xml && \
    echo '    </resources>' >> $JBOSS_HOME/modules/org/postgresql/main/module.xml && \
    echo '    <dependencies>' >> $JBOSS_HOME/modules/org/postgresql/main/module.xml && \
    echo '        <module name="javax.api"/>' >> $JBOSS_HOME/modules/org/postgresql/main/module.xml && \
    echo '        <module name="javax.transaction.api"/>' >> $JBOSS_HOME/modules/org/postgresql/main/module.xml && \
    echo '    </dependencies>' >> $JBOSS_HOME/modules/org/postgresql/main/module.xml && \
    echo '</module>' >> $JBOSS_HOME/modules/org/postgresql/main/module.xml

COPY --chown=jboss ./.deployment/docker/jboss/properties/*.properties $JBOSS_CONFIG/

EXPOSE 8084 9994 8789
CMD ["/opt/jboss/bin/standalone.sh","-c", "standalone.xml", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0", "--debug", "*:8789"]
