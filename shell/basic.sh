source /etc/profile

if [ ! -f "/data/filename" ];then
  echo "文件不存在"
else
  echo "删除源文件"
  rm -f /root/应用服务-底层服务-Basic.jar
fi

cp /mnt/e/spring-platform-cloud/spring-platform-basic/target/应用服务-底层服务-Basic.jar .
ps -ef|grep "应用服务-底层服务-Basic" |grep -v 'grep' |awk '{print $2}' |xargs kill -9
cd ~ && nohup java -jar 应用服务-底层服务-Basic.jar > /dev/null &
