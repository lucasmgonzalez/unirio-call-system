--
-- ASSOCIAR UM GESTOR A UMA UNIDADE FUNCIONAL
--

DROP PROCEDURE IF EXISTS AssociarGestorUnidadeFuncional;
DELIMITER //
CREATE PROCEDURE AssociarGestorUnidadeFuncional(thisIdGestor INT, thisIdUnidade INT)
BEGIN
    INSERT INTO GestorUnidadaFuncional (idUnidade, idGestor)
    VALUES (thisIdUnidade, thisIdGestor);
END //
DELIMITER ;

--
-- DESASSOCIAR UM GESTOR A UMA UNIDADE FUNCIONAL
--

DROP PROCEDURE IF EXISTS DesassociarGestorUnidadeFuncional;
DELIMITER //
CREATE PROCEDURE DesassociarGestorUnidadeFuncional(thisIdGestor INT, thisIdUnidade INT)
BEGIN
    DELETE FROM GestorUnidadaFuncional
    WHERE idUnidade = thisIdUnidade
    AND idGestor = thisIdGestor;
END //
DELIMITER ;
