DELETE FROM `nutri_op`.`op803trb`;
DELETE FROM `nutri_op`.`op906ope`;
DELETE FROM `nutri_op`.`op000tur`;
DELETE FROM `nutri_op`.`op000ban`;
DELETE FROM `nutri_op`.`op000cop`;
DELETE FROM `nutri_op`.`op000eve`;
DELETE FROM `nutri_op`.`op000exp`;
DELETE FROM `nutri_op`.`op000maq`;
DELETE FROM `nutri_op`.`op011def`;
DELETE FROM `nutri_op`.`op018mtv`;
DELETE FROM `nutri_op`.`op070emp`;
DELETE FROM `nutri_op`.`op725cre`;
DELETE FROM `nutri_op`.`op900eoq`;
DELETE FROM `nutri_op`.`op900qdo`;
DELETE FROM `nutri_op`.`op930mpr`;
DELETE FROM `nutri_op`.`op000ope`;

/*------------------SINC -----------------op900qdo*/
DELETE FROM `nutri_op_sinc`.`op906ope`;
DELETE FROM `nutri_op_sinc`.`op803trb`;
DELETE FROM `nutri_op_sinc`.`op000tur`;
DELETE FROM `nutri_op_sinc`.`op000ban`;
DELETE FROM `nutri_op_sinc`.`op000cop`;
DELETE FROM `nutri_op_sinc`.`op000eve`;
DELETE FROM `nutri_op_sinc`.`op000exp`;
DELETE FROM `nutri_op_sinc`.`op000maq`;
DELETE FROM `nutri_op_sinc`.`op011def`;
DELETE FROM `nutri_op_sinc`.`op018mtv`;
DELETE FROM `nutri_op_sinc`.`op070emp`;
DELETE FROM `nutri_op_sinc`.`op725cre`;
DELETE FROM `nutri_op_sinc`.`op900eoq`;
DELETE FROM `nutri_op_sinc`.`op900qdo`;
DELETE FROM `nutri_op_sinc`.`op930mpr`;
DELETE FROM `nutri_op_sinc`.`op000ope`;

INSERT INTO `nutri_op`.`op000ope` (`CODOPE`, `DESOPE`) VALUES ('1', 'MENU');
INSERT INTO `nutri_op`.`op000ope` (`CODOPE`, `DESOPE`) VALUES ('2', 'PRODUÇÃO');
INSERT INTO `nutri_op`.`op000ope` (`CODOPE`, `DESOPE`) VALUES ('3', 'FINALIZADO');
INSERT INTO `nutri_op`.`op000ope` (`CODOPE`, `DESOPE`) VALUES ('4', 'APONTAMENTO PARADA');
INSERT INTO `nutri_op`.`op000ope` (`CODOPE`, `DESOPE`) VALUES ('5 ', 'AVISTO TRAVAMENTO');
INSERT INTO `nutri_op`.`op000ope` (`CODOPE`, `DESOPE`) VALUES ('6 ', 'CONFIGURAÇÃO');
INSERT INTO `nutri_op`.`op000ope` (`CODOPE`, `DESOPE`) VALUES ('7', 'REFUGO');
INSERT INTO `nutri_op`.`op000ope` (`CODOPE`, `DESOPE`) VALUES ('9', 'INICIO PRODUÇÃO');
INSERT INTO `nutri_op`.`op000ope` (`CODOPE`, `DESOPE`) VALUES ('10', 'LIBERADO PRODUÇÃO');

INSERT INTO `nutri_op`.`op000cop` (`CODCOP`,`CODOPE`,`HOROPE`,`DATAOPE`) VALUES (1,1,1,1);

INSERT INTO `nutri_op`.`op000exp` (`EXPERP`, `DESEXP`) VALUES ('1', 'EXPORTADO');
INSERT INTO `nutri_op`.`op000exp` (`EXPERP`, `DESEXP`) VALUES ('0', 'NÃO EXPORTADO');
INSERT INTO `nutri_op`.`op070emp` (`CODEMP`, `DESEMP`) VALUES ('1', 'NUTRIPLAN');
