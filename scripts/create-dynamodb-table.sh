#!/usr/bin/env bash

set -e

aws dynamodb create-table \
  --table-name urls \
  --attribute-definitions \
    AttributeName=shortUrl,AttributeType=S \
  --key-schema \
    AttributeName=shortUrl,KeyType=HASH \
  --provisioned-throughput ReadCapacityUnits=10,WriteCapacityUnits=10 \
  --endpoint-url http://localhost:54000 \
  --region ap-northeast-2 || true | cat
