# TomcatベースのJava環境を構築
FROM tomcat:9.0-jdk17-temurin

# WARファイルをTomcatのwebappsディレクトリにコピー
COPY ./PotofolioProject.war /usr/local/tomcat/webapps/ROOT.war

# ポート公開
EXPOSE 8080
