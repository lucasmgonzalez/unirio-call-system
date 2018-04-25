--
-- REGISTRAR INSCRICAO CHAMADA
--

DROP PROCEDURE IF EXISTS RegistrarInscricaoChamada;
DELIMITER //
CREATE PROCEDURE RegistrarInscricaoChamada(thisChamada INT, idUsuario INT)
BEGIN
    INSERT INTO InscricaoChamada (idIdChamada, idUsuario, dataRegistro, dataAtualizacao, dataInscricao)
    VALUES (thisIdChamada, thisIdUsuario, NOW(), NOW(), NOW());
END //
DELIMITER ;

--
-- ALTERAR INSCRICAO CHAMADA
--

DROP PROCEDURE IF EXISTS AtualizarIncricaoChamada;
DELIMITER //
CREATE PROCEDURE AtualizarIncricaoChamada(thisId INT, thisIdChamada INT, idUsuario INT)
BEGIN
    UPDATE InscricaoChamada
    SET dataAtualizacao = NOW()
    WHERE id = thisId
END //
DELIMITER ;


--
-- CANCELAR INSCRICAO CHAMADA
--

DROP PROCEDURE IF EXISTS CancelarInscricao;
DELIMITER //
CREATE PROCEDURE CancelarInscricao(thisId INT)
BEGIN
    DELETE FROM InscricaoChamada
    WHERE id = thisId;
END //
DELIMITER ;
