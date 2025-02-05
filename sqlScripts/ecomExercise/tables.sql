-- Tabela para clientes
CREATE TABLE Client (
    Id INT AUTO_INCREMENT PRIMARY KEY, 
    Nif INT NOT NULL,
    Name VARCHAR(100) NOT NULL,              
    Address VARCHAR(200) NOT NULL,
    Email VARCHAR(50) NOT NULL
);


CREATE TABLE Order (
    Id INT AUTO_INCREMENT PRIMARY KEY, 
    ClientId INT NOT NULL,                   
    Date DATE NOT NULL,
    Total DOUBLE NOT NULL,                
    PaymentMethod VARCHAR(50) NOT NULL,      
    FOREIGN KEY (ClientId) REFERENCES Client(Id)
);

CREATE TABLE Product (
    Id INT AUTO_INCREMENT PRIMARY KEY,
    Name VARCHAR(100) NOT NULL,        
    Price DOUBLE NOT NULL                     
);

CREATE TABLE OrderProduct (
    Id INT AUTO_INCREMENT PRIMARY KEY,    
    OrderId INT NOT NULL,                 
    ProductId INT NOT NULL,                   
    Quantity INT NOT NULL,                   
    Price DOUBLE NOT NULL,           
    FOREIGN KEY (OrderId) REFERENCES Order(Id), 
    FOREIGN KEY (ProductId) REFERENCES Product(Id)
);


INSERT INTO Client VALUES
(1, 123456789, 'Pedro', 'Porto', 'pedro@email.com'),
(2, 987654321, 'Joao', 'Aveiro', 'joao@email.com');

INSERT INTO Product VALUES
(1, 'Laptop', 999.99),
(2, 'Mouse', 29.99),
(3, 'Keyboard', 59.99);

INSERT INTO Order VALUES
(null, 1, '2024-01-15', (SELECT (1 * price) + (3 * price) FROM Product WHERE id IN (1,2)), 'Credit Card'),
(null, 2, '2024-01-20', (SELECT price FROM Product WHERE id = 3), 'PayPal');

INSERT INTO OrderProduct VALUES
(null, 1, 1, 1, 999.99),
(null, 1, 2, 3, 29.99),
(null, 2, 3, 1, 59.99);