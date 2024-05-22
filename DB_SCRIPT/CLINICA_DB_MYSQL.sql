--PRIMERO EJECUTAR ESTA SENTENCIA DE MANERA AISLADA!
CREATE DATABASE CLINICA_BD;
-----------------------------------------------------
--DESPUÉS EJECUTAR LAS SIGUIENTES SENTENCIAS
USE CLINICA_BD;

CREATE TABLE USUARIOS(
    CODIGO SMALLINT NOT NULL AUTO_INCREMENT,
    NOMBRE VARCHAR(50) NOT NULL,
    CONTRASENYA VARCHAR(50) NOT NULL,
    CONSTRAINT PK_USUARIOS PRIMARY KEY(CODIGO)
);

CREATE TABLE PACIENTES(
	CODIGO SMALLINT AUTO_INCREMENT,
    NOMBRE VARCHAR(50) NOT NULL,
    DIRECCION VARCHAR(500) NOT NULL,
    CIUDAD VARCHAR(50) NOT NULL,
    TELEFONO VARCHAR(12) NOT NULL,
    DIABETICO BIT(1) NOT NULL,
    FECHA_NACIMIENTO DATETIME NOT NULL,
    TURNO SMALLINT NOT NULL,
    ID_USUARIO SMALLINT NOT NULL,
    CONSTRAINT PK_PACIENTES PRIMARY KEY(CODIGO),
    CONSTRAINT FK_USUARIOS FOREIGN KEY(ID_USUARIO) REFERENCES USUARIOS(CODIGO)
);

CREATE TABLE VISITAS(
	CODIGO SMALLINT NOT NULL AUTO_INCREMENT,
    ID_PACIENTE SMALLINT NOT NULL,
    FECHA_VISITA DATETIME NOT NULL,
    ENFERMEDAD VARCHAR(100) NOT NULL,
    IMPORTE DECIMAL(6,2) NOT NULL,
    PORCENTAJE_PAGO DECIMAL(3) NOT NULL,
    PROXIMA_VISITA DATETIME NOT NULL,
    CONSTRAINT PK_VISITAS PRIMARY KEY(CODIGO),
    CONSTRAINT FK_CODIGO_PACIENTE FOREIGN KEY(ID_PACIENTE) REFERENCES PACIENTES(CODIGO)
);

--FIN DE LA CREACIÓN DE LA BASE DE DATOS.

--INSERCIO DE LOS DATOS DE USURAIOS A MANO PQ TENIA LOS PACIENTES YA CREADOS

INSERT INTO USUARIOS (NOMBRE, CONTRASENYA)
VALUES
('Juan Pérez', '1234'),
('Luis Fernández', '1234'),
('Ana Martínez', '1234'),
('Carlos Gómez', '1234'),
('Laura López', '1234'),
('Miguel Sánchez', '1234'),
('Carmen Ramírez', '1234'),
('Javier Torres', '1234'),
('Elena Ruiz', '1234'),
('Alberto Vázquez', '1234'),
('Isabel Morales', '1234'),
('Pedro Gómez', '1234'),
('Rosa Medina', '1234'),
('Raúl Ortiz', '1234'),
('Teresa Delgado', '1234'),
('Andrés Vega', '1234'),
('Marta Castro', '1234'),
('Francisco Soto', '1234'),
('Lucía Reyes', '1234');

--INSERCIÓN DE DATOS. INSERCIÓN DE DATOS EN PACIENTES HECHA POR CHATGPT

