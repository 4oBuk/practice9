# How to run
1. copy file mail-server.env.example with name (mail-server.env)
2. replace placeholders with your values
3. run mvn clean package -Dspring.mail.host=$MAIL_HOST  -Dspring.mail.port=$MAIL_PORT -Dspring.mail.username=$MAIL_USERNAME -Dspring.mail.password=$MAIL_PASSWORD

    set your values for properties to build .jar file
4. run in your terminal docker-compose up -d (-d to run detached)