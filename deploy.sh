#!/bin/sh
echo "Run pull"
git pull
echo "Run package"
mvn package -Dmaven.test.skip=true
echo "Restart service"
systemctl restart hackdocs-backend.service
echo "Finish"
