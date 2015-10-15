<b>Tudo que você precisa para um sistema web... </b><br>

Com uma combinação de Droid + Web. 
Confira no MarkFullDroid( https://github.com/MarcosJava/MarkFullDroid ), a necessidade 

Configuração de Building no maven, uma auta configuração sem a necessidade de configurar o servidor. 

Serviços : <br>
 -- REST( xml , json)<br>
 -- SOAP (?wsdl) <br>
<br>
Banco de Dados :<br>
 -- PostgreSQL <br>

Ferramenta Utilizada(frameworks):<br>
 -- JPA + HIBERNATE<br>
 -- JSF + PRIMEFACES<br>
 -- EJB 3.1<br>
 -- CDI<br>
 <br>
<b> Observações </b><br>
-- A utilização do EJB 3.1 foi apenas para demonstrar sua utilização. <br>
-- O Projeto dessa maneira poderia ficar em apenas CDI com JSF, sem a necessidade do EJB. <br>
 <br>
 <b> Necessidade :</b><br>
 -- JBoss as Wildfly  9.0.1 Final  instalado.<br>
 -- Git instalado na maquina.<br>
 -- Maven instalado.<br>
 -- PostgreSQL instalado.<br>
 -- PostgreSQL deverá conter o login de postgre e com a senha marcos. <br>
 -- PostgreSQL deverá ter uma database com o nome markfullone.<br>
<br>
<b> Rodando </b><br>
-- Deixe o servidor rodando na porta 8080.<br>
-- Abra a pasta do projeto .<br>
-- Execute os comandos dentro do arquivo onde se encontra o arquivo pom.xml.<br>
mvn clean<br>
mvn install <br>
mvn package <br>
mvn widfly:deploy<br>
