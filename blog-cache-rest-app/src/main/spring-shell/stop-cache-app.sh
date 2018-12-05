#!/bin/bash

export FDIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
export PSACS_ROOT=${FDIR}/../

#specific settings
export APP_NAME=ps-acs-cache-rest-app

export APP_FILENAME=${APP_NAME}.jar

${PSACS_ROOT}/stop-spring-boot.sh