/* ALUNO */
INSERT INTO `aluno` (`COD_ALUNO`, `COD_CURSO`, `NUM_RA`, `NOM_ALUNO`, `NUM_CPF_ALUNO`, `DES_EMAIL`, `IND_DEFICIENTE`) VALUES ('1', '10', '11223344', 'Aluno Teste', '11111111111', 'aluno@newtonpaiva.br', 'N');

/* USUARIO */
INSERT INTO `usuario` (`COD_USUARIO`,`NOM_USUARIO`,`DES_LOGIN`,`COD_SENHA`,`DES_EMAIL`) VALUES (1,'Administrador','admin','123','admin@newtonpaiva.br');

/* EMPRESA */
INSERT INTO `empresa` (`COD_EMPRESA`, `NOM_EMPRESA`, `NUM_CNPJ`, `DES_ENDERECO`, `NOM_BAIRRO`, `NUM_CEP`, `NOM_CIDADE`, `DES_UF`) VALUES (1, 'Newton Paiva', '16521155000103', 'R Jose Claudio Rezende, 420', 'Estoril', '30494230', 'Belo Horizonte', 'MG');
