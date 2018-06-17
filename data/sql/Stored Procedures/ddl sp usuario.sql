--
-- CRIA UM NOVO USUARIO
--

DROP PROCEDURE IF EXISTS RegistraUsuario;
DELIMITER //
CREATE PROCEDURE RegistraUsuario(newNome VARCHAR(80), newEmail VARCHAR(80), newSenha VARCHAR(255), OUT id INT)
BEGIN
	INSERT INTO Usuario (dataRegistro, dataAtualizacao, nome, email, senha)
	VALUES (NOW(), NOW(), newNome, newEmail, newSenha);

	SET id = LAST_INSERT_ID();
END //
DELIMITER ;

--
-- ATRIBUI A SENHA DE UM USUARIO
--

DROP PROCEDURE IF EXISTS AtribuirSenha;
DELIMITER //
CREATE PROCEDURE AtribuirSenha(thisId INT, newSenha VARCHAR(1024))
BEGIN
	UPDATE Usuario SET senha = newSenha WHERE id = thisId;
END //
DELIMITER ;

--
-- ASSOCIA O TOKEN SOCIAL DE UM USUARIO
--

DROP PROCEDURE IF EXISTS AssociarTokenSocial;
DELIMITER //
CREATE PROCEDURE AssociarTokenSocial(token VARCHAR(1024))
BEGIN
	INSERT INTO Usuario (token)
	VALUES (token);

END //
DELIMITER ;

--
-- ATUALIZA A SENHA DE UM USUARIO
--

DROP PROCEDURE IF EXISTS TrocarSenha;
DELIMITER //
CREATE PROCEDURE TrocarSenha(thisId INT, newSenha VARCHAR(1024))
BEGIN
	UPDATE Usuario
	SET senha = newSenha,
	bloqueado = 0,
	dataAtualizacao = NOW()
	WHERE id = thisId;
END //
DELIMITER ;


--
-- REGISTRA UM LOGIN BEM SUCEDIDO DE UM USUARIO
--

DROP PROCEDURE IF EXISTS RegistraSucessoLogin;
DELIMITER //
CREATE PROCEDURE RegistraSucessoLogin(thisId INT)
BEGIN
	UPDATE Usuario
	SET dataUltimoLogin = NOW(),
	falhasLogin = 0,
	dataAtualizacao = NOW()
	WHERE id = thisId;
END //
DELIMITER ;


--
-- REGISTRA UM LOGIN MAL SUCEDIDO DE UM USUARIO
--

DROP PROCEDURE IF EXISTS RegistrarFalhaLogin;
DELIMITER //
CREATE PROCEDURE RegistrarFalhaLogin(thisId INT)
BEGIN
	DECLARE lTentativas INT;

	DECLARE CONTINUE HANDLER FOR SQLEXCEPTION ROLLBACK;
	START TRANSACTION;

	UPDATE Usuario
	SET falhasLogin = falhasLogin + 1
	WHERE id = thisId;

	SELECT falhasLogin
	INTO lTentativas
	FROM Usuario
	WHERE id = thisId;

	IF lTentativas >= 3 THEN
		UPDATE Usuario
		SET bloqueado = 1,
		dataAtualizacao = NOW()
		WHERE id = thisId;
	END IF;

  	COMMIT;
END //
DELIMITER ;


--
-- REGISTRA UM TOKEN DE LOGIN PARA UM USUARIO
--

DROP PROCEDURE IF EXISTS AssociarTokenEsqueciSenha;
DELIMITER //
CREATE PROCEDURE AssociarTokenEsqueciSenha(thisId INT, thisToken VARCHAR(1024))
BEGIN
	UPDATE Usuario
	SET dataAtualizacao = NOW(),
	tokenSenha = thisToken,
	dataTokenSenha = NOW(),
	dataAtualizacao = NOW()
	WHERE id = thisId;
END //
DELIMITER ;

--
-- DESASSOCIA UM TOKEN DE LOGIN PARA UM USUARIO
--

DROP PROCEDURE IF EXISTS DesassociarTokenEsqueciSenha;
DELIMITER //
CREATE PROCEDURE DesassociarTokenEsqueciSenha(thisId INT)
BEGIN
	UPDATE Usuario
	SET dataAtualizacao = NOW(),
	tokenSenha = NULL,
	dataTokenSenha = NOW(),
	dataAtualizacao = NOW()
	WHERE id = thisId;
END //
DELIMITER ;

--
-- ALTERA DADOS DE UM USUARIO
--

DROP PROCEDURE IF EXISTS AlterarDadosUsuario;
DELIMITER //
CREATE PROCEDURE AlterarDadosUsuario(thisId INT, thisNome VARCHAR(80), thisEmail VARCHAR(80))
BEGIN
	UPDATE Usuario
	SET nome = thisNome,
	email = thisEmail
	WHERE id = thisId;
END //
DELIMITER ;

--
-- DESBLOQUEIA ACESSO DE UM USUARIO
--

DROP PROCEDURE IF EXISTS DesbloquearUsuario;
DELIMITER //
CREATE PROCEDURE DesbloquearUsuario(thisId INT)
BEGIN
	UPDATE Usuario
	SET bloqueado = esseNome
	WHERE id = thisId;
END //
DELIMITER ;