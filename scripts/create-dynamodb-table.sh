#!/usr/bin/env bash

set -e

aws dynamodb create-table \
  --table-name urls \
  --attribute-definitions \
    AttributeName=shortCode,AttributeType=S \
  --key-schema \
    AttributeName=shortCode,KeyType=HASH \
  --provisioned-throughput ReadCapacityUnits=10,WriteCapacityUnits=10 \
  --endpoint-url http://localhost:54000 \
  --region eu-west-1 || true | cat
