--
-- CADASTRAR CAMPO
--

DROP PROCEDURE IF EXISTS CadastrarCampo;
DELIMITER //
CREATE PROCEDURE CadastrarCampo(thisIdChamada INT, thisTitulo VARCHAR(40), thisTipo INT, thisDecimais INT, thisOpcional INT, thisJsonOpcoes TEXT)
BEGIN
    INSERT INTO CampoChamada
    (idChamada, titulo, tipo, decimais, opcional, jsonOpcoes)
    VALUES (thisIdChamada, thisTitulo, thisTipo, thisDecimais, thisOpcional, thisJsonOpcoes);
END //
DELIMITER ;

--
-- ALTERAR CAMPO
--

DROP PROCEDURE IF EXISTS AlterarCampo;
DELIMITER //
CREATE PROCEDURE AlterarCampo(thisId INT, thisIdChamada INT, thisTitulo VARCHAR(40), thisTipo INT, thisDecimais INT, thisOpcional INT, thisJsonOpcoes TEXT)
BEGIN
    UPDATE CampoChamada
    SET idChamada = thisIdChamada, 
    titulo = thisTitulo, 
    tipo = thisTipo, 
    decimais = thisDecimais, 
    opcional = thisOpcional, 
    jsonOpcoes = thisJsonOpcoes
    WHERE id = thisId;
END //
DELIMITER ;
