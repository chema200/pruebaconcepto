-- Insertar registros en Hotel si no existen
INSERT INTO Hotel (name, address) 
SELECT 'Hotel Example', 'Address 123' 
WHERE NOT EXISTS (SELECT 1 FROM Hotel WHERE name = 'Hotel Example');

-- Insertar registros en Client si no existen
INSERT INTO Client (name, address, phone) 
SELECT 'John Doe', 'Client Address', '555-1234' 
WHERE NOT EXISTS (SELECT 1 FROM Client WHERE name = 'John Doe');
