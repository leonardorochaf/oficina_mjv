CREATE TABLE defeito (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL
);

CREATE TABLE peca (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL
);

CREATE TABLE veiculo (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL
);

CREATE TABLE registro (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome_cliente VARCHAR(255) NOT NULL,
    data DATE NOT NULL,
    hora VARCHAR(255) NOT NULL,
    veiculo_id INT NOT NULL,
    FOREIGN KEY (veiculo_id) REFERENCES veiculo(id)
);

CREATE TABLE defeito_peca (
    id INT AUTO_INCREMENT PRIMARY KEY,
    defeito_id INT NOT NULL,
    peca_id INT NOT NULL,
    FOREIGN KEY (defeito_id) REFERENCES defeito(id),
    FOREIGN KEY (peca_id) REFERENCES peca(id)
);

CREATE TABLE peca_veiculo (
    peca_id INT NOT NULL,
    veiculo_id INT NOT NULL,
    PRIMARY KEY (peca_id, veiculo_id),
    FOREIGN KEY (peca_id) REFERENCES peca(id),
    FOREIGN KEY (veiculo_id) REFERENCES veiculo(id)
);

CREATE TABLE registro_peca_defeito (
    registro_id INT NOT NULL,
    defeito_peca_id INT NOT NULL,
    PRIMARY KEY (registro_id, defeito_peca_id),
    FOREIGN KEY (registro_id) REFERENCES registro(id),
    FOREIGN KEY (defeito_peca_id) REFERENCES defeito_peca(id)
);