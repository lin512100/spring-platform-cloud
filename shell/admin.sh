source /etc/profile

if [ ! -f "/data/filename" ];then
  echo "文件不存在"
else
  echo "删除源文件"
  rm -f /root/应用服务-管理服务-Admin.jar
fi

cp /mnt/e/spring-platform-cloud/spring-platform-admin/target/应用服务-管理服务-Admin.jar .
ps -ef|grep "应用服务-管理服务-Admin" |grep -v 'grep' |awk '{print $2}' |xargs kill -9
cd ~ && nohup java -jar 应用服务-管理服务-Admin.jar > /dev/null &
