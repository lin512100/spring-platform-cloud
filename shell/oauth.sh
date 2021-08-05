source /etc/profile

if [ ! -f "/data/filename" ];then
  echo "文件不存在"
else
  echo "删除源文件"
  rm -f /root/应用服务-授权服务-Oauth.jar
fi

cp /mnt/e/spring-platform-cloud/spring-platform-oauth/target/应用服务-授权服务-Oauth.jar .
ps -ef|grep "应用服务-授权服务-Oauth" |grep -v 'grep' |awk '{print $2}' |xargs kill -9
cd ~ && nohup java -jar 应用服务-授权服务-Oauth.jar > /dev/null &
