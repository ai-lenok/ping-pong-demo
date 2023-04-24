#!/usr/bin/env bash

HOST="ping-pong.apps.sbc-okd.pcbltools.ru"
METHOD="api/v1/ping"
URL="http://$HOST/$METHOD"

DURATION="600s"
WORKERS=10000
RATE=500
RESULT_FILE="results.bin"

echo "GET ${URL}" | \
vegeta attack -duration=$DURATION -max-workers=$WORKERS -rate=$RATE | \
tee $RESULT_FILE | \
vegeta report -type="hist[0,100ms,200ms,300ms]" ; \
cat $RESULT_FILE | \
vegeta report