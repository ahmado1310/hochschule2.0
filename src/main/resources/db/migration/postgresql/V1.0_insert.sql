INSERT INTO dekan (id, name)
VALUES
    ('123e4567-e89b-12d3-a456-426614174000', 'Dr. Max Mustermann'),
    ('123e4567-e89b-12d3-a456-426614174001', 'Prof. Maria Musterfrau');

INSERT INTO buero (id, gebaeude)
VALUES
    ('123e4567-e89b-12d3-a456-426614174100', 'Gebäude A'),
    ('123e4567-e89b-12d3-a456-426614174101', 'Gebäude B');

INSERT INTO fakultaet (id, name, dekan_id)
VALUES
    ('123e4567-e89b-12d3-a456-426614174200', 'Informatik', '123e4567-e89b-12d3-a456-426614174000'),
    ('123e4567-e89b-12d3-a456-426614174201', 'Biologie', '123e4567-e89b-12d3-a456-426614174001');

INSERT INTO professor (id, name, buero_id, fakultaet_id)
VALUES
    ('123e4567-e89b-12d3-a456-426614174300', 'Prof. Johann Schmidt', '123e4567-e89b-12d3-a456-426614174100', '123e4567-e89b-12d3-a456-426614174200'),
    ('123e4567-e89b-12d3-a456-426614174301', 'Prof. Anna Schulz', '123e4567-e89b-12d3-a456-426614174101', '123e4567-e89b-12d3-a456-426614174201');
