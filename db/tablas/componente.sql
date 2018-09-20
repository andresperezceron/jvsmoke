CREATE TABLE componente(
id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
id_tipo INTEGER NOT NULL,
id_proveedor INTEGER NOT NULL,
referencia TEXT UNIQUE,
escandallo REAL NOT NULL,
descripcion TEXT NOT NULL,
FOREIGN KEY(id_tipo) REFERENCES tipo(id_tipo),
FOREIGN KEY(id_proveedor) REFERENCES proveedor(id)
);