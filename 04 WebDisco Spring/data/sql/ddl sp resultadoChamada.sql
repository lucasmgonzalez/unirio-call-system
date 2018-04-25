--
-- REGUSTRAR RESULTADO CHAMADA
--

DROP PROCEDURE IF EXISTS RegistrarResultadoChamada;
DELIMITER //
CREATE PROCEDURE RegistrarResultadoChamada(thisIdChamada INT, thisValor VARCHAR(8192))
BEGIN
    INSERT INTO ResultadoChamada (idChamada, valor)
    VALUES (thisIdChamada, thisValue);
END //
DELIMITER ;
