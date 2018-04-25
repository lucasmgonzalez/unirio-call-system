--
-- CADASTRAR CAMPO INSCRICAO CHAMADA
--

DROP PROCEDURE IF EXISTS CadastrarInscricaoCampoChamada;
DELIMITER //
CREATE PROCEDURE CadastrarInscricaoCampoChamada(thisIdInscricaoChamada INT, thisIdCampoChamada INT, thisValor VARCHAR(8192))
BEGIN
    INSERT INTO InscricaoCampoChamada (idInscricao, idCampoChamada, valor)
    VALUES (thisIdInscricaoChamada, thisIdCampoChamada, thisValor);
END //
DELIMITER ;

--
-- ALTERAR CAMPO INSCRICAO CHAMADA
--

DROP PROCEDURE IF EXISTS AlterarIncricaoCampoChamada;
DELIMITER //
CREATE PROCEDURE AlterarIncricaoCampoChamada(thisId INT, thisIdInscricaoChamada INT, thisIdCampoChamada INT, thisValor VARCHAR(8192))
BEGIN
    UPDATE InscricaoCampoChamada
    SET idInscricao = thisIdInscricaoChamada,
    idCampoChamada = thisIdCampoChamada,
    valor = thisValor
    WHERE id = thisId;
END //
DELIMITER ;
