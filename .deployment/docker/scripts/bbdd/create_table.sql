-- Crear tabla Hotel si no existe
CREATE TABLE IF NOT EXISTS Hotel (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    address VARCHAR(255),
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Crear tabla Client si no existe
CREATE TABLE IF NOT EXISTS Client (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    address VARCHAR(255),
    phone VARCHAR(50),
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Crear tabla HotelBooking si no existe
CREATE TABLE IF NOT EXISTS hotel_booking (
    id BIGSERIAL PRIMARY KEY, -- ID autoincremental para PostgreSQL
    name VARCHAR(255), -- Nombre de la reserva
    address VARCHAR(255), -- Dirección de la reserva
    created_date DATE DEFAULT CURRENT_DATE, -- Fecha de creación con valor predeterminado de la fecha actual
    hotel_id BIGINT, -- Clave foránea que referencia a la tabla Hotel
    client_id BIGINT, -- Clave foránea que referencia a la tabla Client
    CONSTRAINT fk_hotel
        FOREIGN KEY (hotel_id) REFERENCES hotel(id) ON DELETE CASCADE, -- Relación con Hotel
    CONSTRAINT fk_client
        FOREIGN KEY (client_id) REFERENCES client(id) ON DELETE CASCADE -- Relación con Client
);


