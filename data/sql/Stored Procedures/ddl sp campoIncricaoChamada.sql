--
-- REGISTRAR VALOR DO CAMPO
--

DROP PROCEDURE IF EXISTS CadastrarInscricaoCampoChamada;
DELIMITER //
CREATE PROCEDURE CadastrarInscricaoCampoChamada(thisIdInscricaoChamada INT, thisIdCampoChamada INT, thisValor VARCHAR(8192))
BEGIN
    
    DECLARE lCampoId INT;
    
    SELECT id INTO lCampoId FROM InscricaoCampoChamada 
    WHERE idIncricao = thisIdInscricaoChamada 
    AND idCampoChamada = thisIdCampoChamada;
    
	IF lCampoId IS NULL THEN
		CALL CadastrarInscricaoCampoChamada(thisInscricaoChamada, thisIdCampoChamada, thisValor);
    ELSE
        CALL AlterarInscricaoCampoChamada(lCampoId, thisValor);
	END IF;
    
END //
DELIMITER ;


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
CREATE PROCEDURE AlterarIncricaoCampoChamada(thisId INT, thisValor VARCHAR(8192))
BEGIN
    UPDATE InscricaoCampoChamada
    SET valor = thisValor
    WHERE id = thisId;
END //
DELIMITER ;
