# RegistroDeHoras
Desafio greenmile
A API contem os endpoints:
1. User:

1.1. Cadastro de usuário

1.2. Listar todos os usuários

2. Registro de horas:

2.1. Listar todos os registros de horas de um determinado usuário.

2.2. Registrar horas trabalhadas

3. Segurança:

3.1. Todas as requests que geram escrita no banco de dados (2.2, 1.1) devem exigir
um usuário logado.

3.2. Todas as requests de leitura (1.2, 2.1) podem ser realizadas por usuários
anônimos.

3.3. Utilizar JWT (https://jwt.io/) para autenticação.
