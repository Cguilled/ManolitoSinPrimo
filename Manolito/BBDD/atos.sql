-- phpMyAdmin SQL Dump
-- version 4.8.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 14-12-2018 a las 09:56:44
-- Versión del servidor: 10.1.34-MariaDB
-- Versión de PHP: 7.2.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `atos`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `roles`
--

CREATE TABLE `roles` (
  `codigo_rol` int(11) NOT NULL,
  `rol` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `roles`
--

INSERT INTO `roles` (`codigo_rol`, `rol`) VALUES
(1, 'administrador'),
(2, 'usuario'),
(3, 'rol');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `rol_tarea`
--

CREATE TABLE `rol_tarea` (
  `codigo_rol` int(100) NOT NULL,
  `codigo_tarea` int(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `rol_tarea`
--

INSERT INTO `rol_tarea` (`codigo_rol`, `codigo_tarea`) VALUES
(1, 1),
(2, 2),
(2, 3),
(2, 4),
(3, 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tareas`
--

CREATE TABLE `tareas` (
  `codigo_tarea` int(5) NOT NULL,
  `tarea` varchar(100) NOT NULL,
  `descTarea` varchar(200) NOT NULL,
  `estado` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `tareas`
--

INSERT INTO `tareas` (`codigo_tarea`, `tarea`, `descTarea`, `estado`) VALUES
(1, 'tarea1', 'desc 1', 0),
(2, 'tarea 2', 'desc 2', 1),
(3, 'tarea3', 'desc 3', 0),
(4, 'tarea4', 'desc 4', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `DAS` varchar(7) NOT NULL,
  `correo` varchar(50) NOT NULL,
  `nombre` varchar(20) NOT NULL,
  `apellido` varchar(100) NOT NULL,
  `password` varchar(20) NOT NULL,
  `primer_registro` tinyint(1) NOT NULL DEFAULT '1',
  `acceso` tinyint(1) NOT NULL DEFAULT '0',
  `codigo_rol` int(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`DAS`, `correo`, `nombre`, `apellido`, `password`, `primer_registro`, `acceso`, `codigo_rol`) VALUES
('m000000', 'correo@gmail.com', 'Nombre', 'Apellidos', 'Hp9xTl9TSc7', 0, 0, 1),
('M000001', 'admin@admin.com', 'Diego', 'Sánchez Fernández', 'admin', 1, 1, 1),
('M000002', 'usuario@usuario.com', 'Miguel', 'Carrasco González', 'usuario', 0, 1, 2),
('M000003', 'usu@usu.com', 'David', 'Hinojosa Zamora', 'usuario', 1, 1, 2),
('M000004', 'correo@inexistente.com', 'Guille', 'Cermeño Pesado', 'usuario', 1, 0, 2);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`codigo_rol`);

--
-- Indices de la tabla `rol_tarea`
--
ALTER TABLE `rol_tarea`
  ADD PRIMARY KEY (`codigo_rol`,`codigo_tarea`),
  ADD KEY `codigo_tarea` (`codigo_tarea`);

--
-- Indices de la tabla `tareas`
--
ALTER TABLE `tareas`
  ADD PRIMARY KEY (`codigo_tarea`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`DAS`),
  ADD UNIQUE KEY `correo` (`correo`),
  ADD KEY `codigo_rol` (`codigo_rol`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `roles`
--
ALTER TABLE `roles`
  MODIFY `codigo_rol` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `tareas`
--
ALTER TABLE `tareas`
  MODIFY `codigo_tarea` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `rol_tarea`
--
ALTER TABLE `rol_tarea`
  ADD CONSTRAINT `rol_tarea_ibfk_1` FOREIGN KEY (`codigo_rol`) REFERENCES `roles` (`codigo_rol`),
  ADD CONSTRAINT `rol_tarea_ibfk_2` FOREIGN KEY (`codigo_tarea`) REFERENCES `tareas` (`codigo_tarea`);

--
-- Filtros para la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD CONSTRAINT `usuarios_ibfk_1` FOREIGN KEY (`codigo_rol`) REFERENCES `roles` (`codigo_rol`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
