INSERT INTO mushroom_spot (coordinates, description)
VALUES
  (ST_SetSRID(ST_MakePoint(23.961, 59.004), 4326), 'Kukeseened'),
  (ST_SetSRID(ST_MakePoint(26.774, 59.139), 4326), 'Puravikud'),
  (ST_SetSRID(ST_MakePoint(25.247, 58.981), 4326), 'Männiriisikad'),
  (ST_SetSRID(ST_MakePoint(26.851, 58.028), 4326), 'Kärbseseened'),
  (ST_SetSRID(ST_MakePoint(25.013, 58.383), 4326), 'Pilvikud');