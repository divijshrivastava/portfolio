#!/bin/bash

# Remote server details


remote_server_ip=$1
remote_server_username=$2
remote_script_path=$3

# Path to remote script

# SSH into remote server and execute script

scp  ./build/libs/portfolio-web.war $remote_server_username@$remote_server_ip:~/divij_tech/portfolio-web.war

#ssh -t $remote_server_username@$remote_server_ip "bash $remote_script_path"
