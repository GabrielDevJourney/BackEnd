CREATE TABLE Patients (
    Id INT AUTO_INCREMENT PRIMARY KEY,
    Name VARCHAR(100) NOT NULL,
    Address VARCHAR(255)
);


CREATE TABLE Specialties (
    Id INT AUTO_INCREMENT PRIMARY KEY,
    Name VARCHAR(100) NOT NULL
);


CREATE TABLE Doctors (
    Id INT AUTO_INCREMENT PRIMARY KEY,
    Name VARCHAR(100) NOT NULL,
    SpecialtyID INT NOT NULL,
    FOREIGN KEY (SpecialtyID) REFERENCES Specialties(Id)
);


CREATE TABLE Appointments (
    Id INT AUTO_INCREMENT PRIMARY KEY,
    PatientID INT NOT NULL,
    DoctorID INT NOT NULL,
    Date DATETIME NOT NULL,
    FOREIGN KEY (PatientID) REFERENCES Patients(Id),
    FOREIGN KEY (DoctorID) REFERENCES Doctors(Id)
);


CREATE TABLE Diagnoses (
    Id INT AUTO_INCREMENT PRIMARY KEY,
    Description VARCHAR(255) NOT NULL,
    AppointmentID INT NOT NULL,
    FOREIGN KEY (AppointmentID) REFERENCES Appointments(Id)
);


CREATE TABLE Medications (
    Id INT AUTO_INCREMENT PRIMARY KEY,
    Name VARCHAR(100) NOT NULL,
    Description VARCHAR(255) 
);


CREATE TABLE DiagnosisMedications (
    Id INT AUTO_INCREMENT PRIMARY KEY,
    DiagnosisID INT NOT NULL,
    MedicationID INT NOT NULL,
    FOREIGN KEY (DiagnosisID) REFERENCES Diagnoses(Id),
    FOREIGN KEY (MedicationID) REFERENCES Medications(Id)
);




INSERT INTO Patients (Name, Address) VALUES
('John Doe', '123 Main St'),
('Jane Smith', '456 Elm St');

INSERT INTO Specialties (Name) VALUES
('Cardiology'),
('Dermatology');

INSERT INTO Doctors (Name, SpecialtyID) VALUES
('Dr. Damian', 1),
('Dr. Ali', 2);

INSERT INTO Appointments (PatientID, DoctorID, Date) VALUES
(1, 1, '2023-01-01 10:00:00'),
(2, 2, '2023-02-01 14:30:00');

INSERT INTO Diagnoses (Description, AppointmentID) VALUES
('Cold', 1),
('Headche', 2);

INSERT INTO Medications (Name, Description) VALUES
('Paracetamol', 'Stop this please'),
('Brufen', 'Get some sleep');

INSERT INTO DiagnosisMedications (DiagnosisID, MedicationID) VALUES
(1, 1),
(2, 2);


SELECT diag.Description AS Diagnosis, meds.Name AS Medication
FROM Diagnoses diag
JOIN DiagnosisMedications diagMed ON diag.Id = diagMed.DiagnosisID
JOIN Medications meds ON diagMed.MedicationID = meds.Id
JOIN Appointments appointment ON diag.AppointmentID = appointment.Id
JOIN Patients patient ON appointment.PatientID = patient.Id
WHERE patient.Name = 'John Doe';