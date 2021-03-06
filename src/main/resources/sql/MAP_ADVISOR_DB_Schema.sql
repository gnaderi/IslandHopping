CREATE TABLE IF NOT EXISTS TILE_TYPE (
  ID   INT PRIMARY KEY  NOT NULL,
  TYPE VARCHAR(255)     NOT NULL,
);


CREATE TABLE IF NOT EXISTS MAP (
  ID   INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
  NAME VARCHAR(255)                   NOT NULL
);
CREATE TABLE IF NOT EXISTS TILE (
  ID      INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
  X       INT                            NOT NULL,
  Y       INT                            NOT NULL,
  TYPE_ID INT                            NOT NULL,
  MAP_ID  INT                            NOT NULL,

  FOREIGN KEY (TYPE_ID) REFERENCES TILE_TYPE (ID),
  FOREIGN KEY (MAP_ID) REFERENCES MAP (ID)
);

CREATE TABLE IF NOT EXISTS ISLAND (
  ID     INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
  MAP_ID INT                            NOT NULL,
  FOREIGN KEY (MAP_ID) REFERENCES MAP (ID)

);
CREATE TABLE IF NOT EXISTS ISLAND_AREA (
  ID        INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
  ISLAND_ID INT                            NOT NULL,
  TILE_ID   INT                            NOT NULL,
  FOREIGN KEY (ISLAND_ID) REFERENCES ISLAND (ID),
  FOREIGN KEY (TILE_ID) REFERENCES TILE (ID)

);