-- Insertar 20 pacientes en la tabla PACIENTES con datos de España
INSERT INTO PACIENTES (NOMBRE, DIRECCION, CIUDAD, TELEFONO, DIABETICO, FECHA_NACIMIENTO, TURNO, ID_USUARIO)
VALUES
('Juan Pérez', 'Calle Mayor 10', 'Madrid', '600123456', 0, '1980-05-15 00:00:00', 1, 1),
('Luis Fernández', 'Calle de la Luna 5', 'Valencia', '600789012', 0, '1985-08-30 00:00:00', 3, 2),
('Ana Martínez', 'Calle del Sol 8', 'Sevilla', '600890123', 1, '1975-12-10 00:00:00', 1, 3),
('Carlos Gómez', 'Calle de la Estrella 3', 'Zaragoza', '600901234', 0, '2000-03-20 00:00:00', 2, 4),
('Laura López', 'Avenida del Prado 12', 'Málaga', '600012345', 1, '1995-06-15 00:00:00', 3, 5),
('Miguel Sánchez', 'Calle del Río 7', 'Murcia', '600234567', 0, '1982-01-10 00:00:00', 1, 6),
('Carmen Ramírez', 'Calle de la Sierra 9', 'Palma', '600345678', 1, '1998-09-25 00:00:00', 2, 7),
('Javier Torres', 'Calle del Mar 6', 'Las Palmas', '600456789', 0, '1987-11-05 00:00:00', 3, 8),
('Elena Ruiz', 'Calle del Viento 4', 'Bilbao', '600567890', 1, '1979-04-30 00:00:00', 1, 9),
('Alberto Vázquez', 'Avenida de la Libertad 15', 'Alicante', '600678901', 0, '1992-07-20 00:00:00', 2, 10),
('Isabel Morales', 'Calle de la Paz 11', 'Córdoba', '600789012', 1, '1983-02-15 00:00:00', 3, 11),
('Pedro Gómez', 'Calle del Amor 14', 'Valladolid', '600890123', 0, '1991-05-10 00:00:00', 1, 12),
('Rosa Medina', 'Calle del Parque 19', 'Vigo', '600901234', 1, '1986-10-20 00:00:00', 2, 13),
('Raúl Ortiz', 'Calle de la Esperanza 13', 'Gijón', '600012345', 0, '2001-12-25 00:00:00', 3, 14),
('Teresa Delgado', 'Calle de la Alegría 18', 'Santander', '600234567', 1, '1984-03-15 00:00:00', 1, 15),
('Andrés Vega', 'Calle del Carmen 16', 'Pamplona', '600345678', 0, '1993-06-20 00:00:00', 2, 16),
('Marta Castro', 'Calle de la Libertad 2', 'Burgos', '600456789', 1, '1996-09-30 00:00:00', 3, 17),
('Francisco Soto', 'Calle de la Paz 17', 'Logroño', '600567890', 0, '1988-11-25 00:00:00', 1, 18),
('Lucía Reyes', 'Calle de la Luz 20', 'Salamanca', '600678901', 1, '1978-01-05 00:00:00', 2, 19);

--INSERCIÓN DE DATOS. INSERCIÓN DE DATOS EN VISITAS HECHA POR CHATGPT

-- Lista de enfermedades para variedad en las visitas
SET @enfermedades = 'Gripe,Faringitis,Bronquitis,Diabetes,Control General,Infección Urinaria,Gastroenteritis,Artritis,Alergia,Control Cardiovascular';

-- Crear el procedimiento para insertar visitas
DELIMITER $$

CREATE PROCEDURE InsertarVisitas()
BEGIN
    DECLARE done INT DEFAULT FALSE;
    DECLARE paciente_id INT;
    DECLARE num_visitas INT;
    DECLARE visita_id INT;
    DECLARE fecha_visita DATETIME;
    DECLARE prox_visita DATETIME;
    DECLARE enfermedad VARCHAR(100);
    DECLARE importe DECIMAL(6,2);
    DECLARE porcentaje_pago DECIMAL(5,2);

    -- Cursor para iterar sobre los pacientes
    DECLARE pacientes_cursor CURSOR FOR SELECT CODIGO FROM PACIENTES;
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;

    -- Abrir el cursor
    OPEN pacientes_cursor;

    -- Iterar sobre los pacientes
    leer_pacientes: LOOP
        FETCH pacientes_cursor INTO paciente_id;
        IF done THEN
            LEAVE leer_pacientes;
        END IF;

        -- Determinar el número de visitas para el paciente
        SET num_visitas = FLOOR(RAND() * 10) + 1;

        -- Loop para cada visita del paciente
        SET visita_id = 0;
        WHILE visita_id < num_visitas DO
            -- Generar una fecha aleatoria para la visita dentro del último año
            SET fecha_visita = NOW() - INTERVAL FLOOR(RAND() * 365) DAY;
            SET prox_visita = fecha_visita + INTERVAL FLOOR(RAND() * 90) DAY;

            -- Seleccionar una enfermedad aleatoria
            SET enfermedad = ELT(FLOOR(RAND() * 10) + 1, 'Gripe', 'Faringitis', 'Bronquitis', 'Diabetes', 'Control General', 'Infección Urinaria', 'Gastroenteritis', 'Artritis', 'Alergia', 'Control Cardiovascular');

            -- Generar un importe y porcentaje de pago aleatorio
            SET importe = ROUND(RAND() * 100, 2);
            SET porcentaje_pago = ROUND(RAND() * 100, 2);

            -- Insertar la visita
            INSERT INTO VISITAS (ID_PACIENTE, FECHA_VISITA, ENFERMEDAD, IMPORTE, PORCENTAJE_PAGO, PROXIMA_VISITA)
            VALUES (paciente_id, fecha_visita, enfermedad, importe, porcentaje_pago, prox_visita);

            -- Incrementar el contador de visitas
            SET visita_id = visita_id + 1;
        END WHILE;

    END LOOP leer_pacientes;

    -- Cerrar el cursor
    CLOSE pacientes_cursor;
END$$

DELIMITER ;

-- Llamar al procedimiento para insertar las visitas
CALL InsertarVisitas();

--TERMINA HABIENDO 20 PACIENTES Y 180 VISITAS.