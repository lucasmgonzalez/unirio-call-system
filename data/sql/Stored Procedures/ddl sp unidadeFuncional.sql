--
-- CRIA UMA UNIDADE FUNCIONAL
--

DROP PROCEDURE IF EXISTS CadastrarUnidadeFuncional;
DELIMITER //
CREATE PROCEDURE CadastrarUnidadeFuncional(newNome VARCHAR(80), newSigla VARCHAR(10), OUT id INT)
BEGIN
	INSERT INTO UnidadeFuncional (dataRegistro, dataAtualizacao, nome, sigla)
	VALUES (NOW(), NOW(), novoNome, newSigla);

	SET id = LAST_INSERT_ID();
END //
DELIMITER ;

--
-- ATUALIZA UNIDADE FUNCIONAL
--

DROP PROCEDURE IF EXISTS AlterarUnidadeFuncional;
DELIMITER //
CREATE PROCEDURE AlterarUnidadeFuncional(thisId INT, newNome VARCHAR(80), newSigla VARCHAR(10))
BEGIN
	UPDATE UnidadeFuncional
	SET nome = newNome,
	sigla = newSigla,

	WHERE id = thisId;
END //
DELIMITER ;


--
-- REMOVE UMA UNIDADE FUNCIONAL
--

DROP PROCEDURE IF EXISTS RemoverUnidadeFuncional;
DELIMITER //
CREATE PROCEDURE RemoverUnidadeFuncional(thisId INT)
BEGIN
	DELETE
	FROM UnidadeFuncional

	WHERE id = thisId;
END //
DELIMITER ;
