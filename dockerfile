FROM tomcat
ADD ./docker_files/ROOT.war /usr/local/tomcat/webapps/ROOT.war
ADD ./docker_files/bin/nltk /bin/bin/nltk
ADD ./docker_files/bin/summary /bin/bin/summary
ADD ./docker_files/nltk_data /usr/share/nltk_data
ADD ./docker_files/main.py /root/main.py
RUN apt-get update && apt-get full-upgrade -y && apt-get install python3 -y && apt-get install python3-pip -y
RUN pip3 install requests nltk bs4 django newspaper3k && python3 /root/main.py
RUN echo alias python='/usr/bin/python3.7' >> /root/.bashrc

CMD ["catalina.sh", "run"]