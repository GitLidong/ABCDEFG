常用命令
####Git####
https://www.yiibai.com/git/git_push.html

git push <远程主机名> <本地分支名>:<远程分支名>
如：git push origin master
将本地的master分支推送到origin主机的master分支。如果master不存在，则会被新建。
如果省略本地分支名，则表示删除指定的远程分支，因为这等同于推送一个空的本地分支到远程分支。
git push origin :master
git push origin --delete master


git push origin
将当前分支推送到origin主机的对应分支。如果当前分支只有一个追踪分支，那么主机名都可以省略
git push

####ADB####