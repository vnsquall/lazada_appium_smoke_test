#!/bin/sh
echo "====COPY THE APK TO WORKSPACE===="
cp /home/lazhcm10136/Lazada_2.7.5_56_Amazon_R3.apk /var/lib/jenkins/jobs/appium-smoke-test/workspace

echo "====STARTING GENYMOTION===="
cd /home/lazhcm10136/genymotion/
./player --vm-name "Google Nexus 4 - 4.4.4 - API 19 - 768x1280" &

echo "====STARTING APPIUM===="
cd /home/lazhcm10136/Download/appium
node . &

echo "====START THE SMOKE TEST===="
cd $WORKSPACE
mvn test