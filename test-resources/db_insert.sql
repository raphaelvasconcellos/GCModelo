INSERT INTO `curso` (`COD_CURSO`, `NOM_CURSO`) VALUES (1, 'Direito');
INSERT INTO `curso` (`COD_CURSO`, `NOM_CURSO`) VALUES (2, 'Enfermagem');
INSERT INTO `curso` (`COD_CURSO`, `NOM_CURSO`) VALUES (3, 'Engenharia Civil');
INSERT INTO `curso` (`COD_CURSO`, `NOM_CURSO`) VALUES (4, 'Engenharia de Producao');
INSERT INTO `curso` (`COD_CURSO`, `NOM_CURSO`) VALUES (5, 'Engenharia Eletrica');
INSERT INTO `curso` (`COD_CURSO`, `NOM_CURSO`) VALUES (6, 'Engenharia Mecanica');
INSERT INTO `curso` (`COD_CURSO`, `NOM_CURSO`) VALUES (7, 'Engenharia Quimica');
INSERT INTO `curso` (`COD_CURSO`, `NOM_CURSO`) VALUES (8, 'Farmacia');
INSERT INTO `curso` (`COD_CURSO`, `NOM_CURSO`) VALUES (9, 'Odontologia');
INSERT INTO `curso` (`COD_CURSO`, `NOM_CURSO`) VALUES (10, 'Psicologia');
INSERT INTO `curso` (`COD_CURSO`, `NOM_CURSO`) VALUES (11, 'Sistemas de Informacao');

/* ALUNO */
INSERT INTO `aluno` (`COD_ALUNO`, `COD_CURSO`, `NUM_RA`, `NOM_ALUNO`, `NUM_CPF_ALUNO`, `DES_EMAIL`, `IND_DEFICIENTE`) VALUES ('1', '10', '11223344', 'Aluno Teste', '11111111111', 'aluno@newtonpaiva.br', 'N');
INSERT INTO `aluno` (`COD_ALUNO`, `COD_CURSO`, `NUM_RA`, `NOM_ALUNO`, `NUM_CPF_ALUNO`, `DES_EMAIL`, `IND_DEFICIENTE`) VALUES ('2', '1', '22334455', 'Aluno Deficiente', '22222222222', 'aluno.def@newtonpaiva.br', 'S');

/* USUARIO */
INSERT INTO `usuario` (`COD_USUARIO`,`NOM_USUARIO`,`DES_LOGIN`,`COD_SENHA`,`DES_EMAIL`) VALUES (1,'Administrador','admin','123','admin@newtonpaiva.br');

/* EMPRESA */
INSERT INTO `empresa` (`COD_EMPRESA`, `NOM_EMPRESA`, `NUM_CNPJ`, `DES_ENDERECO`, `NOM_BAIRRO`, `NUM_CEP`, `NOM_CIDADE`, `DES_UF`) VALUES (1, 'Newton Paiva', '16521155000103', 'R Jose Claudio Rezende, 420', 'Estoril', '30494230', 'Belo Horizonte', 'MG');

/* CONVENIO */
INSERT INTO `convenio` (`COD_CONVENIO`, `COD_EMPRESA`, `COD_CURSO`, `COD_CONVENIO_SITUACAO`, `DAT_VENCIMENTO`, `DAT_ASSINATURA`) VALUES ('1', '1', '1', '1', '2017-11-01', '2016-11-19');

/* CONTRATO */
INSERT INTO contrato (`COD_CONTRATO`, `COD_ALUNO`, `COD_EMPRESA`, `COD_CONTRATO_TIPO`, `COD_CONTRATO_SITUACAO_ATUAL`, `NUM_PROTOCOLO`, `DAT_ENTRADA`, `DAT_INICIO_ATUAL`, `QTD_CARGA_HORARIA_DIARIA`, `QTD_CARGA_HORARIA_SEMANAL`, `DES_OBSERVACAO`) VALUES ('1', '1', '1', '0', '0', '123456', '2016-10-01', '2016-10-05', '4', '10', 'Teste');
