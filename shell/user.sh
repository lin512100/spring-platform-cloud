source /etc/profile

if [ ! -f "/data/filename" ];then
  echo "文件不存在"
else
  echo "删除源文件"
  rm -f /root/应用服务-账户服务-User.jar
fi

cp /mnt/e/spring-platform-cloud/spring-platform-user/target/应用服务-账户服务-User.jar .
ps -ef|grep "应用服务-账户服务-User" |grep -v 'grep' |awk '{print $2}' |xargs kill -9
cd ~ && nohup java -jar 应用服务-账户服务-User.jar > /dev/null &
