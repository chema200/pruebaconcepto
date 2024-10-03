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
COPY --chown=jboss jboss/jboss-eap-7.2.zip /tmp/

RUN unzip /tmp/jboss-eap-$JBOSS_VERSION.zip -d /tmp/ \
    && mv /tmp/jboss-eap-$JBOSS_VERSION/* $JBOSS_HOME/ \
    && chmod +x $JBOSS_HOME/bin/*.sh \
    && chown -R jboss:jboss $JBOSS_HOME \
    && $JBOSS_HOME/bin/add-user.sh admin $ADMIN_PWD --silent

FROM server as development

# Orden para copiar auto el archivo WAR al directorio de despliegue del JBoss, pongo war de ejemplo hasta tener la app correcta.
COPY --chown=jboss jboss/holamundo.war $JBOSS_HOME/standalone/deployments/

COPY --chown=jboss jboss/properties/*.properties $JBOSS_CONFIG/

EXPOSE 8084 9994 8789
CMD ["/opt/jboss/bin/standalone.sh","-c", "standalone.xml", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0", "--debug", "*:8789"]