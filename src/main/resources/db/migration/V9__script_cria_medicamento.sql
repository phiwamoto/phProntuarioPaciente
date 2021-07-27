CREATE TABLE medicamento (
  id INT NOT NULL AUTO_INCREMENT,
  data_criacao date NULL,    
  descricao VARCHAR(350) NULL,
  id_medicamento_quantidade int(11) DEFAULT NULL,
  id_medicamento_uso int(11) DEFAULT NULL,  
  PRIMARY KEY (id),
  KEY fk_medicamento_quantidade (id_medicamento_quantidade),
  CONSTRAINT fk_medicamento_quantidade FOREIGN KEY (id_medicamento_quantidade) REFERENCES medicamento_quantidade (id),
  KEY fk_medicamento_uso (id_medicamento_uso),
  CONSTRAINT fk_medicamento_uso FOREIGN KEY (id_medicamento_uso) REFERENCES medicamento_uso (id)    
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;