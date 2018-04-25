--
-- CADASTRAR CHAMADA
--

DROP PROCEDURE IF EXISTS CadastrarChamada;
DELIMITER //
CREATE PROCEDURE CadastrarChamada(thisNome VARCHAR(80), thisSigla VARCHAR(10), thisDataAbertura TIMESTAMP, thisDataEncerramento TIMESTAMP, OUT id INT)
BEGIN
    INSERT INTO Chamada (nome, sigla, dataAbertura, dataEncerramento)
    VALUES (thisNome, thisSigla, thisDataAbertura, thisDataEncerramento);
    
    SET id = LAST_INSERT_ID();
END //
DELIMITER ;

--
-- ALTERAR CHAMADA
--

DROP PROCEDURE IF EXISTS AlterarChamada;
DELIMITER //
CREATE PROCEDURE AlterarChamada(thisId INT, thisNome VARCHAR(80), thisSigla VARCHAR(10), thisDataAbertura TIMESTAMP, thisDataEncerramento TIMESTAMP)
BEGIN
    UPDATE Chamada
    SET nome = thisNome,
    sigla = thisSigla,
    dataAbertura = thisDataAbertura, 
    dataEncerramento = thisDataEncerramento
    WHERE id = thisId
END //
DELIMITER ;

--
-- CANCELAR CHAMADA
--

DROP PROCEDURE IF EXISTS CancelarChamada;
DELIMITER //
CREATE PROCEDURE CancelarChamada(thisId INT)
BEGIN
    UPDATE Chamada
    SET cancelada = 1
    WHERE id = thisId
END //
DELIMITER ;

--
-- ENCERRAR CHAMADA
--

DROP PROCEDURE IF EXISTS EncerrarChamada;
DELIMITER //
CREATE PROCEDURE EncerrarChamada(thisId INT)
BEGIN
    UPDATE Chamada
    SET encerrada = 1
    WHERE id = thisId
END //
DELIMITER ;
